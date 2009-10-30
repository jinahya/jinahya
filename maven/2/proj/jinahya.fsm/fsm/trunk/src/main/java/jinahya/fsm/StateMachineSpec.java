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


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface StateMachineSpec {



    public static final int UNKNOWN_STATE = 0xFFFFFFFF;


    /**
     *
     * @return
     */
    public int getIdentifier();


    /**
     *
     * @param machine
     * @return
     */
    public int getStartingState(StateMachine machine);


    /**
     *
     * @param sourceState
     * @param targetState
     * @return
     */
    public boolean isTransitionAllowed(int sourceState, int targetState);


    /**
     *
     * @param state
     * @return false
     */
    public boolean isFinishingState(int state);
}
