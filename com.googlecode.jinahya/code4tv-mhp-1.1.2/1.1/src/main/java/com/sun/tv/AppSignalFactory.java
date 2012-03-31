/*
 * @(#)AppSignalFactory.java	1.2 99/07/08
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
 */


package com.sun.tv;
import java.util.*;

/**
 * This class is used by applications to add themselves as a listener
 * to application signalling events. It should be implemented such
 * that it communicates with the underlying native code that scans 
 * the broadcast stream for application signalling events. 
 */
public class AppSignalFactory {

    /**
     * Add an AppSignalEventListener. All <i>listeners</i> who have
     * added themselves as listeners will be notified when new 
     * application signalling events are broadcast. 
     * 
     * @param listener An object that implements the AppSignalEventListener
     * interface.
     */
    public static void addAppSignalEventListener(
		 AppSignalEventListener listener){
	
	// create internal structures if not already created
	// set up native code to talk listen for signals
	

    }

    /**
     * Remove an AppSignalEventListener. This method will remove the
     * object from the list of event listeners.
     */
    public static void removeAppSignalListener(
	    AppSignalEventListener listener){
    }
}
