package jinahya.xlet.bind;


import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 *
 *
 * @author <a href="jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Bind implements Serializable {


    public Bind() {
        super();

        pcs = new PropertyChangeSupport(this);
    }


    /**
     *
     *
     * @param in
     * @throws IOException
     */
    public abstract void receive(DataInput in) throws IOException;


    /**
     *
     * @param out
     * @throws IOException
     */
    public abstract void send(DataOutput out) throws IOException;


    protected byte[] receiveBytes(DataInput in, boolean nullable)
        throws IOException {

        boolean nullFlag = in.readBoolean();
        if (nullFlag & !nullable) {
            throw new NullPointerException("null received");
        }
        byte[] v = null;
        if (!nullFlag) {
            v = new byte[in.readInt()];
            in.readFully(v);
        }
        return v;
    }


    protected void sendBytes(DataOutput out, byte[] v, boolean nullable)
        throws IOException {

        if (!nullable & v == null) {
            throw new NullPointerException("sending null");
        }
        out.writeBoolean(v == null);
        if (v != null) {
            out.writeInt(v.length);
            out.write(v);
        }
    }


    protected String receiveString(DataInput in, boolean nullable)
        throws IOException {

        byte[] v = receiveBytes(in, nullable);
        return (v == null ? null : new String(v, "UTF-8"));
    }


    protected void sendString(DataOutput out, String v, boolean nullable)
        throws IOException {

        if (!nullable & v == null) {
            throw new NullPointerException("sending null");
        }

        sendBytes(out, v == null ? null : v.getBytes("UTF-8"), nullable);
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


    public void clearParameters() {
        synchronized (parameters) {
            parameters.clear();
        }
    }


    public Object putParameter(String key, Object value) {
        synchronized (parameters) {
            return parameters.put(key, value);
        }
    }


    public Object removeParameter(String key) {
        synchronized (parameters) {
            return parameters.remove(key);
        }
    }


    public String[][] getParameters() {
        String[][] _parameters;
        synchronized (parameters) {
            _parameters = new String[parameters.size()][2];
            int i = 0;
            for (Enumeration e = parameters.keys(); e.hasMoreElements(); i++) {
                _parameters[i][0] = (String) e.nextElement();
                _parameters[i][1] = (String) parameters.get(_parameters[i][0]);
            }
        }
        return _parameters;
    }


    private PropertyChangeSupport pcs;
    private final Hashtable parameters = new Hashtable();
}