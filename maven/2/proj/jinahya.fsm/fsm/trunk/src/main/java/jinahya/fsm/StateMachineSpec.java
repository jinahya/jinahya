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
public interface StateMachineSpec {


    /**
     * Checks if specifed transition is a starting condition or not
     *
     * @param transition transition
     * @return true if specifed transition is starting transition,
     *         false otherwise
     */
    public boolean isStartingTransition(Transition transition);


    /**
     * Checks if specifed transition is permitted or not.
     * @param transition
     * @return true if given transition is an allowed one, false otherwise.
     */
    public boolean isTransitionAllowed(Transition transition);


    /**
     *
     * @param transition
     * @return true if given transition means one of the finishing transitions
     *         of the machine, false otherwise.
     */
    public boolean isFinishingTransition(Transition transition);
}
