/*
 *  Copyright 2010 Jin Kwon.
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

package jinahya.fsm.xlet.task;


import jinahya.fsm.task.SwitchTask;
import jinahya.fsm.task.TransitionMatcher;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class PlaySwitchTask extends SwitchTask {


    /** Creates a new instance. */
    public PlaySwitchTask() {
        super(new TransitionMatcher[] {XletTransitionMatchers.START_XLET},
              new TransitionMatcher[] {XletTransitionMatchers.PAUSE_XLET,
                                       XletTransitionMatchers.DESTROY_XLET});
        }
}
