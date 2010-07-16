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

package jinahya.util;


import java.util.Arrays;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class EventListenerSupportTest {


    private static class A implements EventListener {
    };


    private static class B implements EventListener {
    };


    @Test
    public void testAdd() {
        final EventListenerSupport support = new EventListenerSupport();
        support.add(A.class, new A());
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddWithNullType() {
        final EventListenerSupport support = new EventListenerSupport();
        support.add(null, new A());
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddWithNullInstance() {
        final EventListenerSupport support = new EventListenerSupport();
        support.add(A.class, null);
    }


    @Test
    public void testRemove() {
        final EventListenerSupport support = new EventListenerSupport();

        final B b = new B();

        Assert.assertEquals(support.remove(B.class, b), false);

        Assert.assertEquals(support.add(B.class, b), true);

        Assert.assertEquals(support.remove(B.class, b), true);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveWithNullType() {
        final EventListenerSupport support = new EventListenerSupport();
        support.add(null, new B());
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveWithNullInstance() {
        final EventListenerSupport support = new EventListenerSupport();
        support.add(B.class, null);
    }


    @Test
    public void testGet() {
        final EventListenerSupport support = new EventListenerSupport();

        final Random random = new Random();

        final A[] a = new A[random.nextInt(100)];
        for (int i = 0; i < a.length; i++) {
            support.add(A.class, a[i] = new A());
        }

        final B[] b = new B[random.nextInt(100)];
        for (int i = 0; i < b.length; i++) {
            support.add(B.class, b[i] = new B());
        }

        final List a1 = Arrays.asList(support.get(A.class));
        Collections.reverse(a1);
        Assert.assertEquals(a1.size(), a.length);
        for (int i = 0; i < a1.size(); i++) {
            Assert.assertEquals(a1.get(i), a[i]);
        }

        final List b1 = Arrays.asList(support.get(B.class));
        Collections.reverse(b1);
        Assert.assertEquals(b1.size(), b.length);
        for (int i = 0; i < b1.size(); i++) {
            Assert.assertEquals(b1.get(i), b[i]);
        }
    }
}
