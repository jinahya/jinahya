/*
 * @(#)ClockStoppedException.java	1.14 98/03/28
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
 * A <code>ClockStoppedException</code> is thrown when a method that 
 * expects the <I>Clock</I> to be <I>Started</I> is
 * called on a <I>Stopped</I>&nbsp;<code>Clock</code>.
 * For example, this exception is thrown if <code>mapToTimeBase</code> 
 * is called on a <I>Stopped</I>&nbsp;<code>Clock</code>.
 * 
 * @version 1.14, 98/03/28
 */

public class ClockStoppedException extends MediaException {

    public ClockStoppedException() {
	super();
    }
    
    public ClockStoppedException(String reason) {
	super(reason);
    }
}
