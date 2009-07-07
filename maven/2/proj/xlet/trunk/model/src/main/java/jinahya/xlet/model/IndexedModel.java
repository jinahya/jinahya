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

package jinahya.xlet.model;


import java.beans.PropertyChangeEvent;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class IndexedModel extends BoundModel {


    /**
     *
     * @param clazz bean class
     * @param name property name which will be fired with for change event
     * @param read property read method name
     */
    public IndexedModel(Class clazz, String name, String read) {
        super(clazz);

        this.name = name;

        try {
            this.read = clazz.getMethod(read, new Class[0]);
            try {
                setValue(this.read.invoke(getBean(), new Object[0]));
            } catch (IllegalAccessException iae) {
                iae.printStackTrace();
            } catch (InvocationTargetException ite) {
                ite.printStackTrace();
            }
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
        }
    }


    //@Override
    public void propertyChange(PropertyChangeEvent pce) {
        super.propertyChange(pce);
        if (pce.getSource().equals(getBean())) {
            if (pce.getPropertyName().equals(name)) {
                setValue(pce.getNewValue());
            }
            firePropertyChange("bean." + pce.getPropertyName(),
                               pce.getNewValue(), pce.getNewValue());
        }
    }


    //@Override
    public synchronized void setBean(Object bean) {
        try {
            setValue(read.invoke(bean, new Object[0]));
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        }
        super.setBean(bean);
    }


    public synchronized Object getValue() {
        return value;
    }


    protected synchronized void setValue(Object value) {
        Object oldValue = this.value;
        this.value = value;
        index = Array.getLength(this.value) == 0 ? -1 : 0;
        firePropertyChange(name, oldValue, this.value);
    }


    public synchronized int getIndex() {
        return index;
    }


    public synchronized void setIndex(int index) {

        if (index < -1) {
            setIndex(-1);
            return;
        }

        int length = getLength();
        if (index >= length) {
            setIndex(length -1);
            return;
        }

        int oldIndex = this.index;
        this.index = index;
        firePropertyChange("index", oldIndex, this.index);
    }


    public synchronized boolean increaseIndex(boolean roll) {
        if (index < 0) {
            return false;
        }
        int length = getLength();
        if (length <= 1) {
            return false;
        }
        if (index < length - 1) {
            setIndex(index + 1);
            return false;
        } else {
            setIndex(0);
            return true;
        }
    }


    public synchronized boolean decreaseIndex(boolean roll) {
        if (index < 0) {
            return false;
        }
        int length = getLength();
        if (length <= 1) {
            return false;
        }
        if (index > 0) {
            setIndex(index - 1);
            return false;
        } else {
            setIndex(length - 1);
            return true;
        }
    }


    public synchronized int getLength() {
        return Array.getLength(value);
    }


    public synchronized Object getValueAt(int index) {
        return Array.get(value, index);
    }


    private String name;
    private Method read;

    private Object value;
    private int index;
}