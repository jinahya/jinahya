/*
 * Copyright 2011 onacit.
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


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author onacit
 */
public class MachineTest {


    @Test
    public void testGetState() {

        final Machine machine = new MachineImpl();

        Assert.assertSame(machine.getState(), State.UNKNOWN);
    }


    @Test
    public void testSetState() throws FSMException {


        final Machine machine = new MachineImpl();

        try {
            machine.setState(machine.getState());
            Assert.fail("setState(getState())");
        } catch (FSMException fsme) {
            // expected
        }

        machine.setState(State.INVALID);

        Assert.assertSame(machine.getState(), State.INVALID);
    }


    @Test
    public void testSetProperty() {

        final Machine machine = new MachineImpl();

        try {
            machine.setProperty(null, "");
            Assert.fail("passed: setProperty(null,)");
        } catch (NullPointerException npe) {
            // expected
        }

        machine.setProperty("", null);
    }


    @Test
    public void testGetProperty() {

        final Machine machine = new MachineImpl();

        try {
            machine.getProperty(null);
            Assert.fail("passed: getProperty(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        Assert.assertNull(machine.getProperty(""));

        machine.setProperty("", null);
        Assert.assertNull(machine.getProperty(""));

        final Object value = "";
        machine.setProperty("", value);
        Assert.assertSame(machine.getProperty(""), value);
    }


    @Test
    public void testAddTransitionListener() {

        final Machine machine = new MachineImpl();

        try {
            machine.addTransitionListener(null);
            Assert.fail("passed: addTransitionListener(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        final TransitionListener listener = new TransitionListenerImpl();

        machine.addTransitionListener(listener);
    }


    @Test
    public void testRemoveTransitionListener() {

        final Machine machine = new MachineImpl();

        try {
            machine.removeTransitionListener(null);
            Assert.fail("passed: removeTransitionListener(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        final TransitionListener listener = new TransitionListenerImpl();

        machine.removeTransitionListener(listener);

        for (int i = 0; i < 100; i++) {
            machine.addTransitionListener(listener);
            machine.removeTransitionListener(listener);
        }

        for (int i = 0; i < 100; i++) {
            machine.addTransitionListener(listener);
        }

        for (int i = 0; i < 100; i++) {
            machine.removeTransitionListener(listener);
        }
    }


    @Test
    public void testAddPropertyChangeListener() throws FSMException {

        final Machine machine = new MachineImpl();

        try {
            machine.addPropertyChangeListener(null);
            Assert.fail("addPropertyChangeListener(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        final PropertyChangeListener pcl = new PropertyChangeListener() {


            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("evt: " + evt);
            }
        };

        machine.addPropertyChangeListener(pcl);

        machine.setState(State.INVALID);
    }


    @Test
    public void testRemovePropertyChangeListener() throws FSMException {

        final Machine machine = new MachineImpl();

        try {
            machine.removePropertyChangeListener(null);
            Assert.fail("removePropertyChangeListener(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        final PropertyChangeListener pcl = new PropertyChangeListener() {


            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("evt: " + evt);
            }
        };

        machine.removePropertyChangeListener(pcl);

        machine.setState(State.INVALID);
    }
}