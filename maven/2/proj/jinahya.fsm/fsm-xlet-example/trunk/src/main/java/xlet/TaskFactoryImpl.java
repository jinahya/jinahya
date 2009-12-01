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


import java.util.Vector;

import jinahya.fsm.StateMachineException;
import jinahya.fsm.Task;
import jinahya.fsm.TaskFactory;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class TaskFactoryImpl implements TaskFactory {

    //@Override
    public Task[] createTasks() throws StateMachineException {
        Vector vector = new Vector();
        for (int i = 0; i < 20; i++) {
            vector.addElement(new DefaultTask());
        }
        vector.addElement(new SimpleLoadTask());
        vector.addElement(new SimpleInitTask());
        vector.addElement(new SimplePlayTask());

        return (Task[]) vector.toArray(new Task[vector.size()]);
    }
}
