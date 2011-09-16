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


package myxlet.task.initialization;


import com.googlecode.jinahya.util.fsm.FSMException;
import com.googlecode.jinahya.util.fsm.TransitionContext;

import com.googlecode.jinahya.util.fsm.XletInitializationTask;


/**
 *
 * @author onacit
 */
public class PrintStateChange extends XletInitializationTask {


    public static final String ID = PrintStateChange.class.getName();


    public PrintStateChange() {
        super(ID);
    }


    @Override
    public void prepare(final TransitionContext context) throws FSMException {
        System.out.println(getId() + " prepare(" + context + ")");
    }


    @Override
    public void perform(final TransitionContext context) throws FSMException {
        System.out.println(getId() + " perform(" + context + ")");
    }
}
