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
 * Task for simple conditions.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class SimpleTask extends Task {


    /**
     * Creates a new instance.
     *
     * @param id task id
     * @param matchers matchers; not null; not empty
     */
    public SimpleTask(final String id, final TransitionMatcher[] matchers) {

        super(id);

        if (matchers == null) {
            throw new NullPointerException("null matchers");
        }

        if (matchers.length == 0) {
            throw new NullPointerException("empty matchers");
        }

        this.matchers = matchers;
    }


    /**
     * Checks if given <code>transition</code> matches any of matchers.
     * 
     * @param transition transition to check
     * @return true if <code>transition</code> matches any of matchers.
     */
    protected boolean matchesAny(final Transition transition) {

        if (transition == null) {
            throw new NullPointerException("null transition");
        }

        return transition.matchesAny(matchers);
    }


    /**
     * Checks if given <code>transition</code> matches all of matchers.
     *
     * @param transition transition to check
     * @return true if <code>transition</code> matches all of matchers.
     */
    protected boolean matchesAll(final Transition transition) {

        if (transition == null) {
            throw new NullPointerException("null transition");
        }

        return transition.matchesAll(matchers);
    }


    /** matchers for switching on. */
    private final TransitionMatcher[] matchers;
}

