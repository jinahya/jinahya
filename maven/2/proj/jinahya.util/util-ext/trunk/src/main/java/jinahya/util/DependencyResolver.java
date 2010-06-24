/*
 *  Copyright 2010 onacit.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.util;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolver<T> {



    /**
     *
     */
    public void reset() {
        dependencyMap.clear();
    }


    /**
     *
     * @param source
     * @param target
     * @throws DependencyResolverException
     */
    public void addDependency(final T source, final T target) {

        if (source == null) {
            throw new IllegalArgumentException("param:0 is null");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException("param:0 equals to param:1");
        }


        synchronized (dependencyMap) {

            List<T> targets = dependencyMap.get(source);
            if (targets == null) {
                targets = new ArrayList<T>();
                dependencyMap.put(source, targets);
            }

            if (target != null && !targets.contains(target)) {
                targets.add(target);
            }
        }
    }


    /*
    public List<T> getDependencies(final T source) {

        if (source == null) {
            throw new IllegalArgumentException("param:0 is null");
        }

        final List<T> dependencies = new ArrayList<T>();
        getDependencies(dependencies, source);
        return dependencies;
    }
     */


    /*
    private void getDependencies(final List<T> dependencies, final T source) {
        synchronized (dependencyMap) {

            final List<T> targets = dependencyMap.get(source);

            if (targets == null) {
                return;
            }

            for (T target : targets) {
                getDependencies(dependencies, target);
                if (!dependencies.contains(target)) {
                    dependencies.add(target);
                }
            }
        }
    }
     */


    /*
    public List<T> getDependency(final T source, final T target) {
        synchronized (dependencyMap) {

            final List<T> path = new ArrayList<T>();
            path.add(source);
            if (getDependency(path, target)) {
                return path;
            }
            return null;
        }
    }
     */


    /*
    private boolean getDependency(final List<T> path, final T target) {

        synchronized (dependencyMap) {

            final List<T> targets =
                dependencyMap.get(path.get(path.size() - 1));

            if (targets == null) {
                return false;
            }

            if (target == null) {
                return true;
            }

            if (targets.contains(target)) {
                path.add(target);
                return true;
            }

            for (T auxiliary : targets) {
                path.add(auxiliary);
                if (getDependency(path, target)) {
                    return true;
                }
            }

            return false;
        }
    }
     */


    /**
     *
     * @param source
     * @param target
     * @return
     */
    public boolean hasDependency(final T source, final T target) {

        if (source == null) {
            throw new IllegalArgumentException("param:0 is null");
        }

        synchronized (dependencyMap) {
            final List<T> targets = dependencyMap.get(source);
            if (targets == null) {
                return false;
            }

            if (target == null || targets.contains(target)) {
                return true;
            }

            for (T auxiliary : targets) {
                if (hasDependency(auxiliary, target)) {
                    return true;
                }
            }

            return false;
        }
    }


    /*
     *
     * @param source
     * @param target
     * @return
    public boolean removeDependency(final T source, final T target) {
        synchronized (dependencyMap) {

            final List<T> targets = dependencyMap.get(source);

            if (targets == null) {
                return false;
            }

            boolean result = true;
            if (target != null) {
                result = targets.remove(target);
            }

            if (result && targets.isEmpty()) {
                dependencyMap.remove(source);
            }

            return result;
        }
    }
     */


    /**
     *
     * @param dependencies
     * @param source
     */
    private void flatten(final T source, final List<T> dependencies) {
        synchronized (dependencyMap) {

            if (dependencies.contains(source)) {
                return;
            }

            final List<T> targets = dependencyMap.get(source);
            if (targets != null) {
                for (T target : dependencyMap.get(source)) {
                    flatten(target, dependencies);
                }
            }

            dependencies.add(source);
        }
    }


    /**
     *
     * @return
     */
    public List<T> getFlatten() {

        final List<T> flatten = new ArrayList<T>();

        synchronized (dependencyMap) {
            for (T source : dependencyMap.keySet()) {
                flatten(source, flatten);
            }
        }

        return flatten;
    }



    /**
     *
     * @param maximum
     */
    public List<List<T>> getFlattens(final int maximum) {

        if (maximum <= 0) {
            throw new IllegalArgumentException(
                "param:0:" + Integer.TYPE + " is null");
        }

        final List<T> remains = getFlatten();

        final List<List<T>> flattens = new ArrayList<List<T>>();

        while (!remains.isEmpty()) {

            if (flattens.size() == maximum - 1) {
                break;
            }

            final List<T> flatten = new ArrayList<T>();

            flattens.add(flatten);
            flatten.add(remains.remove(0));

            for (int i = 0; i < remains.size(); i++) {

                for (T p : flatten) {
                    if (hasDependency(remains.get(i), p)) {
                        flatten.add(remains.get(i));
                        break;
                    }
                }

                if (!flatten.contains(remains.get(i))) {
                    outer:
                    for (int j = i + 1; j < remains.size(); j++) {
                        if (!hasDependency(remains.get(j), remains.get(i))) {
                            continue;
                        }
                        for (T p : flatten) {
                            if (hasDependency(remains.get(j), p)) {
                                flatten.add(remains.get(i));
                                break outer;
                            }
                        }
                    }
                }
            }

            remains.removeAll(flatten);
        }

        if (!remains.isEmpty()) {
            flattens.add(remains);
        }

        return flattens;
    }


    /*
     *
     * @return
    public List<List<T>> getGroups() {

        final List<T> flatten = getFlatten();

        final List<List<T>> paths = new ArrayList<List<T>>();

        int longest = 0;
        while (!flatten.isEmpty()) {
            final List<T> path = new ArrayList<T>();
            paths.add(path);
            for (int i = flatten.size() - 1; i > 0; i--) {
                if (hasDependency(flatten.get(i), flatten.get(0))) {
                    path.add(0, flatten.remove(i));
                }
            }
            path.add(0, flatten.remove(0));
            longest = Math.max(path.size(), longest);
        }

        for (List<T> path : paths) {
            System.out.println("PATH: " + path);
        }

        final List<List<T>> groups = new ArrayList<List<T>>();
        for (int i = 0; i < longest; i++) {
            final List<T> group = new ArrayList<T>();
            for (List<T> path : paths) {
                if (!path.isEmpty()) {
                    group.add(path.remove(0));
                }
            }
            groups.add(group);
        }

        return groups;
    }
     */


    void print(final PrintStream out) {
        System.out.println("DEPENDENCIES ------------------------------------");
        synchronized (dependencyMap) {
            for (Entry<T, List<T>> entry : dependencyMap.entrySet()) {
                out.println("\t" + entry.getKey() + " -> " + entry.getValue());
            }
        }
        System.out.println("-------------------------------------------------");
        getFlatten();
        System.out.println("-------------------------------------------------");

        System.out.println("FLATTEN: " + getFlatten());
        System.out.println("GROUPS ------------------------------------------");
    }


    private Class<T> type;

    private final Map<T, List<T>> dependencyMap =
        Collections.synchronizedMap(new HashMap<T, List<T>>());
}
