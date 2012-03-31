/*
 * @(#)Time.java	1.11 98/03/28
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
 * <code>Time</code> abstracts time in the Java Media framework.
 **/
public class Time {

    public static final long ONE_SECOND = 1000000000L;
    static final double NANO_TO_SEC = 1.0E-9;

    /**
     * Time is kept to a granularity of nanoseconds.
     * Converions to and from this value are done
     * to implement construction or query in seconds.
     */
    protected long nanoseconds;

    /**
     * Construct a time in nanoseconds.
     *
     * @param nano Number of nanoseconds for this time.
     */
    public Time(long nano) {
	nanoseconds = nano;
    }

    /**
     * Construct a time in seconds.
     *
     * @param seconds Time specified in seconds.
     */
    public Time(double seconds) {
	nanoseconds = secondsToNanoseconds(seconds);
    }

    /**
     * Convert seconds to nanoseconds.
     */
    protected long secondsToNanoseconds(double seconds) {
	return (long)(seconds * ONE_SECOND);
    }
	
    /**
     * Get the time value in nanoseconds.
     *
     * @return The time in nanoseconds.
     */
    public long getNanoseconds() {
	return nanoseconds;
    }

    /**
     * Get the time value in seconds.
     *
     */
    public double getSeconds() {
	return nanoseconds*NANO_TO_SEC;
    }

}
