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


import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexTest {


    @Test
    public static void testEncodeDecode() {

        final Random random = ThreadLocalRandom.current();

        final byte[] expected = new byte[random.nextInt(512) + 512];
        random.nextBytes(expected);

        final byte[] encoded = Hex.encode(expected);

        final byte[] actual = Hex.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public static void testEncodeDecodeString() {

        final Random random = ThreadLocalRandom.current();

        final byte[] expected = new byte[random.nextInt(512) + 512];
        random.nextBytes(expected);

        final String encoded = Hex.encodeToString(expected);

        final byte[] actual = Hex.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


//    @Test
//    public static void testNormFast() {
//
//        final Random random = ThreadLocalRandom.current();
//
//        final byte[] expected = new byte[random.nextInt(512) + 512];
//        random.nextBytes(expected);
//
//        long start = System.nanoTime();
//        final String encoded1 = Hex.encodeToString(expected);
//        final byte[] actual1 = Hex.decode(encoded1);
//        long finish = System.nanoTime();
//        System.out.println("norm: " + (finish - start) + " ns");
//        Assert.assertEquals(actual1, expected);
//
//        start = System.nanoTime();
//        final String encoded2 = Hex.encodeToString_(expected);
//        final byte[] actual2 = Hex.decode_(encoded2);
//        finish = System.nanoTime();
//        System.out.println("fast: " + (finish - start) + " ns");
//        Assert.assertEquals(actual2, expected);
//    }


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
            final String actual = Hex.encodeToString(decoded.getBytes("UTF-8"));
            Assert.assertEquals(actual, expected);
        }

        // decode
        for (Iterator<String> i = Arrays.asList(v).iterator(); i.hasNext();) {
            final String expected = i.next();
            final String encoded = i.next();
            final String actual = Hex.decodeToString(encoded);
            Assert.assertEquals(actual, expected);
        }
    }


//    @Test
//    public static void test_AgainstRFC4648Base16TextVector()
//        throws UnsupportedEncodingException {
//
//        final String[] v = {"", "",
//                            "f", "66",
//                            "fo", "666F",
//                            "foo", "666F6F",
//                            "foob", "666F6F62",
//                            "fooba", "666F6F6261",
//                            "foobar", "666F6F626172"};
//
//        // encode
//        for (Iterator<String> i = Arrays.asList(v).iterator(); i.hasNext();) {
//            final String decoded = i.next();
//            final String expected = i.next();
//            final String actual = Hex.encodeToString_(decoded.getBytes("UTF-8"));
//            Assert.assertEquals(actual, expected);
//        }
//
//        // decode
//        for (Iterator<String> i = Arrays.asList(v).iterator(); i.hasNext();) {
//            final String expected = i.next();
//            final String encoded = i.next();
//            final String actual = Hex.decodeToString_(encoded);
//            Assert.assertEquals(actual, expected);
//        }
//    }


}
