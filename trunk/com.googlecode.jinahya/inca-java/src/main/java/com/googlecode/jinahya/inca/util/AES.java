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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AES {


    public static final int KEY_SIZE = 0x80; // 128


    public static final int KEY_SIZE_IN_BYTES = KEY_SIZE / 0x08;


    public static final String ALGORITHM = "AES";


    public static final String MODE = "CBC";


    public static final String PADDING = "PKCS5Padding";


    public static final String TRANSFORMATION =
        ALGORITHM + "/" + MODE + "/" + PADDING;


    /**
     * Encrypts given
     * <code>decrypted</code>.
     *
     * @param decrypted the bytes to encrypt
     * @return encrypted output
     */
    public abstract byte[] encrypt(final byte[] decrypted);


    /**
     * Encrypts given
     * <code>decrypted</code>.
     *
     * @param decrypted the string to encrypt
     * @return encrypted output
     */
    public byte[] encrypt(final String decrypted) {

        if (decrypted == null) {
            throw new IllegalArgumentException("null decrypted");
        }

        return encrypt(HEX.decode(decrypted));
    }


    /**
     * Encrypts given
     * <code>decrypted</code> and returns output as a hex string.
     *
     * @param decrypted the bytes to encrypt
     * @return encrypted output as a hex string
     */
    public String encryptToString(final byte[] decrypted) {

        return HEX.encodeToString(encrypt(decrypted));
    }


    /**
     * Encrypts given
     * <code>decrypted</code> and returns output as a hex string.
     *
     * @param decrypted the bytes to encrypt
     * @return encrypted output as a hex string
     */
    public String encryptToString(final String decrypted) {

        if (decrypted == null) {
            throw new IllegalArgumentException("null decrypted");
        }

        return HEX.encodeToString(encrypt(HEX.decode(decrypted)));
    }


    /**
     * Decrypts given
     * <code>encrypted</code>.
     *
     * @param encrypted encrypted octets to decrypt
     *
     * @return decrypted bytes
     */
    public abstract byte[] decrypt(final byte[] encrypted);


    /**
     * Decrypts given
     * <code>encrypted</code>.
     *
     * @param encrypted encrypted hex string
     *
     * @return decrypted bytes
     */
    public byte[] decrypt(final String encrypted) {

        return decrypt(HEX.decode(encrypted));
    }


    /**
     * Decrypts given
     * <code>encrypted</code> and returns output as a hex string.
     *
     * @param encrypted encrypted hex string
     *
     * @return decrypted output as a hex string
     */
    public String decryptToString(final byte[] encrypted) {

        return HEX.encodeToString(decrypt(encrypted));
    }


    /**
     * Decrypts given
     * <code>encrypted</code> and returns output as a hex string.
     *
     * @param encrypted encrypted hex string
     *
     * @return decrypted output as a hex string
     */
    public String decryptToString(final String encrypted) {

        return HEX.encodeToString(decrypt(encrypted));
    }


}

