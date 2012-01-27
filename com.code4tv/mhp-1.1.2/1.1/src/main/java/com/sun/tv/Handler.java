/*
 * @(#)Handler.java	1.9 00/01/07
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv;

import java.util.Vector;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import javax.media.*;
import javax.media.protocol.*;
import javax.tv.locator.*;
import javax.tv.service.selection.*;

import com.sun.tv.receiver.*;

/**
 * <code>ServiceMediaHandler</code> represents an handler of service
 * components which are real time media sharing the same clock.  A
 * <code>ServiceMediaHandler</code> is associated with the
 * <code>Service</code> currently selected in the
 * <code>ServiceContext</code> from which it was obtained.
 *
 * @see ServiceContext
 * @see javax.tv.media.MediaSelectControl
 */

public class Handler implements ServiceMediaHandler, Player {

	private Locator locator = null;
	private Player player = null;
	private Control videoSizeControl = null;
	private Control mediaSelectControl = null;
	private ServiceContextImpl context = null;

	public Handler(Locator locator, ServiceContextImpl context) {
		this.locator = locator;
		this.context = context;

		String locatorStr = LocatorImpl.getMediaFile(locator);
		if (locatorStr == null) {
			System.out.println("Missing emulation for locator : " + locator.toExternalForm());
		} 

		try {
			MediaLocator medialocator = new MediaLocator(locatorStr);
			this.player = Manager.createPlayer(medialocator);
		} catch (Exception e) {
			System.out.println("Handler error: " + locatorStr + ", " + e);
			this.player = null;
		}

		loadControls();
	}

