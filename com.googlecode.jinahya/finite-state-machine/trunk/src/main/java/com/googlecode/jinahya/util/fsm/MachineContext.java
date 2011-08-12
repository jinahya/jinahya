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

import java.util.ArrayList;
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
     * @param tasks tasks
     */
    protected MachineContext(final Task[] tasks) {
        super();

        if (tasks == null) {
            throw new NullPointerException("null tasks");
        }

        if (tasks.length == 0) {
            throw new IllegalArgumentException("empty tasks");
        }

        for (int i = 0; i < tasks.length; i++) {
            final Task task = tasks[i];
            if (task == null) {
                throw new NullPointerException("null at tasks[" + i + "]");
            }
            final String taskId = task.getId();
            if (this.tasks.containsKey(taskId)) {
                throw new IllegalArgumentException(
                    "duplicate task id at " + i + ": " + taskId);
            }
            this.tasks.put(taskId, task);
        }
    }


    /**
     * 
     * @param transition transition
     * @throws FSMException
     */
    public void transited(final Transition transition) throws FSMException {


        final Map<String, Thread> threads =
            Collections.synchronizedMap(new HashMap<String, Thread>());


        final Map<String, Map<String, String>> properties =
            Collections.synchronizedMap(
            new HashMap<String, Map<String, String>>());


        final ThreadLocal<String> taskIdLocal = new ThreadLocal<String>();

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();


        final TransitionContext context = new TransitionContext() {


            @Override
            public Transition getTransition() {
                return transition;
            }


            @Override
            public void setDependency(final String targetTaskId)
                throws FSMException {

                if (targetTaskId == null) {
                    throw new NullPointerException("null targetTaskId");
                }

                final String sourceTaskId = taskIdLocal.get();
                if (sourceTaskId == null) {
                    throw new IllegalStateException("no source task id set");
                }

                try {
                    resolver.addDependency(sourceTaskId, targetTaskId);
                } catch (DependencyResolverException dre) {
                    throw new FSMException(dre);
                }
            }


            private void setProperty(final String taskId, final String name,
                                     final String value) {

                if (taskId == null) {
                    throw new NullPointerException("null taskId");
                }

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                synchronized (properties) {
                    Map<String, String> map = properties.get(taskId);
                    if (map == null) {
                        map = new HashMap<String, String>();
                        properties.put(taskId, map);
                    }
                    map.put(name, value);
                }
            }


            private String getProperty(final String taskId, final String name) {

                if (taskId == null) {
                    throw new NullPointerException("null taskId");
                }

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                synchronized (properties) {
                    final Map<String, String> map = properties.get(taskId);
                    if (map == null) {
                        return null;
                    }
                    return map.get(name);
                }
            }


            @Override
            public void setProperty(final String name, final String value) {

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                final String taskId = taskIdLocal.get();
                if (taskId == null) {
                    throw new IllegalStateException("no thread local task id");
                }

                setProperty(taskId, name, value);
            }


            @Override
            public String getProprety(final String name) {

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                final String taskId = taskIdLocal.get();
                if (taskId == null) {
                    throw new IllegalStateException("no thread local task id");
                }

                return getProperty(taskId, name);
            }


            @Override
            public String getProprety(final String name,
                                      final String targetTaskId) {

                if (name == null) {
                    throw new NullPointerException("null name");
                }

                if (targetTaskId == null) {
                    throw new NullPointerException("null targetTaskId");
                }

                final String taskId = taskIdLocal.get();
                if (taskId == null) {
                    throw new IllegalStateException("no thread local task id");
                }

                return getProperty(targetTaskId, name);
            }
        };


        for (Task task : tasks.values()) {
            taskIdLocal.set(task.getId());
            task.prepare(context);
        }

        final List<String> single = resolver.getSingleGroup();
        for (String taskId : tasks.keySet()) {
            if (!single.contains(taskId)) {
                single.add(taskId);
            }
        }

        for (List<String> taskIdList : resolver.getHorizontalGroups()) {
            final List<Task> taskList = new ArrayList<Task>(taskIdList.size());
            for (String taskId : taskIdList) {
                taskList.add(tasks.get(taskId));
            }
            perform(taskList);
        }
    }


    /**
     * 
     * @param taskList
     * @throws FSMException 
     */
    protected abstract void perform(final List<Task> taskList)
        throws FSMException;


    /** task map. */
    private final Map<String, Task> tasks = new HashMap<String, Task>();
}

