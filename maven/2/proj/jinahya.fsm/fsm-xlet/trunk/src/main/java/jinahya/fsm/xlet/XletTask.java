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

package jinahya.fsm.xlet;


import jinahya.fsm.TransitionMatcher;
import jinahya.fsm.task.SwitchTask;
import jinahya.fsm.task.SimpleTask;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class XletTask {


    /**
     * 
     */
    public abstract static class LoadTask extends SimpleTask {

        /**
         * Creates a new instance.
         */
        public LoadTask() {
            super(new TransitionMatcher[] {XletTransitionMatcher.LOAD_XLET});
        }
    }



    /**
     * 
     */
    public abstract static class LoadSwitchTask extends SwitchTask {


        /**
         * Creates a new instance.
         */
        public LoadSwitchTask() {
            super(new TransitionMatcher[] {XletTransitionMatcher.LOAD_XLET},
                  new TransitionMatcher[] {XletTransitionMatcher.DESTROY_XLET});
        }
    }


    /**
     *
     */
    public abstract static class InitTask extends SimpleTask {

        public InitTask() {
            super(new TransitionMatcher[] {XletTransitionMatcher.INIT_XLET});
        }
    }


    /**
     *
     */
    public abstract static class InitSwitchTask extends SwitchTask {


        /**
         * Creates a new instance.
         */
        public InitSwitchTask() {
            super(new TransitionMatcher[] {XletTransitionMatcher.INIT_XLET},
                  new TransitionMatcher[] {XletTransitionMatcher.DESTROY_XLET});
        }
    }


    /**
     * Task performed when xlet's state changes to ACTIVE.
     */
    public abstract static class PlayTask extends SimpleTask {

        /**
         * Creates a new instance.
         */
        public PlayTask() {
            super(new TransitionMatcher[] {XletTransitionMatcher.START_XLET});
        }
    }


    /**
     *
     */
    public abstract static class PlaySwitchTask extends SwitchTask {


        /**
         * Creates a new instance.
         */
        public PlaySwitchTask() {
            super(new TransitionMatcher[] {
                      XletTransitionMatcher.START_XLET},
                  new TransitionMatcher[] {
                      XletTransitionMatcher.PAUSE_XLET,
                      XletTransitionMatcher.DESTROY_XLET});
        }
    }


    /**
     *
     */
    private XletTask() {
        super();
    }
}
