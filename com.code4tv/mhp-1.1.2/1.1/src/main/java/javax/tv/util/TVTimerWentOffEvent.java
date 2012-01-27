/*
 * @(#)TVTimerWentOffEvent.java	1.2 00/08/06
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
 * An event indicating that a timer specification has gone off.
 *
 */
public class TVTimerWentOffEvent extends java.util.EventObject {
    private TVTimerSpec spec = null;
    
    /**
     * Creates a new TVTimerWentOffEvent with the specified timer and
     * timer specification.
     * @param source the timer that sent this event
     * @param spec the timer specification that went off
     */
    public TVTimerWentOffEvent(TVTimer source, TVTimerSpec spec) {
	super(source);
	this.spec = spec;
    }
            
    /**
     * Returns the timer specification for this event.
     */
    public TVTimerSpec getTimerSpec() {
	return spec;
    }
}
