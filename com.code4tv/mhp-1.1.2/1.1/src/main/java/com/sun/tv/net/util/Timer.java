/*
 * @(#)Timer.java	1.22 97/03/06 SMI
 *
 * Copyright (c) 1996 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

//
// Timer.java
//
// This class provides a general purpose event timer.
//
// How to use:
//
// 1. Subclass Timer and implement your own callback(long) method.
// 2. Instantiate your subclass:
//        FooTimer foo = new FooTimer();
// 3. Start the timer to go off n msec in the future:
//        foo.start(n);
//    Your callback() method will be called with this interval expires.
// 4. You can call stop() to cancel the timer:
//        foo.stop();
// 5. Calling start(long) on a timer in progress will cancel the current
//    timer and start a new one with the given delay.
//
// sritchie -- Oct 95
//
// notes
//
// We implement this using a sorted priority list.  Sort key is the
// system absolute time that the timer callback should be invoked.
//
// The priority list is currently a simple double-linked list.  Starts
// are O(n) linear search, expiries are O(1), stops are O(1).  Might want
// to use a heap if the number of timers gets large.
//


package com.sun.tv.net.util;



class TimerThread extends SystemThread {

    TimerThread() {
	super("JavaOS Timer Thread",TimerThreadPriority);
    }

    public void run() {
	for (;;) {
	    try {
		Timer.doit();
	    } catch (OutOfMemoryError e) {
		// don't printStackTrace() for OutOfMemoryError -- might
		// not have enough memory to do that.
	    } catch (Throwable t) {
		// don't let this important system thread exit.
		try {
		    t.printStackTrace();
		} catch (OutOfMemoryError e) {
		}
	    }
	}
    }
}


public abstract class Timer {

    public Timer() {
    }
    
    // Users of the Timer class must implement this method to
    // receive their timer expiry callbacks.
    // delta is the number of milliseconds since the timer was started.
    public abstract void callback(long delta);

    
    //----------------------------------------------------------------------

    public boolean isStarted() {
	
	// we maintain the expiry field such that if it is zero,
	// the timer is guaranteed to be not in use.
	return (expiry != 0);
    }

    private void remove() {

	// remove this timer from the queue.
	//dprint("stop expiry: " + expiry);

	expiry = 0;

	if (this == timerHead) {
	    timerHead.prev = null;
	    timerHead = next;
	} else {
	    prev.next = next;
	}

	if (next != null) {
	    next.prev = prev;
	}

    }

    // stop this timer event
    public void stop() {

	synchronized (timerThread) {
	    if (isStarted() == true) {
		remove();
	    }
	}
    }

    //----------------------------------------------------------------------
    
    public void start(long sleepTime) {

	synchronized (timerThread) {
	    if (isStarted() == true) {
		remove();
	    }

	    startedAt = System.currentTimeMillis();
	    expiry = startedAt + sleepTime;	

	    //dprint("start expiry: " + expiry);

	    Timer timer;
	
	    if (timerHead != null) {

		// search for the insertion point.
		timer = timerHead;
		while (timer.expiry <= expiry) {
    
		    if (timer.next == null) {
			//dprint("insert at tail");
			timer.next = this;
			prev = timer;
			next = null;
			return;
		    }
		    timer = timer.next;
		}
	
		// insert us before current timer
		next = timer;
		if (timer.prev == null) {

		    prev = null;
		    timerHead = this;
		
		    // notify TimerThread that something has changed at the
		    // beginning of the list.
		    //dprint("inserted before head, signalling timerThread");

		    timerThread.notify();

		} else {
		    prev = timer.prev;
		    timer.prev.next = this;
		}
	    
		timer.prev = this;

	    } else {
		next = null;
		prev = null;
		timerHead = this;
		
		// notify TimerThread that something has changed at the
		// beginning of the list.
		//dprint("insert on empty, signalling timerThread");

		timerThread.notify();
	    }

	    return;
	}
    }
    
    //----------------------------------------------------------------------
    
    static void doit() throws InterruptedException {

	Timer timer = null;
	long delta = 0;

	synchronized (timerThread) {

	    long time = System.currentTimeMillis();

	    // Loop until the timer event at the head of the queue expires.
	    while (timerHead == null || timerHead.expiry > time) {
		    
		long timeout;
		if (timerHead != null) {
		    timeout = timerHead.expiry - time;
		} else {
		    timeout = 0;
		}

		// wait for a timer event to be posted
		timerThread.wait(timeout);

		time = System.currentTimeMillis();
	    }

	    // If we get here then an expired event is sitting at the
	    // head of the queue.
	    timer = timerHead;

	    // remove the first timer element
	    timerHead = timerHead.next;
	    if (timerHead != null)
		timerHead.prev = null;

	    //dprint("Expiring at " + time + " for " + timer.owner +
	    //      " started at " + timer.startedAt + " expiry:" +
	    //      timer.expiry);

	    // this means this timer is dequeued.
	    timer.expiry = 0;

	    // compute time between timer start and expiry time.
	    delta = time - timer.startedAt;
	}

	// Now that we have released the monitor, callback to the
	// user's event handler telling it how long it waited.
	timer.callback(delta);
    }

    // This method is called by sun.javaos.Clock.setTime() to update the
    // Timer event queue's notion of what the current time is.
    // eg, in the boot process when we get the current time from the
    // network and reset it.
    // delta is the time change in msec.
    static void resyncTime(long delta) {

	synchronized (timerThread) {
	    Timer timer = timerHead;

	    while (timer != null) {
		// adjust the expiry time by the given delta.
		timer.expiry += delta;
		timer.startedAt += delta;

		timer = timer.next;
	    }
	}
    }


    //----------------------------------------------------------------------
    
    private static boolean debug = false;

    private static void dprint(String mess) {
        if (debug) {
            System.err.println("Timer: " + mess);
        }
    }

    //----------------------------------------------------------------------
    
    private static Timer timerHead;
    private static TimerThread timerThread;
        
    private Timer next;
    private Timer prev;
    
    private long expiry;
    private long startedAt;
    
    static {
	timerThread = new TimerThread();
	timerThread.start();
    }
}

/*
 * This code is used to test the Timer implementation.
 *
class FooTimer extends Timer {

    protected void callback(long delta) {
	System.out.println("FooTimer: expired "+delta+" msec.");
	TimerTest.numCallbacks--;
    }
}


class TimerTest {

    public static int numCallbacks;

    public static void main(String argv[]) {

        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {

            if (i != 0) {
                System.out.println("*** starting again");
            }

            for (numCallbacks = 0; numCallbacks < 5; numCallbacks++) {
		String s = "Gimple " + numCallbacks;
                Timer t = new FooTimer(s);

                int time = rand.nextInt() % 5000;
                if (time < 0) {
		    time = -time;
                }

	        System.out.println("Starting "+s+" " + time + " msec.");
		t.start(time);

                if (rand.nextInt() < 0) {
                    numCallbacks--;
		    System.out.println("Stopping " + s);
                    t.stop();
                }
            }

            while (numCallbacks > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException intr) {
                    // do nothing
                }
            }

        }
    }
}*/


