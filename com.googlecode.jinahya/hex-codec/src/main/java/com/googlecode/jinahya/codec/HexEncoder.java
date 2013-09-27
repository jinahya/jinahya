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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexEncoder {


    /**
     * Encodes a single nibble.
     *
     * @param input the nibble to encode.
     *
     * @return the encoded half octet.
     */
    private static int encodeHalf(final int input) {

        switch (input) {
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
                return input + 0x30; // 0x30('0') ~ 0x39('9')
            case 0x0A:
            case 0x0B:
            case 0x0C:
            case 0x0D:
            case 0x0E:
            case 0x0F:
                return input + 0x57; // 0x61('a') ~ 0x66('f')
            default:
                throw new IllegalArgumentException("illegal half: " + input);
        }
    }


    /**
     * Encodes a single octet into two nibbles.
     *
     * @param input the octet to encode.
     * @param output the array to which each encoded nibbles are written.
     * @param outputOffset the offset in the output array.
     */
    public static void encodeSingle(final int input, final byte[] output,
                                    final int outputOffset) {

        if (output == null) {
            throw new NullPointerException("output");
        }

        if (outputOffset < 0) {
            throw new IllegalArgumentException(
                "outputOffset(" + outputOffset + ") < 0");
        }

        if (outputOffset >= output.length - 1) {
            throw new IllegalArgumentException(
                "outputOffset(" + outputOffset + ") >= output.length("
                + output.length + ") - 1");
        }

        output[outputOffset] = (byte) encodeHalf((input >> 4) & 0x0F);
        output[outputOffset + 1] = (byte) encodeHalf(input & 0x0F);
    }


    /**
     * Encodes a single octet into two nibbles.
     *
     * @param input the input byte array
     * @param inputOffset the offset in the input array
     * @param output the array to which each encoded nibbles are written.
     * @param outputOffset the offset in the output array.
     */
    public static void encodeSingle(final byte[] input, final int inputOffset,
                                    final byte[] output,
                                    final int outputOffset) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (inputOffset < 0) {
            throw new IllegalArgumentException(
                "inputOffset(" + inputOffset + ") < 0");
        }

        if (inputOffset >= input.length) {
            throw new IllegalArgumentException(
                "inputOffset(" + inputOffset + ") >= input.length("
                + input.length + ")");
        }

        encodeSingle(input[inputOffset], output, outputOffset);
    }


    public static byte[] encodeMultiple(
        final byte[] input, int inputOffset, final int inputLength,
        final byte[] output, int outputOffset) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (inputOffset < 0) {
            throw new IllegalArgumentException(
                "inputOffset(" + inputOffset + ") < 0");
        }

        if (inputLength < 0) {
            throw new IllegalArgumentException(
                "inputLength(" + inputLength + ") < 0");
        }

        if (inputOffset + inputLength > input.length) {
            throw new IllegalArgumentException(
                "inputOffset(" + inputOffset + ") + inputLength(" + inputLength
                + ") >= input.length(" + input.length + ")");
        }

        if (output == null) {
            throw new NullPointerException("output");
        }

        if (outputOffset < 0) {
            throw new IllegalArgumentException(
                "outputOffset(" + outputOffset + ") < 0");
        }

        final byte[] encoded = new byte[input.length << 1]; // * 2

        for (int i = 0; i < inputLength; i++) {
            encodeSingle(input, inputOffset, encoded, outputOffset);
            inputOffset += 1;
            outputOffset += 2;
        }

        return encoded;
    }


    /**
     * Encodes given sequence of octets into a sequence of nibbles.
     *
     * @param input the octets to encode
     *
     * @return the encoded nibbles.
     */
    public static byte[] encodeMultiple(final byte[] input) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final byte[] output = new byte[input.length << 1]; // * 2

        encodeMultiple(input, 0, input.length, output, 0);

        return output;
    }


    /**
     * Encodes given sequence of octets into a sequence of nibbles.
     *
     * @param input the octets to encode.
     *
     * @return the encoded nibbles.
     */
    public byte[] encode(final byte[] input) {

        if (input == null) {
            throw new NullPointerException("decoded");
        }

        return encodeMultiple(input);
    }


    public String encodedToString(final byte[] input,
                                  final String outputCharset)
        throws UnsupportedEncodingException {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(encode(input), outputCharset);
    }


    public String encodedToString(final byte[] input,
                                  final Charset outputCharset) {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(encode(input), outputCharset);
    }


    public byte[] encode(final String input, final String inputCharset)
        throws UnsupportedEncodingException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (inputCharset == null) {
            throw new NullPointerException("inputCharset");
        }

        return encode(input.getBytes(inputCharset));
    }


    public byte[] encode(final String input, final Charset inputCharset) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (inputCharset == null) {
            throw new NullPointerException("inputCharset");
        }

        return encode(input.getBytes(inputCharset));
    }


    public String encodeToString(final String input, final String inputCharset,
                                 final String outputCharset)
        throws UnsupportedEncodingException {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(encode(input, inputCharset), outputCharset);
    }


    public String encodeToString(final String input, final Charset inputCharset,
                                 final Charset outputCharset) {

        if (outputCharset == null) {
            throw new NullPointerException("outputCharset");
        }

        return new String(encode(input, inputCharset), outputCharset);
    }


    /**
     * [TESTING].
     *
     * @param decoded octets.
     *
     * @return nibbles.
     */
    byte[] encodeLikeAnEngineer(final byte[] decoded) {

        final byte[] encoded = new byte[decoded.length << 1];

        int index = 0; // index in encoded
        for (int i = 0; i < decoded.length; i++) {
            encoded[index++] = (byte) encodeHalf((decoded[i] & 0xF0) >> 4);
            encoded[index++] = (byte) encodeHalf((decoded[i] & 0x0F));
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
    byte[] encodeLikeABoss(final byte[] decoded) {

        final byte[] encoded = new byte[decoded.length * 2];

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
