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


package com.googlecode.jinahya.nica;


import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.AesBC;
import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.HacBC;
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
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HeadersTest {


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


    @Test
    public void testGetHeaders() {

        final Map<String, String> names = new HashMap<String, String>();
        names.put("key1", "value1");
        names.put("key2", "value2");
        final String name = Par.encode(names);

        final Codes codes = new Codes();
        codes.putVolatileCode(CodeKeys.USER_USERNAME, "username");
        codes.putVolatileCode(CodeKeys.USER_PASSWORD, "password");

        final byte[] key = newKey();

        final Headers headers = new Headers(
            name, codes, new AesBC(key), new HacBC(key));

        final long start = System.currentTimeMillis();
        final Map headers_ = headers.getHeaders();
        final long finish = System.currentTimeMillis();
        System.out.println("elapsed: " + (finish - start) + "ms");

        for (Header header : Header.values()) {
            System.out.println(header.fieldName() + ": "
                               + headers_.get(header.fieldName()));
        }

        final byte[] iv = Hex.decode(
            (String) headers_.get(Header.INIT.fieldName()));

        final byte[] code = Hex.decode(
            (String) headers_.get(Header.CODE.fieldName()));

        final byte[] base = new AesJCE(key).decrypt(iv, code);

        final byte[] auth = Hex.decode(
            (String) headers_.get(Header.AUTH.fieldName()));

        Assert.assertEquals(new HacJCE(key).authenticate(base), auth);
    }


}

