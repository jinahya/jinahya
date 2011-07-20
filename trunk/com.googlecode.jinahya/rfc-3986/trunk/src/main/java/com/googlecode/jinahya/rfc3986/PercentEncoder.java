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


/**
 *
 * @author <a href="jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://tools.ietf.org/html/rfc3986#section-2.1">
 *      Percent Encoding</a>
 */
public class PercentEncoder {


    /**
     * Encodes given <code>input</code>.
     *
     * @param input string to encode
     * @return encoding output
     * @throws IOException if an I/O error occurs.
     */
    public static byte[] encode(final String input) throws IOException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        return encode(input.getBytes("UTF-8"));
    }


    /**
     * Encodes given <code>input</code>.
     *
     * @param input bytes to encode
     * @return encoding output
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
     * Encodes bytes from <code>input</code> and write to <code>output</code>.
     *
     * @param input input
     * @param output output
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

        for (int b = -1; (b = input.read()) != -1;) {

            if ((b >= 0x30 && b <= 0x39) // digit
                || (b >= 0x41 && b <= 0x5A) // upper case alpha
                || (b >= 0x61 && b <= 0x7A) // lower case alpha
                || b == 0x2D // -
                || b == 0x5F // _
                || b == 0x2E // .
                || b == 0x7E) { // ~
                output.write(b);
            } else {
                output.write(0x25); // '%'
                output.write(itoa(b >> 4));
                output.write(itoa(b & 0xF));
            }
        }
    }


    /**
     * Integer to ASCII.
     *
     * @param integer integer
     * @return ASCII
     */
    static int itoa(final int integer) {

        if (integer < 0) {
            throw new IllegalArgumentException("negative integer");
        }

        if (integer > 0x0F) {
            throw new IllegalArgumentException(
                "too big integer: " + integer + " > 0x0F");
        }

        return integer + (integer < 0x0A ? 0x30 : 0x37);
    }


    /**
     * Creates a new instance.
     */
    protected PercentEncoder() {
        super();
    }
}

