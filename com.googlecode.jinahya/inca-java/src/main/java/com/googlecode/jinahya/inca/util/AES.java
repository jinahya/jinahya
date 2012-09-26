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


package com.googlecode.jinahya.inca.util;


import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AES {


    public static final int KEY_SIZE = 128;


    public static final int KEY_SIZE_IN_BYTES = 128 / 8;


    public static final String NAME = "AES";


    public static final String MODE = "CBC";


    public static final String PADDING = "PKCS5Padding";


    public static final String TRANSFORMATION =
        NAME + "/" + MODE + "/" + PADDING;


    public static byte[] encrypt(final Key key, final IvParameterSpec params,
                                 final byte[] decrypted) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (params == null) {
            throw new IllegalArgumentException("null params");
        }

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


    public static byte[] encrypt(final byte[] key, final byte[] iv,
                                 final byte[] decrypted) {

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

        if (decrypted == null) {
            throw new IllegalArgumentException("null decrypted");
        }

        return encrypt(new SecretKeySpec(key, NAME), new IvParameterSpec(iv),
                       decrypted);
    }


    public static byte[] encrypt(final byte[] key, final byte[] iv,
                                 final String decrypted) {

        if (decrypted == null) {
            throw new IllegalArgumentException("null decrypted");
        }

        return encrypt(key, iv, HEX.decode(decrypted));
    }


    public static String encryptToString(final byte[] key, final byte[] iv,
                                         final byte[] decrypted) {

        return HEX.encodeToString(encrypt(key, iv, decrypted));
    }


    public static String encryptToString(final byte[] key, final byte[] iv,
                                         final String decrypted) {

        if (decrypted == null) {
            throw new IllegalArgumentException("null decrypted");
        }

        return HEX.encodeToString(encrypt(key, iv, HEX.decode(decrypted)));
    }


    /**
     * Decrypts given
     * <code>encrypted</code>.
     *
     * @param key encryption key
     * @param params parameters
     * @param encrypted encrypted bytes to decrypt
     *
     * @return decrypted bytes
     */
    public static byte[] decrypt(final Key key, final IvParameterSpec params,
                                 final byte[] encrypted) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (params == null) {
            throw new IllegalArgumentException("null params");
        }

        if (encrypted == null) {
            throw new IllegalArgumentException("null decrypted");
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
     * Decrypts given
     * <code>encrypted</code>.
     *
     * @param key encryption key
     * @param iv initialization vector
     * @param encrypted encrypted octets to decrypt
     *
     * @return decrypted bytes
     */
    public static byte[] decrypt(final byte[] key, final byte[] iv,
                                 final byte[] encrypted) {

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

        if (encrypted == null) {
            throw new IllegalArgumentException("null decrypted");
        }

        return decrypt(new SecretKeySpec(key, NAME), new IvParameterSpec(iv),
                       encrypted);
    }


    /**
     * Decrypts given
     * <code>encrypted</code>.
     *
     * @param key encryption key
     * @param iv initialization vector
     * @param encrypted encrypted hex string
     *
     * @return decrypted bytes
     */
    public static byte[] decrypt(final byte[] key, final byte[] iv,
                                 final String encrypted) {

        return decrypt(key, iv, HEX.decode(encrypted));
    }


    /**
     * Decrypts given
     * <code>encrypted</code> and returns output as a hex string.
     *
     * @param key encryption key
     * @param iv initialization vector
     * @param encrypted encrypted hex string
     *
     * @return decrypted output as a hex string
     */
    public static String decryptToString(final byte[] key, final byte[] iv,
                                         final byte[] encrypted) {

        return HEX.encodeToString(decrypt(key, iv, encrypted));
    }


    /**
     * Decrypts given
     * <code>encrypted</code> and returns output as a hex string.
     *
     * @param key encryption key
     * @param iv initialization vector
     * @param encrypted encrypted hex string
     *
     * @return decrypted output as a hex string
     */
    public static String decryptToString(final byte[] key, final byte[] iv,
                                         final String encrypted) {

        return HEX.encodeToString(decrypt(key, iv, encrypted));
    }


    /**
     * Creates a new instance.
     */
    protected AES() {
        super();
    }


}

