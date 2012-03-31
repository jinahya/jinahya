/*
 * @(#)MCI.java	1.2 99/08/14
 *
 * Copyright 1996-1999 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.media.amovie;


public class MCI {
    
    private String errString;
    private String retString;
    private int hwndCallback = 0;

    public native int getDeviceId(String name);

    public native String getErrorString(int errId);

    public native boolean sendString(String command);

    static {
	com.sun.tv.media.util.JMFSecurity.loadLibrary("jmmci");
    }

    public void ssendString(String command) {
	boolean ret = sendString(command);
	//System.out.println(errString);
	//System.out.println(retString);
	if (!ret)
	    throw new Error(errString);
    }

    public String getErrorMessage() {
	return errString;
    }

    public String getReturnString() {
	return retString;
    }
}
