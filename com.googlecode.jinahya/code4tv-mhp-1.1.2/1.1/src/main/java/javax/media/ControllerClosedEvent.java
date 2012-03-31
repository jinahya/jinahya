/*
 * @(#)ControllerClosedEvent.java	1.7 98/03/28
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
 * A <CODE>ControllerClosedEvent</CODE> describes an event that is
 * generated when an a <code>Controller</code> is closed. This implies
 * that the <code>Controller</code> is no longer operational.
 **/

public class ControllerClosedEvent extends ControllerEvent {

    protected String message;
    
    /**
     * Construct a <CODE>ControllerClosedEvent</CODE>.
     */
    public ControllerClosedEvent(Controller from) {
        super(from);
	message = new String("");
    }

    public ControllerClosedEvent(Controller from, String why) { 
	super(from);
	message = why;
    }

    /**
     * Obtain the message describing why this event
     * occurred.
     *
     * @return Message describing event cause.
     */
    public String getMessage() {
	return message;
    }
     
}
