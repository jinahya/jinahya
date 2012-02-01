/*
 * @(#)ThreadedEventQueue.java	1.7 98/03/28
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

package com.sun.tv.media.util;

import java.util.Vector;
import javax.media.*;
import com.sun.tv.media.*;

/**
 * This class is a part of the porting layer implementation for JavaTV.
 * A utility class to manage an event queue in a thread.
 * To use it, subclass from it and implement the processEvent() method.
 * @version 1.7, 98/03/28
 */
public abstract class ThreadedEventQueue extends MediaThread {

    private Vector eventQueue = new Vector();
    private boolean killed = false;

    public ThreadedEventQueue() {
	useControlPriority();
    }

    /**
     * Invoked when there is at least one event in the queue.
     * Implement this as a callback to process one event.
     */
    protected abstract void processEvent(ControllerEvent evt);

    /**
     * Wait until there is something in the event queue to process.  Then
     * dispatch the event to the listeners.The entire method does not
     need to be synchronized since this includes taking the event out
     from the queue and processing the event. We only need to provide
     exclusive access over the code where an event is removed from the
     queue. 
     */
    protected void dispatchEvents() {

	ControllerEvent evt = null;
	
	synchronized (this){
	    
	// Wait till there is an event in the event queue.
	try {
	    while (!killed && eventQueue.size() == 0)
		wait();
	} catch (InterruptedException e) {
	    System.err.println("MediaNode event thread " + e);
	    return;
	}
//	if (killed)
//	    stop();
	// Remove the event from the queue and dispatch it to the listeners.
	evt = (ControllerEvent)eventQueue.elementAt(0);
	eventQueue.removeElementAt(0);
	} // end of synchronized

	if (evt != null)
	    processEvent(evt);
    }
    
    /**
     * Queue the given event in the event queue.
     */
    public synchronized void postEvent(ControllerEvent evt) {
	eventQueue.addElement(evt);
	notifyAll();
    }

    /**
     * kill the thread.
     */
    public synchronized void kill() {
	killed = true;
	notifyAll();
    }

    /**
     * An inifinite while loop to dispatch ControllerEvent.
     */
    public void run() {
	while (true) {
	    dispatchEvents();
	}
    }
}


