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


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class EventListenerSupport {



    /**
     * Adds listener to this support.
     *
     * @param listenerClass the type of the listener to be added
     * @param listenerInstance the listener to be added
     */
    @SuppressWarnings("unchecked")
    public <T extends EventListener> void add(final Class<T> listenerClass,
                                              final T listenerInstance) {

        if (listenerClass == null) {
            throw new NullPointerException("listenerClass");
        }

        if (listenerInstance == null) {
            throw new NullPointerException("listenerInstance");
        }

        synchronized (map) {
            List<T> instances = (List<T>) map.get(listenerClass);

            if (instances == null) {
                instances = new ArrayList<T>();
                map.put(listenerClass, instances);
            }
            instances.add(listenerInstance);
        }
    }


    /**
     * Remove all clases and listeners from this support.
     */
    public void clear() {
        synchronized (map) {
            map.clear();
        }
    }


    /**
     * Removes listeners of specified type.
     *
     * @param listenerClass listener type
     */
    public void clear(final Class<?> listenerClass) {
        synchronized (map) {
            map.remove(listenerClass);
        }
    }


    /**
     * Checks if this support contains specified listener.
     *
     * @param listenerClass listener class
     * @param listenerInstance listener instance
     * @return true if this support contained the specifed listener.
     */
    public <T extends EventListener> boolean contains(
        final Class<T> listenerClass, final T listenerInstance) {

        if (listenerClass == null) {
            throw new NullPointerException("listenerClass");
        }

        if (listenerInstance == null) {
            throw new NullPointerException("listenerInstance");
        }

        synchronized (map) {
            List<?> instances = map.get(listenerClass);
            if (instances == null) {
                return false;
            }
            return instances.contains(listenerInstance);
        }
    }


    /**
     * Returns all listener classes.
     *
     * @return listener classes
     */
    public Class<?>[] getListenerClasses() {
        synchronized (map) {
            Class<?>[] listenerClasses = new Class<?>[map.size()];
            map.keySet().toArray(listenerClasses);
            return listenerClasses;
        }
    }


    /**
     * Returns the total number of listeners for this listener suppoert.
     *
     * @return the total number of listeners.
     */
    public int getListenerCount() {
        int count = 0;
        synchronized (map) {
            for (List<?> list : map.values()) {
                count += list.size();
            }
        }
        return count;
    }


    /**
     * Returns the total number of listeners of the supplied type for this
     * listener list.
     *
     * @param listenerClass
     * @return the number of listener instances of given type.
     */
    public int getListenerCount(final Class<?> listenerClass) {

        if (listenerClass == null) {
            throw new NullPointerException("listenerClass");
        }

        synchronized (map) {
            List<?> instances = map.get(listenerClass);
            if (instances == null) {
                return 0;
            }
            return instances.size();
        }
    }


    /*
    public EventListener[] getListeners() {
        List<EventListener> instances = new ArrayList<EventListener>();
        synchronized (map) {
            for (List<? extends EventListener> value : map.values()) {
                instances.addAll(value);
            }
        }
        return (EventListener[]) instances.toArray();
    }
     */


    /**
     * Return an array of all the listeners of the given type.
     *
     * @param listenerClass listener class
     * @return all listeners of the given type.
     */
    @SuppressWarnings("unchecked")
    public <T extends EventListener> T[] getListeners(
        final Class<T> listenerClass) {

        if (listenerClass == null) {
            throw new NullPointerException("listenerClass");
        }

        synchronized (map) {
            List<?> list = map.get(listenerClass);
            T[] result = (T[]) Array.newInstance(listenerClass, list.size());
            for (int i = 0; i < list.size(); i++) {
                result[i] = (T) list.get(i);
            }
            return result;
        }
    }


    /**
     * Removes the listener as a listener of the specified type.
     *
     * @param listenerClass listener class
     * @param listenerInstance listener instance
     * @return true if support contained the specifed listener.
     */
    public <T extends EventListener> boolean remove(
        final Class<T> listenerClass, final T listenerInstance) {

        if (listenerClass == null) {
            throw new NullPointerException("listenerClass");
        }

        if (listenerInstance == null) {
            throw new NullPointerException("listenerInstance");
        }

        synchronized (map) {
            List<?> instances = map.get(listenerClass);
            if (instances == null) {
                return false;
            }
            boolean result = instances.remove(listenerInstance);
            if (result && instances.isEmpty()) {
                map.remove(listenerClass);
            }
            return result;
        }
    }


    private final Map<Class<?>, List<?>> map =
        Collections.synchronizedMap(new HashMap<Class<?>, List<?>>());
}
