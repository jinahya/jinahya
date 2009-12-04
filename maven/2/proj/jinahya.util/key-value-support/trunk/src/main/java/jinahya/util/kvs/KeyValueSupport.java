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

package jinahya.util.kvs;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import jinahya.util.els.EventListenerSupport;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class KeyValueSupport {


    private static final String EXTENSION = "kvs";


    // <Class, KeyValueSupport>
    private static final Hashtable INSTANCES = new Hashtable();


    public static KeyValueSupport getInstance(final Class owner) {
        synchronized (INSTANCES) {
            KeyValueSupport instance = (KeyValueSupport) INSTANCES.get(owner);
            if (instance == null) {
                instance = new KeyValueSupport(owner);
                INSTANCES.put(owner, instance);
            }
            return instance;
        }
    }


    public static File[] readAllIn(final File dir) {

        synchronized (INSTANCES) {

            String[] list = dir.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith("." + EXTENSION);
                }
            });

            if (list == null) {
                // this abstract pathname does not denote a directory,
                // or if an I/O error occurs
                return new File[0];
            }

            Vector skippedFileVector = new Vector();

            for (int i = 0; i < list.length; i++) {

                File file = new File(dir, list[i]);

                if (!file.isFile()) {
                    continue;
                }

                Class owner = null;
                try {
                    String filename = file.getName();
                    int lastDotIndex = filename.lastIndexOf('.');
                    owner = Class.forName(filename.substring(0, lastDotIndex));
                } catch (ClassNotFoundException cnfe) {
                    cnfe.printStackTrace();
                    skippedFileVector.addElement(file);
                    continue;
                }

                KeyValueSupport support = getInstance(owner);
                try {
                    support.read(file);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            File[] skippedFiles = new File[skippedFileVector.size()];
            skippedFileVector.toArray(skippedFiles);
            return skippedFiles;
        }
    }


    public static void readAll(File file) throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        try {
            readAll(in);
        } finally {
            in.close();
        }
    }


    public static void readAll(ObjectInput in) throws IOException {
        synchronized (INSTANCES) {
            int size = in.readInt();
            for (int i = 0; i < size; i++) {

                Class owner = null;
                try {
                    owner = Class.forName(in.readUTF());
                } catch (ClassNotFoundException cnfe) {
                    cnfe.printStackTrace();
                    continue;
                }

                KeyValueSupport support = getInstance(owner);
                try {
                    support.read(in);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }


    public static void writeAllIn(File dir) throws IOException {
        synchronized (INSTANCES) {
            for (Enumeration e = INSTANCES.elements(); e.hasMoreElements();) {
                ((KeyValueSupport) e.nextElement()).writeIn(dir);
            }
        }
    }


    public static void writeAll(File file) throws IOException {
        ObjectOutputStream out =
            new ObjectOutputStream(new FileOutputStream(file));
        try {
            writeAll(out);
            out.flush();
        } finally {
            out.close();
        }
    }


    public static void writeAll(ObjectOutput out) throws IOException {
        synchronized (INSTANCES) {
            out.writeInt(INSTANCES.size());
            for (Enumeration e = INSTANCES.elements(); e.hasMoreElements();) {
                KeyValueSupport support = (KeyValueSupport) e.nextElement();
                out.writeUTF(support.getOwner().getName());
                support.write(out);
            }
        }
    }


    private KeyValueSupport(final Class owner) {
        super();

        this.owner = owner;
    }


    public Class getOwner() {
        return owner;
    }


    public void clear() {
        values.clear();
    }


    public int size() {
        return values.size();
    }


    public void readIn(File dir) throws IOException {
        read(new File(dir, owner.getName() + "." + EXTENSION));
    }


    public void read(File file) throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        try {
            read(in);
        } finally {
            in.close();
        }
    }


    public void read(ObjectInput in) throws IOException {
        int size = in.readInt();
        synchronized (values) {
            for (int i = 0; i < size; i++) {
                String key = in.readUTF();
                try {
                    values.put(key, in.readObject());
                } catch (ClassNotFoundException cnfe) {
                    cnfe.printStackTrace();
                    continue;
                }
            }
        }
    }


    public void writeIn(File dir) throws IOException {
        write(new File(dir, owner.getName() + "." + EXTENSION));
    }


    public void write(File file) throws IOException {
        ObjectOutputStream out =
            new ObjectOutputStream(new FileOutputStream(file));
        try {
            write(out);
            out.flush();
        } finally {
            out.close();
        }
    }


    public void write(ObjectOutput out) throws IOException {
        synchronized (values) {
            out.writeInt(values.size());
            for (Enumeration e = values.keys(); e.hasMoreElements();) {
                String key = (String) e.nextElement();
                out.writeUTF(key);
                out.writeObject(values.get(key));
            }
        }
    }


    public int getInt(String key, int def) {
        Object val = get(key, null);
        return val != null ? ((Integer) val).intValue() : def;
    }


    public void putInt(String key, int val) {
        put(key, new Integer(val));
    }


    public float getFloat(String key, float def) {
        Object val = get(key, null);
        return val != null ? ((Float) val).floatValue() : def;
    }


    public void putFloat(String key, float val) {
        put(key, new Float(val));
    }


    public long getLong(String key, long def) {
        Object val = get(key, null);
        return val != null ? ((Long) val).longValue() : def;
    }


    public void putLong(String key, long val) {
        put(key, new Long(val));
    }


    public double getDouble(String key, double def) {
        Object val = get(key, null);
        return val != null ? ((Double)val).doubleValue() : def;
    }


    public void putDouble(String key, double val) {
        put(key, new Double(val));
    }


    public String getString(String key, String def) {
        return (String) get(key, def);
    }


    public void putString(String key, String val) {
        put(key, val);
    }


    public Object get(String key, Object def) {
        Object val = values.get(key);
        return val != null ? val : def;
    }


    public void put(final String key, final Object val) {
        synchronized (values) {
            Object old = values.get(key);
            values.put(key, val);

            firePropertyChangeEvent(key, old, val);
        }
    }


    public void remove(final String key) {
        Object old = values.remove(key);

        firePropertyChangeEvent(key, old, null);
    }


    private void firePropertyChangeEvent(String propertyName, Object oldValue,
                                         Object newValue) {
        PropertyChangeEvent event =
            new PropertyChangeEvent(this, propertyName, oldValue, newValue);
        Object[] listeners = els.getListeners(PropertyChangeListener.class);
        for (int i = 0; i < listeners.length; i++) {
            ((PropertyChangeListener) listeners[i]).propertyChange(event);
        }
    }


    public void addPropertyChangeListener(final PropertyChangeListener l) {
        els.addIfNotAdded(PropertyChangeListener.class, l);
    }


    public void removePropertyChangeListener(final PropertyChangeListener l) {
        els.removeIfAdded(PropertyChangeListener.class, l);
    }


    private Class owner;

    // <String, Object>
    private final Hashtable values = new Hashtable();

    private final EventListenerSupport els = new EventListenerSupport();
}
