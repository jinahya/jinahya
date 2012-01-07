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


package com.googlecode.jinahya.fsm;


import java.util.List;

import javax.microedition.xlet.Xlet;
import javax.microedition.xlet.XletContext;
import javax.microedition.xlet.XletStateChangeException;


/**
 * Xlet for ME.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class MEXlet implements Xlet {


    /**
     * Creates a new instance.
     *
     * @param tasks tasks
     */
    public MEXlet(final List<Task> tasks) {
        super();

        machine = new XletMachine(tasks);
    }


    @Override
    public void initXlet(final XletContext xletContext)
        throws XletStateChangeException {

        try {
            if (machine.getState().equals(State.UNKNOWN)) {
                machine.setState(XletState.LOADED);
            }
            machine.setState(XletState.PAUSED);
        } catch (FSMException fsme) {
            throw new XletStateChangeException(fsme.getMessage());
        }

        synchronized (this) {
            this.xletContext = xletContext;
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

        synchronized (this) {
            xletContext = null;
        }
    }


    /**
     * Returns machine.
     *
     * @return machine
     */
    protected final Machine getMachine() {
        return machine;
    }


    /**
     * Returns xlet context.
     *
     * @return xlet context.
     */
    protected final synchronized XletContext getXletContext() {
        return xletContext;
    }


    /** machine. */
    private final Machine machine;


    /** xlet context. */
    private volatile XletContext xletContext;
}
