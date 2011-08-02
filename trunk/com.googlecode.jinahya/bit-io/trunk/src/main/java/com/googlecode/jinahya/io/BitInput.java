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


import java.io.CharArrayWriter;
import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;
import java.util.BitSet;


/**
 * Bit Input.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInput {


    /**
     * Creates a new instance.
     *
     * @param in source octet input
     */
    public BitInput(final InputStream in) {
        super();

        if (in == null) {
            throw new NullPointerException("null in");
        }

        this.in = in;
    }


    /**
     * Reads an unsigned byte value.
     *
     * @param length bit length between 0 (exclusive) and 8 (inclusive)
     * @return an unsigned byte value.
     * @throws IOException if an I/O error occurs.
     */
    protected final int readUnsignedByte(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        if (length > 8) {
            throw new IllegalArgumentException("length(" + length + ") > 8");
        }

        if (index == 8) {
            int octet = in.read();
            if (octet == -1) {
                throw new EOFException("eof");
            }
            for (int i = 7; i >= 0; i--) {
                set.set(i, (octet & 0x01) == 0x01);
                octet >>= 1;
            }
            index = 0;
            count++;
        }

        final int required = length - (8 - index);

        if (required > 0) {
            return (readUnsignedByte(length - required) << required)
                   | readUnsignedByte(required);
        }

        int value = 0x00;
        for (int i = 0; i < length; i++) {
            value <<= 1;
            value |= (set.get(index + i) ? 0x01 : 0x00);
        }

        index += length;

        return value;
    }


    /**
     * Reads 1-bit boolean value.
     *
     * @return boolean value
     * @throws IOException if an I/O error occurs.
     */
    public final boolean readBoolean() throws IOException {

        return readUnsignedByte(1) == 0x01;
    }


    /**
     * Reads an unsigned short value.
     *
     * @param length bit length between 0 (exclusive) and 16 (inclusive).
     * @return an unsigned short value.
     * @throws IOException if an I/O error occurs.
     */
    protected final int readUnsignedShort(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        if (length > 16) {
            throw new IllegalArgumentException("length(" + length + ") > 16");
        }

        final int quotient = length / 0x08;
        final int remainder = length % 0x08;

        int value = 0x00;
        for (int i = 0; i < quotient; i++) {
            value <<= 0x08;
            value |= readUnsignedByte(0x08);
        }

        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedByte(remainder);
        }

        return value;
    }


    /**
     * Reads an unsigned int.
     *
     * @param length bit length between 1 (inclusive) and 32 (exclusive).
     * @return an unsigned int value
     * @throws IOException if an I/O error occurs
     */
    public final int readUnsignedInt(final int length) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length >= 32) {
            throw new IllegalArgumentException("length(" + length + ") >= 32");
        }

        final int quotient = length / 0x10;
        final int remainder = length % 0x10;

        int value = 0x00;
        for (int i = 0; i < quotient; i++) {
            value <<= 0x10;
            value |= readUnsignedShort(0x10);
        }

        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedShort(remainder);
        }

        return value;
    }


    /**
     * Reads a signed int.
     *
     * @param length bit length between 1 (exclusive) and 32 (inclusive).
     * @return a <code>length</code>bit-long signed integer
     * @throws IOException if an I/O error occurs.
     */
    public final int readInt(final int length) throws IOException {

        if (length <= 1) {
            throw new IllegalArgumentException("length(" + length + ") <= 1");
        }

        if (length > 32) {
            throw new IllegalArgumentException("length(" + length + ") > 32");
        }

        int value = 0x00;

        if (readBoolean()) {
            value--;
        }

        value <<= (length - 1);

        value |= readUnsignedInt(length - 1);

        return value;
    }


    /**
     * Reads an unsigned long.
     *
     * @param length bit length between 1 (inclusive) and 64 (exclusive)
     * @return an unsigned long value
     * @throws IOException if an I/O error occurs
     */
    public final long readUnsignedLong(final int length) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length >= 64) {
            throw new IllegalArgumentException("length(" + length + ") >= 64");
        }

        final int quotient = length / 0x10;
        final int remainder = length % 0x10;

        long value = 0x00L;
        for (int i = 0x00; i < quotient; i++) {
            value <<= 0x10;
            value |= readUnsignedShort(0x10);
        }

        if (remainder > 0x00) {
            value <<= remainder;
            value |= readUnsignedShort(remainder);
        }

        return value;
    }


    /**
     * Reads a signed long value.
     *
     * @param length bit length between 1 (exclusive) and 64 (inclusive).
     * @return a signed long value.
     * @throws IOException if an I/O error occurs.
     */
    public final long readLong(final int length) throws IOException {

        if (length <= 1) {
            throw new IllegalArgumentException("length(" + length + ") <= 1");
        }

        if (length > 64) {
            throw new IllegalArgumentException("length(" + length + ") > 64");
        }

        long value = 0x00L;
        if (readBoolean()) {
            value--;
        }
        value <<= (length - 1);

        value |= readUnsignedLong(length - 1);

        return value;
    }


    /**
     * Reads a byte array. First, a 31-bit unsigned integer is read for the byte
     * array length. And then each byte is read as 8-bit unsigned int.
     *
     * @return a byte array
     * @throws IOException if an I/O error occurs.
     */
    public final byte[] readBytes() throws IOException {

        final byte[] bytes = new byte[readUnsignedInt(0x1F)];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) readUnsignedByte(8);
        }

        return bytes;
    }


    /**
     * Reads a 7-bit ASCII String. Reads a 31-bit unsigned integer for character
     * count following the characters which each is read as 7-bit unsigned byte.
     *
     * @return an ASCII String
     * @throws IOException if an I/O error occurs.
     */
    public final String readASCII() throws IOException {

        final byte[] bytes = new byte[readUnsignedInt(0x1F)]; // 31
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) readUnsignedByte(0x07);
        }

        return new String(bytes, "US-ASCII");
    }


    /**
     * Reads a modified-UTF8 String. Identical to {@link DataInput#readUTF()}.
     *
     * @return a String
     * @throws IOException if an I/O error occurs.
     * @see DataInput#readUTF() 
     */
    public final String readUTF() throws IOException {

        final CharArrayWriter caw = new CharArrayWriter();

        final int length = readUnsignedInt(0x10); // 16
        int i = 0;
        for (; i < length; i++) {

            final int first = readUnsignedByte(0x08);
            if (first <= 0x7F) { // 0xxxxxxx
                caw.write(first);
                continue;
            }

            if (first <= 0xBF) {
                throw new UTFDataFormatException(
                    "illegal first byte: " + first);
            }

            if (first <= 0xDF) { // 110xxxxx
                final int second = readUnsignedByte(0x08); // EOFException
                i++;
                if (second > 0xBF) { // !10xxxxxxxx
                    throw new UTFDataFormatException(
                        "illegal second byte: " + second);
                }
                caw.write(((first & 0x1F) << 6) | (second & 0x3F));
                continue;
            }

            if (first <= 0xEF) { // 1110xxxx
                final int second = readUnsignedByte(0x08); // EOFException
                i++;
                if (second > 0xBF) { // !10xxxxxxxx
                    throw new UTFDataFormatException(
                        "illegal second byte: " + second);
                }
                final int third = readUnsignedByte(0x08); // EOFException
                i++;
                if (third > 0xBF) { // !10xxxxxxxx
                    throw new UTFDataFormatException(
                        "illegal third byte: " + second);
                }
                caw.write(((first & 0x0F) << 12)
                          | ((second & 0x3F) << 6)
                          | (third & 0x3F));
                continue;
            }

            throw new UTFDataFormatException("illegal first byte: " + first);
        }

        return caw.toString();
    }


    /**
     * Align to given <code>length</code> bytes.
     *
     * @param length number of bytes to align
     * @throws IOException if an I/O error occurs.
     */
    public final void aling(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        if (index < 8) { // bit index to read
            readUnsignedByte(8 - index);
        }

        int octets = count % length;

        if (octets == 0) {
            return;
        }

        if (octets > 0) {
            octets = length - octets;
        } else { // mod < 0
            octets = 0 - octets;
        }

        for (; octets > 0; octets--) {
            readUnsignedByte(8);
        }
    }


    /** input source. */
    private final InputStream in;


    /** bit set. */
    private final BitSet set = new BitSet(8);


    /** bit index to read. */
    private int index = 8;


    /** so far read octet count. */
    private int count = 0;


    /** test. */
    int getCount() {
        return count;
    }


    /** test. */
    void setCount(int count) {
        this.count = count;
    }
}

