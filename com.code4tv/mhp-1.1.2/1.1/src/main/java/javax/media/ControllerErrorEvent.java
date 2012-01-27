/*
 * @(#)ControllerErrorEvent.java	1.18 98/03/28
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
 * A <CODE>ControllerErrorEvent</CODE> describes an event that is
 * generated when an error condition occurs that will cause
 * a <code>Controller</code> to cease functioning.  Events
 * should only subclass from <code>ControllerErrorEvent</code> if the 
 * error being reported will result in catastrophic failure if action is
 I not taken, or if the <code>Controller</code> has already failed.
 *
 * A <CODE>ControllerErrorEvent</CODE> indicates that
 * the <code>Controller</code> is closed.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.18, 98/03/28
 */
public class ControllerErrorEvent extends ControllerClosedEvent {


    public ControllerErrorEvent(Controller from) {
        super(from);
    }

    public ControllerErrorEvent(Controller from, String why) {
        super(from, why);
    }
							     
    /**
     * Returns the String representation of this event's values.
     */
    public String toString() {
	return getClass().getName() + "[source=" + eventSrc + 
	    ",message=" + message + "]";
    }
}
