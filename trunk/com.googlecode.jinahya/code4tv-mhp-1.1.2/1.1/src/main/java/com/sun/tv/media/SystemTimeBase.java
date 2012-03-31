/*
 * @(#)SystemTimeBase.java	1.11 98/03/28
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

package com.sun.tv.media;

import javax.media.*;

/**
 * SystemTimeBase is the implementation of the default <CODE>TimeBase</CODE> that ships
 * with JavaMedia.
 *
 * @see TimeBasemake doc.
 *
 * @version 1.11, 98/03/28.
 *
*/ 
final public class SystemTimeBase implements TimeBase {

    // Pick some offset (start-up time) so the system time won't be
    // so huge.  The huge numbers overflow floating point operations
    // in some cases.
    static long offset = System.currentTimeMillis() * 1000000L;
  
    public Time getTime() {
	return new Time(getNanoseconds());
    }

    public long getNanoseconds() {
	return (System.currentTimeMillis() * 1000000L) - offset;
    }
}
