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
import java.util.Collection;
import java.util.Collections;
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
public class TaskContext {


    /** task index filename. */
    private static final String TASK_INDEX_FILENAME = "task.index";


    /**
     * Platform specific resource loader.
     */
    protected static interface ResourceLoader {


        /**
         * Loads resource denoted by given <code>resourceName</code> using
         * specified <code>classLoader</code>.
         *
         * @param classLoader class loader
         * @param resourceName resource name
         * @return loaded resource; never null
         * @throws IOException if an I/O error occurs
         * @throws FSMException if an error occurs
         */
        InputStream load(ClassLoader classLoader, String resourceName)
            throws IOException, FSMException;
    };


    /** Default implementation. */
    protected static final ResourceLoader DEFAULT_RESOURCE_LOADER =
        new ResourceLoader() {


            @Override
            public InputStream load(final ClassLoader classLoader,
                                    final String resourceName)
                throws IOException, FSMException {

                if (classLoader == null) {
                    throw new NullPointerException("null classLoader");
                }

                if (resourceName == null) {
                    throw new NullPointerException("null resourceName");
                }

                if (resourceName.trim().length() == 0) {
                    throw new IllegalArgumentException("empty resourceName");
                }

                final InputStream resourceStream =
                    classLoader.getResourceAsStream(resourceName);
                if (resourceName == null) {
                    throw new FSMException(
                        "failed to find resource: " + resourceName);
                }

                return resourceStream;
            }
        };


    /**
     * Loads task classes.
     *
     * @param contextPath context path
     * @param classLoader class loader
     * @return a collection of loaded task classes
     * @throws FSMException if an error occurs.
     */
    protected static Set<Class<?>> load(final String contextPath,
                                        final ClassLoader classLoader)
        throws FSMException {

        return load(contextPath, classLoader, DEFAULT_RESOURCE_LOADER);
    }


    /**
     * Loads task classes.
     *
     * @param contextPath context path
     * @param classLoader class loader
     * @param resourceLoader platform specific resource loader
     * @return a collection of loaded task classes
     * @throws FSMException if an error occurs.
     */
    protected static Set<Class<?>> load(final String contextPath,
                                        final ClassLoader classLoader,
                                        final ResourceLoader resourceLoader)
        throws FSMException {

        if (contextPath == null) {
            throw new NullPointerException("null contextPath");
        }

        if (classLoader == null) {
            throw new NullPointerException("null classLoader");
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
        if (packageNames.isEmpty()) {
            throw new FSMException("no package names parsed");
        }

        final Set<Class<?>> classes = new HashSet<Class<?>>();

        for (String packageName : packageNames) {
            final String resourceName =
                packageName.replace('.', '/') + "/" + TASK_INDEX_FILENAME;
            try {
                final InputStream resourceStream =
                    resourceLoader.load(classLoader, resourceName);
                if (resourceStream == null) {
                    throw new FSMException("resourceLoader returned null?");
                }
                try {
                    final BufferedReader reader = new BufferedReader(
                        new InputStreamReader(resourceStream, "UTF-8"));
                    try {
                        for (String line = null;
                             (line = reader.readLine()) != null;) {

                            line = line.trim();
                            if (line.length() == 0 || line.startsWith("#")) {
                                continue;
                            }
                            final String className = packageName + "." + line;
                            try {
                                classes.add(classLoader.loadClass(className));
                            } catch (ClassNotFoundException cnfe) {
                                throw new FSMException(
                                    "failed to load class: " + className, cnfe);
                            }
                        }
                    } finally {
                        reader.close();
                    }
                } finally {
                    resourceStream.close();
                }
            } catch (IOException ioe) {
                throw new FSMException(ioe);
            }
        }

        return classes;
    }


    /**
     * Creates a new instance of <code>FSMContext</code>.
     *
     * @param classes pre-loaded task classes
     * @return a new instance of <code>FSMContext</code>.
     * @throws FSMException if an error occurs.
     */
    protected static List<Task> instantiate(final Set<Class<?>> classes)
        throws FSMException {

        if (classes == null) {
            throw new NullPointerException("null classes");
        }

        final Set<String> taskIdSet = new HashSet<String>();
        final List<Task> taskList = new ArrayList<Task>(classes.size());

        for (Class<?> clazz : classes) {
            if (clazz == null) {
                throw new NullPointerException("null task class");
            }
            if (!Task.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException(
                    clazz + " is not assignable to " + Task.class);
            }
            try {
                final Constructor<?> constructor =
                    clazz.getConstructor((Class[]) null);
                try {
                    final Task task =
                        (Task) constructor.newInstance((Object[]) null);
                    final String taskId = task.getId();
                    if (!taskIdSet.add(taskId)) {
                        throw new FSMException("duplicate task id: " + taskId);
                    }
                    taskList.add(task);
                } catch (InstantiationException ie) {
                    throw new FSMException(ie);
                } catch (IllegalAccessException iae) {
                    throw new FSMException(iae);
                } catch (InvocationTargetException ite) {
                    throw new FSMException(ite);
                }
            } catch (NoSuchMethodException nsme) {
                throw new FSMException(nsme);
            }
        }

        return taskList;
    }


    /**
     * 
     * @param contextPath
     * @return
     * @throws FSMException 
     */
    public static TaskContext newInstance(final String contextPath)
        throws FSMException {

        return newInstance(contextPath, DEFAULT_RESOURCE_LOADER);
    }


    /**
     * 
     * @param contextPath
     * @param resourceLoader
     * @return
     * @throws FSMException 
     */
    public static TaskContext newInstance(final String contextPath,
                                          final ResourceLoader resourceLoader)
        throws FSMException {

        final ClassLoader classLoader =
            Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            throw new FSMException("no context class loader");
        }

        return newInstance(contextPath, classLoader, resourceLoader);
    }


