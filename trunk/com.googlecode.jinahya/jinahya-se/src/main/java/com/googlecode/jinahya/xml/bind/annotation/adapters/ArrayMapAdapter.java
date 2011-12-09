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


package com.googlecode.jinahya.xml.bind.annotation.adapters;


import java.lang.reflect.Array;

import java.util.HashMap;
import java.util.Map;


/**
 * An XmlAdapter for Array ValueType and Map BoundType.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <K> map key type parameter
 * @param <V> map value type parameter
 */
public abstract class ArrayMapAdapter<K, V>
    extends MapBoundTypeAdapter<V[], K, V> {


    /**
     * Creates a new instance.
     *
     * @param valueElementType the type of element type
     */
    public ArrayMapAdapter(final Class<V> valueElementType) {
        super();

        if (valueElementType == null) {
            throw new NullPointerException("null valueElementType");
        }

        this.valueElementType = valueElementType;
    }


    @Override
    public V[] marshal(final Map<K, V> boundType) throws Exception {

        @SuppressWarnings("unchecked")
        final V[] valueType =
            (V[]) Array.newInstance(valueElementType, boundType.size());

        boundType.values().toArray(valueType);

        return valueType;
    }


    @Override
    public Map<K, V> unmarshal(final V[] valueType) throws Exception {

        final Map<K, V> boundType = newBoundType(valueType.length);

        for (V value : valueType) {
            boundType.put(getKey(value), value);
        }

        return boundType;
    }


    @Override
    protected Map<K, V> newBoundType(final int valueTypeSize) {
        return new HashMap<K, V>(valueTypeSize);
    }


    /**
     * The type of array element.
     */
    protected final Class<V> valueElementType;


}

