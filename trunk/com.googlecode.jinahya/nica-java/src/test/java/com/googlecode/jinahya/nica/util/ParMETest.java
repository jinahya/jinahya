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
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
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


    @Test(expectedExceptions = IllegalArgumentException.class)
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


    @Test(expectedExceptions = IllegalArgumentException.class)
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


    protected static Hashtable newSingleValued() {

        final Hashtable singleValued = new Hashtable();

        final int count = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < count; i++) {
            singleValued.put(RandomStringUtils.random(RANDOM.nextInt(128)),
                             RandomStringUtils.random(RANDOM.nextInt(128)));
        }

        return singleValued;
    }


    protected static Vector newValues() {

        final Vector values = new Vector();

        final int count = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < count; i++) {
            values.add(RandomStringUtils.random(RANDOM.nextInt(128)));
        }

        return values;
    }


    protected static Hashtable newMultiValued() {

        final Hashtable multiValued = new Hashtable();

        final int count = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < count; i++) {
            multiValued.put(RandomStringUtils.random(RANDOM.nextInt(128)),
                            newValues());
        }

        return multiValued;
    }


    @Test(invocationCount = 128)
    public static void testEncodeDecode() {

        final Hashtable expected = newSingleValued();

        final String encoded = ParME.encode(expected);

        final Hashtable actual = ParME.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public static void testEncodingAgainstSE() {

        final Hashtable expected = newSingleValued();

        final String encoded = ParME.encode(expected);

        final Map<String, String> actual = Par.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public static void testDecodingAgainstSE() {

        final Map<String, String> expected = ParTest.newSingleValued();

        final String encoded = Par.encode(expected);

        final Hashtable actual = ParME.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public static void testEncodeDecodeValues() {

        final Vector expected = newValues();

        final String encoded = ParME.encodeValues(expected);

        final Vector actual = ParME.decodeValues(encoded);

        Collections.sort(expected);
        Collections.sort(actual);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public static void testEncodeDecodeMultivalued() {

        final Hashtable expected = newMultiValued();

        final String encoded = ParME.encodeMultivalued(expected);

        final Hashtable actual = ParME.decodeMultivalued(encoded);

        Assert.assertEquals(actual.keySet(), expected.keySet());

        for (Object key : actual.keySet()) {
            Collections.sort((Vector) expected.get(key));
            Collections.sort((Vector) actual.get(key));
            Assert.assertEquals(actual.get(key), expected.get(key));
        }
    }


    @Test(invocationCount = 128)
    public static void testEncodeMultivaluedAgainstSE() {

        final Hashtable expected = newMultiValued();

        final String encoded = ParME.encodeMultivalued(expected);

        final Map<String, List<String>> actual =
            Par.decodeMultiValued(encoded);

        Assert.assertEquals(actual.keySet(), expected.keySet());

        for (String key : actual.keySet()) {
            Collections.sort((Vector) expected.get(key));
            Collections.sort(actual.get(key));
            Assert.assertEquals(actual.get(key), expected.get(key));
        }
    }


    @Test(invocationCount = 128)
    public static void testDecodeMultivaluedAgainstSE() {

        final Map<String, List<String>> expected = ParTest.newMultiValued();

        final String encoded = Par.encodeMultivalued(expected);

        final Hashtable actual = ParME.decodeMultivalued(encoded);

        Assert.assertEquals(actual.keySet(), expected.keySet());

        for (String key : expected.keySet()) {
            Collections.sort(expected.get(key));
            Collections.sort((Vector) actual.get(key));
            Assert.assertEquals(actual.get(key), expected.get(key));
        }
    }


}

