package jinahya.xlet.model;


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


    public void setBind(Bind newBind) {
        if (!clazz.isInstance(newBind)) {
            throw new IllegalArgumentException
                (bind + " is not an bind of " + clazz);
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