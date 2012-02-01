/*
 * @(#)StartEvent.java	1.33 98/03/28
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
 * <code>StartEvent</code> is a <code>TransitionEvent</code> that indicates that
 * a <code>Controller</code> has entered the <i>Started</i> state.
 * Entering the <i>Started</i> state implies that 
 * <code>syncStart</code> has been invoked, providing a new
 * <i>media time</i> to <i>time-base time</i> mapping.
 * <code>StartEvent</code> provides the <I>time-base time</I>
 * and the <I>media-time</I> that <i>Started</i> this <CODE>Controller</CODE>.
 * 
 * @see Controller
 * @see ControllerListener
 * @version 1.33, 98/03/28
 */
public class StartEvent extends TransitionEvent {

    private Time mediaTime, timeBaseTime;

    /**
     * Construct a new <code>StartEvent</code>.
     * The <code>from</code> argument identifies the <code>Controller</code> that
     * is generating this event.
     * The <code>mediaTime</code> and the <code>tbTime</code> identify the <I>media-time</I> to
     * <I>time-base-time</I> mapping that <i>Started</i> the <code>Controller</code>
     * @param from The <code>Controller</code> that has <I>Started</I>.
     * @param mediaTime The media time when the <code>Controller</code> <I>Started</I>.
     * @param tbTime The time-base time when the <code>Controller</code> <I>Started</I>.
     *
     */
    public StartEvent(Controller from,
		      int previous, int current, int target,
		      Time mediaTime, Time tbTime) {
	super(from, previous, current, target);
	this.mediaTime = mediaTime;
	this.timeBaseTime = tbTime;
    }

    /**
     * Get the clock time (<I>media time</I>) when the <code>Controller</code> started.
     *
     * @return The <code>Controller's</code>&nbsp;<I>media time</I> when it started.
     */
    public Time getMediaTime() {
	return mediaTime;
    }

    /**
     * Get the time-base time that started the <code>Controller</code>.
     * @return The <I>time-base time</I> associated with the <code>Controller</code> when it started.
     */
    public Time getTimeBaseTime() {
	return timeBaseTime;
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
	    ",timeBaseTime=" + timeBaseTime +
	    "]";
    }
}
