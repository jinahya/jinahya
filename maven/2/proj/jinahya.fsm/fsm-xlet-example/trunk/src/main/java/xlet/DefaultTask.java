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
import jinahya.fsm.Task;
import jinahya.fsm.Transition;



/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DefaultTask extends Task {


    //@Override
    public void perform(final Transition transition, final int priority)
        throws MachineException {

        /*
        if (priority != 0) {
            return;
        }
         */

        try {
            Thread.sleep(10L);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        //System.out.println(transition + " @ " + priority);
        jinahya.fsm.State[] history = transition.getTransitionHistory();
        for (int i = 0; i < history.length; i++) {
            System.out.println("[" + i + "]: " + history[i]);
        }
    }
}
