/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.nica;


import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.HacJCE;
import com.googlecode.jinahya.nica.util.Hex;
import com.googlecode.jinahya.nica.util.Par;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.KeyGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class NicaTest {


    protected static final byte[] newKey() {
        try {
            final KeyGenerator keyGenerator =
                KeyGenerator.getInstance(AesJCE.ALGORITHM);
            keyGenerator.init(Aes.KEY_SIZE);
            return keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
    }


    @Test
    public void testNica() {

        final byte[] key = new byte[]{
            (byte) 0x07, (byte) 0xC6, (byte) 0xFD, (byte) 0x43,
            (byte) 0x58, (byte) 0x24, (byte) 0x4A, (byte) 0xFE,
            (byte) 0x0F, (byte) 0x27, (byte) 0xD2, (byte) 0x54,
            (byte) 0xAD, (byte) 0xAB, (byte) 0x91, (byte) 0x63
        };
        System.out.println("  key: " + Hex.encodeToString(key));

        final byte[] iv = new byte[]{
            (byte) 0xBB, (byte) 0xAB, (byte) 0xBE, (byte) 0xBC,
            (byte) 0x6C, (byte) 0x06, (byte) 0xC7, (byte) 0x23,
            (byte) 0x27, (byte) 0xD0, (byte) 0x81, (byte) 0xE7,
            (byte) 0x7F, (byte) 0xB0, (byte) 0x7E, (byte) 0x96
        };
        System.out.println("   iv: " + Hex.encodeToString(iv));

        final Map<String, String> names = new HashMap<>();
        for (int i = 0; i < 3; i++) {
//            names.put("name" + i, "name" + i);
        }
        names.put("Last", "홍");
        names.put("First", "길동");
        System.out.println("names: " + names);

        final Map<String, String> codes = new HashMap<>();
        for (int i = 0; i < 5; i++) {
//            codes.put("code" + i, "code" + i);
        }
        codes.put("SSN", "1234가");
        codes.put("CCN", "5678나");
        codes.put("T", "1");
        codes.put("N", "1");
        System.out.println("codes: " + codes);


        // ----------------------------------------------- client-side challenge

        final String name = Par.encode(names);
        System.out.println(HeaderNames.NAME + ": " + name);

        final String init = Hex.encodeToString(iv);
        System.out.println(HeaderNames.INIT + ": " + init
                           + " (" + init.length() + ")");

        final String base = Par.encode(codes);
        System.out.println(HeaderNames.BASE + ": " + base);

        final String code = new AesJCE(key).encryptToString(iv, base);
        System.out.println(HeaderNames.CODE + ": " + code);

        final String auth = new HacJCE(key).authenticateToString(base);
        System.out.println(HeaderNames.AUTH + ": " + auth
                           + " (" + auth.length() + ")");


        // ---------------------------------------------- server-side validation

        final String base1 = new AesJCE(key).decryptToString(iv, code);
        Assert.assertEquals(base1, base);

        final String auth1 = new HacJCE(key).authenticateToString(base);
        Assert.assertEquals(auth1, auth);
    }


}
