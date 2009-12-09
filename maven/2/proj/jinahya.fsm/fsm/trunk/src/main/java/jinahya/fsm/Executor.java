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
//final class Executor extends Thread {
final class Executor implements Runnable {


    /**
     *
     * @param service service
     * @param transition transition
     * @param priority priority
     */
    public Executor(final ExecutorService service, final Transition transition,
                    final int priority) {
        super();

        this.service = service;
        this.transition = transition;
        this.priority = priority;
    }


    //@Override
    public void run() {
        for (Task task = null; (task = service.getTask()) != null;) {
            try {
                task.perform(transition, priority);
            } catch (MachineException me) {
                me.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private ExecutorService service;
    private Transition transition;
    private int priority;
}
