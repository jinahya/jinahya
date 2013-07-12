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


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ParTest {


    private static final Random RANDOM = new Random();


    protected static final String[] VALID_ENCODED = new String[]{
        "=",
        "a=",
        "a=&=",
        "a=b",
        "a=b&b=c"
    };


    protected static final String[] INVALID_ENCODED = new String[]{
        "",
        "&",
        "a",
        "a&",
        "=&",
        "=&=",
        "a=&",
        "a=&a="
    };


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEncodeWithNull() {
        Par.encodeValues(null);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEncodeWithEmpty() {
        Par.encode(Collections.<String, String>emptyMap());
    }


//    //@Test(invocationCount = 32)
//    public void testEncode() {
//
////        System.out.println(Par.WORD);
////        System.out.println(Par.PAIR);
////        System.out.println(Par.REGEX);
//
//        final Map<String, String> expected = new HashMap<String, String>();
//
//        final int count = RANDOM.nextInt(64);
//        for (int i = 0; i < count; i++) {
//            expected.put(RandomStringUtils.random(RANDOM.nextInt(32)),
//                         RandomStringUtils.random(RANDOM.nextInt(32)));
//        }
//
//        final String encoded = Par.encode(expected);
//
//        final boolean matches = Par.PATTERN.matcher(encoded).matches();
//
//        Assert.assertTrue(Par.PATTERN.matcher(encoded).matches());
//    }
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


    @Test(invocationCount = 32)
    public void testEncodeDecode() {

        final Map<String, String> expected = new HashMap<String, String>();

        final int pairCount = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < pairCount; i++) {
            expected.put(RandomStringUtils.random(RANDOM.nextInt(128)),
                         RandomStringUtils.random(RANDOM.nextInt(128)));
        }

        final String encoded = Par.encode(expected);

        final Map<String, String> actual = Par.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    protected static Map<String, String> newSingleValued() {

        final Map<String, String> singleValued = new HashMap<String, String>();

        final int count = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < count; i++) {
            singleValued.put(RandomStringUtils.random(RANDOM.nextInt(128)),
                             RandomStringUtils.random(RANDOM.nextInt(128)));
        }

        return singleValued;
    }


    protected static List<String> newValues() {

        final List<String> values = new ArrayList<String>();

        final int count = RANDOM.nextInt(128) + 1;
        for (int j = 0; j < count; j++) {
            values.add(RandomStringUtils.random(RANDOM.nextInt(128)));
        }

        return values;
    }


    protected static Map<String, List<String>> newMultiValued() {

        final Map<String, List<String>> multiValued =
            new HashMap<String, List<String>>();

        final int count = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < count; i++) {
            multiValued.put(RandomStringUtils.random(RANDOM.nextInt(128)),
                            newValues());
        }

        return multiValued;
    }


    @Test(invocationCount = 32)
    public static void testEncodeDecodeValues() {

        final List<String> expected = newValues();
        final String encoded = Par.encodeValues(expected);
        final List<String> actual = Par.decodeValues(encoded);

        Collections.sort(expected);
        Collections.sort(actual);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 32)
    public void testEncodeDecodeMultivalued() {

        final Map<String, List<String>> expected =
            new HashMap<String, List<String>>();

        final int pairCount = RANDOM.nextInt(128);
        for (int i = 0; i < pairCount; i++) {
            final String key = RandomStringUtils.random(RANDOM.nextInt(128));
            final List<String> values = newValues();
            expected.put(key, values);
        }

        final String encoded = Par.encodeMultivalued(expected);

        final Map<String, List<String>> actual = Par.decodeMultiValued(encoded);

        Assert.assertEquals(actual.keySet(), expected.keySet());

        for (String key : actual.keySet()) {
            Collections.sort(actual.get(key));
            Collections.sort(expected.get(key));
            Assert.assertEquals(actual.get(key), expected.get(key));
        }
    }


    @Test
    public void love() {

        final Map<String, String> expected = new HashMap<String, String>();

        expected.put("English", "love");
        expected.put("한국어", "사랑");

        final String encoded = Par.encode(expected);
        System.out.println(encoded);
    }


    @Test
    public void testFast() {

        final Map<String, String> expected = new HashMap<>();

        expected.put("English", "love");
        expected.put("한국어", "사랑");
        expected.put("中國語", "愛");

        System.out.println("expected: " + expected);

        final String encoded = Par.encode(expected);
        System.out.println("encoded: " + encoded);

        final Map<String, String> actual = Par.decode(encoded);
        System.out.println("actual: " + actual);

        Assert.assertEquals(actual, expected);
    }


}
