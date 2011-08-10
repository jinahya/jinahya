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


package com.googlecode.jinahya.util.fsm;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Machine {


    /**
     * Creates a new instance.
     *
     * @param context task context
     */
    public Machine(final TaskContext context) {

        super();

        if (context == null) {
            throw new NullPointerException("null context");
        }

        this.context = context;
    }


    /**
     * Returns current state.
     *
     * @return state
     */
    public synchronized State getState() {
        return state;
    }


    /**
     * Sets state.
     *
     * @param state state
     */
    public synchronized void setState(final State state) {

        if (state == null) {
            throw new NullPointerException("null state");
        }

        if (this.state.equals(state)) {
            throw new IllegalStateException("same state");
        }

        if (finished) {
            throw new IllegalStateException("already finished");
        }

        final State source = this.state;
        this.state = state;
        final State target = this.state;

        final Transition transition = new Transition(this, source, target);

        if (!started) {
            if (!isStarting(transition)) {
                throw new IllegalStateException("not started yet");
            }
            started = true;
        }

        try {
            context.transited(transition);
        } finally {
            if (isFinishing(transition)) {
                finished = true;
            }
        }
    }


    /**
     * 
     * @param transition
     * @return 
     */
    protected abstract boolean isStarting(Transition transition);


    /**
     * 
     * @param transition
     * @return 
     */
    protected abstract boolean isAllowed(Transition transition);


    /**
     * 
     * @param transition
     * @return 
     */
    protected abstract boolean isFinishing(Transition transition);


    /** flag for started. */
    private volatile boolean started = false;


    /** flag for finished. */
    private volatile boolean finished = false;


    /** current state. */
    private volatile State state = State.UNKNOWN;


    /** task context. */
    private final TaskContext context;
}

