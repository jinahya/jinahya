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


import java.io.IOException;
import java.io.OutputStream;


/**
 * Class for bit level output.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutput {


    /*
    private static final byte[] FF = new byte[8];
    static {
        FF[0] = 0x01;
        for (int i = 1; i < FF.length; i++) {
            FF[i] = (byte) (FF[i - 1] * 2 - 1);
        }
    }
     */


    /**
     *
     */
    public static interface OctetOutput {


        /**
         * Writes a composed octet to desired output.
         *
         * @param octet the octet value between 0 and 255.
         * @throws IOException if an I/O error occurs
         */
        void writeOctet(int octet) throws IOException;
    }


    /**
     * Creates a new instance with specifed output.
     *
     * @param out output to which octets are write
     */
    public BitOutput(final OutputStream out) {
        this(new OctetOutput() {
            @Override
            public void writeOctet(final int octet) throws IOException {
                out.write(octet);
            }
        });
    }


    /**
     * Creates a new instance with specified output.
     *
     * @param out output to which octets are write
     */
    public BitOutput(final OctetOutput output) {
        super();

        if (output == null) {
            throw new IllegalArgumentException("null output");
        }

        this.output = output;
    }


    /**
     * Writes a boolean value. Only one bit is consumed.
     *
     * @param value boolean value
     * @throws IOException if an I/O error occurs.
     */
    public void writeBoolean(final boolean value) throws IOException {
        write8(1, value ? 0x01 : 0x00);
    }


    /**
     * See {@link java.io.DataOutput#write(byte[])}.
     *
     * @param value the data.
     * @throws IOException if an I/O error occurs.
     * @see #writeBytes(byte[], int, int)
     */
    public void writeBytes(final byte[] value) throws IOException {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        writeInt(value.length);
        for (int i = 0; i < value.length; i++) {
            write8(8, value[i]);
        }
    }


    /**
     * Writes the <code>length</code> low-order bits of the <code>value</code>.
     * The 32 - <code>length</code> high-order bits of <code>value</code> are
     * ignored.
     *
     * @param length bit length between 1 (inclusive) and 8 (inclusive).
     * @param value value
     * @throws IOException if an I/O error occurs
     */
    private void write8(final int length, final int value) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " < 1");
        }

        if (length > 8) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " > " + 8);
        }

        if (avail >= length) {
            octet <<= length;
            octet |= (value & (0xFF >> (8 - length)));
            //octet |= (value & FF[length - 1]);
            count += length;
            avail -= length;
            if (avail == 0) {
                output.writeOctet(octet);
                octet = 0x00;
                avail = 0x08;
            }
        } else {
            final int requi = length - avail;
            write8(avail, (value >> (requi)));
            write8(requi, value);
        }
    }


    /**
     * Writes an unsigned <code>length</code> bit short.
     *
     * @param length bit length between 1 (inclusive) and 16 (inclusive)
     * @param value value
     * @throws IOException if an I/O error occurs
     */
    private void write16(final int length, final int value) throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " < 1");
        }

        if (length > 16) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " > 16");
        }

        int quotient = length / 8;
        int remainder = length % 8;
        if (remainder > 0) {
            write8(remainder, (value >> (quotient * 8)));
        }
        for (int i = quotient - 1; i >= 0; i--) {
            write8(8, (value >> (i * 8)));
        }
    }


    /**
     * Writes an unsigned <code>length</code> bit integer.
     *
     * @param length bit length between 1 (inclusive) and 32 (exclusive)
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedInt(final int length, final int value)
        throws IOException {

        if (length < 1) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " < 1");
        }

        if (length >= 32) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " >= 32");
        }

        int quotient = length / 16;
        int remainder = length % 16;
        if (remainder > 0) {
            write16(remainder, (value >> (quotient * 16)));
        }
        for (int i = quotient -1; i >= 0; i--) {
            write16(16, (value >> (i * 16)));
        }
    }


    /**
     * Writes an signed <code>length</code> bit integer.
     *
     * @param length bit length between 1 (exclusive) and 32 (inclusive)
     * @param value int value
     * @throws IOException if an I/O error occurs.
     */
    public void writeInt(final int length, final int value) throws IOException {

        if (length <= 1) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " <= 1");
        }

        if (length > 32) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " > 32");
        }

        writeBoolean(value < 0);
        writeUnsignedInt(length - 1, value);
    }


    /**
     * Writes a 32-bit signed integer.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeInt(final int value) throws IOException {
        writeInt(32, value);
    }


    /**
     * Writes a 32-bit signed floating-point value.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeFloat(final float value) throws IOException {
        writeInt(Float.floatToIntBits(value));
    }


    /**
     * Writes an unsigned <code>length</code> long.
     *
     * @param length bit length between 1 (inclusive) and 64 (exclusive).
     * @param value value
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

        final int quotient = length / 31;
        final int remainder = length % 31;
        if (remainder > 0) {
            writeUnsignedInt(remainder, (int) (value >> (quotient * 31)));
        }
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedInt(31, (int) (value >> (i * 31)));
        }
    }


    /**
     * Writes a signed <code>length</code> long.
     *
     * @param length bit length between 1 (exclusive) and 64 (inclusive).
     * @param value value
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

        writeBoolean(value < 0L);
        writeUnsignedLong(length - 1, value);
    }


    /**
     * Writes a 64-bit signed long.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeLong(final long value) throws IOException {
        writeLong(64, value);
    }


    /**
     * Writes a 64-bit signed floating-point value.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeDouble(final double value) throws IOException {
        writeLong(Double.doubleToLongBits(value));
    }


    /**
     * Returns the number of bits written so far.
     *
     * @return number of bits written so far.
     */
    public long getCount() {
        return count;
    }


    /**
     * Align to <code>length</code> bits by writing some dummy bits if required.
     *
     * @param length number of bits to be aligned; non-zero positive
     * @throws IOException if an I/O error occurs.
     * @return number of padded bits
     */
    public int align(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        final int mod = (int) (count % length);

        if (mod == 0) {
            return mod;
        }

        final int required = length - mod;

        final int quotient = required / 8;
        for (int i = 0; i < quotient; i++) {
            write8(8, 0x00);
        }

        final int remainder = required % 8;
        if (remainder > 0) {
            write8(remainder, 0x00);
        }
        return required;
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


    protected void reset() {
        octet = 0x00;
        avail = 0x08;
        count = 0L;
    }


    private OctetOutput output;

    private int octet = 0x00;
    private int avail = 0x08;

    private long count = 0L;
}
