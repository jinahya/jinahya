package jinahya.xlet.model;


import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import jinahya.xlet.bind.Bind;


/**
 *
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a>
 */
public abstract class Model extends AbstractModel {


    public Model(Class clazz)
        throws InstantiationException, IllegalAccessException {

        super();

        if (Bind.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException
                (clazz + " is not assignable to " + Bind.class);
        }

        bind = (Bind) (this.clazz = clazz).newInstance();
    }


    public Bind getBind() {
        return bind;
    }


    public void setBind(Bind bind) {
        if (!clazz.isInstance(bind)) {
            throw new IllegalArgumentException
                (bind + " is not an bind of " + clazz);
        }
        this.bind.removePropertyListener(this);
        Bind old = this.bind;
        this.bind = bind;
        firePropertyChangeEvent("bind", old, this.bind);
        this.bind.addPropertyListener(this);
    }


    private Class clazz;
    private Bind bind;
}