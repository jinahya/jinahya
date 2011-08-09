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
public class Machine {


    /**
     * Creates a new instance.
     *
     * @param state initial state
     */
    public Machine(final State state) {

        super();

        if (state == null) {
            throw new NullPointerException("null state");
        }

        this.state = state;
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
    }


    private volatile State state;
}

