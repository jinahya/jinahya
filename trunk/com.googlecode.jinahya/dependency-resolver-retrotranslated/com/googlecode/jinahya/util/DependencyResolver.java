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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Dependency resolving utility.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <E> element type parameter
 */
public class DependencyResolver<E> {


    private DependencyResolver(final Map<E, List<E>> map) {

        super();

        if (map == null) {
            throw new NullPointerException("map");
        }

        this.map = map;
    }


    public DependencyResolver() {

        this(new HashMap<E, List<E>>());
    }


    /**
     * Adds direct dependencies from {@code source} to each of {@code targets}.
     *
     * @param source the source
     * @param targets the targets
     */
    public void add(final E source, final Collection<? extends E> targets) {

        if (source == null) {
            throw new NullPointerException("source");
        }

        if (targets == null) {
            throw new NullPointerException("targets");
        }

        List<E> list = map.get(source);
        if (list == null) {
            list = new ArrayList<E>(); // default capacity of 10?
            list.add(null);
            map.put(source, list);
        }

        for (final E target : targets) {

            if (source.equals(target)) {
                throw new IllegalArgumentException(
                    "source(" + source + ") is equals to one of targets("
                    + target + ")");
            }

            if (target != null && contains(target, source)) {
                throw new IllegalStateException(
                    "there is already a dependency to one of targets("
                    + target + ") from the source(" + source + ")");
            }

            if (!list.contains(target)) {
                list.add(target);
            }
        }
    }


    /**
     * Adds direct dependencies from {@code source} to each of {@code targets}.
     *
     * @param source the source
     * @param targets the targets
     *
     * @see #add(java.lang.Object, java.util.Collection)
     */
    public void add(final E source, final E... targets) {

        if (targets == null) {
            throw new NullPointerException("targets");
        }

        add(source, Arrays.asList(targets));
    }


    /**
     * Removes direct dependencies from {@code source} to each of
     * {@code targets}.
     *
     * @param source the source
     * @param targets the targets
     */
    public void remove(final E source, final Collection<? extends E> targets) {

        if (source == null) {
            throw new NullPointerException("source");
        }

        if (targets == null) {
            throw new NullPointerException("targets");
        }

        final List<E> list = map.get(source);
        if (list == null) {
            return;
        }

        for (final E target : targets) {
            if (list.remove(target) && list.isEmpty()) {
                map.remove(source);
                return;
            }
        }

        if (!list.isEmpty() && !list.contains(null)) {
            list.add(0, null);
        }
    }


    /**
     * Removes direct dependencies from {@code source} to each of
     * {@code targets}.
     *
     * @param source source
     * @param targets targets
     *
     * @see #remove(java.lang.Object, java.util.Collection)
     */
    public void remove(final E source, final E... targets) {

        if (targets == null) {
            throw new NullPointerException("targets");
        }

        remove(source, Arrays.asList(targets));
    }


    /**
     * Checks if there is a direct or indirect dependency from {@code source} to
     * {@code target}.
     *
     * @param source the source
     * @param target the target
     *
     * @return true if there is a direct or indirect dependency from
     * {@code source} to {@code target}; false if there is none.
     */
    public boolean contains(final E source, final E target) {

        if (source == null) {
            throw new NullPointerException("source");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "source(" + source + ") is equals to target(" + target + ")");
        }

        final List<E> list = map.get(source);

        if (list == null) {
            return false;
        }

        if (list.contains(target)) {
            return true;
        }

