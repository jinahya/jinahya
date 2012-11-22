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


import java.io.UnsupportedEncodingException;


/**
 * Percent Encoder.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/w4GhD">Percent Encoding</a>
 */
public class PercentEncoder {


    private static int encodeHalf(final int decoded) {

        switch (decoded) {
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
                return decoded + 0x30; // 0x30('0') - 0x39('9')
            case 0x0A:
            case 0x0B:
            case 0x0C:
            case 0x0D:
            case 0x0E:
            case 0x0F:
                return decoded + 0x37; // 0x41('A') - 0x46('F')
            default:
                throw new IllegalArgumentException("illegal half: " + decoded);
        }
    }


    protected static void encodeSingle(final int decoded, final byte[] encoded,
                                       final int offset) {

        if (decoded >> 8 != 0) {
            throw new IllegalArgumentException(
                "decoded(" + decoded + ") >> 8 != 0");
        }

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (encoded.length < 1) {
            throw new IllegalArgumentException(
                "encoded.length(" + encoded.length + ") < 1");
        }

        if (offset < 0) {
            throw new IllegalArgumentException("offset(" + offset + ") < 0");
        }

        if (offset >= encoded.length) {
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
        } else {
            if (offset >= encoded.length - 2) {
                throw new IllegalArgumentException(
                    "offset(" + offset + ") >= encoded.length("
                    + encoded.length + ") - 2");
            }
            encoded[offset] = 0x25; // '%'
            encoded[offset + 1] = (byte) encodeHalf(decoded >> 4);
            encoded[offset + 2] = (byte) encodeHalf(decoded & 0x0F);
        }
    }


    public static byte[] encodeMultiple(final byte[] decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        final byte[] encoded = new byte[(decoded.length << 2) + decoded.length];

        int offset = 0;
        for (int i = 0; i < decoded.length; i++) {
            encodeSingle(decoded[i] & 0xFF, encoded, offset);
            offset += encoded[offset] == 0x25 ? 3 : 1;
        }

        if (offset == encoded.length) {
            return encoded;
        }

        final byte[] trimmed = new byte[offset];
        System.arraycopy(encoded, 0, trimmed, 0, offset);

        return trimmed;
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

        return encodeMultiple(decoded);
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

