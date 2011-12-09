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


import java.util.HashMap;
import java.util.Map;


/**
 * An XmlAdapter for ListValueType and Map.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <L> ListValueType type parameter
 * @param <K> map key type parameter
 * @param <V> map value type parameter
 */
public abstract class ListMapAdapter<L extends ListValueType<V>, K, V>
    extends MapBoundTypeAdapter<L, K, V> {


    /**
     * Creates a new instance.
     *
     * @param valueTypeClass the type of ValueType.
     */
    public ListMapAdapter(final Class<L> valueTypeClass) {

        super();

        if (valueTypeClass == null) {
            throw new NullPointerException("null valueTypeClass");
        }

        this.valueTypeClass = valueTypeClass;
    }


    @Override
    public L marshal(final Map<K, V> boundType) throws Exception {

        final L valueType = newValueType(boundType.size());
        valueType.getValues().addAll(boundType.values());

        return valueType;
    }


    @Override
    public Map<K, V> unmarshal(final L valueType) throws Exception {

        final Map<K, V> boundType = newBoundType(valueType.getValues().size());
        for (V value : valueType.getValues()) {
            boundType.put(getKey(value), value);
        }

        return boundType;
    }


    /**
     * Returns the instance to add values.
     *
     * @param boundTypeSize size hint
     * @return a new ValueType instance
     */
    protected L newValueType(final int boundTypeSize) {
        try {
            return valueTypeClass.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create a new instance of " + valueTypeClass, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create a new instance of " + valueTypeClass, iae);
        }
    }


    @Override
    protected Map<K, V> newBoundType(final int valueTypeSize) {
        return new HashMap<K, V>(valueTypeSize);
    }


    /**
     * The type of ListValueType.
     */
    protected final Class<L> valueTypeClass;


}

