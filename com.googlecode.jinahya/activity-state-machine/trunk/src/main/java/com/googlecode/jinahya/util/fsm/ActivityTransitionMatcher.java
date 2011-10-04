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


import android.app.Activity;

/**
 * Transition matchers.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public enum ActivityTransitionMatcher implements TransitionMatcher {


    /**
     * Matcher for the transition of
     * {@link Activity#onCreate(android.os.Bundle)}.
     */
    ON_CREATE {


        @Override
        public boolean matches(final Transition transition) {
            return transition.getSource().equals(State.UNKNOWN)
                   && transition.getTarget().equals(ActivityState.STOPPED);
        }
    },
    /**
     * 
     */
    ON_START {


        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSource().equals(ActivityState.STOPPED)
                    || transition.getSource().equals(ActivityState.RESTARTING))
                   && transition.getTarget().equals(ActivityState.PAUSED);
        }
    },
    /**
     * 
     */
    ON_RESUME {


        @Override
        public boolean matches(final Transition transition) {
            return transition.getSource().equals(ActivityState.STOPPED)
                   && transition.getTarget().equals(ActivityState.ACTIVE);
        }
    },
    /**
     * 
     */
    ON_PAUSE {


        @Override
        public boolean matches(final Transition transition) {
            return transition.getSource().equals(ActivityState.ACTIVE)
                   && transition.getTarget().equals(ActivityState.PAUSED);
        }
    },
    /**
     * 
     */
    ON_STOP {


        @Override
        public boolean matches(final Transition transition) {
            return transition.getSource().equals(ActivityState.PAUSED)
                   && transition.getTarget().equals(ActivityState.STOPPED);
        }
    },
    /**
     * 
     */
    ON_RESTART {


        @Override
        public boolean matches(final Transition transition) {
            return transition.getSource().equals(ActivityState.STOPPED)
                   && transition.getTarget().equals(ActivityState.RESTARTING);
        }
    },
    /**
     * 
     */
    ON_DESTROY {


        @Override
        public boolean matches(final Transition transition) {
            return transition.getSource().equals(ActivityState.STOPPED)
                   && transition.getTarget().equals(ActivityState.DESTROYED);
        }
    };
}

