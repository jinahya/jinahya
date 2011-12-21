/*
 * Copyright 2011 Jin Kwon &lt;jinahya at gmail.com&gt;.
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
 * @author Jin Kwon &lt;jinahya at gmail.com&gt;
 */
public final class InvalidState extends AbstractState {


    /** GENERATED. */
    private static final long serialVersionUID = 13872897746229354L;


    /** instance holder. */
    private static class InstanceHolder {


        /** INSTANCE. */
        private static final State INSTANCE = new InvalidState();


    }


    /**
     * Returns the instance. InvalidState is a singleton.
     *
     * @return the instance
     */
    public static State getInstance() {
        return InstanceHolder.INSTANCE;
    }


    /**
     * The code of this state.
     */
    public static final int CODE = -2;


    /**
     * The name of this state.
     */
    public static final String NAME = "INVALID";


    /**
     * Creates a new instance.
     */
    private InvalidState() {
        super(CODE, NAME);
    }


}

