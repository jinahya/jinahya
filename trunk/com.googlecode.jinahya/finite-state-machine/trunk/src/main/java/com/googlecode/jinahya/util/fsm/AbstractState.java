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
 * State of a machine.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class AbstractState implements State {


    /** GENERATED. */
    private static final long serialVersionUID = 3885354297727614037L;


    /**
     * Creates a new State instance.
     *
     * @param code state code
     * @param name state name
     * @return a new State instance.
     */
    public static State newInstance(final int code, final String name) {

        return new AbstractState(code, name) {


            /** GENERATE. */
            private static final long serialVersionUID = -7766829699556888699L;
        };
    }


    /**
     * Creates a new instance.
     *
     * @param code state value; must be a non-zero positive
     * @param name  state name; must not be null
     */
    public AbstractState(final int code, final String name) {
        super();

        if (name == null) {
            throw new NullPointerException("null name");
        }

        if (name.trim().length() == 0) {
            throw new NullPointerException("empty name");
        }

        this.code = code;
        this.name = name;
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AbstractState)) { // (null instanceof XXX) -> false
            return false;
        }

        final AbstractState state = (AbstractState) obj;

        if (code != state.getCode()) {
            return false;
        }

        if (!name.equals(state.getName())) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode = 37 * hashCode + code;

        hashCode = 37 * hashCode + name.hashCode();

        return hashCode;
    }


    @Override
    public String toString() {
        return "State(" + name + "(" + code + "))";
    }


    /**
     * Returns code.
     *
     * @return code
     */
    public final int getCode() {
        return code;
    }


    /**
     * Returns name.
     *
     * @return name
     */
    public final String getName() {
        return name;
    }


    /** value. */
    private final int code;


    /** name. */
    private final String name;
}

