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


import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UTFDataFormatException;

import java.util.BitSet;


/**
 * Bit-level wrapper for OutputStream.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutput {


    /**
     * Creates a new instance.
     *
     * @param out target octet output
     */
    public BitOutput(final OutputStream out) {
        super();

        if (out == null) {
            throw new NullPointerException("null out");
        }

        this.out = out;
    }


    /**
     * Writes an unsigned byte value. This method doesn't check the validity of
     * the value and writes the lower <code>length</code> bits in
     * <code>value</code>.
     *
     * @param length bit length between 0 (exclusive) and 8 (inclusive).
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    protected final void writeUnsignedByte(final int length, final int value)
        throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        if (length > 8) {
            throw new IllegalArgumentException("length(" + length + ") > 8");
        }

        final int required = length - (8 - index);
        if (required > 0) {
            writeUnsignedByte(length - required, value >> required);
            writeUnsignedByte(required, value);
            return;
        }

        index += length;
        for (int i = 0; i < length; i++) {
            index--;
            set.set(index, ((value >>> i) & 0x01) == 0x01);
        }

        index += length;
        if (index == 8) {
            int octet = 0x00;
            for (int i = 0; i < 8; i++) {
                octet <<= 1;
                octet |= (set.get(i) ? 0x01 : 0x00);
            }
            out.write(octet);
            count++;
            index = 0;
        }
    }


    /**
     * Writes an unsigned short value. This method doesn't check the validity of
     * the value and writes the lower <code>length<code> bits in
     * <code>value</code>.
     *
     * @param length bit length between 0 (exclusive) and 16 (inclusive)
     * @param value value to write
     * @throws IOException if an I/O error occurs
     */
    protected final void writeUnsignedShort(final int length, final int value)
        throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        if (length > 16) {
            throw new IllegalArgumentException("length(" + length + ") > 16");
        }

        final int quotient = length / 8;
        final int remainder = length % 8;

        if (remainder > 0) {
            writeUnsignedByte(remainder, value >> (quotient * 8));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedByte(8, value >> (8 * i));
        }
    }


    /**
     * Writes a boolean value. Only one bit is used; 1 for true 0 for false.
     *
     * @param value value
     * @throws IOException if an I/O error occurs
     */
    public final void writeBoolean(final boolean value) throws IOException {
        writeUnsignedByte(0x01, value ? 0x01 : 0x00);
    }


    /**
     * Writes an unsigned int value. An <code>IllegalArgumentException</code>
     * will be thrown if <code>length</code> or <code>value</code> is out of
     * valid range.
     *
     * @param length bit length between 1 (inclusive) and 32 (exclusive)
     * @param value unsigned int value
     * @throws IOException if an I/O error occurs.
     */
    public final void writeUnsignedInt(final int length, final int value)
        throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        if (length >= 0x20) {
            throw new IllegalArgumentException(
                "length(" + length + ") >= 0x20");
        }

        if ((value >> length) != 0x00) {
            throw new IllegalArgumentException(
                "value(" + value + ") >> length(" + length + ") != 0x00");
        }

        final int quotient = length / 0x10;
        final int remainder = length % 0x10;

        if (remainder > 0) {
            writeUnsignedShort(remainder, value >> (quotient * 0x10));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(0x10, value >> (0x10 * i));
        }
    }


    /**
     * Writes a signed int.
     *
     * @param length bit length between 1 (exclusive) and 32 (inclusive).
     * @param value signed int value
     * @throws IOException if an I/O error occurs.
     */
    public final void writeInt(final int length, final int value)
        throws IOException {

        if (length <= 0x01) {
            throw new IllegalArgumentException("length(" + length + ") <= 1");
        }

        if (length > 0x20) {
            throw new IllegalArgumentException("length(" + length + ") > 32");
        }

        if (length < 0x20) {
            if (value < 0x00) {
                if (value >> length != -1) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> length(" + length + ") != -1");
                }
            } else { // value >= 0x00
                if ((value >> length) != 0x00) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> length(" + length + ") != 0");
                }
            }
        }

        final int quotient = length / 0x10;
        final int remainder = length % 0x10;

        if (remainder > 0) {
            writeUnsignedShort(remainder, value >> (quotient * 0x10));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(0x10, value >> (0x10 * i));
        }
    }


    /**
     * Writes a 32-bit signed integer.
     *
     * @param value a 32-bit signed integer
     * @throws IOException if an I/O error occurs.
     */
    public final void writeInt(final int value) throws IOException {
        writeInt(0x20, value);
    }


    /**
     * Writes a 32-bit signed Integer.
     *
     * @param value value to write
     * @throws IOException if an I/O error occurs.
     */
    public void writeINTEGER(final Integer value) throws IOException {

        final boolean notNull = value != null;

        writeBoolean(notNull);

        if (notNull) {
            writeInt(value.intValue());
        }
    }


    /**
     * Writes a float value.
     *
     * @param value the float value
     * @throws IOException if an I/O error occurs.
     */
    public final void writeFloat(final float value) throws IOException {
        writeInt(Float.floatToRawIntBits(value));
    }


    public void writeFLOAT(final Float value) throws IOException {

        final boolean notNull = value != null;

        writeBoolean(notNull);

        if (notNull) {
            writeFloat(value.floatValue());
        }
    }


    /**
     * Writes an unsigned long.
     *
     * @param length bit length between 1 (inclusive) and 64 (exclusive)
     * @param value value to write
     * @throws IOException if an I/O error occurs.
     */
    public final void writeUnsignedLong(final int length, final long value)
        throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length >= 64) {
            throw new IllegalArgumentException("length(" + length + ") >= 64");
        }

        if ((value >> length) != 0) {
            throw new IllegalArgumentException(
                "value(" + value + ") >> length(" + length + ") != 0");
        }

        final int quotient = length / 0x10;
        final int remainder = length % 0x10;

        if (remainder > 0) {
            writeUnsignedShort(
                remainder, (int) ((value >> (quotient * 0x10)) & 0xFFFF));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(0x10, (int) ((value >> (0x10 * i)) & 0xFFFF));
        }
    }


    /**
     * Writes a signed long <code>value</code> in <code>length</code> bits.
     *
     * @param length bit length between 1 (exclusive) and 64 (inclusive).
     * @param value value to write
     * @throws IOException if an I/O error occurs.
     */
    public final void writeLong(final int length, final long value)
        throws IOException {

        if (length <= 0x01) {
            throw new IllegalArgumentException(
                "length(" + length + ") <= 0x01");
        }

        if (length > 0x40) {
            throw new IllegalArgumentException("length(" + length + ") > 0x40");
        }

        if (length < 64) {
            if (value < 0L) {
                if (value >> length != -1L) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> length(" + length
                        + ") != -1L");
                }
            } else {
                if ((value >> length) != 0x00L) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> length(" + length
                        + ") != 0x00L");
                }
            }
        }

        final int quotient = length / 0x10;
        final int remainder = length % 0x10;

        if (remainder > 0x00) {
            writeUnsignedShort(
                remainder, (int) ((value >> (quotient * 0x10)) & 0xFFFF));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(16, (int) ((value >> (16 * i)) & 0xFFFF));
        }
    }


    /**
     * Writes a 64-bit signed long value.
     *
     * @param value the value
     * @throws IOException if an I/O error occurs.
     */
    public final void writeLong(final long value) throws IOException {
        writeLong(0x40, value);
    }


    /**
     * 
     * @param value
     * @throws IOException 
     */
    public void writeLONG(final Long value) throws IOException {

        final boolean notNull = value != null;

        writeBoolean(notNull);

        if (notNull) {
            writeLong(value.longValue());
        }
    }


    /**
     * Writes a double value.
     *
     * @param value the value
     * @throws IOException if an I/O error occurs.
     */
    public final void writeDouble(final double value) throws IOException {
        writeLong(Double.doubleToRawLongBits(value));
    }


    /**
     * 
     * @param value
     * @throws IOException 
     */
    public void writeDOUBLE(final Double value) throws IOException {

        final boolean notNull = value != null;

        writeBoolean(notNull);

        if (notNull) {
            writeDouble(value.doubleValue());
        }
    }


    /**
     * Writes given <code>bytes</code>.
     *
     * @param bytes bytes to write
     * @throws IOException if an I/O error occurs.
     */
    public final void writeBytes(final byte[] bytes) throws IOException {

        final boolean notNull = bytes != null;

        writeBoolean(notNull);

        if (notNull) {
            writeNonNullBytes(bytes);
        }
    }


    /**
     * 
     * @param bytes
     * @throws IOException 
     */
    private final void writeNonNullBytes(final byte[] bytes)
        throws IOException {

        writeUnsignedInt(0x1F, bytes.length); // 31

        for (int i = 0; i < bytes.length; i++) {
            writeUnsignedByte(0x08, bytes[i] & 0xFF);
        }
    }


    /**
     * Writes a 7-bit ASCII string. Writes a 31-bit unsigned integer for the
     * character count following all characters which each is written as 7-bit
     * unsigned byte.
     *
     * @param value ASCII string to write
     * @throws IOException if an I/O error occurs
     */
    public final void writeASCII(final String value) throws IOException {

        final boolean notNull = value != null;

        writeBoolean(notNull);

        if (notNull) {
            final byte[] bytes = value.getBytes("US-ASCII");
            writeUnsignedInt(0x1F, bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                writeUnsignedByte(0x07, bytes[i] & 0xFF);
            }
        }
    }


    /**
     * Writes a UTF string. Identical to {@link DataOutput#writeUTF(String)}.
     *
     * @param value string to write
     * @throws IOException if an I/O error occurs
     * @see DataOutput#writeUTF(String)
     * @deprecated Use {@link #writeString(String, String)}
     */
    public final void writeUTF(final String value) throws IOException {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (int i = 0; i < value.length(); i++) {

            final char c = value.charAt(i);

            if (baos.size() > 65535) {
                throw new UTFDataFormatException("too long");
            }

            if (c >= '\u0001' && c <= '\u007F') {
                baos.write(c);
                continue;
            }

            if (c == '\u0000' || (c >= '\u0080' && c <= '\u07FF')) {
                baos.write((0xC0 | (0x1F & (c >> 6))));
                baos.write((0x80 | (0x3F & c)));
                continue;
            }

            if (c >= '\u0800' && c <= '\uFFFF') {
                baos.write((0xE0 | (0x0F & (c >> 12))));
                baos.write((0x80 | (0x3F & (c >> 6))));
                baos.write((0x80 | (0x3F & c)));
                continue;
            }
        }

        final byte[] bytes = baos.toByteArray();
        writeUnsignedInt(0x10, bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            writeUnsignedByte(0x08, bytes[i]);
        }
    }


    /**
     * Writes a <code>String</code>.
     *
     * @param value value to write
     * @param charsetName character set name to serialize the String
     * @throws IOException if an I/O error occurs.
     */
    public void writeString(final String value, final String charsetName)
        throws IOException {

        final boolean notNull = value != null;

        writeBoolean(notNull);

        if (notNull) {
            writeNonNullBytes(value.getBytes(charsetName));
        }
    }


    /**
     * Aligns to given <code>length</code> as bytes.
     *
     * @param length the number of bytes to align
     * @return the bits padded to align
     * @throws IOException if an I/O error occurs.
     */
    public final int align(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        int bits = 0;

        if (index > 0) { // bit index to write
            bits = (8 - index);
            writeUnsignedByte(8 - index, 0);
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
            writeUnsignedByte(8, 0);
            bits += 8;
        }

        return bits;
    }


    /**
     * Align to 1 byte.
     *
     * @return the number of bits padded to align.
     * @throws IOException if an I/O error occurs.
     */
    public final int align() throws IOException {
        return align(1);
    }


    /** output target .*/
    private final OutputStream out;


    /** bit set. */
    private final BitSet set = new BitSet(8);


    /** bit index to write. */
    private int index = 0;


    /** so far written octet count. */
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

