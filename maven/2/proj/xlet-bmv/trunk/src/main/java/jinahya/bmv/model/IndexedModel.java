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


    public IndexedModel(Class clazz, Bind bind, String name, String read) {
        super(clazz, bind);

        this.name = name;

        try {
            this.read = clazz.getMethod(read, new Class[0]);
            value = this.read.invoke(getBind(), new Object[0]);
            index = Array.getLength(value) == 0 ? -1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        super.setBind(newBind);

        try {
            setValue(read.invoke(newBind, new Object[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Object getValue() {
        return value;
    }


    protected void setValue(Object newValue) {
        Object oldValue = value;
        value = newValue;

        index = Array.getLength(value) == 0 ? -1 : 0;

        firePropertyChange("value", oldValue, value);
        firePropertyChange(name, oldValue, value);
    }


    public int getIndex() {
        return index;
    }


    public void setIndex(int newIndex) {

        if (newIndex < -1 || newIndex >= Array.getLength(value)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int oldIndex = index;
        index = newIndex;
        firePropertyChange("index", oldIndex, index);
    }


    public boolean increaseIndex(int count, boolean roll) {
        if (count <= 0) {
            throw new IllegalArgumentException("negative count: " + count);
        }

        if (index < 0) {
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


    public boolean decreaseIndex(int count, boolean roll) {
        if (count <= 0) {
            throw new IllegalArgumentException("negative count: " + count);
        }

        if (index < 0) {
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


    public Object getIndexedValue() {
        if (index < 0 || Array.getLength(value) == 0) {
            return null;
        }
        return Array.get(value, index);
    }


    private String name;
    private Method read;

    private Object value;
    private int index;
}