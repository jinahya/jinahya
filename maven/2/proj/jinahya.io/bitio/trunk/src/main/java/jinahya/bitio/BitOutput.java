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

package jinahya.bitio;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UTFDataFormatException;


/**
 * Class for bit level output.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutput {


    /**
     * Creates a new instance with specifed output.
     *
     * @param out output to which octets are write
     */
    public BitOutput(final OutputStream out) {
        this(new OctetOutput() {
            public void writeOctet(int b) throws IOException {
                out.write(b);
            }
        });
    }


    /**
     * Creates a new instance with specified output.
     *
     * @param out output to which octets are write
     */
    public BitOutput(final OctetOutput out) {
        super();

        output = out;
    }


    /**
     * Creates an instance inheriting attributes from specified
     * <code>parent</code>.
     *
     * @param parent parent output
     */
    public BitOutput(final BitOutput parent) {
        super();

        output = parent.output;

        octet = parent.octet;
        avail = parent.avail;
        count = parent.count;
    }


    /**
     * Writes a boolean value. Only one bit is consumed.
     *
     * @param value boolean value
     * @throws IOException if an I/O error occurs.
     */
    public final void writeBoolean(final boolean value) throws IOException {
        writeUnsignedByte(1, value ? 0x01 : 0x00);
    }


    /**
     * Same as <code>writeBytes(value, 0, value.length)</code>.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     * @see #writeBytes(byte[], int, int)
     */
    public final void writeBytes(final byte[] value) throws IOException {
        writeBytes(value, 0, value.length);
    }


    /**
     * Writes an array of bytes.
     *
     * @param value byte array
     * @param offset offset
     * @param length length
     * @throws IOException if an I/O error occurs.
     */
    public final void writeBytes(final byte[] value, final int offset,
                                 final int length)
        throws IOException {

        /*
        System.out.println(
            "writeBytes(" + value + ", " + offset + ", " + length + ")");
         */

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
                "offset(" + offset + ") + length(" + length +
                ") > value.length(" + value.length + ")");
        }

        for (int i = 0; i < length; i++) {
            writeUnsignedByte(8, value[offset + i] & 0xFF);
        }
    }


    /**
     * Writes an <code>length</code> bit unsigned byte.
     *
     * @param length bit length between 0 (exclusive) and 8 (inclusive).
     * @param value value
     * @throws IOException if an I/O error occurs
     */
    private void writeUnsignedByte(final int length, final int value)
        throws IOException {

        //System.out.println("writeUnsignedByte(" + length + ", " + value + ")");

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        if (length > 8) {
            throw new IllegalArgumentException("length(" + length + ") > " + 8);
        }

        if (avail >= length) {
            octet <<= length;
            octet |= (value & (0xFF >> (8 - length)));
            count += length;
            avail -= length;
            if (avail == 0) {
                output.writeOctet(octet);
                //System.out.println("OctetOutput.writeOctet(" + octet + ")");
                octet = 0;
                avail = 8;
            }
        } else {
            final int requi = length - avail;
            writeUnsignedByte(avail, (value >> (requi)));
            writeUnsignedByte(requi, value);
        }
    }


    /**
     * Writes an unsigned <code>length</code> bit short.
     *
     * @param length bit length between 0 (exclusive) and 16 (inclusive)
     * @param value value
     * @throws IOException if an I/O error occurs
     */
    private void writeUnsignedShort(final int length, final int value)
        throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") <= 0");
        }

        if (length > 16) {
            throw new IllegalArgumentException("length(" + length + ") > 16");
        }

        int quotient = length / 8;
        int remainder = length % 8;
        if (remainder > 0) {
            writeUnsignedByte(remainder, (value >> (quotient * 8)));
        }
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedByte(8, (value >> (i * 8)));
        }
    }


    /**
     * Writes an unsigned <code>length</code> bit integer.
     *
     * @param length bit length between 0 (exclusive) and 32 (exclusive)
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public final void writeUnsignedInt(final int length, final int value)
        throws IOException {

        //System.out.println("writeUnsignedInt(" + length + ", " + value + ")");

        if (length <= 0) {
            throw new IllegalArgumentException("length <= 0");
        }

        if (length >= 32) {
            throw new IllegalArgumentException("length >= 32");
        }

        int quotient = length / 16;
        int remainder = length % 16;
        if (remainder > 0) {
            writeUnsignedShort(remainder, (value >> (quotient * 16)));
        }
        for (int i = quotient -1; i >= 0; i--) {
            writeUnsignedShort(16, (value >> (i * 16)));
        }
    }


    /**
     * Writes an signed <code>length</code> bit integer.
     *
     * @param length bit length between 1 (exclusive) and 32 (inclusive)
     * @param value int value
     * @throws IOException if an I/O error occurs.
     */
    public final void writeInt(final int length, final int value)
        throws IOException {

        if (length <= 1) {
            throw new IllegalArgumentException("length <= 1");
        }

        if (length > 32) {
            throw new IllegalArgumentException("length > 32");
        }

        writeUnsignedByte(1, value >> (length - 1));
        writeUnsignedInt(length - 1, value);
    }


    /**
     * Writes a 32-bit signed integer.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public final void writeInt(final int value) throws IOException {
        writeInt(32, value);
    }


    /*
     *
     * @param octetLength
     * @param value
     * @throws IOException
    public void writeIntLE(final int octetLength, final int value)
        throws IOException {

        if (octetLength <= 0 || octetLength > 4) {
            throw new IllegalArgumentException
                ("illegal octet length: " + octetLength);
        }

        for (int i = 0; i < octetLength; i++) {
            writeUnsignedInt(8, value >>> (i * 8));
        }
    }
     */


    /**
     * Writes a 32-bit signed floating-point value.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public final void writeFloat(final float value) throws IOException {
        writeInt(Float.floatToIntBits(value));
    }


    /**
     * Writes an unsigned <code>length</code> long.
     *
     * @param length bit length between 0 (exclusive) and 64 (exclusive).
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public final void writeUnsignedLong(final int length, final long value)
        throws IOException {

        if (length < 0) {
            throw new IllegalArgumentException("length(" + length + ") < 0");
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
    public final void writeLong(final int length, final long value)
        throws IOException {

        if (length <= 1) {
            throw new IllegalArgumentException("length(" + length + ") <= 1");
        }

        if (length > 64) {
            throw new IllegalArgumentException("length(" + length + ") > 64");
        }

        writeUnsignedByte(1, (int) (value >> (length - 1)));
        writeUnsignedLong(length - 1, value);
    }


    /**
     * Writes a 64-bit signed long.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public final void writeLong(final long value) throws IOException {
        writeLong(64, value);
    }


    /*
     *
     * @param octetLength
     * @param value
     * @throws IOException
    public void writeLongLE(final int octetLength, final long value)
        throws IOException {

        if (octetLength <= 0 || octetLength > 8) {
            throw new IllegalArgumentException
                ("illegal octet length: " + octetLength);
        }

        for (int i = 0; i < octetLength; i++) {
            writeUnsignedLong(8, value >>> (i * 8));
        }
    }
     */


    /**
     * Writes a 64-bit signed floating-point value.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public final void writeDouble(final double value) throws IOException {
        writeLong(Double.doubleToLongBits(value));
    }


    /**
     * Returns the number of bits written so far.
     *
     * @return number of bits written so far.
     */
    public final long getCount() {
        return count;
    }


    /**
     * Align to <code>length</code> bits by writing some dummy bits if required.
     *
     * @param length number of bits to be aligned
     * @throws IOException if an I/O error occurs.
     * @return this object
     */
    public final BitOutput align(final int length) throws IOException {

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") < 0");
        }

        final int mod = (int) (count % length);
        if (mod > 0) {
            final int required = length - mod;

            final int quotient = required / 8;
            for (int i = 0; i < quotient; i++) {
                writeUnsignedByte(8, 0x00);
            }

            final int remainder = required % 8;
            if (remainder > 0) {
                writeUnsignedByte(remainder, 0x00);
            }
        }

        return this;
    }


    /**
     * Align to 8 bits.
     *
     * @return this object
     * @throws IOException if an I/O error occurs
     */
    public final BitOutput align() throws IOException {
        return align(8);
    }


    /**
     * Writes a string in modified UTF-8 encoding.
     *
     * @param value String to be written
     * @throws IOException if an I/O error occurs.
     */
    public final void writeUTF(final String value) throws IOException {
        final Reader reader = new StringReader(value);
        try {
            writeUTF(reader);
        } finally {
            reader.close();
        }
    }


    /**
     * Writes a sequence of characters in modifed UTF-8 encoding.
     *
     * @param reader character source
     * @throws IOException if an I/O error occurs
     */
    public void writeUTF(final Reader reader) throws IOException {
        //System.out.println("writeUTF(" + reader + ")");
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            for (int c = -1; (c = reader.read()) != -1;) {
                if (c >= '\u0001' && c <= '\u007F') {
                    baos.write(c);
                } else if (c == '\u0000' || c <= '\u07FF') {
                    baos.write(0xC0 | (0x1F & (c >> 6)));
                    baos.write(0x80 | (0x3F & c));
                } else { // if (c <= '\uFFFF') {
                    baos.write(0xE0 | (0x0F & (c >> 12)));
                    baos.write(0x80 | (0x3F & (c >> 6)));
                    baos.write(0x80 | (0x3F & c));
                }
                if (baos.size() > 65535) {
                    throw new UTFDataFormatException(
                        "length(" + baos.size() + ") > 65535");
                }
            }
            baos.flush();

            writeUnsignedInt(16, baos.size());
            writeBytes(baos.toByteArray());

        } finally {
            baos.close();
        }
    }


    /*
    public void writeUnsignedFixedPoint(final int m, final int n,
                                        final float value)
        throws IOException {

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

        writeUnsignedInt(m + n, (int) (value * Math.pow(2, n)));
    }
     */


    /*
    public void writeFixedPoint(final int m, final int n, final float value)
        throws IOException {

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

        writeInt(1 + m + n, (int) (value * Math.pow(2, n)));
    }
     */


    /*
    public void writeUnsignedLongFixedPoint(final int m, final int n,
                                            final double value)
        throws IOException {

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

        writeUnsignedLong(m + n, (long) (value * Math.pow(2, n)));
    }
     */


    /*
    public void writeLongFixedPoint(final int m, final int n,
                                    final double value)
        throws IOException {

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

        writeLong(1 + m + n, (long) (value * Math.pow(2, n)));
    }
     */

    /*
    private void writeFraction(final int n, float value) throws IOException {
        float fraction = .5f;
        for (int i = 0; i < n; i++) {
            if (value >= fraction) {
                writeBoolean(true);
                value -= fraction;
            } else {
                writeBoolean(false);
            }
            fraction /= 2.0f;
        }
    }
     */


    /*
    private float getMaximumUnsignedFixedPoint(int m, int n) {

        float value = .0f;

        float integer = 1.0f;
        for (int i = 0; i < m; i++) {
            value += integer;
            integer *= 2.0f;
        }

        float fraction = .5f;
        for (int i = 0; i < n; i++) {
            value += fraction;
            fraction /= 2.0f;
        }

        return value;
    }
     */


    private OctetOutput output;

    private int octet = 0x00;
    private int avail = 0x08;

    private long count = 0L;
}
