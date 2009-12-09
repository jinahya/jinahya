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

package jinahya.fsm;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Task {


    /**
     * Initialize this task. Override this method if you have to allocate any
     * resources.
     *
     * @throws MachineException if any error occurs.
     */
    public void initialize() throws MachineException {
        // empty
    }


    /**
     * Performs the desired jobs.
     * <p>
     * This method invoked with 10 different <code>priority</code> values (0-9)
     *
     * @param transition transition
     * @param priority priority
     * @throws MachineException if any error occurs
     */
    public abstract void perform(Transition transition, int priority)
        throws MachineException;


    /**
     * Destroys this task. Override this method if you have to free any
     * resources.
     *
     * @throws MachineException if any error occurs.
     */
    public void destroy() throws MachineException {
        // empty
    }
}
