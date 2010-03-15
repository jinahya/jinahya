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


/**
 *
 */
package jinahya.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UTFDataFormatException;
import java.io.Writer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class ModifiedUTF8 {


    /**
     * Generates a string encoded in Modified UTF-8.
     *
     * @param length the maximum length in bytes between 0 (inclusive) and 65536
     *        (exclusive)
     * @return a generated string
     * @throws IOException if an I/O error occurs.
     */
    public static String generateString(final int length) throws IOException {
        final CharArrayWriter caw = new CharArrayWriter();
        try {
            generateString(length, caw);
            caw.flush();
            return caw.toString();
        } finally {
            caw.close();
        }
    }


    /**
     * Generates a string encoded in Modified UTF-8.
     *
     * @param length the maximum number of bytes between 0 (inclusive) and 65536
     *        (exclusive)
     * @param target a writer to which generated character sequence is write
     * @throws IOException if an I/O error occurs.
     */
    public static void generateString(final int length, final Writer target)
        throws IOException {

        int _length = Math.max(0, length);
        _length = Math.min(_length, 65535);

        int first;
        int second;
        int third;

        int count = 0;
        while (count < _length - 2) {
            first = (int) (Math.random() * 256);

            switch ((first >> 4)) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    /* 0xxx xxxx */
                    char ch1 = (char) first;
                    if (Character.isISOControl(ch1)) {
                        break;
                    }
                    target.write(ch1);
                    count++;
                    break;

                case 12:
                case 13:
                    /* 110x xxxx */
                    second = ((int) (Math.random() * 64)) + 128;
                    char ch2 = (char) (((first & 0x1F) << 6)
                        | (second & 0x3F));
                    if (Character.isISOControl(ch2)) {
                        break;
                    }
                    target.write(ch2);
                    count += 2;
                    break;

                case 14:
                    /* 1110 xxxx */
                    second = ((int) (Math.random() * 64)) + 128;
                    third = ((int) (Math.random() * 64)) + 128;
                    char ch3 = (char) (((first & 0x0F) << 12)
                        | ((second & 0x3F) << 6)
                        | (third & 0x3F));
                    if (Character.isISOControl(ch3)) {
                        break;
                    }
                    target.write(ch3);
                    count += 3;
                    break;

                default:
                    /* 10xx xxxx, 1111 xxxx */
                    break;
            }
        }

        // zero padding
        while (count < _length) {
            target.write(0);
            count++;
        }
    }


    /**
     * Reads a string from <code>in</code>.
     *
     * @param in input stream
     * @return decoded string
     * @throws IOException if an I/O error occur.
     */
    public static String readString(final InputStream in) throws IOException {

        final int length = ((in.read() << 8) | in.read());
        if (length == -1) {
            throw new EOFException("EOF while reading length");
        }

        final byte[] encoded = new byte[length];

        // read fully
        for (int offset = 0; offset < encoded.length;) {
            int read = in.read(encoded, offset, encoded.length - offset);
            if (read == -1) {
                throw new EOFException("EOF while reading bytes at " + offset);
            }
            offset += read;
        }

        return new String(decode(encoded));
    }


    /**
     * Writes the given <code>s</code> to specified <code>out</code>.
     *
     * @param s string
     * @param out outputs stream
     * @throws IOException if an I/O error occurs.
     */
    public static void writeString(final String s, final OutputStream out)
        throws IOException {

        final byte[] encoded = encode(s.toCharArray());

        out.write(encoded.length >> 8);
        out.write(encoded.length & 0xFF);

        out.write(encoded);
    }


    /**
     * Decodes an array of bytes into an array of chars.
     *
     * @param encoded encoded byte array
     * @return decoded char array
     * @throws IOException if an I/O error occurs
     */
    public static char[] decode(final byte[] encoded) throws IOException {

        final CharArrayWriter caw = new CharArrayWriter();
        try {
            int byte1;
            int byte2;
            int byte3;
            for (int i = 0; i < encoded.length;) {
                byte1 = encoded[i++] & 0xFF;
                switch ((byte1 >> 4)) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        /* 0xxx xxxx */

                        caw.write(byte1);

                        break;

                    case 12:
                    case 13:
                        /* 110x xxxx */

                        try {
                            byte2 = encoded[i++] & 0xFF;
                        } catch (ArrayIndexOutOfBoundsException aioobe) {
                            throw new EOFException("EOF at " + i);
                        }
                        if (byte2 >> 6 != 0x02) { // !(10xx xxxx)
                            throw new UTFDataFormatException(
                                "illegal byte2 byte("
                                + Integer.toBinaryString(byte2) + ")");
                        }

                        char ch2 = (char) (((byte1 & 0x1F) << 6) | (byte2 & 0x3F));
                        caw.write(ch2);

                        break;

                    case 14:
                        /* 1110 xxxx */

                        try {
                            byte2 = encoded[i++] & 0xFF;
                        } catch (ArrayIndexOutOfBoundsException aioobe) {
                            throw new EOFException("EOF at " + i);
                        }
                        if (byte2 >> 6 != 0x02) { // !(10xx xxxx)
                            throw new UTFDataFormatException(
                                "illegal byte2(" + Integer.toBinaryString(byte2)
                                + ")");
                        }

                        try {
                            byte3 = encoded[i++] & 0xFF;
                        } catch (ArrayIndexOutOfBoundsException aioobe) {
                            throw new EOFException("EOF at " + i);
                        }
                        if (byte3 >> 6 != 0x02) { // !(10xx xxxx)
                            throw new UTFDataFormatException(
                                "illegal byte3(" + Integer.toBinaryString(byte3)
                                + ")");
                        }

                        char ch3 = (char) (((byte1 & 0x0F) << 12)
                                           | ((byte2 & 0x3F) << 6)
                                           | (byte3 & 0x3F));
                        caw.write(ch3);
                        break;

                    default:
                        /* 10xx xxxx, 1111 xxxx */
                        throw new UTFDataFormatException(
                            "illegal byte1(" + Integer.toBinaryString(byte1)
                            + ")");
                }
            }

            caw.flush();
            return caw.toCharArray();

        } finally {
            caw.close();
        }
    }





    /**
     * Encodes a sequence of characters into a byte array in Modified UTF-8
     * format.
     *
     * @param decoded decoded char array
     * @return encoded byte array
     * @throws IOException if an I/O error occurs.
     */
    public static byte[] encode(final char[] decoded) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            for (int i = 0; i < decoded.length; i++) {
                char c = decoded[i];
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
            return baos.toByteArray();
        } finally {
            baos.close();
        }
    }


}
