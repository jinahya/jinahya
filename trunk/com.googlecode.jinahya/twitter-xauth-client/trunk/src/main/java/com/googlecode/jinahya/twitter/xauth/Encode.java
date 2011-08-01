/*
 * Copyright 2011 Jin Kwon.
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


package com.googlecode.jinahya.twitter.xauth;


import java.io.UnsupportedEncodingException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://tools.ietf.org/html/rfc3986">RFC 3986</a>
 * @see <a href="http://tools.ietf.org/html/rfc4648">RFC 4648</a>
 */
public class Encode {


    /**
     *
     * @param input
     * @return
     * @throws UnsupportedEncodingException
     * @see <a href="http://tools.ietf.org/html/rfc3986#section-2.1">
     *      Percent-Encoding (RFC 3986)</a>
     */
    public static String percent(final String input)
        throws UnsupportedEncodingException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        return percent(input.getBytes("UTF-8"));
    }


    public static String percent(final byte[] input)
        throws UnsupportedEncodingException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        final StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < input.length; i++) {
            final char ch = (char) (input[i] & 0xFF);
            if ((ch >= 0x30 && ch <= 0x39)
                || (ch >= 0x41 && ch <= 0x5A)
                || (ch >= 0x61 && ch <= 0x7A)
                || ch == 0x2D || ch == 0x2E || ch == 0x5F || ch == 0x7E) {
                buffer.append(ch);
            } else {
                buffer.append('%');
                if (ch <= 0x0F) {
                    buffer.append('0');
                }
                buffer.append(Integer.toHexString(ch).toUpperCase());
            }
        }

        return buffer.toString();
    }


    /**
     *
     * @param input
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String url(final String input)
        throws UnsupportedEncodingException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        return url(input.getBytes("UTF-8"));
    }


    /**
     * 
     * @param input
     * @return
     */
    public static String url(final byte[] input) {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < input.length; i++) {
            final char ch = (char) (input[i] & 0xFF);
            if ((ch >= 0x30 && ch <= 0x39)
                || (ch >= 0x41 && ch <= 0x5A)
                || (ch >= 0x61 && ch <= 0x7A)
                || ch == 0x2D || ch == 0x2E || ch == 0x5F || ch == 0x7E) {
                buffer.append(ch);
            } else if (ch == 0x20) {
                buffer.append('+');
            } else {
                buffer.append('%');
                if (ch <= 0x0F) {
                    buffer.append('0');
                }
                buffer.append(Integer.toHexString(ch).toUpperCase());
            }
        }

        return buffer.toString();
    }


    /**
     * The Base 16 Alphabet.
     *
     * '0', '1', '2', '3', '4', '5', '6', '7',
     * '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
     */
    private static final byte[] BASE16_ALPHABET = {
        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37,
        0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46
    };


    /**
     * Encodes given <code>input</code> into a Base16(hex) string.
     *
     * @param input octet input
     * @return a base 16 encoded string
     * @see <a href="http://tools.ietf.org/html/rfc4648#section-8">Base 16
     *      Encoding (RFC 4648)</a>
     */
    public static String base16(final byte[] input) {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        final byte[] output = new byte[input.length * 2];

        int octet;
        for (int i = 0; i < input.length; i++) {
            octet = input[i] & 0xFF;
            output[i * 2 + 1] = BASE16_ALPHABET[octet & 0xF];
            output[i * 2] = BASE16_ALPHABET[octet >> 4];
        }

        return new String(output);
    }


    /**
     * The Base 64 Alphabet.
     *
     * 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
     * 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
     * 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
     * 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
     * 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
     * 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
     * 'w', 'x', 'y', 'z', '0', '1', '2', '3',
     * '4', '5', '6', '7', '8', '9', '+', '/'
     */
    private static final byte[] BASE64_ALPHABET = {
        0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48,
        0x49, 0x4A, 0x4B, 0x4C, 0x4D, 0x4E, 0x4F, 0x50,
        0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58,
        0x59, 0x5A, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66,
        0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E,
        0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76,
        0x77, 0x78, 0x79, 0x7A, 0x30, 0x31, 0x32, 0x33,
        0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x2B, 0x2F
    };


    /**
     * Encodes given byte array into a BASE64 string.
     *
     * @param input octet input
     * @return a BASE64 string
     * @see <a href="http://tools.ietf.org/html/rfc4648#section-4">Base 64
     *      Encoding (RFC 4648)</a>
     */
    public static String base64(final byte[] input) {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        int count = input.length / 3;
        if (input.length % 3 > 0) {
            count++;
        }

        final byte[] output = new byte[count * 4];
        int index = 0;

        int pad = 0;
        int word;
        for (int i = 0; i < input.length; i += 3) {
            word = 0x00;
            for (int j = 0; j < 3; j++) {
                if ((i + j) < input.length) {
                    word <<= 8;
                    word |= (input[i + j] & 0xFF);
                } else {
                    switch (j) {
                        case 1:
                            pad = 2;
                            word <<= 4;
                            break;
                        case 2:
                            pad = 1;
                            word <<= 2;
                            break;
                        default:
                            break;
                    }
                    break;
                }
            }
            for (int j = 0; j < pad; j++) {
                output[index + 3 - j] = '=';
            }
            for (int j = 3 - pad; j >= 0; j--) {
                output[index + j] = BASE64_ALPHABET[word & 0x3F];
                word >>= 6;
            }
            if (pad != 0) {
                break;
            }
            index += 4;
        }

        return new String(output);
    }


    private Encode() {
        super();
    }
}
