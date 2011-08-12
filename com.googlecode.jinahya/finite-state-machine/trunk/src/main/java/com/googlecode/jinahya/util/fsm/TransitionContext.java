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
     * identified by <code>targetTaskId</code>.
     *
     * @param targetTaskId the id of the target task to wait
     * @throws FSMException if targetTaskId is unknown or illegal dependency
     *         detected.
     */
    void setDependency(String targetTaskId) throws FSMException;


    /**
     * Sets property.
     *
     * @param name property name; may not null
     * @param value property value
     */
    void setProperty(String name, String value);


    /**
     * Returns property value identified by <code>name</code>.
     *
     * @param name property name; may not null
     * @return property value
     */
    String getProprety(String name);


    /**
     * Returns property value identified by <code>name</code> owned to the task
     * identified by <code>targetTaskId</code>.
     *
     * @param name property name; may not null
     * @param targetTaskId owner task id; may not null
     * @return property value
     */
    String getProprety(String name, String targetTaskId);
}