    /**
     * 
     * @param contextPath
     * @param classLoader
     * @return
     * @throws FSMException 
     */
    public static TaskContext newInstance(final String contextPath,
                                          final ClassLoader classLoader)
        throws FSMException {

        return newInstance(contextPath, classLoader, DEFAULT_RESOURCE_LOADER);
    }


    /**
     * 
     * @param contextPath
     * @param classLoader
     * @param resourceLoader
     * @return
     * @throws FSMException 
     */
    public static TaskContext newInstance(
        final String contextPath, final ClassLoader classLoader,
        final ResourceLoader resourceLoader)
        throws FSMException {

        return new TaskContext(load(contextPath, classLoader, resourceLoader));
    }


    /**
     * Creates a new instance.
     *
     * @param classes task classes
     */
    protected TaskContext(final Set<Class<?>> classes) {

        super();

        if (classes == null) {
            throw new NullPointerException("null classes");
        }

        for (Class<?> clazz : classes) {
            if (clazz == null) {
                throw new NullPointerException("null class");
            }
            if (!Task.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException(
                    clazz + " is not assignable to " + Task.class);
            }
        }

        this.classes = new HashSet<Class<?>>(classes);
    }


    /**
     * Returns an unmodifiable collection of task instances.
     *
     * @return an unmodifiable collection of task instances.
     * @throws FSMException if an error occurs.
     */
    public synchronized Collection<Task> getTasks() throws FSMException {

        if (tasks == null) {
            tasks = instantiate(classes);
        }

        return Collections.unmodifiableCollection(tasks);
    }


    /**
     * Returns an unmodifiable map of task if and tasks.
     *
     * @return a map of task id and tasks.
     * @throws FSMException if an error occurs.
     */
    public synchronized Map<String, Task> getTaskMap() throws FSMException {

        final Map<String, Task> taskMap = new HashMap<String, Task>();

        for (Task task : getTasks()) {
            taskMap.put(task.getId(), task);
        }

        return Collections.unmodifiableMap(taskMap);
    }


    /** classes. */
    private final Set<Class<?>> classes;


    /** tasks. */
    private volatile List<Task> tasks = null;
}

