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


import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AESTest {


    private static final Random RANDOM = new Random();


    /**
     * @see {@link <a href="http://goo.gl/DfQvM">AES CBC 128-bit encryption mode</a>
     * }
     */
    @Test
    public static void testAgainstTestVector1() {

        final String encryptionKey = "2b7e151628aed2a6abf7158809cf4f3c";
        final byte[] key = HEX.decode(encryptionKey);

        final String[] vectors = {
            "000102030405060708090A0B0C0D0E0F", // Initialization vector
            "6bc1bee22e409f96e93d7e117393172a", // Test vector 
            "7649abac8119b246cee98e9b12e9197d", // Cipher text 
            "7649ABAC8119B246CEE98E9B12E9197D",
            "ae2d8a571e03ac9c9eb76fac45af8e51",
            "5086cb9b507219ee95db113a917678b2",
            "5086CB9B507219EE95DB113A917678B2",
            "30c81c46a35ce411e5fbc1191a0a52ef",
            "73bed6b8e3c1743b7116e69e22229516",
            "73BED6B8E3C1743B7116E69E22229516",
            "f69f2445df4f9b17ad2b417be66c3710",
            "3ff1caa1681fac09120eca307586e1a7"};

        for (int i = 0; i < vectors.length; i += 3) {
            final byte[] iv = HEX.decode(vectors[i]);
            final String decrypted = vectors[i + 1].toUpperCase();
            final String expected = vectors[i + 2].toUpperCase();
            final String actual = AES.encryptToString(key, iv, decrypted);
            Assert.assertTrue(actual.startsWith(expected));
        }
    }


    @Test(invocationCount = 128)
    public static void testEncryptDecrypt() {

        final byte[] key = new byte[AES.KEY_SIZE_IN_BYTES];
        RANDOM.nextBytes(key);

        final byte[] iv = new byte[AES.KEY_SIZE_IN_BYTES];
        RANDOM.nextBytes(iv);

        final byte[] expected = new byte[RANDOM.nextInt(1024)];
        RANDOM.nextBytes(expected);

        final byte[] encrypted = AES.encrypt(key, iv, expected);

        final byte[] actual = AES.decrypt(key, iv, encrypted);

        Assert.assertEquals(actual, expected);
    }


}

