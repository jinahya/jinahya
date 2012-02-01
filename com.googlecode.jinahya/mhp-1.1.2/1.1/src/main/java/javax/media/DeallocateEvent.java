/*
 * @(#)DeallocateEvent.java	1.13 98/03/28
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
 * A <code>DeallocateEvent</code> is posted as an acknowledgement of the
 * invocation of the <code>deallocate</code> method. It implies that
 * the scarce resources associated with this <code>Controller</code>
 * are no longer available and must be reacquired.
 * <p>
 * A  <code>DeallocateEvent</code> can be posted at any time regardless of the <CODE>Controller's</CODE>
 * previous or current state.
 * <code>DeallocateEvent</code> is a <code>StopEvent</code> because if the <code>Controller</code>
 * is in the <I>Started</I> state when the event is posted, it transitions to one of the
 * <i>Stopped</i> states.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.13, 98/03/28.
*/

public class DeallocateEvent extends StopEvent {

    public DeallocateEvent(Controller from,
			   int previous, int current, int target,
			   Time mediaTime) {
	super(from, previous, current, target, mediaTime);
    }
}
