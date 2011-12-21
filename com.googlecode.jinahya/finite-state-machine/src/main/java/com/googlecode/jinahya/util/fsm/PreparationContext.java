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
 * A context for preparing for the transition.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface PreparationContext extends TransitionContext {


    /**
     * Makes sure the task calling this method be performed after the task
     * identified by <code>previousTaskId</code>.
     *
     * @param previousTaskId the id of a task which must be performed before
     * this task
     * @throws FSMException if <code>previousTaskId</code> is unknown or an
     * illegal dependency resolution detected.
     */
    void setPerformAfter(String previousTaskId) throws FSMException;


    /**
     * Make sure the task calling this method be performed before the task
     * identified by <code>nextTaskId</code>.
     *
     * @param nextTaskId the id of a task which must be performed after this
     * task
     * @throws FSMException if <code>nextTaskId</code> is unknown or an illegal
     * dependency resolution detected
     */
    void setPerformBefore(String nextTaskId) throws FSMException;
}
