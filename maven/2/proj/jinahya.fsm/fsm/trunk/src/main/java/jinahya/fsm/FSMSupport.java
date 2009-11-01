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
        this.factory = factory;

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
    public synchronized void setState(final int state) throws FSMException {
        transit(state);
    }


    /**
     *
     * @param newState
     * @throws FSMException
     */
    public synchronized void transit(final int newState) throws FSMException {

        if (newState == FSMSpec.UNKNOWN_STATE || newState == state ||
            !spec.isTransitionAllowed(state, newState)) {
            throw new FSMException
                ("transition is not allowed: " + state + " -> " + newState);
        }

        if (finished) {
            throw new FSMException("already finished!");
        }

        int oldState = state;
        state = newState;


        if (!started && spec.isStartingTransition(oldState, state)) {
            started = true;
            final Vector temp = new Vector();
            factory.createTasks(temp);
            synchronized (temp) {
                for (int i = temp.size() - 1; i >= 0; i--) {
                    if (!(temp.elementAt(i) instanceof FSMTask)) {
                        temp.removeElementAt(i);
                        continue;
                    }
                    for (int j = i - 1; j >= 0; j--) {
                        if (temp.elementAt(j).equals(temp.elementAt(i))) {
                            temp.removeElementAt(i);
                        }
                    }
                }
                for (int i = 0; i < temp.size(); i++) {
                    tasks.addElement(temp.elementAt(i));
                }
            }
        }


        if (!finished && spec.isFinishingTransition(oldState, state)) {
            finished = true;
        }


        for (int priority = 0; priority < 10; priority++) {
            for (int i = 0; i < tasks.size(); i++) {
                FSMTask task = (FSMTask) tasks.elementAt(i);
                task.perform(oldState, state, priority);
            }
        }
    }


    private FSMSpec spec;
    private FSMTaskFactory factory;

    private int state = FSMSpec.UNKNOWN_STATE;

    private final Vector tasks = new Vector();

    private volatile boolean started = false;
    private volatile boolean finished = false;
}
