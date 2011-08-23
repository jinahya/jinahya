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


import java.util.Collections;
import java.util.Set;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ActivityMachineTest {


    private static final ResourceLoader RESOURCE_LOADER =
        new ClassResourceLoader(ActivityMachineTest.class.getClassLoader());


    private static final Set<String> CLASS_NAMES;


    static {
        try {
            final Set<String> classNames = TaskContext.getClassNames(
                "com.googlecode.jinahya.util.fsm", RESOURCE_LOADER);
            CLASS_NAMES = Collections.unmodifiableSet(classNames);
        } catch (FSMException fsme) {
            throw new InstantiationError(fsme.getMessage());
        }
    }


    public static Machine newInstance() {

        return new ActivityMachine(
            new MachineContext(new TaskContext(CLASS_NAMES)));
    }


    @Test
    public void testSetState() throws FSMException {

        final Machine machine = newInstance();

        testSetState(machine, ActivityState.STOPPED, ActivityState.PAUSED, ActivityState.ACTIVE);
    }


    private void testSetState(final Machine machine, State... states)
        throws FSMException {

        for (State state : states) {
            machine.setState(state);
        }
    }
}

