/*
 *  Copyright 2010 Jin Kwon.
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
 */

package jinahya.util.ts;


import java.util.Vector;


/**
 * A simple thread support.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ThreadSupport {


    /**
     * .
     */
    private final class TaskSupplier {


        /**
         * .
         */
        public TaskSupplier() {
            super();

            synchronized (tasks) {
                runnables = new Runnable[tasks.size()];
                tasks.copyInto(runnables);
            }
            //this.runnables = runnables;
            index = 0;
        }


        /**
         * Returns next task.
         *
         * @return a Runnable instance or null
         */
        public synchronized Runnable getRunnable() {
            if (index < runnables.length) {
                return runnables[index++];
            } else {
                return null;
            }
        }


        private Runnable[] runnables;
        private int index;
    }


    /**
     * Start executing tasks.
     *
     * @throws InterruptedException if interrupted while waiting previous
     *         execution finished.
     */
    public final synchronized void start() throws InterruptedException {

        //System.out.println("start");

        finish();

        synchronized (tasks) {
            final TaskSupplier supplier = new TaskSupplier();

            threads = new Thread[Math.min(tasks.size(), executorCount)];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread() {
                    //@Override
                    public void run() {
                        Runnable runnable = null;
                        while ((runnable = supplier.getRunnable()) != null) {
                            try {
                                runnable.run();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                threads[i].start();
            }
        }
    }


    /**
     * Wait for all executors are finished.
     *
     * @throws InterruptedException if interrupted
     */
    public final synchronized void finish() throws InterruptedException {

        //System.out.println("finish");

        if (threads == null) {
            return;
        }

        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }
        } finally {
            threads = null;
        }
    }


    /**
     * Adds given task.
     *
     * @param task the task to be added.
     */
    public final void addTask(final Runnable task) {
        if (task == null) {
            throw new NullPointerException("task");
        }
        tasks.addElement(task);
    }


    /**
     * Removes given <code>task</code>.
     *
     * @param task the task to be removed
     * @return true if specified <code>task</code> is added before and removed,
     *         false otherwise.
     */
    public final boolean removeTask(final Runnable task) {
        if (task == null) {
            throw new NullPointerException("task");
        }
        return tasks.removeElement(task);
    }


    public synchronized final int getExecutorCount() {
        return executorCount;
    }


    public synchronized final void setExecutorCount(final int executorCount) {
        if (executorCount <= 0) {
            throw new IllegalArgumentException("executorCount <= 0");
        }
        this.executorCount = executorCount;
    }


    public final void clearTasks() {
        synchronized (tasks) {
            for (int i = tasks.size() - 1; i >= 0; i--) {
                tasks.removeElementAt(i);
            }
        }
    }

    private final Vector tasks = new Vector();

    private volatile int executorCount = 1;

    private transient volatile Thread[] threads;
}
