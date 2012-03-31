/*
 * @(#)MediaController.java	1.61 98/11/02
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv.media;

import java.util.Vector;
import java.util.Enumeration;
import java.util.EventListener;
import javax.media.*;
import com.sun.tv.media.util.*;
import com.sun.tv.media.controls.*;

/**
 * This class is a part of the porting layer implementation for JavaTV.
 * Media Controller implements the basic functionalities of a 
 * java.media.Controller.  These include:<
 * <ul>
 * <li> The clock calculations using the MediaClock helper class.
 * <li> The RealizeWorkThread and PrefetchWorkThread to implement realize() and
 *    prefetch() in the correct unblocking manner.
 * <li> The ListenerList to maintain the list of ControllerListener.
 * <li> Two ThreadedEventQueues for incoming and outgoing ControllerEvents.
 * </ul><p>
 * @version 1.61, 98/11/02
 */
public abstract class MediaController 
implements Controller, ControllerListener, Duration, EventListener {

    private int targetState = Unrealized;
    protected int state = Unrealized;
    private Vector listenerList = null;
    private SendEventQueue sendEvtQueue;
    private RecvEventQueue recvEvtQueue;
    private RealizeWorkThread realizeThread = null;
    private PrefetchWorkThread prefetchThread = null;
    private String processError = null;
    private MediaClock clock;	// Use the MediaClock to keep track of time
				// and for some calculations.
    private TimedStartThread startThread = null;
    private StopTimeThread stopTimeThread = null;

    protected Controller parent = null;
 
    /*AD
    protected SliderRegionControl regionControl = null;
 AD*/
 
  /**
  * constructs media controller. Initializes the send and receive queues
  * and the clock
  */
 	public MediaController() {
	// Initialize and start the event queue threads.
	    try {
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadArgs);
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadGroupArgs);
	    } catch (Exception e) {}

	sendEvtQueue = new SendEventQueue(this);
	sendEvtQueue.setName(sendEvtQueue.getName() + ": SendEventQueue: " +
			     this.getClass().getName());
	sendEvtQueue.start();
	recvEvtQueue = new RecvEventQueue(this);
	recvEvtQueue.setName(recvEvtQueue.getName() + ": RecvEventQueue: " +
			     this.getClass().getName());
	recvEvtQueue.start();
	clock = new MediaClock();
	//	clock.setMediaLength(getDuration());
    }

    /**
     * The stub function to perform the steps to realize the controller.
     * Call realize() for the public method.
     * This is called from a separately running thread.  So do take 
     * necessary precautions to protect shared resources.
     * It's OK to put an empty stub function body here.<p>
     * Return true if the realize is successful.  Return false and
     * set the processError string if failed.<p>
     * This function is not declared synchronized because first it is 
     * already guaranteed by realize() not to be called more than once 
     * simultaneously.  Secondly if this is synchronized, then other
     * synchronized methods, deallocate() and processEvent() will be
     * blocked since they are synchronized methods.
     * Override this to implement subclass behavior.
     * @return true if successful.
     */ 
    protected abstract boolean doRealize();

    /**
     * Called when the realize() is aborted, i.e. deallocate() was called
     * while realizing.  Release all resources claimed previously by the
     * realize() call.
     * Override this to implement subclass behavior.
     */
    protected abstract void abortRealize();

    /**
     * The stub function to perform the steps to prefetch the controller.
     * Call prefetch for the public method.
     * This is called from a separately running thread.  So do take 
     * necessary precautions to protect shared resources.
     * It's OK to put an empty stub function body here.<p>
     * Return true if the prefetch is successful.  Return false and
     * set the processError string if failed.<p>
     * This function is not declared synchronized because first it is 
     * already guaranteed by realize() not to be called more than once 
     * simultaneously.  Secondly if this is synchronized, then other
     * synchronized methods, deallocate() and processEvent() will be
     * blocked since they are synchronized methods.
     * Override this to implement subclass behavior.
     * @return true if successful.
     */ 
    protected abstract boolean doPrefetch();

    /**
     * Called when the prefetch() is aborted, i.e. deallocate() was called
     * while prefetching.  Release all resources claimed previously by the
     * prefetch call.
     * Override this to implement subclass behavior.
     */
    protected abstract void abortPrefetch();

    /**
     * Start immediately.
     * Invoked from start(tbt) when the scheduled start time is reached.
     * Use the public start(tbt) method for the public interface.
     * Override this to implement subclass behavior.
     */
    protected abstract void doStart();

    /**
     * Invoked from stop().
     * Override this to implement subclass behavior.
     */
    protected void doStop() {}

    /**
     * A subclass of this implement close to stop all threads to make
     * it "finalizable", i.e., ready to be garbage collected.
     */
    public void close() {
	// stop receive threads
	if (recvEvtQueue != null) {
	    recvEvtQueue.kill();
	    recvEvtQueue = null;
	}

//	if (realizeThread != null)
//	    realizeThread.stop();
//
//	if (prefetchThread != null)
//	    prefetchThread.stop();

//	if (startThread != null)
//	    startThread.stop();

//	if (stopTimeThread != null)
//	    stopTimeThread.stop();

	doClose();

	sendEvent(new ControllerClosedEvent(this));
    }

    /**
     * Invoked by close() to cleanup the Controller.
     * Override this to implement subclass behavior.
     */
    protected void doClose() {
    }

    /**
    * stops send and receive threads
    */
    final protected void cleanup() {
	// stop send threads
	if (sendEvtQueue != null) {
	    sendEvtQueue.kill();
	    sendEvtQueue = null;
	}

	// stop receive threads
	// This is not necessary when an user initiates a close, because
	// in that case the close method takes care of killing the
	// recvEvtQueue. But when a ControllerErrorEvent is posted by
	// one of the Controllers then all Controllers are effectively
	// closed and we need to kill the recvEvtQueue also.
	if (recvEvtQueue != null) {
	    recvEvtQueue.kill();
	    recvEvtQueue = null;
	}

    }
	
    /**
     * Set the timebase used by the controller.
     * i.e. all media-time to time-base-time will be computed with the
     * given time base.
     * The subclass should implement and further specialized this method
     * to do the real work.  But it should also invoke this method to
     * maintain the correct states.
     * @param tb the time base to set to.
     * @exception IncompatibleTimeBaseException is thrown if the Controller
     * cannot accept the given time base.
     */
    public void setTimeBase(TimeBase tb) throws IncompatibleTimeBaseException {
	if (state == Unrealized || state == Realizing)
	    throw new NotRealizedError("Cannot set time base on an unrealized controller.");
	clock.setTimeBase(tb);
    }

    /**
     * Return a list of <b>Control</b> objects this <b>Controller</b>
     * supports.
     * If there are no controls, then an array of length zero
     * is returned.
     *
     * @return list of <b>Controller</b> controls.
     */
    public Control[] getControls() {
      // Not implemented $$$
      // Is this correct ? $$$
      return new Control[0];
    }

    /**
     * Get the <code>Control</code> that supports the
     * class or interface specified. The full class
     * or interface name should be specified.
     * <code>Null</code> is returned if the <code>Control</code>
     * is not supported.
     *
     * @return <code>Control</code> for the class or interface
     * name.
     */
    public Control getControl(String forName) {
	return null;
    }

    /**
     * Start the controller.
     * Invoke clock.start() to maintain the clock states.
     * It starts a wait thread to wait for the given tbt.
     * At tbt, it will wake up and call doStart().
     * A subclass should implement the doStart() method to do
     * the real work. 
     * @param tbt the timebase time to start the controller.
     */
    public void syncStart(Time tbt) {
	if (state == Unrealized || state == Realizing 
	    || state == Realized || state == Prefetching) {
	    throw new NotPrefetchedError("Cannot start the controller before it has been prefetched.");
	}
	clock.syncStart(tbt); // Will generate ClockStartedError if state is Started
	state = Started;
	setTargetState(Started);
	sendEvent(new StartEvent(this, Prefetched, Started,
				 Started, getMediaTime(), tbt));
	if (activateStopThread()) {
	    // If the stop-time is set to a value that the Clock
	    // has already passed, the Clock immediately stops.
	    stopAtTime();
	    return;
	}

	// Schedule the start time.
	// startThread will wake up at the scheduled tbt and call the
	// protected doStart() method.
	    try {
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadArgs);
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadGroupArgs);
	    } catch (Exception e) {}
	startThread = new TimedStartThread(this, tbt.getNanoseconds());
	startThread.setName(startThread.getName() + " ( startThread: " + this + " )");
	startThread.start();
    }

    // Return true if the stop time has already passed
    /**
    * activates the stop thread if there is enough time until stop time
    *
    * @return true if the stop thread has been launched; otherwise false
    */
    private boolean activateStopThread() {
	long stopTime = getStopTime().getNanoseconds();

	if (stopTime != Long.MAX_VALUE) {
	    long timeToStop = (long) ((stopTime - getMediaTime().getNanoseconds()) / getRate());
	    // TODO: We don't support negative rates now but may do so in the future
	    // The if statement assumes that rates are positive and so has to modified 
            // to handle both positive and negative rates.
	    if (timeToStop > 100000000) {
		try {
		    JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						       JMFSecurity.threadArgs);
		    JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						       JMFSecurity.threadGroupArgs);
		} catch (Exception e) {}
		(stopTimeThread = new StopTimeThread(this, timeToStop)).start();
		return false;
	    } else {
		return true;
	    }
	}
	return false;
    }

    /**
     * Stop the controller.
     * Invoke clock.stop() to maintain the clock states.
     * The subclass should implement and further specialized this method
     * to do the real work.  But it should also invoke this method to
     * maintain the correct states.
     */
    public void stop() {
	if (state == Started) {
	    clock.stop();
	    state = Prefetched;
	    setTargetState(Prefetched);

	    if ( (stopTimeThread != null) && stopTimeThread.isAlive() ) {
		if (Thread.currentThread() != stopTimeThread) {
//		    stopTimeThread.stop();
		} else {
		}
	    }


	    // If start(tbt) was invoked and it hasn't reached the
	    // scheduled tbt yet, the startThread is spinning and
	    // we need to abort that.
	    if (startThread != null && startThread.isAlive())
		startThread.abort();

	    doStop();
	}
    }

	/**
	  * Stop because stop time has been reached.
	  * Subclasses should override this method.
	  */
	protected void stopAtTime() {
	}

    /**
     * Set the stop time.
     * Invoke clock.setStopTime() to maintain the clock states.
     * The subclass should implement and further specialized this method
     * to do the real work.  But it should also invoke this method to
     * maintain the correct states.
     * @param t the time to stop.
     */
    public void setStopTime(Time t) {
	if (state == Unrealized || state == Realizing)
	    throw new NotRealizedError("Cannot set stop time on an unrealized controller.");
	Time oldStopTime = getStopTime();
	clock.setStopTime(t);
	boolean stopTimeHasPassed = false;
	if (state == Started) {
	    if (activateStopThread())
		stopTimeHasPassed = true;
	}
	if ( oldStopTime.getNanoseconds() != t.getNanoseconds() ) {
	    sendEvent(new StopTimeChangeEvent(this, t));
	}
	if (stopTimeHasPassed) {
	    stopAtTime();
	}
    }

    /**
     * Get the preset stop time.
     * @return the preset stop time.
     */
    public Time getStopTime() {
	return clock.getStopTime();
    }

    /**
     * Set the media time.
     * Invoke clock.setMediaTime() to maintain the clock states.
     * The subclass should implement and further specialized this method
     * to do the real work.  But it should also invoke this method to
     * maintain the correct states.
     * @param now the media time to set to.
     */
    public void setMediaTime(Time now) {
	if ( (state == Unrealized) || (state == Realizing) )
	    throw new NotRealizedError("Cannot set media time on a unrealized controller");
	clock.setMediaTime(now);
	sendEvent(new MediaTimeSetEvent(this, getMediaTime()));
    }

    /**
     * Return the current media time.
     * Uses the clock to do the computation.  A subclass can override this
     * method to do the right thing for itself.
     * @return the current media time.
     */
    public Time getMediaTime() {
	return clock.getMediaTime();
    }

    /**
     * Get the current media time in nanoseconds.
     * @return the media time in nanoseconds.
     */
    public long getMediaNanoseconds() {
	return clock.getMediaNanoseconds();
    }

    /**
     * For subclasses to access to the theoretical clock time as computed
     * by MediaClock.  Normally, getMediaTime() would return the same
     * value.  But some classes might choose to override getMediaTime().
     * And in those cases, getMediaClockTime() will bypass that and give
     * you the theoretical time. 
     */
    final protected Time getMediaClockTime() {
	return clock.getMediaTime();
    }

    /**
     * Return the Sync Time.
     * Not yet implementated.
     **/
    public Time getSyncTime() {
	return new Time(0);
    }

    /**
     * Get the current time base.
     * @return the current time base.
     */
    public TimeBase getTimeBase() {
	if ( (state == Unrealized) || (state == Realizing) )
	    throw new NotRealizedError("Cannot get Time Base from an unrealized controller");

	return clock.getTimeBase();
    }

    /**
     * Map the given media-time to time-base-time.
     * @param t given media time.
     * @return timebase time.
     * @exception ClockStoppedException thrown if the Controller has already
     * been stopped.
     */
    public Time mapToTimeBase(Time t) throws ClockStoppedException {
	return clock.mapToTimeBase(t);
    }

    /**
     * Set the rate of presentation: 1.0: normal, 2.0: twice the speed.
     * -2.0: twice the speed in reverse.
     * Note that not all rates are supported.
     * Invokes clock.setRate() to maintain the clock states.
     * The subclass SHOULDN'T in general override this class.
     * If necessary, it should override the behavior using the
     * doSetRate method. By overriding the doSetRate method,
     * subclass Conrollers can support negative rates, fractional rates
     * etc., but they should guard against illegal rates from going into 
     * the clock calculations.
     * @param factor the rate to set to.
     * @return the actual rate used.
     */
    public float setRate(float factor) {
	if (state == Unrealized || state == Realizing)
	    throw new NotRealizedError("Cannot set rate on an unrealized controller.");

	float oldRate = getRate();
	float rateSet = doSetRate(factor);
	float newRate =  clock.setRate(rateSet);

	if (newRate != oldRate) {
	    sendEvent(new RateChangeEvent(this, newRate));
	}
	return newRate;
    }


    // Override this to implement subclass behavior.
    // Conrollers can override this method if they
    // can support negative rates, and/or have other
    // other restrictions.
    protected float doSetRate(float factor) {
	return factor;
    }

    /**
     * Get the current presentation speed.
     * @return the current presentation speed.
     */
    public float getRate() {
	return clock.getRate();
    }

    /**
     * Get the current state of the controller.
     * @return the current state of the controller.
     */
    final public int getState() {
	return state;
    }

    /**
     * Set the target state.
     */
    final protected /*synchronized*/ void setTargetState(int state) {
	targetState = state;
    }

    /**
     * Get the current target state.
     * @return the current target state.
     */
    final public int getTargetState() {
	return targetState;
    }

    /**
     * Returns the start latency.
     * Don't know until the particular node is implemented.
     * @return the start latency.
     */
    public Time getStartLatency() {
	if ( (state == Unrealized) || (state == Realizing) )
	    throw new NotRealizedError("Cannot get start latency from an unrealized controller");
	return LATENCY_UNKNOWN;
    }

    /**
     * Return the duration of the media.
     * It's unknown until we implement a particular node.
     * @return the duration of the media.
     */
    public Time getDuration() {
	return Duration.DURATION_UNKNOWN;
    }

    /**
     * Set the duration of the media in nanoseconds.
     * @param t duration in nanoseconds.
     */
    protected void setMediaLength(long t) {
	clock.setMediaLength(t);
    }

    /**
     * Take the necessary steps to realize the controller.
     * This is a non-blocking call.  It starts a work thread to do the 
     * real work.  The actual code to do the realizing should be written
     * in doRealize().  The thread is also responsible for catching all
     * the RealizeCompleteEvents from the down stream nodes.  When the
     * steps to realize the controller are completed and when all the
     * RealizeCompleteEvents from down stream nodes have been received,
     * the completeRealize() call will be invoked.
     */
    public final synchronized void realize() {
	if (getTargetState() <= Unrealized)
	    setTargetState(Realized);

	switch (state) {
	case Realized:
	case Prefetching:
	case Prefetched:
	case Started:
	    sendEvent(new RealizeCompleteEvent(this, state, state, getTargetState()));
	    break;
	case Realizing:
	    break; // $$ Nothing is done. Two realize() will result in one event
	case Unrealized:
	    // Put it in the realizing state.
	    state = Realizing;
	    sendEvent(new TransitionEvent(this, Unrealized, Realizing, getTargetState()));

	    // Start the realize thread for this controller.
	    try {
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadArgs);
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadGroupArgs);
	    } catch (Exception e) {}
	    realizeThread = new RealizeWorkThread(this);
	    realizeThread.setName(realizeThread.getName() +
				  "[ " + this + " ]" +
				  " ( realizeThread)");
	    
	    realizeThread.start();
	}
    }

    /**
     * Called when the controller is realized and when all the 
     * RealizeCompleteEvents from down stream Controllers have been received.
     * If a subclass wants to override this method, it should still
     * invoke this to ensure the correct events being sent to the
     * upstream Controllers.
     */
    protected synchronized void completeRealize() {
	// Send back the events to whoever is listening, most likely the
	// upstream nodes.
	/* -ivg
	   This is causing problems with Netscape and gc at this point 
	   doesn't seem to be necessary.
	  System.gc();
	  System.runFinalization();
	*/
	state = Realized;
	sendEvent(new RealizeCompleteEvent(this, Realizing, Realized, getTargetState()));

	if (getTargetState() >= Prefetched) {
	    prefetch();
	}
    }

    /**
     * Called when realize() has failed.
     */
    protected void doFailedRealize() {
	state = Unrealized;
	setTargetState(Unrealized);
	processError = "realize failed:" + processError;
	sendEvent(new ResourceUnavailableEvent(this, processError));
	processError = null;
    }

    /**
     * Take the necessary steps to prefetch the controller.
     * This is a non-blocking call.  It starts a work thread to do the 
     * real work.  The actual code to do the realizing should be written
     * in doPrefetch().  The thread is also responsible for catching all
     * the PrefetchCompleteEvents from the down stream nodes.  When the
     * steps to prefetch the controller are completed and when all the
     * PrefetchCompleteEvents from down stream nodes have been received,
     * the completePrefetch() call will be invoked.
     */
    public final /*synchronized*/ void prefetch() {
	if (getTargetState() <= Realized)
	    setTargetState(Prefetched);
	switch (state) {
	case Prefetched:
	case Started:
	    sendEvent(new PrefetchCompleteEvent(this, state, state, getTargetState()));
	    return;
	case Realizing:
	    return; // Handled. prefetch() will be called in completeRealize()
	case Prefetching:
	    return; // $$ Nothing is done. Two prefetch() will result in one event
	case Unrealized:
	    // The controller is not realized yet, we have to implicitly
	    // carry out a realize().
	    realize();
	    return;
	case Realized:
	    break;
	}

	// Put it in the prefetching state.
	//	synchronized(this) {
	state = Prefetching;
	sendEvent(new TransitionEvent(this, Realized, Prefetching, getTargetState()));

	// Start the prefetch thread for this controller.
	    try {
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadArgs);
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadGroupArgs);
	    } catch (Exception e) {}
	prefetchThread = new PrefetchWorkThread(this);
	prefetchThread.setName(prefetchThread.getName() + " ( prefetchThread)");
	prefetchThread.start();
	//	}
    }

    /**
     * Called when the controller is prefetched and when all the 
     * PrefetchCompleteEvents from down stream nodes have been received.
     * If a subclass wants to override this method, it should still
     * invoke this to ensure the correct events being sent to the
     * upstream Controllers.
     */
    protected /*synchronized*/ void completePrefetch() {
	// Send back the events to whoever is listening, most likely the
	// upstream nodes.
	/* -ivg
	   This is causing problems with Netscape and gc at this point 
	System.gc();
	System.runFinalization();
	*/
	// We've passed the Prefetched or Started state, so don't bother...
	if (state == Prefetched || state == Started)
	    return;
	state = Prefetched;
	sendEvent(new PrefetchCompleteEvent(this, Prefetching, Prefetched, getTargetState()));
    }

    /**
     * Called when the prefetch() has failed.
     */
    protected void doFailedPrefetch() {
	state = Realized;
	setTargetState(Realized);
	processError = "prefetch failed:" + processError;
	sendEvent(new ResourceUnavailableEvent(this, processError));
	processError = null;
    }

    /**
     * Release the resouces held by the controller.
     * It obeys strict rules as specified in the spec.  Implement the
     * abortRealize and abortPrefetch methods to actually do the work.
     */
      final public /* synchronized */ void deallocate() {

	int previousState = getState();
	// It's illegal to use deallocate on a started controller.
	if (state == Started) {
	    throw new ClockStartedError("deallocate cannot be used on a started controller.");
        }

	// stop the thread even if isAlive() is false as
        // we want to kill the thread that has been created
        // but not scheduled to run

        if (state == Realizing && realizeThread != null ) {
//	    realizeThread.stop();	// Is this ok?
	    abortRealize();
	    state = Unrealized;
	}

	if (state == Prefetching || state == Prefetched) {
	    // stop the thread even if isAlive() is false as
	    // we want to kill the thread that has been created
	    // but not scheduled to run
	    if (prefetchThread != null)
//		prefetchThread.stop();	// Is this OK?
	    abortPrefetch();
	    state = Realized;
        }

	setTargetState(state);

	// Use by subclass to add in its own behavior.
	doDeallocate();
	sendEvent(new DeallocateEvent(this, previousState, state,
				      state, getMediaTime()));
    }

    /**
     * Called by deallocate().
     * Subclasses should implement this for its specific behavior.
     */
    protected void doDeallocate() {
    } 

    /**
     * Add a listener to the listenerList.  This listener will be notified
     * of this controller's event.
     * This needs to be a synchronized method so as to maintain the integrity
     * of the listenerList.
     * @param listener controller listener to be added to the list of listeners
     */
    final public synchronized void addControllerListener(ControllerListener listener) {
	if (listenerList == null) {
	    listenerList = new Vector();
	}
	if (!listenerList.contains(listener)) {
	    listenerList.addElement(listener);
	}
    }

    /**
     * Remove a listener from the list of listeners.  
     * This needs to be a synchronized method so as to maintain the 
     * integrity of the listenerList.
     * @param listener listener to be removed from the list of listeners
     */
    final public synchronized void removeControllerListener(ControllerListener listener) {
	if (listenerList != null) {
	    listenerList.removeElement(listener);
	}
    }

    /**
     * Send an event to the listeners listening to my events.
     * The event is Queued in the sendEvtQueue which runs in a
     * separate thread.  This way, sendEvent() won't be blocked.
     * @param evt event to be sent
     */
    final protected void sendEvent(ControllerEvent evt) {
	if (sendEvtQueue != null)
	    sendEvtQueue.postEvent(evt);
    }

    /**
     * An internal function to notify the listeners on the listener list
     * the given event.  This gets called by the sendEvtQueue's processEvent()
     * callback.
     * This method updates a lock on the Vector listenerList before
     * enumerating it.
     */

    final protected void dispatchEvent(ControllerEvent evt) {

	if (evt instanceof ControllerErrorEvent) {
	    if (! (evt instanceof ResourceUnavailableEvent) ) {
		cleanup();
		doClose();
	    }
	} else if (evt instanceof ControllerClosedEvent) {
	    cleanup();
	}

	if (listenerList == null)
	    return;

	synchronized(listenerList) {
	    Enumeration list = listenerList.elements();
	    while (list.hasMoreElements()) {
		ControllerListener listener = (ControllerListener)list.nextElement();
		listener.controllerUpdate(evt);
	    }
	}
    }

    /**
     * This get called when some Controller notifies this controller of 
     * any event.  We queue that event in the recvEvtQueue to be handled
     * later.  This way, the calling controller won't get blocked.
     * This doesn't have to be synchronized since the recvEvtQueue already
     * handles the locking.
     */
    final public void controllerUpdate(ControllerEvent evt) {
	if (recvEvtQueue != null)
	    recvEvtQueue.postEvent(evt);
    }

    /**
     * Process one event from another Controller which we are listening to.
     * This is invoked by the recvEvtQueue when there is at least one event
     * in the queue.
     * If you need to extend this methods, please also invoke this such that
     * the events can be catched by the work threads.
     * @param evt controller event to be processed
     */
    protected /*synchronized*/ void processEvent(ControllerEvent evt) {
    }
	
    /**
    * sets the parent controller
    *
    * @param parent parent controller
    */
    final void setParent(Controller parent) {
	this.parent = parent;
    }

    /**
    * returns parent controller
    * @return parent controller
    */
    final Controller getParent() {
	return parent;
    }

   /*AD
    protected void fetchRegionControl() {
	if (parent != null && parent instanceof MediaPlayer)
	    regionControl = ((MediaPlayer)parent).regionControl;
    }
 AD*/
}

