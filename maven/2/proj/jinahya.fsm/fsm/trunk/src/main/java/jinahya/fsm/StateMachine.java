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
public class StateMachine {


    /**
     * Creates a new instance.
     *
     * @param spec spec
     * @param factory task factory
     * @param state initial state
     * @throws StateMachineException if any error occurs.
     */
    public StateMachine(final StateMachineSpec spec, final TaskFactory factory,
                        final State state)
        throws StateMachineException {

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
     * @throws StateMachineException if any error occurs.
     */
    public synchronized void setState(final State state)
        throws StateMachineException {

        transit(state);
    }


    /**
     * Transit the state.
     *
     * @param state new state
     * @throws StateMachineException if any error occurs.
     */
    public synchronized void transit(final State state)
        throws StateMachineException {

        if (state == null) {
            throw new IllegalArgumentException("new state is null!");
        }

        if (finished) {
            throw new IllegalStateException("already finished!");
        }


        // -------------------------------------- JOIN PREVIOUS EXECUTOR SERVICE
        if (previousExecutorService != null) {
            try {
                previousExecutorService.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }


        // --------------------------------------------------- CREATE TRANSITION
        final State[] transitionHistory = new State[history.size()];
        history.toArray(transitionHistory);
        final Transition transition =
            new Transition(this.state, state, transitionHistory);


        // -------------------------------------------- CHECK TRANSITION ALLOWED
        if (!spec.isTransitionAllowed(transition)) {
            throw new StateMachineException
                ("transition is not allowed: " + transition);
        }


        // -------------------------------------------------------- CHANGE STATE
        State oldState = this.state;
        this.state = state;


        // ------------------------------------------------------ CHECK STARTING
        if (!started && spec.isStartingTransition(transition)) {
            start();
        }


        if (started) {

            // --------------------------------------------------------- PERFORM
            if (threadCount > 0) {
                ExecutorService parent = null;
                for (int priority = 9; priority >= 0; priority--) {
                    ExecutorService child =
                        new ExecutorService(parent, tasks, transition, priority,
                                            threadCount);
                    child.start();
                    parent = child;
                }
                previousExecutorService = parent;
            } else {
                for (int priority = 9; priority >= 0; priority--) {
                    for (int i = 0; i < tasks.length; i++) {
                        tasks[i].perform(transition, priority);
                    }
                }
            }

            // ------------------------------------------------- CHECK FINISHING
            if (!finished && spec.isFinishingTransition(transition)) {
                finish();
            }
        }

        // ------------------------------------------------------------- HISTORY
        history.addElement(oldState);
        while (history.size() > historyCount) {
            history.removeElementAt(0);
        }
    }


    /**
     * Returns the current thread count.
     *
     * @return thread count
     */
    public synchronized int getThreadCount() {
        return threadCount;
    }


    /**
     * Set the thread count.
     *
     * @param threadCount new thread count; zero for non-thread
     */
    public synchronized void setThreadCount(final int threadCount) {
        if (threadCount < 0) {
            throw new IllegalArgumentException
                ("illegal thread count: " + threadCount);
        }
        this.threadCount = threadCount;
    }


    /**
     * Returns current value of historyCount.
     *
     * @return historyCount
     */
    public synchronized int getHistoryCount() {
        return historyCount;
    }


    /**
     * Sets state transition history count.
     *
     * @param historyCount new history count; non-zero positive
     */
    public synchronized void setHistoryCount(int historyCount) {
        if (historyCount < 0) {
            throw new IllegalArgumentException
                ("illegal history count: " + historyCount);
        }
        this.historyCount = historyCount;
    }


    public synchronized void start() throws StateMachineException {
        if (started) {
            return;
        }
        started = true;

        final Task[] created = factory.createTasks();
        for (int i = 0; i < created.length; i++) {
            if (created[i] == null) {
                throw new NullPointerException("null task @ " + i);
            }
        }
        tasks = new Task[created.length];
        System.arraycopy(created, 0, tasks, 0, tasks.length);

        // ---------------------------------------------------- INITIALIZE TASKS
        for (int i = 0; i < tasks.length; i++) {
            tasks[i].initialize();
        }
    }


    public synchronized void finish() throws StateMachineException {
        if (finished) {
            return;
        }
        finished = true;

        if (tasks != null) {
            return;
        }

        // ------------------------------------------------------- DESTROY TASKS
        for (int i = 0; i < tasks.length; i++) {
            tasks[i].destroy();
        }
    }


    private StateMachineSpec spec = null;
    private TaskFactory factory = null;

    private State state = State.UNKNOWN;

    private transient Task[] tasks = null;

    private volatile boolean started = false;
    private volatile boolean finished = false;

    private volatile int threadCount = 0;

    private volatile int historyCount = 0;
    private final Vector history = new Vector();

    private transient Thread previousExecutorService = null;
}
