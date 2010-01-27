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

package jinahya.fsm.xlet;


import jinahya.fsm.MachineSpec;
import jinahya.fsm.State;
import jinahya.fsm.Transition;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletMachineSpec extends MachineSpec {


    //@Override
    public boolean isStartingTransition(final Transition transition) {
        // UNKNOWN -> (LOADED | PAUSED)
        return (transition.getSourceState() == State.UNKNOWN &&
                (transition.getTargetState() == XletState.LOADED ||
                 transition.getTargetState() == XletState.PAUSED));
    }


    //@Override
    public boolean isTransitionAllowed(final Transition transition) {

        boolean allowed = super.isTransitionAllowed(transition);

        final int targetState = transition.getTargetState();

        switch (transition.getSourceState()) {
            case State.UNKNOWN: // UNKNOWN -> (LOADED | PAUSED)
                allowed = (targetState == XletState.LOADED ||
                           targetState == XletState.PAUSED);
                break;
            case XletState.LOADED: // LOADED -> (PAUSED | DESTROYED)
                allowed = (targetState == XletState.PAUSED ||
                           targetState == XletState.DESTROYED);
                break;
            case XletState.PAUSED: // PAUSED -> (ACTIVE | DESTROYED)
                allowed = (targetState == XletState.ACTIVE ||
                           targetState == XletState.DESTROYED);
                break;
            case XletState.ACTIVE: // ACTIVE -> (PAUSED | DESTROYED)
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
        // (LOADED | PAUSED | ACTIVE) -> DESTROYED
        return ((transition.getSourceState() == XletState.LOADED ||
                 transition.getSourceState() == XletState.PAUSED ||
                 transition.getSourceState() == XletState.ACTIVE) &&
                transition.getTargetState() == XletState.DESTROYED);
    }
}
