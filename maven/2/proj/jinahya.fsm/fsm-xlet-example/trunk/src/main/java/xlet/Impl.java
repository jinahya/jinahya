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

import jinahya.fsm.MachineException;
import jinahya.fsm.Machine;
import jinahya.fsm.Task;

import jinahya.fsm.xlet.XletState;
import jinahya.fsm.xlet.XletMachineSpec;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Impl implements Xlet {


    /**
     *
     */
    public Impl() {
        super();

        xsm = new Machine(new XletMachineSpec());

        for (int i = 0; i < 100; i++) {
            xsm.submit(new DefaultTask());
        }
        xsm.submit(new SimpleLoadTask());
        xsm.submit(new SimpleInitTask());
        xsm.submit(new SimplePlayTask());
        xsm.submit(new HistoryTask());


        xsm.setMinimumPrecedence(10);
        xsm.setPoolSize(10);
        xsm.setHistorySize(10);

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
