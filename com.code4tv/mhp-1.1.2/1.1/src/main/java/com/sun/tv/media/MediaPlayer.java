/*
 * @(#)MediaPlayer.java	1.140 98/10/29
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

import java.io.*;
import java.net.URL;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.media.*;
import javax.media.protocol.*;
import com.sun.tv.media.util.*;
import com.sun.tv.media.protocol.file.*;
import com.sun.tv.media.controls.*;
//A.D.import com.sun.tv.media.jmf.audio.AudioMixer;

/**
 * This class is a part of the porting layer implementation for JavaTV.
 * MediaPlayer implements the bases of a javax.media.Player.  It handles
 * all the Player state transitions, event handling and management of
 * any Controller under its control.
 */

public abstract class MediaPlayer extends MediaController
implements Player, GainChangeListener {

    static public String VERSION = JMFI18N.getResource("mediaplayer.version");
    static {
	//System.err.println("Java Media Player (" + VERSION + ")");
	try {
	    new JMFProperties();
	} catch (Exception e) {
	}
    }

    //    private MediaSource source = null;
    protected javax.media.protocol.DataSource source = null;
    private Time duration = DURATION_UNKNOWN;
    private Vector nodeList = null;
    private Vector internalNodeList = null;
    private Vector optionalNodeList = new Vector();
    private Vector removedNodeList = new Vector();
    private Vector currentNodeList = new Vector();
    private Vector potentialEventsList = null;
    private Vector receivedEventList = new Vector();
    private boolean receivedAllEvents = false;
    private Vector realizeEventList = new Vector();
    private Vector prefetchEventList = new Vector();
    private Vector stopEventList = new Vector();
    private boolean waitingForRealizeCompleteEvent = false;
    private boolean waitingForPrefetchCompleteEvent = false;
    private boolean deallocated = false;
    private ControllerEvent realizeCompletionEvent = null;
    private ControllerEvent prefetchCompletionEvent = null;
    private ControllerEvent CachingControlEvent = null;
    private Controller restartFrom = null;
    private Vector eomEventsReceivedFrom = new Vector();
    private PlayThread playThread = null;
    private Time startTime, mediaTimeAtStart;


    protected static TimeBase systemTimeBase = new SystemTimeBase();
//A.D.    protected static TimeBase mixerTimeBase = AudioMixer.getMixerTimeBase();

    // Playback controls
 //A.D.   protected PlayBCA        playControl;
//A.D.    protected StopBCA        stopControl;
//A.D.    protected NumericControl seekControl;
//A.D.    protected RateNCA        rateControl;
//A.D.    protected PlaybackControl playbackControl;
    protected Control [] controls = null;
    protected GainControl gainControl = null;

    // Information controls
//A.D.    protected ProgressControl progressControl;
//A.D.    public SliderRegionControl regionControl; 
//A.D.    protected StringControl frameRate = null;
//A.D.    protected StringControl bitRate = null;
//A.D.    protected StringControl videoProps = null;
//A.D.    protected StringControl audioProps = null;
//A.D.    protected StringControl videoCodec = null;
 //A.D.   protected StringControl audioCodec = null;
    
 //A.D.   protected StringControl urlName = null;

    private Integer mediaTimeSync;

    public static ColorModel defaultColorModel = null;
    
/**
* Constructs a MediaPlayer. The contructor sets up the default color
* model, default toolkit, lists of events for different states of the 
* player and a utility object used for synchronization among threads
*/
    public MediaPlayer() {
	if (defaultColorModel == null) {
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    /*
	    defaultColorModel = new DirectColorModel(32,
						     0x00FF0000,
						     0x0000FF00,
						     0x000000FF);
	    */
	    defaultColorModel = tk.getColorModel();
	}
	nodeList = new Vector();
 	realizeEventList.addElement("javax.media.RealizeCompleteEvent");
 	realizeEventList.addElement("javax.media.ResourceUnavailableEvent");

 	prefetchEventList.addElement("javax.media.PrefetchCompleteEvent");
 	prefetchEventList.addElement("javax.media.ResourceUnavailableEvent");

	stopEventList.addElement("javax.media.StopEvent");
	stopEventList.addElement("javax.media.StopByRequestEvent");
	stopEventList.addElement("javax.media.StopAtTimeEvent");
	//controls
//A.D.	playbackControl = new PBC();

   //A.D.     frameRate = new StringControlAdapter();
   //A.D.     frameRate.setValue(JMFI18N.getResource("mediaplayer.N/A"));
  //A.D.      bitRate = new StringControlAdapter();
  //A.D.      bitRate.setValue(JMFI18N.getResource("mediaplayer.N/A"));
  //A.D.      videoProps = new StringControlAdapter();
  //A.D.      videoProps.setValue(JMFI18N.getResource("mediaplayer.N/A"));
  //A.D.      audioProps = new StringControlAdapter();
  //A.D.      audioProps.setValue(JMFI18N.getResource("mediaplayer.N/A"));
  //A.D.      audioCodec = new StringControlAdapter();
  //A.D.      audioCodec.setValue(JMFI18N.getResource("mediaplayer.N/A"));
  //A.D.      videoCodec = new StringControlAdapter();
  //A.D.      videoCodec.setValue(JMFI18N.getResource("mediaplayer.N/A"));

/*A.D.
        progressControl = new ProgressControlAdapter( frameRate, 
						      bitRate, 
						      videoProps,
						      audioProps,
						      videoCodec,
						      audioCodec );
A.D.*/

//A.D.	regionControl = new SliderRegionControlAdapter();
/*A.D.
	Control pbcs[] = new Control[3];
	pbcs[0] = playbackControl;
	pbcs[1] = progressControl;
	pbcs[2] = regionControl;
A.D.*/

//begin A.D.
//A.D.	Control pbcs[] = new Control[1];
//A.D.	pbcs[0] = regionControl;
//end A.D.
	
	//A.D. addControls(pbcs);

	mediaTimeSync = new Integer(1);
    }

    /**
     * Set the DataSource that provides the media for this player.
     * MediaPlayer only supports PullDataSource by default.  Subclasses
     * can override this method to support other DataSources. 
     *
     * @param source of media for this player.
     * @exception IOException thrown when an i/o error occurs
     * in reading information from the data source.
     * @exception IncompatibleSourceException thrown if the Player can't use this source.
     */
    public void setSource(javax.media.protocol.DataSource source)
	throws IOException, IncompatibleSourceException {
	if (!(source instanceof javax.media.protocol.PullDataSource)) {
	    System.out.println("Unsupported data source: " + source);
	    System.out.println(this + " currently supports only pull data sources.");
	    throw new IncompatibleSourceException(this + ": doesn't support " + source);
	} else
	    this.source = source;
/*A.D.	if (urlName == null) {
	    urlName = new StringControlAdapter();
	    MediaLocator ml = source.getLocator();
	    if (ml != null) {
		urlName.setValue("URL=" + ml.toString());
		Control [] controls = new Control[1];
		controls[0] = urlName;
		addControls(controls);
	    }
	}
A.D.*/
    }

    /**
     * Get the DataSource used by this player.
     * @return the DataSource used by this player.
     */
    protected javax.media.protocol.DataSource getSource() {
	return source;
    }

    /**
     * Close the player to stop all threads to make * it "finalizable".  
     * i.e., it's ready to be garbage collected.
     * This is a "final" method and cannot be overridden.  Subclasses should
     * override doClose() to implement specific behavior.
     */
    public final void close() {

	// Ask all its  to close themselves.
	if (nodeList != null) {
	    Controller c;
	    while (!nodeList.isEmpty()) {
		c = (Controller)nodeList.firstElement();
		c.close();
		nodeList.removeElement(c);
	    }
	}

	//EP: removed doClose() since it is called by MediaController
	// in super.close() and should nto be called twice.
	//doClose();
	super.close();
    }

    /**
     * This is called when close() is invoked on the Player.  close() takes
     * care of the general behavior before invoking doClose().  Subclasses 
     * should implement this only if it needs to do something specific to 
     * close the player.
     */
    protected void doClose() {
    }

    /**
     * Assigns a timebase for the player.
     * If the MediaPlayer plays back audio, the timebase can be none
     * other than the master timebase as returned by getMasterTimeBase().
     * This is to ensure that we won't set to a timebase the audio
     * cannot handle at this point.
     * If the playback is video only, the timebase can be set to any
     * timebase desired.
     * @param tb time base to be used by the Player.
     * @exception IncompatibleTimeBaseException thrown when a time base other
     *  than the master time base is set when audio is enabled.
     */
    public void setTimeBase(TimeBase tb) throws IncompatibleTimeBaseException{
	/*
	  -ivg  This is too restrictive for some players.

	if (audioEnabled()){
	    TimeBase mastertb = getMasterTimeBase();
	    if (tb != mastertb)
throw new IncompatibleTimeBaseException("Audio Player's TimeBase must be set to AudioMixer's TimeBase");
	}
	*/

	if (tb == null)
	    tb = getMasterTimeBase();

	super.setTimeBase(tb);

	if (nodeList == null) return;
	int i = nodeList.size();
	while (--i >= 0)
	    ((Controller)nodeList.elementAt(i)).setTimeBase(tb);
    }
    
    /**
     * Set the upper bound of the media time.
     * @param duration the duration in nanoseconds.
     */
    protected void setMediaLength(long t) {
	duration = new Time(t);
	super.setMediaLength(t);
    }


    long lastTime = 0;
    
    /**
     * Get the duration of the media.
     * @return the duration of the media.
     */
    public Time getDuration() {
	if ( (long) getMediaTime().getSeconds() > lastTime) {
	    lastTime = (long) getMediaTime().getSeconds();
	    updateDuration();
	}
	if (duration.getSeconds() <= 0) {        //$$ WORKAROUND
	    return Duration.DURATION_UNKNOWN;   //$$ WORKAROUND
	} else {
	    return duration;
	}
    }

    // NOTE: we shouldn't be using -1 for unknown or uninitialized duration but
    // Duration.DURATION_UNKNOWN
    /**
    * updates player's duration. The duration could be changed as a result of
    * adding or removing managed controllers from the player
    */
    protected synchronized void updateDuration() {
	if (internalNodeList == null) {
	    return; // realize is not done
	}
	if (duration.getSeconds() <= 0) {
	    duration = DURATION_UNKNOWN;
	}
 	Time oldDuration = duration;
 	duration = DURATION_UNKNOWN;
	// Loop thru the Master players internal Controllers only.
	for (int i = 0; i < internalNodeList.size(); i++) {
	    Controller c = (Controller) internalNodeList.elementAt(i);
	    Time dur = c.getDuration();

	    if ( dur.equals(DURATION_UNBOUNDED) ) {
		duration = dur;
		break;
	    }
	    if ( ! ((dur.getSeconds() <= 0) || dur.equals(DURATION_UNKNOWN)) ) {
		// This if is mainly needed for MIDI because for some
		// reason MIDISourceNode give incorrect and very small duration.
		if ( duration.equals(DURATION_UNKNOWN) ||
		    (duration.getNanoseconds() < dur.getNanoseconds()) )
		    duration = dur;
	    }
	}

	/** If the master's duration is unbounded or
	 * unknown, then the Player's duration is unbounded or
	 * unknown, respectively and there is no need to
	 * check the duration of the slave Controllers.
	 */
	if ( ! (duration.equals(DURATION_UNKNOWN) ||
		duration.equals(DURATION_UNBOUNDED) ) ) {
	    // Loop thru slave Controllers only
	    for (int i = 0; i < nodeList.size(); i++) {
		Controller c = (Controller) nodeList.elementAt(i);
		if (internalNodeList.contains(c))
		    continue;
		Time dur = c.getDuration();
		if (dur.getSeconds() <= 0) {
		    dur = DURATION_UNKNOWN;
		}
		if ( dur.equals(DURATION_UNKNOWN) ||
		     dur.equals(DURATION_UNBOUNDED) ) {
		    /** If a slave Controller's duration is unbounded or
		     * unknown, then the Player's duration is unbounded or
		     * unknown, respectively
		     */
		    duration = dur;
		    break;
		}
		if (duration.getNanoseconds() < dur.getNanoseconds())
		    duration = dur;
	    }
	}

	if (duration.getNanoseconds() != oldDuration.getNanoseconds()) {
	    setMediaLength(duration.getNanoseconds());
	    sendEvent( new DurationUpdateEvent(this, duration) );
	}
    }
    

/**
* sets up the specified stop time for all controllers in the 
* internal node list
*
* @param t stop time
*/
    public void setStopTime(Time t) {
	if (state == Unrealized || state == Realizing)
	    throw new NotRealizedError("Cannot set stop time on an unrealized controller.");
	Vector list = getNodeList();
        int i = list.size();
        while (--i >= 0) {
	    Controller c = (Controller)getNodeList().elementAt(i);
	    if ( ! (internalNodeList.contains(c)) ) {
		c.setStopTime(t);
	    }
	}
	super.setStopTime(t);
    }

    /**
     * Loops through the list of nodes and controllers maintained by this
     * player and invoke setMediaTime on each of them.
     * This is a "final" method and cannot be overridden by subclasses.
     * @param now the target media time.
     **/
    public final void setMediaTime(Time now) {

	// Set Media time from EOM and user click on the slider is
	// trampling on one another.  Causing the player to hang
	// this mediaTimeSync will guard against that.
    synchronized (mediaTimeSync) {

	boolean restart = false;
	if (getState() == Controller.Started) {
	    restart = true;
	    stop("restarting");
	}
	super.setMediaTime(now);
        int i = getNodeList().size();
        while (--i >= 0) {
            ((Controller)getNodeList().elementAt(i)).setMediaTime(now);	
	}

	// For subclasses to add in their own behavior.
	doSetMediaTime(now);

	// Flush the data pipe.
	flush();

	if (restart) {
	    syncStart(getTimeBase().getTime());
	}
    }

    }

    /**
     * Called from setMediaTime.
     * This is used for subclasses to add in their own behavior.
     * @param now the target media time.
     */
    protected void doSetMediaTime(Time now) {
    	/*A.D.
	if (seekControl instanceof SeekNCA)
	    ((SeekNCA)seekControl).progSetValue(now.getNanoseconds());
	    A.D.*/
    }

    /**
     * Set the Component this player will output its visual media to.  If
     * this player has no visual component (e.g. audio only)
     * setVisualComponent() will be ignored.
     * This is only a hint to the video renderer.  A particular player
     * implementation may ignore this method.
     * Subclasses should override this method and do the right thing.
     * But it should also call this method first to ensure that the 
     * restrictions on player methods are enforced.
     * @param component the component the player will use for displaying.
     * @param bounds the bounding rect the player uses for displaying.
     * 
     */
    public void setVisualComponent(Component component, Rectangle bounds) {
	int state = getState();
	if ( (state != Unrealized) && (state != Realizing) )
	    throw new NotRealizedError("Cannot set visual component on an realized player");
    }

    /**
     * Get the Component this player will output its visual media to.  If
     * this player has no visual component (e.g. audio only)
     * getVisualComponent() will return null.
     * Subclasses should override this method and return the visual
     * component but call this method first to ensure that the restrictions
     * on player methods are enforced.
     * @return the media display component.
    */
    public Component getVisualComponent() {
	int state = getState();
	if ( (state == Unrealized) || (state == Realizing) )
	    throw new NotRealizedError("Cannot get visual component on an unrealized player");
	return null;
    }

    /**
     * Get the Component with the default user interface for controlling
     * this player.
     * If this player has no default control panel null is
     * returned.
     * Subclasses should override this method and return the control panel
     * component but call this method first to ensure that the restrictions
     * on player methods are enforced.
     * @return the default control panel GUI.
     */
    public Component getControlPanelComponent() {
	int state = getState();
	if ( (state == Unrealized) || (state == Realizing) )
	    throw new NotRealizedError("Cannot get control panel component on an unrealized player");
	return null;
    }

    /**
     * Get the object for controlling audio gain. Return null
     * if this player does not have a GainControl (e.g. no audio).
     *
     * @return the GainControl object for this player.
    */
     public GainControl getGainControl() {
	int state = getState();
	if ( (state == Unrealized) || (state == Realizing) ) {
	    throw new NotRealizedError("Cannot get gain control on an unrealized player");
	} else {
	    return gainControl;
	}
    }

    /**
     * Subclasses can overide the implementation of seek control.
     * @return the NumericControlAdapter that implements the seek control.
     */
     /*A.D.
    protected NumericControlAdapter getSeekNCA() {
	return new SeekNCA();
    }
A.D.*/

    /**
     * Subclass should override this method to change the audio gain.
     * @param gce the GainChangeEvent specifying the new gain level to set to.
     */
    public void gainChange(GainChangeEvent gce) {
	System.out.println("MediaPlayer.gainChange: " + gce.getLevel());
    }

    /**
     * Subclass should override this method to implement the mute behavior.
     * @param m the boolean value: true muted, false unmuted.
     */
    public void muteChange(boolean m) {
	System.out.println("MediaPlayer.muteChange(): " + m);
    }

    /**
     * Add more controls to the player 
     * @param newControls list of controls to add to the player.
     */
    public void addControls(Control [] newControls) {
	if (controls == null) {
	    controls = newControls;
	} else {
	    int i;
	    Control concat[] = new Control[controls.length + newControls.length];
	    for (i = 0; i < controls.length; i++)
		concat[i] = controls[i];
	    for (i = 0; i < newControls.length; i++)
		concat[i+controls.length] = newControls[i];
	    controls = concat;
	}
    }

    /**
     * Return the controls that have been added (atleast includes the
     * playback control)
     * @return the list of controls supported by this player.
     */
    public Control [] getControls() {
/*A.D.
    if (controls == null) {
	    controls = new Control[1];
	    controls[0] = playbackControl;
	}
	A.D.*/
	return controls;
    }
    
    /**
     * Return the list of MediaControllers supported by this Player.
     * @return a vector of the MediaControllers supported by this Player.
     */ 
    public final Vector getNodeList() {
	return nodeList;
    }

    /**
     * Return the list of potential events. The list of potential events
     * depends on a state of the player. For realized state the list is comprised 
     * of the list of realize events (RealizeCompleteEvent, ResourceUnavailableEvent)
     * for Prefetched state the list constists of PrefetectCompleteEvent and 
     * ResourceUnavailableEvent
     *
     * @return a vector of potential events
     */ 
    private Vector getPotentialEventsList() {
	return potentialEventsList;
    }

    /**
     * Resets the list of received events
     */
    private void resetReceivedEventList() {
	if (receivedEventList != null)
	    receivedEventList.removeAllElements();
    }

    /**
     * Return the list of received events
     */
    private Vector getReceivedEventsList() {
	return receivedEventList;
    }

    /**
     * Updates the list of received events. Sources are stored.
     * @param event received event
     */
    private void updateReceivedEventsList(ControllerEvent event) {
	if (receivedEventList != null) {
	    Controller source = event.getSourceController();

	    if (receivedEventList.contains(source)) {
		// System.out.println("DUPLICATE " + event +  " received from: " +
		//                     source);
		return;
	    }
	    receivedEventList.addElement(source);
	}
    }

    /**
     * Start the Player as soon as possible.
     * Start attempts to transition the player into the
     * started state.
     * If the player has not been realized, or prefetched,
     * then the equivalent of those actions will occur,
     * and the appropriate events will be generated.
     * If the implied realize or prefetch fail, a failure
     * event will be generated and the Player will remain in
     * one of the non-started states.<p>
     * This is a "final" method.  Subclasses should override doStart() to
     * implement its own specific behavior.
     */
    public final void start() {
	if (restartFrom != null) {
	    return;
	}

	if (getState() == Started) {
	    sendEvent(new StartEvent(this, Started, Started,
				     Started, mediaTimeAtStart, startTime));

	    return; // ignored according to jmf spec.
	}
	if ( (playThread == null) || (! playThread.isAlive()) ) {
	    setTargetState(Started);
	    try {
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadArgs);
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadGroupArgs);
	    } catch (Exception e) {}
	    playThread = new PlayThread(this);
	    playThread.start();
	} else {
	    // $$$$
	    // 	    System.out.print("WARNING: playThread is alive. start ignored"); // $$$
	    // 	    System.out.println(": MP State: " + getState());
	}
    }

    /**
     * Start at the given time base time.
     * This overrides Clock.syncStart() and obeys all its semantics.<p>
     * This is a "final" method.  Subclasses should override doStart() to
     * implement its own specific behavior.
     * @param tbt the time base time to start the player.
     */
    public final void syncStart(Time tbt) {
	int state = getState();

	if (state == Started)
	    throw new ClockStartedError("syncStart() cannot be used on an already started player");

	if (state != Prefetched) {
	    throw new NotPrefetchedError("Cannot start player before it has been prefetched");
	}

	setTargetState(Started);

	int size = getNodeList().size();
	for (int i = 0; i < size; i++) {
	    if (getTargetState() == Started) {  // ADDED
		((Controller)getNodeList().elementAt(i)).syncStart(tbt);
	    }
	}

	if (getTargetState() == Started) {  // ADDED
	    // If control comes here, the nodes
	    // are in Started state.
	    startTime = tbt;
	    mediaTimeAtStart = getMediaTime();
	    super.syncStart(tbt); // To start the clock and set the state to Started
	}
    }

    /**
     * Invoked by start() or syncstart().
     * Called from a separate thread called TimedStart thread.
     * subclasses can override this method to implement its specific behavior.
     */
     protected void doStart() {
/*A.D.
	if (playControl != null)
	    playControl.progSetValue(true);
    A.D.*/
     }
    
     /**
     * Realize the player and block until it's been realized or if it fails.
     * @return true if realize was completed and successful.
     */
    public final synchronized boolean synchronousRealize() {
	if (getState() != Unrealized)
	    return false;
	// Make sure you are notified when realize completes.
	this.addControllerListener(this);
	realize();
 	if (getState() != Realizing) {
	    this.removeControllerListener(this);
 	    return false;
	}
	realizeCompletionEvent = null;
	waitingForRealizeCompleteEvent = true;

	try {
	    while (realizeCompletionEvent == null)
		wait();
	} catch (InterruptedException e) {
	} finally {
	    waitingForRealizeCompleteEvent = false;
	    this.removeControllerListener(this);
	}
	return (realizeCompletionEvent instanceof RealizeCompleteEvent);
    }

    
    /**
     * Prefetch the player and block until it's been prefetched or if it fails.
     * @return true if the prefetch is successful.
     */
    public final synchronized boolean synchronousPrefetch() {
	boolean realized = (getState() == Realized); // already realized?

	if (getState() == Unrealized) {
	    // The controller is not realized yet, we have to implicitly
	    // carry out a realize().
	    realized = synchronousRealize();
	}

	if (getState() != Realized)
	    return false; // Prefetch can only be done on a realized controller

	// Make sure you are notified when prefetch completes.
	this.addControllerListener(this);
	prefetch();
 	if (getState() != Prefetching) {
	    this.removeControllerListener(this);
 	    return false;
	}
	prefetchCompletionEvent = null;
	waitingForPrefetchCompleteEvent = true;

	try {
	    while (prefetchCompletionEvent == null)
		wait();
	} catch (InterruptedException e) {
	} finally {
	    waitingForPrefetchCompleteEvent = false;
	    this.removeControllerListener(this);
	}
	return (prefetchCompletionEvent instanceof PrefetchCompleteEvent);
    }

    /**
     * This method gets run in a separate thread called PlayThread
     */
    final synchronized void play() {
	boolean status;

	// If a deallocate() happens before this thread gets to run.
	// or if a stop() happens before this thread gets to run.
	if (getTargetState() != Started) {
	    return;
	}

	int state = getState();
	if ( (state == Unrealized) || (state == Realized) ) {
	    prefetch();
	}

	while ( (getState() == Realizing) ||  (getState() == Prefetching) ) {
	    try {
		wait();
	    } catch (InterruptedException e) {
	    }
	}

	synchronized (mediaTimeSync) {
	if ( (getTargetState() == Started) && (getState() == Prefetched) ) {
	    syncStart(getTimeBase().getTime());
	}
	}
    }

    /**
     * The stop() method calls doStop() so that subclasses can
     * add additional behavior.
     */
    protected void doStop() {
     /*A.D.
	if (stopControl != null) {
	    stopControl.progSetValue(true);
	}
A.D.*/
    }
    
    /**
     * requests to stop the player by delegating to stop(String request)
     * method.
     * sends a StopByRequest event to all registered listener(s).
     */
    public final /*synchronized*/ void stop() {
	stop("StopByRequest");
    }

    
    /**
     * stops the player because of the specified stop time has passed.
     * sends a StopByTime event to all registered listener(s). resets
     * the stop time for the player
     */
    protected void stopAtTime() {
	stop("StopAtTime");
	setStopTime(Clock.RESET); //	super.setStopTime(Clock.RESET);

    }

    
    /**
    * stops the player. If the current stse of the player is unrealized,
    * realized or prefetched, the player will be moved to the same state.
    * if the player is in realizing state, it will be moved to the realized 
    * state. If the player is in prefetching or started state, it will be moved 
    * to the prefetched state. All managed controllers will be stoped as well.
    *
    * @param request specifies the type of stop request. Possible types of 
    * the request are StopAtTime, StopByRequest, and restarting. Corresponding
    * events notifying that player has been stoped are sent to all listeners.
    */
    protected /*synchronized*/ void stop(String request) {

	int state;

	switch (state = getState()) {
	case Unrealized:
	case Realized:
	case Prefetched:
	    setTargetState(state);
	    break;
	case Realizing:
	    setTargetState(Realized);
	    break;
	case Prefetching:
	case Started:
	    setTargetState(Prefetched);
	    break;
	}

	if ( (getState() != Started) && (request.equals("StopByRequest")) ) {
	    sendEvent( new StopByRequestEvent(this, getState(),
					      getState(),
					      getTargetState(),
					      getMediaTime()));
	} else if (getState() == Started) {
	  synchronized(this) {
	    // List of potential events for stop()
	    potentialEventsList = stopEventList; 
	    // Reset list of received events
	    resetReceivedEventList();
	    receivedAllEvents = false;
	    currentNodeList.removeAllElements();

	    if (nodeList == null)
		return;

	    int i = nodeList.size();
	    while (--i >= 0) {
		Controller c = (Controller) nodeList.elementAt(i);
		currentNodeList.addElement(c);
		c.stop();
	    }
	    if (currentNodeList == null)
		return;
	    if (!currentNodeList.isEmpty()) {
		try {
		    while (!receivedAllEvents)
			wait();
		} catch (InterruptedException e) {
		}
		currentNodeList.removeAllElements();
	    }
	    eomEventsReceivedFrom.removeAllElements();
	    super.stop();
	    //doStop(); // Allow subclasses to extend the behavior

	    if (request.equals("StopByRequest")) {
		sendEvent( new StopByRequestEvent(this, Started,
						  Prefetched,
						  getTargetState(),
						  getMediaTime()));
            } else if (request.equals("StopAtTime")) {
		sendEvent( new StopAtTimeEvent(this, Started,
					       Prefetched,
					       getTargetState(),
					       getMediaTime()));
	    } else if (request.equals("restarting")) {
		setTargetState(Started);
		sendEvent(new RestartingEvent(this, Started, 
						Prefetching, 
						getTargetState(),
					  	getMediaTime()));
	    }
	  }
	}
    }

