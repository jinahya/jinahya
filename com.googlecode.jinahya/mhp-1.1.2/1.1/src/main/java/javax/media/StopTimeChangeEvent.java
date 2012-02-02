/*
 * @(#)StopTimeChangeEvent.java	1.14 98/03/28
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
 * A <code>StopTimeChangeEvent</code> is generated by a <code>Controller</code> when its stop time
 * has changed.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.14, 98/03/28.
 */
public class StopTimeChangeEvent extends ControllerEvent {


    Time stopTime;
    
    public StopTimeChangeEvent(Controller from, Time newStopTime) {
	super(from);
	stopTime = newStopTime;
    }

    /**
     * Get the new stop-time for the <code>Controller</code> that
     * generated this event.
     *
     * @return The new stop time for the <code>Controller</code> that generated this event.
     */
    public Time getStopTime() {
	return stopTime;
    }

    /**
     * Returns the String representation of this event's values.
     */
    public String toString() {
	return getClass().getName() + "[source=" + eventSrc + 
	    ",stopTime=" + stopTime + "]";
    }
}