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


/**
 * BitInput.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInput extends BitIOBase {


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
    protected int readUnsignedByte(final int length) throws IOException {

        if (length <= ZERO) {
            throw new IllegalArgumentException(
                "length(" + length + ") <= " + ZERO);
        }

        if (length > Byte.SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") > " + Byte.SIZE);
        }

        if (index == Byte.SIZE) {
            final int octet = in.read();
            if (octet == -1) {
                throw new EOFException("eof");
            }
            increaseCount();
            setOctet(octet);
            index = ZERO;
        }

        final int required = length - (Byte.SIZE - index);

        if (required > ZERO) {
            return (readUnsignedByte(length - required) << required)
                   | readUnsignedByte(required);
        }

        int value = ZERO;
        for (int i = 0; i < length; i++) {
            value <<= 1;
            value |= (getBit(index + i) ? ONE : ZERO);
        }

        index += length;

        return value;
    }


    /**
     * Reads a 1-bit boolean value. ONE for true, ZERO for false.
     *
     * @return the read boolean value
     * @throws IOException if an I/O error occurs.
     */
    public boolean readBoolean() throws IOException {

        return readUnsignedByte(ONE) == ONE;
    }


    /**
     * Reads a 1-bit boolean value for a null flag.
     * @return true it null and the value must not be read; false otherwise.
     * @throws IOException if an I/O error occurs.
     */
    protected boolean isNull() throws IOException {
        return readBoolean();
    }


    /**
     * Reads a 1-bit boolean value for a null flag.
     *
     * @return true if not null and the value must be read; false otherwise.
     * @throws IOException if an I/O error occurs.
     */
    protected boolean isNotNull() throws IOException {
        return !isNull();
    }


    /**
     * Reads a nullable 1-bit Boolean value. Identical to
     * <code>readBOOLEAN(null)</code>.
     *
     * @return the read Boolean value or <code>null</code> if the null flag is
     * set
     * @throws IOException if an I/O error occurs.
     * @see #readBOOLEAN(Boolean)
     */
    public Boolean readBOOLEAN() throws IOException {

        return readBOOLEAN(null);
    }


    /**
     * Reads a 1-bit Boolean value. First, a 1-bit boolean value is read for a
     * null flag. And, if the flag is not set, the value read via
     * {@link #readBoolean()} is returned, If the null flag is set,
     * <code>defaultValue</code> is returned.
     *
     * @param defaultValue the default value to be returned if the null flag is
     * set
     * @return the Boolean value read or the <code>defaultValue</code> if null
     * flag is set
     * @throws IOException if an I/O error occurs.
     * @see #readBoolean()
     */
    public Boolean readBOOLEAN(final Boolean defaultValue) throws IOException {

        return isNull() ? defaultValue : Boolean.valueOf(readBoolean());
    }


    /**
     * Reads an unsigned short value.
     *
     * @param length bit length between ONE (inclusive) and Short.SIZE (inclusive).
     * @return the unsigned short value read.
     * @throws IOException if an I/O error occurs.
     */
    protected int readUnsignedShort(final int length) throws IOException {

        if (length < ONE) {
            throw new IllegalArgumentException("length(" + length + ") < ONE");
        }

        if (length > Short.SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") > " + Short.SIZE);
        }

        int quotient = length / Byte.SIZE;
        int remainder = length % Byte.SIZE;

        int value = ZERO;
        for (int i = 0; i < quotient; i++) {
            value <<= Byte.SIZE;
            value |= readUnsignedByte(Byte.SIZE);
        }

        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedByte(remainder);
        }

        return value;
    }


    /**
     * Reads an <code>length</code>-bit unsigned int value.
     *
     * @param length bit length between 1 (inclusive) and 32 (exclusive).
     * @return the unsigned int value read from the input
     * @throws IOException if an I/O error occurs
     */
    public int readUnsignedInt(final int length) throws IOException {

        if (length < ONE) {
            throw new IllegalArgumentException("length(" + length + ") < ONE");
        }

        if (length >= Integer.SIZE) { // 32
            throw new IllegalArgumentException(
                "length(" + length + ") >= " + Integer.SIZE);
        }

        final int quotient = length / Short.SIZE;
        final int remainder = length % Short.SIZE;

        int value = ZERO;
        for (int i = 0; i < quotient; i++) {
            value <<= Short.SIZE;
            value |= readUnsignedShort(Short.SIZE);
        }

        if (remainder > ZERO) {
            value <<= remainder;
            value |= readUnsignedShort(remainder);
        }

        return value;
    }


    /**
     * Reads a nullable <code>length</code>-bit unsigned <code>Integer</code>
     * value. Identical to <code>readUnsignedINTEGER(length, null)</code>.
     *
     * @param length bit length; See {@link #readUnsignedInt(int)} for valid
     * range.
     * @return the read Integer or <code>null</code> if the null flag is set.
     * @throws IOException if an I/O error occurs.
     * @see #readUnsignedINTEGER(int, Integer)
     */
    public Integer readUnsignedINTEGER(final int length) throws IOException {

        return readUnsignedINTEGER(length, null);
    }


    /**
     * Reads a nullable <code>length</code>-bit unsigned <code>Integer</code>
     * value. First, a 1-bit boolean is read for a null flag. And, if the flag
     * is not set, the value of {@link #readUnsignedInt(length)} is returned. If
     * the flag is set, the <code>defaultValue</code> is returned. This method
     * doesn't check the <code>defaultValue</code>'s validity.
     *
     * @param length bit length. See {@link #readUnsignedInt(int)} for valid
     * range.
     * @param defaultValue the defaultValue returned if the null flag is set
     * @return the Integer value or <code>defaultValue</code> if the null flag
     * is set.
     * @throws IOException if an I/O error occurs.
     * @see #readUnsignedInt(int)
     */
    public Integer readUnsignedINTEGER(final int length,
                                       final Integer defaultValue)
        throws IOException {

        return isNull()
               ? defaultValue : Integer.valueOf(readUnsignedInt(length));
    }


    /**
     * Reads a <code>length</code>-bit signed int.
     *
     * @param length bit length between ONE (exclusive) and
     * {@value java.lang.Integer#SIZE} (inclusive).
     * @return a unsigned int value read from the input.
     * @throws IOException if an I/O error occurs.
     */
    public int readInt(final int length) throws IOException {

        if (length <= ONE) {
            throw new IllegalArgumentException(
                "length(" + length + ") <= " + ONE);
        }

        if (length > Integer.SIZE) { // 32
            throw new IllegalArgumentException(
                "length(" + length + ") > " + Integer.SIZE);
        }

        return (((readBoolean() ? -1 : ZERO) << (length - 1))
                | readUnsignedInt(length - 1));
    }


    /**
     * Reads a nullable <code>length</code>-bit signed <code>Integer</code>.
     * Identical to <code>readINTEGER(length, null)</code>.
     *
     * @param length bit length; See {@link #readInt(int)} for valid range.
     * (inclusive)
     * @return the read Integer or null if a null flag read.
     * @throws IOException if an I/O error occurs.
     * @see #readINTEGER(int, Integer)
     */
    public Integer readINTEGER(final int length) throws IOException {

        return readINTEGER(length, null);
    }


    /**
     * Reads a nullable <code>length</code>-bit signed <code>Integer</code>
     * value. First, a 1-bit boolean is read for a null flag. And, if the flag
     * is not set, the value of {@link #readInt(length)} is returned. If the
     * flag is set, the <code>defaultValue</code> is returned.
     *
     * @param length bit length. See {@link #readInt(int)} for valid range.
     * (inclusive)
     * @param defaultValue the defaultValue returned if the null flag is set
     * @return the read Integer value or <code>defaultValue</code> if the null
     * flag is set.
     * @throws IOException if an I/O error occurs.
     * @see #readInt(int)
     */
    public Integer readINTEGER(final int length, final Integer defaultValue)
        throws IOException {

        return isNull() ? defaultValue : Integer.valueOf(readInt(length));
    }


    /**
     * Reads a float value.
     *
     * @return the read float value
     * @throws IOException if an I/O error occurs
     */
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt(Integer.SIZE));
    }


    /**
     * Reads a nullable <code>Float</code> value. Identical to
     * <code>readFLOAT(null)</code>.
     *
     * @return the read Float value or null if a null flag read
     * @throws IOException if an I/O error occurs.
     * @see #readFLOAT(Float)
     */
    public Float readFLOAT() throws IOException {

        return readFLOAT(null);
    }


    /**
     * Reads a nullable <code>Float</code> value. First, a 1-bit boolean is read
     * for a null flag. And, if the flag is not set, the value of
     * {@link #readFloat()} is returned. If the flag is set, the
     * <code>defaultValue</code> is returned.
     *
     * @param defaultValue the defaultValue to be returned if the null flag is
     * set
     * @return the Float value or the <code>defaultValue</code> if a null flag
     * is set.
     * @throws IOException if an I/O error occurs.
     * @see #readFloat()
     */
    public Float readFLOAT(final Float defaultValue) throws IOException {

        return isNull() ? defaultValue : Float.valueOf(readFloat());
    }


    /**
     * Reads an unsigned long.
     *
     * @param length bit length between ONE (inclusive) and Long.SIZE (exclusive)
     * @return an unsigned long value
     * @throws IOException if an I/O error occurs
     */
    public long readUnsignedLong(final int length) throws IOException {

        if (length < ONE) {
            throw new IllegalArgumentException(
                "length(" + length + ") < " + ONE);
        }

        if (length >= Long.SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") >= " + Long.SIZE);
        }

        final int quotient = length / Short.SIZE;
        final int remainder = length % Short.SIZE;

        long value = 0x00L;
        for (int i = 0; i < quotient; i++) {
            value <<= Short.SIZE;
            value |= readUnsignedShort(Short.SIZE);
        }

        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedShort(remainder);
        }

        return value;
    }


    /**
     * Reads a nullable <code>length</code>-bit unsigned Long value. Identical
     * to <code>readUnsignedLONG(length, null)</code>.
     *
     * @param length the bit length; See {@link #readUnsignedLong(int)} for
     * valid range.
     * @return the Long value or the <code>null</code> if null flag is set
     * @throws IOException if an I/O error occurs.
     * @see #readUnsignedLONG(int, Long)
     */
    public Long readUnsignedLONG(final int length) throws IOException {

        return readUnsignedLONG(length, null);
    }


    /**
     * Reads a nullable <code>length</code>-bit unsigned Long value. First, a
     * 1-bit boolean value is read for a null flag. And, if the null flag is not
     * set, a value read via {@link #readUnsignedLong(int)} is returned.
     * If the null flag is set, the <code>defaultValue</code> is returned. This
     * method doesn't check the <code>defaultValue</code>'s validity.
     *
     * @param length the bit length; See {@link #readUnsignedLong(int)} for
     * valid range.
     * @param defaultValue the default value returned if the null flag is set
     * @return the Long value or the <code>defaultValue</code> if null flag is
     * set
     * @throws IOException if an I/O error occurs.
     * @see #readUnsignedLong(int)
     */
    public Long readUnsignedLONG(final int length, final Long defaultValue)
        throws IOException {

        return isNull() ? defaultValue : Long.valueOf(readUnsignedLong(length));
    }


    /**
     * Reads a <code>length</code>-bit signed long value.
     *
     * @param length bit length between ONE (exclusive) and Long.SIZE (inclusive).
     * @return the signed long value.
     * @throws IOException if an I/O error occurs.
     */
    public long readLong(final int length) throws IOException {

        if (length <= ONE) {
            throw new IllegalArgumentException(
                "length(" + length + ") <= " + ONE);
        }

        if (length > Long.SIZE) {
            throw new IllegalArgumentException(
                "length(" + length + ") > " + Long.SIZE);
        }

        return (((readBoolean() ? -1L : 0L)
                 << (length - 1))
                | readUnsignedLong(length - 1));

    }


    /**
     * Reads a nullable <code>length</code>-bit signed Long value. Identical to
     * <code>readLONG(length, null)</code>.
     *
     * @param length the bit length; See {@link #readLong(int)} for valid range.
     * @return the Long value or the <code>null</code> if null flag is set
     * @throws IOException if an I/O error occurs.
     * @see #readLONG(int, Long)
     */
    public Long readLONG(final int length) throws IOException {

        return readLONG(length, null);
    }


    /**
     * Reads a nullable <code>length</code>-bit signed Long value. First, a
     * 1-bit boolean value is read for a null flag. And, if the null flag is not
     * set, a value read via {@link #readLong(int)} is returned.
     * If the null flag is set, the <code>defaultValue</code> is returned. This
     * method doesn't check the <code>defaultValue</code>'s validity.
     *
     * @param length the bit length; See {@link #readLong(int)} for valid range.
     * @param defaultValue the default value returned if the null flag is set
     * @return the Long value or the <code>defaultValue</code> if null flag is
     * set
     * @throws IOException if an I/O error occurs.
     * @see #readLong(int)
     */
    public Long readLONG(final int length, final Long defaultValue)
        throws IOException {

        return isNull() ? defaultValue : Long.valueOf(readLong(length));
    }


    /**
     * Reads a double value.
     *
     * @return double value
     * @throws IOException if an I/O error occurs.
     */
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong(Long.SIZE));
    }


    /**
     * Reads a nullable Double value. Identical to
     * <code>readDOUBLE(null)</code>.
     *
     * @return the Double value or <code>null</code> if null flag is set
     * @throws IOException if an I/O error occurs.
     * @see #readDOUBLE(Double)
     */
    public Double readDOUBLE() throws IOException {

        return readDOUBLE(null);
    }


    /**
     * Reads a nullable Double value. First, a 1-bit boolean value is read for a
     * null flag. And, if the null flag is not set, a value read via
     * {@link #readDouble()} is returned. If the null flag is set, the
     * <code>defaultValue</code> is returned.
     *
     * @param defaultValue the default value returned if the null flag is set
     * @return the Double value or the <code>defaultValue</code> if null flag is
     * set
     * @throws IOException if an I/O error occurs.
     * @see #readDouble()
     */
    public Double readDOUBLE(final Double defaultValue) throws IOException {

        return isNull() ? defaultValue : Double.valueOf(readDouble());
    }


    /**
     * Reads a non-null byte array. First, a 31-bit unsigned integer is read for
     * the byte count. And each byte is read.
     *
     * @return read byte array
     * @throws IOException if an I/O error occurs.
     */
    public byte[] readBytes() throws IOException {

        final byte[] value = new byte[readUnsignedInt(0x1F)]; // 31

        for (int i = 0; i < value.length; i++) {
            value[i] = (byte) readUnsignedByte(Byte.SIZE);
        }

        return value;
    }


    /**
     * Reads a nullable byte array. Identical to <code>readBYTES(null)</code>.
     *
     * @return a byte array or <code>null</code> if the null flag is set
     * @throws IOException if an I/O error occurs.
     * @see #readBYTES(byte[])
     */
    public byte[] readBYTES() throws IOException {

        return readBYTES(null);
    }


    /**
     * Reads a nullable byte array. First, a 1-bit boolean is read for a null
     * flag. And, if the null flag is not set, the value read via
     * {@link #readBytes()} is returned. If the null flag is set, the
     * <code>defaultValue</code> is returned.
     *
     * @param defaultValue the default value to be returned if the null flag is
     * set.
     * @return a byte array or <code>defaultValue</code> if the null flag set
     * @throws IOException if an I/O error occurs.
     * @see #readBytes()
     */
    public byte[] readBYTES(final byte[] defaultValue) throws IOException {

        return isNull() ? defaultValue : readBytes();
    }


    /**
     * Reads a nullable 7-bit ASCII String. Identical to
     * <code>readASCII(null)</code>.
     *
     * @return the ASCII String or <code>null</code> if the null flag is set
     * @throws IOException if an I/O error occurs.
     * @see #readASCII(String)
     */
    public String readASCII() throws IOException {

        return readASCII(null);
    }


    /**
     * Reads a nullable 7-bit ASCII String. First, a 1-bit boolean is read for
     * a null flag. And, if the null flag is not set, a 31-bit unsigned integer
     * is read for the character count following the characters which each is
     * read as a 7-bit unsigned byte. If the null flag is set, the
     * <code>defaultValue</code> is returned. This method doesn't check the
     * <code>defaultValue</code>'s validity.
     *
     * @param defaultValue the default value to be returned if the null flag is
     * set.
     * @return a read ASCII String or <code>defaultValue</code> if the null flag
     * is set
     * @throws IOException if an I/O error occurs.
     */
    public String readASCII(final String defaultValue) throws IOException {

        if (isNull()) {
            return defaultValue;
        }

        byte[] bytes = new byte[readUnsignedInt(0x1F)]; // 31
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
     * @deprecated Not for Bit-I/O. Use {@link #readSTRING(String)}.
     */
    public String readUTF() throws IOException {

        final CharArrayWriter caw = new CharArrayWriter();

        int length = readUnsignedInt(Short.SIZE);
        int i = 0;
        for (; i < length; i++) {

            int first = readUnsignedByte(Byte.SIZE);
            if (first <= 0x7F) { // 0xxxxxxx
                caw.write(first);
                continue;
            }

            if (first <= 0xBF) {
                throw new UTFDataFormatException(
                    "illegal first byte: " + first);
            }

            if (first <= 0xDF) { // 110xxxxx
                int second = readUnsignedByte(Byte.SIZE); // EOFException
                i++;
                if (second > 0xBF) { // !10xxxxxxxx
                    throw new UTFDataFormatException(
                        "illegal second byte: " + second);
                }
                caw.write(((first & 0x1F) << 6) | (second & 0x3F));
                continue;
            }

            if (first <= 0xEF) { // 1110xxxx
                int second = readUnsignedByte(Byte.SIZE); // EOFException
                i++;
                if (second > 0xBF) { // !10xxxxxxxx
                    throw new UTFDataFormatException(
                        "illegal second byte: " + second);
                }
                int third = readUnsignedByte(Byte.SIZE); // EOFException
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
     * Reads a non-null String.
     *
     * @param charsetName charset name
     * @return the String read.
     * @throws IOException if an I/O error occurs.
     */
    public String readString(final String charsetName) throws IOException {

        return new String(readBytes(), charsetName);
    }


    /**
     * Reads a nullable String. Identical to <code>readSTRING(null)</code>.
     *
     * @param charsetName the character set name for constructing a String with
     * raw bytes
     * @return a String value read or <code>null</code> if the null flag is set
     * @throws IOException if an I/O error occurs.
     * @see #readSTRING(String)
     */
    public String readSTRING(final String charsetName) throws IOException {

        return readSTRING(charsetName, null);
    }


    /**
     * Reads a nullable String. First, a 1-bit boolean is read for a null flag.
     * And, if the null flag is not set, the value from {@link #readBytes()} is
     * returned as a String created with <code>characterSet</code>.
     * If the null flag is set, the <code>defaultValue</code> is returned. This
     * method doesn't check the <code>defaultValue</code>'s validity.
     *
     * @param charsetName the character set name for constructing a String with
     * raw bytes.
     * @param defaultValue the default value to be returned if the null flag is
     * set.
     * @return a String value or <code>defaultValue</code> if the null flag is
     * set
     * @throws IOException if an I/O error occurs.
     * @see #readBytes()
     * @see String#String(byte[], String)
     */
    public String readSTRING(final String charsetName,
                             final String defaultValue)
        throws IOException {

        if (isNull()) {
            return defaultValue;
        }

        return readString(charsetName);
    }


    /**
     * Align to given <code>length</code> octets.
     *
     * @param length number of octets to align; must be non-zero positive.
     * @return the number of bits discarded for alignment.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int align(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        int bits = 0;

        if (index < Byte.SIZE) { // bit index to read
            bits = (Byte.SIZE - index);
            readUnsignedByte(bits);
        }

        int octets = getCount() % length;

        if (octets == 0) {
            return bits;
        }

        if (octets > 0) {
            octets = length - octets;
        } else { // octets < 0
            octets = 0 - octets;
        }

        for (; octets > 0; octets--) {
            readUnsignedByte(Byte.SIZE);
            bits += Byte.SIZE;
        }

        return bits;
    }


    /*
     * Returns the available bits for reading in current octet.
     *
     * @return available bits for reading in current octet.
     */
    public int available() {
        return Byte.SIZE - index;
    }


    /** source input. */
    private final InputStream in;


    /** bit index to read. */
    private int index = Byte.SIZE;


}

