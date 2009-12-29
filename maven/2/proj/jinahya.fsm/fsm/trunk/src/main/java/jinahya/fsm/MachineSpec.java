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
public interface MachineSpec {



    /*
    public static interface TransitionSpec {
        int getPoolSize();
        long getPoolSleep();
        int getMinimumPrecedence();
    }
     */


    /**
     * Checks if specifed <code>transition</code> is a starting condition or
     * not. Once this method returns true, this method is never going to be
     * invoked.
     *
     * @param transition transition to be checked
     * @return true if specifed transition is starting transition, false
     *         otherwise
     */
    boolean isStartingTransition(Transition transition);


    /**
     * Checks if specifed <code>transition</code> is permitted or not.
     *
     * @param transition transition to be checked.
     * @return true if given transition is allowed, false otherwise.
     */
    boolean isTransitionAllowed(Transition transition);


    /**
     * Check if specified <code>transition</code> is for finishing or not. Once
     * this method returns true, this method is never going to be invoked.
     *
     * @param transition transition to be checked
     * @return true if given <code>transition<code> means one of the finishing
     *         transitions of the machine, false otherwise.
     */
    boolean isFinishingTransition(Transition transition);
}
