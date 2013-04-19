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
    public interface ByteInput {


        /**
         * Reads next unsigned byte.
         *
         * @return next unsigned byte. -1 for EOF.
         *
         * @throws IOException if an I/O error occurs.
         */
        int readUnsignedByte() throws IOException;


    }


    /**
     * An implementation for InputStreams.
     */
    public static class StreamInput implements ByteInput {


        /**
         * Creates a new instance.
         *
         * @param input the stream to wrap.
         */
        public StreamInput(final InputStream input) {
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
        private final InputStream input;


    }


    /**
     * Creates a new instance.
     *
     * @param input the byte input
     */
    public BitInput(final ByteInput input) {
        super();

        if (input == null) {
            throw new NullPointerException("null input");
        }

        this.input = input;
    }


    /**
     * Reads an {@code length}-bit unsigned byte value.
     *
     * @param length bit length between 0 exclusive and 8 inclusive
     *
     * @return an unsigned byte value.
     *
     * @throws IOException if an I/O error occurs.
     */
    protected int readUnsignedByte(final int length) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length > 8) {
            throw new IllegalArgumentException("length(" + length + ") > 8");
        }

        if (index == 8) {
            int octet = input.readUnsignedByte();
            if (octet == -1) {
                throw new EOFException("eof");
            }
            count++;
            for (int i = 7; i >= 0; i--) {
                bitset.set(i, (octet & 0x01) == 0x01);
                octet >>= 1;
            }
            index = 0;
        }

        final int required = length - (8 - index);

        if (required > 0) {
            return (readUnsignedByte(8 - index) << required)
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
     * Reads a {@code 1}-bit boolean value. {@code 0x01} for
     * {@code true}, {@code 0x00} for {@code false}.
     *
     * @return a boolean value
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean readBoolean() throws IOException {

        return readUnsignedByte(1) == 0x01;
    }


    /**
     * Reads an {@code length}-bit unsigned short value.
     *
     * @param length bit length between 1 (inclusive) and
     * {@value java.lang.Short#SIZE} (inclusive).
     *
     * @return the unsigned short value read.
     *
     * @throws IOException if an I/O error occurs.
     */
    protected int readUnsignedShort(final int length) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length > 16) {
            throw new IllegalArgumentException("length(" + length + ") > 16");
        }

        final int quotient = length / 8;
        final int remainder = length % 8;

        int result = 0x00;

        for (int i = 0; i < quotient; i++) {
            result <<= 8;
            result |= readUnsignedByte(8);
        }

        if (remainder > 0) {
            result <<= remainder;
            result |= readUnsignedByte(remainder);
        }

        return result;
    }


    /**
     * Reads an {@code length}-bit unsigned int value.
     *
     * @param length bit length between 1 (inclusive) and
     * {@value java.lang.Integer#SIZE} (exclusive).
     *
     * @return the unsigned {@code length}-bit int value read from the input.
     *
     * @throws IOException if an I/O error occurs
     */
    public int readUnsignedInt(final int length) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length > 31) {
            throw new IllegalArgumentException("length(" + length + ") > 31");
        }

        final int quotient = length / 16;
        final int remainder = length % 16;

        int result = 0x00;

        for (int i = 0; i < quotient; i++) {
            result <<= 16;
            result |= readUnsignedShort(16);
        }

        if (remainder > 0) {
            result <<= remainder;
            result |= readUnsignedShort(remainder);
        }

        return result;
    }


    /**
     * Reads a {@code length}-bit signed int value.
     *
     * @param length bit length between 1 exclusive and 16 inclusive.
     *
     * @return the signed {@code length}-bit int value read from the input.
     *
     * @throws IOException if an I/O error occurs.
     */
    public int readInt(final int length) throws IOException {

        if (length < 2) {
            throw new IllegalArgumentException("length(" + length + ") < 2");
        }

        if (length > 32) { // 32
            throw new IllegalArgumentException("length(" + length + ") > 32");
        }

        return (((readBoolean() ? -1 : 0) << (length - 1))
                | readUnsignedInt(length - 1));
    }


    /**
     * Reads a float value.
     *
     * @return a float value.
     *
     * @throws IOException if an I/O error occurs
     */
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt(32));
    }


    /**
     * Reads a {@code length}-bit unsigned long value.
     *
     * @param length bit length between 1 (inclusive) and 64 (exclusive)
     *
     * @return an unsigned long value
     *
     * @throws IOException if an I/O error occurs
     */
    public long readUnsignedLong(final int length) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length > 63) {
            throw new IllegalArgumentException("length(" + length + ") > 63");
        }

        final int quotient = length / 31;
        final int remainder = length % 31;

        long result = 0x00L;

        for (int i = 0; i < quotient; i++) {
            result <<= 31;
            result |= readUnsignedInt(31);
        }

        if (remainder > 0) {
            result <<= remainder;
            result |= readUnsignedInt(remainder);
        }

        return result;
    }


    /**
     * Reads a {@code length}-bit signed long value.
     *
     * @param length bit length between 1 (exclusive) and 64 (inclusive).
     *
     * @return the signed long value.
     *
     * @throws IOException if an I/O error occurs.
     */
    public long readLong(final int length) throws IOException {

        if (length < 2) {
            throw new IllegalArgumentException("length(" + length + ") < 2");
        }

        if (length > 64) {
            throw new IllegalArgumentException("length(" + length + ") > 64");
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
     * Reads a UTF-8 code point.
     *
     * @return a UTF-8 decoded character value.
     *
     * @throws IOException if an I/O error occurs.
     */
    public int readUTF8Char() throws IOException {

        if (!readBoolean()) {
            return readUnsignedByte(7);
        }

        int tails = 0;
        for (; readBoolean(); tails++) {
            if (tails == 3) {
                throw new IOException("illegal encoding: more than 3 tails");
            }
        }

        if (tails == 0) {
            throw new IOException("illegal encodeding; zero tails");
        }

        int value = readUnsignedByte(8 - 2 - tails);

        for (int i = 0; i < tails; i++) {
            if (readUnsignedByte(2) != 0x02) { // !10xxxxxx
                throw new IOException("illegal encoding; wrong tail bits");
            }
            value <<= 6;
            value |= readUnsignedByte(6);
        }

        return value;
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

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        int bits = 0;

        // discard remained bits on current octet.
        if (index < 8) {
            bits = 8 - index;
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
            readUnsignedByte(8);
            bits += 8;
        }

        return bits;
    }


    /**
     * Returns current bit index to read.
     *
     * @return
     */
    public int getIndex() {
        return index;
    }


    /**
     * Returns the number of octets read so far including current octet.
     *
     * @return the number of octets read so far.
     */
    public int getCount() {
        return count;
    }


    /**
     * source byte input.
     */
    private final ByteInput input;


    /**
     * bits in current octet.
     */
    private final BitSet bitset = new BitSet(8);


    /**
     * bit index to read.
     */
    private int index = 8;


    /**
     * number of bytes read so far.
     */
    private int count = 0;


}

