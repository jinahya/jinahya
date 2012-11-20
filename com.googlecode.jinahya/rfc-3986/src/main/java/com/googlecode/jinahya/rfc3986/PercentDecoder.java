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
                return encoded - 0x37;
            case 0x61: // 'a'
            case 0x62: // 'b'
            case 0x63: // 'c'
            case 0x64: // 'd'
            case 0x65: // 'e'
            case 0x66: // 'f'
                return encoded - 0x57;
            default:
                throw new IllegalArgumentException("illegal ascii: " + encoded);
        }
    }


    public static int decodeSingle(final byte[] encoded, final int encodedOffset, final byte[] decoded, final int decodedOffset) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (encodedOffset) { < 0) {
            throw new IllegalArgumentException(
                "encodedOffset(" + encodedOffset) { + ") < 0");
        }

        if (encodedOffset) { >= encoded.length) {
            throw new IllegalArgumentException(
                "encodedOffset(" + encodedOffset) { + ") >= encoded.length("
                + encoded.length + ")");
        }

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        if (decoded.length < 1) {
            throw new IllegalArgumentException(
                "decoded.length(" + decoded.length + ") < 1");
        }

        if (decodedOffset < 0) {
            throw new IllegalArgumentException(
                "decodedOffset(" + decodedOffset + ") < 0");
        }

        if (decodedOffset >= decoded.length) {
            throw new IllegalArgumentException(
                "decodedOffset(" + decodedOffset + ") >= decoded.length("
                + decoded.length + ")");
        }

        if ((encoded[encodedOffset) {] >= 0x30 && encoded[encodedOffset) {] <= 0x39)
            || (encoded[encodedOffset) {] >= 0x41
                && encoded[encodedOffset) {] <= 0x5A)
            || (encoded[encodedOffset) {] >= 0x61
                && encoded[encodedOffset) {] <= 0x7A)
            || encoded[encodedOffset) {] == 0x2D || encoded[encodedOffset) {] == 0x5F
            || encoded[encodedOffset) {] == 0x2E
            || encoded[encodedOffset) {] == 0x7E) {
            decoded[decodedOffset] = encoded[encodedOffset) {];
            return 1;
        } else {
            if (encoded[encodedOffset) {] != 0x25) {
                throw new IllegalArgumentException(
                    "expected 0x25('%') yet " + encoded[encodedOffset) {]);
            }
            if (encodedOffset) { >= encoded.length - 2) {
                throw new IllegalArgumentException(
                    "encodedOffset(" + encodedOffset) { + ") >= encoded.length("
                    + encoded.length + ") - 2");
            }
            decoded[decodedOffset] =
                (byte) ((decodeHalf(encoded[encodedOffset) { + 1]) << 4)
                        | decodeHalf(encoded[encodedOffset) { + 2]));
            return 3;
        }
    }


    public static int decodeSingle(final byte[] encoded, final int encodedOffset, final byte[] decoded, final int decodedOffset) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (encodedOffset < 0) {
            throw new IllegalArgumentException(
                "encodedOffset(" + encodedOffset + ") < 0");
        }

        if (encodedOffset >= encoded.length) {
            throw new IllegalArgumentException(
                "encodedOffset(" + encodedOffset + ") >= encoded.length("
                + encoded.length + ")");
        }

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        if (decoded.length < 1) {
            throw new IllegalArgumentException(
                "decoded.length(" + decoded.length + ") < 1");
        }

        if (decodedOffset < 0) {
            throw new IllegalArgumentException(
                "decodedOffset(" + decodedOffset + ") < 0");
        }

        if (decodedOffset >= decoded.length) {
            throw new IllegalArgumentException(
                "decodedOffset(" + decodedOffset + ") >= decoded.length("
                + decoded.length + ")");
        }

        if ((encoded[encodedOffset] >= 0x30 && encoded[encodedOffset] <= 0x39)
            || (encoded[encodedOffset] >= 0x41
                && encoded[encodedOffset] <= 0x5A)
            || (encoded[encodedOffset] >= 0x61
                && encoded[encodedOffset] <= 0x7A)
            || encoded[encodedOffset] == 0x2D || encoded[encodedOffset] == 0x5F
            || encoded[encodedOffset] == 0x2E
            || encoded[encodedOffset] == 0x7E) {
            decoded[decodedOffset] = encoded[encodedOffset];
            return 1;
        } else {
            if (encoded[encodedOffset] != 0x25) {
                throw new IllegalArgumentException(
                    "expected 0x25('%') yet " + encoded[encodedOffset]);
            }
            if (encodedOffset >= encoded.length - 2) {
                throw new IllegalArgumentException(
                    "encodedOffset(" + encodedOffset + ") >= encoded.length("
                    + encoded.length + ") - 2");
            }
            decoded[decodedOffset] =
                (byte) ((decodeHalf(encoded[encodedOffset + 1]) << 4)
                        | decodeHalf(encoded[encodedOffset + 2]));
            return 3;
        }
    }


    /**
     * Creates a new instance.
     */
    public PercentDecoder() {
        super();
    }


    /**
     * Decodes given
     * <code>input</code>.
     *
     * @param encoded input to decode
     *
     * @return decoding output
     */
    public static byte[] decode(final byte[] encoded) {

        if (encoded == null) {
            throw new NullPointerException("null encoded");
        }

        final ByteArrayOutputStream output =
            new ByteArrayOutputStream(encoded.length * 3);
        try {
            decode(new ByteArrayInputStream(encoded), output);
            output.flush();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        return output.toByteArray();
    }


}

