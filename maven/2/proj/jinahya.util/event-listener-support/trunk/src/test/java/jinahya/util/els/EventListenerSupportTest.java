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

package jinahya.util.els;


import jinahya.util.EventListenerSupport;
import java.util.EventListener;
import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class EventListenerSupportTest {


    private static class MyEventListener implements EventListener {
    };


    @Before
    public void clearSupportBeforeTest() {
        //support.clear();
    }


    // --------------------------------------------------------------------- ADD
    @Test(expected = NullPointerException.class)
    public void testAddWithNullClass() {
        support.add(null, null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddWithNoneEventListenerClass() {
        support.add(Object.class, null);
    }


    @Test(expected = NullPointerException.class)
    public void testAddWithNullInstance() {
        support.add(EventListener.class, null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddWithIllegalInstance() {
        support.add(MyEventListener.class, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // empty
            }
        });
    }


    // ------------------------------------------------------------------- CLEAR
    /*
    @Test
    public void testClear() {
        support.clear();

        MyEventListener listenerInstance = new MyEventListener();
        support.add(MyEventListener.class, listenerInstance);
        support.clear();
    }
     */


    // ---------------------------------------------------------------- contains
    @Test
    public void testContains() {
        MyEventListener listenerInstance = new MyEventListener();

        support.add(MyEventListener.class, listenerInstance);
        assertEquals(true, support.contains(MyEventListener.class,
                                            listenerInstance));

        support.remove(MyEventListener.class, listenerInstance);
        assertEquals(false, support.contains(MyEventListener.class,
                                             listenerInstance));
    }


    private final EventListenerSupport support = new EventListenerSupport();
}
