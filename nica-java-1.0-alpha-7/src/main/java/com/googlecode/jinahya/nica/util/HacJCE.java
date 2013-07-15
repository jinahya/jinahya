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
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HacJCE extends Hac {


    /**
     * algorithm.
     */
    private static final String ALGORITHM = "HmacSHA1";


    /**
     * Creates a new instance.
     *
     * @param key encryption key.
     */
    public HacJCE(final byte[] key) {

        super();

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (key.length != Aes.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != " + Aes.KEY_SIZE_IN_BYTES);
        }

        try {
            mac = Mac.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(
                "\"" + ALGORITHM + "\" is not supported?", nsae);
        }

        try {
            mac.init(new SecretKeySpec(key, ALGORITHM));
        } catch (InvalidKeyException ike) {
            throw new RuntimeException(ike);
        }
    }


    //@Override // commented for pre5
    public byte[] authenticate(final byte[] message) {

        if (message == null) {
            throw new NullPointerException("message");
        }

        return mac.doFinal(message);
    }


    /**
     * mac.
     */
    private final Mac mac;


}
