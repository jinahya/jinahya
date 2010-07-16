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
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


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
    public DependencyResolver() {
        super();

        this.dependencies = new Hashtable<T, Vector<T>>();
    }


    /**
     *
     */
    public void clear() {
        synchronized (dependencies) {
            dependencies.clear();
        }
    }


    /**
     *
     * @param source
     * @param target
     */
    public void addDependencies(final T source, final T... targets) {

        if (source == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (targets == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        synchronized (dependencies) {
            for (T target : targets) {
                addDependency(source, target);
            }
        }
    }


    /**
     *
     * @param source
     */
    public void addSource(final T source) {

        if (source == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        synchronized (dependencies) {
            if (!dependencies.containsKey(source)) {
                dependencies.put(source, new Vector<T>());
            }
        }
    }


    /**
     *
     * @param source
     * @param target
     */
    public void addDependency(final T source, final T target) {

        if (source == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (target == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "self dependency: " + source + " -> " + target);
        }

        synchronized (dependencies) {

            Vector<T> targets = dependencies.get(source);
            if (targets == null) {
                targets = new Vector<T>();
                dependencies.put(source, targets);
            }

            if (!targets.contains(target)) {
                targets.addElement(target);
            }
        }
    }


    /**
     * 
     * @param source
     * @param target
     * @return
     */
    public Vector<Vector<T>> getDependencyGroups(final T source,
                                                 final T target) {

        if (source == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (target == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "self dependency: " + source + " -> " + target);
        }

        synchronized (dependencies) {

            if (!dependencies.containsKey(source)) {
                return new Vector();
            }

            final Vector<Vector<T>> groups = new Vector<Vector<T>>();

            final Vector<T> targets = dependencies.get(source);

            for (int i = 0; i < targets.size(); i++) {

                final T auxiliary = targets.elementAt(i);

                if (auxiliary.equals(target)) {
                    final Vector<T> group = new Vector<T>();
                    group.addElement(source);
                    group.addElement(auxiliary);
                    groups.addElement(group);
                    continue;
                }

                final Vector<Vector<T>> dependencyGroups =
                    getDependencyGroups(auxiliary, target);
                for (int j = 0; j <dependencyGroups.size(); j++) {
                    final Vector<T> dependencyGroup =
                        dependencyGroups.elementAt(j);
                    groups.addElement(dependencyGroup);
                    dependencyGroup.insertElementAt(source, 0);
                }
            }

            return groups;
        }
    }


    /**
     *
     * @param source
     * @return
     */
    public boolean hasSource(final T source) {

        if (source == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        return dependencies.containsKey(source);
    }


    /**
     *
     * @param source
     * @param target
     * @return
     */
    public boolean hasDependency(final T source, final T target) {

        if (source == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (target == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "self dependency: " + source + " -> " + target);
        }

        synchronized (dependencies) {

            if (!dependencies.containsKey(source)) {
                return false;
            }

            final Vector<T> targets = dependencies.get(source);

            if (targets.contains(target)) {
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
     */
    public void removeDependencies(final T source, final T... targets) {

        if (source == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (targets == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        synchronized (dependencies) {
            for (T target : targets) {
                removeDependency(source, target);
            }
        }
    }


    /**
     *
     * @param source
     */
    public void removeSource(final T source) {

        if (source == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        dependencies.remove(source);
    }


    /**
     *
     * @param source
     * @param target
     */
    public void removeDependency(final T source, final T target) {

        if (source == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (target == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        if (source.equals(target)) {
            throw new IllegalArgumentException(
                "self dependency: " + source + " -> " + target);
        }

        synchronized (dependencies) {

            if (!dependencies.containsKey(source)) {
                return;
            }

            final Vector<T> targets = dependencies.get(source);

            if (targets.removeElement(target) && targets.isEmpty()) {
                dependencies.remove(source);
            }
        }
    }


    /**
     *
     * @param flatten
     * @param source
     */
    private void getFlatten(final T source, final Vector<T> flatten) {

        synchronized (dependencies) {

            if (flatten.contains(source)) {
                return;
            }

            final Vector<T> targets = dependencies.get(source);
            if (targets != null) {
                for (T target : targets) {
                    getFlatten(target, flatten);
                }
            }

            flatten.addElement(source);
        }
    }


    /**
     *
     * @return
     */
    public Vector<T> getFlatten() {

        final Vector<T> flatten = new Vector<T>();

        synchronized (dependencies) {
            for (T source : dependencies.keySet()) {
                getFlatten(source, flatten);
            }
        }

        return flatten;
    }


    /**
     * 
     * @return
     */
    public Vector<Vector<T>> getHorizontalGroups() {
        return getHorizontalGroups(Integer.MAX_VALUE);
    }


    /**
     *
     * @param maximum
     */
    public Vector<Vector<T>> getHorizontalGroups(final int maximum) {

        if (maximum <= 0) {
            throw new IllegalArgumentException(
                "param:0:" + Integer.TYPE + ":" + maximum + " <= 0");
        }

        final Vector<T> remains = getFlatten();

        final Vector<Vector<T>> groups = new Vector<Vector<T>>();

        while (!remains.isEmpty() && (groups.size() < (maximum - 1))) {

            final Vector<T> group = new Vector<T>();

            groups.addElement(group);

            group.addElement(remains.firstElement());
            remains.removeElementAt(0);

            for (int i = 0; i < remains.size();) {

                boolean added = false;

                for (T p : group) {
                    if (hasDependency(remains.elementAt(i), p)) {
                        group.addElement(remains.elementAt(i));
                        remains.removeElementAt(i);
                        added = true;
                        break;
                    }
                }

                if (!added) {
                    outer:
                    for (int j = i + 1; j < remains.size(); j++) {
                        if (!hasDependency(remains.elementAt(j),
                                           remains.elementAt(i))) {
                            continue;
                        }
                        for (T p : group) {
                            if (hasDependency(remains.elementAt(j), p)) {
                                group.addElement(remains.elementAt(i));
                                remains.removeElementAt(i);
                                added = true;
                                break outer;
                            }
                        }
                    }
                }

                if (!added) {
                    i++;
                }
            }
        }

        if (!remains.isEmpty()) {
            groups.addElement(remains);
        }

        return groups;
    }


    /**
     *
     */
    public Vector<Vector<T>> getVerticalGroups() {
        return getVerticalGroups(Integer.MAX_VALUE);
    }


    /**
     *
     * @param maximum
     */
    public Vector<Vector<T>> getVerticalGroups(final int maximum) {

        if (maximum <= 0) {
            throw new IllegalArgumentException(
                "param:0:" + Integer.TYPE + ":" + maximum + " <= 0");
        }

        final Vector<T> remains = getFlatten();

        final Vector<Vector<T>> groups = new Vector<Vector<T>>();

        while (!remains.isEmpty() && (groups.size() < (maximum - 1)))  {

            final Vector<T> group = new Vector<T>();

            groups.addElement(group);

            outer:
            for (int i = remains.size() - 1; i >= 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (hasDependency(remains.elementAt(i),
                                      remains.elementAt(j))) {
                        continue outer;
                    }
                }
                group.addElement(remains.elementAt(i));
                remains.removeElementAt(i);
            }
        }

        if (!remains.isEmpty()) {
            groups.addElement(remains);
        }

        return groups;
    }


    void print(final PrintStream out) {
        synchronized (dependencies) {
            final Enumeration<T> e = dependencies.keys();
            while (e.hasMoreElements()) {
                final T key = e.nextElement();
                out.println(key + " -> " + dependencies.get(key));
            }
        }
    }


    private final Hashtable<T, Vector<T>> dependencies;
}
