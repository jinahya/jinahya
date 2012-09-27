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


import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.KeyGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AESTest<A extends AES> {


    protected static final Random RANDOM = new Random();


    protected static final KeyGenerator GENERATOR;


    static {
        try {
            GENERATOR = KeyGenerator.getInstance(AES.NAME);
            GENERATOR.init(128);
        } catch (NoSuchAlgorithmException nsae) {
            throw new InstantiationError(nsae.getMessage());
        }
    }


    protected static byte[] generateKey() {
        return GENERATOR.generateKey().getEncoded();
    }


    protected static byte[] generateIv() {
        final byte[] iv = new byte[AES.KEY_SIZE_IN_BYTES];
        RANDOM.nextBytes(iv);
        return iv;
    }


    @Test(invocationCount = 128)
    public void testEncryptDecrypt() {

        final byte[] key = generateKey();
        final byte[] iv = generateIv();

        final A aes = newInstance(key, iv);

        final byte[] expected = new byte[RANDOM.nextInt(1024)];
        RANDOM.nextBytes(expected);

        final byte[] encrypted = aes.encrypt(expected);

        final byte[] actual = aes.decrypt(encrypted);

        Assert.assertEquals(actual, expected);
    }


    /**
     *
     * @return
     */
    protected abstract A newInstance(final byte[] key, final byte[] iv);


}

