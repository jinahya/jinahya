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
import jinahya.fsm.StateMachine;
import jinahya.fsm.Task;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletStateMachine extends StateMachine {


    /**
     * Creates an instance. Intended to be invoked in constructor.
     *
     * @param tasks
     * @return a new instance
     * @throws StateMachineException
     */
    public static XletStateMachine createInConstructor(Task[] tasks)
        throws StateMachineException {

        return new XletStateMachine(tasks, XletState.LOADED);
    }


    /**
     * Creates an instance. Intended to be invoked in <code>initXlet</code>.
     *
     * @param tasks tasks
     * @return a new instance
     * @throws StateMachineException
     */
    public static XletStateMachine createInInitXlet(Task[] tasks)
        throws StateMachineException {

        return new XletStateMachine(tasks, XletState.PAUSED);
    }


    /**
     * Creates a new instance.
     *
     * @param tasks tasks
     * @param state initial state
     * @throws StateMachineException if any error occurs
     */
    public XletStateMachine(final Task[] tasks, final State state)
        throws StateMachineException {

        super(new XletStateMachineSpec(), tasks, state);
    }


    /**
     * Notifies the <code>initXlet<code> invoked.
     *
     * @throws StateMachineException if any error occurs.
     */
    public void initXletInvoked() throws StateMachineException {
        transit(XletState.PAUSED);
    }


    /**
     * Notifies the <code>startXlet</code> invoked.
     *
     * @throws StateMachineException if any error occurs.
     */
    public void startXletInvoked() throws StateMachineException {
        transit(XletState.STARTED);
    }


    /**
     * Notifies the <code>pauseXlet</code> invoked.
     *
     * @throws StateMachineException if any error occurs.
     */
    public void pauseXletInvoked() throws StateMachineException {
        transit(XletState.PAUSED);
    }


    /**
     * Notifies the <code>destroyXlet</code> invoked.
     *
     * @throws StateMachineException if any error occurs.
     */
    public void destroyXletInvoked() throws StateMachineException {
        transit(XletState.DESTROYED);
    }
}
