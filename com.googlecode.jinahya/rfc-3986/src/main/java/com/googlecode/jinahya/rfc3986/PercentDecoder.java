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
import java.io.UnsupportedEncodingException;


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
     * @throws UnsupportedEncodingException
     */
    public static byte[] decode(final String input)
        throws UnsupportedEncodingException {

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
     */
    public static byte[] decode(final byte[] input) {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            decode(new ByteArrayInputStream(input), output);
            output.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }

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

        final char[] hex = new char[2];

        int h;
        int l;
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
                if ((h = input.read()) == -1) {
                    throw new EOFException("eof");
                }
                if ((l = input.read()) == -1) {
                    throw new EOFException("eof");
                }
                output.write(atoi(h) << 4 | atoi(l));
            }
        }
    }


    /**
     * Converts a single 7-bit ASCII value to a 4-bit unsigned integer.
     *
     * @param a 7-bit ASCII value; digit (0x30 ~ 0x39), upper alpha (0x41 ~
     * 0x46), or lower alpha (0x61 ~ 0x66)
     *
     * @return 4-bit unsigned integer (0x00 ~ 0x0F)
     */
    private static int atoi(final int a) {

        if (a < 0x30) { // ~ 0x2F('/')
            throw new IllegalArgumentException("wrong ascii: " + a);
        }

        if (a <= 0x39) { // 0x30('0') ~ 0x39('9')
            return a - 0x30; // 0x00, 0x01, ...
        }

        if (a <= 0x40) { // 0x3A(':') ~ 0x40('@')
            throw new IllegalArgumentException("wrong ascii: " + a);
        }

        if (a <= 0x46) { // 0x41('A') ~ 0x46('F')
            return a - 0x37; // 0x0A, 0x0B, ...
        }

        if (a <= 0x60) { // 0x47('G') ~ 0x60('`')
            throw new IllegalArgumentException("wrong ascii: " + a);
        }

        if (a <= 0x66) { // 0x61('a') ~ 0x66('f')
            return a - 0x57; // 0x0A, 0x0B, ...
        }

        // 0x67('g') ~
        throw new IllegalArgumentException("wrong ascii: " + a);
    }


    /**
     * Creates a new instance.
     */
    protected PercentDecoder() {
        super();
    }


}

