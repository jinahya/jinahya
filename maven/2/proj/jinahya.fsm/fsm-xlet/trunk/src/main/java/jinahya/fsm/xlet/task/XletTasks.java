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


import jinahya.fsm.task.TransitionMatcher;
import jinahya.fsm.task.SwitchTask;
import jinahya.fsm.task.SimpleTask;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class XletTasks {


    /**
     * A task executes for {@link XletTransitionMatchers#LOAD_XLET}.
     */
    public abstract static class LoadTask extends SimpleTask {

        /** Creates a new instance. */
        public LoadTask() {
            super(new TransitionMatcher[] {XletTransitionMatchers.LOAD_XLET});
        }
    }


    /**
     * A switch task turns on {@link XletTransitionMatchers#LOAD_XLET} and turns
     * off {@link XletTransitionMatchers#DESTROY_XLET}.
     */
    public abstract static class LoadSwitchTask extends SwitchTask {

        /** Creates a new instance. */
        public LoadSwitchTask() {
            super(new TransitionMatcher[] {XletTransitionMatchers.LOAD_XLET},
                  new TransitionMatcher[] {XletTransitionMatchers.DESTROY_XLET});
        }
    }


    /**
     * A simple task executes for {@link XletTransitionMatchers#INIT_XLET}.
     */
    public abstract static class InitTask extends SimpleTask {

        /** Creates an instance. */
        public InitTask() {
            super(new TransitionMatcher[] {
                      XletTransitionMatchers.INIT_XLET});
        }
    }


    /**
     * A switch task turns on {@link XletTransitionMatchers#INIT_XLET} and turns
     * off {@link XletTransitionMatchers#DESTROY_XLET}.
     */
    public abstract static class InitSwitchTask extends SwitchTask {

        /** Creates a new instance. */
        public InitSwitchTask() {
            super(new TransitionMatcher[] {
                      XletTransitionMatchers.INIT_XLET},
                  new TransitionMatcher[] {
                      XletTransitionMatchers.DESTROY_XLET});
        }
    }


    /**
     * A simple task executes for {@link XletTransitionMatchers#START_XLET}.
     */
    public abstract static class PlayTask extends SimpleTask {

        /** Creates a new instance. */
        public PlayTask() {
            super(new TransitionMatcher[] {XletTransitionMatchers.START_XLET});
        }
    }


    /**
     * A switch task turns on for
     * {@link XletTransitionMatchers#START_XLET} and turned off for
     * {@link XletTransitionMatchers#PAUSE_XLET} or
     * {@link XletTransitionMatchers#DESTROY_XLET}.
     */
    public abstract static class PlaySwitchTask extends SwitchTask {

        /** Creates a new instance. */
        public PlaySwitchTask() {
            super(new TransitionMatcher[] {
                      XletTransitionMatchers.START_XLET},
                  new TransitionMatcher[] {
                      XletTransitionMatchers.PAUSE_XLET,
                      XletTransitionMatchers.DESTROY_XLET});
        }
    }


    /**
     * .
     */
    private XletTasks() {
        super();
    }
}
