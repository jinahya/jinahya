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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolver<T> {


    /**
     * 
     * @param source
     * @param target 
     */
    public void addDependency(final T source, final T target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "self dependency: " + source + " -> " + target);
        }

        synchronized (map) {

            if (target != null && hasDependency(target, source)) {
                throw new IllegalStateException(
                    "cyclic dependency: " + source + " -> [" + target + " -> "
                    + source + "]");
            }

            List<T> targets = map.get(source);

            if (targets == null) {
                targets = new ArrayList<T>();
                map.put(source, targets);
            }

            if (targets.contains(target)) {
                return;
            }

            targets.add(target);
        }
    }


    /**
     * Remove direct dependencies from given <code>source</code> to all of
     * specified <code>targets</code>.
     *
     * @param source source; may not be null
     * @param target targets; may not be null
     */
    public void removeDependency(final T source, final T target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        synchronized (map) {

            final List<T> targets = map.get(source);

            if (targets == null) {
                return;
            }

            if (targets.remove(target) && targets.isEmpty()) {
                map.remove(source);
            }
        }
    }


    /**
     * Checks if there is any direct or indirect dependency from given
     * <code>source</code> to specified <code>target</code>.
     *
     * @param source source; may not be null
     * @param target target; may be null
     * @return true if there is a dependency; false otherwise
     */
    public boolean hasDependency(final T source, final T target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (target == null) {
            throw new NullPointerException("null targets");
        }

        synchronized (map) {

            final List<T> list = map.get(source);

            if (list == null) {
                return false;
            }

            if (list.contains(target)) {
                return true;
            }

            for (T auxiliary : list) {
                if (auxiliary == null) {
                    continue;
                }
                if (hasDependency(auxiliary, target)) {
                    return true;
                }
            }

            return false;
        }
    }


    /**
     * Finds all direct or indirect dependency paths from given
     * <code>source</code> to specified <code>target</code>.
     *
     * @param source source
     * @param target target
     * @return a list of array which each is a found path
     */
    public List<List<T>> findDependencyPath(final T source, final T target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        synchronized (map) {

            final List<List<T>> paths = new ArrayList<List<T>>();

            final List<T> targets = map.get(source);

            if (targets == null) {
                return paths;
            }

            for (T auxiliary : targets) {

                if ((auxiliary == null && target == null)
                    || auxiliary.equals(target)) {

                    final List<T> path = new LinkedList<T>();
                    path.add(source);
                    path.add(auxiliary);
                    paths.add(path);
                    continue;
                }

                if (auxiliary == null) {
                    continue;
                }

                for (List<T> path : findDependencyPath(auxiliary, target)) {
                    path.add(0, source);
                    paths.add(path);
                }
            }

            return paths;
        }
    }


    /**
     * Returns a single list of all dependencies.
     *
     * @return the flatten dependency list
     */
    public List<T> getSingleGroup() {

        final List<T> group = new LinkedList<T>();

        synchronized (map) {
            for (T source : map.keySet()) {
                getSingleGroup(source, group);
            }
        }
        return group;
    }


    /**
     * Flatten dependencies from given <code>source</code>.
     *
     * @param source source
     * @param group group
     */
    private void getSingleGroup(final T source, final List<T> group) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (group == null) {
            throw new NullPointerException("null group");
        }

        synchronized (map) {

            if (group.contains(source)) {
                return;
            }

            final List<T> targets = map.get(source);
            if (targets != null) {
                for (T target : targets) {
                    if (target == null) {
                        continue;
                    }
                    getSingleGroup(target, group);
                }
            }

            group.add(source);
        }
    }


    /**
     * 
     * @param maximum the maximum number groups to get; 0 for unlimited
     * @return horizontal groups
     */
    public List<List<T>> getHorizontalGroups(final int maximum) {

        if (maximum < 0) {
            throw new IllegalArgumentException("negative maximum: " + maximum);
        }

        final List<List<T>> groups = new ArrayList<List<T>>();

        synchronized (map) {

            final List<T> single = getSingleGroup();

            while (!single.isEmpty()
                   && (maximum == 0 || (groups.size() < (maximum - 1)))) {

                final List<T> group = new ArrayList<T>();
                groups.add(group);

                group.add(single.remove(0));

                outer:
                for (int i = 0; i < single.size();) {
                    for (T g : group) {
                        if (hasDependency(single.get(i), g)) {
                            group.add(single.remove(i));
                            continue outer;
                        }
                    }
                    for (int j = i + 1; j < single.size(); j++) {
                        if (!hasDependency(single.get(j), single.get(i))) {
                            continue;
                        }
                        for (T g : group) {
                            if (hasDependency(single.get(j), g)) {
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
        }

        return groups;
    }


    public List<List<T>> getVerticalGroups(final int maximum) {

        if (maximum < 0) {
            throw new IllegalArgumentException("negative maximum: " + maximum);
        }

        final List<List<T>> groups = new ArrayList<List<T>>();

        synchronized (map) {

            final List<T> single = getSingleGroup();

            while (!single.isEmpty()
                   && (maximum == 0 || (groups.size() < (maximum - 1)))) {

                final List<T> group = new ArrayList<T>();
                groups.add(group);

                outer:
                for (int i = single.size() - 1; i >= 0; i--) {
                    for (int j = 0; j < i; j++) {
                        if (hasDependency(single.get(i), single.get(j))) {
                            continue outer;
                        }
                    }
                    group.add(single.remove(i));
                }
            }

            if (!single.isEmpty()) {
                groups.add(single);
            }
        }

        return groups;
    }


    /** map. */
    private final Map<T, List<T>> map =
        Collections.synchronizedMap(new HashMap<T, List<T>>());
}

