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

    // <Class, KeyValueSupport>
    private static final Hashtable INSTANCES = new Hashtable();


    /**
     * Returns mapped instance for given <code>owner</code>.
     *
     * @param owner instance owner
     * @return the instance owned by given <code>owner</code>
     */
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


    /**
     * Returns all classes for created instances.
     *
     * @return an array <code>Class</code>
     */
    public static Class[] getInstanceOwners() {
        final Vector owners = new Vector();
        synchronized (INSTANCES) {
            for (Enumeration e = INSTANCES.keys(); e.hasMoreElements();) {
                owners.addElement(e.nextElement());
            }
        }
        Class[] result = new Class[owners.size()];
        owners.copyInto(result);
        return result;
    }


    /**
     * Removes the instance mapped with specifed <code>owner</code>.
     *
     * @param owner support owner
     * @return previous instance mapped with given <code>owner</code> or null.
     */
    public static KeyValueSupport removeInstance(Class owner) {
        synchronized (INSTANCES) {
            return (KeyValueSupport) INSTANCES.remove(owner);
        }
    }


    private KeyValueSupport(final Class owner) {
        super();

        this.owner = owner;
    }


    /**
     * Returns the current owner of this support.
     *
     * @return owner
     */
    public Class getOwner() {
        return owner;
    }


    /**
     * Removes all entries regardless of type.
     */
    public void clear() {
        values.clear();
    }


    /**
     * Remove all entries of given <code>type</code>.
     *
     * @param type entry type
     */
    public void clear(Class type) {
        values.remove(type);
    }


    /**
     * Returns the total number of entries regardless of type.
     *
     * @return the total number of entries of this support.
     */
    public int size() {
        int size = 0;
        synchronized (values) {
            for (Enumeration e = values.elements(); e.hasMoreElements();) {
                size += ((Hashtable) (e.nextElement())).size();
            }
        }
        return size;
    }


    /**
     * Returns the total number of entries of given <code>type</code>.
     *
     * @param type entry type
     * @return the number of entries of given <code>type</code>
     */
    public int size(Class type) {
        synchronized (values) {
            Hashtable classified = (Hashtable) values.get(type);
            if (classified == null) {
                return 0;
            }
            return classified.size();
        }
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public boolean getBoolean(final String key, final boolean def) {
        return ((Boolean) get(Boolean.TYPE, key, Boolean.valueOf(def))).booleanValue();
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putBoolean(final String key, final boolean val) {
        put(Boolean.TYPE, key, Boolean.valueOf(val));
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public int getInt(final String key, final int def) {
        return ((Integer) get(Integer.TYPE, key, new Integer(def))).intValue();
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putInt(final String key, final int val) {
        put(Integer.TYPE, key, new Integer(val));
    }


    /**
     * 
     * @param key
     */
    public void ridInt(final String key) {
        rid(Integer.TYPE, key);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public float getFloat(final String key, final float def) {
        return ((Float) get(Float.TYPE, key, new Float(def))).floatValue();
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putFloat(final String key, final float val) {
        put(Float.TYPE, key, new Float(val));
    }


    /**
     *
     * @param key
     */
    public void ridFloat(final String key) {
        rid(Float.TYPE, key);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public long getLong(String key, long def) {
        return ((Long) get(Long.class, key, new Long(def))).longValue();
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putLong(String key, long val) {
        put(Long.class, key, new Long(val));
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public double getDouble(String key, double def) {
        return ((Double) get(Double.class, key, new Double(def))).doubleValue();
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putDouble(String key, double val) {
        put(Double.class, key, new Double(val));
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public String getString(String key, String def) {
        return (String) get(String.class, key, def);
    }


    /**
     *
     * @param key
     */
    public void ridString(String key) {
        rid(String.class, key);
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putString(String key, String val) {
        put(String.class, key, val);
    }


    /**
     * Put value.
     *
     * @param type value type
     * @param key value key
     * @param val value
     */
    public void put(final Class type, final String key, final Object val) {

        if (type == null) {
            throw new NullPointerException("type");
        }

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (val == null) {
            throw new NullPointerException("val");
        }

        if (!type.isInstance(val)) {
            throw new IllegalArgumentException(
                val + " is not an instnace of " + type);
        }

        synchronized (values) {
            Hashtable classified = (Hashtable) values.get(type);
            if (classified == null) {
                classified = new Hashtable();
                values.put(type, classified);
            }
        }
    }


    /**
     *
     * @param type
     * @param key
     * @param def
     * @return
     */
    public Object get(final Class type, final String key, final Object def) {

        if (type == null) {
            throw new NullPointerException("type");
        }

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (def != null && !type.isInstance(def)) {
            throw new IllegalArgumentException(
                def + " is not an instnace of " + type);
        }

        synchronized (values) {
            if (!values.contains(type)) {
                return null;
            }
            Hashtable classified = (Hashtable) values.get(type);
            if (!classified.contains(key)) {
                return def;
            }
            return classified.get(key);
        }
    }


    /**
     * Remove specified entry of given type.
     *
     * @param type entry type
     * @param key entry name
     */
    public void remove(final Class type, final String key) {

        if (type == null) {
            throw new NullPointerException("type");
        }

        if (key == null) {
            throw new NullPointerException("key");
        }

        synchronized (values) {
            Hashtable classified = (Hashtable) values.get(type);
            if (classified != null) {
                classified.remove(key);
            }
        }
    }


    private Class owner;
    // <Class, Hashtable<String, Object>>
    private final Hashtable values = new Hashtable();

    private final EventListenerSupport els = new EventListenerSupport();
}
