/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.util.fsm;


import java.beans.PropertyChangeEvent;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Transition extends PropertyChangeEvent {


    /** GENERATED. */
    private static final long serialVersionUID = -2669482205992821932L;


    /**
     * Property name for state.
     */
    public static final String PROPERTY_NAME_STATE = "state";


    /**
     * Creates a new instance.
     *
     * @param machine source machine
     * @param sourceState source state
     * @param targetState target state
     */
    public Transition(final Machine machine, final State sourceState,
                      final State targetState) {

        super(machine, PROPERTY_NAME_STATE, sourceState, targetState);

        if (machine == null) {
            throw new NullPointerException("null machine");
        }

        if (sourceState == null) {
            throw new NullPointerException("null sourceState");
        }

        if (targetState == null) {
            throw new NullPointerException("null targetState");
        }

        if (sourceState.equals(targetState)) {
            throw new IllegalArgumentException(
                "sourceState is equlas to targetState");
        }
    }


    /**
     * Returns the source machine that this transition made.
     *
     * @return the source machine
     */
    public Machine getMachine() {
        return (Machine) super.source;
    }


    /**
     * Returns the source state.
     *
     * @return source state
     */
    public State getSourceState() {
        return (State) super.getOldValue();
    }


    /**
     * Returns the target state.
     *
     * @return target state
     */
    public State getTargetState() {
        return (State) super.getNewValue();
    }


    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Transition)) { // (null instanceof XXX) -> false
            return false;
        }

        final Transition transition = (Transition) obj;

        if (!getMachine().equals(transition.getMachine())) {
            return false;
        }

        if (!getSourceState().equals(transition.getSource())) {
            return false;
        }

        if (!getTargetState().equals(transition.getTargetState())) {
            return false;
        }

        return true;
    }


    public int hashCode() {

        int hashCode = super.hashCode();

        hashCode = 37 * hashCode + getMachine().hashCode();

        hashCode = 37 * hashCode + getSourceState().hashCode();

        hashCode = 37 * hashCode + getTargetState().hashCode();

        return hashCode;
    }


    public String toString() {
        return "Transition(" + getSourceState() + "->" + getTargetState() + ")";
    }
}

