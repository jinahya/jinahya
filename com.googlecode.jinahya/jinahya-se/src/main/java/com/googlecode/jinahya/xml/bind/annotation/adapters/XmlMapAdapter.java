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


import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * An XmlAdapter for Map BoundTypes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> ValueType type parameter
 * @param <M> map type parameter
 * @param <K> map key type parameter
 * @param <V> map value type parameter
 */
public abstract class XmlMapAdapter<T, M extends Map<K, V>, K, V>
    extends XmlAdapter<T, M> {


    /**
     * Returns the map to put keys and values.
     *
     * @param valueTypeSize size hint
     * @return the boundType
     */
    protected abstract M getBoundType(int valueTypeSize);


    /**
     * Returns the key for specified <code>value</code>.
     *
     * @param value map value
     * @return map key for specified <code>value</code>
     */
    protected abstract K getKey(V value);


}

