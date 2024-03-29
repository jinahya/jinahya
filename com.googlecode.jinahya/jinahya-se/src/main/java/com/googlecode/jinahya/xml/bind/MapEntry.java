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


import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;


/**
 * An abstract class for JAXB aware type of {@link java.util.Map.Entry}.
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <K> key type parameter
 * @param <V> value type parameter
 */
@XmlTransient
public abstract class MapEntry<K, V> {


    /**
     * Returns key.
     *
     * @return key.
     */
    protected K getKey() {

        return key;
    }


    /**
     * Sets key.
     *
     * @param key key.
     */
    protected void setKey(final K key) {

        this.key = key;
    }


    /**
     * Returns value.
     *
     * @return value.
     */
    protected V getValue() {

        return value;
    }


    /**
     * Sets value.
     *
     * @param value value.
     */
    protected void setValue(final V value) {

        this.value = value;
    }


    /**
     * Puts this entry into specified map.
     *
     * @param map the map
     *
     * @return previous value
     */
    protected V put(final Map<K, V> map) {

        if (map == null) {
            throw new NullPointerException("map");
        }

        return map.put(key, value);
    }


    /**
     * key.
     */
    private K key;


    /**
     * value.
     */
    private V value;


}