/*A.D.
    protected final void processEndOfMedia() {
	super.stop();
 	sendEvent(new EndOfMediaEvent(this, Started, Prefetched,
 				      getTargetState(), getMediaTime()));
//A.D.	stopControl.progSetValue(true);
    }
A.D.*/
    /**
     * Add a MediaNode to the list of Controllers under this Player's
     * management.  This is a protected method use only by subclasses.
     * Use addController() for public access.
     */
    protected final void addNode(Controller node) {
	if (node != null) {
	    if (!nodeList.contains(node)) {
		nodeList.addElement(node);
		if (node instanceof MediaController)
		    ((MediaController)node).setParent(this);
	    }
	}
	updateDuration();
    }

    /**
     * Add a MediaNode to the list of Controllers under this Player's
     * management.
     */
    protected final void addNode(Controller node, boolean optional) {
	if (node != null) {
	    if (!nodeList.contains(node)) {
		nodeList.addElement(node);
		if (node instanceof MediaController)
		    ((MediaController)node).setParent(this);
		if (optional)
		  optionalNodeList.addElement(node);
	    }
	}
	updateDuration();
    }

    /**
     * Remove a MediaNode from the list of Controllers under this Player's
     * management.  This is a protected method use only by subclasses.
     * Use removeController() for public access.
     * @param node a controller removed from the list of nodes
     */
    protected final void removeNode(Controller node) {
	if (node != null)
	    if (nodeList.contains(node)) {
		nodeList.removeElement(node);
	    }
    }

    /**
     * Assume control of another Controller.
     * A Player can accept responsibility for controlling
     * another Controller.
     * Once a Controller has been added
     * this Player will:
     * <ul>
     * <li> Slave the Controller to the Player's time-base.
     * <li> Use the Controller in the Player's computation 
     * of start latency.
     * The value the Player returns in its <b>getStartLatency</b> 
     * method is the larger
     * of:  <b>getStartLatency</b> before the Controller was added, or
     * <b>getStartLatency</b> of the Controller.
     * <li> Pass along, as is appropriate, events that the Controller
     * generates.
     * <li> Invoke all Controller methods on the Controller.
     * <li> For all asynchronous methods (realize, prefetch) a completion
     * event will not be generated until all added Controllers have 
     * generated completion events.
     * </ul><p>
     *
     * <b>Note:</b> It is undefined what will happen if a Controller is
     * under the control of a Player and any of the 
     * Controller's methods are called outside of the controlling 
     * Player.
     *
     * @param newController the Controller this
     * Player will control.
     * @exception IncompatibleTimeBaseException thrown if the new controller 
     * will not accept the player's timebase.
     */
    public final synchronized void addController(Controller newController)
	throws IncompatibleTimeBaseException
    {
	int playerState = getState();

	if (playerState == Started)
	    throw new ClockStartedError("Cannot add controller to a started player");

	if ( (playerState == Unrealized) || (playerState == Realizing) )
	    throw new NotRealizedError("A Controller cannot be added to an Unrealized Player");

	if (newController == null)
	    return;

	int controllerState = newController.getState();
	if ( (controllerState == Unrealized) || (controllerState == Realizing) )
	    throw new NotRealizedError("An Unrealized Controller cannot be added to a Player");
 	if (nodeList.contains(newController)) {
	    return;
	}

	if (playerState == Prefetched) {
	    if ( (controllerState == Realized) ||
		 (controllerState == Prefetching) ) {
		// System.out.println("Calling deallocate");
		deallocate(); // Transition back to realized state
	    }
	}

	newController.setTimeBase(getTimeBase());
	newController.setMediaTime(getMediaTime());
	newController.setStopTime(getStopTime());
	float rate = getRate();
	// TODO: use epsilon when comparing 2 floats
	if (newController.setRate(rate) != rate) {
	    newController.setRate(1.0F);
	    setRate(1.0F);
	}
	nodeList.addElement(newController);
	if (newController instanceof MediaController)
	    ((MediaController)newController).setParent(this);
	newController.addControllerListener(this);
	updateDuration();
    }

    /**
     * Removes the controller from the list of controlled controllers and 
     * sets the controller to its default time base.
     *
     * @param oldController the Controller to stop controlling.
     */
    public final synchronized void removeController(Controller oldController) {
	int state = getState();

	if (state == Started)
	    throw new ClockStartedError("Cannot remove controller from a started player");

	if ( (state == Unrealized) || (state == Realizing) )
	    throw new NotRealizedError("Cannot remove controller from a unrealized player");

	if (oldController == null)
	    return;

 	if (nodeList.contains(oldController)) {
 	    nodeList.removeElement(oldController);
	    oldController.removeControllerListener(this);
	    updateDuration();
	    // Reset the controller to its default time base.
	    try {
		oldController.setTimeBase(null);
	    } catch (IncompatibleTimeBaseException e) {}
	}
    }

    /**
     * This is invoked from doRealize() during the player realization stage.
     * See doRealize() for a detail description.
     * Subclasses should implement this to construct the internals of the 
     * player.
     * The method in the subclass should create all the nodes (Controllers) 
     * that it needs to assemble a Player, use addNode(node) to add them to
     * the list maintained by the MediaPlayer.  The nodes should not be
     * connected at this point since they haven't been realized.  The 
     * MediaPlayer's doRealize() call will be responsible for invoking 
     * realize() on each node, wait for their RealizeCompleteEvent.  At
     * that point, it will invoke connectNodes().  Only at that point should
     * subclasses connect up the nodes.<p>
     * In addition, this method should also choose a master timebase to be
     * used by this player.  This should be returned on a getMasterTimeBase() 
     * call.<p>
     * It should return success/failure.  A subsequent call to createNodes()
     * after the first successful completion should just return true.
     * @return true if successful; false if failed.
     */ 
    protected abstract boolean createNodes();

    /**
     * This is invoked from doRealize() after all the nodes created in
     * createNode() is fully realized.  At this point, subclasses should
     * implement this method to connect up the nodes.
     * It should return a boolean signifying success/failure.
     * @return true if successful; false if failed.
     */ 
    protected abstract boolean connectNodes();

    /**
     * Return true if the player is currently playing media 
     * with an audio track.
     * @return true if the player is playing audio.
     */
    protected abstract boolean audioEnabled();

    /**
     * Return true if the player is currently playing media 
     * with a video track.
     * @return true if the player is playing video.
     */
   protected abstract boolean videoEnabled();

    /**
     * This should be implemented by the subclass.
     * The subclass method should return the master TimeBase -- the
     * TimeBase that all other Controllers (nodes) slave to.
     * Use SystemTimeBase if unsure.
     * @return the master time base.
     */
    protected abstract TimeBase getMasterTimeBase();

    /**
     * The stub function (invoked from realize()) to perform the steps to 
     * realize the player.  It performs the following:
     * <ul>
     * <li> invoke createNodes() to allow subclasses to create the internal
     * nodes of a player;
     * <li> call realize() on each node created from createNode(); 
     * <li> wait for RealizeCompleteEvent from each node;
     * <li> call connectNodes() to allow subclasses to connect up the nodes
     * after they are realized.
     * </ul>
     * Subclasses are allowed to override doRealize().  But this should be
     * done in caution.  createNodes() and connectNodes() should have 
     * provided all the flexiblity needed without changing doRealize().
     * This is called from a separately running thread.
     * @return true if successful.
     */ 
    protected synchronized boolean doRealize() {
	if (createNodes()) {
	    internalNodeList = (Vector) getNodeList().clone();

	    int i = getNodeList().size();
	    while (--i >= 0) {
		((Controller)getNodeList().elementAt(i)).addControllerListener(this);
	    }
	} else {
	    return false;  // realize() failed.
	}

	potentialEventsList = realizeEventList; // List of potential events for the
	                                        // realize() method
	resetReceivedEventList(); 		// Reset list of received events
	receivedAllEvents = false;
	currentNodeList.removeAllElements();

	int i = getNodeList().size();
	while (--i >= 0) {
	    Controller c = (Controller) getNodeList().elementAt(i);
	    if (c.getState() == Unrealized) {
		currentNodeList.addElement(c);
	    }
	}

	i = currentNodeList.size();
	while (--i >= 0) {
	    Controller c = (Controller) currentNodeList.elementAt(i);
	    c.realize();
	}

	if (!currentNodeList.isEmpty()) {
	    try {
		while (!receivedAllEvents)
		    wait();
	    } catch (InterruptedException e) {
	    }
	    currentNodeList.removeAllElements();
	}

	// Make sure all the nodes are in in Realized State.
	// If not, it means that realize failed on one or more nodes.
	// Currenly, if realize fails then you get a ResourceUnavailableEvent
	// instead of RealizeCompleteEvent

	i = getNodeList().size();
	while (--i >= 0) {
	    Controller c = (Controller) getNodeList().elementAt(i);
	    if (c.getState() != Realized) {
		System.err.println("Error: Unable to realize " + c);
		return false;
	    }
	}

	createGainControl();
	updateDuration();
	// subclass will implement this to connect up the signal graph.
	return connectNodes();
    }

    /**
    * If the Player can play audio, a gain control adapter is created
    * and the player is registered a listener for gain change events
    */
    protected void createGainControl() {
	// Check for audio capabilities.
	if (audioEnabled()) {
	    gainControl = new GainCA(false);
	    gainControl.addGainChangeListener( this );
	}
    }
	// set rate control to null if player plays only AUDIO since
	// we dont support audio rate control  
	/*A.D.
	if ((rateControl != null) && (audioEnabled()) && (!videoEnabled()))
	    rateControl = null;
    }
     A.D.*/
   
    /**
     * Called as a last step to complete the realize call.
     */
    protected synchronized void completeRealize() {
	super.completeRealize();
	try {
	    slaveToMasterTimeBase(getMasterTimeBase());
	} catch (IncompatibleTimeBaseException e) {
	    System.out.println(e);
	}
	notify();
    }

    /**
     * Called when realize fails.
     */
    protected synchronized void doFailedRealize() {
	super.doFailedRealize();
	notify();
    }

    /**
     * Called as a last step to complete the prefetch call. 
     */
    protected void completePrefetch() {
	try {
	    slaveToMasterTimeBase(getMasterTimeBase());
	} catch (IncompatibleTimeBaseException e) {
	    System.out.println(e);
	}
	super.completePrefetch();
	synchronized(this) {
	    notify();
	}
    }

    /**
     * Called when prefetch fails.
     */
    protected synchronized void doFailedPrefetch() {
	super.doFailedPrefetch();
	notify();
    }

    /**
     * Called when the realize() is aborted, i.e. deallocate() was called
     * while realizing.  Release all resources claimed previously by the
     * realize() call.
     */
    protected final void abortRealize() {
	if (nodeList != null) {
	    int i = nodeList.size();
	    while (--i >= 0) {
		Controller c = (Controller) nodeList.elementAt(i);
		c.deallocate();
	    }
	}
	synchronized(this) {
	    notify();
	}
    }

    /**
     * Called during prefetch to allocate any necessary buffers.
     * Subclasses can override this to implement its specific requirements.
     */
    protected  void allocBuffers() {
    }
	
    /**
     * The stub function to perform the steps to prefetch the controller.
     * This will call prefetch() on every node in the node list and wait
     * for their completion events.
     * This is called from a separately running thread.
     * @return true if successful.
     */ 
    protected  /*synchronized*/ boolean doPrefetch() {

	if (deallocated) {
	    setMediaTime(new Time(0));
	    deallocated = false;
	}

	allocBuffers();

	potentialEventsList = prefetchEventList; // List of potential events for the
	                                        // prefetch() method
	resetReceivedEventList(); 		// Reset list of received events
	receivedAllEvents = false;
	currentNodeList.removeAllElements();

	Vector list = getNodeList();

	if (list == null) {
	    return false;
	}

	int i = list.size();
	while (--i >= 0) {
	    Controller c = (Controller) list.elementAt(i);
	    if (c.getState() == Realized) {
		currentNodeList.addElement(c);
		c.prefetch();
	    }
	}
	if (!currentNodeList.isEmpty()) {
	    synchronized(this) {
	    try {
		while (!receivedAllEvents)
		    wait();
	    } catch (InterruptedException e) {
	    }
	    currentNodeList.removeAllElements();
	    }
	}

	// Make sure all the nodes are in in Prefetched State.
	// If not, it means that prefetch failed on one or more nodes.
	// Currenly, if prefetch fails then you get a ResourceUnavailableEvent
	// instead of PrefetchCompleteEvent

	i = list.size();
	while (--i >= 0) {
	    Controller c = (Controller) list.elementAt(i);
	    if (c.getState() != Prefetched) {
		System.err.println("Error: Unable to prefetch " + c);
		if (optionalNodeList.contains(c)) {
		  //System.out.println(c + " Controller is optional... continuing");
		  removedNodeList.addElement(c);
		} else {
		    return false;
                }
	    }
	}
	if (removedNodeList != null) {
	  i = removedNodeList.size();
	  while (--i >= 0) {
	    Object  o =  removedNodeList.elementAt(i);
	    nodeList.removeElement(o);
	    ( (MediaController) o).close();
	    if (! deviceBusy((MediaController) o)) {
		return false; // prefetch failed
	    }
	  }
	  removedNodeList.removeAllElements();
	  //$ System.err.println("final list of nodes: " + list);
	}
	return true;
    }

    /**
     * Called when the prefetch() is aborted, i.e. deallocate() was called
     * while prefetching.  Release all resources claimed previously by the
     * prefetch call.
     */
    protected final void abortPrefetch() {
	flush();
	if (nodeList != null) {
	    int i = nodeList.size();
	    while (--i >= 0) {
		Controller c = (Controller) nodeList.elementAt(i);
		c.deallocate();
	    }
	}
	synchronized(this) {
	    notify();
	}
	deallocated = true;
    }

    /**
     * This should trigger a flush to the data path.
     */
    protected void flush() {
	if (getState() == Started)
	    throw new ClockStartedError("flush() cannot be used on an already started player");
    }

    /**
     * Check the given controller to see if it's busy or not. 
     * Needs to be overridden by subclass.
     * The subclass method should change the master timebase
     * if necessary. It should handle audio only or video
     * only tracks properly when the device is busy.
     * @return true if the given controller is usable; false if the controller
     *  cannot be used.
     */
    protected boolean deviceBusy(MediaController mc) {
	return true;
    }

    /**
     * Slave all the controllers to the master time base.
     * The controllers should be in realized or greater state
     * This differs from the setTimeBase() as it loops through each
     * controllers and call setTimeBase on each of them.
     * @param tb the time base to be used by all controllers.
     * @exception IncompatibleTimeBaseException thrown if any controller 
     * will not accept the player's timebase.
     */
    protected void slaveToMasterTimeBase(TimeBase tb)
	 	throws javax.media.IncompatibleTimeBaseException {
	//$$ System.out.println("slaveToMasterTimeBase: master timebase is " + tb);
	//$ System.out.println("Setting master " + tb + " on " + this);
	this.setTimeBase(tb); // For the player
    }

 
    /**
    * notifies that the events arrived from all controllers in the
    * specified node list
    *
    * @param nodeList contains the list of controllers managed by the player
    *
    * @param receivedEventList the list of recieved events
    */
    private /*synchronized*/ void notifyIfAllEventsArrived(Vector nodeList,
						       Vector receivedEventList) {
	if ( (receivedEventList != null) &&
	     (receivedEventList.size() == currentNodeList.size()) ) {
	    receivedAllEvents = true;
	    resetReceivedEventList(); 		// Reset list of received events
	    synchronized(this) {
		notifyAll();
	    }
	}
    }


    protected /*synchronized*/ void processEvent(ControllerEvent evt) {
	
	Controller source = evt.getSourceController();

        if (source == this) { // Event that resulted from a synchronousRealize or
	                      // synchronousPrefetch call

	    if ( waitingForRealizeCompleteEvent &&
		 realizeEventList.contains(evt.getClass().getName())) {
		realizeCompletionEvent = evt;
		synchronized(this) {
		    notifyAll();
		}
	    } else if (waitingForPrefetchCompleteEvent &&
		prefetchEventList.contains(evt.getClass().getName())) {
		prefetchCompletionEvent = evt;
		synchronized(this) {
		    notifyAll();
		}
	    }
	   return;
        }
/*A.D.
	if (evt instanceof AudioDeviceUnavailableEvent) {
	    sendEvent(new AudioDeviceUnavailableEvent(this));
	    return;
	}
A.D.*/
	if ( (evt instanceof ControllerErrorEvent) && nodeList.contains(source) &&
	     (! (evt instanceof ResourceUnavailableEvent) ) ) {
	    sendEvent(new ControllerErrorEvent(this,
                          ((ControllerErrorEvent) evt).getMessage()));
	    // -ivg
	    // This could be a risky thing to do.  But the spec. dictates
	    // this...
	    close();
	}

	//
	// Send SizeChangeEvent down to Player
	//
	/*A.D.
	if ( (evt instanceof SizeChangeEvent) && nodeList.contains(source) ) {
            // System.err.println("width = " +  ((SizeChangeEvent)evt).getWidth());
            // System.err.println("height = " +  ((SizeChangeEvent)evt).getHeight());
            sendEvent(new SizeChangeEvent(this,
                                    ((SizeChangeEvent)evt).getWidth(),
                                    ((SizeChangeEvent)evt).getHeight(),
                                    ((SizeChangeEvent)evt).getScale()));
	    return;
        }

A.D.*/
 	// 
	// Send UnsupportedFormatEvent down to Player
	// 
  /*A.D.      
	if ( (evt instanceof UnsupportedFormatEvent) && 
		nodeList.contains(source) ) {
            // System.err.println("Reason = " +  ((UnsupportedFormatEvent)evt).toString());
            sendEvent(new UnsupportedFormatEvent(this, 
			((UnsupportedFormatEvent)evt).getFormat()));
            return;
        }
	A.D.*/

	// If we get a DurationUpdateEvent from one of the controllers,
	// update the duration of the player
	if ((evt instanceof DurationUpdateEvent) && nodeList.contains(source)) {
	    updateDuration();
	    return;
	}

	// HANGS.
// 	if ((evt instanceof RestartingEvent) && nodeList.contains(source)) {
// 	    System.out.println("MP: Got RestartingEvent from " + source);
// 	    stop(""); // Stop without sending any stop event
// 	    sendEvent(new RestartingEvent(this, Started, Prefetching, Started,
// 					  getMediaTime()));
// 	}

	// So I am handling RestartingEvent this way
	if ((evt instanceof RestartingEvent) && nodeList.contains(source)) {
	    restartFrom = source;
	    int i = getNodeList().size();
	    super.stop(); // Added
	    setTargetState(Prefetched); // necessary even if super.stop is called.
//A.D.	    playControl.setEnabled(false);

	    for (int ii = 0; ii < i; ii++) {
		Controller c = (Controller) getNodeList().elementAt(ii);
		if (c != source) {
		    c.stop();
		}
	    }
	    super.stop();
	    //	    doStop(); // Allow subclasses to extend the behavior
	    sendEvent(new RestartingEvent(this, Started, Prefetching, Started,
					  getMediaTime()));
	}


	if ((evt instanceof StartEvent) && (source == restartFrom) ) {
	    restartFrom = null;
	    // $$ TODO: Should probably send PrefetchCompleteEvent
	    start();
//A.D.	    playControl.setEnabled(true);	    
	}

// 	if ( (evt instanceof EndOfMediaEvent) && nodeList.contains(source) ) {
// 	    if (getState() != Started) {
// 		//System.out.println("IGNORED: GOT EOM # " + ": " +
// 		//		   eomEventsReceivedFrom.size() +
// 		//		   "from " + source);
// 	    }
// 	}
/*A.D.
	if ( (evt instanceof SeekFailedEvent) && nodeList.contains(source) ) {

	    int i = getNodeList().size();
	    super.stop(); // Added
	    setTargetState(Prefetched); // necessary even if super.stop is called.

	    for (int ii = 0; ii < i; ii++) {
		Controller c = (Controller) getNodeList().elementAt(ii);
		if (c != source) {
		    c.stop();
		}
	    }
	    /*
	    super.stop();
	    setMediaTime(new Time(0));
	    start();
	    */
	/*A.D.    sendEvent(new SeekFailedEvent(this, Started, Prefetched, Prefetched,
					  getMediaTime()));
	}
	    A.D.*/

	if ( (evt instanceof EndOfMediaEvent) && nodeList.contains(source) // &&
	     /* (getState() == Started) */ ) {

	    if (eomEventsReceivedFrom.contains(source)) {
		return;
	    }

	    eomEventsReceivedFrom.addElement(source);
	    if (eomEventsReceivedFrom.size() == nodeList.size()) {
		eomEventsReceivedFrom.removeAllElements();
		super.stop();
		sendEvent(new EndOfMediaEvent(this, Started, Prefetched,
					      getTargetState(), getMediaTime()));
//A.D.		playControl.progSetValue(false);
	    }
	    return;
	}

	/*A.D.
	if ((evt instanceof CachingControlEvent) && nodeList.contains(source) ) {
	    MediaCachingControl mcc = (MediaCachingControl) 
				((CachingControlEvent) evt).getCachingControl();
	    sendEvent(new CachingControlEvent(this, 
				mcc, 
				mcc.getContentProgress()));
	    return;
	} 
	A.D.*/

	Vector nodeList = getNodeList();
	Vector eventList = getPotentialEventsList();

	if (nodeList != null && nodeList.contains(source) &&
	    eventList != null && eventList.contains(evt.getClass().getName())) {
	    updateReceivedEventsList(evt);
	    notifyIfAllEventsArrived(nodeList, getReceivedEventsList());
	}
    }

    /**
    * tries to set the specified rate to all managed controllers
    * @return true if successfull, false otherwise
    */
    private boolean trySetRate(float rate) {
	Vector nodeList = getNodeList();
	int i = nodeList.size();

	while(--i >=0) {
	    Controller c = (Controller)nodeList.elementAt(i);
	    if ( c.setRate(rate) != rate ) {
		return false;
	    }
	}
	return true;
    }

   /**
   * Called from setRate.
   * This is used for subclasses to add in their own behavior.
   * @param factor the rate to set to.
   * return the actual rate should be returned
   */
   protected float doSetRate(float factor) {
	  return factor;
    }
    
    /**
     * Set the playback rate on the player.
     * It loops through its list of controllers and invoke setRate on each
     * of them.
     *
     * @param rate the rate to be set
     */
    public float setRate(float rate) {
	if (state == Unrealized || state == Realizing)
	    throw new NotRealizedError("Cannot set rate on an unrealized Player.");

	if (source instanceof RateConfigureable)
	    rate = checkRateConfig((RateConfigureable)source, rate);
	
	float oldRate = getRate();
	
	boolean restart = false;
	if (getState() == Controller.Started) {
	    restart = true;
	    stop("restarting");
	}
	
	float rateSet; // Actual rate set
	if (!trySetRate(rate)) {
	    if (!trySetRate(oldRate)) { // try to go back to the oldRate
		trySetRate(1.0F); // try setRate(1.0) which shouldn't fail
		rateSet = 1.0F;
	    } else {
		rateSet = oldRate;
	    }
	} else {
	    rateSet = rate;
	}
	super.setRate(rateSet);

	/*A.D.
	if (rateControl != null)
	    rateControl.progSetValue(rateSet);
	    A.D.*/
	    
	if (oldRate != rateSet && rateSet == 1.0f)
	    setMediaTime(getMediaTime());

	if (restart) {
	    syncStart(getTimeBase().getTime());
	}
	return rateSet;
    }


    /**
     * Check if the given rate configureable datasource supports the 
     * given rate.
     * if not, returns the closest match.
     *
     * @param rc rate configurable datasource to be checked
     *
     * @param rate rate to be shecked
     */
    float checkRateConfig(RateConfigureable rc, float rate) {
	RateConfiguration config[] = rc.getRateConfigurations();
	if (config == null)
	    return 1.0f;

	RateConfiguration c;
	RateRange rr;
	float corrected = 1.0f;
	for (int i = 0; i < config.length; i++) {
	    rr = config[i].getRate();
	    if (rr != null && rr.getMinimumRate()<=rate && rr.getMaximumRate() >= rate) {
		rr.setCurrentRate(rate);
		corrected = rate;
		c = rc.setRateConfiguration(config[i]);
		if (c != null && (rr = c.getRate()) != null)
		    corrected = rr.getCurrentRate();	
		break;
	    }
	}
	return corrected;
    }


    
    /*************************************************************************
     * INNER CLASSES
     *************************************************************************/
    
    class PlayThread extends MediaThread {
	MediaPlayer player;
	
	public PlayThread(MediaPlayer player) {
	    this.player = player;
	    setName(getName() + " (PlayThread)");
	    useControlPriority();
	}
	
	/**
	* starts playing the player
	*/
	public void run() {
	    player.play();
	}
    }
