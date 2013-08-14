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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class JinahyaRandomTest {


    private static final Logger LOGGER =
        Logger.getLogger(JinahyaRandomTest.class.getName());


    private static void testNextIntMinMax(final JinahyaRandom random,
                                          final int minimum,
                                          final int maximum) {

        LOGGER.log(Level.INFO, "testNextIntMinMax({0}, {1}, {2})",
                   new Object[]{random, minimum, maximum});

        boolean min = false;
        boolean max = false;
        while (!min || !max) {
            final int nextInt = random.nextInt(minimum, maximum);
            if (!min && nextInt == minimum) {
                LOGGER.log(Level.INFO, "minimum generated: {0}", nextInt);
                min = true;
            }
            if (!max && nextInt == maximum - 1) {
                LOGGER.log(Level.INFO, "maximum generated: {0}", nextInt);
                max = true;
            }
        }
    }


    private static void testNextBytes(final JinahyaRandom random,
                                      final int minimumLength,
                                      final int maximumLength) {

        final byte[] bytes = random.nextBytes(minimumLength, maximumLength);

        Assert.assertTrue(bytes.length >= minimumLength);
        Assert.assertTrue(bytes.length < maximumLength);
    }


    @Test
    public void testConstructor() throws NoSuchAlgorithmException {

        try {
            new JinahyaRandom(null);
            Assert.fail("passed: JinahyaRandom(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        JinahyaRandom random;
        random = new JinahyaRandom(new Random());
        random = new JinahyaRandom(new SecureRandom());
        random = new JinahyaRandom(ThreadLocalRandom.current());
    }


    @Test
    public void testNextIntWithToWideRange() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        random.nextInt(Integer.MIN_VALUE, -1); // ok

        try {
            random.nextInt(Integer.MIN_VALUE, 0);
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        } catch (IllegalArgumentException iae) {
            // expected
        }

        random.nextInt(0, Integer.MAX_VALUE);

        try {
            random.nextInt(-1, Integer.MAX_VALUE);
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }


    @Test
    public void testNextInt() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextInt(0, -1);
            Assert.fail("passed: nextInt(0, -1)");
        } catch (IllegalArgumentException iae) {
        }

        random.nextInt(Integer.MIN_VALUE, Integer.MIN_VALUE);
        random.nextInt(-1, -1);
        random.nextInt(0, 0);
        random.nextInt(1, 1);
        random.nextInt(Integer.MAX_VALUE, Integer.MAX_VALUE);

        final int length =
            ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);

        random.nextInt(length, length);

        testNextIntMinMax(random, Integer.MIN_VALUE, Integer.MIN_VALUE + 200);
        testNextIntMinMax(random, -100, 100);
        testNextIntMinMax(random, Integer.MAX_VALUE - 200, Integer.MAX_VALUE);
    }


    @Test
    public void testNextUnsignedIntWithWrongArguments() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextUnsignedInt(0, 1);
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
    }


    @Test(invocationCount = 128)
    public void testNextUnsignedInt() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

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

        final int nextUnsignedInt =
            random.nextUnsignedInt(minimumBitLength, maximumBitLength);
        Assert.assertTrue(nextUnsignedInt >= 0);
        Assert.assertTrue(nextUnsignedInt >> maximumBitLength == 0);
    }


    @Test
    public void testNextSignedIntWithWrongArguments() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

        try {
            random.nextSignedInt(1, 1);
            Assert.fail("passed: nextSignedInt(1, 1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextSignedInt(2, 1);
            Assert.fail("passed: nextSignedInt(2, 1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            random.nextSignedInt(2, Integer.SIZE + 1);
            Assert.fail("passed: nextSignedInt(2, Integer.SIZE + 1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }


    @Test(invocationCount = 128)
    public void testNextSignedInt() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

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
    public void testNextSignedLongWithWrongArguments() {

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
    }


    @Test(invocationCount = 128)
    public void testNextSignedLong() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

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
    public void testNextUnsignedLongWithWrongArguments() {

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
    }


    @Test(invocationCount = 128)
    public void testNextUnsignedLong() {

        final JinahyaRandom random =
            new JinahyaRandom(ThreadLocalRandom.current());

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

