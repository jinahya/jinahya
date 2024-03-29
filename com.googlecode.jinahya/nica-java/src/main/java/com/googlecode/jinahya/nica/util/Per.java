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


package com.googlecode.jinahya.nica.util;


import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;


/**
 * A percent encoder.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Per {


    /**
     * Encodes given {@code decoded}.
     *
     * @param decoded input bytes to encode.
     *
     * @return encoded output.
     */
    public static byte[] encode(final byte[] decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        final ByteArrayOutputStream encoded =
            new ByteArrayOutputStream(decoded.length * 3); // possible maximum

        final byte[] hex = new byte[2];
        for (int i = 0; i < decoded.length; i++) {
            if ((decoded[i] >= 0x30 && decoded[i] <= 0x39) //    [0-9]
                || (decoded[i] >= 0x41 && decoded[i] <= 0x5A) // [a-z]
                || (decoded[i] >= 0x61 && decoded[i] <= 0x7A) // [A-Z]
                || decoded[i] == 0x2D //                         '-'
                || decoded[i] == 0x5F //                         '_'
                || decoded[i] == 0x2E //                         '.'
                || decoded[i] == 0x7E) { //                      '~'
                encoded.write(decoded[i]);
            } else {
                encoded.write(0x25); //                          '%'
                Hex.encodeSingle(decoded[i] & 0xFF, hex, 0);
                encoded.write(hex[0]);
                encoded.write(hex[1]);
            }
        }

        return encoded.toByteArray();
    }


    /**
     * Encodes given {@code decoded} which is treated as a {@code UTF-8} encoded
     * string.
     *
     * @param decoded the string to encode.
     *
     * @return encoded output
     */
    public static byte[] encode(final String decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        try {
            return encode(decoded.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    /**
     * Encodes given {@code decoded} and returns the result as a
     * {@code US-ASCII} encoded string.
     *
     * @param decoded the bytes to encode
     *
     * @return encoded output as a {@code US-ASCII} encoded string
     */
    public static String encodeToString(final byte[] decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        try {
            return new String(encode(decoded), "US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


    /**
     * Encodes given {@code decoded} which is treated as a {@code UTF-8} encoded
     * string and returns the result as a {@code US-ASCII} encoded string.
     *
     * @param decoded the string to encode.
     *
     * @return encoded output as a {@code US-ASCII} encoded string
     */
    public static String encodeToString(final String decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        try {
            return encodeToString(decoded.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    /**
     * Decodes given {@code encoded}.
     *
     * @param encoded the bytes to encode
     *
     * @return decoded output
     */
    public static byte[] decode(final byte[] encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        final ByteArrayOutputStream decoded =
            new ByteArrayOutputStream(encoded.length); // possible maximum

        for (int i = 0; i < encoded.length; i++) {
            if ((encoded[i] >= 0x30 && encoded[i] <= 0x39) //    [0-9]
                || (encoded[i] >= 0x41 && encoded[i] <= 0x5A) // [a-z]
                || (encoded[i] >= 0x61 && encoded[i] <= 0x7A) // [A-Z]
                || encoded[i] == 0x2D //                         '-' hyphen
                || encoded[i] == 0x5F //                         '_' underscore
                || encoded[i] == 0x2E //                         '.' period
                || encoded[i] == 0x7E) { //                      '~' tilde
                decoded.write(encoded[i]);
            } else if (encoded[i] == 0x25) { // '%'
                if ((i + 2) >= encoded.length) {
                    throw new IllegalArgumentException(
                        "illegal encoded: " + encoded[i]);
                }
                decoded.write(Hex.decodeSingle(encoded, ++i));
                ++i;
            } else {
                throw new IllegalArgumentException(
                    "illegal encoded: " + encoded[i]);
            }
        }

        return decoded.toByteArray();
    }


    /**
     * Decodes given {@code encoded} which is treated as a {@code US-ASCII}
     * encoded string.
     *
     * @param encoded the string to decode.
     *
     * @return decoded output.
     */
    public static byte[] decode(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("null encoded");
        }

        try {
            return decode(encoded.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


    /**
     * Decodes given {@code encoded} and returns the result as a {@code UTF-8}
     * encoded string.
     *
     * @param encoded the bytes to decode.
     *
     * @return decoded output as a {@code UTF-8} encoded string.
     */
    public static String decodeToString(final byte[] encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        try {
            return new String(decode(encoded), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    /**
     * Decodes given {@code encoded} which is treated as a {@code US-ASCII}
     * encoded string and returns the result as a {@code UTF-8} encoded string.
     *
     * @param encoded the string to decode.
     *
     * @return decoded output as a {@code UTF-8} encoded string.
     */
    public static String decodeToString(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        try {
            return decodeToString(encoded.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


    /**
     * Creates a new instance.
     */
    protected Per() {
        super();
    }


}
