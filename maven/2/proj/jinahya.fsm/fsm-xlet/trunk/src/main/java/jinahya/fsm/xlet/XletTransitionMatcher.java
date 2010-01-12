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


    /**
     * <code>UNKNOWN -> LOADED</code>.
     *
     * @see jinahya.fsm.State#UNKNOWN
     * @see XletState#LOADED
     */
    public static final TransitionMatcher LOAD_XLET = new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                return (transition.getSourceState() == State.UNKNOWN &&
                        transition.getTargetState() == XletState.LOADED);
            }
        };


    /**
     * <code>(UNKNOWN|LOADED) -> PAUSED</code>.
     *
     * @see jinahya.fsm.State#UNKNOWN
     * @see XletState#LOADED
     * @see XletState#PAUSED
     */
    public static final TransitionMatcher INIT_XLET = new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                final int sourceState = transition.getSourceState();
                final int targetState = transition.getTargetState();
                return ((sourceState == State.UNKNOWN ||
                         sourceState == XletState.LOADED) &&
                        targetState == XletState.PAUSED);
            }
        };



    /**
     * <code>PAUSED -> STARTED</code>.
     *
     * @see XletState#PAUSED
     * @see XletState#STARTED
     */
    public static final TransitionMatcher START_XLET = new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                return (transition.getSourceState() == XletState.PAUSED &&
                        transition.getTargetState() == XletState.ACTIVE);
            }
    };


    /**
     * <code>STARTED -> PAUSED</code>.
     *
     * @see XletState#STARTED
     * @see XletState#PAUSED
     */
    public static final TransitionMatcher PAUSE_XLET = new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                return (transition.getSourceState() == XletState.ACTIVE &&
                        transition.getTargetState() == XletState.PAUSED);
            }
    };


    /**
     * {@link XletState#LOADED} &#8594; {@link XletState#DESTROYED} ||
     * {@link XletState#PAUSED} &#8594; {@link XletState#DESTROYED} ||
     * {@link XletState#STARTED) &#8594; {@link XletState#DESTROYED}.
     *
     * @see XletState#LOADED
     * @see XletState#PAUSED
     * @see XletState#ACTIVE
     * @see XletState#DESTROYED
     */
    public static final TransitionMatcher DESTROY_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                return ((transition.getSourceState() == XletState.LOADED ||
                         transition.getSourceState() == XletState.PAUSED ||
                         transition.getSourceState() == XletState.ACTIVE) &&
                        transition.getTargetState() == XletState.DESTROYED);
            }
        };


    private XletTransitionMatcher() {
        super();
    }
}
