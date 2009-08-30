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

package jinahya.xlet.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventObject;
import java.util.EventListener;
import java.util.Vector;


/**
 *
 * @author <a href="mailto:jkwon@tlab.co.kr">Jin Kwon</a>
 */
public class EventListenerSupport {


    public EventListenerSupport(Object source) {
        super();

        this.source = source;
    }


    public boolean addListener(EventListener listener) {
        synchronized (listeners) {
            for (int i = 0; i < listeners.size(); i += 2) {
                if (!listeners.elementAt(i).equals(listener.getClass())) {
                    continue;
                }
                if (listeners.elementAt(i + 1).equals(listeners)) {
                    return false;
                }
            }
            listeners.add(listener.getClass());
            listeners.add(listener);
            return true;
        }
    }


    public boolean removeListener(EventListener listener) {
        synchronized (listeners) {
            for (int i = 0; i < listeners.size(); i += 2) {
                if (listeners.elementAt(i).equals(listener.getClass()) &&
                    listeners.elementAt(i + 1).equals(listeners)) {
                    listeners.removeElementAt(i + 1);
                    listeners.removeElementAt(i);
                    return true;
                }
            }
            return false;
        }
    }


    public Vector getListeners(Class listenerClass) {
        return getListeners(listenerClass, new Vector());
    }


    public Vector getListeners(Class cls, Vector vector) {
        synchronized (listeners) {
            for (int i = 0; i < listeners.size(); i += 2) {
                if (listeners.elementAt(i).equals(cls)) {
                    vector.addElement(listeners.elementAt(i + 1));
                }
            }
        }
        return vector;
    }


    public void fireEvent(Class listenerClass, String listenerMethodName,
                          EventObject eventObject)
        throws NoSuchMethodException, IllegalAccessException,
               InvocationTargetException {

        if (!source.equals(eventObject.getSource())) {
            throw new IllegalArgumentException("different source");
        }

        Method listenerMethod = listenerClass.getMethod
            (listenerMethodName, new Class[]{eventObject.getClass()});

        Vector listenerInstances = getListeners(listenerClass);
        for (int i = 0; i < listenerInstances.size(); i++) {
            listenerMethod.invoke(listenerInstances.elementAt(i),
                                  new Object[] {eventObject});
        }
    }


    private Object source;
    private final Vector listeners = new Vector();
}
