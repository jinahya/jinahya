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


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AesJCE extends Aes {


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
     * Generates a new key.
     *
     * @return a new key.
     */
    protected static final byte[] newKey() {
        try {
            final KeyGenerator keyGenerator =
                KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(KEY_SIZE);
            return keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
    }


    /**
     * Creates a new instance.
     *
     * @param key encryption key
     */
    public AesJCE(final byte[] key) {

        super();

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (key.length != KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != " + KEY_SIZE_IN_BYTES);
        }

        this.key = new SecretKeySpec(key, ALGORITHM);

        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(
                "\"" + ALGORITHM + "\" not supported?", nsae);
        } catch (NoSuchPaddingException nspe) {
            throw new RuntimeException(
                "\"" + PADDING + "\" not supported?", nspe);
        }
    }


    //@Override // commented for pre5
    public byte[] encrypt(final byte[] iv, final byte[] decrypted) {

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
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        } catch (InvalidKeyException ike) {
            throw new RuntimeException(ike);
        } catch (InvalidAlgorithmParameterException iape) {
            throw new RuntimeException(iape);
        }

        try {
            return cipher.doFinal(decrypted);
        } catch (IllegalBlockSizeException ibse) {
            throw new RuntimeException(ibse);
        } catch (BadPaddingException bpe) {
            throw new RuntimeException(bpe);
        }
    }


    //@Override // commented for pre5
    public byte[] decrypt(final byte[] iv, final byte[] encrypted) {

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
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        } catch (InvalidKeyException ike) {
            throw new RuntimeException(ike);
        } catch (InvalidAlgorithmParameterException iape) {
            throw new RuntimeException(iape);
        }

        try {
            return cipher.doFinal(encrypted);
        } catch (IllegalBlockSizeException ibse) {
            throw new RuntimeException(ibse);
        } catch (BadPaddingException bpe) {
            throw new RuntimeException(bpe);
        }
    }


    /**
     * key.
     */
    private final Key key;


    /**
     * cipher.
     */
    private final Cipher cipher;


}
