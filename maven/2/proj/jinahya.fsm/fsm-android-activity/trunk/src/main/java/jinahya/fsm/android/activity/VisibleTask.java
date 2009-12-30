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


import jinahya.fsm.MachineException;
import jinahya.fsm.Task;
import jinahya.fsm.Transition;

import static jinahya.fsm.android.activity.ActivityTransitionMatcher.*;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class VisibleTask extends Task {


    @Override
    public void perform(final Transition transition, final int precedence)
        throws MachineException {

        if (ON_START.matches(transition)) {
            perform(precedence);
        }
    }


    /**
     * Performs when xlet <code>onResume()</code> method invoked.
     *
     * @param precedence task precedence
     * @throws MachineException if any error occurs
     */
    protected abstract void perform(int precedence) throws MachineException;
}
