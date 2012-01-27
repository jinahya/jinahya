/*
 * @(#)DurationUpdateEvent.java	1.12 98/03/28
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
 * <CODE>DurationUpdateEvent</CODE> is posted by a <CODE>Controller</CODE> when its 
 * duration changes.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.12, 98/03/28.
 */
public class DurationUpdateEvent extends ControllerEvent {


    Time duration;
    
    public DurationUpdateEvent(Controller from, Time newDuration) {
	super(from);
	duration = newDuration;
    }

    /**
     * Get the duration of the media that this <CODE>Controller</CODE>
     * is using.
     *
     * @return The duration of this <CODE>Controller's</CODE> media.
     */
    public Time getDuration() {
	return duration;
    }

    /**
     * Returns the String representation of this event's values.
     */
    public String toString() {
	return getClass().getName() + "[source=" + eventSrc + 
	    ",duration=" + duration;
    }
}
