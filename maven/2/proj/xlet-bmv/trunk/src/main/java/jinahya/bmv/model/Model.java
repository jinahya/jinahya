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


    public Model() {
        super();
    }


    public Bind getBind() {
        return bind;
    }


    public void setBind(Bind newBind) {
        if (bind != null) {
            bind.removePropertyChangeListener(this);
        }
        Bind oldBind = bind;
        bind = newBind;
        firePropertyChange("bind", oldBind, bind);
        if (bind != null) {
            bind.addPropertyChangeListener(this);
        }
    }


    // java.beans.PropertyChangeListener
    public void propertyChange(PropertyChangeEvent pce) {
        // empty
    }


    private Bind bind = null;
}