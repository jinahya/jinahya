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


import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AESBC extends AES {


    /**
     * Creates a new instance.
     */
    public AESBC(final byte[] key, final byte[] iv) {
        this(AESEngine.class, key, iv);
    }


    /**
     * Creates a new instance for given
     * <code>engineClass</code>.
     */
    public AESBC(final Class engineClass, final byte[] key, final byte[] iv) {
        super();

        if (engineClass == null) {
            throw new IllegalArgumentException("null engineClass");
        }

        if (!BlockCipher.class.isAssignableFrom(engineClass)) {
            throw new IllegalArgumentException(
                "engineClass(" + engineClass + ") is not assignable to "
                + BlockCipher.class);
        }

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

        this.engineClass = engineClass;

        cipherParameters = new ParametersWithIV(new KeyParameter(key), iv);
    }


    //@Override
    public byte[] encrypt(final byte[] decrypted) {

        if (decrypted == null) {
            throw new IllegalArgumentException("null decrypted");
        }

        final BlockCipher engine;
        try {
            engine = (BlockCipher) engineClass.newInstance();
            final String algorithmName = engine.getAlgorithmName();
            if (!ALGORITHM.equals(algorithmName)) {
                throw new RuntimeException(
                    "wrong engine.algorithmName:" + algorithmName);
            }
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie.getMessage());
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae.getMessage());
        }

        final BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
            new CBCBlockCipher(engine));

        cipher.init(true, cipherParameters);

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
    public byte[] decrypt(final byte[] encrypted) {

        if (encrypted == null) {
            throw new IllegalArgumentException("null encrypted");
        }

        final BlockCipher engine;
        try {
            engine = (BlockCipher) engineClass.newInstance();
            final String algorithmName = engine.getAlgorithmName();
            if (!ALGORITHM.equals(algorithmName)) {
                throw new RuntimeException(
                    "wrong engine.algorithmName:" + algorithmName);
            }
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie.getMessage());
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae.getMessage());
        }

        final BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
            new CBCBlockCipher(engine));

        cipher.init(false, cipherParameters);

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
     * engine class.
     */
    private final Class engineClass;


    /**
     * cipher parameters.
     */
    private final CipherParameters cipherParameters;


}

