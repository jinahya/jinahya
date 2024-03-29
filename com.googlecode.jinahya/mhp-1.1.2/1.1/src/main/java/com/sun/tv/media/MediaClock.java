/*
 * @(#)MediaClock.java	1.16 98/10/29
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

import javax.media.*;

/**
 * This class is a part of the porting layer implementation for JavaTV
 * MediaClock implements javax.media.Clock.
 * This is not a running thread that implements the clock ticks.
 * It just implements the math and maintains the correct states
 * to perform the computations from media-time to time-base-time.
 * @version 1.16, 98/10/29
 **/
public class MediaClock implements Clock {

    private TimeBase master;
    private long startTime = Long.MAX_VALUE; 	// in time base time
    private long stopTime = Long.MAX_VALUE;	// in media time
    private long mediaTime = 0;		// The media time when the state of
					// the clock last changes.
    private long mediaStart = 0;	// lower bound of the mediaTime. 
    private long mediaLength = -1;	// upper bound of the mediaTime.
    private float rate = (float)1.0;

    private final static int STOPPED = 0;
    private final static int STARTED = 1;

    /**
    * constructs a MediaClock
    */
    public MediaClock() {
	master = new SystemTimeBase();
    }


    /**
     * Set a time base on the clock.
     * All media-time to time-base-time will be computed with the
     * given time base.
     * @param master the new master time base.
     * @exception IncompatibleTimeBaseException thrown if clock cannot accept
     * the given time base.
     **/
    public void setTimeBase(TimeBase master) throws IncompatibleTimeBaseException{
	if (getState() == STARTED) {
	    throw new ClockStartedError("setTimeBase cannot be used on a started clock.");
	}
	if (master == null) {
	    if (!(this.master instanceof SystemTimeBase))
		this.master = new SystemTimeBase();
	} else
	    this.master = master;
    }

    /**
     * Start the clock with the given time-base-time
     * @param the time base time to start the clock.
     **/
    public void syncStart(Time tbt) {
	if (getState() == STARTED) {
	    throw new ClockStartedError("syncStart() cannot be used on an already started clock.");
	}
	// If the given start time is already later than now.  We'll reset
	// the clock start time to now.
	if (master.getNanoseconds() > tbt.getNanoseconds())
	    startTime = master.getNanoseconds();
	else
	    startTime = tbt.getNanoseconds();
    }

    /**
     * Stop the clock immediately.
     **/
    public void stop() {
	if (getState() == STOPPED) {
	    // It's already stopped.  No-op.
	    return;
	}

	// It's a change of state, so we'll mark the mediaTime.
	mediaTime = getMediaNanoseconds();

	// Reset the start time.
	startTime = Long.MAX_VALUE;
    }

    /**
     * Preset a stop time in media time.
     * @param t the preset stop time.
     **/
    public void setStopTime(Time t) {
	if (getState() == STARTED && stopTime != Long.MAX_VALUE) {
	    throw new StopTimeSetError("setStopTime() may be set only once on a Started Clock");
	}
	stopTime = t.getNanoseconds();
    }

    /**
     * Return the preset stop time.
     * @return the preset stop time.
     **/
    public Time getStopTime() {
	return new Time(stopTime);
    }

    /**
     * Set the media time.  This will be the media presented at the
     * clock's start time.
     * @param the media time to set to.
     **/
    public void setMediaTime(Time now) {
	if (getState() == STARTED) {
	    throw new ClockStartedError("setMediaTime() cannot be used on a started clock.");
	}
	mediaTime = validateTime(now.getNanoseconds());
    }

    /**
     * Return the current media time.
     * @return the current media time.
     **/
    public Time getMediaTime() {
	return new Time(getMediaNanoseconds());
    }

    /**
     * Get the current media time in nanoseconds.
     * @return the current media time in nanoseconds.
     */
    public long getMediaNanoseconds() {
	if (getState() == STOPPED) {
	    return mediaTime;
	}

	long now = master.getNanoseconds();
	if (now > startTime) {
	    // The media has been playing for a while.
	    long t = (long)((double)(now - startTime) * rate) + mediaTime;
	    return validateTime(t);
	} else {
	    // We haven't reached the scheduled start time yet.
	    return mediaTime;
	}
    }

    /**
     * Set the lower bound of the media time.
     * @param t the lower bound of the media time.
     */
    protected void setMediaStart(long t) {
	mediaStart = t;
    }

    /**
     * Set the upper bound of the media time.
     * @param t the upper bound of the media time.
     */
    protected void setMediaLength(long t) {
	mediaLength = t;
    }

    /**
     * Check the time and restricted to not go beyond the duration of 
     * the media.
     * @return the given time within the valid boundary.
     */
    protected long validateTime(long t) {
	if (t < mediaStart)
	    return mediaStart;
	if (mediaLength != -1 && t > mediaStart + mediaLength)
	    return mediaStart + mediaLength;
	return t;
    }

    /**
     * Return the current state of the clock in either started or stopped
     * state.
     * @return the current clock state.
     */
    protected int getState() {

	// A start time has not been set.
	if (startTime == Long.MAX_VALUE)
	    return STOPPED;

	// A stop time has not been set.
	if (stopTime == Long.MAX_VALUE)
	    return STARTED;

	// The tricky case is when the clocked has started and
	// the media has reached the scheduled stop time.  In that
	// case, the clock is considered stopped.
// COMMENTED OUT BY BABU
// 	long now = master.getNanoseconds();
//         if (now > startTime) {
// 	    // The media has already been playing for some time.
		
// 	    long curMediaTime = (long)((now - startTime) * rate) + mediaTime; 
// 	    if ((rate > 0 && curMediaTime >= stopTime) ||
// 		(rate < 0 && curMediaTime <= stopTime)) {
// 	        // We have gone past the scheduled stop time.
// 		// We are in the stop state.
// 		System.out.println(this + ": MediaClock getState() SIDEEFFECT");
// 		mediaTime = validateTime(stopTime);
// 		startTime = Long.MAX_VALUE;
// 	        return STOPPED;
// 	    }
// 	}

	// In all other cases, the clock has already been started.
	return STARTED;
    }

    /**
     * Return the Sync Time.
     * Not yet implementated.
     **/
    public Time getSyncTime() {
	return new Time(0);
    }

    /**
     * Get the Time Base that the clock is currently using.
     * @return the Time Base that the clock is currently using.
     **/
    public TimeBase getTimeBase() {
	return master;
    }

    /**
     * Map the the given media-time to time-base-time.
     * @param t media time
     * @return time base time.
     * @exception ClockStoppedException is thrown if this method is invoked
     * on a stopped clock.
     **/
    public Time mapToTimeBase(Time t) throws ClockStoppedException {
	if (getState() == STOPPED) {
	    throw new ClockStoppedException();
	}
	return new Time((long)((t.getNanoseconds() - mediaTime)/rate) + startTime);
    }

    /**
     * Set the rate of presentation: 1.0: normal, 2.0: twice the speed.
     * -2.0: twice the speed in reverse.
     * @param the speed factor.
     * @return the actual rate the clock is set to.
     **/
    public float setRate(float factor) {
	if (getState() == STARTED) {
	    throw new ClockStartedError("setRate() cannot be used on a started clock.");
	}
	rate = factor;
	return rate;
    }

    /**
     * Get the current presentation speed.
     * @return the current presentation speed.
     **/
    public float getRate() {
	return rate;
    }

}

