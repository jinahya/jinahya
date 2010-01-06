/*
 *  Copyright 2010 Jin Kwon.
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
 */

package jinahya.fsm;


import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com>Jin Kwon</a>
 */
public class TransitionHistoryTask extends Task {


    /**
     * Creates a new instance.
     *
     * @param size the maximum number of states that can be hold.
     */
    public TransitionHistoryTask(int size) {
        super();

        if (size <= 0) {
            throw new IllegalArgumentException("size(" + size + ") <= 0");
        }

        this.size = size;
    }


    //@Override
    public void initialize() throws MachineException {
        super.initialize();

        history.addElement(new Integer(State.UNKNOWN));
    }


    //@Override
    public void perform(final Transition transition, final int precedence)
        throws MachineException {

        if (precedence > 0) {
            return;
        }

        synchronized (history) {
            history.insertElementAt
                (new Integer(transition.getTargetState()), 0);
            history.setSize(Math.min(size, history.size()));
        }
    }


    //@Override
    public void destroy() throws MachineException {
        super.destroy();

        //history.setSize(0);
    }


    /**
     * Returns state values in an array of int. Lower the index later the state.
     *
     * @return an array of int contains states transitted so far.
     */
    public int[] getTransitionHistory() {
        synchronized (history) {
            int[] states = new int[history.size()];
            for (int i = 0; i < states.length; i++) {
                states[i] = ((Integer) history.elementAt(i)).intValue();
            }
            return states;
        }
    }


    private int size;

    private final Vector history = new Vector();
}
