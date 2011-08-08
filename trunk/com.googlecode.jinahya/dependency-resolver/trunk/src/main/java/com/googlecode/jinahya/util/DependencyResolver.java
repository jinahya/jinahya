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


import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


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

        table = new Hashtable();
    }


    public void addSource(final String source) {

        if (source == null) {
            throw new NullPointerException("null source");
        }

        synchronized (table) {

            if (!table.containsKey(source)) {
                table.put(source, new Vector());
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

        synchronized (table) {

            Vector targets = (Vector) table.get(source);

            if (targets == null) {
                targets = new Vector();
                table.put(source, targets);
            }

            if (!targets.contains(target)) {
                targets.addElement(target);
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

        synchronized (table) {

            final Vector targets = (Vector) table.get(source);

            if (targets == null) {
                return;
            }

            if (targets.removeElement(target) && targets.isEmpty()) {
                table.remove(source);
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

        synchronized (table) {

            final Vector targets = (Vector) table.get(source);

            if (targets == null) {
                return false;
            }

            if (targets.contains(target)) {
                return true;
            }

            for (Enumeration i = targets.elements(); i.hasMoreElements();) {
                if (hasDependency((String) i.nextElement(), target)) {
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
    public Vector findDependencies(final String source, final String target) {

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

        synchronized (table) {

            final Vector dependencies = new Vector();

            final Vector targets = (Vector) table.get(source);

            if (targets == null) {
                return dependencies;
            }

            for (Enumeration i = targets.elements(); i.hasMoreElements();) {

                final String auxiliary = (String) i.nextElement();

                if (auxiliary.equals(target)) {
                    final Vector path = new Vector();
                    path.addElement(source);
                    path.addElement(auxiliary);
                    dependencies.addElement(path);
                    continue;
                }

                final Vector dependencies_ = findDependencies(auxiliary, target);
                for (Enumeration j = dependencies_.elements(); j.hasMoreElements();) {
                    final Vector path = (Vector) j.nextElement();
                    path.insertElementAt(source, 0);
                    dependencies.addElement(path);
                }
            }

            return dependencies;
        }
    }


    private final Hashtable table; // <String, List<String>>
}

