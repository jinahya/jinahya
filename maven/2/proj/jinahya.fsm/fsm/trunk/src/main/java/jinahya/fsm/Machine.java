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


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Machine {


    /**
     * Creates a new instance.
     *
     * @param spec machine spec
     * @param tasks tasks to be performed
     */
    public Machine(final MachineSpec spec, final Task[] tasks) {
        super();

        if (spec == null) {
            throw new NullPointerException("spec");
        }

        if (tasks == null) {
            throw new NullPointerException("tasks");
        }

        this.spec = spec;

        this.tasks = new Task[tasks.length];
        System.arraycopy(tasks, 0, this.tasks, 0, this.tasks.length);
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
     * @see #setState(jinahya.fsm.State)
     */
    public synchronized void transit(final State state)
        throws MachineException {

        if (state == null) {
            throw new NullPointerException("state");
        }


        // ------------------------------------------------------ CHECK FINISHED
        if (isFinished()) {
            return;
        }


        // --------------------------------------------------- CREATE TRANSITION
        final State sourceState = this.state;
        final State targetState = state;
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
            if (threadPoolSize == 0) {
                for (int precedence = 0; precedence <= minimumPrecedence;
                     precedence++) {
                    for (int i = 0; i < tasks.length; i++) {
                        tasks[i].perform(transition, precedence);
                    }
                }
            } else {

                Thread parent = null;

                for (int precedence = 0; precedence <= minimumPrecedence;
                     precedence++) {
                    Thread child = new Thread
                        (new Executor(parent, tasks, transition, precedence,
                                      threadPoolSize));
                    child.start();
                    parent = child;
                }

                while (parent.isAlive()) {
                    try {
                        parent.join();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }


        // ----------------------------------------------------- CHECK FINISHING
        if (spec.isFinishingTransition(transition)) {
            finish();
        }


        this.state = state;
    }


    public synchronized boolean isStarted() {
        return started;
    }


    public synchronized void start() throws MachineException {
        if (started) {
            return;
        }

        //tasks = factory.createTasks();

        for (int i = 0; i < tasks.length; i++) {
            tasks[i].initialize();
        }

        started = Boolean.TRUE.booleanValue();
    }


    public synchronized boolean isFinished() {
        return finished;
    }


    public synchronized void finish() throws MachineException {
        if (finished) {
            return;
        }

        if (isStarted()) {
            for (int i = 0; i < tasks.length; i++) {
                tasks[i].destroy();
            }
        }

        finished = Boolean.TRUE.booleanValue();
    }


    public int getMinimumPrecedence() {
        return minimumPrecedence;
    }


    public void setMinimumPrecedence(int minimumPrecedence) {
        if (minimumPrecedence < 0) {
            throw new IllegalArgumentException
                ("minimumPrecedence: " + minimumPrecedence + " < 0");
        }

        if (isStarted() || isFinished()) {
            return;
        }

        this.minimumPrecedence = minimumPrecedence;
    }


    public int getThreadPoolSize() {
        return threadPoolSize;
    }


    public void setThreadPoolSize(int threadPoolSize) {
        if (threadPoolSize < 0) {
            throw new IllegalArgumentException
                ("threadPoolSize: " + threadPoolSize + " < 0");
        }

        if (isStarted() || isFinished()) {
            return;
        }

        this.threadPoolSize = threadPoolSize;
    }


    private MachineSpec spec;
    private Task[] tasks;
    //private TaskFactory factory;

    private int minimumPrecedence = 0;
    private int threadPoolSize = 0;

    //private transient Task[] tasks = null;

    private volatile State state = State.UNKNOWN;

    private volatile boolean started = Boolean.FALSE.booleanValue();
    private volatile boolean finished = Boolean.FALSE.booleanValue();
}
