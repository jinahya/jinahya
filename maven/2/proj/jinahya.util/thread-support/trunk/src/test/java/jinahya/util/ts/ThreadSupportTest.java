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


import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ThreadSupportTest {


    private static int INCREMENT = 0;

    private static final Runnable EMPTY_TASK = new Runnable() {
        public void run() {
            synchronized (ThreadSupportTest.class) {
                System.out.println("EMPTY_TASK.INCREMENT: " + INCREMENT++);
            }
        }
    };


    private static final Random RANDOM = new Random();


    @Before
    public void clearTasksBeforeTest() {
        support.clearTasks();
    }


    @Test(expected = NullPointerException.class)
    public void testNullTask() {
        support.addTask(null);
    }


    @Test
    public void testTaskRemoval() {
        support.addTask(EMPTY_TASK);
        assertEquals(true, support.removeTask(EMPTY_TASK));
        assertEquals(false,  support.removeTask(EMPTY_TASK));
    }


    public void testExecutorCount() {
        int executorCount = 0;
        while ((executorCount = Math.abs(RANDOM.nextInt())) != 0) {
            support.setExecutorCount(executorCount);
            break;
        }
        assertEquals(executorCount, support.getExecutorCount());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNonPositiveExecutorCount() {
        support.setExecutorCount(0 - RANDOM.nextInt(Integer.MAX_VALUE));
    }


    @Ignore("takes too long; enable only on release testing")
    @Test
    public void testSpeed() throws InterruptedException {
        final long sleep = 3000L;
        final Runnable sleepingBeauty = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 100; i++) {
            support.addTask(sleepingBeauty);
        }

        measureElapsed(1);
        measureElapsed(5);
        measureElapsed(10);
        measureElapsed(50);
        measureElapsed(100);
    }


    private void measureElapsed(final int executorCount)
        throws InterruptedException {

        System.out.print("executorCount(" + executorCount + ") -> ");
        support.setExecutorCount(executorCount);
        long start = System.currentTimeMillis();
        support.start();
        support.finish();
        long finish = System.currentTimeMillis();
        System.out.println("elapsed: " + (finish - start) + " ms");
    }


    @Test
    public void testStartingTwice() throws InterruptedException {
        // You should see a sequence of incrementing numbers.
        for (int i = 0; i < 100; i++) {
            support.addTask(EMPTY_TASK);
        }
        support.start();
        support.start();
        support.finish();
    }


    private ThreadSupport support = new ThreadSupport();
}
