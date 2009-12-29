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


import jinahya.fsm.MachineSpec;
import jinahya.fsm.State;
import jinahya.fsm.Transition;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletMachineSpec implements MachineSpec {


    //@Override
    public boolean isStartingTransition(final Transition transition) {
        // UNKNOWN -> (LOADED | PAUSED)
        final int sourceState = transition.getSourceState();
        final int targetState = transition.getTargetState();
        return (sourceState == State.UNKNOWN &&
                (targetState == XletState.LOADED ||
                 targetState == XletState.PAUSED));
    }


    //@Override
    public boolean isTransitionAllowed(final Transition transition) {
        final int sourceState = transition.getSourceState();
        final int targetState = transition.getTargetState();

        boolean allowed = false;

        switch (sourceState) {
            case State.UNKNOWN: // UNKNOWN -> (LOADED | PAUSED)
                allowed = (targetState == XletState.LOADED ||
                           targetState == XletState.PAUSED);
                break;
            case XletState.LOADED: // LOADED -> (PAUSED | DESTROYED)
                allowed = (targetState == XletState.PAUSED ||
                           targetState == XletState.DESTROYED);
                break;
            case XletState.PAUSED: // PAUSED -> (STARTED | DESTROYED)
                allowed = (targetState == XletState.STARTED ||
                           targetState == XletState.DESTROYED);
                break;
            case XletState.STARTED: // STARTED -> (PAUSED | DESTROYED)
                allowed = (targetState == XletState.PAUSED ||
                           targetState == XletState.DESTROYED);
                break;
            default:
                break;
        }

        return allowed;
    }


    //@Override
    public boolean isFinishingTransition(final Transition transition) {
        // (LOADED | PAUSED | STARTED) -> DESTROYED
        final int sourceState = transition.getSourceState();
        final int targetState = transition.getTargetState();
        return ((sourceState == XletState.LOADED ||
                 sourceState == XletState.PAUSED ||
                 sourceState == XletState.STARTED) &&
                targetState == XletState.DESTROYED);
    }
}
