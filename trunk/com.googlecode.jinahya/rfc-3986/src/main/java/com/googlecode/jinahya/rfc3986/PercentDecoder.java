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
import java.nio.charset.Charset;


/**
 * Percent Decoder.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/w4GhD">Percent Encoding</a>
 */
public class PercentDecoder {


    /**
     * Converts a single 7-bit ASCII value to a 4-bit unsigned integer.
     *
     * @param encoded 7-bit ASCII value; digit (0x30 ~ 0x39), upper alpha (0x41
     * ~ 0x46), or lower alpha (0x61 ~ 0x66)
     *
     * @return 4-bit unsigned integer (0x00 ~ 0x0F)
     */
    private static int decodeHalf(final int encoded) {

        switch (encoded) {
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
                return encoded - 0x30; // 0x00 - 0x09
            case 0x41: // 'A'
            case 0x42: // 'B'
            case 0x43: // 'C'
            case 0x44: // 'D'
            case 0x45: // 'E'
            case 0x46: // 'F'
                return encoded - 0x37; // 0x0A - 0x0F
            case 0x61: // 'a'
            case 0x62: // 'b'
            case 0x63: // 'c'
            case 0x64: // 'd'
            case 0x65: // 'e'
            case 0x66: // 'f'
                return encoded - 0x57; // 0x0A - 0x0F
            default:
                throw new IllegalArgumentException("illegal ascii: " + encoded);
        }
    }


    private static int decodeSingle(final byte[] encoded, final int offset) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (offset < 0) {
            throw new IllegalArgumentException("offset(" + offset + ") < 0");
        }

        if (offset >= encoded.length) {
            throw new IllegalArgumentException(
                "offset(" + offset + ") >= encoded.length(" + encoded.length
                + ")");
        }

        if ((encoded[offset] >= 0x30 && encoded[offset] <= 0x39)
            || (encoded[offset] >= 0x41 && encoded[offset] <= 0x5A)
            || (encoded[offset] >= 0x61 && encoded[offset] <= 0x7A)
            || encoded[offset] == 0x2D || encoded[offset] == 0x5F
            || encoded[offset] == 0x2E
            || encoded[offset] == 0x7E) {
            return encoded[offset];
        } else {
            if (encoded[offset] != 0x25) {
                throw new IllegalArgumentException(
                    "expected 0x25('%') yet " + encoded[offset]);
            }
            if (offset >= encoded.length - 2) {
                throw new IllegalArgumentException(
                    "offset(" + offset + ") >= encoded.length("
                    + encoded.length + ") - 2");
            }
            return (decodeHalf(encoded[offset + 1]) << 4)
                   | decodeHalf(encoded[offset + 2]);
        }
    }


    /**
     *
     * @param encoded
     *
     * @return
     */
    public static byte[] decodeMultiple(final byte[] encoded) {

        final byte[] decoded = new byte[encoded.length];
        int i = 0;

        for (int offset = 0; offset < encoded.length;) {
            decoded[i++] = (byte) decodeSingle(encoded, offset);
            offset += encoded[offset] == 0x25 ? 3 : 1;
        }

        if (i == decoded.length) {
            return decoded;
        }

        final byte[] trimmed = new byte[i];
        System.arraycopy(decoded, 0, trimmed, 0, i);

        return trimmed;
    }


    /**
     * Decodes given
     * <code>input</code>.
     *
     * @param input input to decode
     *
     * @return decoding output
     */
    public byte[] decode(final byte[] input) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        return decodeMultiple(input);
    }


    public String decodeToString(final byte[] input, final String outputCharset)
        throws UnsupportedEncodingException {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(decode(input), outputCharset);
    }


    public String decodeToString(final byte[] input,
                                 final Charset outputCharset) {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(decode(input), outputCharset);
    }


    public byte[] decode(final String input, final String inputCharset)
        throws UnsupportedEncodingException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (inputCharset == null) {
            throw new NullPointerException("inputCharset");
        }

        return decode(input.getBytes(inputCharset));
    }


    public byte[] decode(final String input, final Charset inputCharset) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (inputCharset == null) {
            throw new NullPointerException("inputCharset");
        }

        return decode(input.getBytes(inputCharset));
    }


    public String decodeToString(final String input, final String inputCharset,
                                 final String outputCharset)
        throws UnsupportedEncodingException {

        return new String(decode(input, inputCharset), outputCharset);
    }


    public String decodeToString(final String input, final Charset inputCharset,
                                 final Charset outputCharset) {

        return new String(decode(input, inputCharset), outputCharset);
    }


}
