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


    protected static byte[] generateKey() {
        synchronized (GENERATOR) {
            return GENERATOR.generateKey().getEncoded();
        }
    }


    protected static byte[] generateIv() {
        final byte[] iv = new byte[Aes.BLOCK_SIZE_IN_BYTES];
        ThreadLocalRandom.current().nextBytes(iv);
        return iv;
    }


    @Test
    public void testEncryptDecrypt() {

        final byte[] key = generateKey();
        final byte[] iv = generateIv();

        final A aes = newInstance(key);

        final byte[] expected =
            new byte[ThreadLocalRandom.current().nextInt(1024)];
        ThreadLocalRandom.current().nextBytes(expected);

        final byte[] encrypted = aes.encrypt(iv, expected);

        final byte[] actual = aes.decrypt(iv, encrypted);

        Assert.assertEquals(actual, expected);
    }


    /**
     *
     * @return
     */
    protected abstract A newInstance(final byte[] key);


}

