/*
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 219: Foundation
Profile 1.1. In the event of a discrepency between this work and the
JSR 219 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
*/


  


package java.util;

import java.util.Date;


/** 
 * A facility for threads to schedule tasks for future execution in a
 * background thread.  Tasks may be scheduled for one-time execution, or for
 * repeated execution at regular intervals.
 *
 * <p>Corresponding to each <tt>Timer</tt> object is a single background
 * thread that is used to execute all of the timer's tasks, sequentially.
 * Timer tasks should complete quickly.  If a timer task takes excessive time
 * to complete, it "hogs" the timer's task execution thread.  This can, in
 * turn, delay the execution of subsequent tasks, which may "bunch up" and
 * execute in rapid succession when (and if) the offending task finally
 * completes.
 *
 * <p>After the last live reference to a <tt>Timer</tt> object goes away
 * <i>and</i> all outstanding tasks have completed execution, the timer's task
 * execution thread terminates gracefully (and becomes subject to garbage
 * collection).  However, this can take arbitrarily long to occur.  By
 * default, the task execution thread does not run as a <i>daemon thread</i>,
 * so it is capable of keeping an application from terminating.  If a caller
 * wants to terminate a timer's task execution thread rapidly, the caller
 * should invoke the the timer's <tt>cancel</tt> method.
 *
 * <p>If the timer's task execution thread terminates unexpectedly, for
 * example, because its <tt>stop</tt> method is invoked, any further
 * attempt to schedule a task on the timer will result in an
 * <tt>IllegalStateException</tt>, as if the timer's <tt>cancel</tt>
 * method had been invoked.
 *
 * <p>This class is thread-safe: multiple threads can share a single
 * <tt>Timer</tt> object without the need for external synchronization.
 *
 * <p>This class does <i>not</i> offer real-time guarantees: it schedules
 * tasks using the <tt>Object.wait(long)</tt> method.
 *
 * <p>Implementation note: This class scales to large numbers of concurrently
 * scheduled tasks (thousands should present no problem).  Internally,
 * it uses a binary heap to represent its task queue, so the cost to schedule
 * a task is O(log n), where n is the number of concurrently scheduled tasks.
 *
 * @author  Josh Bloch
 * @version 1.8, 05/17/00
 * @see     TimerTask
 * @see     Object#wait(long)
 * @since   1.3
 */
public class Timer
{

    /** 
     * Creates a new timer.  The associated thread does <i>not</i> run as
     * a daemon.
     *
     * @see Thread
     * @see #cancel()
     */
    public Timer() { }

    /** 
     * Creates a new timer whose associated thread may be specified to 
     * run as a daemon.  A deamon thread is called for if the timer will
     * be used to schedule repeating "maintenance activities", which must
     * be performed as long as the application is running, but should not
     * prolong the lifetime of the application.
     *
     * @param isDaemon true if the associated thread should run as a daemon.
     *
     * @see Thread
     * @see #cancel()
     */
    public Timer(boolean isDaemon) { }

    /** 
     * Schedules the specified task for execution after the specified delay.
     *
     * @param task  task to be scheduled.
     * @param delay delay in milliseconds before task is to be executed.
     * @throws IllegalArgumentException if <tt>delay</tt> is negative, or
     *         <tt>delay + System.currentTimeMillis()</tt> is negative.
     * @throws IllegalStateException if task was already scheduled or
     *         cancelled, or timer was cancelled.
     */
    public void schedule(TimerTask task, long delay) { }

    /** 
     * Schedules the specified task for execution at the specified time.  If
     * the time is in the past, the task is scheduled for immediate execution.
     *
     * @param task task to be scheduled.
     * @param time time at which task is to be executed.
     * @throws IllegalArgumentException if <tt>time.getTime()</tt> is negative.
     * @throws IllegalStateException if task was already scheduled or
     *         cancelled, timer was cancelled, or timer thread terminated.
     */
    public void schedule(TimerTask task, java.util.Date time) { }

    /** 
     * Schedules the specified task for repeated <i>fixed-delay execution</i>,
     * beginning after the specified delay.  Subsequent executions take place
     * at approximately regular intervals separated by the specified period.
     *
     * <p>In fixed-delay execution, each execution is scheduled relative to
     * the actual execution time of the previous execution.  If an execution
     * is delayed for any reason (such as garbage collection or other
     * background activity), subsequent executions will be delayed as well.
     * In the long run, the frequency of execution will generally be slightly
     * lower than the reciprocal of the specified period (assuming the system
     * clock underlying <tt>Object.wait(long)</tt> is accurate).
     *
     * <p>Fixed-delay execution is appropriate for recurring activities
     * that require "smoothness."  In other words, it is appropriate for
     * activities where it is more important to keep the frequency accurate
     * in the short run than in the long run.  This includes most animation
     * tasks, such as blinking a cursor at regular intervals.  It also includes
     * tasks wherein regular activity is performed in response to human
     * input, such as automatically repeating a character as long as a key
     * is held down.
     *
     * @param task   task to be scheduled.
     * @param delay  delay in milliseconds before task is to be executed.
     * @param period time in milliseconds between successive task executions.
     * @throws IllegalArgumentException if <tt>delay</tt> is negative, or
     *         <tt>delay + System.currentTimeMillis()</tt> is negative.
     * @throws IllegalStateException if task was already scheduled or
     *         cancelled, timer was cancelled, or timer thread terminated.
     */
    public void schedule(TimerTask task, long delay, long period) { }

