package jinahya.xlet.model;


import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 *
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a>
 */
public abstract class AbstractModel implements PropertyChangeListener {


    public AbstractModel() {
        super();

        pcs = new PropertyChangeSupport(this);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }


    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }


    protected void firePropertyChange(String propertyName, Object oldValue,
                                      Object newValue) {

        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }


    protected void firePropertyChange(String propertyName, int oldValue,
                                      int newValue) {

        firePropertyChange(propertyName, new Integer(oldValue),
                           new Integer(newValue));
    }


    protected void firePropertyChange(String propertyName, boolean oldValue,
                                      boolean newValue) {

        firePropertyChange(propertyName,
                           oldValue ? Boolean.TRUE : Boolean.FALSE,
                           newValue ? Boolean.TRUE : Boolean.FALSE);
    }


    protected void firePropertyChange(String propertyName, long oldValue,
                                      long newValue) {

        firePropertyChange(propertyName, new Long(oldValue),
                           new Long(newValue));
    }


    protected void firePropertyChange(String propertyName, byte oldValue,
                                      byte newValue) {

        firePropertyChange(propertyName, new Byte(oldValue),
                           new Byte(newValue));
    }


    protected void firePropertyChange(String propertyName, char oldValue,
                                      char newValue) {

        firePropertyChange(propertyName, new Character(oldValue),
                           new Character(newValue));
    }


    protected void firePropertyChange(String propertyName, short oldValue,
                                      short newValue) {

        firePropertyChange(propertyName, new Short(oldValue),
                           new Short(newValue));
    }


    protected void firePropertyChange(String propertyName, float oldValue,
                                      float newValue) {

        firePropertyChange(propertyName, new Float(oldValue),
                           new Float(newValue));
    }


    protected void firePropertyChange(String propertyName, double oldValue,
                                      double newValue) {

        firePropertyChange(propertyName, new Double(oldValue),
                           new Double(newValue));
    }


    private PropertyChangeSupport pcs;
}