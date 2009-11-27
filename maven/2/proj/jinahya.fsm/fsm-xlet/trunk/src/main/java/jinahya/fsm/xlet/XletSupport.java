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

package jinahya.fsm.xlet;


import jinahya.fsm.State;
import jinahya.fsm.StateMachineException;
import jinahya.fsm.StateMachineSupport;
import jinahya.fsm.TaskFactory;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletSupport extends StateMachineSupport {


    /**
     * Creates an instance. Intended to be invoked in constructor.
     *
     * @param factory
     * @return a new instance
     * @throws StateMachineException
     */
    public static XletSupport createInConstructor(TaskFactory factory)
        throws StateMachineException {

        return new XletSupport(factory, XletState.LOADED);
    }


    /**
     * Creates an instance. Intended to be invoked in <code>initXlet</code>
     * @param factory
     * @return
     * @throws StateMachineException
     */
    public static XletSupport createInInitXlet(TaskFactory factory)
        throws StateMachineException {

        return new XletSupport(factory, XletState.PAUSED);
    }


    /**
     *
     * @param factory
     * @param state
     * @throws StateMachineException
     */
    public XletSupport(TaskFactory factory, State state)
        throws StateMachineException {

        super(new XletSpec(), factory, state);
    }


    public void initXletInvoked() throws StateMachineException {
        transit(XletState.PAUSED);
    }


    public void startXletInvoked() throws StateMachineException {
        transit(XletState.STARTED);
    }


    public void pauseXletInvoked() throws StateMachineException {
        transit(XletState.PAUSED);
    }


    public void destroyXletInvoked() throws StateMachineException {
        transit(XletState.DESTROYED);
    }
}
