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

package jinahya.statemachine;


import java.util.Vector;

import jinahya.statemachine.event.StateTransitionEvent;
import jinahya.statemachine.event.StateTransitionListener;

import jinahya.util.event.EventListenerSupport;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class StateMachineSupport {


    /*
    public static MachineIdentifier getMachineIdentifier
        (final Class machineSpec, final String machineName) {

        if (machineSpec == null || machineName == null) {
            throw new IllegalArgumentException("NULL ARGUMENT!");
        }
        if (!StateMachineSpec.class.isAssignableFrom(machineSpec)) {
            throw new IllegalArgumentException
                (machineSpec.getName() + " IS NOT ASSIGNABLE TO " +
                 StateMachineSpec.class);
        }
        return new MachineIdentifier() {
            //@Override
            public Class getMachineSpec() {
                return machineSpec;
            }
            //@Override
            public String getMachineName() {
                return machineName;
            }
            //@Override
            public int hashCode() {
                int result = 17;
                result = 31 * result + machineSpec.hashCode();
                result = 31 * result + machineName.hashCode();
                return result;
            }
            //@Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof MachineIdentifier)) {
                    return false;
                }
                MachineIdentifier mi = (MachineIdentifier) obj;
                return machineSpec.equals(mi.getMachineSpec()) &&
                    machineName.equals(mi.getMachineName());
            }
            //@Override
            public String toString() {
                return machineSpec.getName() + "/" + machineName;
            }
        };
    }



    public interface MachineIdentifier {

        public Class getMachineSpec();

        public String getMachineName();
    }
    */


    /**
     *
     * @param stateMachine
     * @param stateMachineSpec
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public StateMachineSupport(final StateMachine stateMachine,
                               final StateMachineSpec stateMachineSpec)

        throws InstantiationException, IllegalAccessException {

        super();

        this.stateMachine = stateMachine;
        this.stateMachineSpec = stateMachineSpec;

        els = new EventListenerSupport(this.stateMachine);
    }


    //@Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException(":)");
    }


    public void transit(final int newState) {
        Vector listeners = els.getListeners(StateTransitionListener.class);
        if (listeners.isEmpty()) {
            return;
        }

        int oldState = state;
        state = newState;

        StateTransitionEvent event =
            new StateTransitionEvent(stateMachine, oldState, newState);
        for (int i = 0; i < listeners.size(); i++) {
            ((StateTransitionListener)listeners.elementAt(i)).
                stateTransited(event);
        }
    }


    public void addStateTransitionListener(StateTransitionListener listener) {
        els.addEventListener(listener);
    }


    public void removeStateTransitionListener
        (StateTransitionListener listener) {

        els.removeEventListener(listener);
    }



    private EventListenerSupport els;

    private StateMachine stateMachine;
    private StateMachineSpec stateMachineSpec;

    private int state = StateMachineSpec.UNKNOWN_STATE;
}
