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
import java.io.OutputStream;


/**
 * Percent Decoder.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://tools.ietf.org/html/rfc3986#section-2.1">
 *      Percent Encoding</a>
 */
public class PercentDecoder {


    /**
     * Decodes given <code>input</code>.
     *
     * @param input input to decode
     * @return decoding output
     * @throws IOException if an I/O error occurs.
     * @throws PercentCodecException if a wrong value read from the input
     */
    public static byte[] decode(final String input)
        throws IOException, PercentCodecException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        return decode(input.getBytes("US-ASCII"));
    }


    /**
     * Decodes given <code>input</code>.
     *
     * @param input input to decode
     * @return decoding output
     * @throws IOException if an I/O error occurs.
     * @throws PercentCodecException if a wrong value read from the input.
     */
    public static byte[] decode(final byte[] input)
        throws IOException, PercentCodecException {

        if (input == null) {
            throw new NullPointerException("null bytes");
        }

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        decode(new ByteArrayInputStream(input), output);
        output.flush();

        return output.toByteArray();
    }


    /**
     * Decodes from <code>input</code> to <code>output</code>.
     *
     * @param input input
     * @param output output
     * @throws IOException if an I/O error occurs.
     * @throws PercentCodecException if a wrong value read from the input.
     */
    public static void decode(final InputStream input,
                              final OutputStream output)
        throws IOException, PercentCodecException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        if (output == null) {
            throw new NullPointerException("null output");
        }

        for (int b = -1; (b = input.read()) != -1;) {

            if ((b >= 0x30 && b <= 0x39) // digit
                || (b >= 0x41 && b <= 0x5A) // upper case alpha
                || (b >= 0x61 && b <= 0x7A) // lower case alpha
                || b == 0x2D // -
                || b == 0x5F // _
                || b == 0x2E // .
                || b == 0x7E) { // ~
                output.write(b);
            } else if (b == 0x25) { // '%'
                int high = input.read();
                if (high == -1) {
                    throw new EOFException("eof");
                }
                int row = input.read();
                if (row == -1) {
                    throw new EOFException("eof");
                }
                output.write((atoi(high) << 4) | atoi(row));
            } else {
                throw new IOException("illegal octet: " + b);
            }
        }
    }


    /**
     * Converts a single 7-bit ASCII value to a 4-bit unsigned integer. A
     * <code>PercentCodecException</code> will be thrown if given
     * <code>ascii</code> is in wrong range.
     *
     * @param ascii 7-bit ASCII value; digit (0x30 ~ 0x39),
     *        upper alpha (0x41 ~ 0x46), or lower alpha (0x61 ~ 0x66)
     * @return 4-bit unsigned integer (0x00 ~ 0x0F)
     * @throws PercentCodecException if given <code>ascii</code> is not valid
     */
    static int atoi(final int ascii) throws PercentCodecException {

        if (ascii < 0x30) { // ~ 0x2F('/')
            throw new PercentCodecException("wrong ascii: " + ascii);
        }

        if (ascii <= 0x39) { // 0x30('0') ~ 0x39('9')
            return ascii - 0x30; // 0x00, 0x01, ...
        }

        if (ascii <= 0x40) { // 0x3A(':') ~ 0x40('@')
            throw new PercentCodecException("wrong ascii: " + ascii);
        }

        if (ascii <= 0x46) { // 0x41('A') ~ 0x46('F')
            return ascii - 0x37; // 0x0A, 0x0B, ...
        }

        if (ascii <= 0x60) { // 0x47('G') ~ 0x60('`')
            throw new PercentCodecException("wrong ascii: " + ascii);
        }

        if (ascii <= 0x66) { // 0x61('a') ~ 0x66('f')
            return ascii - 0x57; // 0x0A, 0x0B, ...
        }

        // 0x67('g') ~
        throw new PercentCodecException("wrong ascii: " + ascii);

        //return ascii - (ascii >= 0x41 ? 0x37 : 0x30);
    }


    /**
     * Creates a new instance.
     */
    protected PercentDecoder() {
        super();
    }
}
