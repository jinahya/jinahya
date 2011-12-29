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


package com.googlecode.jinahya.sql.metadata.bind;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Entry {


    /**
     * Creates a new instance.
     *
     * @param key key
     * @param value value
     * @return a new instance.
     */
    public static Entry newIntance(final String key, final String value) {

        final Entry instance = new Entry();
        instance.setKey(key);
        instance.setValue(value);

        return instance;
    }


    /**
     * Returns label.
     *
     * @return label
     */
    public final String getKey() {
        return key;
    }


    /**
     * Sets label.
     *
     * @param key key.
     */
    public final void setKey(final String key) {

        if (key == null) {
            throw new NullPointerException("null key");
        }

        if (key.trim().length() == 0) {
            throw new IllegalArgumentException("emtpy key");
        }

        this.key = key;
    }


    /**
     * Returns value.
     *
     * @return value.
     */
    public final String getValue() {
        return value;
    }


    /**
     * Sets value.
     *
     * @param value value
     */
    public final void setValue(final String value) {
        this.value = value;
    }


    //@XmlAttribute(required = true)
    @XmlElement(required = true)
    private String key;


    //@XmlValue
    @XmlElement(required = true, nillable = true)
    private String value;


}

