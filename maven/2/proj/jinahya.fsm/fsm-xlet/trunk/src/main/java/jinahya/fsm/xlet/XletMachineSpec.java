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
import jinahya.fsm.MachineSpec;
import jinahya.fsm.Transition;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletMachineSpec implements MachineSpec {


    //@Override
    public boolean isStartingTransition(final Transition transition) {
        // UNKNOWN -> (LOADED | PAUSED)
        final State sourceState = transition.getSourceState();
        final State targetState = transition.getTargetState();
        return (sourceState.equals(State.UNKNOWN) &&
                targetState.equals(XletState.LOADED)) ||
               (sourceState.equals(State.UNKNOWN) &&
                targetState.equals(XletState.PAUSED));
    }


    //@Override
    public boolean isTransitionAllowed(final Transition transition) {
        final State sourceState = transition.getSourceState();
        final State targetState = transition.getTargetState();
        if (sourceState.equals(State.UNKNOWN)) {
            // UNKNOWN -> (LOADED | PAUSED)
            return (targetState.equals(XletState.LOADED) ||
                    targetState.equals(XletState.PAUSED));
        } else if (sourceState.equals(XletState.LOADED)) {
            // LOADED -> (PAUSED | DESTROYED)
            return (targetState.equals(XletState.PAUSED) ||
                    targetState.equals(XletState.DESTROYED));
        } else if (sourceState.equals(XletState.PAUSED)) {
            // PAUSED -> (STARTED | DESTROYED)
            return (targetState.equals(XletState.STARTED) ||
                    targetState.equals(XletState.DESTROYED));
        } else if (sourceState.equals(XletState.STARTED)) {
            // STARTED -> (PAUSED | DESTROYED)
            return (targetState.equals(XletState.PAUSED) ||
                    targetState.equals(XletState.DESTROYED));
        }

        return false;
    }


    //@Override
    public boolean isFinishingTransition(final Transition transition) {
        // (LOADED | PAUSED | STARTED) -> DESTROYED
        final State sourceState = transition.getSourceState();
        final State targetState = transition.getTargetState();
        return ((sourceState.equals(XletState.LOADED) ||
                 sourceState.equals(XletState.PAUSED) ||
                 sourceState.equals(XletState.STARTED)) &&
                targetState.equals(XletState.DESTROYED));
    }
}
