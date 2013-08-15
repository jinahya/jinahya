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


package com.googlecode.jinahya.crypto;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.testng.Assert;

import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class CiphersTest {


    @Test
    public void testAES_CBC_PKCS5Padding() throws Exception {

        final String algorithm = "AES";
        final String mode = "CBC";
        final String padding = "PKCS5Padding";
        final String transformation = algorithm + "/" + mode + "/" + padding;

        final Cipher cipher = Cipher.getInstance(transformation);

        final KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
        keygen.init(128);
        final Key key = keygen.generateKey();

        final byte[] iv = new byte[cipher.getBlockSize()];
        ThreadLocalRandom.current().nextBytes(iv);

        final byte[] expected = new byte[1051];
        ThreadLocalRandom.current().nextBytes(expected);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        Ciphers.doFinal(cipher, new ByteArrayInputStream(expected), baos,
                        new byte[102]);

        final byte[] encrypted = baos.toByteArray();

        baos.reset();
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        Ciphers.doFinal(cipher, new ByteArrayInputStream(encrypted), baos,
                        new byte[983]);

        final byte[] actual = baos.toByteArray();

        Assert.assertEquals(actual, expected);
    }


}

