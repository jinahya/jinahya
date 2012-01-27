/*
 * @(#)TVTimerWentOffListener.java	1.2 00/08/06
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
 * A listener interested in timer specifications going off.
 *
 */
public interface TVTimerWentOffListener {

    /**
     * Notifies the listener that a timer specification went off.
     * @param e The event specifying which timer and which timer specification
     * went off.
     */
    void timerWentOff(TVTimerWentOffEvent e);
}
