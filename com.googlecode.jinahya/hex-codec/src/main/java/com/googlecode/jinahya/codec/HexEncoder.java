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
    protected static int encodeHalf(final int decoded) {

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


    /**
     * Encodes a single octet into two nibbles.
     *
     * @param decoded the octet to encode.
     * @param encoded the array to which each encoded nibbles are written.
     * @param offset the offset in the array.
     */
    protected static void encodeSingle(final int decoded, final byte[] encoded,
                                       final int offset) {

//        if (decoded < 0x00) {
//            throw new IllegalArgumentException(
//                "decoded(" + decoded + ") < 0x00");
//        }
//
//        if (decoded > 0xFF) {
//            throw new IllegalArgumentException(
//                "decoded(" + decoded + ") > 0xFF");
//        }

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
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
            throw new IllegalArgumentException("null decoded");
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
     * Encodes given sequence of octets into a sequence of nibbles.
     *
     * @param decoded the octets to encode.
     *
     * @return the encoded nibbles.
     */
    public byte[] encodeLikeABoss(byte[] decoded) {

        byte[] encoded = new byte[decoded.length * 2];

        int i = 0;
        for (byte b : decoded) {
            String s = Integer.toHexString(b & 0xFF).toUpperCase();
            if (s.length() == 1) {
                s = "0" + s;
            }
            encoded[i++] = (byte) s.charAt(0);
            encoded[i++] = (byte) s.charAt(1);
        }

        return encoded;
    }


}

