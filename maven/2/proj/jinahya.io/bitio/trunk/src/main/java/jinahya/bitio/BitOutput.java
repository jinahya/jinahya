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
 *  under the License.
 */

package jinahya.bitio;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UTFDataFormatException;


/**
 *
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
     * @param output output to which octets are write
     */
    public BitOutput(final OctetOutput output) {
        super();

        this.output = output;
    }


    /**
     * Writes a boolean value. Only one bit is consumed.
     *
     * @param value boolean value
     * @throws IOException if an I/O error occurs.
     */
    public void writeBoolean(boolean value) throws IOException {
        writeUnsignedByte(1, value ? 0x01 : 0x00);
    }


    /**
     * Writes a sequence of bytes.
     *
     * @param value value to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeBytes(byte[] value) throws IOException {
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
    public void writeBytes(byte[] value, int offset, int length)
        throws IOException {

        if (offset < 0 || offset >= value.length) {
            throw new IllegalArgumentException("illegal offset: " + offset);
        }

        if (length <= 0 || (offset + length) > value.length) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        for (int i = 0; i < length; i++) {
            writeUnsignedShort(8, value[offset + i]);
        }
    }


    private void writeUnsignedByte(final int length, final int value)
        throws IOException {

        if (avail >= length) {
            octet <<= length;
            octet |= (value & (0xFF >> (0x08 - length)));
            count += length;
            if ((avail -= length) == 0x00) {
                output.writeOctet(octet);
                octet = 0x00;
                avail = 0x08;
            }
        } else {
            int requi = length - avail;
            writeUnsignedByte(avail, (value >> (requi)));
            writeUnsignedByte(requi, value);
        }
    }


    private void writeUnsignedShort(final int length, final int value)
        throws IOException {

        int quotient = length / 0x07;
        int remainder = length % 0x07;
        if (remainder > 0) {
            writeUnsignedByte(remainder, (value >> (quotient * 0x07)));
        }
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedByte(7, (value >> (i * 0x07)));
        }
    }


    /**
     * Writes an unsigned integer.
     *
     * @param length number of bits
     * @param value int value
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedInt(final int length, final int value)
        throws IOException {

        if (length < 0x1 || length >= 0x20) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        int quotient = length / 0x0F;
        int remainder = length % 0x0F;
        if (remainder > 0) {
            writeUnsignedShort(remainder, (value >> (quotient * 0x0F)));
        }
        for (int i = quotient -1; i >= 0; i--) {
            writeUnsignedShort(0x0F, (value >> (i * 0x0F)));
        }
    }


    /**
     * Writes an signed integer.
     *
     * @param length number of bits
     * @param value int value
     * @throws IOException if an I/O error occurs.
     */
    public void writeInt(final int length, final int value) throws IOException {

        if (length <= 0x01 || length > 0x20) {
            throw new IllegalArgumentException("illegal length: " + length);
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
    public void writeInt(final int value) throws IOException {
        writeInt(0x20, value);
    }


    /**
     *
     * @param octetLength
     * @param value
     * @throws IOException
     */
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
     * Writes an unsigned long.
     *
     * @param length number of bits
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public void writeUnsignedLong(final int length, final long value)
        throws IOException {

        if (length < 0x01 || length >= 0x40) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        int quotient = length / 0x1F;
        int remainder = length % 0x1F;
        if (remainder > 0) {
            writeUnsignedInt(remainder, (int) (value >> (quotient * 0x1F)));
        }
        for (int i = quotient - 1; i >= 0; i--) {
            writeUnsignedInt(0x1F, (int) (value >> (i * 0x1F)));
        }
    }


    /**
     * Writes a signed long.
     *
     * @param length number of bits
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public void writeLong(final int length, final long value)
        throws IOException {

        if (length <= 0x01 || length > 0x40) {
            throw new IllegalArgumentException("illegal length: " + length);
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
    public void writeLong(final long value) throws IOException {
        writeLong(0x40, value);
    }


    /**
     *
     * @param octetLength
     * @param value
     * @throws IOException
     */
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
     * Writes some (if required) dummy bits for octet alignemnt.
     *
     * @param octetLength number of bytes to be aligned
     * @throws IOException if an I/O error occurs.
     */
    public void alignOctets(int octetLength) throws IOException {
        if (octetLength <= 0x00) {
            throw new IllegalArgumentException
                ("illegal octet length: " + octetLength);
        }
        int length = (octetLength * 8) - (int) (count % 8);

        int quotient = length / 7;
        for (int i = 0; i < quotient; i++) {
            writeUnsignedByte(7, 0x00);
        }

        int remainder = length % 7;
        if (remainder > 0) {
            writeUnsignedByte(remainder, 0x00);
        }
    }


    /**
     * Writes a string in modified UTF-8 encoding.
     *
     * @param value String to be written
     * @throws IOException if an I/O error occurs.
     */
    public void writeUTF(String value) throws IOException {
        Reader reader = new StringReader(value);
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
    public void writeUTF(Reader reader) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
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
                    throw new UTFDataFormatException("length: " + baos.size());
                }
            }
            baos.flush();

            writeUnsignedInt(16, baos.size());
            writeBytes(baos.toByteArray());

        } finally {
            baos.close();
        }
    }


    private OctetOutput output;

    private int octet = 0x00;
    private int avail = 0x08;

    private long count = 0L;
}
