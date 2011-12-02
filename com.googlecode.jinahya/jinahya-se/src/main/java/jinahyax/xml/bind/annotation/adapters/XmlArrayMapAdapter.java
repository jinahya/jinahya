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


package jinahyax.xml.bind.annotation.adapters;


import java.lang.reflect.Array;

import java.util.Map;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <M> map type parameter
 * @param <K> map key type parameter
 * @param <V> map value type parameter
 */
public abstract class XmlArrayMapAdapter<M extends Map<K, V>, K, V>
    extends XmlMapAdapter<V[], M, K, V> {


    public XmlArrayMapAdapter(final Class<V> mapValueType) {
        super();

        if (mapValueType == null) {
            throw new NullPointerException("null mapValueType");
        }

        this.mapValueType = mapValueType;
    }


    @Override
    @SuppressWarnings("unchecked")
    public V[] marshal(final M bound) throws Exception {

        return bound.values().toArray(
            (V[]) Array.newInstance(mapValueType, bound.size()));
    }


    @Override
    public M unmarshal(final V[] value) throws Exception {

        final M map = newMap();
        for (V v : value) {
            map.put(getMapKey(v), v);
        }

        return map;
    }


    /** map value type. */
    private final Class<V> mapValueType;


}

