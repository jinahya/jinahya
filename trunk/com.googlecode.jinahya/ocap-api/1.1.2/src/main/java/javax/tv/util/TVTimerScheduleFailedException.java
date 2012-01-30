/*
 * @(#)TVTimerScheduleFailedException.java	1.2 00/08/06
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.tv.util;

/**
 * An exception thrown by the <code>TVTimer.schedule()</code> method
 * when a timer specification cannot be met.  */
public class TVTimerScheduleFailedException extends java.lang.Exception {

    /**
     * Constructs a TVTimerScheduleFailedException with null as its error 
     * detail message.
     */
    public TVTimerScheduleFailedException() {
	super();
    }

    /**
     * Constructs a TVTimerScheduleFailedException with the specified
     * detail message.  The error message string <code>s</code> can later be
     * retrieved by <code>java.lang.Throwable.getMessage()</code> method.
     *
     * @param s The detail message.  */
    public TVTimerScheduleFailedException(String s){
	super((String)null);
    }
}
