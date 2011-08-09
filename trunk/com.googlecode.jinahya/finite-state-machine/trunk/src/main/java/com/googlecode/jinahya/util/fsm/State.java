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


import java.io.Serializable;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface State extends Serializable {


    /**
     * Constant for UNKONWN state.
     */
    public static final State UNKNOWN = new AbstractState(-1, "UNKNOWN") {


        /** GENERATED. */
        private static final long serialVersionUID = -7766829699556888699L;
    };


    /**
     * Constant for INVALID state.
     */
    public static final State INVALID = new AbstractState(-1, "INVALID") {


        /** GENERATE. */
        private static final long serialVersionUID = -7766829699556888699L;
    };


    /**
     * Returns the integer value representing this state.
     *
     * @return integer representation
     */
    int getValue();


    /**
     * Returns the name of this state.
     *
     * @return name of this state
     */
    String getName();
}

