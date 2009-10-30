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

package jinahya.fsm;


import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class StateMachineSupport {


    /**
     *
     * @param machine
     * @param spec
     * @param manager
     * @throws StateMachineException
     */
    public StateMachineSupport(final StateMachine machine,
                               final StateMachineSpec spec,
                               final StateMachineTaskManager manager)
        throws StateMachineException {

        super();

        if (manager == null) {
            new DefaultStateMachineTaskManager(machine, spec).loadTasks(tasks);
        } else {
            manager.loadTasks(tasks);
        }

        for (int i = tasks.size() - 1; i >= 0; i--) {
            if (!(tasks.elementAt(i) instanceof StateMachineTask)) {
                tasks.removeElementAt(i);
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (tasks.elementAt(j).equals(tasks.elementAt(i))) {
                    tasks.removeElementAt(i);
                }
            }
        }

        transit((this.spec = spec).getStartingState(this.machine = machine));
    }


    /**
     *
     * @return
     */
    public synchronized int getState() {
        return state;
    }


    /**
     *
     * @param newState
     * @throws StateMachineException
     */
    public synchronized void transit(int newState)
        throws StateMachineException {

        if (newState == StateMachineSpec.UNKNOWN_STATE || newState == state ||
            !spec.isTransitionAllowed(state, newState)) {
            throw new StateMachineException
                ("STATE TRANSITION NOT ALLOWED: " + state + " -> " + newState);
        }

        if (finished) {
            throw new StateMachineException("ALREADY FINISHED!");
        }

        int oldState = state;
        state = newState;

        StateMachineException thrown = null;

        for (int priority = 0; priority < 10; priority++) {
            for (int i = 0; i < tasks.size(); i++) {
                StateMachineTask task = (StateMachineTask) tasks.elementAt(i);
                try {
                    task.perform(oldState, state, priority);
                } catch (StateMachineException sme) {
                    thrown = sme;
                }
            }
        }

        if (spec.isFinishingState(state)) {
            finished = true;
        }

        if (thrown != null) {
            throw thrown;
        }
    }


    private StateMachine machine;
    private StateMachineSpec spec;

    private int state = StateMachineSpec.UNKNOWN_STATE;

    private final Vector tasks = new Vector();

    private boolean finished = false;
}
