/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.codec;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexEncoder {


    /**
     * Encodes a single nibble.
     *
     * @param decoded the nibble to encode.
     *
     * @return the encoded half octet.
     */
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
                return decoded + 0x57; // 0x61('a') - 0x66('f')
            default:
                throw new IllegalArgumentException("illegal half: " + decoded);
        }
    }


    /**
     * Encodes a single octet into two nibbles.
     *
     * @param decoded the octet to encode.
     * @param encoded the array to which each encoded nibbles are written.
     * @param offset the offset in the array.
     */
    protected static void encodeSingle(final int decoded, final byte[] encoded,
                                       final int offset) {

        if (encoded == null) {
            throw new NullPointerException("null encoded");
        }

        if (encoded.length < 2) {
            // not required
            throw new IllegalArgumentException(
                "encoded.length(" + encoded.length + ") < 2");
        }

        if (offset < 0) {
            throw new IllegalArgumentException("offset(" + offset + ") < 0");
        }

        if (offset >= encoded.length - 1) {
            throw new IllegalArgumentException(
                "offset(" + offset + ") >= encoded.length(" + encoded.length
                + ") - 1");
        }

        encoded[offset] = (byte) encodeHalf((decoded >> 4) & 0x0F);
        encoded[offset + 1] = (byte) encodeHalf(decoded & 0x0F);
    }


    /**
     * Decodes given sequence of octets into a sequence of nibbles.
     *
     * @param decoded the octets to encode
     *
     * @return the encoded nibbles.
     */
    protected static byte[] encodeMultiple(final byte[] decoded) {

        if (decoded == null) {
            throw new NullPointerException("null decoded");
        }

        final byte[] encoded = new byte[decoded.length << 1];

        int offset = 0;
        for (int i = 0; i < decoded.length; i++) {
            encodeSingle(decoded[i], encoded, offset);
            offset += 2;
        }

        return encoded;
    }


    /**
     * Creates a new instance.
     */
    public HexEncoder() {
        super();
    }


    /**
     * Encodes given sequence of octets into a sequence of nibbles.
     *
     * @param decoded the octets to encode.
     *
     * @return the encoded nibbles.
     */
    public byte[] encode(final byte[] decoded) {

        return encodeMultiple(decoded);
    }


    /**
     * [TESTING].
     *
     * @param decoded octets.
     *
     * @return nibbles.
     */
    public byte[] encodeLikeAnEngineer(final byte[] decoded) {

        if (decoded == null) {
            throw new NullPointerException("null decoded");
        }

        final byte[] encoded = new byte[decoded.length << 1];

        int index = 0; // index in encoded
        for (int i = 0; i < decoded.length; i++) {
            encoded[index++] = (byte) encodeHalf((decoded[i] & 0xF0) >> 4);
            encoded[index++] = (byte) encodeHalf(decoded[i] & 0x0F);
        }

        return encoded;
    }


    /**
     * [TESTING].
     *
     * @param decoded octets.
     *
     * @return nibbles.
     */
    public byte[] encodeLikeABoss(byte[] decoded) {

        byte[] encoded = new byte[decoded.length * 2];

        for (int i = 0; i < decoded.length; i++) {
            String s = Integer.toString(decoded[i] & 0xFF, 16);
            if (s.length() == 1) {
                s = "0" + s;
            }
            encoded[i * 2] = (byte) s.charAt(0);
            encoded[i * 2 + 1] = (byte) s.charAt(1);
        }

        return encoded;
    }


}

