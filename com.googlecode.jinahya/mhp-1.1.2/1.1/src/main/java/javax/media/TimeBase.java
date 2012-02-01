/*
 * @(#)TimeBase.java	1.15 98/03/28
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
 * A <code>TimeBase</code> is a constantly ticking source of time,
 * much like a crystal.<p>
 *
 * Unlike a <code>Clock</code>, a <code>TimeBase</code> cannot be temporally
 * transformed, reset, or stopped.
 *
 * @see Clock
 * @version 1.15, 98/03/28.
 */
public interface TimeBase {

    /**
     * Get the current time of this <code>TimeBase</code>.
     *
     * @return the current <code>TimeBase</code> time.
     */
    public Time getTime();

    /**
     * Get the current time of the <code>TimeBase</code>
     * specified in nanoseconds.
     *
     * @return the current <code>TimeBase</code> time in
     * nanoseocnds.
     */
    public long getNanoseconds();

}
