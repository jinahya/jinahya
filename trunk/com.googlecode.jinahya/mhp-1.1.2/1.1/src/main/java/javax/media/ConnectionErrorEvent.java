/*
 * @(#)ConnectionErrorEvent.java	1.7 98/03/28
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
 * A <CODE>ConnectionErrorEvent</CODE> is posted when an error occurs within a
 <CODE>DataSource</CODE>
 * when obtaining data or communicating with a server.
 **/

public class ConnectionErrorEvent extends ControllerErrorEvent {


    public ConnectionErrorEvent(Controller from) {
        super(from);
    }

    public ConnectionErrorEvent(Controller from, String why) {
        super(from, why);
    }

}
