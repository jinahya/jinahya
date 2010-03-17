/*
 *  Copyright 2009 Jin Kwon.
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

package jinahya.io;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import jinahya.util.ModifiedUTF8;


/**
 * Class for bit level input.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInput {


    /**
     * The interface for octet input.
     */
    public static interface OctetInput {


        /**
         * Reads an octet from desired input.
         *
         * @return octet value 0-255 or -1 if EOF
         * @throws IOException if an I/O error occurs
         */
        int readOctet() throws IOException;
    }


    /**
     * Creates a new instance with specified input.
     *
     * @param in the input stream to read octets.
     */
    public BitInput(final InputStream in) {
        this(new OctetInput() {
            public int readOctet() throws IOException {
                return in.read();
            }
        });
    }


    /**
     * Creates a new instance with specified input.
     *
     * @param in the octet input to read octets.
     */
    public BitInput(final OctetInput in) {
        super();

        input = in;
    }


    /**
     * Creates an instance inheriting attributes from specified
     * <code>parent</code>.
     *
     * @param parent parent instance
     */
    public BitInput(final BitInput parent) {
        super();

        input = parent.input;

        octet = parent.octet;
        avail = parent.octet;
        count = parent.count;
    }


    /**
     * Reads one bit and returns true if it is 0x01.
     *
     * @return true for 0x01, false otherwise(0x00)
     * @throws IOException if an I/O error occurs.
     */
    public final boolean readBoolean() throws IOException {
        return (readUnsignedByte(1) == 0x01);
    }


    /**
     * Same as <code>writeBytes(value, 0, value.length)</code>.
     *
     * @param value byte array to which octets are stored
     * @throws IOException if an I/O error occurs.
     * @see #readBytes(byte[], int, int)
     */
    public final void readBytes(final byte[] value) throws IOException {
        readBytes(value, 0, value.length);
    }


    /**
     * See comemnts from {@link java.io.DataInput#readFully(byte[], int, int)}.
     *
     * @param value byte array to which octets are stored
     * @param offset the offset to start
     * @param length the number of octets to read.
     * @throws IOException if an I/O error occurs.
     * @see java.io.DataInput#readFully(byte[], int, int)
     */
    public final void readBytes(final byte[] value, final int offset,
                                final int length)
        throws IOException {

        if (value == null) {
            throw new NullPointerException("value");
        }

        if (offset < 0) {
            throw new IndexOutOfBoundsException("offset(" + offset + ") < 0");
        }

        if (length < 0) {
            throw new IndexOutOfBoundsException("length(" + length + ") < 0");
        }

        if ((offset + length) > value.length) {
            throw new IndexOutOfBoundsException(
                "offset(" + offset + ") + length(" + length
                + ") > value.length(" + value.length + ")");
        }

        for (int i = 0; i < length; i++) {
            value[offset + i] = (byte) readUnsignedByte(8);
        }
    }


    /**
     * Reads an unsigned <code>length</code> bit byte.
     *
     * @param length bit length between 1 (inclusive) and 8 (inclusive).
     * @return an unsigned <code>length</code> bit integer.
     * @throws IOException if an I/O error occurs.
     */
    private final int readUnsignedByte(final int length) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length > 8) {
            throw new IllegalArgumentException("length(" + length + " > 8");
        }

        if (avail == 0) {
            octet = input.readOctet();
            //System.out.println("read octet: " + octet);
            if (octet == -1) {
                throw new EOFException("EOF");
            }
            avail = 8;
        }

        if (avail >= length) {
            int value = octet >> (avail - length);
            avail -= length;
            count += length;
            octet ^= (value << avail);
            return value;
        } else {
            final int requi = length - avail;
            return ((readUnsignedByte(avail) << requi)
                    | readUnsignedByte(requi));
        }
    }


    /**
     * Reads an unsigned <code>length</code> bit short.
     *
     * @param length bit length between 1 (inclusive) and 16 (inclusive).
     * @return an unsigned <code>length</code> bit integer.
     * @throws IOException if an I/O error occurs
     */
    private final int readUnsignedShort(final int length) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length > 16) {
            throw new IllegalArgumentException("length(" + length + ") > 16");
        }

        int value = 0x00;

        int quotient = length / 8;
        for (int i = 0; i < quotient; i++) {
            value <<= 8;
            value |= readUnsignedByte(8);
        }

        int remainder = length % 8;
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedByte(remainder);
        }

        return value;
    }


    /**
     * Reads an unsigned <code>length</code> bit int.
     *
     * @param length bit length between 1 (inclusive) and 32 (exclusive)
     * @return an unsigned int
     * @throws IOException if an I/O error occurs
     */
    public final int readUnsignedInt(final int length) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length >= 32) {
            throw new IllegalArgumentException("length(" + length + ") >= 32");
        }

        int value = 0;

        final int quotient = length / 16;
        for (int i = 0; i < quotient; i++) {
            value <<= 16;
            value |= readUnsignedShort(16);
        }

        final int remainder = length % 16;
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedShort(remainder);
        }

        return value;
    }


    /**
     * Reads a <code>length</code> bit signed integer.
     *
     * @param length bit length between 1 (exclusive) and 32 (inclusive).
     * @return a signed int
     * @throws IOException if an I/O error occurs.
     */
    public final int readInt(final int length) throws IOException {

        if (length <= 1) {
            throw new IllegalArgumentException("length(" + length +") <= 1");
        }

        if (length > 32) {
            throw new IllegalArgumentException("length(" + length +") > 32");
        }

        int value = 0;
        if (readBoolean()) {
            value = (-1 << (length - 1));
        }

        value |= readUnsignedInt(length - 1);

        return value;
    }


    /**
     * Reads a 32-bit signed int.
     *
     * @return a 32-bit signed int
     * @throws IOException if an I/O error occurs
     */
    public final int readInt() throws IOException {
        return readInt(32);
    }


    /*
     * Read an int in little endian byte order.
     *
     * @param length byte length between 0 (exclusive) and 4 (inclusive)
     * @return an int value in little endian byte order
     * @throws IOException if an I/O error occurs
    public int readIntLE(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length <= 0");
        }

        if (length > 4) {
            throw new IllegalArgumentException("length > 4");
        }

        int value = 0;
        for (int i = 0; i < length; i++) {
            value |= (readUnsignedByte(8) << (i * 8));
        }

        return value;
    }
     */


    /**
     * Read a 32-bit signed floating-point value.
     *
     * @return a float
     * @throws IOException if an I/O error occurs.
     */
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }


    /**
     * Reads a <code>length</code> bit unsigned long.
     *
     * @param length bit length between 0 (exclusive) and 64 (exclusive).
     * @return an unsigned long
     * @throws IOException if an I/O error occurs.
     */
    public final long readUnsignedLong(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        if (length >= 64) {
            throw new IllegalArgumentException("length(" + length + ") >= 64");
        }

        long value = 0L;

        final int quotient = length / 31;
        for (int i = 0; i < quotient; i++) {
            value <<= 31;
            value |= readUnsignedInt(31);
        }

        final int remainder = length % 31;
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedInt(remainder);
        }

        return value;
    }


    /**
     * Reads a <code>length</code> bit signed long.
     *
     * @param length bit length between 1 (exclusive) and 64 (inclusive)
     * @return a signed long
     * @throws IOException if an I/O error occurs.
     */
    public final long readLong(final int length) throws IOException {

        if (length <= 1) {
            throw new IllegalArgumentException("length(" + length + ") <= 1");
        }

        if (length > 64) {
            throw new IllegalArgumentException("length(" + length + ") > 64");
        }

        long value = 0L;
        if (readBoolean()) {
            value = (-1L << (length - 1));
        }

        value |= readUnsignedLong(length - 1);

        return value;
    }


    /**
     * Reads a 64-bit signed long.
     *
     * @return a 64-bit sigend long
     * @throws IOException if an I/O error occurs.
     */
    public final long readLong() throws IOException {
        return readLong(64);
    }


    /*
     * Reads an signed long in little endian byte order.
     *
     * @param length byte length between 0 (exclusive) and 8 (inclusive).
     * @return an long value in little endian byte order
     * @throws IOException if an I/O error occurs
    public long readLongLE(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length <= 0");
        }

        if (length > 8) {
            throw new IllegalArgumentException("length > 8");
        }

        long value = 0L;
        for (int i = 0; i < length; i++) {
            value |= (readUnsignedByte(8) << (i * 8));
        }

        return value;
    }
     */


    /**
     * Reads a 64-bit signed floating-point value.
     *
     * @return a double
     * @throws IOException if an I/O error occurs.
     */
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }


    /**
     * Returns the number of bits consumed so far.
     *
     * @return number of bits consumed so far.
     */
    public final long getCount() {
        return count;
    }


    /**
     * Align to <code>length</code> bits by reading some dummy bits if required.
     *
     * @param length bit length greater than 0
     * @throws IOException if an I/O error occurs
     * @return the number bits padded for octet alignment
     */
    public final int align(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        final int mod = (int) (count % length);
        if (mod == 0) {
            return 0;
        }

        final int required = length - mod;

        final int quotient = required / 8;
        for (int i = 0; i < quotient; i++) {
            readUnsignedByte(8);
        }

        final int remainder = required % 8;
        if (remainder > 0) {
            readUnsignedByte(remainder);
        }

        return mod;
    }


    /**
     * Align to 8 bits.
     *
     * @return this object
     * @throws IOException if an I/O error occurs
     */
    public final int align() throws IOException {
        return align(8);
    }


    /**
     * <b>Deprecated</b>;use {@link #readModifiedUTF8String()}.
     * Reads a string in modifed UTF-8 encoding.
     *
     * @return a string
     * @throws IOException if an I/O error occurs.
     * @see java.io.DataInput#readUTF()
     */
    public final String readUTF() throws IOException {
        return readModifiedUTF8String();
    }


    /**
     * Reads a string in modified UTF-8 encoding.
     *
     * @return a string
     * @throws IOException if an I/O error occurs.
     * @see java.io.DataInput#readUTF()
     */
    public final String readModifiedUTF8String() throws IOException {
        final byte[] encoded = new byte[readUnsignedShort(16)];
        readBytes(encoded);
        return new String(ModifiedUTF8.decode(encoded));
    }


    /**
     * Reads a String encoded in US-ASCII.
     *
     * @return a String
     * @throws IOException if an I/O error occurs.
     */
    public final String readUSASCIIString() throws IOException {
        return new String(readUSASCIIBytes(), "US-ASCII");
    }


    /**
     * Reads a sequence of US-ASCII bytes.
     *
     * @return an array of bytes
     * @throws IOException if an I/O error occurs.
     */
    public final byte[] readUSASCIIBytes() throws IOException {

        final byte[] value = new byte[readUnsignedInt(31)];

        for (int i = 0; i < value.length; i++) {
            value[i] = (byte) readUnsignedByte(7);
        }

        return value;
    }


    /*
     *
     * @param m
     * @param n
     * @return an unsigned fixed point number denoted Q(m, n)
     * @throws IOException if an I/O error occurs.
    public float readUnsignedFixedPoint(int m, int n) throws IOException {

        if (m < 0) {
            throw new IllegalArgumentException("m(" + m + ") < 0");
        }

        if (n < 0) {
            throw new IllegalArgumentException("n(" + n + ") < 0");
        }

        if (m == 0 && n == 0) {
            throw new IllegalArgumentException("m == 0 && n == 0");
        }

        if (m + n > 0x20) {
            throw new IllegalArgumentException
                ("m(" + m + ") + n(" + n + ") > 0x20");
        }

        return readUnsignedInt(m + n) / new Double(Math.pow(2, n)).floatValue();
    }
     */


    /*
     * Reads a signed fixed-point.
     *
     * @param m integer bit length (excluding the sign bit) (m >= 0)
     * @param n fraction bit length (n >= 0)
     * @return Q(m, n)
     * @throws IOException if an I/O error occurs
    public float readFixedPoint(int m, int n) throws IOException {

        if (m < 0) {
            throw new IllegalArgumentException("m(" + m + ") < 0");
        }

        if (n < 0) {
            throw new IllegalArgumentException("n(" + n + ") < 0");
        }

        if (m == 0 && n == 0) {
            throw new IllegalArgumentException("m == 0 && n == 0");
        }

        if (m + n >= 0x20) {
            throw new IllegalArgumentException
                ("m(" + m + ") + n(" + n + ") >= 0x20");
        }

        return readInt(1 + m + n) / new Double(Math.pow(2, n)).floatValue();
    }
     */



    /*
    public double readUnsignedLongFixedPoint(int m, int n) throws IOException {

        if (m < 0) {
            throw new IllegalArgumentException("m(" + m + ") < 0");
        }

        if (n < 0) {
            throw new IllegalArgumentException("n(" + n + ") < 0");
        }

        if (m == 0 && n == 0) {
            throw new IllegalArgumentException("m == 0 && n == 0");
        }

        if (m + n > 0x40) {
            throw new IllegalArgumentException
                ("m(" + m + ") + n(" + n + ") > 0x40");
        }

        return readUnsignedLong(m + n) / Math.pow(2, n);
    }
     */


    /*
     * Reads a signed fixed-point.
     *
     * @param m integer bit length (excluding the sign bit) (m >= 0)
     * @param n fraction bit length (n >= 0)
     * @return Q(m, n)
     * @throws IOException if an I/O error occurs
    public double readLongFixedPoint(int m, int n) throws IOException {

        if (m < 0) {
            throw new IllegalArgumentException("m(" + m + ") < 0");
        }

        if (n < 0) {
            throw new IllegalArgumentException("n(" + n + ") < 0");
        }

        if (m == 0 && n == 0) {
            throw new IllegalArgumentException("m == 0 && n == 0");
        }

        if (m + n >= 0x40) {
            throw new IllegalArgumentException
                ("m(" + m + ") + n(" + n + ") >= 0x40");
        }

        return readLong(1 + m + n) / Math.pow(2, n);
    }
     */


    /*
    private float readFraction(int n) throws IOException {
        float value = .0f;

        float fraction = .5f;
        for (int i = 0; i < n; i++) {
            if (readBoolean()) {
                value += fraction;
            }
            fraction /= 2.0f;
        }

        return value;
    }
     */


    /** the underlying octet input. */
    private OctetInput input;

    /** the current octet. */
    private int octet = 0x00;

    /** the number of available bits in current octet. */
    private int avail = 0x00;

    /** the number of bits consumed so far. */
    private long count = 0L;
}