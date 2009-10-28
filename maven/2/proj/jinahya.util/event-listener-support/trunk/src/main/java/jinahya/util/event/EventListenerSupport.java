/*
 *  Copyright 2009 onacit.
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
     * 
     * @param source
     */
    public EventListenerSupport(Object source) {
        super();

        this.source = source;
    }


    /**
     *
     * @return
     */
    public Object getSource() {
        return source;
    }


    /**
     *
     * @param listenerClass
     * @return
     */
    public Vector getListeners(final Class listenerClass) {
        return getListeners(listenerClass, true);
    }


    /**
     *
     * @param listenerClass
     * @param includeAssignables
     * @return
     */
    public Vector getListeners(final Class listenerClass,
                               final boolean includeAssignables) {

        Vector vector = new Vector();

        synchronized (listeners) {
            for (int i = 0; i < listeners.size(); i += 2) {
                Class clazz = (Class) listeners.elementAt(i);
                if (clazz.equals(listenerClass)) {
                    vector.addElement(listeners.elementAt(i + 1));
                } else {
                    if (includeAssignables &&
                        listenerClass.isAssignableFrom(clazz)) {
                        vector.addElement(listeners.elementAt(i + 1));
                    }
                }
            }
        }

        return vector;
    }


    /**
     *
     * @param listener
     * @return
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
     *
     * @param listener
     * @return
     */
    public boolean removeEventListener(EventListener listener) {
        synchronized (listeners) {
            int index = listeners.indexOf(listener);
            if (index == -1) {
                return false;
            }
            listeners.removeElementAt(index);
            listeners.removeElementAt(index - 1);
        }
        return true;
    }


    private Object source;

    private final Vector listeners = new Vector();
}
