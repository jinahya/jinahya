/*
 *  Copyright 2009 onacit.
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


import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import jinahya.fsm.StateMachineException;
import jinahya.fsm.StateMachineSupport;

import jinahya.fsm.xlet.XletState;
import jinahya.fsm.xlet.XletSupport;


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

        try {
            fsms = XletSupport.createInConstructor(new TaskFactoryImpl());
            fsms.setThreadCount(5);
        } catch (StateMachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void initXlet(XletContext ctx) throws XletStateChangeException {
        try {
            fsms.setState(XletState.PAUSED);
        } catch (StateMachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void startXlet() throws XletStateChangeException {
        try {
            fsms.setState(XletState.STARTED);
        } catch (StateMachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void pauseXlet() {
        try {
            fsms.setState(XletState.PAUSED);
        } catch (StateMachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void destroyXlet(boolean unconditional)
        throws XletStateChangeException {

        try {
            fsms.setState(XletState.DESTROYED);
        } catch (StateMachineException fsme) {
            fsme.printStackTrace();
        }
    }


    private StateMachineSupport fsms;
}
