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


package com.googlecode.jinahya.rfc3986;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;


/**
 * Percent Decoder.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/w4GhD">Percent Encoding</a>
 */
public class PercentDecoder {


    /**
     * Decodes given
     * <code>input</code>.
     *
     * @param input input to decode
     *
     * @return decoded output
     *
     * @throws IOException if an I/O error occurs
     */
    public static byte[] decode(final String input) throws IOException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        return decode(input.getBytes("US-ASCII"));
    }


    /**
     * Decodes given
     * <code>input</code>.
     *
     * @param input input to decode
     *
     * @return decoding output
     *
     * @throws IOException if an I/O error occurs
     */
    public static byte[] decode(final byte[] input) throws IOException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        decode(new ByteArrayInputStream(input), output);
        output.flush();

        return output.toByteArray();
    }


    /**
     * Decodes characters from
     * <code>input</code> and writes those decoded bytes to
     * <code>output</code>.
     *
     * @param input character input
     * @param output byte output
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void decode(final InputStream input,
                              final OutputStream output)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        if (output == null) {
            throw new NullPointerException("null output");
        }

        final Reader reader = new InputStreamReader(input, "US-ASCII");

        decode(reader, output);
    }


    /**
     * Decodes characters from
     * <code>input</code> and writes those decoded bytes to
     * <code>output</code>.
     *
     * @param input character input
     * @param output byte output
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void decode(final Reader input, final OutputStream output)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        if (output == null) {
            throw new NullPointerException("null output");
        }

        int high, low;
        for (int c = -1; (c = input.read()) != -1;) {
            if ((c >= 0x30 && c <= 0x39) // digit
                || (c >= 0x41 && c <= 0x5A) // upper case alpha
                || (c >= 0x61 && c <= 0x7A) // lower case alpha
                || c == 0x2D || c == 0x5F || c == 0x2E || c == 0x7E) { // -_.~
                output.write(c);
            } else {
                if (c != 0x25) {
                    throw new IOException("expected '%'");
                }
                if ((high = input.read()) == -1) {
                    throw new EOFException("eof");
                }
                if ((low = input.read()) == -1) {
                    throw new EOFException("eof");
                }
                output.write(atoi(high) << 4 | atoi(low));
            }
        }
    }


    /**
     * Converts a single 7-bit ASCII value to a 4-bit unsigned integer.
     *
     * @param ascii 7-bit ASCII value; digit (0x30 ~ 0x39), upper alpha (0x41 ~
     * 0x46), or lower alpha (0x61 ~ 0x66)
     *
     * @return 4-bit unsigned integer (0x00 ~ 0x0F)
     */
    private static int atoi(final int ascii) {

        switch (ascii) {
            case 0x30: // '0'
            case 0x31: // '1'
            case 0x32: // '2'
            case 0x33: // '3'
            case 0x34: // '4'
            case 0x35: // '5'
            case 0x36: // '6'
            case 0x37: // '7'
            case 0x38: // '8'
            case 0x39: // '9'
                return ascii - 0x30; // 0x00 - 0x09
            case 0x41: // 'A'
            case 0x42: // 'B'
            case 0x43: // 'C'
            case 0x44: // 'D'
            case 0x45: // 'E'
            case 0x46: // 'F'
                return ascii - 0x37;
            case 0x61: // 'a'
            case 0x62: // 'b'
            case 0x63: // 'c'
            case 0x64: // 'd'
            case 0x65: // 'e'
            case 0x66: // 'f'
                return ascii - 0x57;
            default:
                throw new IllegalArgumentException("illegal asccii");
        }
    }


    /**
     * Creates a new instance.
     */
    protected PercentDecoder() {
        super();
    }


}

