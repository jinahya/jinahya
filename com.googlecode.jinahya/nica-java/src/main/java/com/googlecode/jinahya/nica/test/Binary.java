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


import java.util.concurrent.ThreadLocalRandom;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"hex", "base64"})
public class Binary {


    public static Binary newInstance(final int size) {

        if (size < 0) {
            throw new IllegalArgumentException("size(" + size + ") < 0");
        }

        final byte[] data = new byte[size];
        ThreadLocalRandom.current().nextBytes(data);

        final Binary instance = new Binary();

        instance.data = data;

        return instance;
    }


    @XmlElement
    //@XmlSchemaType(name = "hexBinary") // seems not work
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    public byte[] getHex() {
        return data;
    }


    @XmlElement
    public byte[] getBase64() {
        return data;
    }


    private byte[] data;


}

