/*
 * @(#)Duration.java	1.18 98/03/28
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
 * The <code>Duration</code> interface provides a way to determine the
 * duration of the media being played by a media object.
 * Media objects that expose a media duration
 * implement this interface. 
 * <p>
 *
 * A <code>Controller</code> that supports the <code>Duration</code> interface
 * posts a <code>DurationUpdateEvent</code> whenever its
 * duration changes.
 * 
 * @see Controller
 * @see DurationUpdateEvent
 *
 * @version 1.18, 98/03/28
 */
public interface Duration {

    /**
     * Returned by <code>getDuration</code>.
     */
    public final static Time DURATION_UNBOUNDED = new Time(Long.MAX_VALUE);
    /**
     * Returned by <code>getDuration</code>.
     */
    public final static Time DURATION_UNKNOWN = new Time(Long.MAX_VALUE - 1);
    
    /**
     * Get the duration of the media represented
     * by this object.
     * The value returned is the media's duration
     * when played at the default rate.
     * If the duration can't be determined  (for example, the media object is presenting live
     * video)  <CODE>getDuration</CODE> returns <CODE>DURATION_UNKNOWN</CODE>.
     *
     * @return A <CODE>Time</CODE> object representing the duration or DURATION_UNKNOWN.
     */
    public Time getDuration();
}
