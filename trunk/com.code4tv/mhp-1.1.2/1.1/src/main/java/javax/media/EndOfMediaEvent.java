/*
 * @(#)EndOfMediaEvent.java	1.23 98/03/28
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
 * An <code>EndOfMediaEvent</code> indicates that the <code>Controller</code> 
 * has reached the end of its media and is stopping.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.23, 98/03/28.
*/

public class EndOfMediaEvent extends StopEvent {

    public EndOfMediaEvent(Controller from,
			   int previous, int current, int target,
			   Time mediaTime) {
	super(from, previous, current, target, mediaTime);
    }
}
