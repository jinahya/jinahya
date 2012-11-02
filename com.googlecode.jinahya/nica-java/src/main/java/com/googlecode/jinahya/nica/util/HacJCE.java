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
     * Encryption algorithm.
     */
    private static final String ALGORITHM = "HmacSHA512";


    /**
     * Returns a synchronized (thread-safe) hac. In order to guarantee serial
     * access, it is critical that all access to the backing hac is accomplished
     * through the returned hac.
     *
     * @param key the encryption key
     * @return a synchronized instance.
     */
    public static Hac newSynchronizedInstance(final byte[] key) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (key.length != Aes.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != " + Aes.KEY_SIZE_IN_BYTES);
        }

        final Mac mac;
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

        return new Hac() {


            @Override
            public byte[] authenticate(byte[] message) {

                if (message == null) {
                    throw new IllegalArgumentException("null message");
                }

                synchronized (mac) {

                    mac.reset();
                    mac.update(message);

                    return mac.doFinal();
                }
            }


        };
    }


    /**
     * Creates a new instance.
     *
     * @param key encryption key.
     */
    public HacJCE(final byte[] key) {
        super();

        if (key == null) {
            throw new IllegalArgumentException("null key");
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


    @Override
    public byte[] authenticate(byte[] message) {

        if (message == null) {
            throw new IllegalArgumentException("null message");
        }

        mac.reset();
        mac.update(message);

        return mac.doFinal();
    }


    /**
     * mac.
     */
    private final Mac mac;


}

