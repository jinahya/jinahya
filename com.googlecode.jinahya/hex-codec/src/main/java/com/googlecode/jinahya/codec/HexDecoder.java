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


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexDecoder {


    /**
     * Decodes a single nibble.
     *
     * @param input the nibble to decode.
     *
     * @return the decoded half octet.
     */
    private static int decodeHalf(final int input) {

        switch (input) {
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
                return input - 0x30;
            case 0x41: // 'A'
            case 0x42: // 'B'
            case 0x43: // 'C'
            case 0x44: // 'D'
            case 0x45: // 'E'
            case 0x46: // 'F'
                return input - 0x37;
            case 0x61: // 'a'
            case 0x62: // 'b'
            case 0x63: // 'c'
            case 0x64: // 'd'
            case 0x65: // 'e'
            case 0x66: // 'f'
                return input - 0x57;
            default:
                throw new IllegalArgumentException("illegal half: " + input);
        }
    }


    /**
     * Decodes two nibbles into a single octet.
     *
     * @param input the nibble array.
     * @param inputOffset the offset in the array.
     *
     * @return decoded octet.
     */
    public static int decodeSingle(final byte[] input, final int inputOffset) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (input.length < 2) {
            // not required
            throw new IllegalArgumentException(
                "input.length(" + input.length + ") < 2");
        }

        if (inputOffset < 0) {
            throw new IllegalArgumentException(
                "inputOffset(" + inputOffset + ") < 0");
        }

        if (inputOffset >= input.length - 1) {
            throw new IllegalArgumentException(
                "inputOffset(" + inputOffset + ") >= input.length("
                + input.length + ") - 1");
        }

        return (decodeHalf(input[inputOffset] & 0xFF) << 4)
               | decodeHalf(input[inputOffset + 1] & 0xFF);
    }


    public static void decodeSingle(final byte[] input, final int inputOffset,
                                    final byte[] output,
                                    final int outputOffset) {

        if (output == null) {
            throw new NullPointerException("output");
        }

        if (outputOffset < 0) {
            throw new IllegalArgumentException(
                "outputOffset(" + outputOffset + ") < 0");
        }

        if (outputOffset >= output.length) {
            throw new IllegalArgumentException(
                "outputOffset (" + outputOffset + ") >= output.length("
                + output.length + ")");
        }

        output[outputOffset] = (byte) decodeSingle(input, inputOffset);
    }


    public static void decodeMultiple(final byte[] input, int inputOffset,
                                      final byte[] output, int outputOffset,
                                      final int outputLength) {

        if (outputLength < 0) {
            throw new IllegalArgumentException(
                "inputLength(" + outputLength + ") < 0");
        }

        for (int i = 0; i < outputLength; i++) {
            decodeSingle(input, inputOffset, output, outputOffset);
            inputOffset += 2;
            outputOffset += 1;
        }
    }


    /**
     * Encodes given sequence of nibbles into a sequence of octets.
     *
     * @param input the nibbles to decode.
     *
     * @return the encoded octets.
     */
    public static byte[] decodeMultiple(final byte[] input) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final byte[] output = new byte[input.length >> 1]; // /2

        decodeMultiple(input, 0, output, 0, output.length);

        return output;
    }


    /**
     * Decodes given sequence of nibbles into a sequence of octets.
     *
     * @param input the nibbles to decode.
     *
     * @return the decoded octets.
     */
    public byte[] decode(final byte[] input) {

        return decodeMultiple(input);
    }


    public String decodeToString(final byte[] input, final String outputCharset)
        throws UnsupportedEncodingException {

        return new String(decode(input), outputCharset);
    }


    public String decodeToString(final byte[] input,
                                 final Charset outputCharset) {

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

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(decode(input, inputCharset), outputCharset);
    }


    public String decodeToString(final String input, final Charset inputCharset,
                                 final Charset outputCharset) {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(decode(input, inputCharset), outputCharset);
    }


    /**
     * [TESTING].
     *
     * @param encoded nibbles.
     *
     * @return octets.
     */
    byte[] decodeLikeAnEngineer(final byte[] encoded) {

//        if (encoded == null) {
//            throw new IllegalArgumentException("null encoded");
//        }

//        if ((encoded.length & 0x01) == 0x01) {
//            throw new IllegalArgumentException(
//                "encoded.length(" + encoded.length + ") is not even");
//        }

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
    byte[] decodeLikeABoss(byte[] encoded) {

        final byte[] decoded = new byte[encoded.length / 2];

        for (int i = 0; i < decoded.length; i++) {
            String s = new String(encoded, i * 2, 2, StandardCharsets.US_ASCII);
            decoded[i] = (byte) Integer.parseInt(s, 16);
        }

        return decoded;
    }


}
