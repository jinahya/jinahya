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


import com.googlecode.jinahya.inca.util.HEX;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HEXTest {


    private static final Random RANDOM = new Random();


    @Test(invocationCount = 128)
    public static void testEncodingDecoding() {

        final byte[] expected = new byte[RANDOM.nextInt(512) + 512];
        RANDOM.nextBytes(expected);

        final byte[] encoded = HEX.encode(expected);

        final byte[] actual = HEX.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public static void testEncodingDecodingString() {

        final byte[] expected = new byte[RANDOM.nextInt(512) + 512];
        RANDOM.nextBytes(expected);

        final String encoded = HEX.encodeToString(expected);

        final byte[] actual = HEX.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public static void testAgainstRFC4648Base16TextVector()
        throws UnsupportedEncodingException {


        final String[] v = {"", "",
                            "f", "66",
                            "fo", "666F",
                            "foo", "666F6F",
                            "foob", "666F6F62",
                            "fooba", "666F6F6261",
                            "foobar", "666F6F626172"};

        // encode
        for (Iterator<String> i = Arrays.asList(v).iterator(); i.hasNext();) {
            final String decoded = i.next();
            final String expected = i.next();
            final String actual = HEX.encodeToString(decoded.getBytes("UTF-8"));
            Assert.assertEquals(actual, expected);
        }

        // decode
        for (Iterator<String> i = Arrays.asList(v).iterator(); i.hasNext();) {
            final String expected = i.next();
            final String encoded = i.next();
            final String actual = HEX.decodeToString(encoded);
            Assert.assertEquals(actual, expected);
        }
    }


}

