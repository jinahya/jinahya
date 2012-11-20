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
import java.io.UnsupportedEncodingException;


/**
 * Percent Encoder.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/w4GhD">Percent Encoding</a>
 */
public class PercentEncoder {


    protected static int encodeSingle(final int decoded, final byte[] encoded, final int offset) {

        int half;
        if ((decoded >= 0x30 && decoded <= 0x39) // digit
            || (decoded >= 0x41 && decoded <= 0x5A) // upper case alpha
            || (decoded >= 0x61 && decoded <= 0x7A) // lower case alpha
            || decoded == 0x2D || decoded == 0x5F || decoded == 0x2E
            || decoded == 0x7E) { // -_.~
            encoded[offset] = (byte) decoded;
            return 1;
        } else {
            encoded[offset] = 0x25;
            encoded[offset + 1] = (byte) ((half = decoded >> 4) < 0x0A
                                          ? half + 0x30 : half + 0x37);
            encoded[offset + 2] = (byte) ((half = decoded & 0x0F) < 0x0A
                                          ? half + 0x30 : half + 0x37);
            return 3;
        }
    }


    public static void encode(final InputStream input,
                              final OutputStream output)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        if (output == null) {
            throw new IllegalArgumentException("null output");
        }

        int h;
        for (int b = -1; (b = input.read()) != -1;) {
            if ((b >= 0x30 && b <= 0x39) // digit
                || (b >= 0x41 && b <= 0x5A) // upper case alpha
                || (b >= 0x61 && b <= 0x7A) // lower case alpha
                || b == 0x2D || b == 0x5F || b == 0x2E || b == 0x7E) { // -_.~
                output.write(b);
            } else {
                output.write(0x25); // '%'
                output.write((h = b >> 4) < 0x0A ? h + 0x30 : h + 0x37);
                output.write((h = b & 0x0F) < 0x0A ? h + 0x30 : h + 0x37);
            }
        }
    }


    /**
     * Creates a new instance.
     */
    public PercentEncoder() {
        super();
    }


    /**
     *
     * @param decoded
     * @return
     */
    public byte[] encode(final byte[] decoded) {

        if (decoded == null) {
            throw new NullPointerException("null decoded");
        }

        final byte[] encoded = new byte[decoded.length * 3];

        int offset = 0;
        for (int i = 0; i < decoded.length; i++) {
            offset = encodeSingle(decoded[i] & 0xFF, encoded, offset);
        }

        return encoded;
    }


    /**
     * Encodes given
     * <code>input</code>.
     *
     * @param decoded string to encode
     *
     * @return encoding output
     */
    public byte[] encode(final String decoded) {

        if (decoded == null) {
            throw new NullPointerException("null decoded");
        }

        try {
            return encode(decoded.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("'UTF-8' is not supported?");
        }
    }


}

