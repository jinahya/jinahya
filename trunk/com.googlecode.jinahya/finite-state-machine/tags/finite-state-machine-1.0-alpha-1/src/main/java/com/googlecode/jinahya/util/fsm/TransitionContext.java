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


package com.googlecode.jinahya.util.fsm;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface TransitionContext {


    /**
     * Returns transition.
     *
     * @return transition
     */
    Transition getTransition();


    /**
     * Makes sure the task calling this method be performed after the task
     * identified by <code>sourceTaskId</code>.
     *
     * @param targetTaskId the id of task which must be performed before this
     *        task
     * @throws FSMException if sourceTaskId is unknown or illegal dependency
     *         detected.
     */
    void setPerformAfter(String targetTaskId) throws FSMException;


    /**
     * Make sure the task calling this method be performed before the task
     * identified by <code>targetTaskId</code>.
     *
     * @param sourceTaskId the id of task which must be performed after this
     *        task
     * @throws FSMException if targetTaskId is unknown or illegal dependency
     *         detected
     */
    void setPerformBefore(String sourceTaskId) throws FSMException;


    void setProperty(Task owner, String name, Object value);


    Object getProprety(Task owner, String name);


    Object getProprety(String name, String ownerId);
}
