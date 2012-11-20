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
import java.io.UnsupportedEncodingException;
import java.io.Writer;


/**
 * Percent Encoder.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/w4GhD">Percent Encoding</a>
 */
public class PercentEncoder {


    /**
     * Converts a 4-bit unsigned integer to a single 7-bit ASCII value. An
     * <code>IllegalArgumentException</code> will be thrown if given integer is
     * in wrong range.
     *
     * @param integer a 4-bit unsigned integer (0x00 ~ 0x0F)
     *
     * @return 7-bit ASCII value; digit (0x30 ~ 0x39), upper alpha (0x41 ~ 0x46)
     */
    protected static int encodeHalf(final int decoded) {

        if (decoded >> 4 != 0) {
            throw new IllegalArgumentException("illegal decoded: " + decoded);
        }

        return decoded + (decoded < 0x0A ? 0x30 : 0x37);
        
//        switch (decoded) {
//            case 0x00:
//            case 0x01:
//            case 0x02:
//            case 0x03:
//            case 0x04:
//            case 0x05:
//            case 0x06:
//            case 0x07:
//            case 0x08:
//            case 0x09:
//                return decoded + 0x30; // '0' - '9'
//            case 0x0A:
//            case 0x0B:
//            case 0x0C:
//            case 0x0D:
//            case 0x0E:
//            case 0x0F:
//                return decoded + 0x37; // 'A' - 'F'
//            default:
//                throw new IllegalArgumentException(
//                    "illegal integer: " + decoded);
//        }
    }


    protected static int encodeSingle(final int decoded, final byte[] encoded,
                                      final int offset) {

        if (decoded >> 8 != 0) {
            throw new IllegalArgumentException("illegal decoded: " + decoded);
        }

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (encoded.length < 3) {
            // not required
            throw new IllegalArgumentException(
                "encoded.length(" + encoded.length + ") < 3");
        }

        if (offset < 0) {
            throw new IllegalArgumentException("offset(" + offset + ") < 0");
        }

        if (offset >= encoded.length - 2) {
            throw new IllegalArgumentException(
                "offset(" + offset + ") >= encoded.length(" + encoded.length
                + ")");
        }

        if ((decoded >= 0x30 && decoded <= 0x39) // digit
            || (decoded >= 0x41 && decoded <= 0x5A) // upper case alpha
            || (decoded >= 0x61 && decoded <= 0x7A) // lower case alpha
            || decoded == 0x2D || decoded == 0x5F || decoded == 0x2E
            || decoded == 0x7E) { // -_.~
            encoded[offset] = (byte) decoded;
            return 1;
        } else {
            encoded[offset] = 0x25; // '%'
            encoded[offset + 1] = (byte) encodeHalf(decoded >> 4);
            encoded[offset + 1] = (byte) encodeHalf(decoded & 0x0F);
            return 3;
        }
    }


    /**
     * Encodes given
     * <code>input</code>.
     *
     * @param input string to encode
     *
     * @return encoding output
     */
    public static byte[] encode(final String input) {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        try {
            return encode(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("'UTF-8' is not supported");
        }
    }


    /**
     * Encodes given
     * <code>input</code>.
     *
     * @param input bytes to encode
     *
     * @return encoding output
     */
    public static byte[] encode(final byte[] input) {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            encode(new ByteArrayInputStream(input), output);
            output.flush();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

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
//        encodeSingle(input, writer);
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
                output.write(encodeHalf(b >> 4));
                output.write(encodeHalf(b & 0x0F));
            }
        }
    }


    /**
     * Creates a new instance.
     */
    public PercentEncoder() {
        super();
    }


    public byte[] encode1(final byte[] decoded) {

        if (decoded == null) {
            throw new NullPointerException("null decoded");
        }

        final byte[] encoded = new byte[decoded.length * 3];

        int offset = 0;
        for (int i = 0; i < decoded.length; i++) {
            offset += encodeSingle(decoded[i] & 0xFF, encoded, offset);
        }

        if (offset == encoded.length) {
            return encoded;
        }

        final byte[] trimmed = new byte[offset];
        System.arraycopy(encoded, 0, trimmed, 0, offset);

        return trimmed;
    }


}

