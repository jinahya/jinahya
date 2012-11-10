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
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
//@XmlType(propOrder = {"index", "bytes"})
public class Key {


    private static final KeyGenerator GENERATOR;


    static {
        try {
            GENERATOR = KeyGenerator.getInstance(Aes.ALGORITHM);
            GENERATOR.init(Aes.KEY_SIZE);
        } catch (NoSuchAlgorithmException nsae) {
            throw new InstantiationError(nsae.getMessage());
        }
    }


    public static byte[] newKey() {
        synchronized (GENERATOR) {
            return GENERATOR.generateKey().getEncoded();
        }
    }


    public static Key[] newInstances(final int size) {

        if (size < 0) {
            throw new IllegalArgumentException("negative size");
        }

        final Key[] instances = new Key[size];

        for (int i = 0; i < instances.length; i++) {
            instances[i] = newInstance(i);
        }

        return instances;
    }


    public static Key newInstance(final int index) {

        return newInstance(index, newKey());
    }


    protected static Key newInstance(final int index,
                                     final byte[] bytes) {

        if (bytes == null) {
            throw new IllegalArgumentException("null key");
        }

        if (bytes.length != Aes.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "bytes.length(" + bytes.length + ") != "
                + Aes.KEY_SIZE_IN_BYTES);
        }

        final Key instance = new Key();

        instance.index = index;
        instance.bytes = bytes;

        return instance;
    }


    /**
     * index.
     */
    @XmlAttribute(required = true)
    private Integer index;


    /**
     * bytes.
     */
    @XmlValue
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    private byte[] bytes;


}