    /** 
     * Schedules the specified task for repeated <i>fixed-delay execution</i>,
     * beginning at the specified time. Subsequent executions take place at
     * approximately regular intervals, separated by the specified period.
     *
     * <p>In fixed-delay execution, each execution is scheduled relative to
     * the actual execution time of the previous execution.  If an execution
     * is delayed for any reason (such as garbage collection or other
     * background activity), subsequent executions will be delayed as well.
     * In the long run, the frequency of execution will generally be slightly
     * lower than the reciprocal of the specified period (assuming the system
     * clock underlying <tt>Object.wait(long)</tt> is accurate).
     *
     * <p>Fixed-delay execution is appropriate for recurring activities
     * that require "smoothness."  In other words, it is appropriate for
     * activities where it is more important to keep the frequency accurate
     * in the short run than in the long run.  This includes most animation
     * tasks, such as blinking a cursor at regular intervals.  It also includes
     * tasks wherein regular activity is performed in response to human
     * input, such as automatically repeating a character as long as a key
     * is held down.
     *
     * @param task   task to be scheduled.
     * @param firstTime First time at which task is to be executed.
     * @param period time in milliseconds between successive task executions.
     * @throws IllegalArgumentException if <tt>time.getTime()</tt> is negative.
     * @throws IllegalStateException if task was already scheduled or
     *         cancelled, timer was cancelled, or timer thread terminated.
     */
    public void schedule(TimerTask task, java.util.Date firstTime, long period)
    { }

    /** 
     * Schedules the specified task for repeated <i>fixed-rate execution</i>,
     * beginning after the specified delay.  Subsequent executions take place
     * at approximately regular intervals, separated by the specified period.
     *
     * <p>In fixed-rate execution, each execution is scheduled relative to the
     * scheduled execution time of the initial execution.  If an execution is
     * delayed for any reason (such as garbage collection or other background
     * activity), two or more executions will occur in rapid succession to
     * "catch up."  In the long run, the frequency of execution will be
     * exactly the reciprocal of the specified period (assuming the system
     * clock underlying <tt>Object.wait(long)</tt> is accurate).
     *
     * <p>Fixed-rate execution is appropriate for recurring activities that
     * are sensitive to <i>absolute</i> time, such as ringing a chime every
     * hour on the hour, or running scheduled maintenance every day at a
     * particular time.  It is also appropriate for for recurring activities
     * where the total time to perform a fixed number of executions is
     * important, such as a countdown timer that ticks once every second for
     * ten seconds.  Finally, fixed-rate execution is appropriate for
     * scheduling multiple repeating timer tasks that must remain synchronized
     * with respect to one another.
     *
     * @param task   task to be scheduled.
     * @param delay  delay in milliseconds before task is to be executed.
     * @param period time in milliseconds between successive task executions.
     * @throws IllegalArgumentException if <tt>delay</tt> is negative, or
     *         <tt>delay + System.currentTimeMillis()</tt> is negative.
     * @throws IllegalStateException if task was already scheduled or
     *         cancelled, timer was cancelled, or timer thread terminated.
     */
    public void scheduleAtFixedRate(TimerTask task, long delay, long period) { }

    /** 
     * Schedules the specified task for repeated <i>fixed-rate execution</i>,
     * beginning at the specified time. Subsequent executions take place at
     * approximately regular intervals, separated by the specified period.
     *
     * <p>In fixed-rate execution, each execution is scheduled relative to the
     * scheduled execution time of the initial execution.  If an execution is
     * delayed for any reason (such as garbage collection or other background
     * activity), two or more executions will occur in rapid succession to
     * "catch up."  In the long run, the frequency of execution will be
     * exactly the reciprocal of the specified period (assuming the system
     * clock underlying <tt>Object.wait(long)</tt> is accurate).
     *
     * <p>Fixed-rate execution is appropriate for recurring activities that
     * are sensitive to <i>absolute</i> time, such as ringing a chime every
     * hour on the hour, or running scheduled maintenance every day at a
     * particular time.  It is also appropriate for for recurring activities
     * where the total time to perform a fixed number of executions is
     * important, such as a countdown timer that ticks once every second for
     * ten seconds.  Finally, fixed-rate execution is appropriate for
     * scheduling multiple repeating timer tasks that must remain synchronized
     * with respect to one another.
     *
     * @param task   task to be scheduled.
     * @param firstTime First time at which task is to be executed.
     * @param period time in milliseconds between successive task executions.
     * @throws IllegalArgumentException if <tt>time.getTime()</tt> is negative.
     * @throws IllegalStateException if task was already scheduled or
     *         cancelled, timer was cancelled, or timer thread terminated.
     */
    public void scheduleAtFixedRate(TimerTask task, java.util.Date firstTime,
        long period)
    { }

    /** 
     * Terminates this timer, discarding any currently scheduled tasks.
     * Does not interfere with a currently executing task (if it exists).
     * Once a timer has been terminated, its execution thread terminates
     * gracefully, and no more tasks may be scheduled on it.
     *
     * <p>Note that calling this method from within the run method of a
     * timer task that was invoked by this timer absolutely guarantees that
     * the ongoing task execution is the last task execution that will ever
     * be performed by this timer.
     *
     * <p>This method may be called repeatedly; the second and subsequent 
     * calls have no effect.
     */
    public void cancel() { }
}
