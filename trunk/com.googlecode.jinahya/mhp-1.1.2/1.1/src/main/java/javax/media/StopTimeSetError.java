/*
 * @(#)StopTimeSetError.java	1.12 98/03/28
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
 * <CODE>StopTimeSetError</CODE> is thrown when the stop time 
 * has been set on a <I>Started</I>&nbsp;<CODE>Clock</CODE> and <code>setStopTime</code> is invoked
 * again.
 * 
 * @version 1.12, 98/03/28.
 */

public class StopTimeSetError extends MediaError {

    public StopTimeSetError(String reason) {
       super(reason);
    }
}
