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


package com.googlecode.jinahya.util.fsm.b;


import com.googlecode.jinahya.util.fsm.FSMException;
import com.googlecode.jinahya.util.fsm.Task;
import com.googlecode.jinahya.util.fsm.TransitionContext;
import com.googlecode.jinahya.util.fsm.TransitionMatcher;


/**
 *
 * @author onacit
 */
public class B0 extends Task {


    protected static final String ID = B0.class.getSimpleName();


    public B0() {
        super(ID, TransitionMatcher.YES);
    }


    @Override
    public void prepare(final TransitionContext context) throws FSMException {
        System.out.println("Task(" + getId() + ").prepare(" + context + ")");
    }


    @Override
    public void perform(final TransitionContext context) throws FSMException {
        System.out.println("Task(" + getId() + ").perform(" + context + ")");
    }
}
