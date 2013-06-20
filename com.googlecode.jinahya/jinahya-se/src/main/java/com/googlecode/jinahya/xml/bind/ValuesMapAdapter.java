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


import com.googlecode.jinahya.xml.bind.ValuesMapAdapter.Values;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * An XmlAdapter for Map BoundTypes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> ValueType type parameter
 * @param <K> Map key type parameter
 * @param <V> Map value type parameter
 */
public abstract class ValuesMapAdapter<T extends Values<V>, K, V>
    extends XmlAdapter<T, Map<K, V>> {


    @XmlTransient
    public static abstract class Values<V> {


        /**
         * Returns value list.
         *
         * @return value list
         */
        protected List<V> getValue() {

            if (value == null) {
                value = new ArrayList<V>();
            }

            return value;
        }


        /**
         * values.
         */
        private List<V> value;


    }


    public ValuesMapAdapter(final Class<T> valueTypeClass) {
        super();

        if (valueTypeClass == null) {
            throw new NullPointerException("null valueTypeClass");
        }

        this.valueTypeClass = valueTypeClass;
    }


    @Override
    public Map<K, V> unmarshal(final T marshalled) throws Exception {

        if (marshalled == null) {
            return null;
        }

        final Map<K, V> boundType =
            new HashMap<K, V>(marshalled.getValue().size());

        for (V value : marshalled.getValue()) {
            boundType.put(getKey(value), value);
        }

        return boundType;
    }


    @Override
    public T marshal(final Map<K, V> unmarshalled) throws Exception {

        if (unmarshalled == null) {
            return null;
        }

        final T valueType = valueTypeClass.newInstance();

        for (V value : unmarshalled.values()) {
            valueType.getValue().add(value);
        }

        return valueType;

    }


    /**
     * Returns the map to put keys and values.
     *
     * @param valueTypeSize size hint
     *
     * @return a new BoundType instance
     */
    protected Map<K, V> newBoundType(final int valueTypeSize) {

        return new HashMap<K, V>(valueTypeSize);
    }


    /**
     * Returns the map key for specified {@code value}.
     *
     * @param value map value
     *
     * @return map key for specified {@code value}.
     */
    protected abstract K getKey(V value);


    private final Class<T> valueTypeClass;


}
