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


import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AESJCE extends AES {


    public static final Key newKey(final byte[] key) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (key.length != AES.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != KEY_SIZE_IN_BYTES("
                + AES.KEY_SIZE_IN_BYTES + ")");
        }

        return new SecretKeySpec(key, ALGORITHM);
    }


    /**
     * Creates a new instance.
     *
     * @param key {@value AbstractAES#KEY_SIZE}-bit secret key.
     * @param iv {@value AbstractAES#KEY_SIZE_IN_BYTES}-bit initialization
     * vector
     */
    public AESJCE(final byte[] key, final byte[] iv) {
        super();

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (key.length != KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != KEY_SIZE_IN_BYTES("
                + KEY_SIZE_IN_BYTES + ")");
        }

        if (iv == null) {
            throw new IllegalArgumentException("null iv");
        }

        if (iv.length != KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "iv.length(" + iv.length + ") != KEY_SIZE_IN_BYTES("
                + KEY_SIZE_IN_BYTES + ")");
        }

        this.key = new SecretKeySpec(key, ALGORITHM);
        this.params = new IvParameterSpec(iv);
    }


    @Override
    public byte[] encrypt(final byte[] decrypted) {

        if (decrypted == null) {
            throw new IllegalArgumentException("null decrypted");
        }

        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key, params);
            return cipher.doFinal(decrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public byte[] decrypt(final byte[] encrypted) {

        if (encrypted == null) {
            throw new IllegalArgumentException("null encrypted");
        }

        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key, params);
            return cipher.doFinal(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * key.
     */
    private final Key key;


    /**
     * params.
     */
    private final AlgorithmParameterSpec params;


}

