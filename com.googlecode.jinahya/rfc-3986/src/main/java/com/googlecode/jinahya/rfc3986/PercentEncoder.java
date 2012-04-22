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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;


/**
 * Percent Encoder.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/w4GhD">Percent Encoding</a>
 */
public class PercentEncoder {


    /**
     * Encodes given
     * <code>input</code>.
     *
     * @param input string to encode
     *
     * @return encoding output
     *
     * @throws IOException if an I/O error occurs
     */
    public static byte[] encode(final String input) throws IOException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        return encode(input.getBytes("UTF-8"));
    }


    /**
     * Encodes given
     * <code>input</code>.
     *
     * @param input bytes to encode
     *
     * @return encoding output
     *
     * @throws IOException if an I/O error occurs
     */
    public static byte[] encode(final byte[] input) throws IOException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        encode(new ByteArrayInputStream(input), output);
        output.flush();

        return output.toByteArray();
    }


    /**
     * Encodes bytes from
     * <code>input</code> and writes encoded bytes to
     * <code>output</code>.
     *
     * @param input input
     * @param output output
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void encode(final InputStream input,
                              final OutputStream output)
        throws IOException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        if (output == null) {
            throw new IllegalArgumentException("null output");
        }

        final Writer writer = new OutputStreamWriter(output, "US-ASCII");
        encode(input, writer);
        writer.flush();
    }


    /**
     * Encodes bytes from
     * <code>input</code> and writes those encoded characters to
     * <code>output</code>.
     *
     * @param input byte input
     * @param output character output
     *
     * @throws IOException if an I/O error occurs
     */
    public static void encode(final InputStream input, final Writer output)
        throws IOException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        if (output == null) {
            throw new IllegalArgumentException("null output");
        }

        for (int b = -1; (b = input.read()) != -1;) {
            if ((b >= 0x30 && b <= 0x39) // digit
                || (b >= 0x41 && b <= 0x5A) // upper case alpha
                || (b >= 0x61 && b <= 0x7A) // lower case alpha
                || b == 0x2D || b == 0x5F || b == 0x2E || b == 0x7E) { // -_.~
                output.write(b);
            } else {
                output.write(0x25); // '%'
                output.write(itoa(b >> 4));
                output.write(itoa(b & 0xF));
            }
        }
    }


    /**
     * Converts a 4-bit unsigned integer to a single 7-bit ASCII value. A
     * <code>PercentCodecException</code> will be thrown if given
     * <code>integer</code> is in wrong range.
     *
     * @param integer 4-bit unsigned integer (0x00 ~ 0x0F)
     *
     * @return 7-bit ASCII value; digit (0x30 ~ 0x39), upper alpha (0x41 ~ 0x46)
     */
    private static int itoa(final int integer) throws IOException {

        switch (integer) {
            case 0x00:
            case 0x01:
            case 0x02:
            case 0x03:
            case 0x04:
            case 0x05:
            case 0x06:
            case 0x07:
            case 0x08:
            case 0x09:
                return integer + 0x30; // '0' - '9'
            case 0x0A:
            case 0x0B:
            case 0x0C:
            case 0x0D:
            case 0x0E:
            case 0x0F:
                return integer + 0x37; // 'A' - 'F'
            default:
                throw new IllegalArgumentException(
                    "illegal integer: " + integer);
        }
    }


    /**
     * Creates a new instance.
     */
    protected PercentEncoder() {
        super();
    }


}

