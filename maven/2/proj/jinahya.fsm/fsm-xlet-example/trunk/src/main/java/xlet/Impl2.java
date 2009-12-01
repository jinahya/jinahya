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

import jinahya.fsm.xlet.XletStateMachine;


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

        try {
            xsm = XletStateMachine.createInConstructor(new TaskFactoryImpl());
            xsm.setThreadCount(20);
        } catch (StateMachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void initXlet(XletContext ctx) throws XletStateChangeException {
        try {
            xsm.initXletInvoked();
        } catch (StateMachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void startXlet() throws XletStateChangeException {
        try {
            xsm.startXletInvoked();
        } catch (StateMachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void pauseXlet() {
        try {
            xsm.pauseXletInvoked();
        } catch (StateMachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void destroyXlet(boolean unconditional)
        throws XletStateChangeException {

        try {
            xsm.destroyXletInvoked();
        } catch (StateMachineException fsme) {
            fsme.printStackTrace();
        }
    }


    private XletStateMachine xsm;
}
