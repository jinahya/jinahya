/*
 *  Copyright 2009 onacit.
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


package jinahya.util;


import java.beans.PropertyChangeListener;
import java.util.Enumeration;
import java.util.Hashtable;

import jinahya.beans.ExtendedPropertyChangeSupport;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class KeyValueSupport {



    /**
     *
     */
    public static interface Marshaller {


        /**
         *
         * @param support
         * @throws Exception
         */
        public void marshal(KeyValueSupport support) throws Exception;
    }


    /**
     *
     */
    public static interface Unmarshaller {


        /**
         *
         * @param support
         * @throws Exception
         */
        public void unmarshal(KeyValueSupport support) throws Exception;
    }


    /**
     *
     */
    public KeyValueSupport() {
        super();

        epcs = new ExtendedPropertyChangeSupport(this);
    }


    //@Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException(":)");
    }


    /**
     * Check if this KVS contains entries with specified <code>key</code>.
     *
     * @param key key to be checked
     * @return <code>true</code> if this KVS contains an entry with the
     * <code>key</code>; <code>false</code> otherwise
     */
    public boolean contains(String key) {
        return entries.contains(key);
    }


    /**
     * Returns an array of the keys in this KVS.
     *
     * @return all keys.
     */
    public String[] keys() {
        synchronized (entries) {
            String[] keys = new String[entries.size()];
            Enumeration e = entries.keys();
            for (int i = 0; e.hasMoreElements(); i++) {
                keys[i] = (String) e.nextElement();
            }
            return keys;
        }
    }


    /**
     * Removes all of the mappings from this map. The map will be empty after
     * this call returns.
     */
    public void clear() {
        entries.clear();
    }


    /**
     * Removes the <code>key</code> (and its corresponding value) from this
     * Hashtable. This method does nothing if the <code>key</code> is not in
     * this Hashtable.
     *
     * @param key the key that needs to be removed.
     * @return the value to which the <code>key</code> had been mapped in this
     *         KVS, or <code>null</code> if the <code>key</code> did not have a
     *         mapping.
     */
    protected String remove(String key) {
        return (String) entries.remove(key);
    }


    /**
     * Tests if this KVS maps no keys to value.
     *
     * @return <code>true</code> if this KVS maps no keys to values;
     *         <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return entries.isEmpty();
    }


    /**
     * Returns the number of keys in this KVS.
     *
     * @return the number of keys in this KVS.
     */
    public int size() {
        return entries.size();
    }


    /**
     *
     * @param unmarshaller
     * @throws Exception
     */
    public void unmarshal(final Unmarshaller unmarshaller) throws Exception {
        KeyValueSupport copy = new KeyValueSupport();
        unmarshaller.unmarshal(copy);
        copy(copy);
    }


    /**
     *
     * @param marshaller
     * @throws Exception
     */
    public void marhsal(final Marshaller marshaller) throws Exception {
        KeyValueSupport copy = new KeyValueSupport();
        copy.copy(this);
        marshaller.marshal(copy);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public String getString(String key, String def) {
        String val = (String) entries.get(key);
        return val != null ? val : def;
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putString(final String key, final String val) {
        String old = getString(key, null);
        entries.put(key, val);
        epcs.firePropertyChange(key, old, val);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public boolean getBoolean(String key, boolean def) {
        String val = getString(key, null);
        return val != null ? Boolean.valueOf(val).booleanValue() : def;
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putBoolean(String key, boolean val) {
        boolean old = getBoolean(key, false);
        entries.put(key, (val ? Boolean.TRUE : Boolean.FALSE).toString());
        epcs.firePropertyChange(key, old, val);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public byte getByte(final String key, final byte def) {
        String val = getString(key, null);
        return val != null ? Byte.parseByte(val) : def;
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putByte(String key, byte val) {
        byte old = getByte(key, new Integer(~val).byteValue());
        entries.put(key, Byte.toString(val));
        epcs.firePropertyChange(key, old, val);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public short getShort(String key, short def) {
        String val = getString(key, null);
        return val != null ? Short.parseShort(val) : def;
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putShort(String key, short val) {
        short old = getShort(key, new Integer(~val).shortValue());
        putString(key, Short.toString(val));
        epcs.firePropertyChange(key, old, val);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public int getInt(String key, int def) {
        String val = getString(key, null);
        return val != null ? Integer.parseInt(val) : def;
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putInt(String key, int val) {
        int old = getInt(key, ~val);
        putString(key, Integer.toString(val));
        epcs.firePropertyChange(key, old, val);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public long getLong(String key, long def) {
        String val = getString(key, null);
        return val != null ? Long.parseLong(val) : def;
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putLong(String key, long val) {
        long old = getLong(key, ~val);
        putString(key, Long.toString(val));
        epcs.firePropertyChange(key, old, val);
    }


    private double getRandom(double except) {
        while (true) {
            double random = Math.random();
            if (random != except) {
                return random;
            }
        }
    }


    private float getRandom(float except) {
        while (true) {
            float random = new Float(getRandom((double) except)).floatValue();
            if (random != except) {
                return random;
            }
        }
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public float getFloat(String key, float def) {
        String val = getString(key, null);
        return val != null ? Float.valueOf(val).floatValue() : def;
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putFloat(String key, float val) {
        float old = getFloat(key, getRandom(val));
        putString(key, Float.toString(val));
        epcs.firePropertyChange(key, old, val);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public double getDouble(String key, double def) {
        String val = getString(key, null);
        return val != null ? Double.valueOf(val).doubleValue() : def;
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putDouble(String key, double val) {
        double old = getDouble(key, getRandom(val));
        putString(key, Double.toString(val));
        epcs.firePropertyChange(key, old, val);
    }


    /**
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        epcs.addPropertyChangeListener(listener);
    }


    /**
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        epcs.removePropertyChangeListener(listener);
    }


    public void copy(KeyValueSupport support) {
        synchronized (entries) {
            entries.clear();
            synchronized (support.entries) {
                for (Enumeration e = support.entries.keys();
                     e.hasMoreElements(); ) {

                    String key = (String) e.nextElement();
                    entries.put(key, (String) support.entries.get(key));
                }
            }
        }
    }


    public void merge(KeyValueSupport support, boolean overwrite) {
         synchronized (entries) {
            synchronized (support.entries) {
                Enumeration e = support.entries.keys();
                while (e.hasMoreElements()) {
                    Object key = e.nextElement();
                    if (!overwrite && entries.contains(key)) {
                        continue;
                    }
                    entries.put(key, support.entries.get(key));
                }
            }
        }
    }


    public boolean retain(KeyValueSupport support) {
        boolean changed = false;
        synchronized (support.entries) {
            synchronized (entries) {
                String[] keys = keys();
                for (int i = 0; i < keys.length; i++) {
                    if (!support.entries.contains(keys[i])) {
                        entries.remove(keys[i]);
                        changed = true;
                    }
                }
            }
        }
        return changed;
    }


    /**
     *
     * @param prefix
     * @return
     */
    public KeyValueSupport addPrefix(final String prefix) {
        String trimmed = prefix.trim();
        if (trimmed.length() == 0) {
            throw new IllegalArgumentException("ILLEGAL PREFIX: " + prefix);
        }
        synchronized (entries) {
            String[] keys = keys();
            for (int i = 0; i < keys.length; i++) {
                entries.put(trimmed + keys[i], entries.get(keys[i]));
                entries.remove(keys[i]);
            }
        }
        return this;
    }


    /**
     * Removes prefix from each entry's key.
     *
     * @param prefix
     * @return self
     */
    public KeyValueSupport removePrefix(final String prefix) {
        String trimmed = prefix.trim();
        if (trimmed.length() == 0) {
            throw new IllegalArgumentException("ILLEGAL PREFIX: " + prefix);
        }
        synchronized (entries) {
            String[] keys = keys();
            for (int i = 0; i < keys.length; i++) {
                if (!keys[i].startsWith(trimmed)) {
                    continue;
                }
                entries.put(keys[i].substring(trimmed.length()),
                            entries.get(keys[i]));
                entries.remove(keys[i]);
            }
        }
        return this;
    }


    /**
     * Returns a new copy contains entries of which each key starts with given
     * prefix.
     *
     * @param prefix prefix to be filtered
     * @return a new copy of KVS
     */
    public KeyValueSupport filterPrefix(final String prefix) {
        String trimmed = prefix.trim();
        if (trimmed.length() == 0) {
            throw new IllegalArgumentException("ILLEGAL PREFIX: " + prefix);
        }
        KeyValueSupport copy = new KeyValueSupport();
        synchronized (entries) {
            String[] keys = keys();
            for (int i = 0; i < keys.length; i++) {
                if (keys[i].startsWith(trimmed)) {
                    copy.entries.put(keys[i], entries.get(keys[i]));
                }
            }
        }
        return copy;
    }


    private final Hashtable entries = new Hashtable();

    private ExtendedPropertyChangeSupport epcs;
}
