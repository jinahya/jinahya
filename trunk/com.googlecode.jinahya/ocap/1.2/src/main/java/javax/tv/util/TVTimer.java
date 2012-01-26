/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
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
 * @author: Alan Bishop
 */
public abstract class TVTimer
{

    /** 
     * Constructs a TVTimer object.
     */
    public TVTimer() { }

    /** 
     * Returns the default timer for the system.  There may be one
     * TVTimer instance per virtual machine, one per applet, one per
     * call to <code>getTimer()</code>, or some other platform dependent
     * implementation. 
     *
     * @return A non-null TVTimer object.  
     */
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
     * granularity and repeat interval.
     * <p>
     * If you schedule an absolute
     * specification that should have gone off already, it will
     * be scheduled to go off immediately, and the return value of this
     * method will be an absolute specification reflecting the current
     * time. The actual listener notification may happen asynchronously.
     * <p>
     * If the scheduled specification cannot be
     * satisfied, the exception {@link TVTimerScheduleFailedException}
     * should be thrown. </p>
     * 
     * <p>You may schedule a timer specification with multiple timers.
     * You may schedule a timer specification with the same timer
     * multiple times (in which case it will go off multiple times).  If
     * you modify a timer specification after it has been scheduled
     * with any timer, the results are unspecified.
     * <p>
     * Note: The specified <code>TimerSpec</code> object may
     * be modified by this method, e.g., a delayed <code>TimerSpec</code>
     * may be transformed into an absolute <code>TimerSpec</code>.
     *
     * @param t The timer specification to begin monitoring.  
     * @return The real TVTimerSpec that was scheduled.
     * @exception TVTimerScheduleFailedException is thrown when the scheduled 
     *            specification cannot be satisfied.  
     */
    public abstract TVTimerSpec scheduleTimerSpec(TVTimerSpec t)
        throws TVTimerScheduleFailedException;

    /** 
     * Removes a timer specification from the set of monitored
     * specifications.  The descheduling happens as soon as practical,
     * but may not happen immediately.  If the timer specification has
     * been scheduled multiple times with this timer, all the
     * schedulings are canceled.  No other instances of timer
     * specifications shall be descheduled.
     * <p>
     * The specified <code>TVTimerSpec</code> instance <code>t</code> must
     * be an instance previously passed into the method
     * {@link scheduleTimerSpec} on the same instance of <code>TVTimer</code>.
     * If it is not, no action is performed.
     *
     * @param t The timer specification to end monitoring.
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
     * interval limitation.  
     */
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
