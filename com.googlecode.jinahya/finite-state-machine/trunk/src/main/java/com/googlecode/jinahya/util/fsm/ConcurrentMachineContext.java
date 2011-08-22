/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.util.fsm;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ConcurrentMachineContext extends MachineContext {


    /**
     * Creates a new instance.
     *
     * @param taskContext task context
     * @param threadCount thread count
     */
    public ConcurrentMachineContext(final TaskContext taskContext,
                                    final int threadCount) {
        super(taskContext);

        if (threadCount <= 0) {
            throw new IllegalArgumentException(
                "negative threadCount: " + threadCount);
        }

        this.threadCount = threadCount;
    }


    @Override
    protected void perform(final TransitionContext context, final Task... tasks)
        throws FSMException {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        if (tasks == null) {
            throw new NullPointerException("null tasks");
        }

        if (tasks.length == 0) {
            throw new IllegalArgumentException("empty tasks");
        }

        @SuppressWarnings("unchecked")
        final List<Task> taskList = Collections.synchronizedList(
            new LinkedList<Task>(Arrays.asList(tasks)));

        final Map<Task, Throwable> throwns =
            Collections.synchronizedMap(new HashMap<Task, Throwable>());

        final Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(tasks[i].getId()) {


                @Override
                public void run() {
                    while (true) {
                        synchronized (taskList) {
                            if (taskList.isEmpty()) {
                                break;
                            }
                            final Task task = taskList.remove(0);
                            try {
                                task.perform(context);
                            } catch (Exception thrown) {
                                synchronized (throwns) {
                                    throwns.put(task, thrown);
                                }
                            }
                        }
                    }
                }
            };

            threads[i].start();
        }

        // wait for all threads end
        for (int i = 0; i < threads.length; i++) {
            if (threads[i].isAlive()) {
                try {
                    threads[i].join();
                } catch (InterruptedException ie) {
                    throw new FSMException(ie);
                }
            }
        }

        // throw thrown if exists
        for (Iterator<Throwable> i = throwns.values().iterator();
             i.hasNext();) {
            final Throwable thrown = i.next();
            if (thrown instanceof FSMException) {
                throw (FSMException) thrown;
            }
            throw new FSMException(thrown);
        }

        // done
    }


    /** thread count. */
    private final int threadCount;
}

