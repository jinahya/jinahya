/*
 * @(#)IncompatibleTimeBaseException.java	1.11 98/03/28
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
 * An <CODE>IncompatibleTimeBaseException</CODE> is generated 
 * when <CODE>Clock.setTimeBase</CODE> is invoked
 * using a <CODE>TimeBase</CODE> that the <CODE>Clock</CODE> cannot support.
 * This happens for certain types of <CODE>Players</CODE> that
 * can only be driven by their own internal clocks, such as
 * certain commercial video servers.
 * <p>
 *
 * <B>Note:</B> A <CODE>Player</CODE> might throw this exception when 
 * <CODE>addController</CODE> is called
 * because of the implied <CODE>setTimeBase</CODE> in <CODE>addController</CODE>.
 *
 * @see Clock
 * @see Player
 * @version 1.11, 98/03/28.
 *
 */

public class IncompatibleTimeBaseException extends MediaException {

    public IncompatibleTimeBaseException() {
	super();
    }
    
    public IncompatibleTimeBaseException(String reason) {
	super(reason);
    }
}
