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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class TaskContext {


    /** task index filename. */
    private static final String TASK_INDEX_FILENAME = "task.index";


    /**
     * Loads task classes.
     *
     * @param contextPath context path
     * @param resourceLoader resource loader
     * @return a set of indexed task class names.
     * @throws FSMException if an error occurs.
     */
    public static Set<String> getClassNames(final String contextPath,
                                            final ResourceLoader resourceLoader)
        throws FSMException {

        if (contextPath == null) {
            throw new NullPointerException("null contextPath");
        }

        if (resourceLoader == null) {
            throw new NullPointerException("null resourceLoader");
        }

        final Set<String> packageNames = new HashSet<String>();
        final StringTokenizer tokenizer = new StringTokenizer(contextPath, ":");
        while (tokenizer.hasMoreTokens()) {
            final String packageName = tokenizer.nextToken();
            if (packageName.trim().length() > 0) {
                packageNames.add(packageName);
            }
        }

        final Set<String> classNames = new HashSet<String>();

        for (String packageName : packageNames) {
            final String resourceName =
                packageName.replace('.', '/') + "/" + TASK_INDEX_FILENAME;
            try {
                final InputStream resource = resourceLoader.load(resourceName);
                if (resource == null) {
                    throw new FSMException("resourceLoader loaded null?");
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

        return classNames;
    }


    /**
     * Creates a new instance.
     *
     * @param classNames task class names
     */
    public TaskContext(final Set<String> classNames) {

        super();

        if (classNames == null) {
            throw new NullPointerException("null classNames");
        }

        for (String className : classNames) {
            if (className == null) {
                throw new NullPointerException("null className");
            }
        }

        this.classNames = new HashSet<String>(classNames);
    }


    /**
     * Returns a map of task id and task instances.
     *
     * @return a map of task id and task instances.
     * @throws FSMException if an error occurs.
     */
    public synchronized final Map<String, Task> getTaskMap()
        throws FSMException {

        if (tasks == null) {
            tasks = new HashMap<String, Task>();
            for (String className : classNames) {
                final Class<?> loaded = loadClass(className);
                if (!Task.class.isAssignableFrom(loaded)) {
                    throw new FSMException(
                        "loaded task class(" + loaded
                        + ") is not assignable to " + Task.class);
                }
                try {
                    final Constructor<?> constructor =
                        loaded.getConstructor((Class[]) null);
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
                            "failed to create a new instance: " + loaded, ie);
                    } catch (IllegalAccessException iae) {
                        throw new FSMException(
                            "failed to create a new instance: " + loaded, iae);
                    } catch (InvocationTargetException ite) {
                        throw new FSMException(
                            "failed to create a new instance: " + loaded, ite);
                    }
                } catch (NoSuchMethodException nsme) {
                    throw new FSMException(
                        "no default constructor: " + loaded, nsme);
                }
            }
        }

        return new HashMap<String, Task>(tasks);
    }


    /**
     * Returns a collection of task instances.
     *
     * @return a collection of task instances.
     * @throws FSMException if an error occurs.
     */
    public synchronized final Collection<Task> getTasks() throws FSMException {

        return getTaskMap().values();
    }


    /**
     * Load class named as given <code>className</code>. Default implementation
     * uses {@link Class#forName(java.lang.String) }. Override this method if
     * any customizations are requires.
     *
     * @param className class name
     * @return loaded class
     * @throws FSMException if an error occurs.
     */
    protected Class<?> loadClass(final String className) throws FSMException {

        if (className == null) {
            throw new NullPointerException("null className");
        }

        if (className.trim().length() == 0) {
            throw new IllegalArgumentException("empty className");
        }

        try {
            return Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            throw new FSMException(cnfe);
        }
    }


    /** task class names. */
    private final Set<String> classNames;


    /** tasks. */
    private volatile Map<String, Task> tasks = null;
}

