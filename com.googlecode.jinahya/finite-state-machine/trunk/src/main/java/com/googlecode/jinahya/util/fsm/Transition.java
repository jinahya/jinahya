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
public class Transition {


    /**
     * Creates a new instance.
     *
     * @param source source state
     * @param target target state
     */
    public Transition(final State source, final State target) {

        super();

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (target == null) {
            throw new NullPointerException("null target");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException("source is equlas to target");
        }

        this.source = source;
        this.target = target;
    }


    /**
     * Returns the source state.
     *
     * @return source state
     */
    public final State getSource() {
        return source;
    }


    /**
     * Returns the target state.
     *
     * @return target state
     */
    public final State getTarget() {
        return target;
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Transition)) { // (null instanceof XXX) -> false
            return false;
        }

        final Transition transition = (Transition) obj;

        if (!source.equals(transition.getSource())) {
            return false;
        }

        if (!target.equals(transition.getTarget())) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode = 37 * hashCode + source.hashCode();

        hashCode = 37 * hashCode + target.hashCode();

        return hashCode;
    }


    @Override
    public String toString() {
        return "Transition[" + source + "->" + target + "]";
    }


    /** old state. */
    private final State source;


    /** new state. */
    private final State target;
}

