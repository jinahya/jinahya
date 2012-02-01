/*
 * @(#)ControlChangeListener.java	1.4 98/03/28
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


package com.sun.tv.media.controls;

import com.sun.tv.media.*;

/**
 * This interface is a part of the porting layer implementation for JavaTV.
 * Listener for changes in the state of a Control.
 */
public interface ControlChangeListener {

    /**
     * Gets called whenever the state of a Control changes.
     */
    public void controlChanged(ControlChangeEvent e);

}


