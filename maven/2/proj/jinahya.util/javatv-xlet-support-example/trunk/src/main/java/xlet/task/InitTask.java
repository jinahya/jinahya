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

package xlet.task;


import jinahya.util.javatv.xlet.JavaTVXletSpec;

import jinahya.util.state.StateMachineException;
import jinahya.util.state.StateMachineTask;


/**
 * This task works for initXlet() and destroyXlet().
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class InitTask implements StateMachineTask {

    //@Override
    public void perform(int previousState, int currentState, int priority)
        throws StateMachineException {

        if (previousState == JavaTVXletSpec.LOADED &&
            currentState == JavaTVXletSpec.PAUSED) {
            System.out.println("INIT_TASK: initXlet");
            resource = new int[1024];
            for (int i = 0; i < resource.length; i++) {
                resource[i] = i;
            }
        } else if (currentState == JavaTVXletSpec.DESTROYED) {
            System.out.println("INIT_TASK: destroyXlet");
            resource = null;
        } else {
            // not interested
        }
    }

    private int[] resource;
}
