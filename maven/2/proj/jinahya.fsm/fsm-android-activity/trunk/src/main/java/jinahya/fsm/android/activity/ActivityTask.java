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

package jinahya.fsm.android.activity;


import jinahya.fsm.SwitchTask;
import jinahya.fsm.TransitionMatcher;

import static jinahya.fsm.android.activity.ActivityTransitionMatcher.*;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class ActivityTask {


    /**
     *
     */
    public static abstract class BackgroundSwitchTask extends SwitchTask {


        /**
         * Creates a new instance.
         */
        public BackgroundSwitchTask() {
            super(new TransitionMatcher[] {
                    ON_START},
                new TransitionMatcher[] {
                    ON_STOP});
        }
    }


    /**
     *
     */
    public static abstract class ForegroundSwitchTask extends SwitchTask {


        /**
         * Creates a new instance.
         */
        public ForegroundSwitchTask() {
            super(new TransitionMatcher[] {
                    ON_RESUME},
                new TransitionMatcher[] {
                    ON_PAUSE});
        }
    }


    /**
     * 
     */
    public static abstract class SuspensionSwitchTask extends SwitchTask {


        /**
         * Creates a new instance.
         */
        public SuspensionSwitchTask() {
            super(new TransitionMatcher[] {
                    ON_CREATE},
                new TransitionMatcher[] {
                    ON_DESTROY});
        }
    }

}
