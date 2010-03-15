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
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KeyValueSupport {


    /**
     * Creates a new instance.
     */
    public KeyValueSupport() {
        super();
    }


    /**
     * Removes all entries regardless of type.
     */
    public void clear() {
        synchronized (values) {
            values.clear();
        }
    }


    /**
     * Remove all entries of given <code>type</code>.
     *
     * @param type entry type
     */
    public void clear(final Class<?> type) {
        synchronized (values) {
            values.remove(type);
        }
    }


    /**
     * Returns the total number of entries regardless of type.
     *
     * @return the total number of entries of this support.
     */
    public int size() {
        int size = 0;
        synchronized (values) {
            for (Map<String, ?> classified : values.values()) {
                size += classified.size();
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
    public int size(final Class<?> type) {
        synchronized (values) {
            if (!values.containsKey(type)) {
                return 0;
            }
            return values.get(type).size();
        }
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public boolean getBoolean(final String key, final boolean def) {
        return get(Boolean.TYPE, key, def);
    }


    /**
     * 
     * @param key
     * @param val
     */
    public void putBoolean(final String key, final boolean val) {
        put(Boolean.TYPE, key, val);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public byte[] getByteArray(final String key, final byte[] def) {
        return get(byte[].class, key, def);
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putByteArray(final String key, final byte[] val) {
        put(byte[].class, key, val);
    }


    /**
     * Returns an integer value for key.
     *
     * @param key entry name
     * @param def default value
     * @return stored value or def if not found
     */
    public int getInt(final String key, final int def) {
        return get(Integer.TYPE, key, def);
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putInt(final String key, final int val) {
        put(Integer.TYPE, key, val);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public float getFloat(final String key, final float def) {
        return get(Float.TYPE, key, def);
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putFloat(String key, float val) {
        put(Float.class, key, new Float(val));
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
    public String getString(final String key, final String def) {
        return get(String.class, key, def);
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putString(final String key, final String val) {
        put(String.class, key, val);
    }


    /**
     * Put specifed value for given <code>key</code> or given <code>type</code>.
     *
     * @param <T> type
     * @param type entry type
     * @param key entry name
     * @param val entry value
     */
    public <T> void put(final Class<T> type, final String key, final T val) {

        if (type == null) {
            throw new NullPointerException("type");
        }

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (val == null) {
            throw new NullPointerException("val");
        }

        synchronized (values) {
            @SuppressWarnings("unchecked")
            Map<String, T> classified = (Map<String, T>) values.get(type);
            if (classified == null) {
                classified = new HashMap<String, T>();
                values.put(type, classified);
            }
            classified.put(key, val);
        }
    }


    /**
     * Returns the value for given key of given type.
     *
     * @param <T> type
     * @param type entry type
     * @param key entry name
     * @param def default value
     * @return value for given key of given type or def if not found
     */
    public <T> T get(final Class<T> type, final String key, final T def) {

        if (type == null) {
            throw new NullPointerException("type");
        }

        if (key == null) {
            throw new NullPointerException("key");
        }

        synchronized (values) {
            if (!values.containsKey(type)) {
                return def;
            }

            Map<String, ?> classified = values.get(type);
            if (!classified.containsKey(key)) {
                return def;
            }

            @SuppressWarnings("unchecked")
            T result = (T) classified.get(key);
            return result;
        }
    }


    /**
     * Remove specified entry of given type.
     *
     * @param <T>
     * @param type
     * @param key
     */
    public <T> void remove(final Class<T> type, final String key) {

        if (type == null) {
            throw new NullPointerException("type");
        }

        if (key == null) {
            throw new NullPointerException("key");
        }

        synchronized (values) {
            if (!values.containsKey(type)) {
                return;
            }

            Map<String, ?> classified = values.get(type);
            if (classified.remove(key) == null) {
                return;
            }

            if (classified.isEmpty()) {
                values.remove(type);
            }
        }
    }


    /**
     *
     */
    private final Map<Class<?>, Map<String, ?>> values =
        Collections.synchronizedMap(new HashMap<Class<?>, Map<String, ?>>());
}
