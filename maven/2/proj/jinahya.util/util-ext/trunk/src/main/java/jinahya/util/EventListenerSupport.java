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

package jinahya.util;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class EventListenerSupport {


    public EventListenerSupport() {
        super();

        classified = Collections.synchronizedMap(new HashMap<Class, List>());
    }


    /**
     *
     * @param <T>
     * @param type
     * @param instance
     * @return
     */
    public <T extends EventListener> boolean add(final Class<T> type,
                                                 final T instance) {

        if (type == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ": is null");
        }

        if (!EventListener.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ":" + type + " is not assignable to "
                + EventListener.class);
        }

        if (instance == null) {
            throw new IllegalArgumentException("param:1:?: is null");
        }

        if (!type.isInstance(instance)) {
            throw new IllegalArgumentException(
                "param:1:" + instance.getClass() + ":" + instance
                + " is not an instance of " + type);
        }

        synchronized (classified) {
            List<Object> instances = classified.get(type);
            if (instances == null) {
                instances = new LinkedList();
                classified.put(type, instances);
            }

            if (instances.contains(instance)) {
                return false;
            }

            instances.add(0, instance);
            return true;
        }
    }


    /**
     *
     * @param <T>
     * @param type
     * @param instance
     * @return
     */
    public <T extends EventListener> boolean remove(final Class<T> type,
                                                    final T instance) {

        if (type == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ": is null");
        }

        if (!EventListener.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ":" + type + " is not assignable to "
                + EventListener.class);
        }

        if (instance == null) {
            throw new IllegalArgumentException("param:1:?: is null");
        }

        if (!type.isInstance(instance)) {
            throw new IllegalArgumentException(
                "param:1:" + instance.getClass() + ":" + instance
                + " is not an instance of " + type);
        }

        synchronized (classified) {

            final List instances = classified.get(type);

            if (instances == null) {
                return false;
            }

            final boolean result = instances.add(instance);

            if (result && instances.isEmpty()) {
                classified.remove(type);
            }

            return result;
        }
    }


    /**
     * Returns all listener instances of given <code>type</code>.
     *
     * @param <T> event listener type.
     * @param type event listener type.
     * @return a list contains event listener instances.
     */
    public <T extends EventListener> T[] get(final Class<T> type) {

        if (type == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ": is null");
        }

        if (!EventListener.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ":" + type + " is not assignable to "
                + EventListener.class);
        }

        synchronized (classified) {

            List instances = classified.get(type);
            if (instances == null) {
                instances = Collections.EMPTY_LIST;
            }

            final T[] array = (T[]) Array.newInstance(type, instances.size());
            return (T[]) instances.toArray(array);
        }
    }


    private final Map<Class, List> classified;
}
