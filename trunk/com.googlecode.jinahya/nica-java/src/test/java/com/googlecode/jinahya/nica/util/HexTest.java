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


import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexTest {


    protected static final Map<String, String> RFC_4648_BASE16_TEST_VECTOR;


    static {
        final Map<String, String> map = new HashMap<>(7);
        map.put("", "");
        map.put("f", "66");
        map.put("fo", "666F");
        map.put("foo", "666F6F");
        map.put("foob", "666F6F62");
        map.put("fooba", "666F6F6261");
        map.put("foobar", "666F6F626172");
        RFC_4648_BASE16_TEST_VECTOR = Collections.unmodifiableMap(map);
    }


    protected static String newDecodedString() {

        final Random random = ThreadLocalRandom.current();

        return RandomStringUtils.random(random.nextInt(65536));
    }


    protected static byte[] newDecodedBytes() {

        return newDecodedString().getBytes(StandardCharsets.UTF_8);
    }


    protected static String newEncodedString() {

        final byte[] decoded = newDecodedBytes();

        final StringBuilder builder = new StringBuilder(decoded.length * 2);

        for (byte b : decoded) {
            final String h = Integer.toHexString(b & 0xFF);
            if (h.length() == 1) {
                builder.append('0');
            }
            builder.append(h);
        }

        return builder.toString();
    }


    protected static byte[] newEncodedBytes() {

        return newEncodedString().getBytes(StandardCharsets.US_ASCII);
    }


    @Test
    public static void testEncodeDecode() {

        final byte[] expected = newDecodedBytes();

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


    @Test(invocationCount = 1)
    public static void encodeEnfast() {

        long start, finish;

        final DescriptiveStatistics enfasts = new DescriptiveStatistics();
        final DescriptiveStatistics encodes = new DescriptiveStatistics();

        final Random random = ThreadLocalRandom.current();

        for (int i = 0; i < 128; i++) {

            final String decoded = newDecodedString();

            if (random.nextBoolean()) {

                start = System.nanoTime();
                final String enfasted = Hex.enfastToString(decoded);
                finish = System.nanoTime();
                enfasts.addValue(finish - start);

                start = System.nanoTime();
                final String encoded = Hex.encodeToString(decoded);
                finish = System.nanoTime();
                encodes.addValue(finish - start);

                Assert.assertEquals(enfasted, encoded);

            } else {

                start = System.nanoTime();
                final String encoded = Hex.encodeToString(decoded);
                finish = System.nanoTime();
                encodes.addValue(finish - start);

                start = System.nanoTime();
                final String enfasted = Hex.enfastToString(decoded);
                finish = System.nanoTime();
                enfasts.addValue(finish - start);

                Assert.assertEquals(encoded, enfasted);

            }
        }

        System.out.println("enfasts: " + enfasts.getMean());
        System.out.println("encodes: " + encodes.getMean());
    }


    @Test(invocationCount = 1)
    public static void decodeDefast() {

        long start, finish;

        final DescriptiveStatistics defasts = new DescriptiveStatistics();
        final DescriptiveStatistics decodes = new DescriptiveStatistics();

        final Random random = ThreadLocalRandom.current();

        for (int i = 0; i < 128; i++) {

            final String encoded = newEncodedString();

            if (random.nextBoolean()) {

                start = System.nanoTime();
                final String defasted = Hex.defastToString(encoded);
                finish = System.nanoTime();
                defasts.addValue(finish - start);

                start = System.nanoTime();
                final String decoded = Hex.decodeToString(encoded);
                finish = System.nanoTime();
                decodes.addValue(finish - start);

                Assert.assertEquals(defasted, decoded);

            } else {

                start = System.nanoTime();
                final String decoded = Hex.decodeToString(encoded);
                finish = System.nanoTime();
                decodes.addValue(finish - start);

                start = System.nanoTime();
                final String defasted = Hex.defastToString(encoded);
                finish = System.nanoTime();
                defasts.addValue(finish - start);

                Assert.assertEquals(decoded, defasted);
            }
        }

        System.out.println("defasts: " + defasts.getMean());
        System.out.println("decodes: " + decodes.getMean());
    }


    @Test(invocationCount = 128)
    public static void encodeDefast() {

        final Random random = ThreadLocalRandom.current();
        final byte[] expected = new byte[random.nextInt(1024)];
        random.nextBytes(expected);

        final byte[] encoded = Hex.encode(expected);

        final byte[] actual = Hex.defast(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public static void enfastDecode() {

        final Random random = ThreadLocalRandom.current();
        final byte[] expected = new byte[random.nextInt(1024)];
        random.nextBytes(expected);

        final byte[] encoded = Hex.enfast(expected);

        final byte[] actual = Hex.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public static void testEncodeDecodeAgainstRFC4648Base16TextVector() {

        // encode
        for (Entry<String, String> e : RFC_4648_BASE16_TEST_VECTOR.entrySet()) {
            final String decoded = e.getKey();
            final String expected = e.getValue();
            final String actual = Hex.encodeToString(decoded);
            Assert.assertEquals(actual, expected);
        }

        // decode
        for (Entry<String, String> e : RFC_4648_BASE16_TEST_VECTOR.entrySet()) {
            final String encoded = e.getValue();
            final String expected = e.getKey();
            final String actual = Hex.decodeToString(encoded);
            Assert.assertEquals(actual, expected);
        }
    }


    @Test
    public static void testEnfastDefastAgainstRFC4648Base16TextVector() {

        // encode
        for (Entry<String, String> e : RFC_4648_BASE16_TEST_VECTOR.entrySet()) {
            final String decoded = e.getKey();
            final String expected = e.getValue();
            final String actual = Hex.enfastToString(decoded);
            Assert.assertEquals(actual, expected);
        }

        // decode
        for (Entry<String, String> e : RFC_4648_BASE16_TEST_VECTOR.entrySet()) {
            final String encoded = e.getValue();
            final String expected = e.getKey();
            final String actual = Hex.defastToString(encoded);
            Assert.assertEquals(actual, expected);
        }
    }


}
