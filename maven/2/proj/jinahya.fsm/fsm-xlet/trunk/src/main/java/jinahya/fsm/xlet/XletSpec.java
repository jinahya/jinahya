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

package jinahya.fsm.xlet;


import jinahya.fsm.State;
import jinahya.fsm.StateMachineSpec;
import jinahya.fsm.Transition;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletSpec implements StateMachineSpec {


    /**
     * Xlet object has not even loaded yet.
     */
    public static final int NOT_LOADED = 0x01;


    /**
     * Instantiated.
     */
    public static final int LOADED = (NOT_LOADED << 1);


    /**
     * <code>initXlet()</code> or pauseXlet() called.
     */
    public static final int PAUSED = (LOADED << 1);


    /**
     * <code>startXlet()</code> called.
     */
    public static final int STARTED = (PAUSED << 1);


    /**
     * <code>destoryXlet()</code> called.
     */
    public static final int DESTROYED = (STARTED << 1);


    /**
     * DESTROYED but the Xlet object has not yet been garbage collected.
     */
    public static final int INVALID = (DESTROYED << 1);



    //@Override
    public boolean isStartingTransition(Transition transition) {

        // UNKNOWN -> LOADED || LOADED -> PAUSED
        return (transition.getSourceState().equals(State.UNKNOWN) &&
                transition.getTargetState().equals(XletState.LOADED)) ||
               (transition.getSourceState().equals(XletState.LOADED) &&
                transition.getTargetState().equals(XletState.PAUSED));
    }


    //@Override
    public boolean isTransitionAllowed(Transition transition) {

        if (transition.getSourceState().equals(State.UNKNOWN)) {
            return (transition.getTargetState().equals(XletState.LOADED) ||
                    transition.getTargetState().equals(XletState.PAUSED));
        } else if (transition.getSourceState().equals(XletState.NOT_LOADED)) {
            // not gonna happen
        } else if (transition.getSourceState().equals(XletState.LOADED)) {
            return (transition.getTargetState().equals(XletState.PAUSED) ||
                    transition.getTargetState().equals(XletState.DESTROYED));
        } else if (transition.getSourceState().equals(XletState.PAUSED)) {
            return (transition.getTargetState().equals(XletState.STARTED) ||
                    transition.getTargetState().equals(XletState.DESTROYED));
        } else if (transition.getSourceState().equals(XletState.STARTED)) {
            return (transition.getTargetState().equals(XletState.PAUSED) ||
                    transition.getTargetState().equals(XletState.DESTROYED));
        } else if (transition.getSourceState().equals(XletState.DESTROYED)) {
            // not gonnna happen
        } else if (transition.getSourceState().equals(XletState.INVALID)) {
            // not gonna happen
        } else {
        }

        return false;
    }


    //@Override
    public boolean isFinishingTransition(Transition transition) {
        return transition.getTargetState().equals(XletState.DESTROYED);
    }
}
