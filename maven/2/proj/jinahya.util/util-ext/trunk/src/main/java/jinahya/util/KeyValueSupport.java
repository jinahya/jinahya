/*
 *  Copyright 2010 Jin Kwon.
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

package jinahya.util;


import java.util.Hashtable;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KeyValueSupport {


    /**
     * 
     */
    public KeyValueSupport() {
        super();

        classified = new Hashtable<Class, Hashtable<String, Object>>();
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public boolean getBoolean(final String key, final boolean def) {
        final Boolean val = get(Boolean.class, key, def);
        if (val != null) {
            return val.booleanValue();
        }
        return def;
    }


    /**
     * 
     * @param key
     * @param val
     * @return
     */
    public boolean putBoolean(final String key, final boolean val) {
        final Boolean pre = put(Boolean.class, key, val);
        if (pre != null) {
            return pre.booleanValue();
        }
        return false;
    }


    /**
     *
     * @param key
     * @param val
     * @return
     */
    public boolean ridtBoolean(final String key) {
        final Boolean rid = rid(Boolean.class, key);
        if (rid != null) {
            return rid.booleanValue();
        }
        return false;
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public float getFloat(final String key, final float def) {
        final Float val = get(float.class, key, def);
        if (val != null) {
            return val.floatValue();
        }
        return def;
    }


    /**
     *
     * @param key
     * @param val
     * @return
     */
    public float putFloat(final String key, final float val) {
        final Float pre = put(Float.class, key, val);
        if (pre != null) {
            return pre.floatValue();
        }
        return 0.0f;
    }


    /**
     *
     * @param key
     * @param val
     * @return
     */
    public float ridFloat(final String key) {
        final Float rid = rid(Float.class, key);
        if (rid != null) {
            return rid.floatValue();
        }
        return 0.0f;
    }

    /**
     *
     * @param key
     * @param def
     * @return
     */
    public double getDouble(final String key, final double def) {
        final Double val = get(double.class, key, def);
        if (val != null) {
            return val.doubleValue();
        }
        return def;
    }


    /**
     *
     * @param key
     * @param val
     * @return
     */
    public double putDouble(final String key, final double val) {
        final Double pre = put(Double.class, key, val);
        if (pre != null) {
            return pre.doubleValue();
        }
        return 0.0d;
    }


    /**
     *
     * @param key
     * @param val
     * @return
     */
    public double ridDouble(final String key) {
        final Double rid = rid(Double.class, key);
        if (rid != null) {
            return rid.doubleValue();
        }
        return 0.0d;
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public String getString(final String key, final String def) {
        final String val = get(String.class, key, def);
        if (val != null) {
            return val;
        }
        return def;
    }


    /**
     *
     * @param key
     * @param val
     * @return
     */
    public String putString(final String key, final String val) {
        return put(String.class, key, val);
    }


    /**
     *
     * @param key
     * @param val
     * @return
     */
    public String ridString(final String key) {
        return rid(String.class, key);
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public int getInt(final String key, final int def) {
        final Integer val = get(Integer.class, key, def);
        if (val != null) {
            return val.intValue();
        }
        return def;
    }


    /**
     *
     * @param key
     * @param val
     * @return
     */
    public int putInt(final String key, final int val) {
        final Integer pre = put(Integer.class, key, val);
        if (pre != null) {
            return pre;
        }
        return 0;
    }


    /**
     *
     * @param key
     * @return
     */
    public int ridInt(final String key) {
        final Integer rid = rid(Integer.class, key);
        if (rid != null) {
            return rid;
        }
        return 0;
    }


    /**
     *
     * @param key
     * @param def
     * @return
     */
    public char getChar(final String key, final char def) {
        final Character val = get(Character.class, key, def);
        if (val != null) {
            return val.charValue();
        }
        return def;
    }


    /**
     *
     * @param key
     * @param val
     * @return
     */
    public char putChar(final String key, final char val) {
        final Character pre = put(Character.class, key, val);
        if (pre != null) {
            return pre;
        }
        return '\u0000';
    }


    /**
     *
     * @param key
     * @return
     */
    public char ridChar(final String key) {
        final Character rid = rid(Character.class, key);
        if (rid != null) {
            return rid;
        }
        return '\u0000';
    }


    /**
     *
     * @param <T>
     * @param cls
     * @param key
     * @param val
     */
    public <T> T put(final Class<T> cls, final String key, final T val) {

        if (cls == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ": is null");
        }

        if (key == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        if (val == null) {
            throw new IllegalArgumentException("param:2::" + val + " is null");
        }

        if (!cls.isInstance(val)) {
            throw new IllegalArgumentException(
                "param:2:" + val.getClass() + ":" + val
                + " is not an instance of param:0:" + Class.class + ":" + cls);
        }

        synchronized (classified) {
            Hashtable<String, Object> named = classified.get(cls);
            if (named == null) {
                named = new Hashtable<String, Object>();
                classified.put(cls, named);
            }

            return (T) named.put(key, val);
        }
    }


    /**
     *
     * @param <T>
     * @param cls
     * @param key
     * @param def
     * @return
     */
    public <T> T get(final Class<T> cls, final String key, final T def) {

        if (cls == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + " is null");
        }

        if (key == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + " is null");
        }

        if (def != null && !cls.isInstance(def)) {
            throw new IllegalArgumentException(
                "param:2:" + def.getClass() + ":" + def
                + " is not an instance of param:0:" + cls);
        }

        synchronized (classified) {
            T val = null;
            final Hashtable<String, Object> named = classified.get(cls);
            if (named != null) {
                val = (T) named.get(key);
            }
            if (val == null) {
                return def;
            }
            return val;
        }
    }


    /**
     *
     * @param <T>
     * @param cls
     * @param key
     * @return
     */
    public <T> T rid(final Class<T> cls, final String key) {

        if (cls == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ": is null");
        }

        if (key == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        synchronized (classified) {

            final Hashtable<String, Object> named = classified.get(cls);

            if (named == null) {
                return null;
            }

            final T result = (T) named.remove(key);
            if (result != null && named.isEmpty()) {
                classified.remove(cls);
            }
            return result;
        }
    }


    private final Hashtable<Class, Hashtable<String, Object>> classified;
}
