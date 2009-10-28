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

package jinahya.util.state;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import jinahya.util.event.EventListenerSupport;

import jinahya.util.state.event.StateTransitionEvent;
import jinahya.util.state.event.StateTransitionListener;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class StateMachineSupport {


    /**
     *
     * @param machine
     * @param spec
     * @throws StateMachineException
     */
    public StateMachineSupport(final StateMachine machine,
                               final StateMachineSpec spec)
        throws StateMachineException {

        super();

        els = new EventListenerSupport(machine);

        transit((this.spec = spec).getStartingState(this.machine = machine));
    }


    private String hex(int val) {
        return hex(bytes(val));
    }


    private String hex(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i] & 0xFF;
            if (val < 16) {
                buffer.append('0');
            }
            buffer.append(Integer.toHexString(val));
        }
        return buffer.toString();
    }


    private byte[] bytes(int val) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            bytes[bytes.length - (i + 1)] = (byte)((val >>> (8 * i)) & 0xFF);
        }
        return bytes;
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

        if (state == newState || !spec.isTransitionAllowed(state, newState)) {
            throw new StateMachineException
                ("STATE TRANSITION NOT ALLOWED: " + state + " -> " + newState);
        }

        if (state == StateMachineSpec.UNKNOWN_STATE) {
            String name = "/META-INF/jinahya/util/state/" +
                hex(machine.getIdentifier()) + "." + hex(spec.getIdentifier());
            InputStream stream = machine.getClass().getResourceAsStream(name);
            if (stream == null) {
                throw new StateMachineException("RESOURCE NOT FOUND: " + name);
            }
            try {
                try {
                    BufferedReader reader = new BufferedReader
                        (new InputStreamReader(stream, "UTF8"));
                    try {
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            line = line.trim();
                            if (line.length() == 0) {
                                continue;
                            }
                            try {
                                Class clazz = Class.forName(line);
                                if (!StateMachineTask.class.
                                    isAssignableFrom(clazz)) {
                                    throw new StateMachineException
                                        (clazz.getName() +
                                         " is not assignable to " +
                                         StateMachineTask.class);
                                }
                                try {
                                    tasks.addElement(clazz.newInstance());
                                } catch (InstantiationException ie) {
                                    throw new StateMachineException(ie);
                                } catch (IllegalAccessException iae) {
                                    throw new StateMachineException(iae);
                                }
                            } catch (ClassNotFoundException cnfe) {
                                throw new StateMachineException(cnfe);
                            }
                        }
                    } finally {
                        reader.close();
                    }
                } finally {
                    stream.close();
                }
            } catch (IOException ioe) {
                throw new StateMachineException(ioe);
            }
        }

        if (finished) {
            throw new StateMachineException("ALREADY FINISHED!");
        }

        int oldState = state;
        state = newState;

        Vector listeners = els.getListeners(StateTransitionListener.class);
        if (!listeners.isEmpty()) {
            StateTransitionEvent event =
                new StateTransitionEvent(machine, oldState, state);
            for (int i = 0; i < listeners.size(); i++) {
                ((StateTransitionListener) listeners.elementAt(i)).
                    stateTransited(event);
            }
        }

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



    public void addStateTransitionListener(StateTransitionListener stl) {
        els.addEventListener(stl);
    }


    public void removeStateTransitionListener(StateTransitionListener stl) {
        els.removeEventListener(stl);
    }


    private StateMachine machine;
    private StateMachineSpec spec;

    private int state = StateMachineSpec.UNKNOWN_STATE;

    private final Vector tasks = new Vector();

    private boolean finished = false;

    private EventListenerSupport els;
}
