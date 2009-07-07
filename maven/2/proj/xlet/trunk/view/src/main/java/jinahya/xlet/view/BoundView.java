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

package jinahya.xlet.view;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BoundView extends View implements PropertyChangeListener {


    public BoundView(Class clazz) {
        super();

        try {
            setBean(bean = (this.clazz = clazz).newInstance());
        } catch (InstantiationException ie) {
            ie.printStackTrace();
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        }
    }


    public void propertyChange(PropertyChangeEvent pce) {
    }


    public synchronized Object getBean() {
        return bean;
    }


    public synchronized void setBean(Object bean) {
        if (!clazz.isInstance(bean)) {
            throw new IllegalArgumentException("not an instance of " + clazz);
        }

        if (this.bean != null) { // in case of construction time
            try {
                Method method = clazz.getMethod
                    ("removePropertyChangeListener",
                     new Class[]{PropertyChangeListener.class});
                try {
                    method.invoke(this.bean, new Object[]{this});
                } catch (IllegalAccessException iae) {
                    iae.printStackTrace();
                } catch (InvocationTargetException ite) {
                    ite.printStackTrace();
                }
            } catch (NoSuchMethodException nsme) {
                nsme.printStackTrace();
            }
        }

        Object oldBean = this.bean;
        this.bean = bean;
        firePropertyChange("bean", oldBean, this.bean);

        try {
            Method method = clazz.getMethod
                ("addPropertyChangeListener",
                 new Class[]{PropertyChangeListener.class});
            try {
                method.invoke(this.bean, new Object[]{this});
            } catch (IllegalAccessException iae) {
                iae.printStackTrace();
            } catch (InvocationTargetException ite) {
                ite.printStackTrace();
            }
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
        }
    }


    private Class clazz;
    private Object bean;
}
