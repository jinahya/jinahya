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


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class CiphersTest {


    @Test
    public void testAES_CBC_PKCS5Padding() throws Exception {


        String message = "This string contains a secret message.";
        System.out.println("Plaintext: " + message + "\n");

        // generate a key
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(128);  // To use 256 bit keys, you need the "unlimited strength" encryption policy files from Sun.
        byte[] key = keygen.generateKey().getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

        // build the initialization vector.  This example is all zeros, but it 
        // could be any value or generated using a random number generator.
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        // initialize the cipher for encrypt mode
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec);

        // encrypt the message
        byte[] encrypted = cipher.doFinal(message.getBytes());
//        System.out.println("Ciphertext: " + hexEncode(encrypted) + "\n");

        // reinitialize the cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivspec);

        // decrypt the message
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("Plaintext: " + new String(decrypted) + "\n");

        final Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        final Map<Integer, Integer> m2 = Collections.<Integer, Integer>unmodifiableMap(m);

    }


}