/*A.D.
    public class PlayBCA extends BooleanControlAdapter {
	public boolean value = false;
	public boolean setValue(boolean value) {
	    int targetState = MediaPlayer.this.getTargetState();
	    if (value == true) {
		if (targetState != Controller.Started) {
		    MediaPlayer.this.start();
		}
	    } else {
		if (targetState == Controller.Started) {
		    MediaPlayer.this.stop();
		}
	    }    
	    progSetValue(value);
	    return getValue();
	}

	public boolean progSetValue(boolean value) {
	    if (this.value != value) {
		this.value = value;
		informListeners();
		stopControl.progSetValue(!value);
	    }
	    return this.value;
	}
	
	public boolean getValue() {
	    return this.value;
	}
    }


    public class StopBCA extends BooleanControlAdapter {
	public boolean value = true;
    	public boolean setValue(boolean value) {
	    playControl.setValue( !value );
	    return getValue();
	}

	public boolean progSetValue(boolean value) {
	    if (this.value != value) {
		this.value = value;
		informListeners();
		playControl.progSetValue(!value);
	    }
	    return this.value;
	}   
	
	public boolean getValue() {
	    return this.value;
	}
    }
 A.D.*/
 /*A.D.
    public class SeekNCA extends NumericControlAdapter {
	// Scale down the numbers to milli second range so the floating pt.
	// numbers will not overflow.
	public float value = 0;
	public float setValue(float value) {
	    long t = (long)((double)value * 1000000);
	    this.value = value;
	    MediaPlayer.this.setMediaTime(new Time(t));
	    informListeners();
	    return getValue();
	}

	public float progSetValue(long value) {
	    this.value = (float) value / 1000000;
	    informListeners();
	    return this.value;
	}

	public float getValue() {
	    long t = MediaPlayer.this.getMediaNanoseconds() / 1000000;
	    return (float)t;
	}

	public float getUpperLimit() {
	    Time duration = MediaPlayer.this.getDuration();
	    long t = duration.getNanoseconds();
	    if (t >= Long.MAX_VALUE - 1 || duration == Duration.DURATION_UNKNOWN)
		return 0;
	    else
		t /= 1000000;
	    return (float)t;
	}
    }
    A.D.*/
