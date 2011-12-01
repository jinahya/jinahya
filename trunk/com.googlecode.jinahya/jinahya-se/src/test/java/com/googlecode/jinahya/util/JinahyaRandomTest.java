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


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class JinahyaRandomTest {


    @Test(invocationCount = 128)
    public void testNextBytes() {

        final JinahyaRandom random = new JinahyaRandom();

        try {
            random.nextBytes(-1);
            Assert.fail("passed: nextBytes(-1)");
        } catch (IllegalArgumentException iae) {
        }

        final int length = random.nextInt(128); // 0 - 127
        Assert.assertEquals(random.nextBytes(length).length, length);

        try {
            random.nextBytes(-1, 0);
            Assert.fail("passed: nextBytes(-1, 0)");
        } catch (IllegalArgumentException iae) {
        }

        try {
            random.nextBytes(0, -1);
            Assert.fail("passed: nextBytes(0, -1)");
        } catch (IllegalArgumentException iae) {
        }

        final int minimumLength = random.nextInt(128);
        final int maximumLength = random.nextInt(128) + minimumLength;
        final byte[] nextBytes =
            random.nextBytes(minimumLength, maximumLength);
        Assert.assertTrue(nextBytes.length >= minimumLength);
        Assert.assertTrue(nextBytes.length <= maximumLength);
    }


    @Test(invocationCount = 128)
    public void testNextUnsignedInt() {

        final JinahyaRandom random = new JinahyaRandom();

        try {
            random.nextUnsignedInt(-1);
            Assert.fail("passed: nextUnsignedInt(-1)");
        } catch (IllegalArgumentException iae) {
        }
        try {
            random.nextUnsignedInt(0);
            Assert.fail("passed: nextUnsignedInt(0)");
        } catch (IllegalArgumentException iae) {
        }
        try {
            random.nextUnsignedInt(32);
            Assert.fail("passed: nextUnsignedInt(32)");
        } catch (IllegalArgumentException iae) {
        }
        try {
            random.nextUnsignedInt(33);
            Assert.fail("passed: nextUnsignedInt(33)");
        } catch (IllegalArgumentException iae) {
        }

        Assert.assertTrue(random.nextUnsignedInt() >= 0);

        final int maximumBits = random.nextInt(31) + 1; // 1 - 31
        Assert.assertTrue(random.nextUnsignedInt(maximumBits) >= 0);
    }


    @Test(invocationCount = 128)
    public void testNextSignedInt() {

        final JinahyaRandom random = new JinahyaRandom();

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
            random.nextSignedInt(33);
            Assert.fail("passed: nextUnsignedInt(33)");
        } catch (IllegalArgumentException iae) {
        }

        final int maximumBits = random.nextInt(31) + 2;
        final int nextSignedInt = random.nextSignedInt(maximumBits);
        if (maximumBits < Integer.SIZE) {
            if (nextSignedInt >= 0) {
                Assert.assertTrue(nextSignedInt >> maximumBits == 0);
            } else {
                Assert.assertTrue(nextSignedInt >> maximumBits == -1);
            }
        }
    }


    @Test(invocationCount = 128)
    public void testUnsignedLong() {

        final JinahyaRandom random = new JinahyaRandom();

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

        Assert.assertTrue(random.nextUnsignedLong() >= 0L);

        final int maximumBits = random.nextInt(63) + 1; // 1 - 31
        Assert.assertTrue(random.nextUnsignedLong(maximumBits) >= 0L);
    }


    @Test(invocationCount = 128)
    public void testNextSignedLong() {

        final JinahyaRandom random = new JinahyaRandom();

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

        final int maximumBits = random.nextInt(63) + 2;
        final long nextSignedLong = random.nextSignedLong(maximumBits);
        if (maximumBits < Long.SIZE) {
            if (nextSignedLong >= 0L) {
                Assert.assertTrue(nextSignedLong >> maximumBits == 0L);
            } else {
                Assert.assertTrue(nextSignedLong >> maximumBits == -1L);
            }
        }
    }


}

