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
import com.googlecode.jinahya.util.DependencyResolverException;

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


        final Map<String, Object> properties =
            Collections.synchronizedMap(new HashMap<String, Object>());


        return new TransitionContext() {


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

                try {
                    resolver.add(nextTaskId, taskId);
                } catch (DependencyResolverException dre) {
                    throw new FSMException(dre);
                }
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

                try {
                    resolver.add(taskId, previousTaskId);
                } catch (DependencyResolverException dre) {
                    throw new FSMException(dre);
                }
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

                return properties.put(name, value);
            }
        };
    }


    /** PRIVATE. */
    private TransitionContextFactory() {
        super();
    }
}