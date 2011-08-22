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


import java.lang.String;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ConcurrentMachineContextTest {


    private static final Random RANDOM = new Random();


    private static class SleepTask extends Task {


        public SleepTask(final String id) {
            super(id);
        }


        @Override
        public void prepare(TransitionContext context) throws FSMException {
            // empty
        }


        @Override
        public void perform(TransitionContext context) throws FSMException {

            final int count = RANDOM.nextInt(5) + 5;
            for (int i = 0; i < count; i++) {
                try {
                    Thread.sleep(RANDOM.nextInt(500) + 500);
                } catch (InterruptedException ie) {
                    throw new FSMException(ie);
                }
            }
        }
    }


    private static class SleepTaskContext extends TaskContext {


        public SleepTaskContext(Set<String> classNames) {
            super(classNames);
        }


        @Override
        public synchronized Map<String, Task> getTaskMap() throws FSMException {

            if (map == null) {
                map = new HashMap<String, Task>();
                for (int i = 0; i < 100; i++) {
                    final Task task = new SleepTask(Integer.toString(i));
                    map.put(task.getId(), task);
                }
            }

            return new HashMap<String, Task>(map);
        }


        private volatile Map<String, Task> map;
    }


    @Test
    public void testPerform() {

        final Task[] tasks = new Task[100];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new SleepTask(Integer.toString(i));
        }

        final MachineContext context1 = new ConcurrentMachineContext(
            new SleepTaskContext(Collections.<String>emptySet()), 1);

        final MachineContext context2 = new ConcurrentMachineContext(
            new SleepTaskContext(Collections.<String>emptySet()), 1);
    }
}

