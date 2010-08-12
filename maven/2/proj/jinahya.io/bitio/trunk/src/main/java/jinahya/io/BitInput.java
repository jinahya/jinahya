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


/**
 * Class for bit level input.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInput {


    /**
     * The interface for octet input.
     *
     * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
     */
    public static interface OctetInput {


        /**
         * Reads an octet from desired input.
         *
         * @return octet value between 0(inclusive) and 255(inclusive) or -1 if
         *         EOF
         * @throws IOException if an I/O error occurs
         */
        int readOctet() throws IOException;
    }


    /**
     * Creates a new instance with specified input.
     *
     * @param in the input stream to read octets.
     */
    public BitInput(final InputStream input) {
        this(new OctetInput() {
            @Override
            public int readOctet() throws IOException {
                return input.read();
            }
        });
    }


    /**
     * Creates a new instance with specified input.
     *
     * @param in the octet input to read octets.
     */
    public BitInput(final OctetInput input) {
        super();

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        this.input = input;
    }


    /**
     * Reads one bit and returns true if it is 0x01.
     *
     * @return true for 0x01, false otherwise(0x00)
     * @throws IOException if an I/O error occurs.
     */
    public boolean readBoolean() throws IOException {
        return (read8(1) == 0x01);
    }


    /**
     * See {@link java.io.DataInput#readFully(byte[])}.
     *
     * @param value the buffer into which data is read.
     * @throws IOException if an I/O error occurs.
     * @see #readBytes(byte[], int, int)
     */
    public byte[] readBytes() throws IOException {
        final byte[] value = new byte[readInt()];
        for (int i = 0; i < value.length; i++) {
            value[i] = (byte) read8(8);
        }
        return value;
    }


    /**
     * Reads an unsigned <code>length</code> bit byte.
     *
     * @param length bit length between 1 (inclusive) and 8 (inclusive).
     * @return an unsigned <code>length</code> bit integer.
     * @throws IOException if an I/O error occurs.
     */
    private int read8(final int length) throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " < 1");
        }

        if (length > 0x08) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " > 8");
        }

        if (avail == 0x00) {
            octet = input.readOctet();
            if (octet == -1) {
                throw new EOFException("EOF");
            }
            avail = 0x08;
        }

        if (avail >= length) {
            int value = octet >> (avail - length);
            avail -= length;
            count += length;
            octet ^= (value << avail);
            return value;
        } else {
            final int requi = length - avail;
            return ((read8(avail) << requi)
                    | read8(requi));
        }
    }


    /**
     * Reads an unsigned <code>length</code> bit short.
     *
     * @param length bit length between 1 (inclusive) and 16 (inclusive).
     * @return an unsigned <code>length</code> bit integer.
     * @throws IOException if an I/O error occurs
     */
    private int read16(final int length) throws IOException {

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
            value |= read8(8);
        }

        int remainder = length % 8;
        if (remainder > 0) {
            value <<= remainder;
            value |= read8(remainder);
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
    public int readUnsignedInt(final int length) throws IOException {

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
            value |= read16(16);
        }

        final int remainder = length % 16;
        if (remainder > 0) {
            value <<= remainder;
            value |= read16(remainder);
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
    public int readInt(final int length) throws IOException {

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
    public int readInt() throws IOException {
        return readInt(32);
    }


    /**
     * Read a 32-bit signed floating-point value.
     *
     * @return a float
     * @throws IOException if an I/O error occurs.
     */
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }


    /**
     * Reads a <code>length</code> bit unsigned long.
     *
     * @param length bit length between 0 (exclusive) and 64 (exclusive).
     * @return an unsigned long
     * @throws IOException if an I/O error occurs.
     */
    public long readUnsignedLong(final int length) throws IOException {

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
    public long readLong(final int length) throws IOException {

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
    public long readLong() throws IOException {
        return readLong(64);
    }


    /**
     * Reads a 64-bit signed floating-point value.
     *
     * @return a double
     * @throws IOException if an I/O error occurs.
     */
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }


    /**
     * Returns the number of bits consumed so far.
     *
     * @return number of bits consumed so far.
     */
    public long getCount() {
        return count;
    }


    /**
     * Align to <code>length</code> bits by reading some dummy bits if required.
     *
     * @param length bit length greater than 0
     * @throws IOException if an I/O error occurs
     * @return the number of padded bits
     */
    public int align(final int length) throws IOException {

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
            read8(8);
        }

        final int remainder = required % 8;
        if (remainder > 0) {
            read8(remainder);
        }

        return mod;
    }


    /**
     * Align to 8 bits.
     *
     * @return number of padded bits
     * @throws IOException if an I/O error occurs
     */
    public int align() throws IOException {
        return align(8);
    }


    /** the underlying octet input. */
    private OctetInput input;

    /** the current octet. */
    private int octet = 0x00;

    /** the number of available bits in current octet. */
    private int avail = 0x00;

    /** the number of bits consumed so far. */
    private long count = 0L;
}