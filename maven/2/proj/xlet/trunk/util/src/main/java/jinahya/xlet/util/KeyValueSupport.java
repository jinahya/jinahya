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


package jinahya.xlet.util;


import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KeyValueSupport {


    protected static String getString(Dictionary entries, String key,
                                      String def) {

        String val = (String) entries.get(key);
        return val != null ? val : def;
    }


    protected static void putString(Dictionary entries, String key,
                                    String val) {

        entries.put(key, val);
    }


    protected static boolean getBoolean(Dictionary entries, String key,
                                        boolean def) {

        String val = (String) entries.get(key);
        return val != null ? Boolean.valueOf(val).booleanValue() : def;
    }


    protected static void putBoolean(Dictionary entries, String key,
                                     boolean val) {

        entries.put(key, (val ? Boolean.TRUE : Boolean.FALSE).toString());
    }


    protected static int getByte(Dictionary entries, String key, byte def) {
        String val = (String) entries.get(key);
        return val != null ? Byte.parseByte(val) : def;
    }


    protected static void putByte(Dictionary entries, String key, byte val) {
        entries.put(key, Byte.toString(val));
    }


    protected static int getShort(Dictionary entries, String key, short def) {
        String val = (String) entries.get(key);
        return val != null ? Short.parseShort(val) : def;
    }


    protected static void putShort(Dictionary entries, String key, short val) {
        entries.put(key, Short.toString(val));
    }


    protected static int getInt(Dictionary entries, String key, int def) {
        String val = (String) entries.get(key);
        return val != null ? Integer.parseInt(val) : def;
    }


    protected static void putInt(Dictionary entries, String key, int val) {
        entries.put(key, Integer.toString(val));
    }


    protected static long getLong(Dictionary entries, String key, long def) {
        String val = (String) entries.get(key);
        return val != null ? Long.parseLong(val) : def;
    }


    protected static void putLong(Dictionary entries, String key, long val) {
        entries.put(key, Long.toString(val));
    }


    protected static float getFloat(Dictionary entries, String key, float def) {
        String val = (String) entries.get(key);
        return val != null ? Float.parseFloat(val) : def;
    }


    protected static void putFloat(Dictionary entries, String key, float val) {
        entries.put(key, Float.toString(val));
    }


    protected static double getDouble(Dictionary entries, String key,
                                      double def) {

        String val = (String) entries.get(key);
        return val != null ? Double.parseDouble(val) : def;
    }


    protected static void putDouble(Dictionary entries, String key,
                                    double val) {

        entries.put(key, Double.toString(val));
    }



    public KeyValueSupport(Dictionary entries) {
        super();

        this.entries = entries;
    }


    public String getString(String key, String def) {
        return getString(entries, key(key), def);
    }


    public void putString(String key, String val) {
        putString(entries, key(key), val);
    }


    public boolean getBoolean(String key, boolean def) {
        return getBoolean(key, def);
    }


    public void putBoolean(String key, boolean val) {
        putBoolean(key, val);
    }


    public int getByte(String key, byte def) {
        return getByte(entries, key(key), def);
    }


    public void putByte(String key, byte val) {
        putByte(entries, key(key), val);
    }


    public int getShort(String key, short def) {
        return getShort(entries, key(key), def);
    }


    public void putShort(String key, short val) {
        putShort(entries, key(key), val);
    }


    public int getInt(String key, int def) {
        return getInt(entries, key(key), def);
    }


    public void putInt(String key, int val) {
        putInt(entries, key(key), val);
    }


    public long getLong(String key, long def) {
        return getLong(entries, key(key), def);
    }


    public void putLong(String key, long val) {
        putLong(entries, key(key), val);
    }


    public float getFloat(String key, float def) {
        return getFloat(entries, key(key), def);
    }


    public void putFloat(String key, float val) {
        putFloat(entries, key(key), val);
    }


    public double getDouble(String key, double def) {
        return getDouble(entries, key(key), def);
    }


    public void putDouble(String key, double val) {
        putDouble(entries, key(key), val);
    }


    protected String key(String key) {
        return key;
    }


    public void copy(Dictionary targetEntries) {
        synchronized (entries) {
            for (Enumeration k = entries.keys(); k.hasMoreElements();) {
                Object key = k.nextElement();
                targetEntries.put(key, entries.get(key));
            }
        }
    }


    public void clear() {
        synchronized (entries) {
            Vector keys = new Vector();
            for (Enumeration k = entries.keys(); k.hasMoreElements();) {
                keys.addElement(k.nextElement());
            }
            for (int i = 0; i < keys.size(); i++) {
                entries.remove(keys.elementAt(i));
            }
        }
    }


    /*
    public void write(ObjectOutput out) throws IOException {
        synchronized (entries) {
            out.writeInt(entries.size());
            for (Enumeration k = entries.keys(); k.hasMoreElements();) {
                Object key = k.nextElement();
                out.writeObject(key);
                out.writeObject(entries.get(key));
            }
        }
    }
    */


    /*
    public void read(ObjectInput in)
        throws IOException, ClassNotFoundException {

        synchronized (entries) {
            clear();
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                entries.put(in.readObject(), in.readObject());
            }
        }
    }
    */


    private final Dictionary entries;
}