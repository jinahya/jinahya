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


import java.util.Arrays;
import java.util.List;


/**
 * Transition matchers.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletTransitionMatcher {


    /**
     * Transition matcher for Xlet loading.
     */
    public static final TransitionMatcher LOAD_XLET = new TransitionMatcher() {


        @Override
        public boolean matches(final Transition transition) {
            return transition.getSource().equals(State.UNKNOWN)
                   && transition.getTarget().equals(XletState.PAUSED);
        }
    };


    /**
     * Transition matcher for <code>initXlet</code>.
     */
    public static final TransitionMatcher INIT_XLET = new TransitionMatcher() {


        @Override
        public boolean matches(final Transition transition) {
            return ((transition.getSource().equals(State.UNKNOWN)
                     || transition.getSource().equals(XletState.LOADED))
                    && transition.getTarget().equals(XletState.PAUSED));
        }
    };


    /**
     * Transition matcher for <code>startXlet</code>.
     */
    public static final TransitionMatcher START_XLET = new TransitionMatcher() {


        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSource().equals(XletState.PAUSED)
                    && transition.getTarget().equals(XletState.ACTIVE));
        }
    };


    /**
     * Transition matcher for <code>pauseXlet</code>.
     */
    public static final TransitionMatcher PAUSE_XLET = new TransitionMatcher() {


        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSource().equals(XletState.ACTIVE)
                    && transition.getTarget().equals(XletState.PAUSED));
        }
    };


    /**
     * Transition matcher for <code>destroyXlet</code>.
     */
    public static final TransitionMatcher DESTROY_XLET =
        new TransitionMatcher() {


            @Override
            public boolean matches(final Transition transition) {
                return ((transition.getSource().equals(XletState.LOADED)
                         || transition.getSource().equals(XletState.PAUSED)
                         || transition.getSource().equals(XletState.ACTIVE))
                        && transition.getTarget().equals(XletState.DESTROYED));
            }
        };


    /**
     * 
     * @return 
     */
    public static TransitionMatcher[] values() {
        return new TransitionMatcher[]{
                LOAD_XLET, INIT_XLET, START_XLET, PAUSE_XLET, DESTROY_XLET
            };
    }


    /**
     * 
     */
    public static List<TransitionMatcher> VALUES = Arrays.asList(values());


    protected XletTransitionMatcher() {
        super();
    }
}

