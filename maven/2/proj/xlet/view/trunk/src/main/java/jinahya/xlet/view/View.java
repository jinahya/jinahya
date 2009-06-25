package jinahya.xlet.view;


import java.beans.PropertyChangeEvent;

import jinahya.xlet.model.Model;


/**
 *
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a>
 */
public abstract class View extends AbstractView {


    public View(Class clazz)
        throws InstantiationException, IllegalAccessException {

        super();

        if (Model.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException
                (clazz + " is not assignable to " + Model.class);
        }

        model = (Model) (this.clazz = clazz).newInstance();
    }


    public synchronized Model getModel() {
        return model;
    }


    public synchronized void setModel(Model model) {
        if (!clazz.isInstance(model)) {
            throw new IllegalArgumentException
                (model + " is not an instance of " + clazz);
        }
        this.model.removePropertyChangeListener(this);
        Model old = this.model;
        this.model = model;
        firePropertyChange("model", old, this.model);
        this.model.addPropertyChangeListener(this);
    }


    //@Override
    public void propertyChange(PropertyChangeEvent pce) {
        super.propertyChange(pce);
        // empty
    }


    private Class clazz;
    private Model model;
}