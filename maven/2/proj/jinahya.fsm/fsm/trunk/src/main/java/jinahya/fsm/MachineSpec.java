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
 */

package jinahya.fsm;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class MachineSpec {


    /**
     * Return the maximum number of old states that machine can hold.
     *
     * @return 0
     */
    public int getMaximumHistorySize() {
        return 0x00;
    }


    /**
     * Returns the minimum task precedence for given transition.
     *
     * @param transition state transition
     * @return 0
     */
    public int getMinimumPrecedence(final Transition transition) {
        return 0x00;
    }


    /**
     * Returns the maximum number of threads for given values.
     *
     * @param transition state transition
     * @param precedence task precedence
     * @return 1
     */
    public int getMaximumPoolSize(final Transition transition,
                                  final int precedence) {
        return 0x01;
    }


    /**
     * Checks if specifed <code>transition</code> is a starting condition or
     * not. Once this method returns true, this method is never going to be
     * invoked.
     *
     * @param transition transition to be checked
     * @return true
     */
    public abstract boolean isStartingTransition(Transition transition);


    /**
     * Checks if specifed <code>transition</code> is permitted or not.
     *
     * @param transition transition to be checked.
     * @return true
     */
    public abstract boolean isTransitionAllowed(Transition transition);


    /**
     * Check if specified <code>transition</code> is for finishing or not. Once
     * this method returns true, this method is never going to be invoked.
     *
     * @param transition transition to be checked
     * @return true
     */
    public abstract boolean isFinishingTransition(Transition transition);
}
