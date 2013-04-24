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
         * Writes an unsigned 8-bit integer.
         *
         * @param value the value to write
         *
         * @throws IOException if an I/O error occurs.
         */
        void writeUnsignedByte(final int value) throws IOException;


    }


    /**
     * A {@link ByteOutput} implementation for OutputStreams.
     */
    public static class StreamOutput implements ByteOutput {


        /**
         * 'Creates a new instance.
         *
         * @param output the stream to wrap.
         */
        public StreamOutput(final OutputStream output) {
            super();

            if (output == null) {
                throw new NullPointerException("null output");
            }

            this.output = output;
        }


        //@Override
        public void writeUnsignedByte(final int value) throws IOException {
            output.write(value);
        }


        /**
         * output.
         */
        private final OutputStream output;


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


    /**
     * Writes an {@code length}-bit unsigned byte value. The lower
     * {@code length} bits in {@code value} are written.
     *
     * @param length bit length between 0 exclusive and 8 inclusive.
     * @param value the value to write
     *
     * @throws IOException if an I/O error occurs.
     */
    protected void writeUnsignedByte(final int length, int value)
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

        for (int i = index + length - 1; i >= index; i--) {
            bitset.set(i, ((value & 0x01) == 0x01 ? true : false));
            value >>= 1;
        }
        index += length;

        if (index == 8) {
            int octet = 0x00;
            for (int i = 0; i < 8; i++) {
                octet <<= 1;
                octet |= (bitset.get(i) ? 0x01 : 0x00);
            }
            output.writeUnsignedByte(octet);
            count++;
            index = 0;
        }
    }


    /**
     * Writes a 1-bit boolean value. {@code 0x00} for {@code false} and
     * {@code 0x01} for {@code true}.
     *
     * @param value the value to write
     *
     * @throws IOException if an I/O error occurs
     */
    public void writeBoolean(final boolean value) throws IOException {
        writeUnsignedByte(1, value ? 0x01 : 0x00);
    }


    /**
     * Writes an {@code length}-bit unsigned short value. Only the lower
     * {@code length} bits in {@code value} are written.
     *
     * @param length the bit length between 0 exclusive and 16 inclusive.
     * @param value the value whose lower {@code length}-bits are written.
     *
     * @throws IOException if an I/O error occurs
     */
    protected void writeUnsignedShort(final int length, final int value)
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
     * Writes a {@code length}-bit unsigned int value. Only the lower
     * {@code length}-bits in {@code value} are written.
     *
     * @param length bit length between 1 inclusive and 32 exclusive.
     * @param value the value whose lower {@code length}-bits are written.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedInt(final int length, final int value)
        throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length >= 32) {
            throw new IllegalArgumentException("length(" + length + ") >= 32");
        }

        if ((value >> length) != 0x00) {
            throw new IllegalArgumentException(
                "value(" + value + ") >> length(" + length + ") != 0x00");
        }

        final int quotient = length / 16;
        final int remainder = length % 16;

        if (remainder > 0) {
            writeUnsignedShort(remainder, value >> (quotient * 16));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(16, value >> (16 * i));
        }
    }


    /**
     * Writes a {@code length}-bit signed int value.
     *
     * @param length bit length between 1 (exclusive) and 32 (inclusive).
     * @param value the value to write
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeInt(final int length, final int value) throws IOException {

        if (length <= 1) {
            throw new IllegalArgumentException("length(" + length + ") <= 1");
        }

        if (length > 32) {
            throw new IllegalArgumentException("length(" + length + ") > 32");
        }

        if (length < 32) {
            if (value < 0x00) { // negative
                if (value >> (length - 1) != ~0) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> (length(" + length
                        + ") - 1) != ~0");
                }
            } else { // positive
                if (value >> (length - 1) != 0) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> (length(" + length
                        + ") - 1) != 0");
                }
            }
        }

//        writeBoolean(value < 0);
//
//        writeUnsignedInt(length - 1, value);

        final int quotient = length / 16;
        final int remainder = length % 16;

        if (remainder > 0) {
            writeUnsignedShort(remainder, value >> (quotient * 16));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(16, value >> (16 * i));
        }
    }


    /**
     * Writes a float value.
     *
     * @param value the value to write.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeFloat(final float value) throws IOException {
        writeInt(32, Float.floatToRawIntBits(value));
    }


    /**
     * Writes a {@code length}-bit unsigned long value.
     *
     * @param length bit length between 1 (inclusive) and 64 (exclusive).
     * @param value the value to write.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedLong(final int length, final long value)
        throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException("length(" + length + ") < 1");
        }

        if (length >= 64) {
            throw new IllegalArgumentException("length(" + length + ") >= 64");
        }

        if (value >> length != 0) {
            throw new IllegalArgumentException(
                "value(" + value + ") >> length(" + length + ") != 0");
        }

        final int quotient = length / 16;
        final int remainder = length % 16;

        if (remainder > 0) {
            writeUnsignedShort(remainder, (int) (value >> (quotient * 16)));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(16, (int) (value >> (i * 16)));
        }
    }


    /**
     * Writes a {@code length}-bit signed long value.
     *
     * @param length bit length between 1 (exclusive) and 64 (inclusive).
     * @param value the value whose lower {@code length}-bits are written.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void writeLong(final int length, final long value)
        throws IOException {

        if (length <= 1) {
            throw new IllegalArgumentException("length(" + length + ") <= 1");
        }

        if (length > 64) {
            throw new IllegalArgumentException("length(" + length + ") > 64");
        }

        if (length < 64) {
            if (value < 0L) {
                if (value >> (length - 1) != ~0) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> (length(" + length
                        + ") - 1) != ~0");
                }
            } else {
                if (value >> (length - 1) != 0) {
                    throw new IllegalArgumentException(
                        "value(" + value + ") >> (length(" + length
                        + ") - 1) != 0");
                }
            }
        }

        final int quotient = length / 16;
        final int remainder = length % 16;

        if (remainder > 0) {
            writeUnsignedShort(remainder, (int) (value >> (quotient * 16)));
        }

        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedShort(16, (int) (value >> (i * 16)));
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
        writeLong(64, Double.doubleToRawLongBits(value));
    }


//    /**
//     * Writes a UTF-8 code point.
//     *
//     * @param value UTF-8 character code point
//     *
//     * @throws IOException if an I/O error occurs.
//     */
//    public void writeUTF8Char(final int value) throws IOException {
//
//        if (value >> 21 != 0x00) {
//            throw new IllegalArgumentException("illegal value: " + value);
//        }
//
//        if (value <= 0x7F) {
//            writeUnsignedByte(8, value);
//            return;
//        }
//
//        int tails;
//        if (value >> 11 == 0x00) {
//            tails = 1;
//        } else if (value >> 16 == 0x00) {
//            tails = 2;
//        } else {
//            tails = 3;
//        }
//
//        writeBoolean(true); // 1
//        for (int i = 0; i < tails; i++) {
//            writeBoolean(true); // 1...
//        }
//        writeBoolean(false); // 0
//
//        writeUnsignedByte(6 - tails, value >> (tails * 6)); // head
//
//        for (int i = tails - 1; i >= 0; i--) {
//            writeUnsignedByte(2, 0x02); // 10______
//            writeUnsignedByte(6, (value >> (6 * i)) & 0x3F); // __xxxxxx
//        }
//    }
    /**
     * Aligns to given {@code length} bytes.
     *
     * @param length the number of bytes to align
     *
     * @return number of bits padded to align
     *
     * @throws IOException if an I/O error occurs.
     */
    public int align(final int length) throws IOException {

        if (length < 0x01) {
            throw new IllegalArgumentException("length(" + length + ") < 0x01");
        }

        int bits = 0;

        if (index > 0x00) { // bit index to write
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
     * Returns current bit index to write.
     *
     * @return
     */
    public int getIndex() {
        return index;
    }


    /**
     * Returns the number of octets written so far excluding current octet.
     *
     * @return the number of octets written so far.
     */
    public int getCount() {
        return count;
    }


    /**
     * target byte output.
     */
    protected final ByteOutput output;


    /**
     * bits in current byte.
     */
    private final BitSet bitset = new BitSet(0x08);


    /**
     * bit index to write.
     */
    private int index = 0x00;


    /**
     * number of bytes written so far.
     */
    private int count = 0x00;


}

