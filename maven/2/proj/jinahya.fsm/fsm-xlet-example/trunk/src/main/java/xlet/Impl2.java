/*
 *  Copyright 2009 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package xlet;


import java.util.Vector;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import jinahya.fsm.Machine;
import jinahya.fsm.MachineException;
import jinahya.fsm.Task;

import jinahya.fsm.xlet.XletState;
import jinahya.fsm.xlet.XletMachineSpec;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Impl2 implements Xlet {



    /**
     *
     */
    public Impl2() {
        super();

        Vector vector = new Vector();
        for (int i = 0; i < 100; i++) {
            vector.addElement(new DefaultTask());
        }
        vector.addElement(new SimpleLoadTask());
        vector.addElement(new SimpleInitTask());
        vector.addElement(new SimplePlayTask());

        Task[] tasks = (Task[]) vector.toArray(new Task[vector.size()]);

        xsm = new Machine(new XletMachineSpec(), tasks);

        xsm.setMinimumPrecedence(20);
        xsm.setThreadPoolSize(20);

        try {
            xsm.setState(XletState.LOADED);
        } catch (MachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void initXlet(XletContext ctx) throws XletStateChangeException {
        try {
            xsm.setState(XletState.PAUSED);
        } catch (MachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void startXlet() throws XletStateChangeException {
        try {
            xsm.setState(XletState.STARTED);
        } catch (MachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void pauseXlet() {
        try {
            xsm.setState(XletState.PAUSED);
        } catch (MachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void destroyXlet(boolean unconditional)
        throws XletStateChangeException {

        try {
            xsm.setState(XletState.DESTROYED);
        } catch (MachineException fsme) {
            fsme.printStackTrace();
        }
    }


    private Machine xsm;
}
