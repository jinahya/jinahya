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


package com.googlecode.jinahya.bitio;


import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;


/**
 * Bit Input.
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
     * the value and writes the lower <code>length<code> bits in
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
            set.set(--index, ((value >>> i) & 0x01) == 0x01);
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
     * Writes a boolean value.
     *
     * @param value value
     * @throws IOException if an I/O error occurs
     */
    public final void writeBoolean(final boolean value) throws IOException {
        writeUnsignedByte(0x01, value ? 0x01 : 0x00);
    }


    /**
     * Writes an unsigned int value.
     *
     * @param length bit length between 1 (inclusive) and 32 (exclusive)
     * @param value unsigned int value
     * @throws IOException if an I/O error occurs.
     */
    public final void writeUnsignedInt(final int length, final int value)
        throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length >= 0x20) {
            throw new IllegalArgumentException("length(" + length + ") >= 32");
        }

        if ((value >> length) != 0x00) {
            throw new IllegalArgumentException("illegal value: " + value);
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
                        "illegal value: " + value);
                }
            } else { // value >= 0x00
                if ((value >> length) != 0x00) {
                    throw new IllegalArgumentException(
                        "illegal value: " + value);
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
            throw new IllegalArgumentException("illegal value: " + value);
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
     * Writes an signed long.
     *
     * @param length bit length between 1 (exclusive) and 64 (inclusive).
     * @param value value to write
     * @throws IOException if an I/O error occurs.
     */
    public final void writeLong(final int length, final long value)
        throws IOException {

        if (length <= 1) {
            throw new IllegalArgumentException("length(" + length + ") <= 1");
        }

        if (length > 64) {
            throw new IllegalArgumentException("length(" + length + ") > 64");
        }

        if (length < 64) {
            if (value < 0L) {
                if (value >> length != -1L) {
                    throw new IllegalArgumentException(
                        "illegal value: " + value);
                }
            } else {
                if ((value >> length) != 0L) {
                    throw new IllegalArgumentException(
                        "illegal value: " + value);
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
     * Aligns to given <code>length</code> as bytes.
     *
     * @param length the number of bytes to align
     * @throws IOException if an I/O error occurs.
     */
    public final void aling(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        int octets = count % length;

        if (octets > 0) {
            octets = length - octets;
        } else if (octets == 0) {
            octets = length;
        } else { // mod < 0
            octets = 0 - octets;
        }

        if (index > 0) {
            writeUnsignedByte(8 - index, 0);
            octets--;
        }

        if (octets == length) {
            return;
        }

        for (int i = 0; i < octets; i++) {
            writeUnsignedByte(8, 0);
        }
    }


    private final OutputStream out;


    private final BitSet set = new BitSet(8);


    private int index = 0;


    private int count = 0;
}

