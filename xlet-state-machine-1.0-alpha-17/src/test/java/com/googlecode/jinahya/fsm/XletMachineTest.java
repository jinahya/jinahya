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


package com.googlecode.jinahya.fsm;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * 
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XletMachineTest {


    private static final ClassLoader CLASS_LOADER =
        XletMachineTest.class.getClassLoader();


    private static final ResourceLoader RESOURCE_LOADER =
        new ClassResourceLoader(CLASS_LOADER);


    private static final List<Task> TASKS;


    static {
        try {
            TASKS = TaskLoader.loadTasks(
                "com.googlecode.jinahya.fsm", RESOURCE_LOADER, CLASS_LOADER);
        } catch (FSMException fsme) {
            throw new InstantiationError(fsme.getMessage());
        }
    }


    @Test
    public void testInitialState() {

        final Machine machine = new XletMachine(TASKS);

        Assert.assertSame(machine.getState(), State.UNKNOWN);
    }


    @Test
    public void testStateChanges() throws FSMException {

        // LOADED -> DESTROYED
        {
            final Machine machine = new XletMachine(TASKS);
            machine.setState(XletState.LOADED);
            machine.setState(XletState.DESTROYED);
        }

        // LOADED -> PAUSED -> DESTROYED
        {
            final Machine machine = new XletMachine(TASKS);
            machine.setState(XletState.LOADED);
            machine.setState(XletState.PAUSED);
            machine.setState(XletState.DESTROYED);
        }

        // LOADED -> PAUSED -> ACTIVE -> DESTROYED
        {
            final Machine machine = new XletMachine(TASKS);
            machine.setState(XletState.LOADED);
            machine.setState(XletState.PAUSED);
            machine.setState(XletState.ACTIVE);
            machine.setState(XletState.DESTROYED);
        }

        // LOADED -> PAUSED -> ACTIVE -> PAUSED
        {
            final Machine machine = new XletMachine(TASKS);
            machine.setState(XletState.LOADED);
            machine.setState(XletState.PAUSED);
            machine.setState(XletState.ACTIVE);
            machine.setState(XletState.PAUSED);
        }
    }


}

