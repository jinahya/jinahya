/*
 * @(#)Positionable.java	1.8 98/03/28
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

package javax.media.protocol;

import javax.media.Time;

/**
 * A <code>DataSource</code> implements the <CODE>Positionable</CODE> interface
 * if it supports changing the media position within the stream.
 * 
 * @see Datasource
 * @version 1.8, 98/03/28.
 */
public interface Positionable {

    public static final int RoundUp = 1;
    public static final int RoundDown = 2;
    public static final int RoundNearest = 3;
    
    /**
     * Set the position to the specified time.
     * Returns the rounded position that was actually set.
     *
     * @param time The new position in the stream.
     * @param round The rounding technique to be used: RoundUp, RoundDown, RoundNearest.
     * @return The actual position set.
     */
    Time setPosition(Time where, int rounding);
    
    /**
     * Find out if this source can be repositioned to any point in the stream.
     * If not, the source can only be repositioned to the beginning of the stream.
     *
     * @return Returns <CODE>true</CODE> if the source is random access; <CODE>false</CODE> if the source can only
     * be reset to the beginning of the stream.
     */
    boolean isRandomAccess();

}
