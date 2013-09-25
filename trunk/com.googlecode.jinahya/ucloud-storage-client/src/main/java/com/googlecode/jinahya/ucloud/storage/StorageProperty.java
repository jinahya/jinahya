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


package com.googlecode.jinahya.ucloud.storage;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class StorageProperty implements Entry<String, String> {


    static Map<String, String> copy(
        final Collection<? extends StorageProperty> source,
        final Map<String, String> target) {

        try {
            target.entrySet().addAll(source);
        } catch (final UnsupportedOperationException uoe) {
            for (final StorageProperty storageProperty : source) {
                target.put(storageProperty.getKey(),
                           storageProperty.getValue());
            }
        }

        return target;
    }


    static Map<String, String> map(
        final Collection<? extends StorageProperty> source) {

        return copy(source, new HashMap<String, String>(source.size()));
    }


    static Collection<? super StorageProperty> copy(
        final Map<String, String> source,
        final Collection<? super StorageProperty> target) {

        for (final Entry<String, String> entry : source.entrySet()) {
            final StorageProperty storageProperty = new StorageProperty();
            storageProperty.setKey(entry.getKey());
            storageProperty.setValue(entry.getValue());
            target.add(storageProperty);
        }

        return target;
    }


    static Collection<? super StorageProperty> collect(
        final Map<String, String> source) {

        return copy(source, new ArrayList<StorageProperty>(source.size()));
    }


    @Override
    public String getKey() {

        return key;
    }


    public void setKey(final String key) {

        this.key = key;
    }


    @Override
    public String getValue() {

        return value;
    }


    @Override
    public String setValue(final String value) {

        final String oldValue = this.value;
        this.value = value;

        return oldValue;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.key != null ? this.key.hashCode() : 0);
        hash = 83 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StorageProperty other = (StorageProperty) obj;
        if ((this.key == null)
            ? (other.key != null) : !this.key.equals(other.key)) {
            return false;
        }
        if ((this.value == null)
            ? (other.value != null) : !this.value.equals(other.value)) {
            return false;
        }
        return true;
    }


    @XmlElement(required = true)
    private String key;


    @XmlElement(required = true)
    private String value;


}
