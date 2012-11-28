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


    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testEncodeWithNull() {
        ParME.encode(null);
    }


    @Test
    public static void testEncodeWithEmpty() {
        ParME.encode(new Hashtable());
    }


//    //@Test(invocationCount = 128)
//    public static void testEncode() {
//
//        final Hashtable<String, String> decoded =
//            new Hashtable<String, String>();
//
//        final int count = RANDOM.nextInt(64);
//        for (int i = 0; i < count; i++) {
//            decoded.put(RandomStringUtils.random(RANDOM.nextInt(32)),
//                        RandomStringUtils.random(RANDOM.nextInt(32)));
//        }
//
//        ParME.encode(decoded);
//    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testDecodeWithNull() {
        ParME.decode(null);
    }


    @Test
    public static void testDecodeWithEmpty() {
        ParME.decode("");
    }


    @Test
    public static void testDecodeForValidEncoded() {

        for (String valid : ParTest.VALID_ENCODED) {
            ParME.decode(valid);
        }
    }


    @Test
    public static void testDecodeForInvalidEncoded() {

        for (String invalid : ParTest.INVALID_ENCODED) {
            try {
                ParME.decode(invalid);
                Assert.fail("passed: decode(\"" + invalid + "\")");
            } catch (IllegalArgumentException iae) {
                // expected
            }
        }
    }


    @Test
    public static void testEncodeDecode() {

        final Hashtable expected = new Hashtable();

        final int count = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < count; i++) {
            expected.put(RandomStringUtils.random(RANDOM.nextInt(128)),
                         RandomStringUtils.random(RANDOM.nextInt(128)));
        }

        final String encoded = ParME.encode(expected);

        final Hashtable actual = ParME.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public static void testEncodingAgainstSE() {

        final Hashtable expected = new Hashtable();

        final int count = RANDOM.nextInt(128);
        for (int i = 0; i < count; i++) {
            expected.put(RandomStringUtils.random(RANDOM.nextInt(128)),
                         RandomStringUtils.random(RANDOM.nextInt(128)));
        }

        final String encoded = ParME.encode(expected);

        final Map<String, String> actual = Par.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public static void testDecodingAgainstSE() {

        final Map<String, String> expected = new HashMap<String, String>();

        final int count = RANDOM.nextInt(128);
        for (int i = 0; i < count; i++) {
            expected.put(RandomStringUtils.random(RANDOM.nextInt(128)),
                         RandomStringUtils.random(RANDOM.nextInt(128)));
        }

        final String encoded = Par.encode(expected);

        final Hashtable actual = ParME.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


}

