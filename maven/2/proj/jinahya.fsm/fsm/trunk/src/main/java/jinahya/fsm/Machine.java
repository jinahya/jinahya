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


import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Machine {


    /**
     * Creates a new instance.
     *
     * @param spec spec
     * @param factory task factory
     * @param state initial state
     * @throws MachineException if any error occurs.
     */
    public Machine(final MachineSpec spec, final TaskFactory factory,
                   final State state)
        throws MachineException {

        super();

        if (spec == null) {
            throw new NullPointerException("spec");
        }

        if (factory == null) {
            throw new NullPointerException("factory");
        }

        if (state == null) {
            throw new NullPointerException("state");
        }

        this.spec = spec;
        this.factory = factory;

        transit(state);
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
            try {
                pool.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }


        // --------------------------------------------------- CREATE TRANSITION
        final State sourceState = this.state;
        final State targetState = state;
        final State[] transitionHistory = new State[history.size()];
        history.toArray(transitionHistory);
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
        };


        // -------------------------------------------- CHECK TRANSITION ALLOWED
        if (!spec.isTransitionAllowed(transition)) {
            throw new MachineException("not allowed: " + transition);
        }


        // ------------------------------------------------------ CHECK STARTING
        if (!started && spec.isStartingTransition(transition)) {
            tasks = factory.createTasks();
            // ------------------------------------------------ INITIALIZE TASKS
            for (int i = 0; i < tasks.length; i++) {
                tasks[i].initialize();
            }
            started = true;
        }


        if (started) {

            final int _poolSize = poolSize;
            // --------------------------------------------------------- PERFORM
            if (_poolSize == 0) {
                // ------------------------------------------------ NO THREADING
                for (int priority = 0; priority <= minimumPriority;
                     priority++) {
                    for (int i = 0; i < tasks.length; i++) {
                        tasks[i].perform(transition, priority);
                    }
                }
            } else {
                // --------------------------------------------------- THREADING
                final long _poolSleep = poolSleep;
                Thread parent = null;
                for (int priority = 0; priority <= minimumPriority;
                     priority++) {
                    Thread child = new Thread(new ExecutorService
                        (parent, tasks, transition, priority, _poolSize,
                         _poolSleep));
                    child.start();
                    parent = child;
                }
                pool = parent;
            }

            // ------------------------------------------------- CHECK FINISHING
            if (spec.isFinishingTransition(transition)) {
                try {
                    pool.join();

                    // ------------------------------------------- DESTROY TASKS
                    for (int i = 0; i < tasks.length; i++) {
                        tasks[i].destroy();
                    }

                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }

                finished = true;
            }
        }

        // ------------------------------------------------------------- HISTORY
        history.insertElementAt(this.state, 0);
        int _historySize = historySize;
        while (history.size() > _historySize) {
            history.removeElementAt(history.size() - 1);
        }

        this.state = state;
    }


    /**
     * Returns the current thread count.
     *
     * @return thread count
     */
    public int getPoolSize() {
        return poolSize;
    }


    /**
     * Set the thread count.
     *
     * @param poolSize new thread count; zero for non-thread
     */
    public void setPoolSize(final int poolSize) {
        if (poolSize < 0) {
            throw new IllegalArgumentException
                ("poolSize: " + poolSize + " < 0");
        }
        this.poolSize = poolSize;
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
     *
     * @return
     */
    public long getPoolSleep() {
        return poolSleep;
    }


    /**
     * 
     * @param poolSleep
     */
    public void setPoolSleep(final long poolSleep) {
        if (poolSleep <= 0L) {
            throw new IllegalArgumentException(poolSleep + " <= 0L");
        }

        this.poolSleep = poolSleep;
    }


    /**
     * Return the current value of <code>minimumPriority</code>.
     *
     * @return minimumPriority
     */
    public int getMinimumPriority() {
        return minimumPriority;
    }


    /**
     * Sets new value for <code>minimumPriority</code>.
     *
     * @param minimumPriority minimumPriority (positive)
     */
    public void setMinimumPriority(final int minimumPriority) {
        if (minimumPriority < 0) {
            throw new IllegalArgumentException
                ("minimumPriority: " + minimumPriority + " < 0");
        }
        this.minimumPriority = minimumPriority;
    }


    private MachineSpec spec = null;
    private TaskFactory factory = null;

    private State state = State.UNKNOWN;

    private int historySize = 0;
    private int poolSize = 0;
    private long poolSleep = 0L;
    private int minimumPriority = 9;

    private final Vector history = new Vector();

    private transient Task[] tasks = null;

    private volatile boolean started = false;
    private volatile boolean finished = false;

    private transient Thread pool = null;
}
