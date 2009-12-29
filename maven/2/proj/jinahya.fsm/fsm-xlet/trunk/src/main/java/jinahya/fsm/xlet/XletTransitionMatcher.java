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
     * @see jinahya.fsm.xlet.XletState#LOADED
     */
    public static final TransitionMatcher LOAD_XLET =
        new TransitionMatcher() {
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
     * @see jinahya.fsm.xlet.XletState#LOADED
     * @see jinahya.fsm.xlet.XletState#PAUSED
     */
    public static final TransitionMatcher INIT_XLET =
        new TransitionMatcher() {
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
     * @see jinahya.fsm.xlet.XletState#PAUSED
     * @see jinahya.fsm.xlet.XletState#STARTED
     */
    public static final TransitionMatcher START_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                return (transition.getSourceState() == XletState.PAUSED &&
                        transition.getTargetState() == XletState.STARTED);
            }
    };


    /**
     * <code>STARTED -> PAUSED</code>.
     *
     * @see jinahya.fsm.xlet.XletState#STARTED
     * @see jinahya.fsm.xlet.XletState#PAUSED
     */
    public static final TransitionMatcher PAUSE_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                final int sourceState = transition.getSourceState();
                return (sourceState == XletState.STARTED &&
                        transition.getTargetState() == XletState.PAUSED);
            }
    };


    /**
     * <code>(LOADED|PAUSED|STARTED) -> DESTROYED</code>.
     *
     * @see jinahya.fsm.xlet.XletState#LOADED
     * @see jinahya.fsm.xlet.XletState#PAUSED
     * @see jinahya.fsm.xlet.XletState#STARTED
     * @see jinahya.fsm.xlet.XletState#DESTROYED
     */
    public static final TransitionMatcher DESTROY_XLET =
        new TransitionMatcher() {
            //@Override
            public boolean matches(final Transition transition) {
                final int sourceState = transition.getSourceState();
                final int targetState = transition.getTargetState();
                return ((sourceState == XletState.LOADED ||
                         sourceState == XletState.PAUSED ||
                         sourceState == XletState.STARTED) &&
                        targetState == XletState.DESTROYED);
            }
        };


    private XletTransitionMatcher() {
        super();
    }
}
