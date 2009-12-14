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



    /**
     * Represents how machine works for a specific transition.
     *
     * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
     */
    public static interface TransitionSpec {


        /**
         * Returns number of threads to be forked for executing tasks.
         *
         * @return number of threads
         */
        int getPoolSize();


        /**
         * Returns executors' sleeping time for given <code>transition</code>.
         *
         * @return sleeping time in milliseconds
         */
        long getPoolSleep();


        /**
         * Returns the flag for immediate return after forking threads.
         *
         * @return true for immediate return, false otherwise.
         */
        boolean getImmediateReturnFlag();


        /**
         * Returns minimum precedence for given <code>transition</code>.
         *
         * @return minimum precedence for given <code>transition</code>
         */
        int getMinimumPrecedence();
    }


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


    /**
     * Returns the spec for given <code>transition</code>.
     *
     * @param transition transition
     * @return an instance of <code>TransitionSpec</code>
     */
    TransitionSpec getTransitionSpec(Transition transition);
}
