/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.persistence;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MappedMortonTest {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(MappedMortonTest.class);


    @Test
    protected static void testPbkdf2() {

        final char[] password = null;
        final byte[] salt = new byte[1]; // 0 -> IAE
        final int iterationCount = 1; // 0 -> IAE
        final int keyLength = 1; // 0 -> IAE

        final byte[] result = MappedMorton.pbkdf2(
            password, salt, iterationCount, keyLength);

        Assert.assertEquals(result.length, keyLength / 8);
    }


    @Test
    protected static void testCassword() {

        try {
            MappedMorton.cassword(null);
            Assert.fail("passed: cassword(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        final Random random = ThreadLocalRandom.current();

        final byte[] bassword = new byte[random.nextInt(1024)];
        random.nextBytes(bassword);

        final char[] cassword = MappedMorton.cassword(bassword);

        Assert.assertEquals(cassword.length, bassword.length);
    }


    @Test
    protected void testIterationCount() {

        try {
            MappedMorton.iterationCount(MappedMorton.DENSITY_MIN - 1, null);
            Assert.fail(
                "passed: iterationCount(MappedMorton.DENSITY_MIN - 1, )");
        } catch (final IllegalArgumentException iae) {
            // expected
        }

        try {
            MappedMorton.iterationCount(MappedMorton.DENSITY_MAX + 1, null);
            Assert.fail(
                "passed: iterationCount(MappedMorton.DENSITY_MAX + 1, )");
        } catch (final IllegalArgumentException iae) {
            // expected
        }

        try {
            MappedMorton.iterationCount(MappedMorton.DENSITY_MIN, null);
            Assert.fail("passed: iterationCount(, null)");
        } catch (NullPointerException npe) {
            // expected
        }

        try {
            MappedMorton.iterationCount(MappedMorton.DENSITY_MIN, new byte[0]);
            Assert.fail("passed: iterationCount(, new byte[0])");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        final Random random = ThreadLocalRandom.current();

        final int density = random.nextInt(
            MappedMorton.DENSITY_MAX - MappedMorton.DENSITY_MIN + 1)
                            + MappedMorton.DENSITY_MIN;

        final byte[] bland = new byte[random.nextInt(1024) + 1];
        random.nextBytes(bland);

        final int iterationCount = MappedMorton.iterationCount(density, bland);
        Assert.assertTrue(iterationCount > 0);
    }


    @Test
    protected static void testSodium() {

        try {
            MappedMorton.sodium(-1);
            Assert.fail("passed: sodium(-1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            MappedMorton.sodium(0);
            Assert.fail("passed: sodium(0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        final Random random = ThreadLocalRandom.current();

        final int length = random.nextInt(65536);

        final byte[] sodium = MappedMorton.sodium(length);

        Assert.assertEquals(sodium.length, length);
    }


}
