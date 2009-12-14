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
 *  under the License.
 */

package jinahya.fsm;


import java.io.PrintStream;
import java.util.Date;
import java.util.Vector;

import jinahya.fsm.MachineSpec.TransitionSpec;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Machine {


    /**
     * Date to be used for loggine.
     */
    private static final Date DATE = new Date();


    /**
     * Creates a new instance.
     *
     * @param spec machine spec
     * @param factory task factory
     */
    public Machine(final MachineSpec spec, final TaskFactory factory) {
        super();

        if (spec == null) {
            throw new NullPointerException("spec");
        }

        if (factory == null) {
            throw new NullPointerException("factory");
        }

        this.spec = spec;
        this.factory = factory;
    }


    /**
     * Returns the current state value.
     *
     * @return the current state
     */
    public synchronized State getState() {
        return state;
    }


    /**
     * Sets the current state. Identical to transit().
     *
     * @param state new state
     * @throws MachineException if any error occurs.
     * @see #transit(jinahya.fsm.State)
     */
    public synchronized void setState(final State state)
        throws MachineException {

        transit(state);
    }


    /**
     * Transit the state.
     *
     * @param state new state
     * @throws MachineException if any error occurs.
     */
    public synchronized void transit(final State state)
        throws MachineException {

        if (state == null) {
            throw new NullPointerException("state");
        }

        if (finished) {
            throw new IllegalStateException("finished");
        }


        // -------------------------------------- JOIN PREVIOUS EXECUTOR SERVICE
        if (pool != null) {
            while (pool.isAlive()) {
                try {
                    pool.join();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            pool = null;
        }


        // --------------------------------------------------- CREATE TRANSITION
        final State sourceState = this.state;
        final State targetState = state;
        final State[] transitionHistory = new State[historyVector.size()];
        historyVector.toArray(transitionHistory);
        final Transition transition = new Transition() {
            //@Override
            public State getSourceState() {
                return sourceState;
            }
            //@Override
            public State getTargetState() {
                return targetState;
            }
            //@Override
            public State[] getTransitionHistory() {
                return transitionHistory;
            }
            //@Override
            public String toString() {
                return "[TRANSITION: " + sourceState + " -> " +
                        targetState + "]";
            }
        };

        log("transition: " + transition);


        // -------------------------------------------- CHECK TRANSITION ALLOWED
        if (!spec.isTransitionAllowed(transition)) {
            throw new MachineException("not allowed: " + transition);
        }


        // ------------------------------------------------------ CHECK STARTING
        if (!started && spec.isStartingTransition(transition)) {
            log("checked as a starting transition");

            // ---------------------------------------------------- CREATE TASKS
            log("creating tasks...");
            final Task[] _tasks = factory.createTasks();
            tasks = new Task[_tasks.length];
            System.arraycopy(_tasks, 0, tasks, 0, tasks.length);
            log("tasks are created");

            // ------------------------------------------------ INITIALIZE TASKS
            log("initializing tasks...");
            for (int i = 0; i < tasks.length; i++) {
                tasks[i].initialize();
            }
            log("tasks are initalized");

            started = true;
            log("machine started");
        }


        if (started) {

            final TransitionSpec transitionSpec =
                spec.getTransitionSpec(transition);

            log("transition spec: " + transitionSpec);

            final int poolSize = Math.max(0, transitionSpec.getPoolSize());
            log("pool size: " + poolSize);

            final int minimumPrecedence =
                Math.max(0, transitionSpec.getMinimumPrecedence());
            log("minimum precedence: " + minimumPrecedence);

            // --------------------------------------------------------- PERFORM
            if (poolSize == 0) {

                // ------------------------------------------------ NO THREADING
                for (int precedence = 0; precedence <= minimumPrecedence;
                     precedence++) {
                    for (int i = 0; i < tasks.length; i++) {
                        tasks[i].perform(transition, precedence);
                    }
                }
            } else {

                // --------------------------------------------------- THREADING
                final long poolSleep =
                    Math.max(0L, transitionSpec.getPoolSleep());
                log("pool sleep: " + poolSleep);

                Thread parent = null;
                for (int precedence = 0; precedence <= minimumPrecedence;
                     precedence++) {
                    Thread child = new Thread(new Executor
                        (parent, tasks, transition, precedence, poolSize,
                         poolSleep));
                    child.start();
                    parent = child;
                }
                pool = parent;
            }

            // ------------------------------------------------- CHECK FINISHING
            if (spec.isFinishingTransition(transition)) {
                log("checked as a finishing transition");

                if (pool != null) {
                    while (pool.isAlive()) {
                        try {
                            pool.join();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                    pool = null;
                }

                // ----------------------------------------------- DESTROY TASKS
                log("destroying tasks...");
                for (int i = 0; i < tasks.length; i++) {
                    tasks[i].destroy();
                }
                log("tasks are destroyed");

                finished = true;
                log("machine finished");
            }

            // ------------------------------------------- IMMEDIATE RETURN FLAG
            final boolean immediateReturnFlag =
                transitionSpec.getImmediateReturnFlag();
            log("immediate return flag: " + immediateReturnFlag);
            if (!immediateReturnFlag && pool != null) {
                while (pool.isAlive()) {
                    try {
                        pool.join();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
                pool = null;
            }

        } // started


        // ------------------------------------------------------------- HISTORY
        historyVector.insertElementAt(this.state, 0);
        while (historyVector.size() > historySize) {
            historyVector.removeElementAt(historyVector.size() - 1);
        }


        this.state = state;
    }


    /**
     * Returns current value of count.
     *
     * @return count
     */
    public int getHistorySize() {
        return historySize;
    }


    /**
     * Sets state transition history count.
     *
     * @param historySize new history count; non-zero positive
     */
    public void setHistorySize(final int historySize) {
        if (historySize < 0) {
            throw new IllegalArgumentException
                ("historySize: " + historySize + " < 0");
        }
        this.historySize = historySize;
    }


    /**
     * Set logger to which logging messages written.
     *
     * @param logger logger
     */
    public synchronized void setLogger(final PrintStream logger) {
        this.logger = logger;
    }


    private synchronized void log(final String message) {
        if (logger == null) {
            return;
        }
        synchronized (DATE) {
            DATE.setTime(System.currentTimeMillis());
            logger.println("[JINAHYA.FSM] " + message + " @ " + DATE);
        }
    }


    private MachineSpec spec;
    private TaskFactory factory;

    private State state = State.UNKNOWN;

    private int historySize = 0;
    private final Vector historyVector = new Vector();

    private transient Task[] tasks = null;

    private volatile boolean started = false;
    private volatile boolean finished = false;

    private transient Thread pool = null;

    private transient PrintStream logger;
}
