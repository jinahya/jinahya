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


import jinahya.fsm.MachineSpec;
import jinahya.fsm.State;
import jinahya.fsm.Transition;


/**
 *
 * @author <a href="mailto:jinahya@gmailcom">Jin Kwon</a>
 */
public class ActivityMachineSpec implements MachineSpec {


    public boolean isStartingTransition(final Transition transition) {
        return (transition.getSourceState() == State.UNKNOWN &&
                transition.getTargetState() == ActivityState.LOADED);
    }


    public boolean isTransitionAllowed(final Transition transition) {

        boolean allowed = false;

        final int targetState = transition.getTargetState();

        switch (transition.getSourceState()) {
            case State.UNKNOWN:
                allowed = (targetState == ActivityState.LOADED);
                break;
            case ActivityState.LOADED:
                allowed = (targetState == ActivityState.PAUSED);
                break;
            case ActivityState.PAUSED:
                allowed = (targetState == ActivityState.ACTIVE ||
                           targetState == ActivityState.STOPPED);
                break;
            case ActivityState.ACTIVE:
                allowed = (targetState == ActivityState.PAUSED);
                break;
            case ActivityState.STOPPED:
                allowed = (targetState == ActivityState.LOADED ||
                           targetState == ActivityState.DESTROYED);
                break;
            default:
                break;
        }

        return allowed;
    }


    public boolean isFinishingTransition(final Transition transition) {
        return (transition.getSourceState() == ActivityState.STOPPED &&
                transition.getTargetState() == ActivityState.DESTROYED);
    }
}
