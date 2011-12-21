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


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author onacit
 */
public class MachineTest {


    @Test
    public void testGetState() throws FSMException {

        final Machine machine = new MachineImpl();

        Assert.assertSame(machine.getState(), UnknownState.getInstance());
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

        machine.setState(InvalidState.getInstance());

        Assert.assertSame(machine.getState(), InvalidState.getInstance());
    }


    @Test
    public void testSetProperty() throws FSMException {

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
    public void testGetProperty() throws FSMException {

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


}

