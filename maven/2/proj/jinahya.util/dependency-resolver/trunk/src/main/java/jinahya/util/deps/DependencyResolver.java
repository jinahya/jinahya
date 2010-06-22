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
 *  under the License.
 */

package jinahya.util.deps;


import java.io.PrintStream;
import java.util.ArrayList;
import static java.util.Collections.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolver<T> {


    /**
     *
     * @param clazz
     */
    public DependencyResolver(final Class<T> clazz) {
        super();

        this.clazz = clazz;
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
     * @throws DependencyResolverException
     */
    public void addDependency(final T source, final T target)
        throws DependencyResolverException {

        if (source == null) {
            throw new IllegalArgumentException(
                "param(0:source: " + clazz + ") is null");
        }

        /*
        if (target == null) {
            throw new IllegalArgumentException(
                "param(1:target: " + clazz + ") is null");
        }
         */

        if (source.equals(target)) {
            if (!silentlyIgnoreSelfDependency) {
                throw new DependencyResolverException(
                    "self dependency: " + source + ", " + target);
            }
            return;
        }

        synchronized (dependencyMap) {

            if (target != null) {
                /*
                final List<T> forward = checkDependency(source, target);
                if (forward != null) {
                 */
                if (hasDependency(source, target)) {
                    if (!silentlyIgnoreDuplicateDependency) {
                        throw new DependencyResolverException(
                            "duplicated dependency: " + source + " -> "
                            + target);
                    }
                    return;
                }

                /*
                final List<T> backward = checkDependency(target, source);
                if (backward != null) {
                 */
                if (hasDependency(target, source)) {
                    if (!silentlyIgnoreCyclicDependency) {
                        throw new DependencyResolverException(
                            "cyclic dependency: " + target + " -> " + source);
                    }
                    return;
                }
            }

            List<T> targetList = dependencyMap.get(source);
            if (targetList == null) {
                targetList = new ArrayList<T>();
                dependencyMap.put(source, targetList);
            }
            if (target != null) {
                targetList.add(target);
            }
        }
    }


    /*
    private List<T> checkDependency(final T source, final T target) {
        synchronized (dependencyMap) {
            final LinkedList<T> path = new LinkedList<T>();
            path.add(source);
            if (checkDependency(path, target)) {
                return path;
            }
            return null;
        }
    }


    private boolean checkDependency(final LinkedList<T> path, final T target) {

        synchronized (dependencyMap) {
            final List<T> targetList = dependencyMap.get(path.getLast());
            if (targetList == null) {
                return false;
            }

            if (targetList.contains(target)) {
                path.add(target);
                return true;
            }

            for (T auxiliary : targetList) {
                path.add(auxiliary);
                if (checkDependency(path, target)) {
                    return true;
                }
            }

            return false;
        }
    }
     */


    public boolean hasDependency(final T source, final T target) {

        if (source == null) {
            throw new IllegalArgumentException(
                "param(0:source: " + clazz + ") is null");
        }

        synchronized (dependencyMap) {
            final List<T> targetList = dependencyMap.get(source);
            if (targetList == null) {
                return false;
            }

            if (target == null || targetList.contains(target)) {
                return true;
            }

            for (T auxiliary : targetList) {
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
        synchronized (dependencyMap) {
            final List<T> targetList = dependencyMap.get(source);
            if (targetList == null) {
                return false;
            }

            boolean result = targetList.remove(target);

            if (target == null) {
                result = true;
            }

            if (result && targetList.isEmpty()) {
                dependencyMap.remove(source);
            }

            return result;
        }
    }


    /**
     *
     * @return
     */
    public boolean getSilentlyIgnoreSelfDependency() {
        return silentlyIgnoreSelfDependency;
    }


    /**
     *
     * @param silentlyIgnoreSelfDependency
     */
    public void setSilentlyIgnoreSelfDependency(
        final boolean silentlyIgnoreSelfDependency) {

        this.silentlyIgnoreSelfDependency = silentlyIgnoreSelfDependency;
    }


    /**
     *
     * @return
     */
    public boolean getSilentlyIgnoreDuplicateDependency() {
        return silentlyIgnoreDuplicateDependency;
    }


    /**
     *
     * @param silentlyIgnoreDuplicateDependency
     */
    public void setSilentlyIgnoreDuplicateDependency(
        final boolean silentlyIgnoreDuplicateDependency) {

        this.silentlyIgnoreDuplicateDependency =
            silentlyIgnoreDuplicateDependency;
    }


    /**
     *
     * @return
     */
    public boolean getSilentlyIgnoreCyclicDependency() {
        return silentlyIgnoreCyclicDependency;
    }


    /**
     *
     * @param silentlyIgnoreCyclicDependency
     */
    public void setSilentlyIgnoreCyclicDependency(
        final boolean silentlyIgnoreCyclicDependency) {

        this.silentlyIgnoreCyclicDependency = silentlyIgnoreCyclicDependency;
    }


    /**
     *
     * @param flatten
     * @param source
     */
    private void flatten(final List<T> flatten, final T source) {
        synchronized (dependencyMap) {
            if (flatten.contains(source)) {
                return;
            }
            final List<T> targets = dependencyMap.get(source);
            if (targets != null) {
                for (T target : dependencyMap.get(source)) {
                    flatten(flatten, target);
                }
            }
            flatten.add(source);
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
                flatten(flatten, source);
            }
            System.out.println(flatten);
        }

        return flatten;
    }


    /**
     *
     * @return
     */
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


    void print(final PrintStream out) {
        System.out.println("-------------------------------------------------");
        synchronized (dependencyMap) {
            for (Entry<T, List<T>> entry : dependencyMap.entrySet()) {
                out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
        System.out.println("-------------------------------------------------");
        for (List<T> path : getGroups()) {
            out.println(path);
        }
    }


    private Class<T> clazz;

    private final Map<T, List<T>> dependencyMap =
        synchronizedMap(new HashMap<T, List<T>>());

    private boolean silentlyIgnoreSelfDependency = false;
    private boolean silentlyIgnoreDuplicateDependency = true;
    private boolean silentlyIgnoreCyclicDependency = false;
}
