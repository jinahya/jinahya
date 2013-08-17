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


import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <K> key type parameter
 * @param <V> value type parameter
 */
@XmlTransient
public abstract class MapEntry<K, V> {


    public MapEntry(final Class<K> keyType, final Class<V> valueType) {

        super();

        this.keyType = keyType;
        this.valueType = valueType;
    }


    public final Class<K> getKeyType() {

        return keyType;
    }


    public final Class<V> getValueType() {

        return valueType;
    }


    protected K getKey() {

        return key;
    }


    protected void setKey(final K key) {

        this.key = key;
    }


    protected V getValue() {

        return value;
    }


    protected void setValue(final V value) {

        this.value = value;
    }


    protected final Class<K> keyType;


    protected final Class<V> valueType;


    private K key;


    private V value;


}
