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
//final class ExecutorService extends Thread {
final class ExecutorService implements Runnable {


    /**
     *
     * @param parent
     * @param tasks
     * @param priority
     * @param count
     */
    public ExecutorService(final Thread parent, final Task[] tasks,
                           final Transition transition, final int priority,
                           final int count) {
        super();

        this.parent = parent;
        this.tasks = tasks;
        this.transition = transition;
        this.priority = priority;
        this.count = count;
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
        for (int i = 0; i < count; i++) {
            Thread executor =
                new Thread(new Executor(this, transition, priority));
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


    public synchronized Task getTask() {
        if (index >= tasks.length) {
            return null;
        }
        return tasks[index++];
    }


    private Thread parent;
    private Task[] tasks;
    private Transition transition;
    private int priority;
    private int count;

    private volatile int index = 0;
}

