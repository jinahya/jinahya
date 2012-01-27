/*
 * @(#)TVTimerSpec.java	1.2 00/08/06
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

import java.util.*;
//import com.sun.util.*;

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
 */
public class TVTimerSpec {

    private TVTimerSpec ptimer = null;
    private Vector listeners = null;
 
    /**
     * Creates a timer specification.  It initially is absolute,
     * non-repeating, regular specification set to go off at time 0.
     */
    public TVTimerSpec() {
	ptimer = new TVTimerSpec();
	listeners = new Vector();
    }
    
    /**
     * Sets this specification to be absolute or delayed.
     */
    public void setAbsolute(boolean absolute) {
	ptimer.setAbsolute(absolute);
    }

    /**
     * Checks if this specification is absolute.
     */
    public boolean isAbsolute() {
	return ptimer.isAbsolute();
    }
    
    /**
     * Sets this specification to be repeating or non-repeating.
     */
    public void setRepeat(boolean repeat) {
	ptimer.setRepeat(repeat);
    }
    
    /**
     * Checks if this specification is repeating.
     */
    public boolean isRepeat() {
	return ptimer.isRepeat();
    }
    
    /**
     * Sets this specification to be regular or non-regular.
     */
    public void setRegular(boolean regular) {
	ptimer.setRegular(regular);
    }
    
    /**
     * Checks if this specification is regular.
     */
    public boolean isRegular() {
	return ptimer.isRegular();
    }
    
    /**
     * Sets when this specification should go off.  For absolute
     * specifications, this is a time in milliseconds since midnight,
     * January 1, 1970 UTC.  For delayed specifications, this is a
     * delay time in milliseconds.
     * If the time parameter passed is negative, this method shall throw an IllegalArgumentException.
     *
     * @param time The time when this specification should go off.
     */
    public void setTime(long time) {
	ptimer.setTime(time);
    }
    
    /**
     * Returns the absolute or delay time when this specification
     * will go off.
     */
    public long getTime() {
	return ptimer.getTime();
    }
    
    // listeners
    
    /**
     * Registers a listener with this timer specification.
     * @param l The listener to add.
     */
    public void addTVTimerWentOffListener(TVTimerWentOffListener l) {
	if (l == null) {
	    throw new NullPointerException();
	}
	listeners.addElement(l);
    }
    
    /**
     * Removes a listener to this timer specification.  Silently does nothing
     * if the listener was not listening on this specification.

     * @param l The listener to remove.
     */
    public void removeTVTimerWentOffListener(TVTimerWentOffListener l) {
	if (l == null) {
	    throw new NullPointerException();
	}
	listeners.removeElement(l);
    }
        
    // convenience functions
    
    /**
     * Sets this specification to go off at the given absolute time.
     * This is a convenience function equivalent to
     * <code>setAbsolute(true)</code>, <code>setTime(when)</code>,
     * <code>setRepeat(false)</code>.
     * If the time parameter passed is negative, this method shall throw an IllegalArgumentException.
     *
     * @param when The absolute time for the specification to go off.
     */
    public void setAbsoluteTime(long when) {
	ptimer.setAbsoluteTime(when);
    }
        
    /**
     * Sets this specification to go off after the given delay time.
     * This is a convenience function equivalent to
     * <code>setAbsolute(false)</code>, <code>setTime(delay)</code>,
     * <code>setRepeat(false)</code>.
     * 
     * @param delay The relative time for the specification to go off.  */
    public void setDelayTime(long delay) {
	ptimer.setDelayTime(delay);
    }
    
    // for the benefit of timer implementations
    
    /**
     * Calls all listeners registered on this timer specification.
     * This function is primarily for the benefit of those writing
     * implementations of TVTimers.
     *
     * @param source The TVTimer that decided that this specification
     * should go off.  */
    public void notifyListeners(TVTimer source) {
	Enumeration enum = listeners.elements();

	if(source == null) {
	    throw new NullPointerException();
	}

	while(enum.hasMoreElements()){
	    TVTimerWentOffListener l = (TVTimerWentOffListener)enum.nextElement();
	    TVTimerWentOffEvent evt = new TVTimerWentOffEvent(source, this);
	    l.timerWentOff(evt);
	}
    }
}
