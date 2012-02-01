/*
 * @(#)TransitionEvent.java	1.12 98/03/28
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

package javax.media;

/**
 * <code>TransitionEvent</code> is a <code>ControllerEvent</code> that indicates
 * that a <code>Controller</code> has changed state.
 *

 * @see Controller
 * @see ControllerListener
 * @version 1.12, 98/03/28
 */
public class TransitionEvent extends ControllerEvent {

    int previousState;
    int currentState;
    int targetState;

    /**
     * Construct a new <CODE>TransitionEvent</CODE>.
     *
     * @param from The <code>Controller</code> that is generating this event.
     * @param previous The state that the <code>Controller</code> was in before this event.
     * @param current The state that the <code>Controller</code> is in as a result of this event.
     * @param target The state that the <code>Controller</code> is heading to.
    */

    public TransitionEvent(Controller from, 
			   int previous, int current, int target) {
	super(from);
	previousState = previous; 
	currentState = current;
	targetState = target;
    }

    /**
     * Get the state that the <code>Controller</code> was in before this event occurred.
     *
     * @return The  <code>Controller's</code> previous state.
    */
    public int getPreviousState() {
	return previousState;
    }
    
    /**
     * Get the <code>Controller's</code> state at the time this event was
     * generated
     *
     * @return The <code>Controller's</code> current state.
    */
    public int getCurrentState() {
	return currentState;
    }

    /**
     * Get the <code>Controller's</code> target state at the time this event
     * was generated.
     *
     * @return The <code>Controller's</code> target state.
    */
    public int getTargetState() {
	return targetState;
    }
    
    /**
     * Returns the String representation of this event's values.
     */
    public String toString() {
	return getClass().getName() + "[source=" + eventSrc + 
	    ",previous=" + stateName(previousState) + 
	    ",current=" + stateName(currentState) +
	    ",target=" + stateName(targetState) + "]";
    }


    static String stateName(int state) {
	switch (state) {
	  case Controller.Unrealized: return "Unrealized";
	  case Controller.Realizing: return "Realizing";
	  case Controller.Realized: return "Realized";
	  case Controller.Prefetching: return "Prefetching";
	  case Controller.Prefetched: return "Prefetched";
	  case Controller.Started: return "Started";
	  default: return "<Unknown>";
	}
    }
}



