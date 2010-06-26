/*
 *  Copyright 2010 Jin Kwon.
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolver<T> {


    /**
     * Creates a new instance.
     *
     * @param type processing unit type
     */
    public DependencyResolver(final Class<T> type) {
        super();

        if (type == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ": is null");
        }

        this.type = type;
        
        dependencyMap = Collections.synchronizedMap(new HashMap<T, List<T>>());
    }


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
     * @throws IllegalArgumentException if any source or target is wrong
     */
    private void checkParameters(final T source, final T target) {

        if (source == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (!type.isInstance(source)) {
            throw new IllegalArgumentException(
                "param:0:" + source.getClass() + ":" + source
                + " is not an instance of " + type);
        }

        if (target != null) {

            if (!type.isInstance(target)) {
                throw new IllegalArgumentException(
                "param:1:" + target.getClass() + ":" + target
                + " is not an instance of " + type);
            }

            if (source.equals(target)) {
                throw new IllegalArgumentException("self dependency");
            }
        }
    }


    /**
     *
     * @param source
     * @param target
     */
    public void addDependency(final T source, final T target) {

        checkParameters(source, target);

        synchronized (dependencyMap) {

            List<T> targets = dependencyMap.get(source);
            if (targets == null) {
                targets = new LinkedList<T>();
                dependencyMap.put(source, targets);
            }

            if (target != null && !targets.contains(target)) {
                targets.add(0, target);
            }
        }
    }


    /**
     *
     * @return
     */
    public List<List<T>> getDependencyGroups(final T source, final T target) {

        checkParameters(source, target);

        synchronized (dependencyMap) {

            if (!dependencyMap.containsKey(source)) {
                return Collections.EMPTY_LIST;
            }

            final List<List<T>> groups = new LinkedList<List<T>>();

            if (target == null) {
                final List<T> group = new LinkedList<T>();
                groups.add(0, group);
                group.add(0, source);
                return groups;
            }

            final List<T> targets = dependencyMap.get(source);

            for (T auxiliary : targets) {
                if (auxiliary.equals(target)) {
                    final List<T> group = new LinkedList<T>();
                    groups.add(0, group);
                    group.add(0, auxiliary);
                    group.add(0, source);
                    continue;
                }
                for (List<T> group : getDependencyGroups(auxiliary, target)) {
                    groups.add(0, group);
                    group.add(0, source);
                }
            }

            return groups;
        }
    }


    /**
     *
     * @param source
     * @param target
     * @return
     */
    public boolean hasDependency(final T source, final T target) {

        checkParameters(source, target);

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


    /**
     *
     * @param source
     * @param target
     * @return
     */
    public boolean removeDependency(final T source, final T target) {

        checkParameters(source, target);

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


    /**
     *
     * @param dependencies
     * @param source
     */
    private void getFlatten(final T source, final List<T> dependencies) {

        synchronized (dependencyMap) {

            if (dependencies.contains(source)) {
                return;
            }

            final List<T> targets = dependencyMap.get(source);
            if (targets != null) {
                for (T target : targets) {
                    getFlatten(target, dependencies);
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
                getFlatten(source, flatten);
            }
        }

        return flatten;
    }



    /**
     *
     * @param maximum
     */
    public List<List<T>> getHorizontalGroups(final int maximum) {

        if (maximum <= 0) {
            throw new IllegalArgumentException(
                "param:0:" + Integer.TYPE + ":" + maximum + " <= 0");
        }

        final List<T> remains = getFlatten();

        final List<List<T>> groups = new LinkedList<List<T>>();

        while (!remains.isEmpty()) {

            if (groups.size() == (maximum - 1)) {
                break;
            }

            final List<T> group = new ArrayList<T>();
            groups.add(0, group);
            group.add(remains.remove(0));

            for (int i = 0; i < remains.size(); i++) {

                for (T p : group) {
                    if (hasDependency(remains.get(i), p)) {
                        group.add(remains.get(i));
                        break;
                    }
                }

                if (!group.contains(remains.get(i))) {
                    outer:
                    for (int j = i + 1; j < remains.size(); j++) {
                        if (!hasDependency(remains.get(j), remains.get(i))) {
                            continue;
                        }
                        for (T p : group) {
                            if (hasDependency(remains.get(j), p)) {
                                group.add(remains.get(i));
                                break outer;
                            }
                        }
                    }
                }
            }

            remains.removeAll(group);
        }

        if (!remains.isEmpty()) {
            groups.add(0, remains);
        }

        return groups;
    }


    /**
     *
     * @param maximum
     */
    public List<List<T>> getVerticalGroups(final int maximum) {

        if (maximum <= 0) {
            throw new IllegalArgumentException(
                "param:0:" + Integer.TYPE + ":" + maximum + " <= 0");
        }

        final List<T> remains = getFlatten();

        final List<List<T>> groups = new LinkedList<List<T>>();

        while (!remains.isEmpty()) {

            if (groups.size() == (maximum - 1)) {
                break;
            }

            final List<T> group = new ArrayList<T>();

            groups.add(0, group);

            outer:
            for (int i = 0; i < remains.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (hasDependency(remains.get(i), remains.get(j))) {
                        continue outer;
                    }
                }
                group.add(remains.get(i));
            }

            remains.removeAll(group);
        }

        if (!remains.isEmpty()) {
            groups.add(0, remains);
        }

        return groups;
    }


    public void print(final PrintStream out) {
        synchronized (dependencyMap) {
            for (Entry<T, List<T>> entry : dependencyMap.entrySet()) {
                out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }


    private Class<T> type;

    private final Map<T, List<T>> dependencyMap;
}
