/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.nica.test;


import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.Keg;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
//@XmlType(propOrder = {"index", "bytes"})
public class IndexedKey {


    public static IndexedKey[] newInstances(final int size) {

        if (size < 0) {
            throw new IllegalArgumentException("negative size");
        }

        final IndexedKey[] instances = new IndexedKey[size];

        for (int i = 0; i < instances.length; i++) {
            instances[i] = newInstance(i);
        }

        return instances;
    }


    public static IndexedKey newInstance(final int index) {

        return newInstance(index, Keg.newKey());
    }


    protected static IndexedKey newInstance(final int index,
                                            final byte[] bytes) {

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        if (index >= 10000) {
            throw new IllegalArgumentException("index(" + index + ") >= 10000");
        }

        if (bytes == null) {
            throw new IllegalArgumentException("null key");
        }

        if (bytes.length != Aes.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "bytes.length(" + bytes.length + ") != "
                + Aes.KEY_SIZE_IN_BYTES);
        }

        final IndexedKey instance = new IndexedKey();

        instance.index = index;
        instance.bytes = bytes;

        return instance;
    }


    @XmlAttribute(required = true)
    //@XmlElement
    @XmlID
    @XmlJavaTypeAdapter(StringIndexAdapter.class)
    private Integer index;


    @XmlValue
    //@XmlElement
    //@XmlSchemaType(name = "hexBinary")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    private byte[] bytes;


}

