/*
 *  Copyright 2010 onacit.
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

package jinahya.fsm.task;


import jinahya.fsm.MachineException;
import jinahya.fsm.Transition;
import jinahya.fsm.TransitionMatcher;
import jinahya.fsm.event.TransitionEvent;
import jinahya.fsm.event.TransitionEventListener;


/**
 * Represents a execution unit.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Task
    implements TransitionEventListener, TransitionMatcher {


    /**
     * Transition notification callback.
     *
     * @param event event
     */
    //@Override
    public final void transited(TransitionEvent event) {
        final Transition transition = event.getTransition();
        if (matches(transition)) {
            try {
                perform(transition);
            } catch (MachineException me) {
                me.printStackTrace();
            }
        }
    }


    /**
     * Do any desired jobs.
     *
     * @param transition transition
     * @throws MachineException if any error occurs
     */
    protected abstract void perform(Transition transition)
        throws MachineException;
}
