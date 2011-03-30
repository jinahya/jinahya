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


package jinahya.rfc4648;


import java.io.IOException;
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


    private static final Map<String, String> BASE64 =
        new HashMap<String, String>();


    static {
        BASE64.put("", "");
        BASE64.put("f", "Zg==");
        BASE64.put("fo", "Zm8=");
        BASE64.put("foo", "Zm9v");
        BASE64.put("foob", "Zm9vYg==");
        BASE64.put("fooba", "Zm9vYmE=");
        BASE64.put("foobar", "Zm9vYmFy");
    }


    private static final Map<String, String> BASE32 =
        new HashMap<String, String>();


    static {
        BASE32.put("", "");
        BASE32.put("f", "MY======");
        BASE32.put("fo", "MZXQ====");
        BASE32.put("foo", "MZXW6===");
        BASE32.put("foob", "MZXW6YQ=");
        BASE32.put("fooba", "MZXW6YTB");
        BASE32.put("foobar", "MZXW6YTBOI======");
    }


    private static final Map<String, String> BASE32HEX =
        new HashMap<String, String>();


    static {
        BASE32HEX.put("", "");
        BASE32HEX.put("f", "CO======");
        BASE32HEX.put("fo", "CPNG====");
        BASE32HEX.put("foo", "CPNMU===");
        BASE32HEX.put("foob", "CPNMUOG=");
        BASE32HEX.put("fooba", "CPNMUOJ1");
        BASE32HEX.put("foobar", "CPNMUOJ1E8======");
    }


    private static final Map<String, String> BASE16 =
        new HashMap<String, String>();


    static {
        BASE16.put("", "");
        BASE16.put("f", "66");
        BASE16.put("fo", "666F");
        BASE16.put("foo", "666F6F");
        BASE16.put("foob", "666F6F62");
        BASE16.put("fooba", "666F6F6261");
        BASE16.put("foobar", "666F6F626172");
    }


    private void testBase(final Map<String, String> map, final Base base)
        throws IOException {

        for (Entry<String, String> entry : map.entrySet()) {
            final byte[] input = entry.getKey().getBytes("US-ASCII");
            final char[] output = base.encode(input);
            Assert.assertEquals(new String(output), entry.getValue());
        }

        for (Entry<String, String> entry : map.entrySet()) {
            final char[] input = entry.getValue().toCharArray();
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

        final Map<String, String> BASE64URL =
            new HashMap<String, String>(BASE64);

        for (Entry<String, String> entry : BASE64URL.entrySet()) {
            final String value = entry.getValue();
            final int padIndex = value.indexOf(Base.PAD);
            if (padIndex != -1) {
                entry.setValue(value.substring(0, padIndex));
            }
        }

        testBase(BASE64URL, new Base64URL());
    }


    @Test
    public void testBASE32() throws IOException {
        testBase(BASE32, new Base32());
    }


    @Test
    public void testBASE32HEX() throws IOException {
        testBase(BASE32HEX, new Base32HEX());
    }


    @Test
    public void testBASE16() throws IOException {
        testBase(BASE16, new Base16());
    }
}
