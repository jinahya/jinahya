/*
 * @(#)TVTimer.java	1.2 00/08/06
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
 * A class representing a timer.
 *
 * A timer is responsible for managing a set of timer events specified
 * by timer specifications.  When the timer event should be sent,
 * the timer calls the timer specification's
 * <code>notifyListeners()</code> method.
 *
 * @see TVTimerSpec
 *
 */
public abstract class TVTimer {

    /**
     * Returns the default timer for the system.  There may be one
     * TVTimer instance per virtual machine, one per applet, one per
     * call to <code>getTimer()</code>, or some other platform dependent
     * implementation. 
     *
     * @return A non-null TVTimer object.  */
    public static TVTimer getTimer() {
	return null;
    }

    /**
     * Begins monitoring a TVTimerSpec.
     * 
     * <p> When the timer specification should go off, the timer will
     * call <code>TVTimerSpec.notifyListeners().</code> </p>
     * 
     * <p> Returns the actual <code>TVTimerSpec</code> that got
     * scheduled. If you schedule a specification that implies a
     * smaller granularity than this timer can provide, or a repeat
     * timer specification that has a smaller repeating interval than
     * this timer can provide, the timer should round to the closest
     * value and return that value as a {@link TVTimerSpec} object. An
     * interested application can use accessor methods {@link
     * #getMinRepeatInterval} and {@link #getGranularity} to obtain
     * the Timer's best knowledge of the Timer's limitation on
     * granularity and repeat interval. If you schedule an absolute
     * specification that should have gone off already, it will go off
     * immediately. If the scheduled specification cannot be
     * satisfied, the exception {@link TVTimerScheduleFailedException}
     * should be thrown. </p>
     * 
     * <p>You may schedule a timer specification with multiple timers.
     * You may schedule a timer specification with the same timer
     * multiple times (in which case it will go off multiple times).  If
     * you modify a timer specification after it has been scheduled
     * with any timer, the results are unspecified.</p>
     *
     * @param t The timer specification to begin monitoring.  
     * @return The real TVTimerSpec that was scheduled.
     * @exception TVTimerScheduleFailedException is thrown when the scheduled 
     *            specification cannot be satisfied.  */
    public abstract TVTimerSpec scheduleTimerSpec(TVTimerSpec t) throws TVTimerScheduleFailedException;


    /**
     * Removes a timer specification from the set of monitored specifications.
     * The descheduling happens as soon as practical, but may not happen immediately.
     * If the timer specification has been scheduled multiple times with this
     * timer, all the schedulings are canceled.
     * @ param t The timer specification to end monitoring.
     */
    public abstract void deschedule(TVTimerSpec t);

    /**
     * Report the minimum interval that this timer can repeat tasks.
     * For example, it's perfectly reasonable for a Timer to specify
     * that the minimum interval for a repeatedly performed task is
     * 1000 milliseconds between every run. This is to avoid
     * possible system overloading.
     *
     * @return The timer's best knowledge of minimum repeat interval
     * in milliseconds. Return -1 if this timer doesn't know its repeating
     * interval limitation.  */
    public abstract long getMinRepeatInterval();

    /**
     * Report the granularity of this timer, i.e., the length of time between
     * "ticks" of this timer. 
     *
     * @return The timer's best knowledge of the granularity in
     * milliseconds. Return -1 if this timer doesn't know its granularity. 
     *
     */
    public abstract long getGranularity();

}
