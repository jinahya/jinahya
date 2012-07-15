package org.dvb.application;

import java.util.*;

/**
 * The <code>AppStateChangeEvent</code> class indicates a state transition of
 * the application. 
 * These events are only generated for running applications or for non-running applications 
 * where an attempt to control the application fails.
 * If the state transition was requested by an application
 * through this API, the method <code>hasFailed</code> indicates whether 
 * the state change failed or not. Where a state change succeeds, <code>fromState</code>
 * and <code>toState</code> shall indicate the original and destination state of the
 * transition.
 * If it failed, <code>fromState</code> shall return
 * the state the application was in before the state transition was requested and
 * the <code>toState</code> method shall return the state the application would have
 * been in if the state transition had succeeded.<p>
 * Attempting to start an application which is already in the active state shall fail 
 * and generate an <code>AppStateChangeEvent</code> with <code>hasFailed</code>
 * returning true and both fromstate and tostate being <code>STARTED</code>.
 *
 * @since   MHP1.0
 */
public class AppStateChangeEvent  extends EventObject {
    
    /**
     * Create an AppStateChangeEvent object.
     *
     * @param appid a registry entry representing the tracked application
     * @param fromstate the state the application was in before the state transition was requested, where the value of fromState is one of the state values defined in
     * the AppProxy interface or in the interfaces inheriting from it
     * @param tostate state the application would be in if the state transition succeeds, where the value of toState is one of the state values defined in
     * the AppProxy interface or in the interfaces inheriting from it
     * @param hasFailed an indication of whether the transition failed (true) or succeeded (false)
     * @param source the <code>AppProxy</code> where the state transition happened
     */
    public AppStateChangeEvent(AppID appid, int fromstate, int tostate, Object source, boolean hasFailed) {
	super (source) ;
    }

    /**
     * The application the listener was tracking has made a state
     * transition from <code>fromState</code> to <code>toState</code>.
     * <p>
     * @return     a registry entry representing the tracked application
     * @since   MHP1.0
     */
    public AppID getAppID () {
	return null ;
    }
    
    /**
     * The application the listener is tracking was in<code>fromState</code>, 
     * where the value of fromState is one of the state values defined in
     * the AppProxy interface or in the interfaces inheriting from it.
     *
     * @return     the old state
     * @since   MHP1.0
     */
    public int getFromState () {
	return 0 ;
    }

    /**
     * If the <code>hasFailed</code> method returns false, then the application the listener is 
     * tracking is now in <code>toState</code>. If the <code>hasFailed</code> method returns 
     * true, then the <code>toState</code> is the state 
     * where the state transition was attempted to but the transition failed. The value of 
     * <code>toState</code> is one of the state values defined in the <code>AppProxy</code> 
     * interface or in the interfaces inheriting from it.
     * @return     the intended or actual new state 
     * @since   MHP1.0
     */
    public int getToState () {
	return 0 ;
    }

    /**
     * This method determines whether an attempt to change the state of an application
     * has failed. 
     *
     * @return true if the attempt to change the state of the application failed, false otherwise
     * @since   MHP1.0
     */
    public boolean hasFailed () {
	return false ;
    }
}

