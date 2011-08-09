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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolver {


    /**
     * Creates a new instance.
     */
    public DependencyResolver() {
        super();

        map = new HashMap();
    }


    public void addSource(final String source) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        synchronized (map) {

            if (!map.containsKey(source)) {
                map.put(source, new ArrayList());
            }
        }
    }


    /**
     * Adds a direct path from <code>source</code> to <code>target</code>.
     *
     * @param source source
     * @param target target
     */
    public void addDependency(final String source, final String target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (target == null) {
            throw new NullPointerException("null target");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "self dependency: " + source + " -> " + target);
        }

        if (hasDependency(target, source)) {
            throw new IllegalStateException(
                "cyclic dependency: " + source + " -> [" + target + " -> "
                + source + "]");
        }

        synchronized (map) {

            List targets = (List) map.get(source);

            if (targets == null) {
                targets = new ArrayList();
                map.put(source, targets);
            }

            if (!targets.contains(target)) {
                targets.add(target);
            }
        }
    }


    /**
     * Removes direct path from <code>source</code> to <code>target</code>
     * if exists.
     *
     * @param source source
     * @param target target
     */
    public void removeDependency(final String source, final String target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (target == null) {
            throw new NullPointerException("null target");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "self dependency: " + source + " -> " + target);
        }

        synchronized (map) {

            final List targets = (List) map.get(source);

            if (targets == null) {
                return;
            }

            if (targets.remove(target) && targets.isEmpty()) {
                map.remove(source);
            }
        }
    }


    /**
     * Checks if there is any direct or indirect path from <code>source</code>
     * to <code>target</code>.
     *
     * @param source source
     * @param target target
     * @return true if there is a path; false otherwise
     */
    public boolean hasDependency(final String source, final String target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (target == null) {
            throw new NullPointerException("null target");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "self dependency: " + source + " -> " + target);
        }

        synchronized (map) {

            final List targets = (List) map.get(source);

            if (targets == null) {
                return false;
            }

            if (targets.contains(target)) {
                return true;
            }

            for (Iterator i = targets.iterator(); i.hasNext();) {
                if (hasDependency((String) i.next(), target)) {
                    return true;
                }
            }

            return false;
        }
    }


    /**
     * Finds all direct or indirect paths from <code>source</code> to
     * <code>target</code>.
     *
     * @param source source
     * @param target target
     * @return a list of array which each is a found path
     */
    public List findDependencies(final String source, final String target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (target == null) {
            throw new NullPointerException("null target");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "self dependency: " + source + " -> " + target);
        }

        synchronized (map) {

            final List dependencies = new ArrayList();

            final List targets = (List) map.get(source);

            if (targets == null) {
                return dependencies;
            }

            for (Iterator i = targets.iterator(); i.hasNext();) {

                final String auxiliary = (String) i.next();

                if (auxiliary.equals(target)) {
                    final List path = new ArrayList();
                    path.add(source);
                    path.add(auxiliary);
                    dependencies.add(path);
                    continue;
                }

                final List dependencies_ = findDependencies(auxiliary, target);
                for (Iterator j = dependencies_.iterator(); j.hasNext();) {
                    final List path = (List) j.next();
                    path.add(0, source);
                    dependencies.add(path);
                }
            }

            return dependencies;
        }
    }


    /** table. */
    private final Map map; // <String, List<String>>
}

