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
import jinahya.fsm.Machine;
import jinahya.fsm.Transition;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletMachineSpec implements MachineSpec {


    //@Override
    public boolean isStartingTransition(final Transition transition) {
        // UNKNOWN -> (LOADED | PAUSED)
        return (transition.getSourceState() == Machine.UNKNOWN
                && (transition.getTargetState() == XletMachine.LOADED
                    || transition.getTargetState() == XletMachine.PAUSED));
    }


    //@Override
    public boolean isTransitionAllowed(final Transition transition) {

        boolean allowed = false;

        final int targetState = transition.getTargetState();

        switch (transition.getSourceState()) {
            case Machine.UNKNOWN: // UNKNOWN -> (LOADED | PAUSED)
                allowed = (targetState == XletMachine.LOADED
                           || targetState == XletMachine.PAUSED);
                break;
            case XletMachine.LOADED: // LOADED -> (PAUSED | DESTROYED)
                allowed = (targetState == XletMachine.PAUSED
                           || targetState == XletMachine.DESTROYED);
                break;
            case XletMachine.PAUSED: // PAUSED -> (ACTIVE | DESTROYED)
                allowed = (targetState == XletMachine.ACTIVE
                           || targetState == XletMachine.DESTROYED);
                break;
            case XletMachine.ACTIVE: // ACTIVE -> (PAUSED | DESTROYED)
                allowed = (targetState == XletMachine.PAUSED
                           || targetState == XletMachine.DESTROYED);
                break;
            default:
                break;
        }

        return allowed;
    }


    //@Override
    public boolean isFinishingTransition(final Transition transition) {
        // (LOADED | PAUSED | ACTIVE) -> DESTROYED
        return ((transition.getSourceState() == XletMachine.LOADED
                 || transition.getSourceState() == XletMachine.PAUSED
                 || transition.getSourceState() == XletMachine.ACTIVE)
                && transition.getTargetState() == XletMachine.DESTROYED);
    }
}
