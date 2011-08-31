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
public class SynchronizedDependencyResolver<T>
    implements DependencyResolver<T> {


    /**
     * Creates a new instance.
     *
     * @param resolver resolver
     */
    public SynchronizedDependencyResolver(DependencyResolver<T> resolver) {
        this(resolver, resolver);
    }


    /**
     * Creates a new instance.
     *
     * @param resolver resolver
     * @param mutex mutex
     */
    protected SynchronizedDependencyResolver(DependencyResolver<T> resolver,
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
