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
import jinahya.fsm.SwitchTask;
import jinahya.fsm.Transition;
import jinahya.fsm.TransitionMatcher;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class InitTask extends SwitchTask {


    /**
     *
     */
    public InitTask() {
        super(new TransitionMatcher() {
            //@Override
            public boolean matches(Transition transition) {
                return
                    ((transition.getSourceState().equals(State.UNKNOWN) ||
                      transition.getSourceState().equals(XletState.LOADED)) &&
                     transition.getTargetState().equals(XletState.PAUSED));
            }
        }, new TransitionMatcher() {
            //@Override
            public boolean matches(Transition transition) {
                return transition.getTargetState().equals(XletState.DESTROYED);
            }
        });
    }
}
