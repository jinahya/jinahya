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

package jinahya.fsm.android.activity;


import jinahya.fsm.MachineSpec;
import static jinahya.fsm.State.UNKNOWN;
import jinahya.fsm.Transition;

import static jinahya.fsm.android.activity.ActivityState.*;


/**
 *
 * @author <a href="mailto:jinahya@gmailcom">Jin Kwon</a>
 */
public class ActivityMachineSpec implements MachineSpec {


    /**
     * <code>UNKNOWN -> SUSPENDED</code>.
     *
     * @param transition {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isStartingTransition(final Transition transition) {
        return (transition.getSourceState() == UNKNOWN &&
                transition.getTargetState() == SUSPENDED);
    }


    /**
     * 
     * @param transition {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isTransitionAllowed(final Transition transition) {

        boolean allowed = false;

        final int targetState = transition.getTargetState();

        switch (transition.getSourceState()) {
            case UNKNOWN:
                allowed = (targetState == SUSPENDED);
                break;
            case SUSPENDED:
                allowed = (targetState == PAUSED);
                break;
            case PAUSED:
                allowed = (targetState == ACTIVE || targetState == STOPPED);
                break;
            case ACTIVE:
                allowed = (targetState == PAUSED);
                break;
            case STOPPED:
                allowed = (targetState == SUSPENDED ||
                           targetState == DESTROYED);
                break;
            default:
                break;
        }

        return allowed;
    }


    /**
     * <code>STOPPED -> DESTROYED</code>.
     *
     * @param transition {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinishingTransition(final Transition transition) {
        return (transition.getSourceState() == STOPPED &&
                transition.getTargetState() == DESTROYED);
    }
}
