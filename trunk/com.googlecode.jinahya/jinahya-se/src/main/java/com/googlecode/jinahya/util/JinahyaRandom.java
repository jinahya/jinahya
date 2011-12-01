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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class JinahyaRandom extends Random {


    /** GENERATED. */
    private static final long serialVersionUID = 4093243768555129124L;


    /**
     * Creates a new instance.
     */
    public JinahyaRandom() {
        super();
    }


    /**
     * Generates a byte array in given <code>length</code>.
     *
     * @param length array length
     * @return generated byte array
     */
    public byte[] nextBytes(final int length) {

        if (length < 0) {
            throw new IllegalArgumentException("length(" + length + ") < 0");
        }

        return nextBytes(length, length);
    }


    /**
     * Generates a byte array in arbitrary length.
     *
     * @param minimumLength minimum byte array length (inclusive)
     * @param maximumLength maximum byte array length (inclusive)
     * @return generated byte array
     */
    public byte[] nextBytes(final int minimumLength, final int maximumLength) {

        if (minimumLength < 0) {
            throw new IllegalArgumentException(
                "minimumLength(" + minimumLength + ") < 0");
        }

        if (maximumLength < minimumLength) {
            throw new IllegalArgumentException(
                "maximumLength(" + maximumLength + ") < minimumLength("
                + minimumLength + ")");
        }

        final int length = (minimumLength == maximumLength
                            ? 0 : nextInt(maximumLength - minimumLength))
                           + minimumLength;
        final byte[] bytes = new byte[length];
        nextBytes(bytes);
        return bytes;
    }


    /**
     * Generates an unsigned integer.
     *
     * @param maximumBits maximum bits between 1 (inclusive) and
     * {@value java.lang.Integer#SIZE} (exclusive).
     * @return generated value
     */
    public int nextUnsignedInt(final int maximumBits) {

        if (maximumBits < 0x01) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") < 0x01");
        }

        if (maximumBits >= Integer.SIZE) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") >= " + Integer.SIZE);
        }

        return nextInt() >>> (Integer.SIZE - (nextInt(maximumBits) + 1));
    }


    /**
     * Generates a signed integer in arbitrary bit length.
     *
     * @param maximumBits maximum bits between 1 (exclusive) and
     * {@value java.lang.Integer#SIZE} (inclusive).
     * @return generated value
     */
    public int nextSignedInt(final int maximumBits) {

        if (maximumBits <= 0x01) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") <= 0x01");
        }

        if (maximumBits > Integer.SIZE) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") > " + Integer.SIZE);
        }

        if (maximumBits == Integer.SIZE) {
            return nextInt();
        }

        return nextInt() >> (Integer.SIZE - (nextInt(maximumBits) + 1));
    }


    /**
     * Generates an unsigned long in arbitrary bit length.
     *
     * @param maximumBits maximum bits between 1 (inclusive) and
     * {@value Long#SIZE} (exclusive).
     * @return generated value
     */
    public long nextUnsignedLong(final int maximumBits) {

        if (maximumBits < 0x01) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") < 0x01");
        }

        if (maximumBits >= Long.SIZE) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") >= " + Long.SIZE);
        }

        return nextLong() >>> (Long.SIZE - (nextInt(maximumBits) + 1));
    }


    /**
     * Generates a signed long in arbitrary bit length.
     *
     * @param maximumBits maximum bits between 1 (exclusive) and
     * {@value java.lang.Long#SIZE} (inclusive).
     * @return generated value
     */
    public long nextSignedLong(final int maximumBits) {

        if (maximumBits <= 0x01) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") <= 0x01");
        }

        if (maximumBits > Long.SIZE) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") > " + Long.SIZE);
        }

        if (maximumBits == Long.SIZE) {
            return nextLong();
        }

        return nextLong() >> (Long.SIZE - (nextInt(maximumBits) + 1));
    }


}

