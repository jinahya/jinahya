/*
 * @(#)LoopThread.java	1.9 98/09/28
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

/**
 * LoopThread
 * A looping thread that implements a safe way of pausing and restarting.
 * Instead of using suspend() and resume() from the thread class, a pause() 
 * and restart() method is provided.
 * To use it, you will:
 *   - subclass from it;
 *   - overwrite the process() callback;
 *   - call start() to initiate the thread.
 * @version 1.9, 98/09/28
 */
public abstract class LoopThread extends MediaThread {

    protected boolean paused = false, restarted = false;
    protected boolean killed = false;
    private boolean doneLastLoop = true;

    /**
     * The pause flag determines the initial state.
     */
    public LoopThread() {
	setName("Loop thread");
    }

    /**
     * Set the paused state to true.  The thread will halt at the
     * beginning of the loop at the next iteration.  i.e., unlike
     * suspend(), the thread will NOT pause immediately.  It will execute
     * until the next waitHereIfPaused() is encountered.
     */
    public synchronized void pause() {
	paused = true;
    }

    public synchronized boolean isPaused() {
	return paused;
    }

    public synchronized void blockingPause() {
	if (paused) return;
	paused = true;
	doneLastLoop = false;
	try {
	    if (paused && !doneLastLoop)
		wait(250);
	} catch (InterruptedException e) {
	}
    }

    /**
     * Resume the loop at the beginning of the loop where
     * waitHereIfPaused() is called.
     */
    public synchronized void restart() {
	paused = false;
	restarted = true;
	notifyAll();
    }

    public synchronized void kill() {
	killed = true;
	notifyAll();
    }

    /**
     * Put the thread in a wait() if the paused state is on.  Otherwise, it
     * will just proceed.
     */
    public synchronized void waitHereIfPaused() {
	if (paused) {
	    doneLastLoop = true;
	    notifyAll();	// notify the blocking pause.
	}
	try {
	    while (!killed && paused)
		wait();
	} catch (InterruptedException e) {
	    System.err.println("Timer: timeLoop() wait interrupted " + e);
	}
//	if (killed)
//	    stop();
    }

    /**
     * process callback function.
     */
    public abstract boolean process();

    /**
     * Callback before the thread goes into the pause state.
     * Be careful not to block in this thread.
     */
    public void doPause() {
    }

    /**
     * Callback before the thread is killed.
     * Be careful not to block in this thread.
     */
    public void doKilled() {
    }

    /**
     * The run method for the Thread class.
     */
    public void run() {
	for (;;) {

	    // Wait here if pause() was invoked.  Until restart() is called,
	    // the thread will wait here indefinitely.
	    waitHereIfPaused();

	    // Invoke the callback function.
	    process();

	    if (paused)
		doPause();

	    if (killed)
		doKilled();
	}
    }

}
