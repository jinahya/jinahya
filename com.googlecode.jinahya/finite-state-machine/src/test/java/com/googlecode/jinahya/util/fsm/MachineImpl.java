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


/**
 *
 * @author onacit
 */
public class MachineImpl extends Machine {


    public MachineImpl() throws FSMException {
        this(TaskContextTest.newInstance());
    }


    public MachineImpl(final TaskContext taskContext) throws FSMException {
        super(taskContext);
    }


    @Override
    protected boolean isAllowed(final Transition transition) {
        return true;
    }


    @Override
    protected boolean isStarting(final Transition transition) {
        return true;
    }


    @Override
    protected boolean isFinishing(final Transition transition) {
        return false;
    }
}
