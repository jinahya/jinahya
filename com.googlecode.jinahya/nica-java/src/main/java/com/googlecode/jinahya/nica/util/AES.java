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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AES {


    /**
     * key size in bits.
     */
    public static final int KEY_SIZE = 0x80; // 128


    /**
     * key size in bytes.
     */
    public static final int KEY_SIZE_IN_BYTES = KEY_SIZE / 0x08; // 16


    /**
     * algorithm.
     */
    public static final String ALGORITHM = "AES";


    /**
     * mode.
     */
    public static final String MODE = "CBC";


    /**
     * padding.
     */
    public static final String PADDING = "PKCS5Padding";


    /**
     * transformation.
     */
    public static final String TRANSFORMATION =
        ALGORITHM + "/" + MODE + "/" + PADDING;


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
     * @param encrypted encrypted bytes to decrypt
     *
     * @return decrypted bytes
     */
    public abstract byte[] decrypt(final byte[] iv, final byte[] encrypted);


}

