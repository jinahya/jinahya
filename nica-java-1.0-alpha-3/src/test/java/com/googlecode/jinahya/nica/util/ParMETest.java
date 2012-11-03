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


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ParMETest {


    private static final Random RANDOM = new Random();


    @Test
    public static void testEncode() {

        try {
            ParME.encode(null);
            Assert.fail("passed: encode(null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        final Hashtable<String, String> decoded =
            new Hashtable<String, String>();

//        try {
//            KVPME.encode(decoded);
//            Assert.fail("passed: encode([EMPTY])");
//        } catch (IllegalArgumentException iae) {
//            // expected
//        }

        decoded.put(RandomStringUtils.random(RANDOM.nextInt(16)),
                    RandomStringUtils.random(RANDOM.nextInt(16)));

        final String encoded = ParME.encode(decoded);
    }


    @Test
    public static void testDecode() {

        try {
            ParME.decode(null);
            Assert.fail("passed: decode(null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

//        try {
//            KVPME.decode("");
//            Assert.fail("passed: decode([EMPTY])");
//        } catch (IllegalArgumentException iae) {
//            // expected
//        }

        {
            final Hashtable decoded = ParME.decode("");
            Assert.assertTrue(decoded.isEmpty());
        }

        {
            try {
                final Hashtable decoded = ParME.decode("&");
                Assert.fail("passed: decode(\"&\")");
            } catch (IllegalArgumentException iae) {
                // expected;
            }
        }

        {
            final Hashtable decoded = ParME.decode("=");
            Assert.assertTrue(decoded.size() == 1);
        }

        {
            try {
                final Hashtable decoded = ParME.decode("=&=");
                Assert.fail("passed: decode(\"=&=\")");
            } catch (IllegalArgumentException iae) {
                // expected
            }
        }
    }


    private static void testEncodeDecode(final Hashtable expected) {
        final String encoded = ParME.encode(expected);
        final Hashtable actual = ParME.decode(encoded);
        Assert.assertEquals(actual, expected);
    }


    @Test
    public static void testEncodeDecode() {

        final Hashtable expected = new Hashtable();

        testEncodeDecode(expected);
        expected.clear();

        final int count = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < count; i++) {
            final String key = RandomStringUtils.random(RANDOM.nextInt(16));
            final String val = RandomStringUtils.random(RANDOM.nextInt(16));
            expected.put(key, val);
        }

        testEncodeDecode(expected);
    }


    @Test
    public static void testEncodingAgainstSE() {

        final Hashtable expected = new Hashtable();

        final int count = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < count; i++) {
            final String key = RandomStringUtils.random(RANDOM.nextInt(16));
            final String val = RandomStringUtils.random(RANDOM.nextInt(16));
            expected.put(key, val);
        }

        final String encoded = ParME.encode(expected);

        final Map<String, String> actual = Par.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public static void testDecodingAgainstSE() {


        final Map<String, String> expected = new HashMap<String, String>();

        final int count = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < count; i++) {
            final String key = RandomStringUtils.random(RANDOM.nextInt(16));
            final String val = RandomStringUtils.random(RANDOM.nextInt(16));
            expected.put(key, val);
        }

        final String encoded = Par.encode(expected);

        final Hashtable actual = ParME.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


}

