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


import com.googlecode.jinahya.util.DependencyResolver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * TransitionContext implementation.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
final class TransitionContextFactory {


    /**
     * Creates a new instance.
     *
     * @param transition transition
     * @param buffer task id buffer
     * @param resolver task id dependency resolver
     * @return a new instance
     */
    static TransitionContext newInstance(
        final Transition transition, final StringBuffer buffer,
        final DependencyResolver<String> resolver) {

        if (transition == null) {
            throw new NullPointerException("null transition");
        }

        if (buffer == null) {
            throw new NullPointerException("null buffer");
        }

        if (resolver == null) {
            throw new NullPointerException("null resolver");
        }

        final Map<String, Object> properties =
            Collections.synchronizedMap(new HashMap<String, Object>());


        return new PreparationContext() {


            @Override
            public Transition getTransition() {
                return transition;
            }


            @Override
            public void setPerformBefore(final String nextTaskId)
                throws FSMException {

                if (nextTaskId == null) {
                    throw new NullPointerException("null nextTaskId");
                }

                final String taskId = buffer.toString();
                if (taskId.isEmpty()) {
                    return;
                }

                if (!resolver.contains(nextTaskId, null)) {
                    throw new FSMException(
                        "unknown next taskId: " + nextTaskId);
                }

                resolver.add(nextTaskId, taskId);
            }


            @Override
            public void setPerformAfter(final String previousTaskId)
                throws FSMException {

                if (previousTaskId == null) {
                    throw new NullPointerException("null previousTaskId");
                }

                final String taskId = buffer.toString();
                if (taskId.isEmpty()) {
                    return;
                }

                if (!resolver.contains(previousTaskId, null)) {
                    throw new FSMException(
                        "unknown previous taskId: " + previousTaskId);
                }

                resolver.add(taskId, previousTaskId);
            }


            @Override
            public Object getProperty(final String name) {

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                return properties.get(name);
            }


            @Override
            public Object setProperty(final String name, final Object value) {

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                if (value == null) {
                    return properties.remove(name);
                } else {
                    return properties.put(name, value);
                }
            }


        };
    }


    /** PRIVATE. */
    private TransitionContextFactory() {
        super();
    }


}