/**
 * The event queue to post outgoing events for the MediaController.
 */
class SendEventQueue extends ThreadedEventQueue {

    private MediaController controller;

     /**
    * constructs a sent event queue for the specified controller
    * @param c controller associated with the sent event queue
    */
   public SendEventQueue(MediaController c) {
	controller = c;
    }

    /**
     * Callback from the thread when there is an event to be processed.
     * In this case, we call controller's dispatchEvent() to send the
     * event to the listening controllers.
     */
    public void processEvent(ControllerEvent evt) {
	controller.dispatchEvent(evt);
    }

}


/**
 * The event queue to receive incoming events for the MediaController.
 */
class RecvEventQueue extends ThreadedEventQueue {

    private MediaController controller;

    /**
    * constructs a received event queue for the specified controller
    * @param c controller associated with the received event queue
    */
    public RecvEventQueue(MediaController c) {
	controller = c;
    }

    /**
     * Callback from the thread when there is an event to be processed.
     * In this case, we call controller's processEvent to process the
     * incoming event.
     */
    public void processEvent(ControllerEvent evt) {
	controller.processEvent(evt);
    }

}


/**
 * An execution thread to take care of processing and waiting of
 * completion events for MediaController. 
 * Subclass this to build PrefetchWork thread for example.
 */
