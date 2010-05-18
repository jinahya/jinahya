/*
 *  Copyright 2009 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.fsm;


import jinahya.fsm.event.TransitionEvent;
import jinahya.fsm.event.TransitionListener;
import java.util.Vector;

import jinahya.util.EventListenerSupport;


/**
 * Represents a finite state machine.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Machine {


    /**
     * The state that all new instances of machines has.
     */
    public static final int UNKNOWN = 0xFFFFFFFF;


    /**
     * Creates a new instance with a spec. The maximumHistorySize is 0x0A.
     *
     * @param spec machine spec
     */
    public Machine(final MachineSpec spec) {
        super();

        if (spec == null) {
            throw new NullPointerException("spec");
        }

        this.spec = spec;
    }


    /**
     * Returns the current state value.
     *
     * @return the current state
     */
    public final synchronized int getState() {
        return state;
    }


    /**
     * Sets the current state. Identical to transit().
     *
     * @param state new state
     * @throws MachineException if any error occurs.
     * @see #transit(int)
     */
    public final synchronized void setState(final int state)
        throws MachineException {

        transit(state);
    }


    /**
     * Transit the state.
     *
     * @param state new state
     * @throws MachineException if any error occurs.
     * @see #setState(int)
     */
    public final synchronized void transit(final int state)
        throws MachineException {

        // ------------------------------------------------------ CHECK FINISHED
        if (finished) {
            return;
        }

        // --------------------------------------------------- CREATE TRANSITION
        final int sourceState = this.state;
        final int targetState = state;
        final int[] previousStates = new int[states.size()];
        for (int i = 0; i < previousStates.length; i++) {
            previousStates[i] = ((Integer) states.elementAt(i)).intValue();
        }

        final Transition transition = new Transition() {

            private static final long serialVersionUID = -3638658218680618735L;

            //@Override
            public int getSourceState() {
                return sourceState;
            }

            //@Override
            public int getTargetState() {
                return targetState;
            }

            //@Override
            public int getPreviousStateCount() {
                return previousStates.length;
            }

            //@Override
            public int getPreviousStateAt(final int index) {
                return previousStates[index];
            }

            //@Override
            public final String toString() {
                return "[TRANSITION: " + sourceState + " -> " +
                        targetState + "]";
            }
        };


        // -------------------------------------------- CHECK TRANSITION ALLOWED
        if (!spec.isTransitionAllowed(transition)) {
            throw new MachineException("not allowed by spec: " + transition);
        }


        // ------------------------------------------------------ CHECK STARTING
        if (!started && spec.isStartingTransition(transition)) {
            started = Boolean.TRUE.booleanValue();
        }


        // ----------------------------------------------------- CHECK FINISHING
        if (!finished && spec.isFinishingTransition(transition)) {
            finished = Boolean.TRUE.booleanValue();
        }


        // ------------------------------------ PUT CURRENT STATE TO THE HISTORY
        states.insertElementAt(new Integer(this.state), 0);
        states.setSize(Math.min(states.size(), maximumHistorySize));


        // ------------------------------------------------ CHANGE CURRENT STATE
        this.state = state;


        // ---------------------------------------------------------- FIRE EVENT
        final TransitionEvent event = new TransitionEvent(this, transition);
        final Object[] listeners = els.getListeners();
        for (int i = 0; i < listeners.length; i++) {
            ((TransitionListener) listeners[i]).transited(event);
        }

    }


    /**
     * Adds specified transition listener.
     *
     * @param l listener
     */
    public final void addTransitionListener(final TransitionListener l) {
        els.add(TransitionListener.class, l);
    }


    /**
     * Removes specified transition listener.
     *
     * @param l listener
     */
    public final void removeTransitionListener(final TransitionListener l) {
        els.remove(TransitionListener.class, l);
    }


    /**
     * Checks whether this machine has been started or not.
     *
     * @return true if this machine has been stated, false otherwise.
     */
    public synchronized final boolean isStarted() {
        return started;
    }


    /**
     * Checks whether this machine has been finished or not.
     *
     * @return true if this machine has been finished, false otherwise.
     */
    public synchronized final boolean isFinished() {
        return finished;
    }


    /**
     * Sets the value of maximumHistorySize.
     *
     * @param maximumHistorySize maximum history size
     */
    protected final void setMaximumHistorySize(int maximumHistorySize) {
        if (maximumHistorySize < 0) {
            throw new IllegalArgumentException("maximumHistorySize < 0");
        }

        synchronized (this) {
            this.maximumHistorySize = maximumHistorySize;
        }
    }


    private final MachineSpec spec;
    private volatile int maximumHistorySize = 0x00;

    private volatile int state = UNKNOWN;

    private final Vector states = new Vector();

    private volatile boolean started = Boolean.FALSE.booleanValue();
    private volatile boolean finished = Boolean.FALSE.booleanValue();

    private final EventListenerSupport els = new EventListenerSupport();
}
