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
public class FSMSupport {


    /**
     *
     * @param spec
     * @param factory
     * @param state
     * @throws FSMException
     */
    public FSMSupport(final FSMSpec spec, final FSMTaskFactory factory,
                      final int state)
        throws FSMException {

        super();

        this.spec = spec;

        final Vector temp = new Vector();
        synchronized (temp) {
            factory.createTasks(temp);
            for (int i = temp.size() - 1; i >= 0; i--) {
                if (!(temp.elementAt(i) instanceof FSMTask)) {
                    temp.removeElementAt(i);
                    continue;
                }
                for (int j = 0; j < i; j++) {
                    if (temp.elementAt(j).equals(temp.elementAt(i))) {
                        temp.removeElementAt(i);
                    }
                }
            }
            for (int i = 0; i < temp.size(); i++) {
                tasks.addElement(temp.elementAt(i));
            }
        }

        transit(state);
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
     * @param state
     * @throws FSMException
     */
    public synchronized void setState(int state) throws FSMException {
        transit(state);
    }


    /**
     *
     * @param newState
     * @throws FSMException
     */
    public synchronized void transit(int newState) throws FSMException {

        if (newState == FSMSpec.UNKNOWN_STATE || newState == state ||
            !spec.isTransitionAllowed(state, newState)) {
            throw new FSMException
                ("STATE TRANSITION NOT ALLOWED: " + state + " -> " + newState);
        }

        if (finished) {
            throw new FSMException("ALREADY FINISHED!");
        }

        int oldState = state;
        state = newState;

        FSMException thrown = null;

        for (int priority = 0; priority < 10; priority++) {
            for (int i = 0; i < tasks.size(); i++) {
                FSMTask task = (FSMTask) tasks.elementAt(i);
                try {
                    task.perform(oldState, state, priority);
                } catch (FSMException sme) {
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


    private FSMSpec spec;

    private int state = FSMSpec.UNKNOWN_STATE;

    private final Vector tasks = new Vector();

    private boolean finished = false;
}