abstract class StateTransitionWorkThread extends MediaThread {

    MediaController controller;
    Vector eventQueue = new Vector();
    boolean allEventsArrived = false;

    /**
    * constructs a state transition thread
    */
    StateTransitionWorkThread() {
	useControlPriority();
    }

    /**
     * Implement this to do the real work.
     */
    protected abstract boolean process();

    /**
     * This will be invoked when everything is ready.
     * i.e., the processing is completed and all the events from down
     * stream nodes have been fully captured.
     */
    protected abstract void complete();

    /**
     * Called if the processing failed.
     */
    protected abstract void processFailed();

    /**
     * Return true if the given event is of the right type that the
     * controller is waiting.
     */
    protected abstract boolean isRightEventType(ControllerEvent evt);

    /**
     * Return true if the given controller is already in the correct state.
     * In such case, there is no need to wait for an event notification.
     */
    protected abstract boolean isReady(Controller c);

    /**
     * run() method for the thread.
     * Do the work, wait for every expected events to arrive, signal the
     * the completion.
     */
    public void run() {

	try {
	    if (!process()) {
		// The processing failed.
		processFailed();
		return;
	    }
	} catch (OutOfMemoryError e) {
    	    System.err.println("Out of memory!");
	    processFailed();
	    return;
	}

	complete();
    }
}


