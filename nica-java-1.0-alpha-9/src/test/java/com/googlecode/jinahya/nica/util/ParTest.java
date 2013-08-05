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


import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ParTest {


    protected static final String[] VALID_ENCODED = new String[]{
        "",
        "=",
        "a=",
        "a=&=",
        "a=b",
        "a=b&b=c"
    };


    protected static final String[] INVALID_ENCODED = new String[]{
        "&",
        "a",
        "a&",
        "=&",
        "=&=",
        "a=&",
        "a=&a="
    };


    @Test(expectedExceptions = NullPointerException.class)
    public void testEncodeWithNull() {

        Par.encode(null);
    }


    @Test
    public void testEncodeWithEmpty() {

        Assert.assertTrue(
            Par.encode(Collections.<String, String>emptyMap()).isEmpty());
    }


    @Test
    public static void testDecodeForValidEncoded() {

        for (String valid : VALID_ENCODED) {
            Par.decode(valid);
        }
    }


    @Test
    public static void testDecodeForInvalidEncoded() {

        for (String invalid : INVALID_ENCODED) {
            try {
                Par.decode(invalid);
                Assert.fail("passed: decode(\"" + invalid + "\")");
            } catch (IllegalArgumentException iae) {
                // expected
            }
        }
    }


    @Test
    public static void testDemicroForValidEncoded() {

        for (String valid : VALID_ENCODED) {
            Par.demicro(valid);
        }
    }


    @Test
    public static void testDemicroForInvalidEncoded() {

        for (String invalid : INVALID_ENCODED) {
            try {
                Par.demicro(invalid);
                Assert.fail("passed: decode(\"" + invalid + "\")");
            } catch (IllegalArgumentException iae) {
                // expected
            }
        }
    }


    @Test(invocationCount = 32)
    public void testEncodeDecode() {

        final Random random = ThreadLocalRandom.current();

        final Map<String, String> expected = new HashMap<String, String>();

        final int pairCount = random.nextInt(128) + 1;
        for (int i = 0; i < pairCount; i++) {
            expected.put(RandomStringUtils.random(random.nextInt(128)),
                         RandomStringUtils.random(random.nextInt(128)));
        }

        final String encoded = Par.encode(expected);

        @SuppressWarnings("unchecked")
        final Map<String, String> actual = Par.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    @SuppressWarnings("unchecked")
    public void encodeDecode() {

        System.out.println("----------------------------------- encode/decode");

        final long start = System.nanoTime();

        final Map expected = new HashMap();

        expected.put("English", "love");
        expected.put("한국어", "사랑");
        expected.put("中國語", "愛");

        System.out.println("expected: " + expected);

        final String encoded = Par.encode(expected);
        System.out.println("encoded: " + encoded);

        final Map actual = Par.decode(encoded);
        System.out.println("actual: " + actual);

        Assert.assertEquals(actual, expected);

        final long finish = System.nanoTime();
        System.out.println("encode/decode: " + (finish - start) + " ns");
    }


    @Test
    @SuppressWarnings("unchecked")
    public void enmicroDemicro() {

        System.out.println("--------------------------------- enmicro/demicro");

        final long start = System.nanoTime();

        final Hashtable expected = new Hashtable();

        expected.put("English", "love");
        expected.put("한국어", "사랑");
        expected.put("中國語", "愛");

        System.out.println("expected: " + expected);

        final String encoded = Par.enmicro(expected);
        System.out.println("encoded: " + encoded);

        final Hashtable actual = Par.demicro(encoded);
        System.out.println("actual: " + actual);

        Assert.assertEquals(actual, expected);

        final long finish = System.nanoTime();
        System.out.println("enmicro/demicro: " + (finish - start) + " ns");
    }


}
