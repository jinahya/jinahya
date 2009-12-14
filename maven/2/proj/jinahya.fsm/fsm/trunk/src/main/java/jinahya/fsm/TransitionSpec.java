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
public interface TransitionSpec {


    /**
     * Default implememtation.
     * Waits for all tasks be done without threading in 10 precedences.
     */
    public static final TransitionSpec DEFAULT_SPEC = new TransitionSpec() {

        //@Override
        public int getPoolSize() {
            return 0;
        }

        //@Override
        public long getPoolSleep() {
            return 0L;
        }

        //@Override
        public boolean getImmediateReturnFlag() {
            return Boolean.FALSE.booleanValue();
        }

        //@Override
        public int getMinimumPrecedence() {
            return 9;
        }
    };


    /**
     * Returns number of threads to be forked for executing tasks.
     *
     * @return number of threads
     */
    int getPoolSize();


    /**
     * Returns executors' sleeping time for given <code>transition</code.
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
