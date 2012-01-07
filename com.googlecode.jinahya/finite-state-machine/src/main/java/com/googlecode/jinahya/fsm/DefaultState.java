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


/**
 * PRIVATE.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
class DefaultState implements State {


    /** GENERATED. */
    private static final long serialVersionUID = -1896863125474814276L;


    /**
     * Creates a new instance.
     *
     * @param code state code
     * @param name state name
     */
    public DefaultState(final int code, final String name) {
        super();

        if (name == null) {
            throw new NullPointerException("null name");
        }

        if (name.trim().length() == 0) {
            throw new IllegalArgumentException("empty name");
        }

        this.code = code;
        this.name = name;
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof State)) { // (null instanceof XXX) -> false
            return false;
        }

        final State casted = (State) obj;

        if (code != casted.code()) {
            return false;
        }

        if (!name.equals(casted.name())) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int result = 17;

        result = 37 * result + code;

        result = 37 * result + name.hashCode();

        return result;
    }


    @Override
    public String toString() {
        return super.toString() + "?code=" + code + "&name=" + name;
        //return "State(" + name + "(" + code + "))";
    }


    /**
     * Returns code.
     *
     * @return code
     */
    @Override
    public final int code() {
        return code;
    }


    /**
     * Returns name.
     *
     * @return name
     */
    @Override
    public final String name() {
        return name;
    }


    /** task code. */
    private final int code;


    /** task name. */
    private final String name;


}

