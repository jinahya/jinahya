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

package xlet;


import jinahya.fsm.MachineException;

import jinahya.fsm.xlet.LoadSwitchTask;


/**
 * This task works for initXlet() and destroyXlet().
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class SimpleLoadTask extends LoadSwitchTask {


    //@Override
    public void on(int priority) throws MachineException {
        if (priority != 0) {
            return;
        }
        System.out.println("LOAD: START @ " + priority);
    }


    //@Override
    public void off(int priority) throws MachineException {
        if (priority != 0) {
            return;
        }
        System.out.println("LOAD: FINISH @ " + priority);
    }
}
