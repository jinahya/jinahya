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
        final int[] previousStates = new int[history.size()];
        for (int i = 0; i < previousStates.length; i++) {
            previousStates[i] = ((Integer) history.elementAt(i)).intValue();
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
            if (poolSize == 0x00) {
                for (int p = 0; p <= minimumPrecedence; p++) {
                    for (int i = 0; i < tasks.size(); i++) {
                        try {
                            ((Task) tasks.elementAt(i)).perform(transition, p);
                        } catch (MachineException me) {
                            me.printStackTrace();
                        }
                    }
                }
            } else {

                Thread[] threads = null;

                for (int p = 0; p <= minimumPrecedence; p++) {
                    threads = perform(threads, transition, p);
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
        history.insertElementAt(new Integer(this.state), 0);
        history.setSize(Math.min(history.size(), historySize));


        this.state = state;
    }


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
        for (int i = 0; i < tasks.size(); i++) {
            tmp.addElement(tasks.elementAt(i));
        }

        Thread[] children = new Thread[poolSize];
        for (int i = 0; i < children.length; i++) {
            children[i] = new Thread() {
                //@Override
                public void run() {
                    while (Boolean.TRUE.booleanValue()) {

                        // locate next task
                        Task task = null;
                        synchronized (tmp) {
                            if (tmp.isEmpty()) {
                                break;
                            }
                            task = (Task) tmp.firstElement();
                            tmp.removeElementAt(0x00);
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
        return started;
    }


    public final synchronized void start() throws MachineException {
        if (isStarted() || isFinished()) {
            return;
        }

        try {
            synchronized (tasks) {
                for (int i = 0; i < tasks.size(); i++) {
                    ((Task) tasks.elementAt(i)).initialize();
                }
            }
        } finally {
            started = Boolean.TRUE.booleanValue();
        }
    }


    public final synchronized boolean isFinished() {
        return finished;
    }


    public final synchronized void finish() throws MachineException {
        if (!isStarted() || isFinished()) {
            return;
        }

        try {
            synchronized (tasks) {
                for (int i = tasks.size() - 1; i >= 0; i--) {
                    ((Task) tasks.elementAt(i)).destroy();
                }
            }
        } finally {
            finished = Boolean.TRUE.booleanValue();
        }
    }


    public final int getMinimumPrecedence() {
        return minimumPrecedence;
    }


    public final void setMinimumPrecedence(int minimumPrecedence) {

        if (minimumPrecedence < 0) {
            throw new IllegalArgumentException
                ("minimumPrecedence(" + minimumPrecedence + ") < 0");
        }

        synchronized (this) {
            if (isStarted()) {
                throw new IllegalStateException("already started");
            }

            if (isFinished()) {
                throw new IllegalStateException("already finished");
            }

            this.minimumPrecedence = minimumPrecedence;
        }
    }


    public final int getPoolSize() {
        return poolSize;
    }


    public final void setPoolSize(int poolSize) {
        if (poolSize < 0) {
            throw new IllegalArgumentException
                ("poolSize(" + poolSize + ") < 0");
        }

        synchronized (this) {
            if (isStarted()) {
                throw new IllegalStateException("already started");
            }

            if (isFinished()) {
                throw new IllegalStateException("already finished");
            }

            this.poolSize = poolSize;
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

            tasks.addElement(task);
        }
    }


    public final int getHistorySize() {
        return historySize;
    }


    public final void setHistorySize(final int historySize) {
        if (historySize < 0) {
            throw new IllegalArgumentException
                ("historySize(" + historySize + ") < 0");
        }

        synchronized (this) {
            if (isStarted()) {
                throw new IllegalStateException("already started");
            }

            if (isFinished()) {
                throw new IllegalStateException("already finished");
            }

            this.historySize = historySize;
        }
    }


    private MachineSpec spec;

    private final Vector tasks = new Vector();

    private volatile int state = State.UNKNOWN;

    private int minimumPrecedence = 0;
    private int poolSize = 0;

    private int historySize = 0;
    private final Vector history = new Vector();

    private volatile boolean started = Boolean.FALSE.booleanValue();
    private volatile boolean finished = Boolean.FALSE.booleanValue();
}
