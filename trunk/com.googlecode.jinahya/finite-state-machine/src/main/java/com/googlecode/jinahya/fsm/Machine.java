/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.fsm;


import java.util.Collections;
import java.util.List;


/**
 * A Finite State Machine.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Machine {


    /**
     * Creates a new instance.
     *
     * @param tasks tasks
     */
    public Machine(final List<Task> tasks) {
        super();

        if (tasks == null) {
            throw new NullPointerException("null tasks");
        }

        if (tasks.isEmpty()) {
            throw new NullPointerException("empty tasks");
        }

        this.tasks = Collections.unmodifiableList(tasks);
    }


    /**
     * Returns current state of this machine.
     *
     * @return current state
     */
    public final synchronized State getState() {
        return state;
    }


    /**
     * Sets state.
     *
     * @param state new state
     * @throws FSMException if an error occurs.
     */
    public final synchronized void setState(final State state)
        throws FSMException {

        if (state == null) {
            throw new NullPointerException("null state");
        }

        if (this.state.equals(state)) {
            throw new FSMException("same state");
        }

        if (finished) {
            throw new FSMException("already finished");
        }

        final Transition transition = new Transition(this.state, state);

        if (!isAllowed(transition)) {
            throw new FSMException("not allowed: " + transition);
        }

        if (!started) {
            if (isStarting(transition)) {
                started = true;
            } else {
                throw new FSMException("not started yet");
            }
        }

        for (Task task : tasks) {
            task.perform(transition);
        }

        if (isFinishing(transition)) {
            finished = true;
        }

        this.state = state;
    }


    /**
     * Checks if given <code>transition</code> is a starting condition.
     * Default implementation always returns <code>true</code>. Override this
     * method if customizations are needed.
     *
     * @param transition transition to check
     * @return true if given <code>transition</code> is a starting condition;
     *         false otherwise.
     */
    protected abstract boolean isStarting(Transition transition);


    /**
     * Checks if given <code>transition</code> is allowed. Default
     * implementation always returns <code>true</code>. Override this method if
     * customizations are needed.
     *
     * @param transition transition to check.
     * @return true if given <code>transition</code> is allowed; false if not
     *         allowed.
     */
    protected abstract boolean isAllowed(Transition transition);


    /**
     * Checks if given <code>transition</code> is an finishing condition.
     * Default implementation always returns <code>false</code>. Override this
     * method if customizations are needed.
     *
     * @param transition transition to check
     * @return true if given <code>transition</code> is a finishing condition;
     *         false otherwise.
     */
    protected abstract boolean isFinishing(Transition transition);


    /** tasks. */
    private final transient List<Task> tasks;


    /** flag for started. */
    private volatile boolean started = false;


    /** flag for finished. */
    private volatile boolean finished = false;


    /** current state. */
    private volatile State state = State.UNKNOWN;


}

