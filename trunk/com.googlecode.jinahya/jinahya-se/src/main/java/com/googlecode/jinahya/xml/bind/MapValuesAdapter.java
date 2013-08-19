/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * An XmlAdapter for {@link MapValues} and {@link Map}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> MapValues type parameter
 * @param <K> Key type parameter
 * @param <V> Value type parameter
 */
public abstract class MapValuesAdapter<T extends MapValues<V>, K, V>
    extends XmlAdapter<T, Map<K, V>> {


    /**
     * Creates a new instance.
     *
     * @param mapValuesType MapValues type.
     */
    public MapValuesAdapter(final Class<T> mapValuesType) {

        super();

        if (mapValuesType == null) {
            throw new NullPointerException("mapValuesType");
        }

        this.mapValuesType = mapValuesType;
    }


    @Override
    public Map<K, V> unmarshal(final T v) throws Exception {

        if (v == null) {
            return null;
        }

        final Map<K, V> b = new HashMap<K, V>(v.getValue().size());

        for (V value : v.getValue()) {
            b.put(getKey(value), value);
        }

        return b;
    }


    /**
     * Returns the map key for specified map value.
     *
     * @param value the map value
     *
     * @return map key for specified {@code value}.
     */
    protected abstract K getKey(V value);


    @Override
    public T marshal(final Map<K, V> b) throws Exception {

        if (b == null) {
            return null;
        }

        final T v = mapValuesType.newInstance();

        final List<V> values = v.getValue();

        ((ArrayList<V>) values).ensureCapacity(b.size());

        values.addAll(b.values());

        return v;
    }


    /**
     * MapValues type.
     */
    private final Class<T> mapValuesType;


}

