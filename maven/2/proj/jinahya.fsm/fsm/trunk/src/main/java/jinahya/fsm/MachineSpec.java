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
 * A specification how machine works.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class MachineSpec {


    /**
     * Default value for maximumHistorySize.
     */
    private static final int DEFAULT_MAXIMUM_HISTORY_SIZE = 0x0A;


    /**
     * Default value for minimumPrecedence.
     */
    private static final int DEFAULT_MINIMUM_PRECEDENCE = 0x00;


    /**
     * Default value for maximumPoolSize.
     */
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 0x01;


    /**
     * Return the maximum number of old states that machine can hold.
     *
     * @return {@value #DEFAULT_MAXIMUM_HISTORY_SIZE}
     */
    public int getMaximumHistorySize() {
        return DEFAULT_MAXIMUM_HISTORY_SIZE;
    }


    /**
     * Returns the minimum task precedence for given transition.
     *
     * @param transition state transition
     * @return {@value #DEFAULT_MINIMUM_PRECEDENCE}
     */
    public int getMinimumPrecedence(final Transition transition) {
        return DEFAULT_MINIMUM_PRECEDENCE;
    }


    /**
     * Returns the maximum number of threads for given values.
     *
     * @param transition state transition
     * @param precedence task precedence
     * @return {@value #DEFAULT_MAXIMUM_POOL_SIZE}
     */
    public int getMaximumPoolSize(final Transition transition,
                                  final int precedence) {
        return DEFAULT_MAXIMUM_POOL_SIZE;
    }


    /**
     * Checks if specifed <code>transition</code> is a starting condition or
     * not. Once this method returns true, this method is never going to be
     * invoked.
     *
     * @param transition transition to be checked
     * @return true
     */
    public boolean isStartingTransition(final Transition transition) {
        return Boolean.TRUE.booleanValue();
    }


    /**
     * Checks if specifed <code>transition</code> is permitted or not.
     *
     * @param transition transition to be checked.
     * @return true
     */
    public boolean isTransitionAllowed(final Transition transition) {
        return Boolean.TRUE.booleanValue();
    }


    /**
     * Check if specified <code>transition</code> is for finishing or not. Once
     * this method returns true, this method is never going to be invoked.
     *
     * @param transition transition to be checked
     * @return false
     */
    public boolean isFinishingTransition(final Transition transition) {
        return Boolean.FALSE.booleanValue();
    }
}
