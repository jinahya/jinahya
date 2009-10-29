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

package jinahya.util.state.javatv.xlet;


import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import jinahya.util.state.StateMachine;
import jinahya.util.state.StateMachineException;
import jinahya.util.state.StateMachineSupport;


/**
 *
 * @author <a href="jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class AbstractXlet implements Xlet, StateMachine {


    /**
     *
     * @param identifier
     */
    public AbstractXlet(final int identifier) {
        super();

        this.identifier = identifier;

        try {
            sms = new StateMachineSupport(this, new JavaTVXletSpec());
        } catch (StateMachineException sme) {
            sme.printStackTrace();
        }
    }


    // ----------------------------------------------------------- STATE_MACHINE

    //@Override
    public final int getIdentifier() {
        return identifier;
    }


    // -------------------------------------------------------------------- XLET

    //@Override
    public void initXlet(final XletContext ctx)
        throws XletStateChangeException {

        try {
            sms.transit(JavaTVXletSpec.PAUSED);
        } catch (StateMachineException sme) {
            sme.printStackTrace();
        }
    }


    //@Override
    public void startXlet() throws XletStateChangeException {
        try {
            sms.transit(JavaTVXletSpec.STARTED);
        } catch (StateMachineException sme) {
            sme.printStackTrace();
        }
    }


    //@Override
    public void pauseXlet() {
        try {
            sms.transit(JavaTVXletSpec.PAUSED);
        } catch (StateMachineException sme) {
            sme.printStackTrace();
        }
    }


    //@Override
    public void destroyXlet(final boolean unconditional)
        throws XletStateChangeException {

        try {
            sms.transit(JavaTVXletSpec.DESTROYED);
        } catch (StateMachineException sme) {
            sme.printStackTrace();
        }
    }


    private int identifier;
    private StateMachineSupport sms;
}
