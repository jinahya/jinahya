/*
 *  Copyright 2009 onacit.
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

package jinahya.util.javatv.xlet.task;


import jinahya.util.javatv.xlet.JavaTVXletSpec;
import jinahya.util.state.StateMachineException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class PlayLevelTask extends AbstractTask {


    //@Override
    public void perform(final int previousState, final int currentState,
                        final int priority)
        throws StateMachineException {

         if (currentState == JavaTVXletSpec.STARTED) {
            start(priority);
        }

         if (previousState == JavaTVXletSpec.STARTED &&
             currentState == JavaTVXletSpec.PAUSED) {

             finish(priority);
        }

        if (currentState == JavaTVXletSpec.DESTROYED) {
            finish(priority);
        }
    }
}
