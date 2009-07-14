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


package jinahya.xlet.util.prefs;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class Preferences {


    private static final Hashtable ENTRIES = new Hashtable();


    public static synchronized Preferences nodeForPackage(Class clazz)
        throws Exception {

        String packageName = "unnamed";
        String className = clazz.getName();
        int lastDotIndex = className.lastIndexOf('.');
        if (lastDotIndex != -1) {
            packageName = className.substring(0, lastDotIndex);
            className = className.substring(lastDotIndex + 1);
        }

        synchronized (ENTRIES) {
            Preferences prefs = (Preferences) ENTRIES.get(packageName);
            if (prefs == null) {
                prefs = new Preferences(packageName);
                ENTRIES.put(packageName, prefs);
            }
            return prefs;
        }
    }


    private Preferences(String packageName) {
        super();

        this.packageName = packageName;
    }


    public synchronized void clear() throws Exception {
        synchronized (entries) {
            entries.clear();
        }
    }


    private synchronized Object getClassifiedValue(Class clazz, Object key,
                                                   Object def) {

        synchronized (entries) {
            Hashtable classified = (Hashtable) entries.get(clazz);
            if (classified == null) {
                classified = new Hashtable();
                entries.put(clazz, classified);
            }
            if (!classified.containsKey(key)) {
                classified.put(key, def);
            }
            return classified.get(key);
        }
    }


    private synchronized void putClassifiedValue(Class cls, Object key,
                                                 Object val) {

        if (cls.isInstance(val)) {
            throw new IllegalArgumentException
                (String.valueOf(val) + " is not an instance of " + cls);
        }
        synchronized (entries) {
            Hashtable tbl = (Hashtable) entries.get(cls);
            if (tbl == null) {
                tbl = new Hashtable();
                entries.put(cls, tbl);
            }
            tbl.put(key, val);
        }
    }


    public String get(String key, String def) {
        return (String) getClassifiedValue(String.class, key, def);
    }


    public void put(String key, String val) {
        putClassifiedValue(String.class, key, val);
    }


    public boolean getBoolean(String key, boolean def) {
        Object val = getClassifiedValue
            (Boolean.class, key, def ? Boolean.TRUE : Boolean.FALSE);
        return ((Boolean) val).booleanValue();
    }


    public void putBoolean(String key, boolean val) {
        putClassifiedValue
            (Boolean.class, key, val ? Boolean.TRUE : Boolean.FALSE);
    }


    public double getDouble(String key, double def) {
        Object val = getClassifiedValue(Double.class, key, new Double(def));
        return ((Double) val).doubleValue();
    }


    public void putDouble(String key, double val) {
        putClassifiedValue(Double.class, key, new Double(val));
    }


    public float getFloat(String key, float def) {
        Object val = getClassifiedValue(Float.class, key, new Float(def));
        return ((Float) val).floatValue();
    }


    public void putFloat(String key, float val) {
        putClassifiedValue(Float.class, key, new Float(val));
    }


    public int getInt(String key, int def) {
        Object val = getClassifiedValue(Integer.class, key, new Integer(def));
        return ((Integer) val).intValue();
    }


    public void putInt(String key, int val) {
        putClassifiedValue(Integer.class, key, new Integer(val));
    }


    public long getLong(String key, long def) {
        Object val = getClassifiedValue(Long.class, key, new Long(def));
        return ((Long) val).longValue();
    }


    public void putLong(String key, long val) {
        putClassifiedValue(Long.class, key, new Long(val));
    }


    public String getPackageName() {
        return packageName;
    }


    public void importNode(InputStream in) throws Exception {
        synchronized (entries) {
            entries.clear();
            ObjectInputStream ois = new ObjectInputStream(in);
            try {
                int classCount = ois.readInt();
                for (int i = 0; i < classCount; i++) {
                    Class clazz = Class.forName(ois.readUTF());
                    Hashtable classified = new Hashtable();
                    entries.put(clazz, classified);
                    int entryCount = ois.readInt();
                    for (int j = 0; j < entryCount; j++) {
                        classified.put(ois.readUTF(), ois.readObject());
                    }
                }
            } finally {
                ois.close();
            }
        }
    }


    public void exportNode(OutputStream out) throws Exception {
        synchronized (entries) {
            ObjectOutputStream oos = new ObjectOutputStream(out);
            try {
                oos.writeInt(entries.size());
                for (Enumeration e = entries.keys(); e.hasMoreElements(); ) {
                    Class clazz = (Class) e.nextElement();
                    oos.writeUTF(clazz.getName());
                    Hashtable classified = (Hashtable) entries.get(clazz);
                    oos.writeInt(classified.size());
                    for (Enumeration f = classified.keys();
                         f.hasMoreElements(); ) {

                        Object key = f.nextElement();
                        oos.writeUTF((String) key);
                        oos.writeObject(classified.get(key));
                    }
                }
            } finally {
                oos.close();
            }
        }
    }


    private String packageName;

    private final Hashtable entries = new Hashtable();
}
