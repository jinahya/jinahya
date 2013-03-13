/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package com.googlecode.jinahya.io;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;


/**
 * BitInput.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInput {


    /**
     * An interface for byte source.
     */
    public static interface ByteInput {


        /**
         * Reads next byte.
         *
         * @return next unsigned byte. -1 for EOF.
         *
         * @throws IOException if an I/O error occurs.
         */
        int readUnsignedByte() throws IOException;


    }


    private static class ByteInputStream implements ByteInput {


        public ByteInputStream(final InputStream input) {
            super();

            if (input == null) {
                throw new NullPointerException("null input");
            }

            this.input = input;
        }


        @Override
        public int readUnsignedByte() throws IOException {
            return input.read();
        }


        /**
         * input.
         */
        protected final InputStream input;


    }


    /**
     * Creates a new instance.
     *
     * @param input input
     */
    public BitInput(final ByteInput input) {
        super();

        if (input == null) {
            throw new NullPointerException("null input");
        }

        this.input = input;
    }


    /**
     * Creates a new instance.
     *
     * @param input input
     */
    public BitInput(final InputStream input) {
        this(new ByteInputStream(input));
    }


    /**
     * Reads an unsigned byte value.
     *
     * @param length bit length between 0 (exclusive) and 8 (inclusive)
     *
     * @return an unsigned byte value.
     *
     * @throws IOException if an I/O error occurs.
     */
    protected int readUnsignedByte(final int length) throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        if (length > 0x08) {
            throw new IllegalArgumentException("length(" + length + ") > 0x08");
        }

        if (index == 0x08) {
            int octet = input.readUnsignedByte();
            if (octet == -1) {
                throw new EOFException("eof");
            }
            count++;
            for (int i = 0x07; i >= 0x00; i--) {
                bitset.set(i, (octet & 0x01) == 0x01);
                octet >>= 1;
            }
            index = 0x00;
        }

        final int required = length - (0x08 - index);

        if (required > 0x00) {
            return (readUnsignedByte(0x08 - index) << required)
                   | readUnsignedByte(required);
        }

        int value = 0x00;
        for (int i = 0; i < length; i++) {
            value <<= 1;
            value |= (bitset.get(index++) ? 0x01 : 0x00);
        }

        return value;
    }


    /**
     * Reads a 1-bit boolean value. {@code 0x00} for {@code true}, {@code 0x00}
     * for {@code false}.
     *
     * @return a boolean value
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean readBoolean() throws IOException {

        return readUnsignedByte(0x01) == 0x01;
    }


    /**
     * Reads an {@code length}-bit unsigned short value.
     *
     * @param length bit length between 0x01 (inclusive) and {@value
     * java.lang.Short#SIZE} (inclusive).
     *
     * @return the unsigned short value read.
     *
     * @throws IOException if an I/O error occurs.
     */
    protected int readUnsignedShort(final int length) throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        if (length > 0x10) {
            throw new IllegalArgumentException("length(" + length + ") > 0x10");
        }

        final int quotient = length / 0x08;
        final int remainder = length % 0x08;

        int result = 0x00;

        for (int i = 0; i < quotient; i++) {
            result <<= 0x08;
            result |= readUnsignedByte(0x08);
        }

        if (remainder > 0) {
            result <<= remainder;
            result |= readUnsignedByte(remainder);
        }

        return result;
    }


    /**
     * Reads an {@code length}-bit unsigned {@code int} value.
     *
     * @param length bit length between 0x01 (inclusive) and {@value
     * java.lang.Integer#SIZE} (exclusive).
     *
     * @return the unsigned {@code int} value read from the input
     *
     * @throws IOException if an I/O error occurs
     */
    public int readUnsignedInt(final int length) throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        if (length > 0x1F) {
            throw new IllegalArgumentException("length(" + length + ") > 0x1F");
        }

        final int quotient = length / 0x10;
        final int remainder = length % 0x10;

        int result = 0x00;

        for (int i = 0; i < quotient; i++) {
            result <<= 0x10;
            result |= readUnsignedShort(0x10);
        }

        if (remainder > 0x00) {
            result <<= remainder;
            result |= readUnsignedShort(remainder);
        }

        return result;
    }


    /**
     * Reads a {@code length}-bit signed {@code int}.
     *
     * @param length bit length between 0x00 (exclusive) and {@value
     * java.lang.Integer#SIZE} (inclusive).
     *
     * @return an unsigned {@code int} value.
     *
     * @throws IOException if an I/O error occurs.
     */
    public int readInt(final int length) throws IOException {

        if (length < 0x02) {
            throw new IllegalArgumentException("length(" + length + ") < 0x02");
        }

        if (length > 0x20) { // 32
            throw new IllegalArgumentException("length(" + length + ") > 0x20");
        }

        return (((readBoolean() ? -1 : 0x00) << (length - 1))
                | readUnsignedInt(length - 1));
    }


    /**
     * Reads a {@code float} value.
     *
     * @return the read float value.
     *
     * @throws IOException if an I/O error occurs
     */
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt(Integer.SIZE));
    }


    /**
     * Reads an unsigned {@code long} value.
     *
     * @param length bit length between 0x01 (inclusive) and {@value
     * java.lang.Long#SIZE} (exclusive)
     *
     * @return an unsigned long value
     *
     * @throws IOException if an I/O error occurs
     */
    public long readUnsignedLong(final int length) throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        if (length > 0x3F) {
            throw new IllegalArgumentException("length(" + length + ") > 0x3F");
        }

        final int quotient = length / 0x10;
        final int remainder = length % 0x10;

        long result = 0x00L;

        for (int i = 0; i < quotient; i++) {
            result <<= 0x10;
            result |= readUnsignedShort(0x10);
        }

        if (remainder > 0) {
            result <<= remainder;
            result |= readUnsignedShort(remainder);
        }

        return result;
    }


    /**
     * Reads a {@code length}-bit signed {@code long} value.
     *
     * @param length bit length between 0x01 (exclusive) and {@value
     * java.lang.Long#SIZE} (inclusive).
     *
     * @return the signed long value.
     *
     * @throws IOException if an I/O error occurs.
     */
    public long readLong(final int length) throws IOException {

        if (length < 0x02) {
            throw new IllegalArgumentException("length(" + length + ") < 0x02");
        }

        if (length > 0x40) {
            throw new IllegalArgumentException("length(" + length + ") > 0x40");
        }

        return (((readBoolean() ? -1L : 0L) << (length - 1))
                | readUnsignedLong(length - 1));

    }


    /**
     * Reads a double value.
     *
     * @return double value
     *
     * @throws IOException if an I/O error occurs.
     */
    public final double readDouble() throws IOException {

        return Double.longBitsToDouble(readLong(0x40));
    }


    /**
     * Align to given {@code length} bytes.
     *
     * @param length number of bytes to align; must be non-zero positive.
     *
     * @return the number of bits discarded for alignment.
     *
     * @throws IOException if an I/O error occurs.
     */
    public int align(final int length) throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        int bits = 0;

        // discard remained bits on current octet.
        if (index > 0x00) {
            bits = index;
            readUnsignedByte(bits);
        }

        int octets = count % length;

        if (octets == 0) {
            return bits;
        }

        if (octets > 0) {
            octets = length - octets;
        } else {
            octets = 0 - octets;
        }

        for (; octets > 0; octets--) {
            readUnsignedByte(0x08);
            bits += 0x08;
        }

        return bits;
    }


    /**
     * byte input.
     */
    private final ByteInput input;


    /**
     * bitset.
     */
    private final BitSet bitset = new BitSet(0x08);


    /**
     * bit index to read.
     */
    private int index = 0x08;


    /**
     * number of bytes read so far.
     */
    private int count = 0x00;


}

