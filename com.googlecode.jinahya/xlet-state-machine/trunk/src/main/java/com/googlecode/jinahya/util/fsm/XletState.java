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
 * States.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletState extends AbstractState {


    /** GENERATED. */
    private static final long serialVersionUID = -1830570028535534271L;


    /**
     * LOADED state. This state is a virtual one.
     */
    public static final State LOADED = new XletState(1, "LOADED");


    /**
     * PAUSED state. This state can be reached when <code>initXlet</code>
     * invoked from {@link #LOADED} or <code>pauseXlet</code> invoked from
     * {@link #ACTIVE}.
     */
    public static final State PAUSED =
        new XletState(LOADED.getCode() << 3, "PAUSED");


    /**
     * ACTIVE(STARTED) state. {@link XletMachine} can be reached to this state
     * when <code>pauseXlet</code> invoked from {@link #PAUSED}.
     */
    public static final State ACTIVE =
        new XletState(PAUSED.getCode() << 3, "ACTIVE");


    /**
     * DESTROYED state.
     */
    public static final State DESTROYED =
        new XletState(ACTIVE.getCode() << 3, "DESTROYED");


    /**
     * Creates a new instance.
     *
     * @param code state code
     * @param name state name
     */
    protected XletState(final int code, final String name) {
        super(code, name);
    }
}

