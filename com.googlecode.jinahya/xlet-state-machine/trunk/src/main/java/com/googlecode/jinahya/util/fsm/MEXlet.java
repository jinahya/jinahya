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


import javax.microedition.xlet.Xlet;
import javax.microedition.xlet.XletContext;
import javax.microedition.xlet.XletStateChangeException;


/**
 * 
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class MEXlet implements Xlet {


    /**
     * Creates a new instance.
     *
     * @param machine machine
     */
    public MEXlet(final XletMachine machine) {
        super();

        if (machine == null) {
            throw new NullPointerException("null machine");
        }

        this.machine = machine;
    }


    @Override
    public void initXlet(final XletContext ctx)
        throws XletStateChangeException {

        try {
            if (machine.getState().equals(State.UNKNOWN)) {
                machine.setState(XletState.LOADED);
            }
            machine.setState(XletState.PAUSED);
        } catch (FSMException fsme) {
            throw new XletStateChangeException(fsme.getMessage());
        }
    }


    @Override
    public void startXlet() throws XletStateChangeException {

        try {
            machine.setState(XletState.ACTIVE);
        } catch (FSMException fsme) {
            throw new XletStateChangeException(fsme.getMessage());
        }
    }


    @Override
    public void pauseXlet() {

        try {
            machine.setState(XletState.PAUSED);
        } catch (FSMException fsme) {
            fsme.printStackTrace(System.err);
        }
    }


    @Override
    public void destroyXlet(final boolean unconditional)
        throws XletStateChangeException {

        try {
            if (machine.getState().equals(State.UNKNOWN)) {
                machine.setState(XletState.LOADED);
            }
            machine.setState(XletState.DESTROYED);
        } catch (FSMException fsme) {
            fsme.printStackTrace(System.err);
        }
    }


    /** machine. */
    private final Machine machine;
}

