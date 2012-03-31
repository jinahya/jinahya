/*
 * @(#)MediaTimeSetEvent.java	1.15 98/03/28
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
 * A <code>MediaTimeSetEvent</code> is posted by a <code>Controller</code> when its 
 * media-time has been set with the <code>setMediaTime</code> method. 
 *  
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.15, MediaTimeSetEvent.java.
 */
public class MediaTimeSetEvent extends ControllerEvent {


    Time mediaTime;
    
    public MediaTimeSetEvent(Controller from, Time newMediaTime) {
	super(from);
	mediaTime = newMediaTime;
    }

    /**
     * Get the new media time of the <code>Controller</code> that
     * generated this event.
     *
     * @return The <code>Controller's</code> new media time.
     */
    public Time getMediaTime() {
	return mediaTime;
    }

    /**
     * Returns the String representation of this event's values.
     */
    public String toString() {
	return getClass().getName() + "[source=" + eventSrc + 
	    ",mediaTime=" + mediaTime + "]";
    }
}
