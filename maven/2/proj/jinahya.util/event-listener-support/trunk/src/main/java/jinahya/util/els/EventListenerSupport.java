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
 *  under the License.
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
    public final int getListenerCount() {
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
     * @param c listener class
     * @return the total number of listeners of the given type.
     */
    public final int getListenerCount(final Class c) {
        /*
        if (!EventListener.class.isAssignableFrom(c)) {
            throw new ClassCastException(
                c + " is not assignable to " + EventListener.class);
        }
         */

        synchronized (table) {
            Vector instances = (Vector) table.get(c);
            if (instances == null) {
                return 0;
            }
            return instances.size();
        }
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
        return all.toArray();
    }


    /**
     * Return an array of all the listeners of the given type.
     *
     * @param c listener class
     * @return all listeners of the given type.
     */
    public final Object[] getListeners(final Class c) {
        /*
        if (!EventListener.class.isAssignableFrom(c)) {
            throw new ClassCastException(
                c + " is not assignable to " + EventListener.class);
        }
         */
        synchronized (table) {
            Vector instances = (Vector) table.get(c);
            if (instances == null) {
                return new Object[0];
            }
            return instances.toArray();
        }
    }


    /**
     * Adds listener to this support.
     *
     * @param c listener class
     * @param l listener instance
     */
    public final void add(final Class c, final EventListener l) {

        /*
        if (!EventListener.class.isAssignableFrom(c)) { // NullPointerException
            throw new ClassCastException(
                c + " is not assignable to " + EventListener.class);
        }
         */

        if (!c.isInstance(l)) { // false for null
            throw new IllegalArgumentException(
                l + " is not an instance of " + c);
        }

        synchronized (table) {
            Vector instances = (Vector) table.get(c);
            if (instances == null) {
                instances = new Vector();
                table.put(c, instances);
            }
            instances.addElement(l);
        }
    }


    /**
     * Removes the listener as a listener of the specified type.
     *
     * @param c listener class
     * @param l listener instance
     */
    public final void remove(final Class c, final EventListener l) {

        /*
        if (!EventListener.class.isAssignableFrom(c)) { // NullPointerException
            throw new ClassCastException(
                c + " is not assignable to " + EventListener.class);
        }
         */

        if (!c.isInstance(l)) { // false for null
            throw new IllegalArgumentException(
                l + " is not an instance of " + c);
        }

        synchronized (table) {
            Vector instances = (Vector) table.get(c);
            if (instances != null) {
                instances.removeElement(l);
            }
        }
    }


    //<Class<T extends EventListener>, T>
    private final Hashtable table = new Hashtable();
}
