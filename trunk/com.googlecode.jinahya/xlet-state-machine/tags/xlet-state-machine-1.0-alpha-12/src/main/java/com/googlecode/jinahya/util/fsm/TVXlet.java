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
 * Xlet for TV.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class TVXlet implements Xlet {


    /**
     * Creates a new instance.
     *
     * @param taskContext task context
     */
    public TVXlet(final TaskContext taskContext) {
        this(new XletMachine(taskContext));
    }


    /**
     * Creates a new instance.
     *
     * @param machine machine
     */
    public TVXlet(final XletMachine machine) {
        super();

        if (machine == null) {
            throw new NullPointerException("null machine");
        }

        if (!machine.getState().equals(State.UNKNOWN)) {
            throw new IllegalArgumentException(
                "machine's state is not " + State.UNKNOWN);
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
