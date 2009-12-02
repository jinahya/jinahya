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

package jinahya.fsm.xlet;

import jinahya.fsm.State;
import jinahya.fsm.Transition;
import jinahya.fsm.TransitionMatcher;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class XletTransitionMatcher {


    public static final TransitionMatcher LOAD_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                final State sourceState = transition.getSourceState();
                final State targetState = transition.getTargetState();
                return sourceState.equals(State.UNKNOWN) &&
                       targetState.equals(XletState.LOADED);
            }
        };


    public static final TransitionMatcher INIT_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                final State sourceState = transition.getSourceState();
                final State targetState = transition.getTargetState();
                return ((sourceState.equals(State.UNKNOWN) ||
                         sourceState.equals(XletState.LOADED)) &&
                        targetState.equals(XletState.PAUSED));
            }
        };


    public static final TransitionMatcher START_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                final State sourceState = transition.getSourceState();
                final State targetState = transition.getTargetState();
                return sourceState.equals(XletState.PAUSED) &&
                       targetState.equals(XletState.STARTED);
            }
    };


    public static final TransitionMatcher PAUSE_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                final State sourceState = transition.getSourceState();
                final State targetState = transition.getTargetState();
                return sourceState.equals(XletState.STARTED) &&
                       targetState.equals(XletState.PAUSED);
            }
    };


    public static final TransitionMatcher DESTROY_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                final State sourceState = transition.getSourceState();
                final State targetState = transition.getTargetState();
                return ((sourceState.equals(XletState.PAUSED) ||
                         sourceState.equals(XletState.STARTED)) &&
                        targetState.equals(XletState.DESTROYED));
            }
        };


    private XletTransitionMatcher() {
        super();
    }
}
