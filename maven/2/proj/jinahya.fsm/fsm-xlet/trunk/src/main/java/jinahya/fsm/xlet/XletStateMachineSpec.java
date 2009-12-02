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
import jinahya.fsm.StateMachineSpec;
import jinahya.fsm.Transition;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletStateMachineSpec implements StateMachineSpec {


    //@Override
    public boolean isStartingTransition(final Transition transition) {
        // UNKNOWN -> LOADED || LOADED -> PAUSED | UNKNOWN -> PAUSED
        final State sourceState = transition.getSourceState();
        final State targetState = transition.getTargetState();
        return (sourceState.equals(State.UNKNOWN) &&
                targetState.equals(XletState.LOADED)) ||
                /*
               (sourceState.equals(XletState.LOADED) &&
                targetState.equals(XletState.PAUSED)) ||
                 */
               (sourceState.equals(State.UNKNOWN) &&
                targetState.equals(XletState.PAUSED));
    }


    //@Override
    public boolean isTransitionAllowed(final Transition transition) {
        final State sourceState = transition.getSourceState();
        final State targetState = transition.getTargetState();
        if (sourceState.equals(State.UNKNOWN)) {
            return (targetState.equals(XletState.LOADED) ||
                    targetState.equals(XletState.PAUSED));
        //} else if (sourceState.equals(XletState.NOT_LOADED)) {
            // not gonna happen
        } else if (sourceState.equals(XletState.LOADED)) {
            return targetState.equals(XletState.PAUSED);
        } else if (sourceState.equals(XletState.PAUSED)) {
            return (targetState.equals(XletState.STARTED) ||
                    targetState.equals(XletState.DESTROYED));
        } else if (sourceState.equals(XletState.STARTED)) {
            return (targetState.equals(XletState.PAUSED) ||
                    targetState.equals(XletState.DESTROYED));
        //} else if (sourceState.equals(XletState.DESTROYED)) {
            // not gonnna happen
        //} else if (sourceState.equals(XletState.INVALID)) {
            // not gonna happen
        //} else {
            // @@?
        }

        return false;
    }


    //@Override
    public boolean isFinishingTransition(final Transition transition) {
        // PAUSED -> DESTROYED | STARTED -> DESTROYED
        final State sourceState = transition.getSourceState();
        final State targetState = transition.getTargetState();
        return (sourceState.equals(XletState.PAUSED) &&
                targetState.equals(XletState.DESTROYED)) ||
               (sourceState.equals(XletState.STARTED) &&
                targetState.equals(XletState.DESTROYED));
    }
}
