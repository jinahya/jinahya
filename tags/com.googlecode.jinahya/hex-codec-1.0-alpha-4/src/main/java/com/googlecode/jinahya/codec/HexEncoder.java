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
    public static int encodeHalf(final int input) {

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
                return input + 0x37; // 0x41('A') ~ 0x46('F')
            default:
                throw new IllegalArgumentException("illegal half: " + input);
        }
    }


    /**
     * Encodes a single octet into two nibbles.
     *
     * @param input the octet to encode.
     * @param output the array to which each encoded nibbles are written.
     * @param outoff the offset in the output array.
     */
    public static void encodeSingle(final int input, final byte[] output,
                                    final int outoff) {

        if (output == null) {
            throw new NullPointerException("output");
        }

        if (outoff < 0) {
            throw new IllegalArgumentException("outoff(" + outoff + ") < 0");
        }

        if (outoff >= output.length - 1) {
            throw new IllegalArgumentException(
                "outoff(" + outoff + ") >= output.length(" + output.length
                + ") - 1");
        }

        output[outoff] = (byte) encodeHalf((input >> 4) & 0x0F);
        output[outoff + 1] = (byte) encodeHalf(input & 0x0F);
    }


    /**
     * Encodes a single octet into two nibbles.
     *
     * @param input the input byte array
     * @param inoff the offset in the input array
     * @param output the array to which each encoded nibbles are written.
     * @param outoff the offset in the output array.
     */
    public static void encodeSingle(final byte[] input, final int inoff,
                                    final byte[] output, final int outoff) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (inoff < 0) {
            throw new IllegalArgumentException("inoff(" + inoff + ") < 0");
        }

        if (inoff >= input.length) {
            throw new IllegalArgumentException(
                "inoff(" + inoff + ") >= input.length(" + input.length + ")");
        }

        encodeSingle(input[inoff], output, outoff);
    }


    public static void encodeMultiple(final byte[] input, int inoff,
                                      final byte[] output, int outoff,
                                      final int count) {

        if (count < 0) {
            throw new IllegalArgumentException("count(" + count + ") < 0");
        }

        for (int i = 0; i < count; i++) {
            encodeSingle(input, inoff, output, outoff);
            inoff += 1;
            outoff += 2;
        }
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

        encodeMultiple(input, 0, output, 0, input.length);

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


    /**
     * Encodes given string.
     *
     * @param input the input string.
     * @param inputCharset the charset to decode input string
     * @param outputCharset the charset to encode output string.
     *
     * @return the encoded string.
     */
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
     * @param input octets.
     *
     * @return nibbles.
     */
    byte[] encodeLikeAnEngineer(final byte[] input) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final byte[] output = new byte[input.length << 1];

        int index = 0; // index in output
        for (int i = 0; i < input.length; i++) {
            output[index++] = (byte) encodeHalf((input[i] >> 4) & 0x0F);
            output[index++] = (byte) encodeHalf((input[i] & 0x0F));
        }

        return output;
    }


    /**
     * [TESTING].
     *
     * @param input octets.
     *
     * @return nibbles.
     */
    byte[] encodeLikeABoss(final byte[] input) {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final byte[] output = new byte[input.length << 1];

        int index = 0; // index in output
        for (int i = 0; i < input.length; i++) {
            String s = Integer.toString(input[i] & 0xFF, 16);
            if (s.length() == 1) {
                s = "0" + s;
            }
            output[index++] = (byte) s.charAt(0);
            output[index++] = (byte) s.charAt(1);
        }

        return output;
    }


}
