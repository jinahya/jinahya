/*
 * @(#)RateChangeEvent.java	1.13 98/03/28
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
 * A <code>RateChangeEvent</code> is a <code>ControllerEvent</code> that is posted when 
 * a <code>Controller's</code> rate changes.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.13, 98/03/28.
 */
public class RateChangeEvent extends ControllerEvent {


    float rate;
    
    public RateChangeEvent(Controller from, float newRate) {
	super(from);
	rate = newRate;
    }

    /**
     * Get the new rate of the <code>Controller</code> that
     * generated this event.
     *
     * @return The <code>Controller's</code> new rate.
     */
    public float getRate() {
	return rate;
    }

    /**
     * Returns the String representation of this event's values.
     */
    public String toString() {
	return getClass().getName() + "[source=" + eventSrc + 
	    ",rate=" + rate + "]";
    }
}
