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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Dependency resolving utility.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolver<E> implements Serializable {


    /**
     * Returns a synchronized instance.
     *
     * @param <T> type parameter
     * @return a synchronized instance
     */
    public static <T> DependencyResolver<T> synchronizedInstance() {

        return new DependencyResolver<T>() {


            @Override
            public synchronized void add(T source, T... targets)
                throws DependencyResolverException {

                super.add(source, targets);
            }


            @Override
            public synchronized void remove(T source, T... targets) {
                super.remove(source, targets);
            }


            @Override
            public synchronized boolean contains(T source, T target) {
                return super.contains(source, target);
            }


            @Override
            public synchronized boolean containsAll(T source, T... target) {
                return super.containsAll(source, target);
            }


            @Override
            public synchronized boolean containsAny(T source, T... targets) {
                return super.containsAny(source, targets);
            }


            @Override
            public synchronized List<List<T>> getPaths(T source, T target) {
                return super.getPaths(source, target);
            }


            @Override
            public synchronized List<T> getSingleGroup() {
                return super.getSingleGroup();
            }


            @Override
            public synchronized List<List<T>> getHorizontalGroups() {
                return super.getHorizontalGroups();
            }


            @Override
            public synchronized List<List<T>> getVerticalGroups() {
                return super.getVerticalGroups();
            }


            @Override
            public synchronized void clear() {
                super.clear();
            }


        };
    }


    /**
     * Adds dependencies from <code>source</code> to each of
     * <code>targets</code>.
     *
     * @param source source
     * @param targets targets
     * @throws DependencyResolverException if an error occurs.
     */
    public void add(final E source, final E... targets)
        throws DependencyResolverException {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (targets == null) {
            throw new NullPointerException("null targets");
        }

        if (targets.length == 0) {
            throw new IllegalArgumentException("empty targets");
        }

        List<E> list = map.get(source);

        for (E target : targets) {

            if (source.equals(target)) {
                throw new DependencyResolverException("self dependency");
            }
            if (target != null && contains(target, source)) {
                throw new CyclicDependencyException(source, target);
            }

            if (list == null) {
                list = new ArrayList<E>();
                map.put(source, list);
            }

            if (!list.contains(target)) {
                list.add(target);
            }
        }
    }


    /**
     * Removes direct dependencies from <code>source</code> to each of
     * <code>targets</code>.
     *
     * @param source source
     * @param targets targets
     */
    public void remove(final E source, final E... targets) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (targets == null) {
            throw new NullPointerException("null targets");
        }

        final List<E> list = map.get(source);

        if (list == null) {
            return;
        }

        for (E target : targets) {
            if (list.remove(target) && list.isEmpty()) {
                map.remove(source);
                return;
            }
        }
    }


    /**
     * Check if all of specified <code>targets</code> has a direct or indirect
     * dependency from given <code>source</code>.
     *
     * @param source source
     * @param targets targets
     * @return true if <code>source</code> has dependencies to all of
     *         <code>targets</code>; false if there is no dependency from
     *         <code>source</code> to any of <code>targets</code>.
     */
    public boolean containsAll(final E source, final E... targets) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (targets == null) {
            throw new NullPointerException("null targets");
        }

        for (E target : targets) {
            if (!contains(source, target)) {
                return false;
            }
        }

        return true;
    }


    /**
     * Checks if there is any direct or indirect dependency from
     * <code>source</code> to any of <code>targets</code>.
     *
     * @param source source
     * @param targets targets
     * @return true if there is a dependency; false if there is none.
     */
    public boolean containsAny(final E source, final E... targets) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (targets == null) {
            throw new NullPointerException("null targets");
        }

        for (E target : targets) {
            if (contains(source, target)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Checks if there is a dependency from <code>source</code> to
     * <code>target</code>.
     *
     * @param source source
     * @param target target
     * @return true if there is a direct or indirect dependency from
     *         <code>source</code> to <code>target</code>; false if there is
     *         none.
     */
    public boolean contains(final E source, final E target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        final List<E> list = map.get(source);

        if (list == null) {
            return false;
        }

        if (list.contains(target)) {
            return true;
        }

        for (E auxiliary : list) {
            if (auxiliary == null) {
                continue;
            }
            if (contains(auxiliary, target)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Finds all direct or indirect paths from given <code>source</code> to
     * specified <code>target</code>.
     *
     * @param source source
     * @param target target
     * @return paths from <code>source</code> to <code>target</code>.
     */
    public List<List<E>> getPaths(final E source, final E target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "source(" + source + ") is equals to target(" + target + ")");
        }

        final List<List<E>> paths = new ArrayList<List<E>>();

        final List<E> targets = map.get(source);

        if (targets == null) {
            return paths;
        }

        for (E auxiliary : targets) {

            if ((auxiliary == null && target == null)
                || auxiliary.equals(target)) {

                final List<E> path = new LinkedList<E>();
                path.add(source);
                path.add(auxiliary);
                paths.add(path);
                continue;
            }

            if (auxiliary == null) {
                continue;
            }

            for (List<E> path : getPaths(auxiliary, target)) {
                path.add(0, source);
                paths.add(path);
            }
        }

        return paths;
    }


    /**
     * Returns a single group of all dependencies in order.
     *
     * @return a single dependency group
     */
    public List<E> getSingleGroup() {

        final List<E> group = new LinkedList<E>();

        for (E source : map.keySet()) {
            getSingleGroup(source, group);
        }

        return group;
    }


    /**
     * Returns a list of horizontal dependency groups. Each group can be
     * processed concurrently but all elements in a group must be processed in
     * order.
     *
     * @return horizontal groups
     */
    private void getSingleGroup(final E source, final List<E> group) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (group == null) {
            throw new NullPointerException("null group");
        }

        if (group.contains(source)) {
            return;
        }

        final List<E> targets = map.get(source);
        if (targets != null) {
            for (E target : targets) {
                if (target == null) {
                    continue;
                }
                getSingleGroup(target, group);
            }
        }

        group.add(source);
    }


    /**
     * Returns a list of vertical dependency groups. Each group can be processed
     * concurrently but all elements in a group must be processed in order.
     *
     * @return vertical groups
     */
    public List<List<E>> getHorizontalGroups() {

        final List<List<E>> groups = new ArrayList<List<E>>();

        final List<E> single = getSingleGroup();

        while (!single.isEmpty()) {

            final List<E> group = new ArrayList<E>();
            groups.add(group);

            group.add(single.remove(0));

            outer:
            for (int i = 0; i < single.size();) {
                for (E g : group) {
                    if (contains(single.get(i), g)) {
                        group.add(single.remove(i));
                        continue outer;
                    }
                }
                for (int j = i + 1; j < single.size(); j++) {
                    if (!contains(single.get(j), single.get(i))) {
                        continue;
                    }
                    for (E g : group) {
                        if (contains(single.get(j), g)) {
                            group.add(single.remove(i));
                            continue outer;
                        }
                    }
                }
                i++;
            }
        }

        if (!single.isEmpty()) {
            groups.add(single);
        }

        return groups;
    }


    /**
     * Returns a list of vertical dependency groups. Each element in a group can
     * be processed concurrently but all groups must be processed in order.
     *
     * @return vertical groups
     */
    public List<List<E>> getVerticalGroups() {

        final List<List<E>> groups = new ArrayList<List<E>>();

        final List<E> single = getSingleGroup();

        while (!single.isEmpty()) {

            final List<E> group = new ArrayList<E>();
            groups.add(group);

            outer:
            for (int i = single.size() - 1; i >= 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (contains(single.get(i), single.get(j))) {
                        continue outer;
                    }
                }
                group.add(single.remove(i));
            }
        }

        if (!single.isEmpty()) {
            groups.add(single);
        }

        return groups;
    }


    /**
     * Removes all dependencies from this resolver.
     */
    public void clear() {
        map.clear();
    }


    /** map. */
    private final Map<E, List<E>> map = new HashMap<E, List<E>>();


}

