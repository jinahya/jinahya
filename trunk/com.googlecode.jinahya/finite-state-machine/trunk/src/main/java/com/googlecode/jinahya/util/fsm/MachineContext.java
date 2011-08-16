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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class MachineContext {


    /**
     * Creates a new instance.
     * 
     * @param taskContext tasks
     */
    protected MachineContext(final TaskContext taskContext) {
        super();

        if (taskContext == null) {
            throw new NullPointerException("null taskContext");
        }

        this.taskContext = taskContext;
    }


    /**
     * 
     * @param transition transition
     * @throws FSMException
     */
    public void transited(final Transition transition) throws FSMException {


        final Map<String, Map<String, Object>> properties =
            Collections.synchronizedMap(
            new HashMap<String, Map<String, Object>>());

        final ThreadLocal<String> taskIdLocal = new ThreadLocal<String>();

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();


        final TransitionContext transitionContext = new TransitionContext() {


            @Override
            public Transition getTransition() {
                return transition;
            }


            @Override
            public void setPerformBefore(final String sourceTaskId)
                throws FSMException {

                if (sourceTaskId == null) {
                    throw new NullPointerException("null sourceTaskId");
                }

                final String targetTaskId = taskIdLocal.get();
                if (targetTaskId == null) {
                    throw new IllegalStateException("no task id set");
                }

                try {
                    resolver.addDependency(sourceTaskId, targetTaskId);
                } catch (DependencyResolverException dre) {
                    throw new FSMException(dre);
                }
            }


            @Override
            public void setPerformAfter(final String targetTaskId)
                throws FSMException {

                if (targetTaskId == null) {
                    throw new NullPointerException("null targetTaskId");
                }

                final String sourceTaskId = taskIdLocal.get();
                if (sourceTaskId == null) {
                    throw new IllegalStateException("no task id set");
                }

                try {
                    resolver.addDependency(sourceTaskId, targetTaskId);
                } catch (DependencyResolverException dre) {
                    throw new FSMException(dre);
                }
            }


            /**
             *
             * @param taskId property owner task id
             * @param name property name
             * @param value property value
             */
            private void setProperty(final String taskId, final String name,
                                     final Object value) {

                if (taskId == null) {
                    throw new NullPointerException("null taskId");
                }

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                synchronized (properties) {
                    Map<String, Object> map = properties.get(taskId);
                    if (map == null) {
                        map = new HashMap<String, Object>();
                        properties.put(taskId, map);
                    }
                    map.put(name, value);
                }
            }


            /**
             * Returns property value.
             *
             * @param taskId property owner task id
             * @param name property name
             */
            private Object getProperty(final String taskId, final String name) {

                if (taskId == null) {
                    throw new NullPointerException("null taskId");
                }

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                synchronized (properties) {
                    final Map<String, Object> map = properties.get(taskId);
                    if (map == null) {
                        return null;
                    }
                    return map.get(name);
                }
            }


            @Override
            public void setProperty(final Task owner, final String name,
                                    final Object value) {

                if (owner == null) {
                    throw new NullPointerException("null owner");
                }

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                setProperty(owner.getId(), name, value);
            }


            @Override
            public Object getProprety(final Task owner, final String name) {

                if (owner == null) {
                    throw new NullPointerException("null owner");
                }

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                return getProperty(owner.getId(), name);
            }


            @Override
            public Object getProprety(final String name, final String ownerId) {

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                if (ownerId == null) {
                    throw new NullPointerException("null ownerId");
                }

                return getProperty(ownerId, name);
            }
        };


        final Collection<Task> contextTasks = taskContext.getTasks();

        final Map<String, Task> map = new HashMap<String, Task>();

        for (Task task : contextTasks) {
            final String taskId = task.getId();
            if (taskId == null) {
                throw new FSMException("null task id from " + task);
            }
            if (map.containsKey(taskId)) {
                throw new FSMException("duplicate task id from " + task);
            }
            map.put(taskId, task);
            try {
                resolver.addDependency(taskId, null);
            } catch (DependencyResolverException dre) {
                //dre.printStackTrace(System.err); // not gonna happen
                throw new FSMException(dre);
            }
            taskIdLocal.set(taskId);
            task.prepare(transitionContext);
            taskIdLocal.set(null);
        }

        final List<List<String>> idGroups = resolver.getVerticalGroups();
        for (List<String> idGroup : idGroups) {
            final Task[] tasks = new Task[idGroup.size()];
            for (int i = 0; i < tasks.length; i++) {
                tasks[i] = map.get(idGroup.get(i));
            }
            perform(transitionContext, tasks);
        }
    }


    /**
     * Perform <code>tasks</code>. Each task can be performed concurrently.
     * Implementations must guarantee that all tasks performed before this
     * method returns.
     *
     * @param context transition context
     * @param tasks tasks to be performed
     * @throws FSMException if an error occurs
     */
    protected abstract void perform(final TransitionContext context,
                                    final Task... tasks)
        throws FSMException;


    private final TaskContext taskContext;
}

