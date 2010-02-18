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


import java.util.Vector;

import jinahya.fsm.event.TransitionEvent;
import jinahya.fsm.event.TransitionEventListener;

import jinahya.util.els.EventListenerSupport;


/**
 * Represents a finite state machine.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Machine {


    /** the default value for maximumHistorySize. */
    private static final int DEFAULT_MAXIMUM_HISTORY_SIZE = 0x0A;


    /**
     * Creates a new instance with a spec. The maximumHistorySize is 0x0A.
     *
     * @param spec machine spec
     */
    public Machine(final MachineSpec spec) {
        this(spec, DEFAULT_MAXIMUM_HISTORY_SIZE);
    }


    /**
     * Creates a new instance.
     *
     * @param spec machine spec
     * @param maximumHistorySize maximum history size
     */
    public Machine(final MachineSpec spec, final int maximumHistorySize) {
        super();

        if (spec == null) {
            throw new NullPointerException("spec");
        }

        if (maximumHistorySize < 0) {
            throw new IllegalArgumentException("maximumHistorySize < 0");
        }

        _spec = spec;
        _maximumHistorySize = maximumHistorySize;
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
            //@Override
            public int getSourceState() {
                return sourceState;
            }
            //@Override
            public int getTargetState() {
                return targetState;
            }
            //@Override
            public int getPreviousState(final int depth) {
                if (depth <= 0) {
                    throw new IllegalArgumentException("depth <= 0");
                }
                if (depth < previousStates.length) {
                    return previousStates[depth];
                } else {
                    return State.UNKNOWN;
                }
            }
            //@Override
            public String toString() {
                return "[TRANSITION: " + sourceState + " -> " +
                        targetState + "]";
            }
        };


        // -------------------------------------------- CHECK TRANSITION ALLOWED
        if (!_spec.isTransitionAllowed(transition)) {
            throw new MachineException("not allowed by spec: " + transition);
        }


        // ------------------------------------------------------ CHECK STARTING
        if (!started && _spec.isStartingTransition(transition)) {
            started = Boolean.TRUE.booleanValue();
        }


        /*
        if (started) {

            // --------------------------------------------------------- PERFORM

            final int minimumPrecedence =
                Math.max(0x00, spec.getMinimumPrecedence(transition));

            Thread[] threads = null;

            for (int i = 0; i <= minimumPrecedence; i++) {
                threads = perform(threads, transition, i);
            }

            // wait for all threads in last group end
            // minimumPrecedence is always positive(>=0)
            // assert threads != null;
            for (int i = 0; i < threads.length; i++) {
                while (threads[i].isAlive()) {
                    try {
                        threads[i].join();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }
         */


        // ----------------------------------------------------- CHECK FINISHING
        if (!finished && _spec.isFinishingTransition(transition)) {
            finished = Boolean.TRUE.booleanValue();
        }


        // ------------------------------------ PUT CURRENT STATE TO THE HISTORY
        states.insertElementAt(new Integer(this.state), 0);
        states.setSize(Math.min(states.size(), _maximumHistorySize));


        // ------------------------------------------------ CHANGE CURRENT STATE
        this.state = state;


        // ---------------------------------------------------------- FIRE EVENT
        final TransitionEvent event = new TransitionEvent(this, transition);
        final Object[] listeners = els.getListeners();
        for (int i = 0; i < listeners.length; i++) {
            ((TransitionEventListener) listeners[i]).transited(event);
        }

    }


    /*
    private Thread[] perform(final Thread[] parents,
                             final Transition transition,
                             final int precedence) {

        // wait for all previous threads end
        if (parents != null) {
            for (int i = 0; i < parents.length; i++) {
                while (parents[i].isAlive()) {
                    try {
                        parents[i].join();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }

        // prepare a copy of task list
        final Vector tmp = new Vector();
        synchronized (tasks) {
            for (int i = 0; i < tasks.size(); i++) {
                tmp.addElement(tasks.elementAt(i));
            }
        }

        final int maximumPoolSize =
            Math.max(0x01, spec.getMaximumPoolSize(transition, precedence));

        Thread[] children = new Thread[maximumPoolSize];
        for (int i = 0; i < children.length; i++) {
            children[i] = new Thread() {
                //@Override
                public void run() {
                    for (Task task = null; true; task = null) {

                        // locate next task
                        synchronized (tmp) {
                            if (!tmp.isEmpty()) {
                                task = (Task) tmp.firstElement();
                                tmp.removeElementAt(0x00);
                            }
                        }

                        if (task == null) {
                            break;
                        }

                        // perform the task
                        try {
                            task.perform(transition, precedence);
                        } catch (MachineException me) {
                            me.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }

        for (int i = 0; i < children.length; i++) {
            children[i].start();
        }

        return children;
    }
     */


    /*
    public final void submit(final Task task) {

        if (task == null) {
            throw new NullPointerException("task");
        }

        tasks.addElement(task);
    }
     */


    /**
     * Adds specified transition listener.
     *
     * @param l listener
     */
    public final void addTransitionEventListener(
        final TransitionEventListener l) {

        els.add(TransitionEventListener.class, l);
    }


    /**
     * Removes specified transition listener.
     *
     * @param l listener
     */
    public final void removeTransitionEventListener(
        final TransitionEventListener l) {

        els.remove(TransitionEventListener.class, l);
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
     * Check whether this machine has been started and not finished yet.
     *
     * @return true if this machine has been started and not finished yet.
     */
    public synchronized final boolean isValid() {
        return started && !finished;
    }


    private final MachineSpec _spec;
    private final int _maximumHistorySize;

    private volatile int state = State.UNKNOWN;

    private final Vector states = new Vector();

    private volatile boolean started = Boolean.FALSE.booleanValue();
    private volatile boolean finished = Boolean.FALSE.booleanValue();

    private final EventListenerSupport els = new EventListenerSupport();
}
