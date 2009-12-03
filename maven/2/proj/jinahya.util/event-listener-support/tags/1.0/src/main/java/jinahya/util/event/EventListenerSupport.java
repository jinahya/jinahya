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

package jinahya.util.event;


import java.util.EventListener;
import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class EventListenerSupport {


    /**
     * Creates a new instance.
     *
     * @param source source
     */
    public EventListenerSupport(Object source) {
        super();

        this.source = source;
    }


    /**
     * Returns the source of this support.
     *
     * @return source owner
     */
    public Object getSource() {
        return source;
    }


    /**
     * Returns listeners which is or assignable from specified
     * <code>listenerClass</code>
     *
     * @param listenerClass listener class
     * @return an instance of vector contains listener instances.
     */
    public Vector getListeners(final Class listenerClass) {
        return getListeners(listenerClass, true);
    }


    /**
     * Returns listeners.
     *
     * @param listenerClass listener class
     * @param includeAssignables true for including those assignlables too.
     * @return an instance of vector contains listener instances.
     */
    public Vector getListeners(final Class listenerClass,
                               final boolean includeAssignables) {

        Vector vector = new Vector();

        synchronized (listeners) {
            for (int i = 0; i < listeners.size(); i += 2) {
                Class clazz = (Class) listeners.elementAt(i);
                if (clazz.equals(listenerClass) ||
                    (includeAssignables &&
                     listenerClass.isAssignableFrom(clazz))) {
                    vector.addElement(listeners.elementAt(i + 1));
                }
            }
        }

        return vector;
    }


    /**
     * Adds listener to this support.
     *
     * @param listener listener to be added
     * @return true if added, false if already added
     */
    public boolean addEventListener(EventListener listener) {
        synchronized (listeners) {
            int index = listeners.indexOf(listener);
            if (index != -1) {
                return false;
            }
            listeners.addElement(listeners.getClass());
            listeners.addElement(listener);
        }
        return true;
    }


    /**
     * Removes listener from this support.
     *
     * @param listener listener to be removed
     * @return true if specifed <code>listener</code> removed from this support.
     */
    public boolean removeEventListener(final EventListener listener) {
        if (listener == null) {
            throw new NullPointerException("listener");
        }
        synchronized (listeners) {
            int index = listeners.indexOf(listener);
            if (index == -1) {
                return false;
            }
            listeners.removeElementAt(index); // instance
            listeners.removeElementAt(index - 1); // class
        }
        return true;
    }


    private Object source;

    private final Vector listeners = new Vector();
}
