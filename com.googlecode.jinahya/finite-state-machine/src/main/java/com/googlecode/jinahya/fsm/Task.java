/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.fsm;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface Task {


    /**
     * Checks if given <code>transition</code> matches to any of target
     * matchers.
     *
     * @param transition transition to match.
     * @return true if matches; false otherwise
     */
    boolean matches(Transition transition);


    /**
     * Performs desired actions for given <code>transition</code>.
     *
     * @param transition transition
     */
    void perform(Transition transition);


}

