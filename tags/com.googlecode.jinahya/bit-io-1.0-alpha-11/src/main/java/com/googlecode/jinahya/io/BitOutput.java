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
import java.io.IOException;
import java.io.OutputStream;
import java.io.UTFDataFormatException;


/**
 * BitInput.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutput extends BitIOBase {


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
     * @param length bit length between 1 (inclusive) and
     * {@value java.lang.Byte#SIZE} (inclusive).
     * @param value the value to write
     * @throws IOException if an I/O error occurs.
     */
    protected void writeUnsignedByte(final int length, int value)
        throws IOException {

        if (length < ONE) {
            throw new IllegalArgumentException(
                "length(" + length + ") < " + ONE);
        }

        if (length > Byte.SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") > " + Byte.SIZE);
        }

        final int required = length - (Byte.SIZE - index);
        if (required > 0) {
            writeUnsignedByte(length - required, value >> required);
            writeUnsignedByte(required, value);
            return;
        }

        for (int i = length - 1; i >= 0; i--) {
            setBit(index + i, (value & 0x01) == 0x01);
            value >>= 1;
        }
        index += length;

        if (index == Byte.SIZE) {
            out.write(getOctet());
            increaseCount();
            index = 0;
        }
    }


    /**
     * Writes a 1-bit boolean value. Only one bit is used; 0x01 for true, 0x00
     * for false.
     *
     * @param value the boolean value to write
     * @throws IOException if an I/O error occurs
     */
    public void writeBoolean(final boolean value) throws IOException {
        writeUnsignedByte(ONE, value ? ONE : ZERO);
    }


    /**
     * Writes a 1-bit boolean null flag for given <code>value</code>.
     *
     * @param value value whose null flag is written.
     * @return true is given <code>value</code> is null and the value must not
     * be serialized; false otherwise.
     * @throws IOException if an I/O error occurs.
     */
    protected boolean isNull(final Object value) throws IOException {

        final boolean isNull = value == null;

        writeBoolean(isNull);

        return isNull;
    }


    /**
     * Writes a 1-bit boolean null flag for given <code>value</code>.
     *
     * @param value the value be checked
     * @return true if object is not null and should be serialized; false
     * otherwise.
     * @throws IOException if an I/O error occurs.
     */
    protected boolean isNotNull(final Object value) throws IOException {

        return !isNull(value);
    }


    /**
     * Writes a 1-bit Boolean value. FIrst, a 1-bit boolean value is written for
     * null flag, And, if not null, the value is written via
     * {@link #writeBoolean(boolean)} with its <code>booleanValue()</code>.
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
     * @param length bit length between 1 (exclusive) and
     * {@value java.lang.Short#SIZE} (inclusive)
     * @param value value to write
     * @throws IOException if an I/O error occurs
     */
    protected void writeUnsignedShort(final int length, final int value)
        throws IOException {

        if (length < ONE) {
            throw new IllegalArgumentException(
                "length(" + length + ") < " + ONE);
        }

        if (length > Integer.SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") > " + Integer.SIZE);
        }

        final int quotient = length / Byte.SIZE;
        final int remainder = length % Byte.SIZE;

        if (remainder > 0) {
            writeUnsignedByte(remainder, value >> (quotient * Byte.SIZE));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedByte(Byte.SIZE, value >> (Byte.SIZE * i));
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

        if (length < ONE) {
            throw new IllegalArgumentException(
                "length(" + length + ") < " + ONE);
        }

        if (length >= Integer.SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") >= " + Integer.SIZE);
        }

        if ((value >> length) != ZERO) {
            throw new IllegalArgumentException(
                "value(" + value + ") >> length(" + length + ") != " + ZERO);
        }

        final int quotient = length / Short.SIZE;
        final int remainder = length % Short.SIZE;

        if (remainder > 0) {
            writeUnsignedShort(remainder, value >> (quotient * Short.SIZE));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(Short.SIZE, value >> (Short.SIZE * i));
        }
    }


    /**
     * Writes a <code>length</code>-bit signed int.
     *
     * @param length bit length between 1 (exclusive) and 32 (inclusive).
     * @param value signed int value
     * @throws IOException if an I/O error occurs.
     */
    public void writeInt(final int length, final int value)
        throws IOException {

        if (length <= ONE) {
            throw new IllegalArgumentException(
                "length(" + length + ") <= " + ONE);
        }

        if (length > Integer.SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") > " + Integer.SIZE);
        }

        if (length < Integer.SIZE) {
            if (value < 0) {
                if ((value >> length) != -1) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> length(" + length + ") != -1");
                }
            } else {
                if ((value >> length) != 0) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> length(" + length + ") != 0");
                }
            }
        }

        final int quotient = length / Short.SIZE;
        final int remainder = length % Short.SIZE;

        if (remainder > 0) {
            writeUnsignedShort(remainder, value >> (quotient * Short.SIZE));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(Short.SIZE, value >> (Short.SIZE * i));
        }
    }


    /**
     * Writes a <code>length</code>-bit unsigned Integer. First, a 1-bit boolean
     * value is written for a null flag. And, if the <code>value</code> is not
     * null, the value of <code>intValue()</code> is written via
     * {@link #writeUnsignedInt(int, int)} with specified <code>length</code>
     * and the value's <code>intValue()</code>.
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
        writeInt(Integer.SIZE, Float.floatToRawIntBits(value));
    }


    /**
     * Writes a <code>Float</code> value. First, a 1-bit boolean is written for
     * the null flag. And, if the value is not null, the value of
     * <code>floatValue()</code> is written via {@link #writeFloat(float)}.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     * @see #writeFloat(float)
     */
    public void writeFLOAT(final Float value) throws IOException {

        if (isNotNull(value)) {
            writeFloat(value.floatValue());
        }
    }


    /**
     * Writes an unsigned long value
     *
     * @param length bit length between 0x01 (inclusive) and 0x40 (exclusive)
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedLong(final int length, final long value)
        throws IOException {

        if (length < ONE) {
            throw new IllegalArgumentException(
                "length(" + length + ") < " + ONE);
        }

        if (length >= Long.SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") >= " + Long.SIZE);
        }

        if ((value >> length) != 0L) {
            throw new IllegalArgumentException(
                "value(" + value + ") >> length(" + length + ") != 0L");
        }

        final int quotient = length / Short.SIZE;
        final int remainder = length % Short.SIZE;

        if (remainder > 0) {
            writeUnsignedShort(
                remainder,
                (int) ((value >> (quotient * Short.SIZE)) & 0xFFFF));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(
                Short.SIZE,
                (int) ((value >> (Short.SIZE * i)) & 0xFFFF));
        }
    }


    /**
     * Writes a signed long <code>value</code> in <code>length</code> bits.
     *
     * @param length bit length between 1 (exclusive) and
     * {@value java.lang.Long#SIZE} (inclusive).
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeLong(final int length, final long value)
        throws IOException {

        if (length <= ONE) {
            throw new IllegalArgumentException(
                "length(" + length + ") <= " + ONE);
        }

        if (length > Long.SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") > " + Long.SIZE);
        }

        if (length < Long.SIZE) {
            if (value < 0L) {
                if ((value >> length) != -1L) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> length(" + length
                        + ") != -1L");
                }
            } else {
                if ((value >> length) != 0L) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> length(" + length
                        + ") != 0L");
                }
            }
        }

        final int quotient = length / Short.SIZE;
        final int remainder = length % Short.SIZE;

        if (remainder > 0) {
            writeUnsignedShort(
                remainder,
                (int) ((value >> (quotient * Short.SIZE)) & 0xFFFF));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(
                Short.SIZE, (int) ((value >> (Short.SIZE * i)) & 0xFFFF));
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
        writeLong(Long.SIZE, Double.doubleToRawLongBits(value));
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
     * Writes a non-null byte array. First, a 31-bit unsigned integer is written
     * for the byte count. And each byte is written.
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
            writeUnsignedByte(Byte.SIZE, value[i] & 0xFF);
        }
    }


    /**
     * Writes a nullable byte array. First, a 1-bit boolean is written
     * for a null flag. And, if the <code>value</code> is not null, the value is
     * written via {@link #writeBytes(byte[])}.
     *
     * @param value the byte array to be written
     * @throws IOException if an I/O error occurs.
     * @see #writeBytes(byte[])
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

        if (isNotNull(value)) {
            final byte[] bytes = value.getBytes("US-ASCII");
            writeUnsignedInt(0x1F, bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 0) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") is not an ASCII String");
                }
                writeUnsignedByte(0x07, bytes[i] & 0xFF);
            }
        }
    }


    /**
     * Writes a UTF string. Identical to
     * {@link java.io.DataOutput#writeUTF(String)}.
     *
     * @param value string to write
     * @throws IOException if an I/O error occurs
     * @see java.io.DataOutput#writeUTF(String)
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
        writeUnsignedInt(Short.SIZE, bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            writeUnsignedByte(Byte.SIZE, bytes[i]);
        }
    }


    /**
     * Writes a non-null String.
     *
     * @param value value to be written
     * @param charsetName charset name.
     * @throws IOException if an I/O error occurs.
     * @see #writeBytes(byte[])
     */
    public void writeString(final String value, final String charsetName)
        throws IOException {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        writeBytes(value.getBytes(charsetName));
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
     * @see #writeBytes(byte[])
     */
    public void writeSTRING(final String value, final String charsetName)
        throws IOException {

        if (isNotNull(value)) {
            writeString(value, charsetName);
        }
    }


    /**
     * Aligns to given <code>length</code> as bytes.
     *
     * @param length the number of bytes to align
     * @return the bits padded to align
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int align(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        int bits = 0;

        if (index > 0) { // bit index to write
            bits = (Byte.SIZE - index);
            writeUnsignedByte(bits, 0);
        }

        int octets = getCount() % length;

        if (octets == 0) {
            return bits;
        }

        if (octets > 0) {
            octets = length - octets;
        } else { // mod < 0
            octets = 0 - octets;
        }

        for (; octets > 0; octets--) {
            writeUnsignedByte(Byte.SIZE, 0);
            bits += Byte.SIZE;
        }

        return bits;
    }


    /**
     * Returns the available bits for writing in current octet.
     *
     * @return available bits for writing in current octet.
     */
    @Override
    public int available() {
        return Byte.SIZE - index;
    }


    /** target output. */
    private final OutputStream out;


    /** bit index to write. */
    private int index = 0;


}
