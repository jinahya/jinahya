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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolver<T> {



    /**
     *
     * @param type
     */
    public DependencyResolver(final Class<T> type) {
        this(type, new Hashtable<T, Vector<T>>());
    }


    /**
     * Creates a new instance.
     *
     * @param type processing unit type
     */
    public DependencyResolver(final Class<T> type,
                              final Hashtable<T, Vector<T>> dependencies) {
        super();

        if (type == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ": is null");
        }

        if (dependencies == null) {
            throw new IllegalArgumentException(
                "param:1:" + Hashtable.class + ": is null");
        }


        this.type = type;

        this.dependencies = dependencies;
    }


    /**
     *
     */
    public void reset() {
        synchronized (dependencies) {
            dependencies.clear();
        }
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

        synchronized (dependencies) {

            Vector<T> targets = dependencies.get(source);
            if (targets == null) {
                targets = new Vector<T>();
                dependencies.put(source, targets);
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
    public Vector<Vector<T>> getDependencyGroups(final T source,
                                                 final T target) {

        checkParameters(source, target);

        synchronized (dependencies) {

            if (!dependencies.containsKey(source)) {
                return new Vector();
            }

            final Vector<Vector<T>> groups = new Vector<Vector<T>>();

            if (target == null) {
                final Vector<T> group = new Vector<T>();
                groups.addElement(group);
                group.addElement(source);
                return groups;
            }

            final Vector<T> targets = dependencies.get(source);

            for (T auxiliary : targets) {
                if (auxiliary.equals(target)) {
                    final Vector<T> group = new Vector<T>();
                    groups.addElement(group);
                    group.addElement(source);
                    group.addElement(auxiliary);
                    continue;
                }
                for (Vector<T> group : getDependencyGroups(auxiliary, target)) {
                    groups.addElement(group);
                    group.insertElementAt(source, 0);
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

        synchronized (dependencies) {

            final Vector<T> targets = dependencies.get(source);

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

        synchronized (dependencies) {

            final Vector<T> targets = dependencies.get(source);

            if (targets == null) {
                return false;
            }

            boolean result = true;

            if (target != null) {
                result = targets.removeElement(target);
            }

            if (result && targets.isEmpty()) {
                dependencies.remove(source);
            }

            return result;
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
     * @param maximum
     */
    public Vector<Vector<T>> getHorizontalGroups(final int maximum) {

        if (maximum <= 0) {
            throw new IllegalArgumentException(
                "param:0:" + Integer.TYPE + ":" + maximum + " <= 0");
        }

        final Vector<T> remains = getFlatten();

        final Vector<Vector<T>> groups = new Vector<Vector<T>>();

        while (!remains.isEmpty()) {

            if (groups.size() == (maximum - 1)) {
                break;
            }

            final Vector<T> group = new Vector<T>();

            groups.addElement(group);

            group.addElement(remains.elementAt(0));
            remains.removeElementAt(0);

            for (int i = 0; i < remains.size(); i++) {

                boolean added = false;

                for (T p : group) {
                    if (hasDependency(remains.elementAt(i), p)) {
                        group.addElement(remains.elementAt(i));
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
                                break outer;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < group.size(); i++) {
                remains.removeElement(group.elementAt(i));
            }

        }

        if (!remains.isEmpty()) {
            groups.addElement(remains);
        }

        return groups;
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

        while (!remains.isEmpty()) {

            if (groups.size() == (maximum - 1)) {
                break;
            }

            final Vector<T> group = new Vector<T>();

            groups.addElement(group);

            outer:
            for (int i = remains.size() - 1; i > 0; i--) {
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


    private Class<T> type;

    private final Hashtable<T, Vector<T>> dependencies;
}