/**
 * A Thread to take care of realizing and catching of RealizeCompleteEvents
 * from down stream nodes.
 */
class RealizeWorkThread extends StateTransitionWorkThread {

  /**
  * constructs a thread to realize the controller
  * @param mc a controller to realize
  */
	RealizeWorkThread(MediaController mc) {
		controller = mc;
		setName(getName() + ": " + mc);
     }

     /**
     * calls doRealize of the controller to take the steps to realize
     * the controller
     */
     protected boolean process() {
	return controller.doRealize();
     }

     /**
     * called when the realize is complete
     */
     protected void complete() {
	controller.completeRealize();
     }

     /**
     * called when the realize has failed
     */
     protected void processFailed() {
	controller.doFailedRealize();
     }

     /**
     * checks to see whether the received event is a RealizeCompleteEvent
     */
     protected boolean isRightEventType(ControllerEvent evt) {
	return (evt instanceof RealizeCompleteEvent); 
     }

     /**
     * checks to see if the controller is already in realized state 
     * and no further steps and notification is needed
     */
     protected boolean isReady(Controller c) {
	return c.getState() == Controller.Realized;
     }
}


/**
 * A Thread to take care of prefetching and catching of PrefetchCompleteEvents
 * from down stream nodes.
 */
class PrefetchWorkThread extends StateTransitionWorkThread {

