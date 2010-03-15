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
public class KeyValueSupport {


    /**
     * Removes all entries regardless of type.
     */
    public final void clear() {
        entries.clear();
    }


    /*
     * Remove all entries of given <code>type</code>.
     *
     * @param type entry type
    public final void clear(final Class type) {
        entries.remove(type);
    }
     */


    /**
     * Returns the total number of entries regardless of type.
     *
     * @return the total number of entries of this support.
     */
    public final int size() {
        int size = 0;
        synchronized (entries) {
            for (Enumeration e = entries.elements(); e.hasMoreElements();) {
                size += ((Hashtable) (e.nextElement())).size();
            }
        }
        return size;
    }


    /*
     * Returns the total number of entries of given <code>type</code>.
     *
     * @param type entry type
     * @return the number of entries of given <code>type</code>
    public final int size(final Class type) {
        synchronized (entries) {
            if (!entries.contains(type)) {
                return 0;
            }
            return ((Hashtable) entries.get(type)).size();
        }
    }
     */


    /**
     *
     * @param key
     * @param def
     * @param putIfAbsent
     * @return
     */
    public boolean getBoolean(final String key, final boolean def,
                              final boolean putIfAbsent) {

        return Boolean.parseBoolean(
            get(key, (def ? Boolean.TRUE : Boolean.FALSE).toString(),
                putIfAbsent));
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putBoolean(final String key, final boolean val) {
        put(key, (val ? Boolean.TRUE : Boolean.FALSE).toString());
    }


    public void putByteArray(final String key, final byte[] bytes) {

    }


    /**
     *
     * @param key
     * @param def
     * @param putIfAbsent
     * @param putIfInvalid
     * @return
     */
    public int getInt(final String key, final int def,
                      final boolean putIfAbsent, final boolean putIfInvalid) {
        try {
            return Integer.parseInt(
                get(key, Integer.toString(def), putIfAbsent));
        } catch (NumberFormatException nfe) {
            if (putIfInvalid) {
                putInt(key, def);
            }
            return def;
        }
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putInt(final String key, final int val) {
        put(key, Integer.toString(val));
    }


    /**
     *
     * @param key
     * @param def
     * @param putIfAbsent
     * @param putIfInvalid
     * @return
     */
    public long getLong(final String key, final long def,
                        final boolean putIfAbsent, final boolean putIfInvalid) {

        try {
            return Long.parseLong(get(key, Long.toString(def), putIfAbsent));
        } catch (NumberFormatException nfe) {
            if (putIfInvalid) {
                putLong(key, def);
            }
            return def;
        }
    }


    /**
     *
     * @param key
     * @param val
     */
    public final void putLong(final String key, final long val) {
        put(key, Long.toString(val));
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putFloat(final String key, final float val) {
        put(key, Float.toString(val));
    }


    /**
     *
     * @param key
     * @param def
     * @param putIfAbsent
     * @param putIfInvalid
     * @return
     */
    public float getFloat(final String key, final float def,
                          final boolean putIfAbsent,
                          final boolean putIfInvalid) {

        try {
            return Float.parseFloat(get(key, Float.toString(def), putIfAbsent));
        } catch (NumberFormatException nfe) {
            if (putIfInvalid) {
                putFloat(key, def);
            }
            return def;
        }
    }


    /**
     *
     * @param key
     * @param val
     */
    public void putDouble(final String key, final double val) {
        put(key, Double.toString(val));
    }


    /**
     *
     * @param key
     * @param def
     * @param putIfAbsent
     * @param putIfInvalid
     * @return
     */
    public double getDouble(final String key, final double def,
                            final boolean putIfAbsent,
                            final boolean putIfInvalid) {

        try {
            return Double.parseDouble(
                get(key, Double.toString(def), putIfAbsent));
        } catch (NumberFormatException nfe) {
            if (putIfInvalid) {
                putDouble(key, def);
            }
            return def;
        }
    }


    /**
     * Checks whether an entry for specified <code>key</code> exits or not.
     *
     * @param key entry name
     * @return true if an entry for given <code>key</code> exists.
     */
    public boolean contains(final String key) {
        return entries.containsKey(key);
    }


    /**
     * Returns entry value for given <code>key</code>.
     *
     * @param key entry name
     * @param def default value for return if not found
     * @param putIfAbsent flag for put <code>def</code> if no entry found. This
     *        flag will be silently ignored if <code>def</code> is nul.
     * @return
     */
    public String get(final String key, final String def,
                      final boolean putIfAbsent) {

        synchronized (entries) {
            Object val = entries.get(key);
            if (val != null) {
                return (String) val;
            } else {
                if (def != null && putIfAbsent) {
                    put(key, def);
                }
                return def;
            }
        }
    }


    /**
     *
     * @param key entry name
     * @param val new value
     * @return old value
     */
    public String put(final String key, final String val) {

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (val == null) {
            throw new NullPointerException("val");
        }

        synchronized (entries) {
            String oldValue = (String) entries.put(key, val);
            this.fireEntryChangeEvent(key, oldValue, val);
            return oldValue;
        }
    }


    /**
     * Removes entry for the specifed <code>key</code>.
     *
     * @param key entry name
     * @return true if entry for specified <code>key</code> exists and removed.
     */
    public boolean remove(final String key) {
        synchronized (entries) {
            String oldValue = (String) entries.remove(key);
            if (oldValue != null) {
                this.fireEntryChangeEvent(key, oldValue, null);
            }
            return oldValue != null;
        }
    }


    /**
     * Put value.
     *
     * @param type value type
     * @param key value key
     * @param val value
     */
    /*
    public final void put(final Class type, final String key,
                          final Object val) {

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

        synchronized (entries) {
            Hashtable classified = (Hashtable) entries.get(type);
            if (classified == null) {
                classified = new Hashtable();
                entries.put(type, classified);
            }
            classified.put(key, val);
        }
    }
     */


    /**
     *
     * @param type
     * @param key
     * @param def
     * @return
     */
    /*
    public final Object get(final Class type, final String key,
                            final Object def) {

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

        synchronized (entries) {
            if (!entries.contains(type)) {
                return def;
            }
            Hashtable classified = (Hashtable) entries.get(type);
            if (!classified.contains(key)) {
                return def;
            }
            return classified.get(key);
        }
    }
     */


    /**
     * Remove specified entry of given type.
     *
     * @param type entry type
     * @param key entry name
     */
    /*
    public final void remove(final Class type, final String key) {

        if (type == null) {
            throw new NullPointerException("type");
        }

        if (key == null) {
            throw new NullPointerException("key");
        }

        synchronized (entries) {
            Hashtable classified = (Hashtable) entries.get(type);
            if (classified != null) {
                if (classified.remove(key) != null) {
                    if (classified.isEmpty()) {
                        entries.remove(type);
                    }
                }
            }
        }
    }
     */


    /**
     * 
     * @param enumerator
     */
    public void enumerate(final EntryEnumerator enumerator) {
        synchronized (entries) {
            enumerator.enumerationStarting();
            for (Enumeration e = entries.keys(); e.hasMoreElements();) {
                final String key = (String) e.nextElement();
                final String val = (String) entries.get(key);
                enumerator.enumerate(key, val);
            }
            enumerator.enumerationFinished();
        }
    }


    /**
     * 
     * @param e
     */
    protected void fireEntryChangeEvent(final EntryChangeEvent e) {
        synchronized (els) {
            Object[] listeners = els.getListeners(EntryChangeListener.class);
            for (int i = 0; i < listeners.length; i++) {
                ((EntryChangeListener) listeners[i]).entryChanged(e);
            }
        }
    }


    /**
     *
     * @param key
     * @param oldValue
     * @param newValue
     */
    protected void fireEntryChangeEvent(final String key, final String oldValue,
                                        final String newValue) {

        fireEntryChangeEvent(
            new EntryChangeEvent(this, key, oldValue, newValue));
    }


    /**
     *
     * @param l
     */
    public void addEntryChangeListener(final EntryChangeListener l) {
        els.add(EntryChangeListener.class, l);
    }

    /**
     * 
     * @param l
     */
    public void removeEntryChangeListener(final EntryChangeListener l) {
        els.remove(EntryChangeListener.class, l);
    }


    // <String, String>
    private final Hashtable entries = new Hashtable();

    private final EventListenerSupport els = new EventListenerSupport();
}
