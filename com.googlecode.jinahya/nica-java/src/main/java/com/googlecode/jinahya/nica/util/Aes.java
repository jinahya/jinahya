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
    public static final int KEY_SIZE = 0x100; // 256 0x80; // 128


    /**
     * Key size in bytes.
     */
    public static final int KEY_SIZE_IN_BYTES = KEY_SIZE / 0x08; // 32 16


    /**
     * AES block size in bits.
     */
    public static final int BLOCK_SIZE = 0x80; // 128;


    /**
     * AES block size in bytes.
     */
    public static final int BLOCK_SIZE_IN_BYTES = BLOCK_SIZE / 0x08; // 16


    /**
     * Cipher algorithm.
     */
    public static final String ALGORITHM = "AES";


    /**
     * Cipher mode.
     */
    public static final String MODE = "CBC";


    /**
     * Cipher padding.
     */
    public static final String PADDING = "PKCS5Padding";


    /**
     * Cipher transformation.
     */
    public static final String TRANSFORMATION =
        ALGORITHM + "/" + MODE + "/" + PADDING;


    private static final Random RANDOM = new Random();


    /**
     * Generates a random bytes.
     *
     * @param bytes bytes to fill
     * @return given <code>bytes</code>
     */
    private static byte[] randomBytes(final byte[] bytes) {

        if (bytes == null) {
            throw new IllegalArgumentException("null bytes");
        }

        if (bytes.length == 0) {
            throw new IllegalArgumentException("zero length bytes");
        }

        final Random random;

        synchronized (RANDOM) {
            random = new Random(RANDOM.nextLong());
        }

        return randomBytes(random, bytes);
    }


    /**
     * Generates a random bytes.
     *
     * @param random the random to use
     * @param bytes the bytes to fill
     * @return given <code>bytes</code>
     */
    private static byte[] randomBytes(final Random random, final byte[] bytes) {

        if (random == null) {
            throw new IllegalArgumentException("null random");
        }

        if (bytes == null) {
            throw new IllegalArgumentException("null bytes");
        }

        if (bytes.length == 0) {
            throw new IllegalArgumentException("zero length bytes");
        }

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) random.nextInt(256);
        }

        return bytes;
    }


    /**
     * Generates a new encryption key. This method is not intended to be used in
     * production stage.
     *
     * @return a new encryption key.
     */
    protected static byte[] newKey() {

        return randomBytes(new byte[KEY_SIZE_IN_BYTES]);
    }


    /**
     * Checks given
     * <code>key</code>.
     *
     * @param key key to check
     */
    public static void checkKey(final byte[] key) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (key.length != KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != " + KEY_SIZE_IN_BYTES);
        }
    }


    /**
     * Generates a new initialization vector. This method is not intended to be
     * used in production stage.
     *
     * @return a new initialization vector
     */
    protected static byte[] newIv() {
        return randomBytes(new byte[BLOCK_SIZE_IN_BYTES]);
    }


    /**
     * Checks given
     * <code>iv</code>.
     *
     * @param iv the initialization vector to check
     */
    protected static void checkIv(final byte[] iv) {

        if (iv == null) {
            throw new IllegalArgumentException("null iv");
        }

        if (iv.length != BLOCK_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + iv.length + ") != " + BLOCK_SIZE_IN_BYTES);
        }
    }


    /**
     * The class for synchronized instances.
     */
    private static class SynchronizedAes extends Aes {


        public SynchronizedAes(final Aes aes) {
            super();

            if (aes == null) {
                throw new IllegalArgumentException("null aes");
            }

            this.aes = aes;
        }


        //@Override
        public byte[] encrypt(final byte[] iv, final byte[] decrypted) {
            synchronized (aes) {
                return aes.encrypt(iv, decrypted);
            }
        }


        //@Override
        public byte[] decrypt(final byte[] iv, final byte[] encrypted) {
            synchronized (aes) {
                return aes.decrypt(iv, encrypted);
            }
        }


        /**
         * mutex.
         */
        private final Aes aes;


    }


    /**
     * Returns a synchronized (thread-safe) aes backed by the specified aes.
     *
     * @param aes the aes to be "wrapped" in a synchronized aes.
     * @return a synchronized view of the specified aes.
     */
    public static Aes synchronizedAes(final Aes aes) {

        if (aes == null) {
            throw new IllegalArgumentException("null aes");
        }

        return new SynchronizedAes(aes);
    }


    /**
     * Encrypts given
     * <code>decrypted</code>.
     *
     * @param iv initialization vector
     * @param decrypted the bytes to encrypt
     *
     * @return encrypted output
     */
    public abstract byte[] encrypt(final byte[] iv, final byte[] decrypted);


    /**
     * Decrypts given
     * <code>encrypted</code>.
     *
     * @param iv initialization vector
     * @param encrypted the bytes to decrypt
     *
     * @return decrypted output
     */
    public abstract byte[] decrypt(final byte[] iv, final byte[] encrypted);


}

