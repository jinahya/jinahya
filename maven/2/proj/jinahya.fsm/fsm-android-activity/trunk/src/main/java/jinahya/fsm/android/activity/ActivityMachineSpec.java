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
import jinahya.fsm.Transition;
import jinahya.fsm.TransitionMatcher;


/**
 *
 * @author <a href="mailto:jinahya@gmailcom">Jin Kwon</a>
 */
public class ActivityMachineSpec extends MachineSpec {


    /**
     * {@link jinahya.fsm.State#UNKNOWN} &#8594; {@link ActivityState#SUSPENDED}.
     *
     * @return 2
     */
    @Override
    public int getMaximumHistorySize() {
        return 0x02;
    }


    /**
     * Returns true if and only if {@link jinahya.fsm.State#UNKNOWN} &#8594;
     * {@link ActivityState#SUSPENDED}.
     *
     * @param transition {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isStartingTransition(final Transition transition) {
        return ActivityTransitionMatcher.ON_CREATE.matches(transition);
    }


    /**
     * {@inheritDoc}
     *
     * <ul>
     * <li>{@link jinahya.fsm.State#UNKNOWN} &#8594; {@link ActivityState#SUSPENDED}</li>
     * <li>{@link ActivityState#SUSPENDED} &#8596; {@link ActivityState#PAUSED}</li>
     * <li>{@link ActivityState#PAUSED} &#8596; {@link ActivityState#ACTIVE}</li>
     * <li>{@link ActivityState#SUSPENDED} &#8594; {@link ActivityState#DESTROYED}</li>
     * </ul>
     *
     * @param transition {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isTransitionAllowed(final Transition transition) {

        for (TransitionMatcher matcher : ActivityTransitionMatcher.values()) {
            if (matcher.matches(transition)) {
                return true;
            }
        }

        return false;
    }


    /**
     * {@link ActivityState#SUSPENDED} &#8594; {@link ActivityState#DESTROYED}.
     *
     * @param transition {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinishingTransition(final Transition transition) {
        return ActivityTransitionMatcher.ON_DESTROY.matches(transition);
    }
}
