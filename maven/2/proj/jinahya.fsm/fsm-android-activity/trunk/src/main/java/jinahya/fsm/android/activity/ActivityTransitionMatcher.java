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
     * <code>UNKNOWN -> SUSPENDED</code>.
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
            return (transition.getSourceState() == SUSPENDED &&
                    transition.getTargetState() == PAUSED);
        }
    },


    /**
     * <code>PAUSED -> ACTIVE</code>.
     */
    ON_RESUME {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == PAUSED &&
                    transition.getTargetState() == ACTIVE);
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
                    transition.getTargetState() == STOPPED);
        }
    },


    /**
     * <code>STOPPED -> DESTROYED</code>.
     */
    ON_DESTROY {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == STOPPED &&
                    transition.getTargetState() == DESTROYED);
        }
    },


    /**
     * <code>STOPPED -> SUSPENDED</code>.
     */
    ON_RESTART {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == STOPPED &&
                    transition.getTargetState() == SUSPENDED);
        }
    },


    /**
     * <code>UNKNOWN -> SUSPENDED || STOPPED -> SUSPENDED</code>.
     */
    _SUSPENDED {
        @Override
        public boolean matches(final Transition transition) {
            return (ON_CREATE.matches(transition) ||
                    ON_RESTART.matches(transition));
            /*
            final int sourceState = transition.getSourceState();
            final int targetState = transition.getTargetState();
            return (sourceState == UNKNOWN && targetState == SUSPENDED) ||
                   (sourceState == STOPPED && targetState == SUSPENDED);
             */
        }
    },


    /**
     * <code>SUSPENDED -> PAUSED || ACTIVE -> PAUSED</code>.
     */
    _PAUSED {
        @Override
        public boolean matches(final Transition transition) {
            return ON_START.matches(transition) || ON_PAUSE.matches(transition);
            /*
            final int sourceState = transition.getSourceState();
            final int targetState = transition.getTargetState();
            return (sourceState == SUSPENDED && targetState == PAUSED) ||
                   (sourceState == ACTIVE && targetState == PAUSED);
             */
        }
    },


    /**
     * <code>PAUSED -> ACTIVE</code>.
     */
    _ACTIVE {
        @Override
        public boolean matches(final Transition transition) {
            return ON_RESUME.matches(transition);
            /*
            final int sourceState = transition.getSourceState();
            final int targetState = transition.getTargetState();
            return (sourceState == PAUSED && targetState == ACTIVE);
             */
        }
    },


    /**
     * <code>PAUSED -> STOPPED</code>.
     */
    _STOPPED {
        @Override
        public boolean matches(final Transition transition) {
            return ON_STOP.matches(transition);
            /*
            final int sourceState = transition.getSourceState();
            final int targetState = transition.getTargetState();
            return (sourceState == PAUSED && targetState == STOPPED);
             */
        }
    },


    /**
     * <code>STOPPED -> DESTROYED</code>.
     */
    _DESTROYED {
        @Override
        public boolean matches(final Transition transition) {
            final int sourceState = transition.getSourceState();
            final int targetState = transition.getTargetState();
            return (sourceState == STOPPED && targetState == DESTROYED);
        }
    };

}
