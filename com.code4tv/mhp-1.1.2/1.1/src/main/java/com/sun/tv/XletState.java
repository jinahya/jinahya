/*
 * @(#)XletState.java	1.3 99/07/08
 *
 * Copyright 2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

package com.sun.tv;

/**
 * This class is for tracking xlet's current state.
 */
public class XletState {
    public static final int LOADED = 0;    
    public static final int PAUSED = 1;
    public static final int ACTIVE = 2;
    public static final int DESTROYED = 3;

    private int state = -1;

    XletState () {
	super();
    }

    /**
     * get current state.
     */
    public int getState() {
	return state;
    }

    /**
     * set new state.
     */
    public void setState(int newState) {
	state = newState;
    }
}
