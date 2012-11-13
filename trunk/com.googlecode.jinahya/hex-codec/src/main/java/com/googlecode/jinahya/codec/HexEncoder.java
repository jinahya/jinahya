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
     * Encodes a half portion of an octet.
     *
     * @param decoded the octet to encode; must be between 0x0 (inclusive) and
     * 0xF (inclusive)
     *
     * @return the encoded ASCII character.
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
     * Encodes given
     * <code>decoded</code> and writes those 2 characters onto
     * <code>encoded</code>[
     * <code>offset</code>] and
     * <code>encoded</code>[
     * <code>offset</code> + 1].
     *
     * @param decoded the octet whose lower 8 bits are encoded as 2 ASCII
     * characters.
     * @param encoded the byte array to which encoded characters are written.
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
     * Decodes given
     * <code>decoded</code>.
     *
     * @param decoded the bytes to encode
     *
     * @return the decoded bytes.
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
     * Encodes given byte array and returns result as a byte array.
     *
     * @param decoded the bytes to encode.
     *
     * @return the encoded output.
     */
    public byte[] encode(final byte[] decoded) {

        return encodeMultiple(decoded);
    }


}

