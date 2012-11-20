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


import java.nio.charset.StandardCharsets;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexDecoder {


    /**
     * Decodes a single nibble.
     *
     * @param encoded the nibble to decode.
     *
     * @return the decoded half octet.
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
                return encoded - 0x30;
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
                throw new IllegalArgumentException("illegal half: " + encoded);
        }
    }


    /**
     * Decodes two nibbles into a single octet.
     *
     * @param encoded the nibble array.
     * @param offset the offset in the array.
     *
     * @return decoded octet.
     */
    protected static int decodeSingle(final byte[] encoded, final int offset) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
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

        return (decodeHalf(encoded[offset]) << 4)
               | decodeHalf(encoded[offset + 1]);
    }


    /**
     * Encodes given sequence of nibbles into a sequence of octets.
     *
     * @param encoded the nibbles to decode.
     *
     * @return the encoded octets.
     */
    protected static byte[] decodeMultiple(final byte[] encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if ((encoded.length & 0x01) == 0x01) {
            throw new IllegalArgumentException(
                "encoded.length(" + encoded.length + ") is not even");
        }

        final byte[] decoded = new byte[encoded.length >> 1];

        int offset = 0;
        for (int i = 0; i < decoded.length; i++) {
            decoded[i] = (byte) decodeSingle(encoded, offset);
            offset += 2;
        }

        return decoded;
    }


    /**
     * Decodes given sequence of nibbles into a sequence of octets.
     *
     * @param encoded the nibbles to decode.
     *
     * @return the decoded octets.
     */
    public byte[] decode(final byte[] encoded) {

        return decodeMultiple(encoded);
    }


    /**
     * [TESTING].
     *
     * @param encoded nibbles.
     *
     * @return octets.
     */
    public byte[] decodeLikeAnEngineer(final byte[] encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if ((encoded.length & 0x01) == 0x01) {
            throw new IllegalArgumentException(
                "encoded.length(" + encoded.length + ") is not even");
        }

        final byte[] decoded = new byte[encoded.length >> 1];

        int index = 0; // index in encoded
        for (int i = 0; i < decoded.length; i++) {
            decoded[i] = (byte) ((decodeHalf(encoded[index++]) << 4)
                                 | decodeHalf(encoded[index++]));
        }

        return decoded;
    }


    /**
     * [TESTING].
     *
     * @param encoded nibbles.
     *
     * @return octets.
     */
    public byte[] decodeLikeABoss(byte[] encoded) {

        byte[] decoded = new byte[encoded.length / 2];

        for (int i = 0; i < decoded.length; i++) {
            String s = new String(encoded, i * 2, 2, StandardCharsets.US_ASCII);
            decoded[i] = (byte) Integer.parseInt(s, 16);
        }

        return decoded;
    }


}

