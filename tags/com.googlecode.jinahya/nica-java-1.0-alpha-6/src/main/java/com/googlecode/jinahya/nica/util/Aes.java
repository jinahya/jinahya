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
    public static final int KEY_SIZE_IN_BYTES = KEY_SIZE / 8; // 16


    /**
     * AES block size in bits.
     */
    public static final int BLOCK_SIZE = 0x80; // 128;


    /**
     * AES block size in bytes.
     */
    public static final int BLOCK_SIZE_IN_BYTES = BLOCK_SIZE / 8; // 16





    /**
     * Generates a new initialization vector.
     *
     * @param random a random to use.
     *
     * @return a new initialization vector.
     */
    protected static byte[] newIv(final Random random) {

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
    public static byte[] newIv() {

        return newIv(new SecureRandom());
    }


    /**
     * Encrypts given {@code decrypted}. Any implementation/provider specific
     * exception will thrown as wrapped in a RuntimeException.
     *
     * @param iv initialization vector
     * @param decrypted the bytes to encrypt
     *
     * @return encrypted output
     */
    public abstract byte[] encrypt(byte[] iv, byte[] decrypted);


    /**
     * Decrypts given {@code encrypted}. Any implementation/provider specific
     * exception will be thrown as wrapped in a RuntimeException.
     *
     * @param iv initialization vector
     * @param encrypted the bytes to decrypt
     *
     * @return decrypted output
     */
    public abstract byte[] decrypt(byte[] iv, byte[] encrypted);


}
