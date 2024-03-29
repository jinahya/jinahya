/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.util.fsm;


/**
 * A switch task for background states.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class ActivityBackgroundSwitchTask extends SwitchTask {


    /**
     * Creates a new instance.
     *
     * @param id task id
     */
    public ActivityBackgroundSwitchTask(final String id) {
        super(id, new TransitionMatcher[]{ActivityTransitionMatcher.ON_START},
              new TransitionMatcher[]{ActivityTransitionMatcher.ON_STOP});
    }
}

