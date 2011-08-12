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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class TaskFactory {


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
        InputStream loadResource(ClassLoader classLoader, String resourceName)
            throws IOException, FSMException;
    };


    /** Default implementation. */
    protected static final ResourceLoader DEFAULT_RESOURCE_LOADER =
        new ResourceLoader() {


            @Override
            public InputStream loadResource(final ClassLoader classLoader,
                                            final String resourceName)
                throws IOException, FSMException {

                if (classLoader == null) {
                    throw new NullPointerException("null classLoader");
                }

                if (resourceName == null) {
                    throw new NullPointerException("null resourceName");
                }

                if (resourceName.trim().isEmpty()) {
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
     * Creates a new instance of <code>FSMContext</code>.
     *
     * @param contextPath context path
     * @param classLoader class loader
     * @return a new instance of <code>FSMContext</code>
     * @throws FSMException if an error occurs.
     */
    public static Task[] load(final String contextPath,
                              final ClassLoader classLoader)
        throws FSMException {

        return load(contextPath, classLoader, DEFAULT_RESOURCE_LOADER);
    }


    /**
     * Creates a new instance of <code>FSMContext</code>.
     *
     * @param contextPath context path
     * @param classLoader class loader
     * @param resourceLoader platform specific resource loader
     * @return an array of loaded tasks
     * @throws FSMException if an error occurs.
     */
    public static Task[] load(final String contextPath,
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
            if (!packageName.trim().isEmpty()) {
                packageNames.add(packageName);
            }
        }
        if (packageNames.isEmpty()) {
            throw new FSMException("no package names parsed");
        }

        final List<Class<?>> taskClassList = new ArrayList<Class<?>>();

        for (String packageName : packageNames) {
            final String resourceName =
                packageName.replace('.', '/') + "/" + TASK_INDEX_FILENAME;
            try {
                final InputStream resourceStream =
                    resourceLoader.loadResource(classLoader, resourceName);
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
                            if (line.isEmpty() || line.startsWith("#")) {
                                continue;
                            }
                            final String className = packageName + "." + line;
                            try {
                                final Class<?> taskClass =
                                    classLoader.loadClass(className);
                                if (Task.class.isAssignableFrom(taskClass)) {
                                    throw new FSMException(
                                        "Task class(" + taskClass
                                        + ") is not assignable to "
                                        + Task.class);
                                }
                                taskClassList.add(
                                    taskClass.asSubclass(Task.class));
                            } catch (ClassNotFoundException cnfe) {
                                throw new FSMException(cnfe);
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

        if (taskClassList.isEmpty()) {
            throw new FSMException("no task classes are loaded");
        }

        final Class<?>[] taskClasses = new Class<?>[taskClassList.size()];
        taskClassList.toArray(taskClasses);

        return load(taskClasses);
    }


    /**
     * Creates a new instance of <code>FSMContext</code>.
     *
     * @param taskClasses pre-loaded task classes
     * @return a new instance of <code>FSMContext</code>.
     * @throws FSMException if an error occurs.
     */
    public static Task[] load(final Class<?>[] taskClasses)
        throws FSMException {

        if (taskClasses == null) {
            throw new NullPointerException("null taskClasses");
        }

        if (taskClasses.length == 0) {
            throw new NullPointerException("empty taskClasses");
        }

        for (int i = 0; i < taskClasses.length; i++) {
            if (taskClasses[i] == null) {
                throw new NullPointerException(
                    "null at taskClasses[" + i + "]");
            }
            if (!Task.class.isAssignableFrom(taskClasses[i])) {
                throw new IllegalArgumentException(
                    "taskClasses[" + i + "](" + taskClasses[i]
                    + ") is not assignable to " + Task.class);
            }
            for (int j = 0; j < i; j++) {
                if (taskClasses[j].equals(taskClasses[i])) {
                    throw new IllegalArgumentException(
                        "duplicate taskClass: " + taskClasses[i]);
                }
            }
        }

        final Task[] tasks = new Task[taskClasses.length];
        for (int i = 0; i < tasks.length; i++) {
            try {
                tasks[i] = (Task) taskClasses[i].newInstance();
            } catch (InstantiationException ie) {
                throw new FSMException(ie);
            } catch (IllegalAccessException iae) {
                throw new FSMException(iae);
            }
        }

        return tasks;
    }


    /**
     * Creates a new instance.
     */
    protected TaskFactory() {
        super();
    }
}

