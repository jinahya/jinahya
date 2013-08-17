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


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <T> MepEntries type parameter
 * @param <K> Map key type parameter
 * @param <V> Map value type parameter
 */
public abstract class MapEntriesAdapter<T extends MapEntries<?, K, V>, K, V>
    extends XmlAdapter<T, Map<K, V>> {


    /**
     * Creates a new instance.
     *
     * @param entriesType wrapper type.
     */
    public MapEntriesAdapter(final Class<T> entriesType) {

        super();

        if (entriesType == null) {
            throw new NullPointerException("entriesType");
        }

        this.entriesType = entriesType;
    }


    @Override
    public Map<K, V> unmarshal(final T value) throws Exception {

        if (value == null) {
            return null;
        }

        final List<? extends MapEntry<K, V>> entries = value.getEntries();

        final Map<K, V> bound = new HashMap<K, V>(entries.size());

        for (MapEntry<K, V> entry : entries) {
            bound.put(entry.getKey(), entry.getValue());
        }

        return bound;
    }


//    /**
//     * Creates a new map.
//     *
//     * @param requiredCapacity required capacity hint
//     *
//     * @return a new map.
//     */
//    protected Map<K, V> newMap(final int requiredCapacity) {
//
//        return new HashMap<K, V>(requiredCapacity);
//    }
    @Override
    public T marshal(final Map<K, V> bound) throws Exception {

        if (bound == null) {
            return null;
        }

        final T value = entriesType.newInstance();

        value.addEntries(bound);

//        //final List<? extends MapEntry<K, V>> entries = value.getEntries();
//        @SuppressWarnings("unchecked")
//        final List<? extends MapEntry<K, V>> entries =
//            (List<MapEntry<K, V>>) value.getEntries();
//        ((ArrayList) entries).ensureCapacity(bound.size());
//
//        for (Entry<K, V> bentry : bound.entrySet()) {
//            final MapEntry<K, V> ventry = value.getEntryType().newInstance();
//            ventry.setKey(bentry.getKey());
//            ventry.setValue(bentry.getValue());
//            entries.add(ventry);
//        }

        return value;
    }


    /**
     * entries type.
     */
    private final Class<T> entriesType;


}

