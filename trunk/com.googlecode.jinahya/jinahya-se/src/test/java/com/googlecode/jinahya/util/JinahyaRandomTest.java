/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.util;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class JinahyaRandomTest {


    private static void testNextBytesWithLength(final JinahyaRandom random,
                                                final int length) {

        final byte[] bytes = random.nextBytes(length);

        Assert.assertEquals(bytes.length, length);
    }


    private static void testNextBytesWithRange(final JinahyaRandom random,
                                               final int minimumLength,
                                               final int maximumLength) {

        final byte[] bytes = random.nextBytes(minimumLength, maximumLength);

        Assert.assertTrue(bytes.length >= minimumLength);
        Assert.assertTrue(bytes.length <= maximumLength);
    }


    @Test
    public void testConstructor() {

        try {
            new JinahyaRandom(null);
            Assert.fail("passed: JinahyaRandom(null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }


    @Test
    public void testNextIntWithRange() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextInt(-1, 1);
            Assert.fail("passed: nextInt(-1, 1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextInt(0, -1);
            Assert.fail("passed: nextInt(0, -1)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextInt(0, 0);
        random.nextInt(1, 1);

        final int length =
            ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);

        random.nextInt(length, length);
    }


    @Test
    public void testNextBytesWithLength() {

        final JinahyaRandom random = new JinahyaRandom(new Random());

        try {
            random.nextBytes(-1);
            Assert.fail("passed: nextBytes(-1)");
        } catch (IllegalArgumentException iae) {
        }

        testNextBytesWithLength(random, 0);
        testNextBytesWithLength(random, 1);
        testNextBytesWithLength(random, 128);
    }


    @Test
    public void testNextBytesWithRange() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextBytes(-1, 1);
            Assert.fail("passed: nextBytes(-1, )");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextBytes(0, -1);
            Assert.fail("passed: nextBytes(, -1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        testNextBytesWithRange(random, 0, 1);
        testNextBytesWithRange(random, 1, 1);
        testNextBytesWithRange(random, 1, 2);

        final int minimumLength = ThreadLocalRandom.current().nextInt(128);
        final int maximumLength =
            ThreadLocalRandom.current().nextInt(128) + minimumLength;

        testNextBytesWithRange(random, minimumLength, maximumLength);
    }


    @Test
    public void testNextUnsignedInt() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextUnsignedInt(-1);
            Assert.fail("passed: nextUnsignedInt(-1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedInt(0);
            Assert.fail("passed: nextUnsignedInt(0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedInt(Integer.SIZE);
            Assert.fail("passed: nextUnsignedInt(Integer.SIZE)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedInt(Integer.SIZE + 1);
            Assert.fail("passed: nextUnsignedInt(Integer.SIZE + )");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        random.nextUnsignedInt(1); // minimum bit length
        random.nextUnsignedInt(31); // maximum bit length

        final int bitLength =
            ThreadLocalRandom.current().nextInt(31) + 1; // 1 - 31

        final int unsignedInt = random.nextUnsignedInt(bitLength);

        Assert.assertTrue(unsignedInt >= 0);
    }


    @Test
    public void testNextUnsignedIntWithRange() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextUnsignedInt(0, 0);
            Assert.fail("passed: nextUnsignedInt(-1, 0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedInt(1, 0);
            Assert.fail("passed: nextUnsignedInt(0, -1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedInt(1, 32);
            Assert.fail("passed: nextUnsignedInt(0, 32");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        random.nextUnsignedInt(1, 1);
        random.nextUnsignedInt(1, 31);
        random.nextUnsignedInt(31, 31);

        final int minimumBitLength =
            ThreadLocalRandom.current().nextInt(31) + 1;
        Assert.assertTrue(minimumBitLength > 0);
        Assert.assertTrue(minimumBitLength < 32);

        final int maximumBitLength = random.nextInt(minimumBitLength, 31);

        final int nextUnsignedInt =
            random.nextUnsignedInt(minimumBitLength, maximumBitLength);

        Assert.assertTrue(nextUnsignedInt >= 0);
    }


    @Test
    public void testNextSignedInt() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextSignedInt(-1);
            Assert.fail("passed: nextSignedInt(-1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedInt(0);
            Assert.fail("passed: nextSignedInt(0)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedInt(1);
            Assert.fail("passed: nextSignedInt(1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedInt(Integer.SIZE + 1);
            Assert.fail("passed: nextUnsignedInt(Integer.SIZE + 1)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextSignedInt(2); // minimum bit length
        random.nextSignedInt(32); // maximum bit length

        final int bitLength =
            ThreadLocalRandom.current().nextInt(31) + 2; // 2 - 32

        final int nextSignedInt = random.nextSignedInt(bitLength);
        if (bitLength < Integer.SIZE) {
            if (nextSignedInt >= 0) {
                Assert.assertTrue(nextSignedInt >> bitLength == 0);
            } else {
                Assert.assertTrue(nextSignedInt >> bitLength == -1);
            }
        }
    }


    @Test(invocationCount = 128)
    public void testNextSignedIntWithRange() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextSignedInt(1, 1);
            Assert.fail("passed: nextSignedInt(1, 1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedInt(2, 1);
            Assert.fail("passed: nextSignedInt(2, 1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedInt(2, 33);
            Assert.fail("passed: nextSignedInt(2, 33)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextSignedInt(2, 2);
        random.nextSignedInt(2, 32);
        random.nextSignedInt(32, 32);

        final int minimumBitLength =
            ThreadLocalRandom.current().nextInt(31) + 2;
        Assert.assertTrue(minimumBitLength > 1);
        Assert.assertTrue(minimumBitLength <= Integer.SIZE);

        final int maximumBitLength =
            minimumBitLength == Integer.SIZE ? minimumBitLength
            : ThreadLocalRandom.current().nextInt(33 - minimumBitLength)
              + minimumBitLength;
        Assert.assertTrue(maximumBitLength >= minimumBitLength);
        Assert.assertTrue(maximumBitLength <= Integer.SIZE);

        final int nextSignedInt =
            random.nextSignedInt(minimumBitLength, maximumBitLength);
        if (maximumBitLength < Integer.SIZE) {
            if (nextSignedInt >= 0) {
                Assert.assertTrue(nextSignedInt >> maximumBitLength == 0);
            } else {
                Assert.assertTrue(nextSignedInt >> maximumBitLength == -1);
            }
        }
    }


    @Test
    public void testUnsignedLong() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextUnsignedLong(-1);
            Assert.fail("passed: nextUnsignedInt(-1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextUnsignedLong(0);
            Assert.fail("passed: nextUnsignedInt(0)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextUnsignedLong(64);
            Assert.fail("passed: nextUnsignedInt(64)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextUnsignedLong(65);
            Assert.fail("passed: nextUnsignedInt(65)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextUnsignedLong(1); // minimum bit length
        random.nextUnsignedLong(63); // maximum bit length

        final int bitLength =
            ThreadLocalRandom.current().nextInt(63) + 1; // 1 - 63

        final long unsignedLong = random.nextUnsignedLong(bitLength);

        Assert.assertTrue(unsignedLong >= 0L);
    }


    @Test
    public void testNextSignedLong() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextSignedLong(-1);
            Assert.fail("passed: nextSignedLong(-1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedLong(0);
            Assert.fail("passed: nextSignedLong(0)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedLong(1);
            Assert.fail("passed: nextSignedLong(1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedLong(65);
            Assert.fail("passed: nextUnSignedLong(65)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextSignedLong(2); // minimum bit length
        random.nextSignedLong(64); // maximum bit length

        final int bitLength = ThreadLocalRandom.current().nextInt(63) + 2;
        Assert.assertTrue(bitLength > 1);
        Assert.assertTrue(bitLength <= Long.SIZE);

        final long nextSignedLong = random.nextSignedLong(bitLength);

        if (bitLength < Long.SIZE) {
            if (nextSignedLong >= 0L) {
                Assert.assertTrue(nextSignedLong >> bitLength == 0L);
            } else {
                Assert.assertTrue(nextSignedLong >> bitLength == -1L);
            }
        }
    }


    @Test(invocationCount = 128)
    public void testNextSignedLongWithRange() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextSignedLong(1, 1);
            Assert.fail("passed: nextSignedLong(1, 1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedLong(2, 1);
            Assert.fail("passed: nextSignedLong(2, 1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextSignedLong(2, 65);
            Assert.fail("passed: nextSignedLong(2, 65)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextSignedLong(2, 2);
        random.nextSignedLong(2, 64);
        random.nextSignedLong(64, 64);

        final int minimumBitLength =
            ThreadLocalRandom.current().nextInt(63) + 2;
        Assert.assertTrue(minimumBitLength > 1);
        Assert.assertTrue(minimumBitLength <= Long.SIZE);

        final int maximumBitLength =
            minimumBitLength == Long.SIZE ? minimumBitLength
            : ThreadLocalRandom.current().nextInt(65 - minimumBitLength)
              + minimumBitLength;
        Assert.assertTrue(maximumBitLength >= minimumBitLength);
        Assert.assertTrue(maximumBitLength <= Long.SIZE);

        final long nextSignedLong =
            random.nextSignedLong(minimumBitLength, maximumBitLength);

        if (maximumBitLength < Long.SIZE) {
            if (nextSignedLong >= 0L) {
                Assert.assertTrue(nextSignedLong >> maximumBitLength == 0L);
            } else {
                Assert.assertTrue(nextSignedLong >> maximumBitLength == -1L);
            }
        }
    }


}

