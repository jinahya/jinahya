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


    /**
     * private random.
     */
    private static final Random RANDOM = new Random();


    /**
     * Generates a new initialization vector. This method is not intended to be
     * used in production stages.
     *
     * @return a new initialization vector
     */
    public static byte[] newIv() {

        synchronized (RANDOM) {
            return newIv(RANDOM);
        }
    }


    /**
     * Generates a new initialization vector. This method is not intended to be
     * used in production stages.
     *
     * @param random a random to be used
     *
     * @return a new initialization vector.
     */
    public static final byte[] newIv(final Random random) {

        if (random == null) {
            throw new IllegalArgumentException("null random");
        }

        final byte[] iv = new byte[BLOCK_SIZE_IN_BYTES];
//        random.nextBytes(iv);
        for (int i = 0; i < iv.length; i++) {
            iv[i] = (byte) random.nextInt(256);
        }

        return iv;
    }


    /**
     * The class for synchronized instances.
     */
    private static class SynchronizedAes extends Aes {


        /**
         * Creates a new instance.
         *
         * @param mutex aes to wrapped
         */
        public SynchronizedAes(final Aes mutex) {
            super();

            if (mutex == null) {
                throw new IllegalArgumentException("null mutex");
            }

            this.mutex = mutex;
        }


        //@Override
        public byte[] encrypt(final byte[] iv, final byte[] decrypted) {
            synchronized (mutex) {
                return mutex.encrypt(iv, decrypted);
            }
        }


        //@Override
        public byte[] decrypt(final byte[] iv, final byte[] encrypted) {
            synchronized (mutex) {
                return mutex.decrypt(iv, encrypted);
            }
        }


        /**
         * aes.
         */
        private final Aes mutex;


    }


    /**
     * Returns a synchronized (thread-safe) aes backed by the specified aes.
     *
     * @param aes the aes to be "wrapped" in a synchronized aes.
     *
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
     * <code>decrypted</code>. Any implementation/provider specfic exception
     * will thrown as wrapped in a RuntimeException.
     *
     * @param iv initialization vector
     * @param decrypted the bytes to encrypt
     *
     * @return encrypted output
     */
    public abstract byte[] encrypt(final byte[] iv, final byte[] decrypted);


    /**
     * Decrypts given
     * <code>encrypted</code>. Any implementation/provider specific exception
     * will be thrown as wrapped in a Runtimeexception.
     *
     * @param iv initialization vector
     * @param encrypted the bytes to decrypt
     *
     * @return decrypted output
     */
    public abstract byte[] decrypt(final byte[] iv, final byte[] encrypted);


}

