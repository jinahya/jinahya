package jinahya.bmv.model;


import java.beans.PropertyChangeEvent;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

import jinahya.bmv.bind.Bind;


/**
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a>
 */
public abstract class IndexedModel extends Model {


    public IndexedModel(Class clazz, String name, String read) {
        super();

        if (!Bind.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException
                ("not assignable to " + Bind.class);
        }

        this.clazz = clazz;
        this.name = name;
        this.read = read;
    }


    //@Override
    public void propertyChange(PropertyChangeEvent pce) {
        super.propertyChange(pce);

        if (pce.getSource().equals(getBind()) &&
            pce.getPropertyName().equals(name)) {

            setValue(pce.getNewValue());
        }
    }


    public void setBind(Bind newBind) {
        if (newBind != null & !clazz.isInstance(newBind)) {
            throw new IllegalArgumentException("not an instannce of " + clazz);
        }

        if (newBind == null) {
            setValue(null);
        } else {
            try {
                Method method = clazz.getMethod(read, new Class[0]);
                setValue(method.invoke(newBind, new Object[0]));
            } catch (Exception e) {
                e.printStackTrace();
                setValue(null);
            }
        }

        super.setBind(newBind);
    }


    public synchronized Object getValue() {
        return value;
    }


    protected synchronized void setValue(Object newValue) {

        if (newValue == null) {
            index = -1;
        } else {
            try {
                index = Array.getLength(newValue) == 0 ? -1 : 0;
            } catch (IllegalArgumentException iae) { // not an array?
                iae.printStackTrace();
                setValue(null);
                return;
            }
        }

        Object oldValue = value;
        value = newValue;

        firePropertyChange("value", oldValue, value);
        firePropertyChange(name, oldValue, value);
    }


    public synchronized int getIndex() {
        return index;
    }


    public synchronized void setIndex(int newIndex) {

        if (value == null) {
            return;
        }

        if (newIndex < 0 || newIndex >= Array.getLength(value)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int oldIndex = index;
        index = newIndex;
        firePropertyChange("index", oldIndex, index);
    }


    public synchronized boolean increaseIndex(int count, boolean roll) {
        if (count <= 0) {
            throw new IllegalArgumentException("negative count: " + count);
        }

        if (value == null) {
            return false;
        }

        int length = Array.getLength(value);
        if (length <= 1) {
            return false;
        }

        int targetIndex = index + count;
        if (targetIndex < length) {
            setIndex(targetIndex);
            return false;
        } else {
            if (roll) {
                setIndex(0);
                return true;
            } else {
                setIndex(length - 1);
                return false;
            }
        }
    }


    public synchronized boolean decreaseIndex(int count, boolean roll) {
        if (count <= 0) {
            throw new IllegalArgumentException("negative count: " + count);
        }

        if (value == null) {
            return false;
        }

        int length = Array.getLength(value);
        if (length <= 1) {
            return false;
        }

        int targetIndex = index - count;
        if (targetIndex >= 0) {
            setIndex(targetIndex);
            return false;
        } else {
            if (roll) {
                setIndex(length - 1);
                return true;
            } else {
                setIndex(0);
                return false;
            }
        }
    }


    public synchronized Object getIndexedValue() {
        if (value == null || Array.getLength(value) == 0) {
            return null;
        }
        return Array.get(value, index);
    }


    private Class clazz;
    private String name;
    private String read;

    private Object value = null;
    private int index = -1;
}
