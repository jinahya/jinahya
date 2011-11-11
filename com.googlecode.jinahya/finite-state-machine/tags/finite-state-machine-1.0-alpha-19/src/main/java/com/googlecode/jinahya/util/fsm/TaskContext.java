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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class TaskContext {


    /** task index filename. */
    private static final String TASK_INDEX_FILENAME =
        "com.googlecode.jinahya.util.fsm.task.index";


    /**
     * Creates a new instance.
     *
     * @param contextPath context path
     * @param resourceLoader resource loader
     * @param classLoader class loader
     * @return a new instance
     * @throws FSMException if an error occurs
     */
    public static TaskContext newInstance(final String contextPath,
                                          final ResourceLoader resourceLoader,
                                          final ClassLoader classLoader)
        throws FSMException {

        if (contextPath == null) {
            throw new NullPointerException("null contextPath");
        }

        if (resourceLoader == null) {
            throw new NullPointerException("null resourceLoader");
        }

        if (classLoader == null) {
            throw new NullPointerException("null classLoader");
        }

        // parse package names
        final Set<String> packageNames = new HashSet<String>();
        final StringTokenizer tokenizer = new StringTokenizer(contextPath, ":");
        while (tokenizer.hasMoreTokens()) {
            String packageName = tokenizer.nextToken();
            packageName = packageName.trim();
            if (packageName.length() == 0) {
                throw new FSMException("empty package name");
            }
            packageNames.add(packageName);
        }

        // parse class names
        final Set<String> classNames = new HashSet<String>();
        for (String packageName : packageNames) {
            final String resourceName =
                packageName.replace('.', '/') + "/" + TASK_INDEX_FILENAME;
            try {
                final InputStream resource =
                    resourceLoader.loadResource(resourceName);
                if (resource == null) {
                    throw new FSMException("null resource loaded");
                }
                try {
                    final BufferedReader reader = new BufferedReader(
                        new InputStreamReader(resource, "UTF-8"));
                    try {
                        String className = null;
                        while (true) {
                            className = reader.readLine();
                            if (className == null) {
                                break;
                            }
                            className = className.trim();
                            if (className.length() == 0
                                || className.startsWith("#")) {
                                continue;
                            }
                            classNames.add(packageName + "." + className);
                        }
                    } finally {
                        reader.close();
                    }
                } finally {
                    resource.close();
                }
            } catch (IOException ioe) {
                throw new FSMException(ioe);
            }
        }

        final List<Class<?>> taskClasses =
            new ArrayList<Class<?>>(classNames.size());

        for (String className : classNames) {
            try {
                final Class<?> loaded = classLoader.loadClass(className);
                if (!Task.class.isAssignableFrom(loaded)) {
                    throw new FSMException(
                        "loaded class(" + loaded + ") is not assignable to "
                        + Task.class);
                }
                taskClasses.add(loaded);
            } catch (ClassNotFoundException cnfe) {
                throw new FSMException(cnfe);
            }
        }

        return new TaskContext(taskClasses);
    }


    /**
     * Creates a new instance.
     *
     * @param taskClasses task classes
     */
    private TaskContext(final List<Class<?>> taskClasses) {

        super();

        if (taskClasses == null) {
            throw new NullPointerException("null taskClasses");
        }

        this.taskClasses = taskClasses;
    }


    /**
     * Returns a map of &lt;ID, Instance&gt;.
     *
     * @return a map of tasks.
     * @throws FSMException if an error occurs.
     */
    public Map<String, Task> getTasks() throws FSMException {

        final Map<String, Task> tasks = new HashMap<String, Task>();
        for (Class<?> taskClass : taskClasses) {
            try {
                final Constructor<?> constructor =
                    taskClass.getConstructor((Class[]) null);
                try {
                    final Task task =
                        (Task) constructor.newInstance((Object[]) null);
                    final String taskId = task.getId();
                    if (tasks.containsKey(taskId)) {
                        throw new FSMException(
                            "duplicate task id: " + taskId);
                    }
                    tasks.put(taskId, task);
                } catch (InstantiationException ie) {
                    throw new FSMException(
                        "failed to create a new instance: " + taskClass, ie);
                } catch (IllegalAccessException iae) {
                    throw new FSMException(
                        "failed to create a new instance: " + taskClass, iae);
                } catch (InvocationTargetException ite) {
                    throw new FSMException(
                        "failed to create a new instance: " + taskClass, ite);
                }
            } catch (NoSuchMethodException nsme) {
                throw new FSMException(
                    "no default constructor: " + taskClass, nsme);
            }
        }

        return tasks;
    }


    /** task class list. */
    private final List<Class<?>> taskClasses;
}