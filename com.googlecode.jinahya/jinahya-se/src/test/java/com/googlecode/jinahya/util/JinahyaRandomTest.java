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


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
    public void testConstructor() throws NoSuchAlgorithmException {

        try {
            new JinahyaRandom(null);
            Assert.fail("passed: JinahyaRandom(null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        new JinahyaRandom(new Random());
        new JinahyaRandom(SecureRandom.getInstance("SHA1PRNG"));
        new JinahyaRandom(ThreadLocalRandom.current());
    }


    @Test
    public void testNextIntWithRange() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextInt(-1, -1);
            Assert.fail("passed: nextInt(-1, -1)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextInt(0, -1);
            Assert.fail("passed: nextInt(0, -1)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextInt(0, 0);
        random.nextInt(1, 1);
        random.nextInt(Integer.MAX_VALUE, Integer.MAX_VALUE);

        final int length =
            ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);

        random.nextInt(length, length);
    }


    @Test(invocationCount = 128)
    public void testNextBytesWithLength() {

        final JinahyaRandom random = new JinahyaRandom(new Random());

        try {
            random.nextBytes(-1);
            Assert.fail("passed: nextBytes(-1)");
        } catch (IllegalArgumentException iae) {
        }

        testNextBytesWithLength(random, 0);
        testNextBytesWithLength(random, 1);

        final int length = ThreadLocalRandom.current().nextInt(1024);
        testNextBytesWithLength(random, length);
    }


    @Test(invocationCount = 128)
    public void testNextBytesWithRange() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextBytes(-1, -1);
            Assert.fail("passed: nextBytes(-1, -1)");
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

        final int minimumLength = ThreadLocalRandom.current().nextInt(128);
        final int maximumLength =
            ThreadLocalRandom.current().nextInt(128) + minimumLength;
        testNextBytesWithRange(random, minimumLength, maximumLength);
    }


    @Test(invocationCount = 128)
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

        random.nextUnsignedInt(1);
        random.nextUnsignedInt(Integer.SIZE - 1);

        final int bitLength = ThreadLocalRandom.current().nextInt(31) + 1;
        Assert.assertTrue(bitLength >= 1);
        Assert.assertTrue(bitLength < Integer.SIZE);

        final int unsignedInt = random.nextUnsignedInt(bitLength);
        Assert.assertTrue(unsignedInt >= 0);
        Assert.assertTrue(unsignedInt >> bitLength == 0);
    }


    @Test(invocationCount = 128)
    public void testNextUnsignedIntWithRange() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextUnsignedInt(0, 0);
            Assert.fail("passed: nextUnsignedInt(0, 0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedInt(1, 0);
            Assert.fail("passed: nextUnsignedInt(1, 0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedInt(1, Integer.SIZE);
            Assert.fail("passed: nextUnsignedInt(1, Integer.SIZE");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        random.nextUnsignedInt(1, 1);
        random.nextUnsignedInt(1, Integer.SIZE - 1);
        random.nextUnsignedInt(Integer.SIZE - 1, Integer.SIZE - 1);

        final int minimumBitLength =
            ThreadLocalRandom.current().nextInt(31) + 1;
        Assert.assertTrue(minimumBitLength > 0);
        Assert.assertTrue(minimumBitLength < Integer.SIZE);

        final int maximumBitLength =
            ThreadLocalRandom.current().nextInt(
            Integer.SIZE - minimumBitLength) + minimumBitLength;
        Assert.assertTrue(maximumBitLength >= minimumBitLength);
        Assert.assertTrue(maximumBitLength < Integer.SIZE);

        final int unsignedInt =
            random.nextUnsignedInt(minimumBitLength, maximumBitLength);
        Assert.assertTrue(unsignedInt >= 0);
        Assert.assertTrue(unsignedInt >> maximumBitLength == 0);
    }


    @Test(invocationCount = 128)
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
            random.nextSignedInt(Integer.SIZE + 1); // 33
            Assert.fail("passed: nextUnsignedInt(Integer.SIZE + 1)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextSignedInt(2);
        random.nextSignedInt(Integer.SIZE);

        final int bitLength =
            ThreadLocalRandom.current().nextInt(Integer.SIZE - 1) + 2;
        Assert.assertTrue(bitLength > 1);
        Assert.assertTrue(bitLength <= Integer.SIZE);

        final int signedInt = random.nextSignedInt(bitLength);
        if (bitLength < Integer.SIZE) {
            if (signedInt >= 0) {
                Assert.assertTrue(signedInt >> bitLength == 0);
            } else {
                Assert.assertTrue(signedInt >> bitLength == -1);
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
            random.nextSignedInt(2, Integer.SIZE + 1);
            Assert.fail("passed: nextSignedInt(2, Integer.SIZE + 1)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextSignedInt(2, 2);
        random.nextSignedInt(2, Integer.SIZE);
        random.nextSignedInt(Integer.SIZE, Integer.SIZE);

        final int minimumBitLength =
            ThreadLocalRandom.current().nextInt(Integer.SIZE - 1) + 2;
        Assert.assertTrue(minimumBitLength > 1);
        Assert.assertTrue(minimumBitLength <= Integer.SIZE);

        final int maximumBitLength =
            ThreadLocalRandom.current().nextInt(
            Integer.SIZE + 1 - minimumBitLength) + minimumBitLength;
        Assert.assertTrue(maximumBitLength >= minimumBitLength);
        Assert.assertTrue(maximumBitLength <= Integer.SIZE);

        final int signedInt =
            random.nextSignedInt(minimumBitLength, maximumBitLength);
        if (maximumBitLength < Integer.SIZE) {
            if (signedInt >= 0) {
                Assert.assertTrue(signedInt >> maximumBitLength == 0);
            } else {
                Assert.assertTrue(signedInt >> maximumBitLength == -1);
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
            random.nextUnsignedLong(Long.SIZE);
            Assert.fail("passed: nextUnsignedInt(Long.SIZE)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextUnsignedLong(1);
        random.nextUnsignedLong(Long.SIZE - 1); // 63

        final int bitLength =
            ThreadLocalRandom.current().nextInt(Long.SIZE - 1) + 1;
        Assert.assertTrue(bitLength >= 1);
        Assert.assertTrue(bitLength < Long.SIZE);

        final long unsignedLong = random.nextUnsignedLong(bitLength);

        Assert.assertTrue(unsignedLong >= 0L);
        Assert.assertEquals(unsignedLong >> bitLength, 0L);
    }


    @Test
    public void testNextSignedLong() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextSignedLong(0);
            Assert.fail("passed: nextSignedLong(0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextSignedLong(1);
            Assert.fail("passed: nextSignedLong(1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextSignedLong(Long.SIZE + 1);
            Assert.fail("passed: nextUnSignedLong(Long.SIZE + 1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        random.nextSignedLong(2);
        random.nextSignedLong(Long.SIZE);

        final int bitLength =
            ThreadLocalRandom.current().nextInt(Long.SIZE - 1) + 2;
        Assert.assertTrue(bitLength > 1);
        Assert.assertTrue(bitLength <= Long.SIZE);

        final long signedLong = random.nextSignedLong(bitLength);

        if (bitLength < Long.SIZE) {
            if (signedLong >= 0L) {
                Assert.assertTrue(signedLong >> bitLength == 0L);
            } else {
                Assert.assertTrue(signedLong >> bitLength == -1L);
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
            // expected
        }

        try {
            random.nextSignedLong(2, Long.SIZE + 1);
            Assert.fail("passed: nextSignedLong(2, Long.SIZE + 1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        random.nextSignedLong(2, 2);
        random.nextSignedLong(2, Long.SIZE);
        random.nextSignedLong(Long.SIZE, Long.SIZE);

        final int minimumBitLength =
            ThreadLocalRandom.current().nextInt(Long.SIZE - 1) + 2;
        Assert.assertTrue(minimumBitLength > 1);
        Assert.assertTrue(minimumBitLength <= Long.SIZE);

        final int maximumBitLength =
            ThreadLocalRandom.current().nextInt(
            Long.SIZE + 1 - minimumBitLength) + minimumBitLength;
        Assert.assertTrue(maximumBitLength >= minimumBitLength);
        Assert.assertTrue(maximumBitLength <= Long.SIZE);

        final long signedLong =
            random.nextSignedLong(minimumBitLength, maximumBitLength);
        if (maximumBitLength < Long.SIZE) {
            if (signedLong >= 0L) {
                Assert.assertTrue(signedLong >> maximumBitLength == 0L);
            } else {
                Assert.assertTrue(signedLong >> maximumBitLength == -1L);
            }
        }
    }


    @Test
    public void testNextUnsignedLong() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextUnsignedLong(0);
            Assert.fail("passed: nextUnsignedLong(0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedLong(Long.SIZE);
            Assert.fail("passed: nextUnsignedLong(Long.SIZE)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        random.nextUnsignedLong(1);
        random.nextUnsignedLong(Long.SIZE - 1);

        final int bitLength =
            ThreadLocalRandom.current().nextInt(Long.SIZE - 1) + 1;
        Assert.assertTrue(bitLength >= 1);
        Assert.assertTrue(bitLength < Long.SIZE);

        final long unsignedLong = random.nextUnsignedLong(bitLength);
        Assert.assertTrue(unsignedLong >= 0L);
        Assert.assertTrue(unsignedLong >> bitLength == 0L);
    }


    @Test(invocationCount = 128)
    public void testNextUnsignedLongWithRange() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextUnsignedLong(0, 0);
            Assert.fail("passed: nextUnsignedLong(0, 0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedLong(1, 0);
            Assert.fail("passed: nextUnsignedLong(1, 0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextUnsignedLong(1, Long.SIZE);
            Assert.fail("passed: nextUnsignedLong(0, Long.SIZE");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        random.nextUnsignedLong(1, 1);
        random.nextUnsignedLong(1, Long.SIZE - 1);
        random.nextUnsignedLong(Long.SIZE - 1, Long.SIZE - 1);

        final int minimumBitLength =
            ThreadLocalRandom.current().nextInt(Long.SIZE - 1) + 1;
        Assert.assertTrue(minimumBitLength >= 1);
        Assert.assertTrue(minimumBitLength < Long.SIZE);

        final int maximumBitLength =
            ThreadLocalRandom.current().nextInt(Long.SIZE - minimumBitLength)
            + minimumBitLength;
        Assert.assertTrue(maximumBitLength >= minimumBitLength);
        Assert.assertTrue(maximumBitLength < Long.SIZE);

        final long unsignedLong =
            random.nextUnsignedLong(minimumBitLength, maximumBitLength);

        Assert.assertTrue(unsignedLong >= 0L);
        Assert.assertTrue(unsignedLong >> maximumBitLength == 0L);
    }


}

