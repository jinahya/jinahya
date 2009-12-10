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


import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
final class ExecutorService implements Runnable {


    /**
     *
     * @param parent
     * @param tasks
     * @param priority
     * @param poolSize
     * @param poolSleep
     */
    public ExecutorService(final Thread parent, final Task[] tasks,
                           final Transition transition, final int priority,
                           final int poolSize, final long poolSleep) {
        super();

        this.parent = parent;
        this.tasks = tasks;
        this.transition = transition;
        this.priority = priority;
        this.poolSize = poolSize;
        this.poolSleep = poolSleep;
    }


    //@Override
    public void run() {

        if (parent != null) {
            try {
                parent.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                return;
            }
        }

        Vector executors = new Vector();
        for (int i = 0; i < poolSize; i++) {
            Thread executor = new Thread() {
                //@Override
                public void run() {
                    for (Task task = null; (task = getTask()) != null;) {
                        try {
                            Thread.sleep(poolSleep);
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                        try {
                            task.perform(transition, priority);
                        } catch (MachineException me) {
                            me.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            executors.addElement(executor);
            executor.start();
        }

        while (!executors.isEmpty()) {
            try {
                ((Thread) executors.firstElement()).join();
                executors.removeElementAt(0);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                break;
            }
        }
    }


    private synchronized Task getTask() {
        if (taskIndex >= tasks.length) {
            return null;
        }
        return tasks[taskIndex++];
    }


    private Thread parent;
    private Task[] tasks;
    private Transition transition;
    private int priority;
    private int poolSize;
    private long poolSleep;

    private volatile int taskIndex = 0;
}

