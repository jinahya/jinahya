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
public interface MachineSpec {


    /**
     * Checks if specifed <code>transition</code> is a starting condition or
     * not. Once this method returns true, this method is never going to be
     * invoked.
     *
     * @param transition transition to be checked
     * @return true if given <code>transition</code> is a starting transition,
     *         flase otherwise.
     */
    boolean isStartingTransition(final Transition transition);


    /**
     * Checks if specifed <code>transition</code> is permitted or not.
     *
     * @param transition transition to be checked.
     * @return true if given <code>transition</code> is allowed, false
     *         otherwise.
     */
    boolean isTransitionAllowed(final Transition transition);


    /**
     * Check if specified <code>transition</code> is for finishing or not. Once
     * this method returns true, this method is never going to be invoked.
     *
     * @param transition transition to be checked
     * @return true if given <code>transition</code> is a finishing transition,
     *         false otherwise.
     */
    boolean isFinishingTransition(final Transition transition);
}