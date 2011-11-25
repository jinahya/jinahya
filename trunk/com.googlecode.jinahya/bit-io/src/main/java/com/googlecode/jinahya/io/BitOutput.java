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


    /** octet length. */
    private static int OCTET_SIZE = 0x08;


    /** short length. */
    private static int SHORT_SIZE = 0x10;


    /** integer length. */
    private static int INTEGER_SIZE = 0x20;


    /** long length. */
    private static int LONG_SIZE = 0x40;


    /**
     * Creates a new instance.
     *
     * @param out target octet output
     */
    public BitOutput(OutputStream out) {
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
     * @param value the value to write
     * @throws IOException if an I/O error occurs.
     */
    protected void writeUnsignedByte(final int length, final int value)
        throws IOException {

        if (length <= 0x00) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        if (length > 0x08) {
            throw new IllegalArgumentException("length(" + length + ") > 8");
        }

        int required = length - (0x08 - index);
        if (required > 0x00) {
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
        if (index == 0x08) {
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
     * Writes a 1-bit boolean value. Only one bit is used; 1 for true 0 for
     * false.
     *
     * @param value the boolean value to write
     * @throws IOException if an I/O error occurs
     */
    public void writeBoolean(final boolean value) throws IOException {
        writeUnsignedByte(0x01, value ? 0x01 : 0x00);
    }


    /**
     * Writes a null flag for given <code>value</code>.
     *
     * @param value the value be checked
     * @return true if object is not null and should be serialized; false
     * otherwise.
     * @throws IOException if an I/O error occurs.
     */
    private boolean isNotNull(final Object value) throws IOException {

        final boolean isNull = value == null;

        writeBoolean(isNull);

        return !isNull;
    }


    /**
     * Writes a 1-bit Boolean value. FIrst, a 1-bit boolean value is written for
     * null flag, And, if not null, a 1-bit boolean value is written.
     *
     * @param value the value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeBOOLEAN(final Boolean value) throws IOException {

        if (isNotNull(value)) {
            writeBoolean(value.booleanValue());
        }
    }


    /**
     * Writes an unsigned short value. This method doesn't check the validity of
     * the value and writes the lower <code>length</code> bits in
     * <code>value</code>.
     *
     * @param length bit length between 1 (inclusive) and 16 (inclusive)
     * @param value value to write
     * @throws IOException if an I/O error occurs
     */
    protected void writeUnsignedShort(final int length, final int value)
        throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length > 16) {
            throw new IllegalArgumentException("length(" + length + ") > 16");
        }

        int quotient = length / 8;
        int remainder = length % 8;

        if (remainder > 0) {
            writeUnsignedByte(remainder, value >> (quotient * 8));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedByte(8, value >> (8 * i));
        }
    }


    /**
     * Writes an unsigned int value. An <code>IllegalArgumentException</code>
     * will be thrown if <code>length</code> or <code>value</code> is out of
     * valid range.
     *
     * @param length bit length between 1 (inclusive) and 32 (exclusive)
     * @param value the unsigned int value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedInt(final int length, final int value)
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

        int quotient = length / 0x10;
        int remainder = length % 0x10;

        if (remainder > 0) {
            writeUnsignedShort(remainder, value >> (quotient * 0x10));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(0x10, value >> (0x10 * i));
        }
    }


    /**
     * Writes a <code>length</code>-bit signed int.
     *
     * @param length bit length between 1 (exclusive) and {@value #INTEGER_SIZE}
     * (inclusive).
     * @param value signed int value
     * @throws IOException if an I/O error occurs.
     */
    public void writeInt(final int length, final int value)
        throws IOException {

        if (length <= 0x01) {
            throw new IllegalArgumentException("length(" + length + ") <= 1");
        }

        if (length > INTEGER_SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") > " + INTEGER_SIZE);
        }

        if (length < INTEGER_SIZE) {
            if (value < 0x00) {
                if ((value >> length) != -1) {
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

        int quotient = length / 0x10;
        int remainder = length % 0x10;

        if (remainder > 0) {
            writeUnsignedShort(remainder, value >> (quotient * 0x10));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(0x10, value >> (0x10 * i));
        }
    }


    /**
     * Writes a <code>length</code>-bit unsigned Integer. First, a 1-bit boolean
     * value is written for a null flag. And, if the <code>value</code> is not
     * null, the value of <code>intValue()</code> is written via
     * {@link #writeUnsignedInt(int, int)}.
     *
     * @param length the bit length; See {@link #writeUnsignedInt(int, int)} for
     * valid range.
     * @param value the value to be written
     * @throws IOException if an I/O error occurs.
     * @see #writeUnsignedInt(int, int)
     */
    public void writeUnsignedINTEGER(final int length, final Integer value)
        throws IOException {

        if (isNotNull(value)) {
            writeUnsignedInt(length, value.intValue());
        }
    }


    /**
     * Writes a <code>length</code>-bit signed Integer. First, a 1-bit boolean
     * value is written for a null flag. And, if the <code>value</code> is not
     * null, the value of <code>intValue()</code> is written via
     * {@link #writeInt(int, int)}.
     *
     * @param length the bit length; See {@link #writeInt(int, int)} for valid
     * range.
     * @param value the value to be written
     * @throws IOException if an I/O error occurs.
     * @see #writeInt(int, int)
     */
    public void writeINTEGER(final int length, final Integer value)
        throws IOException {

        if (isNotNull(value)) {
            writeInt(length, value.intValue());
        }
    }


    /**
     * Writes a float value.
     *
     * @param value the float value
     * @throws IOException if an I/O error occurs.
     */
    public void writeFloat(final float value) throws IOException {
        writeInt(INTEGER_SIZE, Float.floatToRawIntBits(value));
    }


    /**
     * 
     * @param value
     * @throws IOException 
     */
    public void writeFLOAT(final Float value) throws IOException {

        if (isNotNull(value)) {
            writeFloat(value.floatValue());
        }
    }


    /**
     * Writes an unsigned long.
     *
     * @param length bit length between 1 (inclusive) and {@value #LONG_SIZE}
     * (exclusive)
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedLong(final int length, final long value)
        throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length >= LONG_SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") >= " + LONG_SIZE);
        }

        if ((value >> length) != 0L) {
            throw new IllegalArgumentException(
                "value(" + value + ") >> length(" + length + ") != 0L");
        }

        int quotient = length / 0x10;
        int remainder = length % 0x10;

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
     * @param length bit length between 1 (exclusive) and {@value #LONG_SIZE}
     * (inclusive).
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeLong(final int length, final long value)
        throws IOException {

        if (length <= 0x01) {
            throw new IllegalArgumentException("length(" + length + ") <= 1");
        }

        if (length > LONG_SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") > " + LONG_SIZE);
        }

        if (length < Long.SIZE) {
            if (value < 0L) {
                if ((value >> length) != -1L) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> length(" + length
                        + ") != -1L");
                }
            } else {
                if ((value >> length) != 0x00L) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> length(" + length
                        + ") != 0L");
                }
            }
        }

        final int quotient = length / SHORT_SIZE;
        final int remainder = length % SHORT_SIZE;

        if (remainder > 0x00) {
            writeUnsignedShort(
                remainder, (int) ((value >> (quotient * SHORT_SIZE)) & 0xFFFF));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(
                SHORT_SIZE, (int) ((value >> (SHORT_SIZE * i)) & 0xFFFF));
        }
    }


    /**
     * Writes a nullable unsigned Long value. First, a 1-bit boolean is written
     * for a null flag. And, if <code>value</code> is not null, the value of
     * <code>longValue()</code> is written via
     * {@link #writeUnsignedLong(int, long)}.
     *
     * @param length the bit length
     * @param value the value to be written.
     * @throws IOException if an I/O error occurs.
     * @see #writeUnsignedLong(int, long)
     */
    public void writeUnsignedLONG(final int length, final Long value)
        throws IOException {

        if (isNotNull(value)) {
            writeUnsignedLong(length, value.longValue());
        }
    }


    /**
     * Writes a nullable signed Long value. First, a 1-bit boolean is written
     * for a null flag. And, if <code>value</code> is not null, the value of
     * <code>longValue()</code> is written via {@link #writeLong(int, long)}.
     *
     * @param length the bit length
     * @param value the value to be written.
     * @throws IOException if an I/O error occurs.
     * @see #writeLong(int, long)
     */
    public void writeLONG(final int length, final Long value)
        throws IOException {

        if (isNotNull(value)) {
            writeLong(length, value.longValue());
        }
    }


    /**
     * Writes a double value.
     *
     * @param value the value
     * @throws IOException if an I/O error occurs.
     */
    public void writeDouble(final double value) throws IOException {
        writeLong(LONG_SIZE, Double.doubleToRawLongBits(value));
    }


    /**
     * Writes a nullable Double value. First, a 1-bit boolean is written
     * for a null flag. And, if <code>value</code> is not null, the value of
     * <code>doubleValue()</code> is written via {@link #writeDouble(double)}.
     *
     * @param value the value to be written
     * @throws IOException if an I/O error occurs.
     * @see #writeDouble(double)
     */
    public void writeDOUBLE(final Double value) throws IOException {

        if (isNotNull(value)) {
            writeDouble(value.doubleValue());
        }
    }


    /**
     * Writes a byte array.
     *
     * @param value the byte array to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeBytes(final byte[] value) throws IOException {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        writeUnsignedInt(0x1F, value.length); // 31

        for (int i = 0; i < value.length; i++) {
            writeUnsignedByte(0x08, value[i] & 0xFF);
        }
    }


    /**
     * Writes a nullable byte array. First, a 1-bit boolean is written
     * for a null flag. And, if the <code>value</code> is not null, the value is
     * written via {@link #writeBytes(byte[])}.
     *
     * @param value the byte array to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeBYTES(final byte[] value) throws IOException {

        if (isNotNull(value)) {
            writeBytes(value);
        }
    }


    /**
     * Writes a 7-bit ASCII string. First, a 1-bit boolean is written for a null
     * flag. And, if the <code>value</code> is not null, a 31-bit unsigned
     * integer is written for the character count following all characters which
     * each is written as 7-bit unsigned byte.
     *
     * @param value the ASCII string to be written
     * @throws IOException if an I/O error occurs
     */
    public void writeASCII(final String value) throws IOException {

        if (value != null) {
            final byte[] bytes = value.getBytes("US-ASCII");
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 0) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") is not an ASCII String");
                }
            }
        }

        if (isNotNull(value)) {
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
     * @deprecated Not for Bit I/O. Use {@link #writeSTRING(String, String)}
     */
    public void writeUTF(final String value) throws IOException {

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
     * Writes a nullable String. First, a 1-bit boolean is written for a null
     * flag. And, if <code>value</code> is not null, the value of
     * <code>getBytes(charsetName)</code> is written via
     * {@link #writeBytes(byte[])}.
     *
     * @param value the value to be written
     * @param charsetName character set name to serialize the String
     * @throws IOException if an I/O error occurs.
     */
    public void writeSTRING(final String value, final String charsetName)
        throws IOException {

        if (isNotNull(value)) {
            writeBytes(value.getBytes(charsetName));
        }
    }


    /**
     * Aligns to given <code>length</code> as bytes.
     *
     * @param length the number of bytes to align
     * @return the bits padded to align
     * @throws IOException if an I/O error occurs.
     */
    public int align(final int length) throws IOException {

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


    /** output target .*/
    private OutputStream out;


    /** bit set. */
    private BitSet set = new BitSet(8);


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

