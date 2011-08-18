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


import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class MyXlet implements Xlet {


    @Override
    public void initXlet(final XletContext context)
        throws XletStateChangeException {

        System.out.println("[XLET] initXlet");

        final TaskContext taskContext;
        try {
            taskContext =
                TaskContext.newInstance("com.googlecode.jinahya.util.fsm");
        } catch (FSMException fsme) {
            throw new XletStateChangeException(fsme.getMessage());
        }

        synchronized (this) {
            machine = new XletMachine(new MachineContext(taskContext));
            try {
                machine.setState(XletStates.PAUSED);
            } catch (FSMException fsme) {
                throw new XletStateChangeException(fsme.getMessage());
            }
        }
    }


    @Override
    public void startXlet() throws XletStateChangeException {

        System.out.println("[XLET] startXlet");

        synchronized (this) {
            try {
                machine.setState(XletStates.ACTIVE);
            } catch (FSMException fsme) {
                throw new XletStateChangeException(fsme.getMessage());
            }
        }
    }


    @Override
    public void pauseXlet() {

        System.out.println("[XLET] pauseXlet");

        synchronized (this) {
            try {
                machine.setState(XletStates.PAUSED);
            } catch (FSMException fsme) {
                fsme.printStackTrace(System.err);
            }
        }
    }


    @Override
    public void destroyXlet(final boolean unconditional)
        throws XletStateChangeException {

        System.out.println("[XLET] destroyXlet");

        synchronized (this) {
            if (machine != null) {
                try {
                    machine.setState(XletStates.DESTROYED);
                } catch (FSMException fsme) {
                    fsme.printStackTrace(System.err);
                }
                machine = null;
            }
        }
    }


    private volatile Machine machine = null;
}