	private void loadControls() {
		try {
			videoSizeControl = (Control)Class.forName(Settings.AWTVideoSizeControlClassName).newInstance();
			mediaSelectControl = new com.sun.tv.media.MediaSelectControlImpl(this, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Locator getServiceLocator() {
		return this.locator;
	}

	public ServiceContextImpl getServiceContext() {
		return this.context;
	}

	public boolean validHandler() {
		return (player != null);
	}

  /**
   * Reports the portions of the service on which this handler operates.
   *
   * @return An array of <code>Locator</code>s representing the portions
   * of the service on which this handler operates.
   */
	public Locator[] getServiceContentLocators() {
		if (this.context == null) {
			return new Locator[0];
		}

		return this.context.getServiceComponentLocators();
	}

    /**
     * Obtain the display <code>Component</code> for this <code>Player</code>.
     * The display <code>Component</code> is where visual media
     * is rendered.
     * If this <code>Player</code> has no visual component,
     * <code>getVisualComponent</code> returns <CODE>null</CODE>.
     * For example, <code>getVisualComponent</code> might return
     * <CODE>null</CODE> if the <code>Player</code> only plays audio.
     *
     *
     * @return The media display <code>Component</code> for this
     * <code>Player</code>.
     */
	public Component getVisualComponent() {
		if (player == null) 
			return null;

		if (player.getState() < player.Realized) 
			return null;

		return player.getVisualComponent();
	}

    /**
     * Obtain the object for controlling this <code>Player's</code>
     * audio gain. 
     * If this player does not have a 
     * <code>GainControl</code>, <code>getGainControl</code> returns
     * <CODE>null</CODE>.  
     * For example, <code>getGainControl</code> might return
     * <CODE>null</CODE> if the <code>Player</code> does not play audio data.
     *
     * @return The <code>GainControl</code> object for this
     * <code>Player</code>.
     */
	public GainControl getGainControl() {
		return player.getGainControl();
	}

    /**
     * Obtain the <code>Component</code> that provides the default user
     * interface for controlling this <code>Player</code>.
     * If this <code>Player</code> has no default control panel,
     * <code>getControlPanelComponent</code> returns <CODE>null</CODE>.
     *
     * @return The default control panel GUI for this <code>Player</code>.
     */
	public Component getControlPanelComponent() {
		return player.getControlPanelComponent();
	}
    
    /**
     * Start the <code>Player</code> as soon as possible.
     * The <CODE>start</CODE> method attempts to transition the
     * <code>Player</code> to the <i>Started</i> state.
     * If the <CODE>Player</CODE> has not been <i>Realized</i> or
     * <i>Prefetched</i>, <code>start</code> automatically performs
     * those actions. The appropriate events
     * are posted as the <code>Player</code> moves through each state.
     */
	public void start() {
		player.start();
	}

    /**
     * Assume control of another <code>Controller</code>.
     *
     * @param newController The <code>Controller</code> to be managed.
     *
     * @exception IncompatibleTimeBaseException Thrown if the added
     * <code>Controller</code> cannot take this
     * <code>Player's</code>&nbsp;<CODE>TimeBase</CODE>.
     */
	public void addController(Controller newController) 
			throws IncompatibleTimeBaseException {
		player.addController(newController);
	}

    /**
     * Stop controlling a <code>Controller</code>.
     *
     * @param oldController The <code>Controller</code> to stop managing.
     */
	public void removeController(Controller oldController) {
		player.removeController(oldController);
	}

    /**
     * Set the media source the <code>MediaHandler</code>
     * should use to obtain content.
     *
     * @param source The <code>DataSource</code> used
     * by this <code>MediaHandler</code>.
     *
     * @exception IOException Thrown if there is an error
     * using the <code>DataSource</code>
     *
     * @exception IncompatibleSourceException Thrown if
     * this <code>MediaHandler</code> cannot make use
     * of the <code>DataSource</code>.
     */
	public void setSource(DataSource source) 
			throws IOException, IncompatibleSourceException {
		player.setSource(source);
	}

    /**
     * Get the current state of this <code>Controller</code>.
     * The state is an integer constant as defined above.
     * <p>
     * <B>Note:</B>
     * A race condition can occur between
     * the return of this method and the execution of
     * a state changing method.
     * 
     * @return The <code>Controller's</code> current state.
     */
	public int getState() {
		return player.getState();
	}

    /**
     * Get the current target state of this <code>Controller</code>.
     * The state is an integer constant as defined above.
     *<p>
     * <B>Note:</B>
     * A race condition can occur between
     * the return of this method and the execution of
     * a state changing method.
     *
     * @return The <code>Controller's</code> current target state.
     */
	public int getTargetState() {
		return player.getTargetState();
	}
    
    /**
     * Construct the media dependent portions of the <code>Controller</code>.
     * This can require examining media data and might
     * take some time to complete.
     * <p>
     * The <code>realize</code> method puts the <code>Controller</code> into the <i>Realizing</i> state and returns immediately.
     * When <code>realize</code> is complete and the <code>Controller</code> is in the
     * <i>Realized</i> state, the <code>Controller</code> posts a
     * <code>RealizeCompleteEvent</code>.
     *
     */
	public void realize() {
		player.realize();
	}

    /**
     * Process as much data as necessary
     * to reduce the <CODE>Controller's</CODE> start latency to the shortest possible time.
     * This typically requires examining media data and takes some
     * time to complete.
     * <p>
     * The <code>prefetch</code> method puts the <code>Controller</code> into the <i>Prefetching</i> state and returns immediately.
     * When <i>Prefetching</i> is complete and the <code>Controller</code> is in
     * the <i>Prefetched</i> state, the <code>Controller</code> posts
     * a <code>PrefetchCompleteEvent</code>.
     *
     */
	public void prefetch() {
		player.prefetch();
	}

    /**
     * Abort the current operation and cease any activity that
     * consumes system resources. If a <code>Controller</code> is not yet 
     * <i>Realized</i>,
     * it returns to the <i>Unrealized</i> state. Otherwise, the <code>Controller</code>
     * returns to the <i>Realized</i> state.
     * <p>
     * It is illegal to call <code>deallocate</code> on a <i>Started</i>&nbsp;<code>Controller</code>. 
     * A <CODE>ClockStartedError</CODE> is thrown if <CODE>deallocate</CODE>
     * is called and the <CODE>Controller</CODE> is in the <i>Started</i> state.
     */
	public void deallocate() {
		player.deallocate();
	}

    /**
     *
     * Release all resources and cease all activity.
     * The <CODE>close</CODE> method indicates that the <code>Controller</code> will
     * no longer be used, and the <code>Controller</code> can
     * shut itself down.
     * A <code>ControllerClosedEvent</code> is posted. 
     * Methods invoked on a closed <code>Controller</code>
     * might throw errors.
     */
	public void close() {
		player.close();
	}

    /**
     * Get the <code>Controller's</code> start latency in nanoseconds. The start latency represents a 
     * worst-case estimate of the  amount of time it will take
     * to present the first frame of data.
     * <p>
     *
     * This method is useful for determining how far in advance the
     * <code>syncStart</code> method must be invoked to ensure 
     * that media will be
     * rendered at the specified start time.
     * <p>
     * For a <code>Controller</code> that has a variable start latency,
     * the value returned represents the maximum possible
     * start latency.  If you call <code>getStartLatency</code> on a <CODE>Controller</CODE> that isn't <I>Prefetched</I> 
     * and <code>getStartLatency</code> returns <code>LATENCY_UNKNOWN</code>, calling
     * <code>prefetch</code> and then calling <code>getStartLatency</code> again after the <code>Controller</code> posts 
     * a <code>PrefetchCompleteEvent</code> might
     * return a more accurate estimate. 
     * If <code>getStartLatency</code> still returns <code>LATENCY_UNKNOWN</code>, 
     * the start latency is indeterminate and you might not be able to use
     * <code>syncStart</code> to 
     * synchronize the <code>Controller</code> with other <code>Controllers</code>.
     * <p>
     * <b>Note</b>: In most cases, the value returned by
     * <code>getStartLatency</code> will change once the
     * <code>Controller</code> is <i>Prefetched</i>.
     *
     * @return The time it will take before the first frame of media
     * can be presented.
     */
	public Time getStartLatency() {
		return player.getStartLatency();
	}

    /**
     * Get a list of the <code>Control</code> objects that
     * this <code>Controller</code> supports.
     * If there are no controls, an array of length zero
     * is returned.
     *
     * @return A list of <code>Controller</code>&nbsp;<code>Controls</code>.
     */
	public Control[] getControls() {
		Control playerControls[] = player.getControls();
		Control controls[] = null;
		if (playerControls != null) {
			controls = new Control[playerControls.length + 2];
			controls[0] = videoSizeControl;
			controls[1] = mediaSelectControl;
			for (int i = 0; i < playerControls.length; i++) {
				controls[i+2] = playerControls[i];
			}
		} else {
			controls = new Control[2];
			controls[0] = videoSizeControl;
			controls[1] = mediaSelectControl;
		}
		return controls;
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
		if ("javax.tv.media.AWTVideoSizeControl".equals(forName)) {
			return videoSizeControl;
		} else if ("javax.tv.media.MediaSelectControl".equals(forName)) {
			return mediaSelectControl;
		} 
		return player.getControl(forName);
	}
 
    /**
     * Specify a <code>ControllerListener</code> to which
     * this <code>Controller</code>  will send events.
     * A <code>Controller</code> can have multiple
     * <code>ControllerListeners</code>.
     *
     * @param listener The listener to which the <CODE>Controller</CODE>
     * will post events.
     */
	public void addControllerListener(ControllerListener listener) {
		player.addControllerListener(listener);
	}

    /**
     * Remove the specified listener from this <code>Controller's</code>
     * listener list.
     *
     * @param listener The listener that has been receiving events from this
     * <code>Controller</code>.
     */
	public void removeControllerListener(ControllerListener listener) {
		player.removeControllerListener(listener);
	}

    /**
     * Get the duration of the media represented
     * by this object.
     * The value returned is the media's duration
     * when played at the default rate.
     * If the duration can't be determined  (for example, the media object is presenting live
     * video)  <CODE>getDuration</CODE> returns <CODE>DURATION_UNKNOWN</CODE>.
     *
     * @return A <CODE>Time</CODE> object representing the duration or DURATION_UNKNOWN.
     */
	public Time getDuration() {
		return player.getDuration();
	}

    /**
     * Set the <code>TimeBase</code> for this <code>Clock</code>.
     * This method can only be called on a 
     * <i>Stopped</i>&nbsp;<code>Clock</code>.
     * A <code>ClockStartedError</code> is thrown if
     * <code>setTimeBase</code> is called on a <i>Started</i>&nbsp;
     * <code>Clock</code>.    
     * 
     * <p>
     * A <code>Clock</code> has a default <code>TimeBase</code> that
     * is determined by the implementation. 
     * To reset a <code>Clock</code> to its default 
     * <code>TimeBase</code>, call <code>setTimeBase(null)</code>.
     * @param master The new <CODE>TimeBase</CODE> or <CODE>null</CODE> to reset the <code>Clock</code>
     * to its default <code>TimeBase</code>.
     * @exception IncompatibleTimeBaseException Thrown if
     * the <code>Clock</code> can't use the specified <code>TimeBase</code>.
     */
	public void setTimeBase(TimeBase master) throws IncompatibleTimeBaseException {
		player.setTimeBase(master);
	}
    
    /**
     * Synchronize the current <i>media time</i> to the specified 
     * <I>time-base time</I> and start the <code>Clock</code>. 
     * The <code>syncStart</code> method sets the <i>time-base start-time</i>,
     * and puts the <code>Clock</code> in the <i>Started</i> state.
     * This method can only be called on a
     * <i>Stopped</i>&nbsp;<code>Clock</code>. 
     * A <code>ClockStartedError</code> is thrown if
     * <code>setTimeBase</code> is called on a <i>Started</i>&nbsp;
     * <code>Clock</code>.  
     * 
     * @param at The <i>time-base time</i> to equate with the
     * current <i>media time</i>.
     */
	public void syncStart(Time at) {
		player.syncStart(at);
	}
    
    /**
     * Stop the <code>Clock</code>. 
     * Calling <code>stop</code> releases the <code>Clock</code> from
     * synchronization with the <code>TimeBase</code>.
     * After this request is issued, the <code>Clock</code> is in the 
     * <i>Stopped</i> state.
     * If <code>stop</code> is called on
     * a <i>Stopped</i>&nbsp;<code>Clock</code>, the request is ignored.
     *
     */
	public void stop() {
		player.stop();
	}

    /**
     *
     * Set the <i>media time</i> at which you want the <code>Clock</code>
     * to stop.
     * The <code>Clock</code> will stop when its <i>media time</i>
     * passes the stop-time.
     * To clear the stop time, set it to: <code>Clock.RESET</code>.
     * <p>
     *
     * You can always call <code>setStopTime</code> on a <i>Stopped</i>&nbsp;
     * <code>Clock</code>.
     * <p>
     *
     * On a <i>Started</i>&nbsp;<code>Clock</code>, the stop-time can only
     * be set <I>once</I>.
     * 
     * A <code>StopTimeSetError</code> is thrown if <code>setStopTime</code>
     * is called and the <i>media stop-time</i> has already been set.
     *
     * @param stopTime The time at which you want the
     * <code>Clock</code> to stop, in <i>media time</i>.
     */
	public void setStopTime(Time stopTime) {
		player.setStopTime(stopTime);
	}

    /**
     * Get the last value successfully set by <CODE>setStopTime</CODE>.
     * 
     * Returns the constant <CODE>Clock.RESET</CODE> if no stop time is set.
     * (<CODE>Clock.RESET</CODE> is the default stop time.)
     *
     * @return The current stop time. 
     */
	public Time getStopTime() {
		return player.getStopTime();
	}
    
    /**
     * Set the <code>Clock's</code>&nbsp;<i>media time</i>.
     * This method can only be called on
     * a <i>Stopped</i>&nbsp;<code>Clock</code>.  
     * A <code>ClockStartedError</code> is thrown if
     * <code>setMediaTime</code> is called on a <i>Started</i>&nbsp;
     * <code>Clock</code>.
     * 
     * @param now The new media time.
     */
	public void setMediaTime(Time now) {
		player.setMediaTime(now);
	}

    /**
     * Get this <code>Clock's</code> current <i>media time</i>.
     * A <i>Started</i>&nbsp;<code>Clock's</code>&nbsp;<i>media time</i>
     * is based on  its <code>TimeBase</code>
     * and rate, as described in <a href="#start"><I>Starting a Clock</I></a>.
     *
     * @return The current <i>media time</i>.
     */
	public Time getMediaTime() {
		return player.getMediaTime();
	}

    /**
     * Get this <code>Clock's</code> current <i>media time</i>
     * in nanoseconds.
     * 
     * @return The current <i>media time</i> in nanoseconds.
     */
	public long getMediaNanoseconds() {
		return player.getMediaNanoseconds();
	}
    
    /**
     * Get the current <i>media time</i> or the time until this
     * <code>Clock</code> will synchronize to its <code>TimeBase</code>.
     * The <code>getSyncTime</code> method is used by <code>Players</code> and
     * advanced applet writers to  synchronize <code>Clocks</code>.
     * <p>
     *
     * Like <code>getMediaTime</code>, this method returns 
     * the <code>Clock's</code> current <i>media time</i>,
     * which is based on its <code>TimeBase</code> and rate.
     *
     * However, when <code>syncStart</code> is used to start
     * the <code>Clock</code>, <code>getSyncTime</code> performs a countdown
     * to the time-base start-time, returning the time remaining until
     * the <i>time-base start-time</i>.
     * Once the <code>TimeBase</code> reaches the
     * <i>time-base start-time</i>,&nbsp;<code>getSyncTime</code>
     * and <code>getMediaTime</code> will return the same value.<p>
     *
     */
	public Time getSyncTime() {
		return player.getSyncTime();
	}

    /**
     * Get the <code>TimeBase</code> that this <code>Clock</code> is using.
     */
	public TimeBase getTimeBase() {
		return player.getTimeBase();
	}

    /**
     * Get the <code>TimeBase</code> time corresponding to the specified <i>media time</i>.
     *
     * @exception ClockStoppedException Thrown if <CODE>mapToTimeBase</CODE> is called on a <i>Stopped</i>&nbsp;
     * <code>Clock</code>.
     * 
     * @param t The <i>media time</i> to map from.
     *
     * @return The <I>time-base time</I> in <I>media-time</I> coordinates.
     */
	public Time mapToTimeBase(Time t) throws ClockStoppedException {
		return player.mapToTimeBase(t);
	}

    /**
     * Get the current temporal scale factor.
     * The scale factor defines the relationship
     * between the <code>Clock's</code>&nbsp;<i>media time</i> 
     * and its <code>TimeBase</code>.<p>
     *
     * For example, a rate of 2.0 indicates that <i>media time</i>
     * will pass twice as fast as the <code>TimeBase</code> time once
     * the <code>Clock</code>
     * starts.  Similarly, a negative rate indicates that
     * the <code>Clock</code> runs in the opposite direction of its <code>TimeBase</code>. 
     * All <code>Clocks</code> are
     * guaranteed to support a rate of 1.0, the default rate. <code>Clocks</code>  are not required 
     * to support any other rate.<p>
     */
	public float getRate() {
		return player.getRate();
	}
 
    /**
     * Set the temporal scale factor.
     * The argument <i>suggests</i> the scale factor to use.<p>
     * 
     * The <code>setRate</code> method returns the actual rate set by the
     * <code>Clock</code>.  <code>Clocks</code> should set their rate as close to 
     * the requested
     * value as possible, but are not required to set the rate to the exact
     * value of any argument other than 1.0. A <code>Clock</code> is only guaranteed to set
     * its rate exactly to 1.0.
     * <p>
     * 
     * You can only call this method on a <i>Stopped</i>&nbsp;<code>Clock</code>. A
     * <code>ClockStartedError</code> is thrown if <code>setRate</code> is called on a <i>Started</i>&nbsp;<code>Clock</code>.<p>
     * 
     * @param factor The temporal scale factor (rate) to set.
     * @return The actual rate set.
     */
	public float setRate(float factor) {
		return player.setRate(factor);
	}

}
