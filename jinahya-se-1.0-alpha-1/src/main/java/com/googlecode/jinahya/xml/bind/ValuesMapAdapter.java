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
    public static interface Values<V> {


        /**
         * Returns a list of value.
         *
         * @return a list of value.
         */
        @XmlTransient
        List<V> getValueList();


    }


    @XmlTransient
    public static abstract class AbstractValues<V> implements Values<V> {


        @Override
        @XmlTransient
        public List<V> getValueList() {

            if (valueList == null) {
                valueList = new ArrayList<V>();
            }

            return valueList;
        }


        /**
         * values.
         */
        @XmlTransient
        private List<V> valueList;


    }


    /**
     * Creates a new instance.
     *
     * @param valueType value type.
     */
    public ValuesMapAdapter(final Class<T> valueType) {

        super();

        if (valueType == null) {
            throw new NullPointerException("valueType");
        }

        this.valueType = valueType;
    }


    @Override
    public Map<K, V> unmarshal(final T v) throws Exception {

        if (v == null) {
            return null;
        }

        final Map<K, V> b = new HashMap<K, V>(v.getValueList().size());

        for (V value : v.getValueList()) {
            b.put(getKey(value), value);
        }

        return b;
    }


    @Override
    public T marshal(final Map<K, V> b) throws Exception {

        if (b == null) {
            return null;
        }

        final T v = valueType.newInstance();

        for (V value : b.values()) {
            v.getValueList().add(value);
        }

        return v;
    }


    /**
     * Returns the map to put keys and values.
     *
     * @param initialCapacity size hint
     *
     * @return a new BoundType instance
     */
    protected Map<K, V> newBoundType(final int initialCapacity) {

        return new HashMap<K, V>(initialCapacity);
    }


    /**
     * Returns the map key for specified {@code value}.
     *
     * @param value map value
     *
     * @return map key for specified {@code value}.
     */
    protected abstract K getKey(V value);


    /**
     * value type.
     */
    private final Class<T> valueType;


}

