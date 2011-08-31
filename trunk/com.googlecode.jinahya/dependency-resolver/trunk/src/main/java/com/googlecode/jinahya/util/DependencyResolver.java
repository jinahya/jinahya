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


package com.googlecode.jinahya.util;


import java.util.List;


/**
 * Dependency resolving utility.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface DependencyResolver<T> {


    public static class SynchronizedDependencyResolver<T>
        implements DependencyResolver<T> {


        SynchronizedDependencyResolver(DependencyResolver<T> resolver,
                                       Object mutex) {
            super();

            if (resolver == null) {
                throw new NullPointerException("null resolver");
            }

            if (mutex == null) {
                throw new NullPointerException("null mutex");
            }

            this.resolver = resolver;
            this.mutex = mutex;
        }


        @Override
        public void addDependencies(T source, T... targets)
            throws DependencyResolverException {

            synchronized (mutex) {
                resolver.addDependencies(source, targets);
            }
        }


        @Override
        public void addDependency(T source, T target)
            throws DependencyResolverException {

            synchronized (mutex) {
                resolver.addDependency(source, target);
            }
        }


        @Override
        public List<List<T>> getDependencyPaths(T source, T target) {

            synchronized (mutex) {
                return resolver.getDependencyPaths(source, target);
            }
        }


        @Override
        public List<List<T>> getHorizontalGroups() {

            synchronized (mutex) {
                return resolver.getHorizontalGroups();
            }
        }


        @Override
        public List<T> getSingleGroup() {

            synchronized (mutex) {
                return resolver.getSingleGroup();
            }
        }


        @Override
        public List<List<T>> getVerticalGroups() {

            synchronized (mutex) {
                return resolver.getVerticalGroups();
            }
        }


        @Override
        public boolean hasDependencies(T source, T... targets) {

            synchronized (mutex) {
                return resolver.hasDependencies(source, targets);
            }
        }


        @Override
        public boolean hasDependency(T source, T target) {

            synchronized (mutex) {
                return resolver.hasDependency(source, target);
            }
        }


        @Override
        public void removeDependencies(T source, T... targets) {

            synchronized (mutex) {
                resolver.removeDependencies(source, targets);
            }
        }


        public void removeDependency(T source, T target) {
            synchronized (mutex) {
                resolver.removeDependency(source, target);
            }
        }


        /** resolver. */
        final DependencyResolver<T> resolver;


        /** mutex. */
        final Object mutex;
    }


    /**
     * Adds direct dependencies from <code>source</code> to each of
     * <code>targets</code>.
     *
     * @param source source; may not be null
     * @param targets targets; may not be null; each element may be null
     * @throws DependencyResolverException if an error occurs
     */
    void addDependencies(final T source, final T... targets)
        throws DependencyResolverException;


    /**
     * Adds a direct dependency from given <code>source</code> to specified
     * <code>target</code>.
     *
     * @param source source; may not be null
     * @param target target; may be null
     * @throws DependencyResolverException if failed to add dependency.
     */
    void addDependency(final T source, final T target)
        throws DependencyResolverException;


    /**
     * Removes direct dependencies from specified <code>source</code> to each of
     * specified <code>targets</code>.
     *
     * @param source source; may not be null
     * @param targets targets; may not be null; each element may be null
     */
    void removeDependencies(final T source, final T... targets);


    /**
     * Remove a direct dependency from given <code>source</code> to specified
     * <code>target</code>.
     *
     * @param source source; may not be null
     * @param target target; may be null
     */
    void removeDependency(final T source, final T target);


    /**
     * Checks if given <code>source</code> has direct or indirect dependencies
     * to all of <code>targets</code>.
     *
     * @param source source; may not be null
     * @param targets targets; may not be null; each element may be null
     * @return true if all targets are dependent from source; false otherwise
     */
    boolean hasDependencies(final T source, final T... targets);


    /**
     * Checks if there is any direct or indirect dependency from
     * <code>source</code> to <code>target</code>.
     *
     * @param source source; may not be null
     * @param target target; may be null
     * @return true if there is a dependency; false otherwise
     */
    boolean hasDependency(final T source, final T target);


    /**
     * Finds all direct or indirect dependency paths from given
     * <code>source</code> to specified <code>target</code>.
     *
     * @param source source
     * @param target target
     * @return dependency paths from <code>source</code> to <code>target</code>.
     */
    public List<List<T>> getDependencyPaths(final T source, final T target);


    /**
     * Returns a single group of all dependencies in order.
     *
     * @return a single dependency group
     */
    List<T> getSingleGroup();


    /**
     * Returns a list of horizontal dependency groups. Each group can be
     * processed concurrently but all elements in a group must be processed in
     * order.
     *
     * @return horizontal groups
     */
    List<List<T>> getHorizontalGroups();


    /**
     * Returns a list of vertical dependency groups. Each element in a group can
     * be processed concurrently but all groups must be processed in order.
     *
     * @return vertical groups
     */
    List<List<T>> getVerticalGroups();
}
