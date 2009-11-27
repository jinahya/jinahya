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
public class StateMachineSupport {


    /**
     *
     * @param spec
     * @param factory
     * @param state
     * @throws StateMachineException
     */
    public StateMachineSupport(final StateMachineSpec spec,
                               final TaskFactory factory,
                               final State state)
        throws StateMachineException {

        super();

        this.spec = spec;
        this.factory = factory;

        transit(state);
    }


    /**
     *
     * @return
     */
    public synchronized State getState() {
        return state;
    }


    /**
     *
     * @param state
     * @throws StateMachineException
     */
    public synchronized void setState(final State state)
        throws StateMachineException {

        transit(state);
    }


    /**
     *
     * @param newState
     * @throws StateMachineException
     */
    public synchronized void transit(final State newState)
        throws StateMachineException {

        if (newState == null) {
            throw new IllegalArgumentException("new state is null!");
        }

        if (finished) {
            throw new StateMachineException("already finished!");
        }

        final Transition transition = new Transition(state, newState);

        if (!spec.isTransitionAllowed(transition)) {
            throw new StateMachineException
                ("transition is not allowed: " + transition);
        }

        state = newState;

        if (!started && spec.isStartingTransition(transition)) {
            started = true;

            tasks = factory.createTasks();
            for (int i = 0; i < tasks.length; i++) {
                tasks[i].initialize();
            }
        }


        if (!finished && spec.isFinishingTransition(transition)) {
            finished = true;

            for (int i = 0; i < tasks.length; i++) {
                tasks[i].destroy();
            }
        }


        TaskExecutorService parent = null;
        for (int priority = 9; priority >= 0; priority--) {
            /*
            for (int i = 0; i < tasks.length; i++) {
                tasks[i].perform(transition, priority);
            }
             */

            TaskExecutorService child =
                new TaskExecutorService(parent, tasks, transition, priority,
                                        threadCount);
            child.start();
            parent = child;
        }

        if (parent != null) {
            try {
                parent.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }


    public synchronized void setThreadCount(final int threadCount) {
        if (threadCount <= 0) {
            throw new IllegalArgumentException
                ("illegal thread count: " + threadCount);
        }
        this.threadCount = threadCount;
    }


    public synchronized int getThreadCount() {
        return threadCount;
    }


    private StateMachineSpec spec;
    private TaskFactory factory;

    private State state = State.UNKNOWN;

    private Task[] tasks;

    private volatile boolean started = false;
    private volatile boolean finished = false;

    private volatile int threadCount = 1;
}


