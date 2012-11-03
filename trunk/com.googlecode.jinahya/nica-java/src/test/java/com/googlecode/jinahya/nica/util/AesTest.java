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
 * @param <A> Aes type parameter
 */
public abstract class AesTest<A extends Aes> {


    protected static final KeyGenerator GENERATOR;


    static {
        try {
            GENERATOR = KeyGenerator.getInstance(Aes.ALGORITHM);
            GENERATOR.init(Aes.KEY_SIZE);
        } catch (NoSuchAlgorithmException nsae) {
            throw new InstantiationError(nsae.getMessage());
        }
    }


    protected static byte[] newKey() {
        synchronized (GENERATOR) {
            return GENERATOR.generateKey().getEncoded();
        }
    }


    protected static byte[] newIv() {
        final byte[] iv = new byte[Aes.BLOCK_SIZE_IN_BYTES];
        ThreadLocalRandom.current().nextBytes(iv);
        return iv;
    }


    protected static byte[] newInput() {
        final Random random = ThreadLocalRandom.current();
        final byte[] input = new byte[random.nextInt(65536)];
        random.nextBytes(input);
        return input;
    }


    @Test(invocationCount = 128)
    public void testEncryptDecrypt() {

        final byte[] key = newKey();
        final byte[] iv = newIv();

        final A aes = newInstance(key);

        final byte[] expected = newInput();
        //System.out.println("expected: " + Hex.encodeToString(expected));

        final byte[] encrypted = aes.encrypt(iv, expected);
        //System.out.println("encrypted: " + Hex.encodeToString(encrypted));

        final byte[] actual = aes.decrypt(iv, encrypted);
        //System.out.println("actual: " + Hex.encodeToString(actual));

        Assert.assertEquals(actual, expected);
    }


    /**
     *
     * @param key
     *
     * @return
     */
    protected abstract A newInstance(final byte[] key);


}