   /**
  * constructs a thread to prefetch the controller
  * @param mc a controller to prefetch
  */
    PrefetchWorkThread(MediaController mc) {
	controller = mc;
	setName(getName() + ": " + mc);
     }

     /**
     * calls doPretetch of the controller to take the steps to prefetch
     * the controller
     */
   protected boolean process() {
	return controller.doPrefetch();
     }

     
  /**
  * called when the prefetch is complete
  */
 protected void complete() {
	controller.completePrefetch();
     }

 /**
 * called when the realize has failed
 */
  protected void processFailed() {
	controller.doFailedPrefetch();
     }

  /**
  * checks to see whether the received event is a PrefetchCompleteEvent
  */
   protected boolean isRightEventType(ControllerEvent evt) {
	return (evt instanceof PrefetchCompleteEvent); 
     }

  /**
  * checks to see if the controller is already in prefetched state 
  * and no further steps and notification is needed
  */
    protected boolean isReady(Controller c) {
	return c.getState() == Controller.Prefetched;
     }
}


/**
 * A Thread to schedule the start of the node.
 */
class TimedStartThread extends MediaThread {

    private MediaController controller;
    private long timeToStart;	// in time-base-time.
    private boolean aborted = false;

    /**
    * constructs TimeStartThread. The thread will start
    * the player after the specified amount of time
    *
    * @param mc controller to be started
    *
    * @param nanoseconds after the nanoseconds time interval the 
    * controller will be started
    */

