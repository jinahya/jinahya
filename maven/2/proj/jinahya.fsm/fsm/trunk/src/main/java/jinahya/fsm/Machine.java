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


/**
 * Represents a finite state machine.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Machine {


    private static final int STARTED = 0x01;

    private static final int FINISHED = STARTED << 1;


    /**
     * Creates a new instance.
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
    public synchronized final int getState() {
        return state;
    }


    /**
     * Sets the current state. Identical to transit().
     *
     * @param state new state
     * @throws MachineException if any error occurs.
     * @see #transit(int)
     */
    public synchronized final void setState(final int state)
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
    public synchronized final void transit(final int state)
        throws MachineException {

        // ------------------------------------------------------ CHECK FINISHED
        if (isFinished()) {
            return;
        }

        // --------------------------------------------------- CREATE TRANSITION
        final int sourceState = this.state;
        final int targetState = state;
        final int[] previousStates = new int[previousStatesVector.size()];
        for (int i = 0; i < previousStates.length; i++) {
            previousStates[i] = ((Integer) previousStatesVector.elementAt(i)).intValue();
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
            public int[] getPreviousStates() {
                int[] result = new int[previousStates.length];
                System.arraycopy(previousStates, 0, result, 0, result.length);
                return result;
            }
            //@Override
            public String toString() {
                return "[TRANSITION: " + sourceState + " -> " +
                        targetState + "]";
            }
        };


        // -------------------------------------------- CHECK TRANSITION ALLOWED
        if (!spec.isTransitionAllowed(transition)) {
            throw new MachineException("not allowed: " + transition);
        }


        // ------------------------------------------------------ CHECK STARTING
        if (spec.isStartingTransition(transition)) {
            start();
        }


        if (isStarted()) {


            // --------------------------------------------------------- PERFORM

            final int maximumPoolSize =
                Math.max(0x00, spec.getMaximumPoolSize());

            final int minimumPrecedence =
                Math.max(0x00, spec.getMinimumPrecedence());

            if (maximumPoolSize == 0) {
                for (int p = 0; p <= minimumPrecedence; p++) {
                    for (int i = 0; i < taskVector.size(); i++) {
                        ((Task) taskVector.elementAt(i)).perform(transition, p);
                    }
                }
            } else {

                Thread[] threads = null;

                for (int p = 0; p <= minimumPrecedence; p++) {
                    threads = perform(threads, transition, p, maximumPoolSize);
                }

                // wait for all threads in last group end
                // minimumPrecedence is always positive(>=0)
                //assert threads != null;
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
        }


        // ----------------------------------------------------- CHECK FINISHING
        if (spec.isFinishingTransition(transition)) {
            finish();
        }


        // ------------------------------------------------------------- HISTORY
        previousStatesVector.insertElementAt(new Integer(this.state), 0);
        final int maximumHistorySize =
            Math.max(0x00, spec.getMaximumHistorySize());
        previousStatesVector.setSize(Math.min(previousStatesVector.size(), maximumHistorySize));


        this.state = state;
    }


    private Thread[] perform(final Thread[] parents,
                             final Transition transition,
                             final int precedence, final int maximumPoolSize) {

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
        for (int i = 0; i < taskVector.size(); i++) {
            tmp.addElement(taskVector.elementAt(i));
        }

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


    public final synchronized boolean isStarted() {
        return ((modifier & STARTED) != 0);
    }


    public final synchronized void start() throws MachineException {
        if (isStarted() || isFinished()) {
            return;
        }

        modifier |= STARTED;

        synchronized (taskVector) {
            for (int i = 0; i < taskVector.size(); i++) {
                try {
                    ((Task) taskVector.elementAt(i)).initialize();
                } catch (MachineException me) {
                    me.printStackTrace();
                }
            }
        }
    }


    public final synchronized boolean isFinished() {
        return ((modifier & FINISHED) != 0);
    }


    public final synchronized void finish() throws MachineException {
        if (isFinished()) {
            return;
        }

        modifier |= FINISHED;

        if (isStarted()) {
            synchronized (taskVector) {
                for (int i = taskVector.size() - 1; i >= 0; i--) {
                    try {
                        ((Task) taskVector.elementAt(i)).destroy();
                    } catch (MachineException me) {
                        me.printStackTrace();
                    }
                }
            }
        }
    }


    public final void submit(final Task task) {

        if (task == null) {
            throw new NullPointerException("task");
        }

        synchronized (this) {
            if (isStarted()) {
                throw new IllegalStateException("already started");
            }

            if (isFinished()) {
                throw new IllegalStateException("already finished");
            }

            taskVector.addElement(task);
        }
    }


    private MachineSpec spec;

    private final Vector taskVector = new Vector();

    private volatile int state = State.UNKNOWN;

    private final Vector previousStatesVector = new Vector();

    private volatile int modifier = 0x00;

    //private volatile boolean started = Boolean.FALSE.booleanValue();
    //private volatile boolean finished = Boolean.FALSE.booleanValue();
}
