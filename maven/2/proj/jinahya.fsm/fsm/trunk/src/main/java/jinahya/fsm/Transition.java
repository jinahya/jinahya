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

package jinahya.fsm;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Transition {


    /**
     * Creates an instance.
     *
     * @param sourceState source state of this transition
     * @param targetState target state of this transition
     * @param transitionHistory history of state transitions
     */
    public Transition(final State sourceState, final State targetState,
                      final State[] transitionHistory) {
        super();

        if (sourceState == null) {
            throw new IllegalArgumentException("illegal sourceState: null");
        }

        if (targetState == null) {
            throw new IllegalArgumentException("illegal targetState: null");
        }

        if (transitionHistory == null) {
            throw new IllegalArgumentException
                ("illegal transition history: null");
        }

        /*
        if (sourceState.equals(targetState)) {
            throw new IllegalArgumentException("transition for same states");
        }
         */

        this.sourceState = sourceState;
        this.targetState = targetState;
        this.transitionHistory = transitionHistory;
    }


    /**
     * Return the source state of this transition.
     *
     * @return source state
     */
    public State getSourceState() {
        return sourceState;
    }


    /**
     * Returns the target state of this transition.
     *
     * @return target state
     */
    public State getTargetState() {
        return targetState;
    }


    /**
     * Returns transition history.
     *
     * @return transition history.
     */
    public State[] getTransitionHistory() {
        return transitionHistory;
    }


    //@Override
    public String toString() {
        return "TRANSITION: [" + sourceState + "] -> [" + targetState + "]";
    }


    private State sourceState;
    private State targetState;
    private State[] transitionHistory;
}
