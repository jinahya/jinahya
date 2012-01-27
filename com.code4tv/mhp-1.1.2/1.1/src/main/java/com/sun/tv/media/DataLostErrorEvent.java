/*
 * @(#)DataLostErrorEvent.java	1.1 98/04/07
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
 * A <CODE>DataLostErrorEvent</CODE> is posted when a <code>Controller</code> 
 * has lost data.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.1, 98/04/07
 */
public class DataLostErrorEvent extends ControllerClosedEvent {
    public DataLostErrorEvent(Controller from) {
        super(from);
    }

    public DataLostErrorEvent(Controller from, String why) {
        super(from, why);
    }
}
