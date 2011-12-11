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
     * Returns a random number between <code>minimum</code> (inclusive) and
     * <code>maximum</code> (exclusive).
     *
     * @param minimum minimum value (inclusive)
     * @param maximum maximum value (exclusive)
     * @return a number between <code>minimum</code> and <code>maximum</code>
     */
    public int nextInt(final int minimum, final int maximum) {

        if (minimum < 0) {
            throw new IllegalArgumentException("minimum(" + minimum + ") < 0");
        }

        if (minimum >= maximum) {
            throw new IllegalArgumentException(
                "minimum(" + minimum + ") >= maximum(" + maximum + ")");
        }

        return nextInt(maximum - minimum) + minimum;
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

        return nextBytes(length, length + 1);
    }


    /**
     * Generates a byte array in arbitrary length.
     *
     * @param minimumLength minimum byte array length (inclusive)
     * @param maximumLength maximum byte array length (exclusive)
     * @return generated byte array
     */
    public byte[] nextBytes(final int minimumLength, final int maximumLength) {

        if (minimumLength < 0) {
            throw new IllegalArgumentException(
                "minimumLength(" + minimumLength + ") < 0");
        }

        if (minimumLength >= maximumLength) {
            throw new IllegalArgumentException(
                "minimumLength(" + minimumLength + ") >= maximumLength("
                + maximumLength + ")");
        }

        final int length = nextInt(minimumLength, maximumLength);
        final byte[] bytes = new byte[length];
        nextBytes(bytes);
        return bytes;
    }


    /**
     * Generates an unsigned int in arbitrary bits.
     *
     * @param minimumBits minimum bits (inclusive)
     * @param maximumBits maximum bits (inclusive)
     * @return generated value.
     */
    public int nextUnsignedInt(final int minimumBits, final int maximumBits) {

        if (minimumBits < 1) {
            throw new IllegalArgumentException(
                "minimumBits(" + minimumBits + ") < 1");
        }

        if (minimumBits > maximumBits) {
            throw new IllegalArgumentException(
                "minimumBits(" + minimumBits + ") > maximumBits("
                + maximumBits + ")");
        }

        if (maximumBits >= Integer.SIZE) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") >= " + Integer.SIZE);
        }

        final int bits = nextInt(minimumBits, maximumBits + 1);
        return nextUnsignedInt(bits);
    }


    /**
     * Generates an unsigned integer.
     *
     * @param bits bits between 1 (inclusive) and
     * {@value java.lang.Integer#SIZE} (exclusive).
     * @return generated value
     */
    public int nextUnsignedInt(final int bits) {

        if (bits < 1) {
            throw new IllegalArgumentException("bits(" + bits + ") < 1");
        }

        if (bits >= Integer.SIZE) {
            throw new IllegalArgumentException(
                "bits(" + bits + ") >= " + Integer.SIZE);
        }

        return nextInt() >>> (Integer.SIZE - bits);
    }


    /**
     * Generates a signed int in arbitrary bits.
     *
     * @param minimumBits minimum bits (inclusive)
     * @param maximumBits maximum bits (inclusive)
     * @return generated value.
     */
    public int nextSignedInt(final int minimumBits, final int maximumBits) {

        if (minimumBits <= 1) {
            throw new IllegalArgumentException(
                "minimumBits(" + minimumBits + ") <= 1");
        }

        if (minimumBits > maximumBits) {
            throw new IllegalArgumentException(
                "minimumBits(" + minimumBits + ") > maximumBits("
                + maximumBits + ")");
        }

        if (maximumBits > Integer.SIZE) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") > " + Integer.SIZE);
        }

        final int bits = nextInt(minimumBits, maximumBits + 1);
        return nextSignedInt(bits);
    }


    /**
     * Generates a signed integer in given <code>bits</code> length.
     *
     * @param bits bits between 1 (exclusive) and
     * {@value java.lang.Integer#SIZE} (inclusive).
     * @return generated value
     */
    public int nextSignedInt(final int bits) {

        if (bits <= 1) {
            throw new IllegalArgumentException("bits(" + bits + ") <= 1");
        }

        if (bits > Integer.SIZE) {
            throw new IllegalArgumentException(
                "bits(" + bits + ") > " + Integer.SIZE);
        }

        if (bits == Integer.SIZE) {
            return nextInt();
        }

        return nextInt() >> (Integer.SIZE - bits);
    }


    /**
     * Generates an unsigned long in arbitrary bits.
     *
     * @param minimumBits minimum bits (inclusive)
     * @param maximumBits maximum bits (inclusive)
     * @return generated value.
     */
    public long nextUnsignedLong(final int minimumBits, final int maximumBits) {

        if (minimumBits < 1) {
            throw new IllegalArgumentException(
                "minimumBits(" + minimumBits + ") < 1");
        }

        if (minimumBits > maximumBits) {
            throw new IllegalArgumentException(
                "minimumBits(" + minimumBits + ") > maximumBits(" + maximumBits
                + ")");
        }

        if (maximumBits >= Long.SIZE) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") >= " + Long.SIZE);
        }

        final int bits = nextInt(minimumBits, maximumBits + 1);
        return nextUnsignedLong(bits);
    }


    /**
     * Generates an unsigned long in arbitrary bit length.
     *
     * @param bits bits between 1 (inclusive) and {@value java.lang.Long#SIZE}
     * (exclusive).
     * @return generated value
     */
    public long nextUnsignedLong(final int bits) {

        if (bits < 1) {
            throw new IllegalArgumentException("bits(" + bits + ") < 1");
        }

        if (bits >= Long.SIZE) {
            throw new IllegalArgumentException(
                "bits(" + bits + ") >= " + Long.SIZE);
        }

        return nextLong() >>> (Long.SIZE - bits);
    }


    /**
     * Generates a signed int in arbitrary bits.
     *
     * @param minimumBits minimum bits (inclusive)
     * @param maximumBits maximum bits (inclusive)
     * @return generated value.
     */
    public long nextSignedLong(final int minimumBits, final int maximumBits) {

        if (minimumBits <= 1) {
            throw new IllegalArgumentException(
                "minimumBits(" + minimumBits + ") <= 1");
        }

        if (minimumBits > maximumBits) {
            throw new IllegalArgumentException(
                "minimumBits(" + minimumBits + ") > maximumBits(" + maximumBits
                + ")");
        }

        if (maximumBits > Long.SIZE) {
            throw new IllegalArgumentException(
                "maximumBits(" + maximumBits + ") > " + Long.SIZE);
        }

        final int bits = nextInt(minimumBits, maximumBits + 1);
        return nextSignedLong(bits);
    }


    /**
     * Generates a signed long in arbitrary bit length.
     *
     * @param bits bits between 1 (exclusive) and {@value java.lang.Long#SIZE}
     * (inclusive).
     * @return generated value
     */
    public long nextSignedLong(final int bits) {

        if (bits <= 1) {
            throw new IllegalArgumentException("bits(" + bits + ") <= 1");
        }

        if (bits > Long.SIZE) {
            throw new IllegalArgumentException(
                "bits(" + bits + ") > " + Long.SIZE);
        }

        if (bits == Long.SIZE) {
            return nextLong();
        }

        return nextLong() >> (Long.SIZE - bits);
    }


}
