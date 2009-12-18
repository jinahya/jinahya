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


import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UTFDataFormatException;
import java.io.Writer;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInput {


    /**
     * Creates a new instance with specified input.
     *
     * @param in octet input
     */
    public BitInput(final InputStream in) {
        this(new OctetInput() {
            public int readOctet() throws IOException {
                return in.read();
            }
        });
    }


    /**
     * Creates a new instance with specified input.
     *
     * @param input octet input
     */
    public BitInput(final OctetInput input) {
        super();

        this.input = input;
    }


    /**
     * Creates an instance inheriting attributes from specified
     * <code>parent</code>.
     *
     * @param parent parent input
     * @param resetCount true for starting count from 0L,
     *                   false for continuing from <code>parent</code>'s count.
     */
    public BitInput(final BitInput parent, boolean resetCount) {
        super();

        input = parent.input;

        octet = parent.octet;
        avail = parent.octet;

        count = resetCount ? 0L : parent.count;
    }


    /**
     * Reads one bit and returns true if it is one.
     *
     * @return true for one, false for zero
     * @throws IOException if an I/O error occurs.
     */
    public boolean readBoolean() throws IOException {
        return (readUnsignedByte(1) == 0x01);
    }


    /**
     * Reads a sequence of bytes.
     *
     * @param value value to be read
     * @throws IOException if an I/O error occurs.
     */
    public void readBytes(byte[] value) throws IOException {
        readBytes(value, 0, value.length);
    }


    /**
     * Reads a sequence of bytes.
     *
     * @param value value to be read
     * @param offset offset to start
     * @param length length to be read
     * @throws IOException if an I/O error occurs.
     */
    public void readBytes(byte[] value, int offset, int length)
        throws IOException {

        if (offset < 0 || offset >= value.length) {
            throw new IllegalArgumentException("offset: " + offset);
        }

        if (length < 0 || (offset + length) > value.length) {
            throw new IllegalArgumentException("length: " + length);
        }

        for (int i = 0; i < length; i++) {
            value[offset + i] = (byte) readUnsignedShort(8);
        }
    }


    private int readUnsignedByte(int length) throws IOException {

        if (length < 1 || length >= 8) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        if (avail == 0x00) {
            octet = input.readOctet();
            if (octet == -1) {
                throw new EOFException(":)");
            }
            avail = 0x08;
        }

        if (avail >= length) {
            int value = octet >> (avail - length);
            avail -= length;
            count += length;
            octet &= (0xFF >> (8 - avail));
            return value;
        } else {
            int requi = length - avail;
            return ((readUnsignedByte(avail) << requi) |
                    readUnsignedByte(requi));
        }
    }


    private int readUnsignedShort(int length) throws IOException {

        if (length < 1 || length >= 16) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        int value = 0x00;

        int quotient = length / 0x07;
        for (int i = 0; i < quotient; i++) {
            value <<= 0x07;
            value |= readUnsignedByte(0x07);
        }

        int remainder = length % 7;
        if (remainder > 0x00) {
            value <<= remainder;
            value |= readUnsignedByte(remainder);
        }

        return value;
    }


    /**
     * Reads specifed number of bits and returns an unsigned integer.
     *
     * @param length number of bits to read
     * @return an unsigned integer
     * @throws IOException if an I/O error occurs
     */
    public int readUnsignedInt(int length) throws IOException {

        if (length < 0x01 || length >= 0x20) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        int value = 0x00;

        int quotient = length / 0x0F;
        for (int i = 0; i < quotient; i++) {
            value <<= 15;
            value |= readUnsignedShort(0x0F);
        }

        int remainder = length % 0x0F;
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedShort(remainder);
        }

        return value;
    }


    /**
     * Read specifed length of bits and return an integer.
     *
     * @param length number of bits to read
     * @return a signed integer
     * @throws IOException if an I/O error occurs.
     */
    public int readInt(int length) throws IOException {

        if (length <= 0x01 || length > 0x20) {
            throw new IllegalArgumentException("length: " + length);
        }

        int value = (0x00 - readUnsignedByte(0x01)) << (length - 1);

        value |= readUnsignedInt(length -1);

        return value;
    }


    /**
     * Read a 32-bit signed integer.
     *
     * @return an int
     * @throws IOException if an I/O error occurs.
     */
    public int readInt() throws IOException {
        return readInt(0x20);
    }


    /**
     * Read <code>length</code> octet-long integer in little endian byte order.
     *
     * @param octetLength number of octets
     * @return an int value in little endian
     * @throws IOException if an I/O error occurs
     */
    public int readIntLE(final int octetLength) throws IOException {
        if (octetLength <= 0 || octetLength > 4) {
            throw new IllegalArgumentException("octetLength: " + octetLength);
        }

        int value = 0x00;
        for (int i = 0; i < octetLength; i++) {
            value |= (readUnsignedInt(8) << (i * 8));
        }

        return value;
    }


    /**
     * Read a 32-bit signed floating-point value.
     *
     * @return a float
     * @throws IOException if an I/O error occurs.
     */
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }


    /**
     * Reads specified number of bits and returns and unsigned integer.
     *
     * @param length number of bits to read.
     * @return an unsigned integer
     * @throws IOException if an I/O error occurs.
     */
    public long readUnsignedLong(int length) throws IOException {

        if (length < 1 || length >= 64) {
            throw new IllegalArgumentException("length: " + length);
        }

        long value = 0x00L;

        int quotient = length / 31;
        for (int i = 0; i < quotient; i++) {
            value <<= 31;
            value |= readUnsignedInt(31);
        }

        int remainder = length % 31;
        if (remainder > 0) {
            value <<= remainder;
            value |= readUnsignedInt(remainder);
        }

        return value;
    }


    /**
     * Reads specified number of bits and returns a signed long.
     *
     * @param length number of bits to read
     * @return a signed long
     * @throws IOException if an I/O error occurs.
     */
    public long readLong(int length) throws IOException {

        if (length <= 1 || length > 0x40) {
            throw new IllegalArgumentException("length: " + length);
        }

        long value = ((long) (0 - readUnsignedByte(1))) << (length - 1);

        value |= readUnsignedLong(length -1);

        return value;
    }


    /**
     * Reads a 64-bit long value.
     *
     * @return a long
     * @throws IOException if an I/O error occurs.
     */
    public long readLong() throws IOException {
        return readLong(0x40);
    }


    /**
     * Reads <code>length</code> octet-long long in little endian byte order.
     *
     * @param octetLength number of octets
     * @return an long value in little endian
     * @throws IOException if an I/O error occurs
     */
    public long readLongLE(final int octetLength) throws IOException {
        if (octetLength <= 0 || octetLength > 8) {
            throw new IllegalArgumentException("octetLength: " + octetLength);
        }

        long value = 0x00;
        for (int i = 0; i < octetLength; i++) {
            value |= (readUnsignedLong(8) << (i * 8));
        }

        return value;
    }


    /**
     * Reads a 64-bit signed floating-point value.
     *
     * @return a double
     * @throws IOException if an I/O error occurs.
     */
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }


    /**
     * Returns the number of bits consumed so far.
     *
     * @return number of bits consumed so far.
     */
    public long getCount() {
        return count;
    }


    /**
     * Reads some (if required) dummy bits for octet alignment.
     *
     * @param octetLength number of octets to be aligned
     * @throws IOException if an I/O error occurs
     */
    public void alignOctets(int octetLength) throws IOException {
        if (octetLength <= 0x00) {
            throw new IllegalArgumentException
                ("illegal octet length: " + octetLength);
        }

        int length = 8 - ((int) (count % (octetLength * 8)));

        int quotient = length / 7;
        for (int i = 0; i < quotient; i++) {
            readUnsignedByte(7);
        }

        int remainder = length % 7;
        if (remainder > 0) {
            readUnsignedByte(remainder);
        }
    }


    /**
     * Reads a string in modifed UTF-8 encoding.
     *
     * @return a string
     * @throws IOException if an I/O error occurs.
     */
    public String readUTF() throws IOException {
        Writer writer = new StringWriter();
        try {
            readUTF(writer);
            writer.flush();
            return writer.toString();
        } finally {
            writer.close();
        }
    }


    /**
     * Reads a sequence of characters in modified UTF-8 encoding.
     *
     * @param writer character source
     * @throws IOException if an I/O error occurs.
     */
    public void readUTF(Writer writer) throws IOException {

        byte[] bytes = new byte[readUnsignedInt(16)];
        readBytes(bytes);

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try {
            for (int a = -1; (a = bais.read()) != -1;) {
                if ((a >> 4) == 0x0F || // 1111xxxx
                    (a >> 6) == 0x02) { // 10xxxxxx
                    throw new UTFDataFormatException("a: " + a);
                }
                if ((a >> 7) == 0x00) { // 0xxxxxxxx
                    writer.append((char) a);
                } else if ((a >> 5) == 0x06) { // 110xxxxx
                    int b = bais.read();
                    if (b == -1 || (b >> 6) != 0x02) { // NOT(10xxxxxx)
                        throw new UTFDataFormatException("2b: " + b);
                    }
                    writer.append((char) (((a & 0x1F) << 6) | (b & 0x3F)));
                } else if (a >> 4 == 0x0E) { // 1110xxxx
                    int b = bais.read();
                    if (b == -1 || (b >> 6) != 0x02) { // NOT(10xxxxxx)
                        throw new UTFDataFormatException("3b: " + b);
                    }
                    int c = bais.read();
                    if (c == -1 || (c >> 6) != 0x02) { // NOT(10xxxxxx)
                        throw new UTFDataFormatException("3c: " + b);
                    }
                    writer.write((char) (((a & 0x0F) << 12) |
                                         ((b & 0x3F) << 6) |
                                          (c & 0x3F)));
                }
            }
        } finally {
            bais.close();
        }
    }


    public float readUnsignedFixedPoint(int m, int n) throws IOException {

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

        return readUnsignedInt(m + n) / new Double(Math.pow(2, n)).floatValue();
    }


    /**
     * Reads a signed fixed-point.
     *
     * @param m integer bit length (excluding the sign bit) (m >= 0)
     * @param n fraction bit length (n >= 0)
     * @return Q(m, n)
     * @throws IOException if an I/O error occurs
     */
    public float readFixedPoint(int m, int n) throws IOException {

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

        return readInt(1 + m + n) / new Double(Math.pow(2, n)).floatValue();
    }



    public double readUnsignedLongFixedPoint(int m, int n) throws IOException {

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

        return readUnsignedLong(m + n) / Math.pow(2, n);
    }


    /**
     * Reads a signed fixed-point.
     *
     * @param m integer bit length (excluding the sign bit) (m >= 0)
     * @param n fraction bit length (n >= 0)
     * @return Q(m, n)
     * @throws IOException if an I/O error occurs
     */
    public double readLongFixedPoint(int m, int n) throws IOException {

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

        return readLong(1 + m + n) / Math.pow(2, n);
    }


    /*
    private float readFraction(int n) throws IOException {
        float value = .0f;

        float fraction = .5f;
        for (int i = 0; i < n; i++) {
            if (readBoolean()) {
                value += fraction;
            }
            fraction /= 2.0f;
        }

        return value;
    }
     */


    private OctetInput input;

    private int octet = 0x00;
    private int avail = 0x00;

    private long count = 0L;
}