/*
 * @(#)RestartingEvent.java	1.16 98/03/28
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
 * A <code>RestartingEvent</code> indicates that a <code>Controller</code> has moved from
 * the <i>Started</i> state back to the <i>Prefetching</i> state
 * (a <i>Stopped</i> state) and intends to return to the
 * <i>Started</i> state when <i>Prefetching</i> is complete.
 * This  occurs when a <i>Started</i>&nbsp;<code>Player</code>
 * is asked to change its rate or media time
 * and to fulfill the request must prefetch its media again.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.16, 98/03/28.
*/

public class RestartingEvent extends StopEvent {

    public RestartingEvent(Controller from,
			   int previous, int current, int target,
			   Time mediaTime) {
	super(from, previous, current, target, mediaTime);
    }
}
