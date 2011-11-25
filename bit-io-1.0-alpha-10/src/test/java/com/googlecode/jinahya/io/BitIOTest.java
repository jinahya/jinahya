/*
 * Copyright 2011 Jin Kwon.
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

import org.apache.commons.lang.RandomStringUtils;

import org.testng.Assert;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitIOTest {


    /** random. */
    protected static final Random RANDOM = new Random();


    /** count. */
    protected static final int INVOCATION_COUNT = 64;


    /** string length. */
    protected static final int STRING_LENGTH = 128;


    /** bytes length. */
    protected static final int BYTES_LENGTH = 128;


    protected static final int newStringLength() {

        return RANDOM.nextInt(STRING_LENGTH) + STRING_LENGTH;
    }


    protected static final int newBytesLength() {

        return RANDOM.nextInt(BYTES_LENGTH) + BYTES_LENGTH;
    }


    protected static final String newASCII(final boolean nullable) {

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        if (RANDOM.nextBoolean()) {
            return "";
        }

        return RandomStringUtils.randomAscii(newStringLength());
    }


    protected static final String newString(final boolean nullable) {

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        if (RANDOM.nextBoolean()) {
            return "";
        }

        return RandomStringUtils.random(newStringLength());
    }


    /**
     * Returns a random count.
     *
     * @return new random count
     */
    protected static final int newCount() {
        return RANDOM.nextInt(INVOCATION_COUNT) + INVOCATION_COUNT;
    }


    protected static final Boolean newBoolean(final boolean nullable) {

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        return RANDOM.nextBoolean();
    }


    protected static final byte[] newBytes(final boolean nullable) {

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        if (RANDOM.nextBoolean()) {
            return new byte[0];
        }

        final byte[] bytes = new byte[newBytesLength()];
        RANDOM.nextBytes(bytes);
        return bytes;
    }


    protected static final int newUnsignedIntLength() {
        return checkUnsignedIntLength(RANDOM.nextInt(31) + 1); // 1 - 31
    }


    protected static final int checkUnsignedIntLength(final int length) {
        Assert.assertTrue(length >= 1);
        Assert.assertTrue(length < Integer.SIZE);
        return length;
    }


    protected static final int newSignedIntLength() {
        return checkSignedIntLength(RANDOM.nextInt(31) + 2); // 2 - 32
    }


    protected static final int checkSignedIntLength(final int length) {
        Assert.assertTrue(length > 1);
        Assert.assertTrue(length <= Integer.SIZE);
        return length;
    }


    protected static final Integer newUnsignedIntValue(final int length,
                                                       final boolean nullable) {

        checkUnsignedIntLength(length);

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        final int value = RANDOM.nextInt() >>> (Integer.SIZE - length);

        Assert.assertTrue((value >> length) == 0);

        return value;
    }


    protected static final Integer newSignedIntValue(final int length,
                                                     final boolean nullable) {

        checkSignedIntLength(length);

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        final int value = RANDOM.nextInt() >> (Integer.SIZE - length);

        if (length < Integer.SIZE) {
            if (value < 0L) {
                Assert.assertTrue((value >> length) == -1);
            } else {
                Assert.assertTrue((value >> length) == 0);
            }
        }

        return value;
    }


    protected static final int newUnsignedLongLength() {
        return checkUnsignedLongLength(RANDOM.nextInt(63) + 1); // 1 - 63
    }


    protected static final int checkUnsignedLongLength(final int length) {
        Assert.assertTrue(length >= 1);
        Assert.assertTrue(length < Long.SIZE);
        return length;
    }


    protected static final int newSignedLongLength() {
        return checkSignedLongLength(RANDOM.nextInt(63) + 2); // 2 - 64
    }


    protected static final int checkSignedLongLength(final int length) {
        Assert.assertTrue(length > 1);
        Assert.assertTrue(length <= Long.SIZE);
        return length;
    }


    protected static final Long newUnsignedLongValue(final int length,
                                                     final boolean nullable) {

        checkUnsignedLongLength(length);

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        final long value = RANDOM.nextLong() >>> (Long.SIZE - length);

        Assert.assertTrue((value >> length) == 0L);

        return value;
    }


    protected static final Long newSignedLongValue(final int length,
                                                   final boolean nullable) {

        checkSignedLongLength(length);

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        final long value = RANDOM.nextLong() >> (Long.SIZE - length);

        if (length < Long.SIZE) {
            if (value < 0L) {
                Assert.assertTrue((value >> length) == -1L);
            } else {
                Assert.assertTrue((value >> length) == 0L);
            }
        }

        return value;
    }


}

