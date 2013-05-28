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


package com.googlecode.jinahya.io;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
class Generator {


    /**
     * Asserts given {code length} for an unsigned int.
     *
     * @param length bit length to assert
     *
     * @return given {@code length}
     */
    private static int assertLengthIntUnsigned(final int length) {

        Assert.assertTrue(length >= 1);

        Assert.assertTrue(length < 32);

        return length;
    }


    /**
     * Asserts given {@code value} for specified {@code length}.
     *
     * @param length bit length
     * @param value the value to check
     *
     * @return given {@code value}
     */
    private static int assertValueIntUnsigned(final int length,
                                              final int value) {

        assertLengthIntUnsigned(length);

        Assert.assertTrue((value >> length) == 0);

        return value;
    }


    static int newLengthIntUnsigned() {

        final Random random = ThreadLocalRandom.current();

        final int length = random.nextInt(31) + 1; // (0 ~ 30) + 1 = (1 ~ 31)

        return assertLengthIntUnsigned(length);
    }


    static int newValueIntUnsigned(final int length) {

        assertLengthIntUnsigned(length);

        final Random random = ThreadLocalRandom.current();

        final int value = random.nextInt() >>> (32 - length);

        return assertValueIntUnsigned(length, value);
    }


    private static int assertLengthInt(final int length) {

        Assert.assertTrue(length > 1);

        Assert.assertTrue(length <= 32);

        return length;
    }


    private static int assertValueInt(final int length, final int value) {

        assertLengthLong(length);

        if (length != 32) {
            if (value < 0L) {
                Assert.assertTrue((value >> length) == ~0);
            } else {
                Assert.assertTrue((value >> length) == 0);
            }
        }

        return value;
    }


    static int newLengthInt() {

        final Random random = ThreadLocalRandom.current();

        final int length = random.nextInt(31) + 2; // (0 ~ 30) + 2 = 2 ~ 32

        return assertLengthInt(length);
    }


    static int newValueInt(final int length) {

        assertLengthInt(length);

        final Random random = ThreadLocalRandom.current();

        final int value = random.nextInt() >> (32 - length); // length == 32 ?

        return assertValueInt(length, value);
    }


    private static int assertLengthLongUnsigned(final int length) {

        Assert.assertTrue(length >= 1);

        Assert.assertTrue(length < 64);

        return length;
    }


    private static long assertValueLongUnsigned(final int length,
                                                final long value) {

        assertLengthLongUnsigned(length);

        Assert.assertTrue((value >> length) == 0L);

        return value;
    }


    /**
     * Generates a valid bit length for unsigned long value.
     *
     * @return a bit length for unsigned long value
     */
    static int newLengthLongUnsigned() {

        final Random random = ThreadLocalRandom.current();

        return assertLengthLongUnsigned(random.nextInt(63) + 1);
    }


    /**
     * Generates an {@code length}-bit unsigned long value.
     *
     * @param length bit length between 1 (inclusive) and 64 (exclusive)
     *
     * @return an unsigned long value.
     */
    static long newValueLongUnsigned(final int length) {

        assertLengthLongUnsigned(length);

        final Random random = ThreadLocalRandom.current();

        final long value = random.nextLong() >>> (64 - length);

        return assertValueLongUnsigned(length, value);
    }


    private static int assertLengthLong(final int length) {

        Assert.assertTrue(length > 1);

        Assert.assertTrue(length <= 64);

        return length;
    }


    private static long assertValueLong(final int length, final long value) {

        assertLengthLong(length);

        if (length != 64) {
            if (value < 0L) {
                Assert.assertTrue((value >> length) == ~0L);
            } else {
                Assert.assertTrue((value >> length) == 0L);
            }
        }

        return value;
    }


    public static int newLengthLong() {

        final Random random = ThreadLocalRandom.current();

        final int length = random.nextInt(63) + 2; // (0 ~ 62) + 2 = (2 ~ 62)

        return assertLengthLong(length);
    }


    public static long newValueLong(final int length) {

        final Random random = ThreadLocalRandom.current();

        final long value = random.nextLong() >> (64 - length);

        return assertValueLong(length, value);
    }


    private Generator() {
        super();
    }


}

