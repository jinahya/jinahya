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


package com.googlecode.jinahya.nica.util;


import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MACJCE extends MAC {


    /**
     * Encryption algorithm.
     */
    private static final String ALGORITHM = "HmacSHA512";


    /**
     * Creates a new instance.
     *
     * @param key encryption key.
     */
    public MACJCE(final byte[] key) {
        super();

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (key.length != AES.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != " + AES.KEY_SIZE_IN_BYTES);
        }

        this.key = new SecretKeySpec(key, ALGORITHM);
    }


    @Override
    public byte[] authenticate(byte[] message) {

        if (message == null) {
            throw new IllegalArgumentException("null unauthenticated");
        }

        try {
            final Mac mac = Mac.getInstance(ALGORITHM);
            try {
                mac.init(key);
                return mac.doFinal(message);
            } catch (InvalidKeyException ike) {
                throw new RuntimeException(ike);
            }
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(
                "\"" + ALGORITHM + "\" is not supported?", nsae);
        }
    }


    /**
     * key.
     */
    private final Key key;


}

