/*
 * Copyright 2011 Jin Kwon.
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


package com.googlecode.jinahya.rfc4648;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class TestVectors {


    public static final Map<String, String> BASE64;


    static {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("", "");
        map.put("f", "Zg==");
        map.put("fo", "Zm8=");
        map.put("foo", "Zm9v");
        map.put("foob", "Zm9vYg==");
        map.put("fooba", "Zm9vYmE=");
        map.put("foobar", "Zm9vYmFy");
        BASE64 = Collections.unmodifiableMap(map);
    }


    public static final Map<String, String> BASE64URL;


    static {
        final Map<String, String> map = new HashMap<String, String>(BASE64);
        for (Entry<String, String> entry : map.entrySet()) {
            final String value = entry.getValue();
            final int padIndex = value.indexOf(Base.PAD);
            if (padIndex != -1) {
                entry.setValue(value.substring(0, padIndex));
            }
        }
        BASE64URL = Collections.unmodifiableMap(map);
    }


    public static final Map<String, String> BASE32;


    static {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("", "");
        map.put("f", "MY======");
        map.put("fo", "MZXQ====");
        map.put("foo", "MZXW6===");
        map.put("foob", "MZXW6YQ=");
        map.put("fooba", "MZXW6YTB");
        map.put("foobar", "MZXW6YTBOI======");
        BASE32 = Collections.unmodifiableMap(map);
    }


    public static final Map<String, String> BASE32HEX;


    static {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("", "");
        map.put("f", "CO======");
        map.put("fo", "CPNG====");
        map.put("foo", "CPNMU===");
        map.put("foob", "CPNMUOG=");
        map.put("fooba", "CPNMUOJ1");
        map.put("foobar", "CPNMUOJ1E8======");
        BASE32HEX = Collections.unmodifiableMap(map);
    }


    public static final Map<String, String> BASE16;


    static {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("", "");
        map.put("f", "66");
        map.put("fo", "666F");
        map.put("foo", "666F6F");
        map.put("foob", "666F6F62");
        map.put("fooba", "666F6F6261");
        map.put("foobar", "666F6F626172");
        BASE16 = Collections.unmodifiableMap(map);
    }


    private void testBase(final Map<String, String> map, final Base base)
        throws IOException {

        for (Entry<String, String> entry : map.entrySet()) {
            final byte[] input = entry.getKey().getBytes("US-ASCII");
            final byte[] output = base.encode(input);
            Assert.assertEquals(new String(output, "US-ASCII"),
                                entry.getValue());
        }

        for (Entry<String, String> entry : map.entrySet()) {
            final byte[] input = entry.getValue().getBytes("US-ASCII");
            final byte[] output = base.decode(input);
            Assert.assertEquals(new String(output, "US-ASCII"),
                                entry.getKey());
        }
    }


    @Test
    public void testBASE64() throws IOException {

        testBase(BASE64, new Base64());
    }


    @Test
    public void testBASE64URL() throws IOException {

        testBase(BASE64URL, new Base64URL());
    }


    @Test
    public void testBASE32() throws IOException {

        testBase(BASE32, new Base32());
    }


    @Test
    public void testBASE32HEX() throws IOException {

        testBase(BASE32HEX, new Base32Hex());
    }


    @Test
    public void testBASE16() throws IOException {

        testBase(BASE16, new Base16());
    }


}

