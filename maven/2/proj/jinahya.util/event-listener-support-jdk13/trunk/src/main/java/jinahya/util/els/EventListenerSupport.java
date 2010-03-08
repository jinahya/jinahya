/*
 *  Copyright 2009 Jin Kwon.
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

package jinahya.util.els;


import java.util.Enumeration;
import java.util.EventListener;
import java.util.Hashtable;
import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class EventListenerSupport {


    /**
     * Returns the total number of listeners for this listener suppoert.
     *
     * @return the total number of listeners.
     */
    public int getListenerCount() {
        int count = 0;
        synchronized (table) {
            for (Enumeration e = table.elements(); e.hasMoreElements();) {
                count += ((Vector) e.nextElement()).size();
            }
        }
        return count;
    }


    /**
     * Returns the total number of listeners of the supplied type for this
     * listener list.
     *
     * @param listenerClass listener class
     * @return the total number of listeners of the given type.
     */
    public int getListenerCount(final Class listenerClass) {

        if (listenerClass == null) {
            throw new NullPointerException("listenerClass");
        }

        if (!EventListener.class.isAssignableFrom(listenerClass)) {
            throw new IllegalArgumentException(
                listenerClass + " is not assignable to " + EventListener.class);
        }

        synchronized (table) {
            Vector instances = (Vector) table.get(listenerClass);
            if (instances == null) {
                return 0;
            }
            return instances.size();
        }
    }


    /**
     * Returns an array of class.
     *
     * @return listener classes
     */
    public final Class[] getListenerClasses() {
        Vector vector = new Vector();
        for (Enumeration e = table.keys(); e.hasMoreElements();) {
            vector.addElement(e.nextElement());
        }
        Class[] classes = new Class[vector.size()];
        vector.copyInto(classes); // 1.1
        //vector.toArray(classes); // 1.2
        return classes;
    }


    /**
     * Returns all listener instances.
     *
     * @return all listeners
     */
    public final Object[] getListeners() {
        Vector all = new Vector();
        synchronized (table) {
            for (Enumeration e = table.elements(); e.hasMoreElements();) {
                Vector instances = (Vector) e.nextElement();
                for (int i = 0; i < instances.size(); i++) {
                    all.addElement(instances.elementAt(i));
                }
            }
        }

        Object[] result = new Object[all.size()];
        all.copyInto(result);
        //all.toArray(result); // 1.2
        return result;
    }


    /**
     * Return an array of all the listeners of the given type.
     *
     * @param listenerClass listener class
     * @return all listeners of the given type.
     */
    public final Object[] getListeners(final Class listenerClass) {

        if (listenerClass == null) {
            throw new NullPointerException("listenerClass");
        }

        if (!EventListener.class.isAssignableFrom(listenerClass)) {
            throw new IllegalArgumentException(
                listenerClass + " is not assignable to " + EventListener.class);
        }

        synchronized (table) {
            Vector instances = (Vector) table.get(listenerClass);
            if (instances == null) {
                return new Object[0];
            }
            Object[] result = new Object[instances.size()];
            instances.copyInto(result);
            //instances.toArray(result); // 1.2
            return result;
        }
    }


    /**
     * Adds listener to this support.
     *
     * @param listenerClass listener class
     * @param listenerInstance listener instance
     */
    public final void add(final Class listenerClass,
                          final EventListener listenerInstance) {

        if (listenerClass == null) {
            throw new NullPointerException("listenerClass");
        }

        if (!EventListener.class.isAssignableFrom(listenerClass)) {
            throw new IllegalArgumentException(
                listenerClass + " is not assignable to " + EventListener.class);
        }

        if (listenerInstance == null) {
            throw new NullPointerException("listenerInstance");
        }

        if (!listenerClass.isInstance(listenerInstance)) {
            throw new IllegalArgumentException(
                listenerInstance + " is not an instance of " + listenerClass);
        }

        synchronized (table) {
            Vector instances = (Vector) table.get(listenerClass);
            if (instances == null) {
                instances = new Vector();
                table.put(listenerClass, instances);
            }
            instances.addElement(listenerInstance);
        }
    }


    /**
     * Removes the listener as a listener of the specified type.
     *
     * @param listenerClass listener class
     * @param listenerInstance listener instance
     * @return true if support contained the specifed listener.
     */
    public boolean remove(final Class listenerClass,
                          final EventListener listenerInstance) {

        if (listenerClass == null) {
            throw new NullPointerException("listenerClass");
        }

        if (!EventListener.class.isAssignableFrom(listenerClass)) {
            throw new IllegalArgumentException(
                listenerClass + " is not assignable to " + EventListener.class);
        }

        if (listenerInstance == null) {
            throw new NullPointerException("listenerInstance");
        }

        if (!listenerClass.isInstance(listenerInstance)) {
            throw new IllegalArgumentException(
                listenerInstance + " is not an instance of " + listenerClass);
        }

        synchronized (table) {
            Vector instances = (Vector) table.get(listenerClass);
            if (instances != null) {
                boolean result = instances.removeElement(listenerInstance);
                if (result && instances.isEmpty()) {
                    table.remove(listenerClass);
                }
                return result;
            }
        }

        return false;
    }


    /**
     * Checks if this support contains specified listener.
     *
     * @param listenerClass listener class
     * @param listenerInstance listener instance
     * @return true if support contained the specifed listener.
     */
    public boolean contains(final Class listenerClass,
                            final EventListener listenerInstance) {

        if (listenerClass == null) {
            throw new NullPointerException("listenerClass");
        }

        if (!EventListener.class.isAssignableFrom(listenerClass)) {
            throw new IllegalArgumentException(
                listenerClass + " is not assignable to " + EventListener.class);
        }

        if (listenerInstance == null) {
            throw new NullPointerException("listenerInstance");
        }

        if (!listenerClass.isInstance(listenerInstance)) {
            throw new IllegalArgumentException(
                listenerInstance + " is not an instance of " + listenerClass);
        }

        synchronized (table) {
            Vector instances = (Vector) table.get(listenerClass);
            if (instances != null) {
                return instances.contains(listenerInstance);
            }
        }

        return false;
    }


    /**
     * Remove all classes and listeners from this support.
     */
    public void clear() {
        synchronized (table) {
            table.clear();
        }
    }


    //<Class<T extends EventListener>, Vector<T>>
    private final Hashtable table = new Hashtable();
}
