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

package jinahya.fsm.xlet.task;


import jinahya.fsm.States;
import jinahya.fsm.Transition;
import jinahya.fsm.task.TransitionMatcher;
import jinahya.fsm.xlet.XletStates;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class XletTransitionMatchers {


    /**
     * A TransitionMatcher for [{@link States#UNKNOWN} &rarr;
     * {@link XletStates#LOADED}].
     *
     * @see States#UNKNOWN
     * @see XletStates#LOADED
     */
    public static final TransitionMatcher LOAD_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                return (transition.getSourceState() == States.UNKNOWN &&
                        transition.getTargetState() == XletStates.LOADED);
            }
        };


    /**
     * A transition matcher for [({@link States#UNKNOWN} ||
     * {@link XletStates#LOADED}) &rarr; {@link XletStates#PAUSED}].
     *
     * @see States#UNKNOWN
     * @see XletStates#LOADED
     * @see XletStates#PAUSED
     */
    public static final TransitionMatcher INIT_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                final int sourceState = transition.getSourceState();
                final int targetState = transition.getTargetState();
                return ((sourceState == States.UNKNOWN ||
                         sourceState == XletStates.LOADED) &&
                        targetState == XletStates.PAUSED);
            }
        };



    /**
     * A transition matcher for [{@link XletStates#PAUSED} &rarr;
     * {@link XletStates#ACTIVE}].
     *
     * @see XletStates#PAUSED
     * @see XletStates#ACTIVE
     */
    public static final TransitionMatcher START_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                return (transition.getSourceState() == XletStates.PAUSED &&
                        transition.getTargetState() == XletStates.ACTIVE);
            }
    };


    /**
     * A transition matcher for [{@link XletStates#ACTIVE} &rarr;
     * {@link XletStates#PAUSED}].
     *
     * @see XletStates#ACTIVE
     * @see XletStates#PAUSED
     */
    public static final TransitionMatcher PAUSE_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                return (transition.getSourceState() == XletStates.ACTIVE &&
                        transition.getTargetState() == XletStates.PAUSED);
            }
    };


    /**
     * A transition matcher for [({@link XletStates#LOADED} ||
     * {@link XletStates#PAUSED} || {@link XletStates#ACTIVE}) &rarr;
     * {@link XletStates#DESTROYED}].
     *
     * @see XletStates#LOADED
     * @see XletStates#PAUSED
     * @see XletStates#ACTIVE
     * @see XletStates#DESTROYED
     */
    public static final TransitionMatcher DESTROY_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                return ((transition.getSourceState() == XletStates.LOADED ||
                         transition.getSourceState() == XletStates.PAUSED ||
                         transition.getSourceState() == XletStates.ACTIVE) &&
                        transition.getTargetState() == XletStates.DESTROYED);
            }
        };


    /** . */
    private XletTransitionMatchers() {
        super();
    }
}
