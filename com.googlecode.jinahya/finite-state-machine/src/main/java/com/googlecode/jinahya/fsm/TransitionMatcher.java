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
 * Interface for matching a transition.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface TransitionMatcher {


    /**
     * A TransitionMatcher simply matches.
     */
    public static final TransitionMatcher YES = new TransitionMatcher() {


        @Override
        public boolean matches(final Transition transition) {

            if (transition == null) {
                throw new NullPointerException("null transition");
            }

            return true;
        }


    };


    /**
     * A TransitionMatcher simply doesn't match.
     */
    public static final TransitionMatcher NO = new TransitionMatcher() {


        @Override
        public boolean matches(final Transition transition) {

            if (transition == null) {
                throw new NullPointerException("null transition");
            }

            return false;
        }


    };


    /**
     * Matches given <code>transition</code>. A
     * <code>NullPointerException</code> will be thrown if specified
     * <code>transition</code> is null.
     *
     * @param transition the transition to match
     * @return true if matches; false otherwise
     */
    boolean matches(Transition transition);


}

