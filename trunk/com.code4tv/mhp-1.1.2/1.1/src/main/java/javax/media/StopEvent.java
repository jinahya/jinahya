/*
 * @(#)StopEvent.java	1.30 98/03/28
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

package javax.media;

/**
 * <code>StopEvent</code> is a <code>ControllerEvent</code> that indicates that a <code>Controller</code>
 * has stopped.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.30, 98/03/28
 */
public class StopEvent extends TransitionEvent {

    private Time mediaTime;

    /**
     * @param from The <code>Controller</code> that generated this event.
     * @param mediaTime The <I>media time</I> at which the <code>Controller</code> stopped.
     */
    public StopEvent(Controller from,
		     int previous, int current, int target,
		     Time mediaTime) {
	super(from, previous, current, target);
	this.mediaTime = mediaTime;
    }

    /**
     * Get the clock time (<I>media time</I>) that was passed into the constructor. 
     *
     * @return The <i>mediaTime</i> at which the <code>Controller</code> stopped.
     */
    public Time getMediaTime(){
	return mediaTime;
    }

    /**
     * Returns the String representation of this event's values.
     */
    public String toString() {
	return getClass().getName() + "[source=" + eventSrc + 
	    ",previous=" + stateName(previousState) + 
	    ",current=" + stateName(currentState) +
	    ",target=" + stateName(targetState) + 
	    ",mediaTime=" + mediaTime +
	    "]";
    }
}
