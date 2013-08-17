/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.xml.bind;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <E> entry type parameter
 * @param <K> map key type parameter
 * @param <V> map value type parameter
 */
@XmlTransient
public abstract class MapEntries<E extends MapEntry<K, V>, K, V> {


    public MapEntries(final Class<E> entryType) {

        super();

        if (entryType == null) {
            throw new NullPointerException("entryType");
        }

        this.entryType = entryType;
    }


    public Class<E> getEntryType() {

        return entryType;
    }


    protected List<E> getEntries() {

        if (entries == null) {
            entries = new ArrayList<E>();
        }

        return entries;
    }


    /**
     * Adds a new entry.
     *
     * @param key entry key.
     * @param value entry value.
     */
    public void addEntry(final K key, final V value) {

        final E entry;
//        try {
//            final Constructor<E> constructor =
//                entryType.getDeclaredConstructor();
//            if (!constructor.isAccessible()) {
//                constructor.setAccessible(true);
//            }
//            try {
//                entry = constructor.newInstance();
//            } catch (InstantiationException ie) {
//                throw new RuntimeException(ie);
//            } catch (IllegalAccessException iae) {
//                throw new RuntimeException(iae);
//            } catch (InvocationTargetException ite) {
//                throw new RuntimeException(ite);
//            }
//        } catch (NoSuchMethodException nsme) {
//            throw new RuntimeException(nsme);
//        }
        try {
            entry = entryType.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }

        entry.setKey(key);
        entry.setValue(value);

        getEntries().add(entry);
    }


    /**
     * Adds a new entry.
     *
     * @param entry the entry to add
     */
    public void addEntry(final Entry<K, V> entry) {

        if (entry == null) {
            throw new NullPointerException("entry");
        }

        addEntry(entry.getKey(), entry.getValue());
    }


    /**
     * Adds entries from specified map.
     *
     * @param map the map
     */
    public void addEntries(final Map<K, V> map) {

        if (map == null) {
            throw new NullPointerException("map");
        }

        for (Entry<K, V> entry : map.entrySet()) {
            addEntry(entry);
        }
    }


    protected final Class<E> entryType;


    private List<E> entries;


}
