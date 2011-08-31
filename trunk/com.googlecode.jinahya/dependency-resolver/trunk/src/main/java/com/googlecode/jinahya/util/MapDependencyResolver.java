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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Dependency resolving utility.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class MapDependencyResolver<T> implements DependencyResolver<T> {


    /**
     * Returns a synchronized wrapper of given <code>resolver</code>.
     *
     * @param resolver resolver to be synchronized
     * @return a synchronized wrapper
     */
    public static DependencyResolver synchronizedInstance(
        final MapDependencyResolver resolver) {

        if (resolver == null) {
            throw new NullPointerException("null resolver");
        }

        return new SynchronizedDependencyResolver(resolver, resolver.map);
    }


    /**
     * {@inheritDoc }
     */
    @Override
    public void addDependencies(final T source, final T... targets)
        throws DependencyResolverException {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (targets == null) {
            throw new NullPointerException("null targets");
        }

        for (T target : targets) {
            addDependency(source, target);
        }
    }


    /**
     * {@inheritDoc }
     */
    @Override
    public void addDependency(final T source, final T target)
        throws DependencyResolverException {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "source(" + source + ") is equals to target(" + target + ")");
        }

        if (target != null && hasDependency(target, source)) {
            throw new CyclicDependencyException(source, target);
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


    /**
     * {@inheritDoc }
     */
    @Override
    public void removeDependencies(final T source, final T... targets) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (targets == null) {
            throw new NullPointerException("null targets");
        }

        for (T target : targets) {
            removeDependency(source, target);
        }
    }


    /**
     * {@inheritDoc }
     */
    @Override
    public void removeDependency(final T source, final T target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        final List<T> targets = map.get(source);

        if (targets == null) {
            return;
        }

        if (targets.remove(target) && targets.isEmpty()) {
            map.remove(source);
        }
    }


    /**
     * {@inheritDoc }
     */
    @Override
    public boolean hasDependencies(final T source, final T... targets) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (targets == null) {
            throw new NullPointerException("null targets");
        }

        for (T target : targets) {
            if (!hasDependency(source, target)) {
                return false;
            }
        }

        return true;
    }


    /**
     * {@inheritDoc }
     */
    @Override
    public boolean hasDependency(final T source, final T target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

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


    /**
     * {@inheritDoc }
     */
    @Override
    public List<List<T>> getDependencyPaths(final T source, final T target) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "source(" + source + ") is equals to target(" + target + ")");
        }

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

            for (List<T> path : getDependencyPaths(auxiliary, target)) {
                path.add(0, source);
                paths.add(path);
            }
        }

        return paths;
    }


    /**
     * {@inheritDoc }
     */
    @Override
    public List<T> getSingleGroup() {

        final List<T> group = new LinkedList<T>();

        for (T source : map.keySet()) {
            getSingleGroup(source, group);
        }

        return group;
    }


    /**
     * {@inheritDoc }
     */
    private void getSingleGroup(final T source, final List<T> group) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        if (group == null) {
            throw new NullPointerException("null group");
        }

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


    /**
     * {@inheritDoc }
     */
    @Override
    public List<List<T>> getHorizontalGroups() {

        final List<List<T>> groups = new ArrayList<List<T>>();

        final List<T> single = getSingleGroup();

        while (!single.isEmpty()) {

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

        return groups;
    }


    /**
     * {@inheritDoc }
     */
    @Override
    public List<List<T>> getVerticalGroups() {

        final List<List<T>> groups = new ArrayList<List<T>>();

        final List<T> single = getSingleGroup();

        while (!single.isEmpty()) {

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

        return groups;
    }


    /** map. */
    private final Map<T, List<T>> map = new HashMap<T, List<T>>();
}
