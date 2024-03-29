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


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * State transition of machines.
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

        final Transition casted = (Transition) obj;

        if (!source.equals(casted.getSource())) {
            return false;
        }

        if (!target.equals(casted.getTarget())) {
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
        return super.toString()
               + "?source=(" + source + ")&target=(" + target + ")";
    }


    /**
     * Checks if this transition matches any of given <code>matchers</code>.
     *
     * @param matchers matchers
     * @return true if matches any; false otherwise
     */
    public final boolean matchesAny(final TransitionMatcher... matchers) {

        if (matchers == null) {
            throw new NullPointerException("null matchers");
        }

        return matchesAny(Arrays.asList(matchers));
    }


    /**
     * Checks if this transition matches any of given <code>matchers</code>.
     *
     * @param matchers matchers
     * @return true if matches any; false otherwise
     */
    public final boolean matchesAny(final List<TransitionMatcher> matchers) {

        if (matchers == null) {
            throw new NullPointerException("null matchers");
        }

        for (TransitionMatcher matcher : matchers) {
            if (matcher.matches(this)) {
                return true;
            }
        }

        return false;
    }


    /**
     * checks if this transition matches all of given <code>matchers</code>.
     *
     * @param matchers matchers
     * @return true if matches all; false otherwise
     */
    public final boolean matchesAll(final TransitionMatcher... matchers) {

        if (matchers == null) {
            throw new NullPointerException("null matchers");
        }

        return matchesAll(Arrays.asList(matchers));
    }


    /**
     * Checks if this transition matches all of given <code>matchers</code>.
     *
     * @param matchers matchers
     * @return true if all matches; false otherwise
     */
    public final boolean matchesAll(final List<TransitionMatcher> matchers) {

        if (matchers == null) {
            throw new NullPointerException("null matchers");
        }

        for (TransitionMatcher matcher : matchers) {
            if (!matcher.matches(this)) {
                return false;
            }
        }

        return true;
    }


    /**
     * Returns property value mapped to given <code>name</code>.
     *
     * @param name property name
     * @return property value
     */
    public final Object getProperty(final String name) {

        if (name == null) {
            throw new NullPointerException("null name");
        }

        synchronized (properties) {
            return properties.get(name);
        }
    }


    /**
     * Sets property value mapped to given <code>name</code>.
     *
     * @param name property name
     * @param value property value
     * @return previous property value mapped to given <code>name</code>.
     */
    public final Object setProperty(final String name, final Object value) {

        if (name == null) {
            throw new NullPointerException("null name");
        }

        synchronized (properties) {
            return properties.put(name, value);
        }
    }


    /** old state. */
    private final State source;


    /** new state. */
    private final State target;


    /** properties. */
    private final Map<String, Object> properties =
        Collections.synchronizedMap(new HashMap<String, Object>());


}

