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
 * State of a machine.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface State extends Serializable {


    /**
     * Default state that every newly created machine has.
     */
    public static final State UNKNOWN = new State() {


        /** GENERATED. */
        private static final long serialVersionUID = -2761992830718509483L;


        @Override
        public int getCode() {
            return -1;
        }


        @Override
        public String getName() {
            return "UNKNOWN";
        }
    };


    /**
     * Returns code value of this State.
     *
     * @return code
     */
    int getCode();


    /**
     * Returns the name of this State.
     *
     * @return name
     */
    String getName();
}