    TimedStartThread(MediaController mc, long tbt) {
	controller = mc;
	timeToStart = tbt;
	useControlPriority();
    }

    /**
     * abort the wait.
     */
    public synchronized void abort() {
	aborted = true;
	notifyAll();
    }

    /**
     * The run method for the Thread class.
     * Schedule the start time.
     */
    public synchronized void run() {
	long now;

	if (aborted)
	    return;

	// ADDED THE IF STATEMENT
	if (controller.getState() != Controller.Started) {
	    return;
	}

	// What time is it now?
	now = controller.getTimeBase().getNanoseconds();

	if (now >= timeToStart) {
	    // We should have already started.
// 	    if (controller.getState() != Controller.Started) {
// 		System.out.println("BUG-: TimedStartThread: state is not Started:  " +
// 				   controller.getState());
// 	    }
	    controller.doStart();
	    return;
	}

	// Calculate the time to sleep in milli second.
	long timeToSleep = (timeToStart - now)/1000000;

	try {
	    // Either sleep for the entire timeToSleep period or
	    // or being waken up by abort().
	    wait(timeToSleep);
	} catch (InterruptedException e) {
	    System.err.println("TimedStartThread: " + e);
	}

	// Wait up now and invoke doStart().
	if (!aborted) {
// 	    if (controller.getState() != Controller.Started) {
// 		System.out.println("BUG: TimedStartThread: state is not Started:  " +
// 				   controller.getState());
// 	    }

	    controller.doStart();
	}

	// Our job is done.
    }
}



class StopTimeThread extends MediaThread {
    private MediaController controller;
    private long sleepFor;

    /**
    * constructs StopTimeThread. The thread sleeps for the 
    * specified amount of time, then wakes up and stops the
    * controller
    *
    * @param mc controller to be stoped at stop time
    *
    * @param nanoseconds after the nanoseconds time interval the 
    * controller will be stoped
    */
    StopTimeThread(MediaController mc, long nanoseconds) {
	controller = mc;
	sleepFor = nanoseconds / 1000000;
	setName(getName() + ": StopTimeThread");
	useControlPriority();
    }

    /**
    * run method for the thread
    */
    public void run() {
	try {
	    sleep(sleepFor);
	    controller.stopAtTime();
	} catch (InterruptedException e) {
	}
    }
}
