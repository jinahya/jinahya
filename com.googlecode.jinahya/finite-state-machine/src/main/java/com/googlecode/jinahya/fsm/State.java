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


import java.io.Serializable;


/**
 * A state of a machine.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface State extends Serializable {


    /**
     * The unknown state.
     */
    public static final State UNKNOWN = new DefaultState(-1, "UNKNOWN");


    /**
     * The invalid state.
     */
    public static final State INVALID = new DefaultState(-2, "INVALID");


    /**
     * Returns code.
     *
     * @return code
     */
    int code();


    /**
     * Returns name.
     *
     * @return name
     */
    String name();


}

