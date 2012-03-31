/*
 * @(#)RealizeCompleteEvent.java	1.16 98/03/28
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
 * A <code>RealizeCompleteEvent</code> is posted when a <code>Controller</code> finishes
 * <I>Realizing</I>. This occurs when a <code>Controller</code> moves
 * from the <i>Realizing</i> state to the <i>Realized</i>
 * state, or as an acknowledgement that the <code>realize</code>
 * method was called and the <code>Controller</code> is already
 * <i>Realized</i>.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.16, 98/03/28
 */
public class RealizeCompleteEvent extends TransitionEvent {

    public RealizeCompleteEvent(Controller from,
				int previous, int current, int target) {
	super(from, previous, current, target);
    }
}
