package jinahya.bmv.model;


import java.io.Serializable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import jinahya.bmv.PropertyChangeSupported;
import jinahya.bmv.bind.Bind;


/**
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a>
 */
public abstract class Model extends PropertyChangeSupported
    implements Serializable, PropertyChangeListener {


    public Model(Class clazz, Bind bind) {
        super();

        if (!Bind.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException
                (clazz + " is not assignable to " + Bind.class);
        }
        if (!clazz.isInstance(bind)) {
            throw new IllegalArgumentException
                (String.valueOf(bind) + " is not an instance of " + clazz);
        }

        this.clazz = clazz;
        this.bind = bind;
    }


    // java.beans.PropertyChangeListener
    public void propertyChange(PropertyChangeEvent pce) {
        // empty
    }


    public Bind getBind() {
        return bind;
    }


    public void setBind(Bind newBind) {

        if (!clazz.isInstance(newBind)) {
            throw new IllegalArgumentException
                (String.valueOf(newBind) + " is not an instance of " + clazz);
        }

        bind.removePropertyChangeListener(this);

        Bind oldBind = bind;
        bind = newBind;
        firePropertyChange("bind", oldBind, bind);

        bind.addPropertyChangeListener(this);
    }


    private Class clazz;
    private Bind bind;
}