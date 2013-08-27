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


import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.KeyGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <A> {@link Aes} type parameter
 */
public abstract class AesTest<A extends Aes> {


    protected static final KeyGenerator GENERATOR;


    static {
        try {
            GENERATOR = KeyGenerator.getInstance(AesJCE.ALGORITHM);
            GENERATOR.init(Aes.KEY_SIZE);
        } catch (NoSuchAlgorithmException nsae) {
            throw new InstantiationError(nsae.getMessage());
        }
    }


    public static final byte[] newKey() {
        synchronized (GENERATOR) {
            return GENERATOR.generateKey().getEncoded();
        }
    }


    protected static byte[] wrongKey() {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return new byte[Aes.KEY_SIZE_IN_BYTES - 1];
            case 1:
                return new byte[Aes.KEY_SIZE_IN_BYTES + 1];
            default: // 2
                return new byte[0];

        }
    }


    public static final byte[] newIv() {

        final byte[] iv = new byte[Aes.BLOCK_SIZE_IN_BYTES];

        ThreadLocalRandom.current().nextBytes(iv);

        return iv;
    }


    protected static byte[] wrongIv() {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return new byte[Aes.BLOCK_SIZE_IN_BYTES - 1];
            case 1:
                return new byte[Aes.BLOCK_SIZE_IN_BYTES + 1];
            default: // 2
                return new byte[0];
        }
    }


    protected static byte[] newInput() {

        final Random random = ThreadLocalRandom.current();

        final byte[] input = new byte[random.nextInt(65536)];

        random.nextBytes(input);

        return input;
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void constructWithNullKey() {

        create(null);
    }


    @Test(expectedExceptions = IllegalArgumentException.class,
          invocationCount = 32)
    public void constructWithWrongKey() {

        create(wrongKey());
    }


    @Test(expectedExceptions = NullPointerException.class,
          invocationCount = 32)
    public void testEncryptWithNullIv() {

        create(newKey()).encrypt(null, new byte[0]);
    }


    @Test(expectedExceptions = IllegalArgumentException.class,
          invocationCount = 32)
    public void testEncryptWithWrongIv() {

        create(newKey()).encrypt(wrongIv(), new byte[0]);
    }


    @Test(expectedExceptions = NullPointerException.class)
    public void testEncryptWithNullInput() {

        create(newKey()).encrypt(newIv(), (byte[]) null);
    }


    @Test(expectedExceptions = NullPointerException.class)
    public void testDecryptWithNullInput() {

        create(newKey()).decrypt(newIv(), (byte[]) null);
    }


    @Test
    public void testEncryptWithEmptyInput() {

        create(newKey()).encrypt(newIv(), new byte[0]);
    }


    @Test(invocationCount = 32)
    public void testEncryptDecrypt() {

        final byte[] key = newKey();
        final byte[] iv = newIv();

        final A aes = create(key);

        for (int i = 0; i < 32; i++) {
            final byte[] expected = newInput();
            final byte[] encrypted = aes.encrypt(iv, expected);
            final byte[] actual = aes.decrypt(iv, encrypted);
            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = 32)
    public void testEncryptDecryptWithEmptyInput() {

        final byte[] key = newKey();
        final byte[] iv = newIv();

        final A aes = create(key);

        for (int i = 0; i < 32; i++) {
            final byte[] expected = new byte[0];
            final byte[] encrypted = aes.encrypt(iv, expected);
            final byte[] actual = aes.decrypt(iv, encrypted);
            Assert.assertEquals(actual, expected);
        }
    }


    /**
     * Creates a new instance.
     *
     * @param key the encryption key
     *
     * @return a new instance
     */
    protected abstract A create(final byte[] key);


}
