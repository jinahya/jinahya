/*
 * @(#)XletStateChangeException.java	1.9 00/10/09
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

package javax.tv.xlet;

/**
 * Signals that a requested Xlet state change failed. This
 * exception is thrown in response to state change calls
 * in the <code>Xlet</code> interface.
 *
 * @see Xlet
 */

public class XletStateChangeException extends Exception {

    /**
     * Constructs an exception with no specified detail message.
     */

    public XletStateChangeException(){  super(); }

    /**
     * Constructs an exception with the specified detail message.
     *
     * @param s the detail message
     */

    public XletStateChangeException(String s){ super((String)null); }

}