        for (final E auxiliary : list) {
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
     * Checks if given {@code source} is dependent on all of specified
     * {@code targets}.
     *
     * @param source the source
     * @param targets the targets
     *
     * @return {@code true} if {@code source} is dependent on all of
     * {@code targets}; {@code false} if there is no dependency from
     * {@code source} to any of {@code targets} at all
     */
    public boolean containsAll(final E source,
                               final Collection<? extends E> targets) {

        if (source == null) {
            throw new NullPointerException("source");
        }

        if (targets == null) {
            throw new NullPointerException("targets");
        }

        for (final E target : targets) {
            if (!contains(source, target)) {
                return false;
            }
        }

        return true;
    }


    /**
     * Check if given {@code source} is dependent on all of specified
     * {@code targets}.
     *
     * @param source the source
     * @param targets the targets
     *
     * @return {@code true} if {@code source} is dependent on all of
     * {@code targets}; {@code false} otherwise
     *
     * @see #containsAll(java.lang.Object, java.util.Collection)
     */
    public boolean containsAll(final E source, final E... targets) {

        if (targets == null) {
            throw new NullPointerException("targets");
        }

        return containsAll(source, Arrays.asList(targets));
    }


    /**
     * Checks if given {@code source} is dependent on any of specified
     * {@code targets}.
     *
     * @param source the source
     * @param targets the targets
     *
     * @return {@code true} if {@code source} is dependent on any of
     * {@code targets}; {@code false} otherwise
     */
    public boolean containsAny(final E source,
                               final Collection<? extends E> targets) {

        if (source == null) {
            throw new NullPointerException("source");
        }

        if (targets == null) {
            throw new NullPointerException("targets");
        }

        for (final E target : targets) {
            if (contains(source, target)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Checks if given {@code source} is dependent on any of specified
     * {@code targets}.
     *
     * @param source the source
     * @param targets the targets
     *
     * @return {@code true} if {@code source} is dependent on any of
     * {@code targets}; {@code false} otherwise
     *
     * @see #containsAny(java.lang.Object, java.util.Collection)
     */
    public boolean containsAny(final E source, final E... targets) {

        if (targets == null) {
            throw new NullPointerException("targets");
        }

        return containsAny(source, Arrays.asList(targets));
    }


    /**
     * Finds all direct or indirect dependency paths from given {@code source}
     * to specified {@code target}. Note that the first element in each list is
     * always the specified {@code source}.
     *
     * @param source the source
     * @param target the target
     *
     * @return a list of possible direct or indirect dependency paths from
     * {@code source} to {@code target}; possibly empty.
     */
    public List<List<E>> getPaths(final E source, final E target) {

        if (source == null) {
            throw new NullPointerException("source");
        }

        if (target == null) {
            throw new NullPointerException("target");
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

        for (final E auxiliary : targets) {

            if (auxiliary == null) {
                continue;
            }

            if (auxiliary.equals(target)) {
                final List<E> path = new ArrayList<E>();
                path.add(source);
                path.add(auxiliary);
                paths.add(path);
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
     * Finds and adds all direct or indirect targets from given {@code source}.
     *
     * @param source source
     * @param group target group
     */
    private void getSingleGroup(final E source, final List<E> group) {

        if (group.contains(source)) {
            return;
        }

        final List<E> list = map.get(source);
        if (list != null) {
            for (final E target : list) {
                if (target == null) {
                    continue;
                }
                getSingleGroup(target, group);
            }
        }

        group.add(source);
    }


    /**
     * Returns a list of all elements in order.
     *
     * @return a list of all elements in order.
     */
    public List<E> getSingleGroup() {

        final List<E> group = new ArrayList<E>();

        for (final E source : map.keySet()) {
            getSingleGroup(source, group);
        }

        return group;
    }


    /**
     * Returns a list of horizontal dependency groups. Each group in the list
     * can be processed concurrently but all elements in a group must be
     * processed in order.
     *
     * @return a list of horizontal groups
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

        return groups;
    }


    /**
     * Returns a list of vertical dependency groups. Each element in a group can
     * be processed concurrently but all groups in the list must be processed in
     * order.
     *
     * @return a list of vertical groups
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

        return groups;
    }


    /**
     * Clears this instance.
     */
    public void clear() {
        map.clear();
    }


    /**
     * map.
     */
    private final Map<E, List<E>> map;


}
