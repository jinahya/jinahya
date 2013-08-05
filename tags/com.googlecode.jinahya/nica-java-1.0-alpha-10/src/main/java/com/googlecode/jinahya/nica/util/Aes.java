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


import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;


/**
 * An abstract class for AES encryption/decryption.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Aes {


    /**
     * Key size in bits.
     */
    public static final int KEY_SIZE = 0x80; // 128


    /**
     * Key size in bytes.
     */
    public static final int KEY_SIZE_IN_BYTES = KEY_SIZE >> 3; // 16


    /**
     * Block size in bits.
     */
    public static final int BLOCK_SIZE = 0x80; // 128;


    /**
     * Block size in bytes.
     */
    public static final int BLOCK_SIZE_IN_BYTES = BLOCK_SIZE >> 3; // 16


    /**
     * Generates a new initialization vector.
     *
     * @param random a random to use.
     *
     * @return a new initialization vector.
     */
    protected static final byte[] newIv(final Random random) {

        if (random == null) {
            throw new NullPointerException("random");
        }

        final byte[] iv = new byte[BLOCK_SIZE_IN_BYTES];

        random.nextBytes(iv);

        return iv;
    }


    /**
     * Generates a new initialization vector.
     *
     * @return a new initialization vector.
     */
    public static final byte[] newIv() {

        return newIv(new SecureRandom());
    }


    /**
     * Encrypts given {@code decrypted}. Any implementation/provider specific
     * exception will be wrapped in a {@code RuntimeException}.
     *
     * @param iv initialization vector
     * @param decrypted the bytes to encrypt
     *
     * @return encrypted output
     */
    public abstract byte[] encrypt(byte[] iv, byte[] decrypted);


    /**
     * Encrypts given {@code decrypted} which is treated as a {@code UTF-8}
     * encoded string.
     *
     * @param iv initialization vector
     * @param decrypted input string to encrypt
     *
     * @return encrypted output
     */
    public byte[] encrypt(final byte[] iv, final String decrypted) {

        if (iv == null) {
            throw new NullPointerException("iv");
        }

        if (iv.length != BLOCK_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "iv.length(" + iv.length + ") != " + BLOCK_SIZE_IN_BYTES);
        }

        if (decrypted == null) {
            throw new NullPointerException("decrypted");
        }

        try {
            return encrypt(iv, decrypted.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?", uee);
        }
    }


    /**
     * Encrypts given {@code decrypted} and returns output as a {@code hex}
     * encoded string.
     *
     * @param iv initialization vector
     * @param decrypted input bytes to encrypt
     *
     * @return encrypted output as a {@code hex} encoded string.
     */
    public String encryptToString(final byte[] iv, final byte[] decrypted) {

        if (iv == null) {
            throw new NullPointerException("iv");
        }

        if (iv.length != BLOCK_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "iv.length(" + iv.length + ") != " + BLOCK_SIZE_IN_BYTES);
        }

        if (decrypted == null) {
            throw new NullPointerException("decrypted");
        }

        return Hex.encodeToString(encrypt(iv, decrypted));
    }


    /**
     * Encrypts given {@code decrypted} which is treated as a {@code UTF-8}
     * encoded string and returns output as a {@code hex} encoded string.
     *
     * @param iv initialization vector
     * @param decrypted input string to encrypt.
     *
     * @return encrypted output as a {@code hex} encoded string.
     */
    public String encryptToString(final byte[] iv, final String decrypted) {

        if (iv == null) {
            throw new NullPointerException("iv");
        }

        if (iv.length != BLOCK_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "iv.length(" + iv.length + ") != " + BLOCK_SIZE_IN_BYTES);
        }

        if (decrypted == null) {
            throw new NullPointerException("decrypted");
        }

        try {
            return encryptToString(iv, decrypted.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?", uee);
        }
    }


    /**
     * Decrypts given {@code encrypted}. Any implementation/provider specific
     * exception will be wrapped in a {@code RuntimeException}.
     *
     * @param iv initialization vector
     * @param encrypted the bytes to decrypt
     *
     * @return decrypted output
     */
    public abstract byte[] decrypt(byte[] iv, byte[] encrypted);


    /**
     * Decrypts given {@code encrypted} which is treated as a {@code hex}
     * string.
     *
     * @param iv initialization vector
     * @param encrypted {@code hex} encoded string
     *
     * @return decrypted output
     */
    public byte[] decrypt(final byte[] iv, final String encrypted) {

        if (iv == null) {
            throw new NullPointerException("iv");
        }

        if (iv.length != BLOCK_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "iv.length(" + iv.length + ") != " + BLOCK_SIZE_IN_BYTES);
        }

        if (encrypted == null) {
            throw new NullPointerException("encrypted");
        }

        return decrypt(iv, Hex.decode(encrypted));
    }


    /**
     * Decrypts given {@code encrypted} and returns the result as a
     * {@code UTF-8} encoded string.
     *
     * @param iv initialization vector
     * @param encrypted input to decrypt
     *
     * @return decrypted result as a {@code UTF-8} encoded string.
     */
    public String decryptToString(final byte[] iv, final byte[] encrypted) {

        if (iv == null) {
            throw new NullPointerException("iv");
        }

        if (iv.length != BLOCK_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "iv.length(" + iv.length + ") != " + BLOCK_SIZE_IN_BYTES);
        }

        if (encrypted == null) {
            throw new NullPointerException("encrypted");
        }

        try {
            return new String(decrypt(iv, encrypted), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?", uee);
        }
    }


    /**
     * Decrypts given {@code encrypted} which is treated as a {@code hex}
     * encoded string and returns output as a {@code UTF-8} encoded string.
     *
     * @param iv initialization vector
     * @param encrypted {@code hex} encoded string
     *
     * @return output as a {@code UTF-8} encoded string.
     */
    public String decryptToString(final byte[] iv, final String encrypted) {

        if (iv == null) {
            throw new NullPointerException("iv");
        }

        if (iv.length != BLOCK_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "iv.length(" + iv.length + ") != " + BLOCK_SIZE_IN_BYTES);
        }

        if (encrypted == null) {
            throw new NullPointerException("encrypted");
        }

        return decryptToString(iv, Hex.decode(encrypted));
    }


}
