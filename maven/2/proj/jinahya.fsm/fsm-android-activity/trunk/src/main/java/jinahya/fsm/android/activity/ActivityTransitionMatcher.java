/*
 *  Copyright 2009 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.fsm.android.activity;


import static jinahya.fsm.State.UNKNOWN;
import jinahya.fsm.Transition;
import jinahya.fsm.TransitionMatcher;

import static jinahya.fsm.android.activity.ActivityState.*;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public enum ActivityTransitionMatcher implements TransitionMatcher {



    /**
     * {@link jinahya.fsm.State#UNKNOWN} &#8594;
     * {@link ActivityState#SUSPENDED}.
     */
    ON_CREATE {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == UNKNOWN &&
                    transition.getTargetState() == SUSPENDED);
        }
    },


    /**
     * <code>SUSPENDED -> PAUSED</code>.
     */
    ON_START {
        @Override
        public boolean matches(final Transition transition) {

            if (!(transition.getSourceState() == SUSPENDED &&
                  transition.getTargetState() == PAUSED)) {

                return false;
            }

            boolean matches = false;

            switch (transition.getPreviousStateAt(0)) {
                case UNKNOWN:
                    // following onCreate()
                    matches = Boolean.TRUE.booleanValue();
                    break;
                case SUSPENDED:
                    // following onRestart()
                    matches = transition.getPreviousStateAt(1) == SUSPENDED;
                    break;
                default:
                    break;
            }

            return matches;
        }
    },


    /**
     * <code>PAUSED -> ACTIVE</code>.
     */
    ON_RESUME {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == PAUSED &&
                    transition.getTargetState() == ACTIVE &&
                    transition.getPreviousStateAt(0) == SUSPENDED);
        }
    },


    /**
     * <code>ACTIVE -> PAUSED</code>.
     */
    ON_PAUSE {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == ACTIVE &&
                    transition.getTargetState() == PAUSED);
        }
    },


    /**
     * <code>PAUSED -> STOPPED</code>.
     */
    ON_STOP {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == PAUSED &&
                    transition.getTargetState() == SUSPENDED &&
                    transition.getPreviousStateAt(0) == ACTIVE);
        }
    },


    /**
     * <code>STOPPED -> DESTROYED</code>.
     */
    ON_DESTROY {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == SUSPENDED &&
                    transition.getTargetState() == DESTROYED &&
                    transition.getPreviousStateAt(0) == PAUSED);
        }
    },


    /**
     * <code>STOPPED -> SUSPENDED</code>.
     */
    ON_RESTART {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == SUSPENDED &&
                    transition.getTargetState() == SUSPENDED &&
                    transition.getPreviousStateAt(0) == PAUSED);
        }
    };
}
