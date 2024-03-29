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


package myxlet.task.activation.switch_;


import com.googlecode.jinahya.util.fsm.FSMException;
import com.googlecode.jinahya.util.fsm.TransitionContext;

import com.googlecode.jinahya.util.fsm.XletActivationSwitchTask;


/**
 *
 * @author onacit
 */
public class PrintStateChange extends XletActivationSwitchTask {


    public static final String ID = PrintStateChange.class.getName();


    public PrintStateChange() {
        super(ID);
    }


    @Override
    public void prepareOn(final TransitionContext context) throws FSMException {
        System.out.println(getId() + " prepareOn(" + context + ")");
    }


    @Override
    public void prepareOff(final TransitionContext context)
        throws FSMException {

        System.out.println(getId() + " prepareOff(" + context + ")");
    }


    @Override
    public void performOn(final TransitionContext context) throws FSMException {
        System.out.println(getId() + " performOn(" + context + ")");
    }


    @Override
    public void performOff(final TransitionContext context)
        throws FSMException {

        System.out.println(getId() + " performOff(" + context + ")");
    }
}
