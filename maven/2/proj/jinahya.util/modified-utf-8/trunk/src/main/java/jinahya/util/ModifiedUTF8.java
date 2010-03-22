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
import java.io.Writer;
import java.io.UTFDataFormatException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class ModifiedUTF8 {


    /**
     * Generates a string encoded in Modifed UTF-8. The byte length is going to
     * be randomly choosen between 0(inclusive) and 65536(exclusive).
     *
     * @param acceptor a character acceptor.
     * @return a randomly generated string.
     * @throws IOException if an I/O error occurs.
     * @see #generateString(int, jinahya.util.ModifiedUTF8.Acceptor)
     */
    public static String generateString(final Acceptor acceptor)
        throws IOException {

        return generateString((int) (Math.random() * 65536.0d), acceptor);
    }


    /**
     * Generates a string encoded in Modified UTF-8.
     *
     * @param length the maximum length in bytes between 0(inclusive) and 65536
     *        (exclusive)
     * @param acceptor a character acceptor
     * @return a generated string
     * @throws IOException if an I/O error occurs.
     */
    public static String generateString(final int length,
                                        final Acceptor acceptor)
        throws IOException {

        final CharArrayWriter target = new CharArrayWriter();
        generateString(length, acceptor, target);
        target.flush();
        return target.toString();

    }


    /**
     * Generates a string encoded in Modified UTF-8 and writes to specifed
     * <code>target</code>.
     *
     * @param length the maximum number of bytes between 0 (inclusive) and 65536
     *        (exclusive)
     * @param acceptor character acceptor
     * @param target a writer to which generated character sequence is write
     * @throws IOException if an I/O error occurs.
     */
    public static void generateString(final int length, final Acceptor acceptor,
                                      final Writer target)
        throws IOException {

        if (length < 0) {
            throw new IllegalArgumentException("length(" + length + ") < 0");
        }

        if (length > 65535) {
            throw new IllegalArgumentException(
                "length(" + length + ") > 65535");
        }

        if (acceptor == null) {
            throw new NullPointerException("acceptor");
        }

        if (target == null) {
            throw new NullPointerException("target");
        }

        int byteCount = 0;
        final int[] bytes = new int[3];

        char ch;

        int totalByteCount = 0;
        while (totalByteCount < length - 2) {

            while (true) {
                bytes[0] = (int) (Math.random() * 256);

                final int shifted = bytes[0] >> 4;

                if (shifted <= 7) { // 0xxx xxxx
                    byteCount = 1;
                    break;

                } else if (shifted <= 11) { // 10xx xxxx
                    continue;

                } else if (shifted <= 13) { // 110x xxxx
                    bytes[1] = (byte) ((Math.random() * 64) + 128);
                    byteCount = 2;
                    break;

                } else if (shifted <= 14) { // 1110 xxxx
                    bytes[1] = (byte) ((Math.random() * 64) + 128);
                    bytes[2] = (byte) ((Math.random() * 64) + 128);
                    byteCount = 3;
                    break;

                } else { // shifted = 15 // 1111 xxxx
                    continue;
                }
            }

            ch = toChar(bytes, byteCount);

            if (acceptor.accept(ch)) {
                target.write(ch);
                totalByteCount += byteCount;
            }
        }

        // padding
        while (totalByteCount < length) {
            target.write(acceptor.generate()); // '~'
            totalByteCount++;
        }
    }


    /**
     * The interface for the character accepting function.
     */
    public static interface Acceptor {

        /**
         * Check whether the specified <code>ch</code> is acceptable or not.
         *
         * @param c the char to be checked.
         * @return true if given <code>ch</code> is acceptable, false otherwise.
         */
        boolean accept(char c);


        /**
         * Generatea a character for padding.
         *
         * @return any char acceptable to this acceptor.
         */
        char generate();
    }


    /*
    private static char generateAChar(final Acceptor acceptor) {

        final int[] bytes = new int[3];
        int count;
        char ch;

        do {
            count = generateBytesForAChar(bytes);
            ch = toChar(count, bytes);
        } while (!acceptor.accept(ch));

        return ch;
    }
     */


    /**
     * Convert given <code>ch</code> into a byte sequence.
     *
     * @param ch char to be converted.
     * @param bytes byte array into which each bytes are write
     * @return the number of valid octets in <code>bytes</code>
     * @throws UTFDataFormatException if any illegal bytes detected.
     * @see java.io.DataOutput#writeUTF(java.lang.String);
     */
    private static int toBytes(final char ch, final int[] bytes)
        throws UTFDataFormatException {

        if (bytes == null) {
            throw new NullPointerException("bytes");
        }

        if (bytes.length != 3) {
            throw new IllegalArgumentException(
                "bytes.length(" + bytes.length + ") != 3");
        }

        if (ch >= '\u0001' && ch <= '\u007F') {
            bytes[0] = ch;
            return 1;

        } else if (ch == '\u0000' || ch <= '\u07FF') {
            bytes[0] = (0xC0 | (0x1F & (ch >> 6)));
            bytes[1] = (0x80 | (0x3F & ch));
            return 2;

        } else { // if (c <= '\uFFFF') {
            bytes[0] = (0xE0 | (0x0F & (ch >> 12)));
            bytes[1] = (0x80 | (0x3F & (ch >> 6)));
            bytes[2] = (0x80 | (0x3F & ch));
            return 3;
        }
    }


    /**
     * Compose a character with first <code>count</code> bytes in
     * <code>bytes</code>.
     *
     * @param bytes a 3 byte long array
     * @param count number of valid bytes in <code>word</code>.
     * @return a character.
     * @see java.io.DataInput#readUTF()
     */
    private static char toChar(final int[] bytes, final int count) {
        if (count == 1) {
            return (char) bytes[0];
        } else if (count == 2) {
            return (char) (((bytes[0] & 0x1F) << 6) | (bytes[1] & 0x3F));
        } else { // count = 3
            return (char) (((bytes[0] & 0x0F) << 12) | ((bytes[1] & 0x3F) << 6)
                           | (bytes[2] & 0x3F));
        }
    }



    /*
     * Generates a sequence of bytes for a single character.
     *
     * @param bytes a 3 length int array for generated bytes.
     * @return the number of valid bytes in given <code>bytes</code>.
    private static int generateBytesForAChar(final int[] bytes) {

        if (bytes == null) {
            throw new NullPointerException("bytes");
        }

        if (bytes.length != 3) {
            throw new IllegalArgumentException(
                "bytes.length(" + bytes.length + ") != 3");
        }

        while (true) {

            bytes[0] = (int) (Math.random() * 256);

            final int shifted = bytes[0] >> 4;

            if (shifted <= 7) { // 0xxx xxxx
                return 1;

            } else if (shifted <= 11) { // 10xx xxxx
                continue;

            } else if (shifted <= 13) { // 110x xxxx
                bytes[1] = (byte) ((Math.random() * 64) + 128);
                return 2;

            } else if (shifted <= 14) { // 1110 xxxx
                bytes[1] = (byte) ((Math.random() * 64) + 128);
                bytes[2] = (byte) ((Math.random() * 64) + 128);
                return 3;

            } else { // shifted = 15 // 1111 xxxx
                continue;
            }
        }
    }
     */


    /**
     * Reads a string from <code>in</code>.
     *
     * @param in input stream
     * @return decoded string
     * @throws IOException if an I/O error occur.
     * @see java.io.DataInput#readUTF()
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

        return new String(decodeRaw(encoded));
    }


    /**
     * Writes the given <code>s</code> to specified <code>out</code>.
     *
     * @param s string
     * @param out outputs stream
     * @throws IOException if an I/O error occurs.
     * @see java.io.DataOutput#writeUTF(java.lang.String)
     */
    public static void writeString(final String s, final OutputStream out)
        throws IOException {

        final byte[] encoded = encodeRaw(s.toCharArray());

        // write UTF length
        out.write(encoded.length >> 8);
        out.write(encoded.length);

        out.write(encoded);
    }


    /*
     * Decodes from a sequence of bytes into a sequence of characters.
     * First 2 bytes are read for <i>UTF length</i>.
     *
     * @param input byte input.
     * @param output char output.
     * @return the number of decoded characters.
     * @throws IOException if an I/O error occurs.
     * @see java.io.DataInput#readUTF()
     * @see java.io.DataOutput#writeUTF(java.lang.String)
    private static int decode(final InputStream input, final Writer output)
        throws IOException {

        final int length = (input.read() << 8) | input.read();
        return decode(length, input, output);
    }
     */


    /*
     * Decodes from a sequence of bytes into a sequence of characters.
     *
     * @param length the number of bytes to read; <i>UTF length</i>.
     * @param input byte input
     * @param output char output
     * @return the number of decoded characters
     * @throws IOException if an I/O error occurs
    private static int decode(final int length, final InputStream input,
                              final Writer output)
        throws IOException {

        if (length < 0) {
            throw new IllegalArgumentException("length(" + length + ") < 0");
        }

        if (length > 65535) {
            throw new IllegalArgumentException(
                "length(" + length + ") > 65535");
        }

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (output == null) {
            throw new NullPointerException("output");
        }


        final int[] bytes = new int[3];

        int charCount = 0;

        for (int byteCount = 0; byteCount < length;) {

            if ((bytes[0] = input.read()) == -1) {
                throw new EOFException("EOF at byte count(" + byteCount + ")");
            }

            final int shifted = bytes[0] >> 4;
            if (shifted <= 7) { // 0xxx xxxx
                output.write(toChar(bytes, 1));
                charCount++;

            } else if (shifted <= 11) { // 10xx xxxx
                throw new UTFDataFormatException(
                    "illegal byte a: " + Integer.toBinaryString(bytes[0]));

            } else if (shifted <= 13) { // 110x xxxx
                if ((bytes[1] = input.read()) == -1) {
                    throw new EOFException(
                        "EOF at byte count(" + byteCount + ")");
                }
                if ((bytes[1] >> 6) != 2) { // NOT 10xx xxxx
                    throw new UTFDataFormatException(
                        "illegal byte b: " + Integer.toBinaryString(bytes[1]));
                }
                byteCount++;
                output.write(toChar(bytes, 2));
                charCount++;

            } else if (shifted <= 14) { // 1110 xxxx
                if ((bytes[1] = input.read()) == -1) {
                    throw new EOFException(
                        "EOF at byte count(" + byteCount + ")");
                }
                if ((bytes[1] >> 6) != 2) { // NOT 10xx xxxx
                    throw new UTFDataFormatException(
                        "illegal byte b: " + Integer.toBinaryString(bytes[1]));
                }
                byteCount++;

                if ((bytes[2] = input.read()) == -1) {
                    throw new EOFException(
                        "EOF at byte count(" + byteCount + ")");
                }
                if ((bytes[2] >> 6) != 2) { // NOT 10xx xxxx
                    throw new UTFDataFormatException(
                        "illegal byte c: " + Integer.toBinaryString(bytes[2]));
                }
                byteCount++;

                output.write(toChar(bytes, 3));
                charCount++;

            } else { // shifted = 15 // 1111 xxxx
                throw new UTFDataFormatException(
                    "illegal byte a:" + Integer.toBinaryString(bytes[0]));
            }

            byteCount++; // byteCount increment for byte a

        }

        return charCount;
    }
    */


    /**
     * Decodes an array of bytes into an array of chars.
     *
     * @param encoded encoded byte array
     * @return decoded char array
     * @throws IOException if an I/O error occurs
     */
    public static char[] decodeRaw(final byte[] encoded) throws IOException {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        if (encoded.length > 65535) {
            throw new IllegalArgumentException(
                "encoded.length(" + encoded.length + ") > 65535");
        }

        //final InputStream input = new ByteArrayInputStream(encoded);
        final CharArrayWriter output = new CharArrayWriter();

        byte bytea;
        byte byteb;
        byte bytec;

        for (int index = 0; index < encoded.length;) {

            bytea = encoded[index++];

            final int shifted = (bytea & 0xFF) >> 4;
            if (shifted <= 7) { // 0xxx xxxx
                output.write((byte) bytea);

            } else if (shifted <= 11) { // 10xx xxxx
                throw new UTFDataFormatException(
                    "illegal byte a: " + Integer.toBinaryString(bytea));

            } else if (shifted <= 13) { // 110x xxxx
                try {
                    byteb = encoded[index++];
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    throw new EOFException(
                        "EOF at byte index(" + index + ")");
                }
                if (((byteb & 0xFF) >> 6) != 2) { // NOT 10xx xxxx
                    throw new UTFDataFormatException(
                        "illegal byte b: " + Integer.toBinaryString(byteb));
                }
                output.write((char) (((bytea & 0x1F) << 6) | (byteb & 0x3F)));

            } else if (shifted <= 14) { // 1110 xxxx

                try {
                    byteb = encoded[index++];
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    throw new EOFException(
                        "EOF at byte index(" + index + ")");
                }
                if (((byteb & 0xFF) >> 6) != 2) { // NOT 10xx xxxx
                    throw new UTFDataFormatException(
                        "illegal byte b: " + Integer.toBinaryString(byteb));
                }

                try {
                    bytec = encoded[index++];
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    throw new EOFException(
                        "EOF at byte index(" + index + ")");
                }
                if (((bytec & 0xFF) >> 6) != 2) { // NOT 10xx xxxx
                    throw new UTFDataFormatException(
                        "illegal byte b: " + Integer.toBinaryString(byteb));
                }
                output.write((char) (((bytea & 0x0F) << 12)
                                     | ((byteb & 0x3F) << 6)
                                     | (bytec & 0x3F)));

            } else { // shifted = 15 // 1111 xxxx
                throw new UTFDataFormatException(
                    "illegal byte a:" + Integer.toBinaryString(bytea));
            }
        }

        //decode(encoded.length, input, output);

        output.flush();

        return output.toCharArray();
    }


    /**
     * Encodes a sequence of characters into a byte array in Modified UTF-8
     * format.
     *
     * @param decoded decoded char array
     * @return encoded byte array
     * @throws IOException if an I/O error occurs.
     */
    public static byte[] encodeRaw(final char[] decoded) throws IOException {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int byteCount;
        final int[] bytes = new int[3];

        for (int i = 0; i < decoded.length; i++) {

            byteCount = toBytes(decoded[i], bytes);
            for (int j = 0; j < byteCount; j++) {
                baos.write(bytes[j]);
            }

            if (baos.size() > 65535) {
                throw new UTFDataFormatException(
                    "length(" + baos.size() + ") > 65535");
            }
        }

        baos.flush();

        return baos.toByteArray();
    }


    /**
     *
     */
    private ModifiedUTF8() {
        super();
    }
}
