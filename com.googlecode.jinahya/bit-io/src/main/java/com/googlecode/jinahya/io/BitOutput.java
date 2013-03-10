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


import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;


/**
 * BitInput.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutput {


    /**
     * An interface for writing bytes.
     */
    public interface ByteOutput {


        /**
         * Writes an unsigned byte.
         *
         * @param value value to write
         *
         * @throws IOException if an I/O error occurs.
         */
        void writeUnsignedByte(final int value) throws IOException;


    }


    /**
     *
     */
    public static class ByteOutputStream implements ByteOutput {


        /**
         * '
         *
         * @param stream
         */
        public ByteOutputStream(final OutputStream stream) {
            super();

            if (stream == null) {
                throw new NullPointerException("null stream");
            }

            this.stream = stream;
        }


        public void writeUnsignedByte(final int value) throws IOException {
            stream.write(value);
        }


        protected final OutputStream stream;


    }


    /**
     * Creates a new instance.
     *
     * @param output target octet output
     */
    public BitOutput(final ByteOutput output) {
        super();

        if (output == null) {
            throw new NullPointerException("null output");
        }

        this.output = output;
    }


    public BitOutput(final OutputStream stream) {
        this(new ByteOutputStream(stream));
    }


    /**
     * Writes an unsigned byte value. This method doesn't check the validity of
     * the value and writes the lower
     * <code>length</code> bits in
     * <code>value</code>.
     *
     * @param length bit length between 1 (inclusive) and {@value
     * java.lang.Byte#SIZE} (inclusive).
     * @param value the value to write
     *
     * @throws IOException if an I/O error occurs.
     */
    protected void writeUnsignedByte(final int length, int value)
        throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        if (length > 0x08) {
            throw new IllegalArgumentException("length(" + length + ") > 0x08");
        }

        final int required = length - (0x08 - index);
        if (required > 0) {
            writeUnsignedByte(length - required, value >> required);
            writeUnsignedByte(required, value);
            return;
        }

        for (int i = index + length - 1; i >= index; i--) {
            bitset.set(i, ((value & 0x01) == 0x01 ? true : false));
            value >>= 1;
        }
        index += length;

        if (index == 0x08) {
            int octet = 0x00;
            for (int i = 0x07; i >= 0; i--) {
                octet <<= 1;
                octet |= (bitset.get(i) ? 0x01 : 0x00);
            }
            output.writeUnsignedByte(octet);
            count++;
            index = 0x00;
        }
    }


    /**
     * Writes a 1-bit boolean value. Only one bit is used; 0x01 for true, 0x00
     * for false.
     *
     * @param value the boolean value to write
     *
     * @throws IOException if an I/O error occurs
     */
    public void writeBoolean(final boolean value) throws IOException {
        writeUnsignedByte(1, value ? 0x01 : 0x00);
    }


    /**
     * Writes an {@code length}-bit unsigned {@code short}. Only the lower
     * {@code length} bits in {@code value} are written.
     *
     * @param length bit length between 1 (exclusive) and {@value
     * java.lang.Short#SIZE} (inclusive)
     * @param value value to write
     *
     * @throws IOException if an I/O error occurs
     */
    protected void writeUnsignedShort(final int length, final int value)
        throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        if (length > 0x10) {
            throw new IllegalArgumentException("length(" + length + ") > 0x10");
        }

        final int quotient = length / 0x08;
        final int remainder = length % 0x08;

        if (remainder > 0) {
            writeUnsignedByte(remainder, value >> (quotient * 0x08));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedByte(0x08, value >> (0x08 * i));
        }
    }


    /**
     * Writes an unsigned int value. An
     * <code>IllegalArgumentException</code> will be thrown if
     * <code>length</code> or
     * <code>value</code> is out of valid range.
     *
     * @param length bit length between 1 (inclusive) and 32 (exclusive)
     * @param value the unsigned int value to be written
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedInt(final int length, final int value)
        throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        if (length > 0x1F) {
            throw new IllegalArgumentException("length(" + length + ") > 0x1F");
        }

        final int quotient = length / 0x10;
        final int remainder = length % 0x10;

        if (remainder > 0) {
            writeUnsignedShort(remainder, value >> (quotient * 0x10));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(Short.SIZE, value >> (0x10 * i));
        }
    }


    /**
     * Writes a {@code length}-bit signed int.
     *
     * @param length bit length between 1 (exclusive) and 32 (inclusive).
     * @param value signed int value
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeInt(final int length, final int value) throws IOException {

        if (length < 0x02) {
            throw new IllegalArgumentException("length(" + length + ") < 0x02");
        }

        if (length > 0x20) {
            throw new IllegalArgumentException("length(" + length + ") > 0x20");
        }

        writeBoolean(value < 0x00);

        writeUnsignedInt(length - 1, value);
    }


    /**
     * Writes a {@code float}.
     *
     * @param value the float value
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeFloat(final float value) throws IOException {
        writeInt(0x20, Float.floatToRawIntBits(value));
    }


    /**
     * Writes an unsigned long value.
     *
     * @param length bit length between 0x01 (inclusive) and 0x40 (exclusive)
     * @param value value to be written
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedLong(final int length, final long value)
        throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        if (length > 0x3F) {
            throw new IllegalArgumentException("length(" + length + ") > 0x3F");
        }

        final int quotient = length / 0x1F;
        final int remainder = length % 0x1F;

        if (remainder > 0) {
            writeUnsignedInt(remainder, (int) (value >> (quotient * 0x1F)));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedInt(0x1F, (int) (value >> (i * 0x1F)));
        }
    }


    /**
     * Writes a signed long
     * <code>value</code> in
     * <code>length</code> bits.
     *
     * @param length bit length between 1 (exclusive) and {@value
     * java.lang.Long#SIZE} (inclusive).
     * @param value value to be written
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeLong(final int length, final long value)
        throws IOException {

        if (length < 0x02) {
            throw new IllegalArgumentException("length(" + length + ") < 0x02");
        }

        if (length > 0x40) {
            throw new IllegalArgumentException("length(" + length + ") > 0x40");
        }

        final int quotient = length / 0x1F;
        final int remainder = length % 0x1F;

        if (remainder > 0) {
            writeUnsignedInt(remainder, (int) (value >> (quotient * 0x1F)));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedInt(0x1F, (int) (value >> (i * 0x1F)));
        }
    }


    /**
     * Writes a double value.
     *
     * @param value the value
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeDouble(final double value) throws IOException {
        writeLong(0x40, Double.doubleToRawLongBits(value));
    }


    /**
     * Writes a byte array. First, a 31-bit unsigned integer is written for the
     * byte count. And each byte is written.
     *
     * @param value the byte array to be written
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeBytes(final byte[] value) throws IOException {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        if (value.length > 65536) {
            throw new IllegalArgumentException(
                "too long value: " + value.length);
        }

        writeUnsignedShort(0x10, value.length); // 31

        for (int i = 0; i < value.length; i++) {
            writeUnsignedByte(Byte.SIZE, value[i] & 0xFF);
        }
    }


    /**
     * Aligns to given {@code length} bytes.
     *
     * @param length the number of bytes to align
     *
     * @return the bits padded to align
     *
     * @throws IOException if an I/O error occurs.
     */
    public int align(final int length) throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        int bits = 0;

        if (index > 0) { // bit index to write
            bits = (0x08 - index);
            writeUnsignedByte(bits, 0x00);
        }

        int octets = count % length;

        if (octets == 0) {
            return bits;
        }

        if (octets > 0) {
            octets = length - octets;
        } else { // mod < 0
            octets = 0 - octets;
        }

        for (; octets > 0; octets--) {
            writeUnsignedByte(0x08, 0x00);
            bits += 0x08;
        }

        return bits;
    }


    /**
     * Returns the number of bits available for writing in current octet.
     *
     * @return available bits for writing
     */
    public final int available() {
        return 0x08 - index;
    }


    /**
     * target output.
     */
    protected final ByteOutput output;


    /**
     * current byte value.
     */
    private final BitSet bitset = new BitSet(0x08);


    /**
     * bit index to write.
     */
    private int index = 0x00;


    /**
     * bytes written so far.
     */
    private int count = 0x00;


}

