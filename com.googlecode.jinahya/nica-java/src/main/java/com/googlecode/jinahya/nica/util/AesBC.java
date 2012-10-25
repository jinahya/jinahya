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


import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESLightEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AesBC extends Aes {


    /**
     * Creates a new instance.
     *
     * @param key encryption key.
     *
     * @return a new instance.
     */
    public static Aes newInstance(final byte[] key) {
        return new AesBC(key);
    }


    /**
     * Creates a new instance.
     *
     * @param key encryption key
     */
    public AesBC(final byte[] key) {
        super();

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (key.length != KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != " + KEY_SIZE_IN_BYTES);
        }

        this.keyParameter = new KeyParameter(key);
    }


    //@Override
    public byte[] encrypt(final byte[] iv, final byte[] decrypted) {

        if (iv == null) {
            throw new IllegalArgumentException("null iv");
        }

        if (iv.length != BLOCK_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "iv.length(" + iv.length + ") != " + BLOCK_SIZE_IN_BYTES);
        }

        if (decrypted == null) {
            throw new IllegalArgumentException("null decrypted");
        }

        final BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
            new CBCBlockCipher(new AESLightEngine()));

        cipher.init(true, new ParametersWithIV(keyParameter, iv));

        final byte[] encrypted =
            new byte[cipher.getOutputSize(decrypted.length)];

        int offset = cipher.processBytes(
            decrypted, 0, decrypted.length, encrypted, 0);

        try {
            offset += cipher.doFinal(encrypted, offset);
        } catch (InvalidCipherTextException icte) {
            throw new RuntimeException(icte.getMessage());
        }

        if (offset < encrypted.length) {
            final byte[] trimmed = new byte[offset];
            System.arraycopy(encrypted, 0, trimmed, 0, offset);
            return trimmed;
        }

        return encrypted;
    }


    //@Override
    public byte[] decrypt(final byte[] iv, final byte[] encrypted) {

        if (iv == null) {
            throw new IllegalArgumentException("null iv");
        }

        if (iv.length != BLOCK_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "iv.length(" + iv.length + ") != " + BLOCK_SIZE_IN_BYTES);
        }

        if (encrypted == null) {
            throw new IllegalArgumentException("null encrypted");
        }

        final BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
            new CBCBlockCipher(new AESLightEngine()));

        cipher.init(false, new ParametersWithIV(keyParameter, iv));

        final byte[] decrypted =
            new byte[cipher.getOutputSize(encrypted.length)];

        int offset = cipher.processBytes(
            encrypted, 0, encrypted.length, decrypted, 0);

        try {
            offset += cipher.doFinal(decrypted, offset);
        } catch (InvalidCipherTextException icte) {
            throw new RuntimeException(icte.getMessage());
        }

        if (offset < decrypted.length) {
            final byte[] trimmed = new byte[offset];
            System.arraycopy(decrypted, 0, trimmed, 0, offset);
            return trimmed;
        }

        return decrypted;
    }


    /**
     * key.
     */
    private final KeyParameter keyParameter;


}