/*A.D.
    public class RateNCA extends NumericControlAdapter {
	public float value = 1.0f;
	public float setValue(float value) {
	    if (value != this.value) {
		this.value = value;
		if (getTargetState() == Controller.Started) {
		    MediaPlayer.this.stop();
		    this.value = MediaPlayer.this.setRate(value);
		    MediaPlayer.this.start();
		} else
		    this.value = MediaPlayer.this.setRate(value);
		informListeners();
	    }
	    return this.value;
	}

	public float progSetValue(float value) {
	    if (this.value != value) {
		this.value = value;
		informListeners();
	    }
	    return this.value;
	}
	
	public float getValue() {
	    this.value = MediaPlayer.this.getRate();
	    return this.value;
	}

	public float getLowerLimit() {
	    return -4.0f;
	}

	public float getUpperLimit() {
	    return 4.0f;
	}

	public float getGranularity() {
	    return 0.1f;
	}
    }
 A.D.*/
/*A.D.
    public class PBC extends AtomicControlAdapter implements PlaybackControl {
	public PBC() {
	    super(null, true, null);

	    playControl = new PlayBCA();
	    
	    // Anonymous class
	    stopControl = new StopBCA();
	    seekControl = getSeekNCA();
	    rateControl = new RateNCA();
	}

	public BooleanControl getPlay() {
	    return playControl;
	}

	public BooleanControl getStop() {
	    return stopControl;
	}

	public ActionControl getStepForward() {
	    return null;
	}

	public ActionControl getStepBackward() {
	   return null;
	}

	public NumericControl getPlayRate() {
	    return rateControl;
	}

	public NumericControl getSeek() {
	    return seekControl;
	}

	public Control [] getControls() {
	    return new Control[0];
	}
    }
A.D.*/

    class GainCA extends GainControlAdapter {

	/**
	* constructs a gain control adapter.
	* @param mute specifies the mute state of the gain control
	*/
    public GainCA(boolean mute) {
	    super(mute);
	}

/**
* sets the mute state of the gain control adapter
* @param m muted if true; unmuted otherwise
*/
	public void setMute(boolean m) {
	    MediaPlayer.this.muteChange(m);
	    super.setMute(m);
	}
    }
  
}
