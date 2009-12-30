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
 *  under the License.
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
//public final class ActivityTransitionMatcher {
public enum ActivityTransitionMatcher implements TransitionMatcher {



    ON_CREATE {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == UNKNOWN &&
                    transition.getTargetState() == LOADED);
        }
    },
    ON_START {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == LOADED &&
                    transition.getTargetState() == PAUSED);
        }
    },
    ON_RESUME {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == PAUSED &&
                    transition.getTargetState() == ACTIVE);
        }
    },
    ON_PAUSE {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == ACTIVE &&
                    transition.getTargetState() == PAUSED);
        }
    },
    ON_STOP {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == PAUSED &&
                    transition.getTargetState() == STOPPED);
        }
    },
    ON_DESTROY {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == STOPPED &&
                    transition.getTargetState() == DESTROYED);
        }
    },
    ON_RESTART {
        @Override
        public boolean matches(final Transition transition) {
            return (transition.getSourceState() == DESTROYED &&
                    transition.getTargetState() == LOADED);
        }
    };
}
