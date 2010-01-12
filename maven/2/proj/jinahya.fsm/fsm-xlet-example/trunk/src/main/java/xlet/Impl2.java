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


import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import jinahya.fsm.Machine;
import jinahya.fsm.MachineException;
import jinahya.fsm.Transition;

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


    }


    //@Override
    public void initXlet(XletContext ctx) throws XletStateChangeException {
        xsm = new Machine(new XletMachineSpec() {
            //@Override
            public int getMinimumPrecedence(Transition transition) {
                return 10;
            }
            //@Override
            public int getMaximumPoolSize(final Transition transition,
                                          final int precedence) {
                return 20;
            }
            //@Override
            public int getMaximumHistorySize() {
                return 10;
            }
        });

        for (int i = 0; i < 100; i++) {
            xsm.submit(new DefaultTask());
        }
        xsm.submit(new SimpleLoadTask());
        xsm.submit(new SimpleInitTask());
        xsm.submit(new SimplePlayTask());
        xsm.submit(new HistoryTask());

        try {
            xsm.setState(XletState.PAUSED);
        } catch (MachineException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void startXlet() throws XletStateChangeException {
        try {
            xsm.setState(XletState.ACTIVE);
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
