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

import java.util.*;

/** 
 * A class representing a timer specification.  A timer specification
 * declares when a <code>TVTimerWentOffEvent</code> should be sent.
 * These events are sent to the listeners registered on the
 * specification.</p>
 *
 * <p>A <code>TVTimerSpec</code> may be <b>absolute</b> or <b>delayed</b>.
 * Absolute specifications go off at the specified time.  Delayed
 * specifications go off after waiting the specified amount of time.</p>
 *
 * <p>Delayed specifications may be repeating or non-repeating.
 * Repeating specifications automatically reschedule themselves after
 * going off.</p>
 *
 * <p>Repeating specifications may be regular or non-regular.  Regular
 * specifications attempt to go off at fixed intervals of time,
 * irrespective of system load or how long it takes to notify the
 * listeners.  Non-regular specifications wait the specified amount of
 * time after all listeners have been called before going off again.</p>
 *
 * <p>For example, you could create a repeating specification that
 * goes off every 100 milliseconds.  Furthermore, imagine that it
 * takes 5 milliseconds to notify the listeners every time it goes
 * off.  If the specification is regular, the listeners will be
 * notified after 100 milliseconds, 200 milliseconds, 300
 * milliseconds, and so on.  If the specification is non-regular, the
 * listeners will be notified after 100 milliseconds, 205
 * milliseconds, 310 milliseconds, and so on.</p>
 *
 * @author: Alan Bishop 
 */
public class TVTimerSpec
{

    /** 
     * Creates a timer specification.  It initially is absolute,
     * non-repeating, regular specification set to go off at time 0.
     */
    public TVTimerSpec() { }

    /** 
     * Sets this specification to be absolute or delayed.
     * 
     * @param absolute Flag to indicate that this specification is
     * either absolute or delayed.  If <code>true</code>, the
     * specification is absolute; otherwise, it is delayed.
     */
    public void setAbsolute(boolean absolute) { }

    /** 
     * Checks if this specification is absolute.
     *
     * @return <code>true</code> if this specification is absolute;
     * <code>false</code> if it is delayed.
     */
    public boolean isAbsolute() {
        return false;
    }

    /** 
     * Sets this specification to be repeating or non-repeating.
     *
     * @param repeat Flag to indicate that this specification is
     * either repeating or non-repeating.  If <code>true</code>, the
     * specification is repeating; otherwise, it is non-repeating.
     */
    public void setRepeat(boolean repeat) { }

    /** 
     * Checks if this specification is repeating.
     *
     * @return <code>true</code> if this specification is repeating;
     * <code>false</code> if it is non-repeating.
     */
    public boolean isRepeat() {
        return false;
    }

    /** 
     * Sets this specification to be regular or non-regular.
     *
     * @param regular Flag to indicate that this specification is
     * either regular or non-regular.  If <code>true</code>, the
     * specification is regular; otherwise, it is non-regular.
     */
    public void setRegular(boolean regular) { }

    /** 
     * Checks if this specification is regular.
     *
     * @return <code>true</code> if this specification is regular;
     * <code>false</code> if it is non-regular.
     */
    public boolean isRegular() {
        return false;
    }

    /** 
     * Sets when this specification should go off.  For absolute
     * specifications, this is a time in milliseconds since midnight,
     * January 1, 1970 UTC.  For delayed specifications, this is a
     * delay time in milliseconds.
     *
     * @param time The time when this specification should go off.
     * @throws IllegalArgumentException If the specified time value is
     * negative.
     */
    public void setTime(long time) { }

    /** 
     * Returns the absolute or delay time when this specification
     * will go off.
     *
     * @return The time when this specification will go off.
     */
    public long getTime() {
        return -1;
    }

    /** 
     * Registers a listener with this timer specification.
     * @param l The listener to add.
     */
    public void addTVTimerWentOffListener(TVTimerWentOffListener l) { }

    /** 
     * Removes a listener to this timer specification.  Silently does nothing
     * if the listener was not listening on this specification.
     *
     * @param l The listener to remove.
     */
    public void removeTVTimerWentOffListener(TVTimerWentOffListener l) { }

    /** 
     * Sets this specification to go off at the given absolute time.
     * This is a convenience function equivalent to
     * <code>setAbsolute(true)</code>, <code>setTime(when)</code>,
     * <code>setRepeat(false)</code>.
     *
     * @param when The absolute time for the specification to go off.
     * @throws IllegalArgumentException If the specified time value is
     * negative.
     */
    public void setAbsoluteTime(long when) { }

    /** 
     * Sets this specification to go off after the given delay time.
     * This is a convenience function equivalent to
     * <code>setAbsolute(false)</code>, <code>setTime(delay)</code>,
     * <code>setRepeat(false)</code>.
     * 
     * @param delay The relative time for the specification to go off.
     * @throws IllegalArgumentException If the specified time value is
     * negative.
     */
    public void setDelayTime(long delay) { }

    /** 
     * Calls all listeners registered on this timer specification.
     * When this method returns, all listeners will have been notified.
     * <p>
     * This function is primarily for the benefit of those writing
     * implementations of TVTimers.
     *
     * @param source The TVTimer that decided that this specification
     * should go off.  
     */
    public void notifyListeners(TVTimer source) { }
}
