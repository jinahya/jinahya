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
public class AbstractState implements State {


    /** GENERATED. */
    private static final long serialVersionUID = 259468593205957460L;


    /**
     * Creates a new instance.
     *
     * @param value state value
     * @param name  state name
     */
    public AbstractState(final int value, final String name) {
        super();

        if (name == null) {
            throw new NullPointerException("null name");
        }

        this.value = value;
        this.name = name;
    }


    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof State)) { // (null instanceof XXX) -> false
            return false;
        }

        final State state = (State) obj;

        if (value != state.getValue()) {
            return false;
        }

        if (!name.equals(state.getName())) {
            return false;
        }

        return true;
    }


    public int hashCode() {

        int hashCode = 17;

        hashCode = 37 * hashCode + value;

        hashCode = 37 * hashCode + name.hashCode();

        return hashCode;
    }


    public String toString() {
        return "State(" + name + "(" + value + "))";
    }


    public int getValue() {
        return value;
    }


    public String getName() {
        return name;
    }


    private final int value;


    private final String name;
}

