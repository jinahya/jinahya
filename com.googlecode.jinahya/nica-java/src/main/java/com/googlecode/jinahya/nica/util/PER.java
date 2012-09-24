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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PER {


    /**
     * logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PER.class.getName());


    static {
        LOGGER.setLevel(Level.OFF);
    }


    public static byte[] decode(final byte[] encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        final ByteArrayOutputStream decoded =
            new ByteArrayOutputStream(encoded.length);

        for (int i = 0; i < encoded.length; i++) {
            if ((encoded[i] >= 0x30 && encoded[i] <= 0x39) // [0-9]
                || (encoded[i] >= 0x41 && encoded[i] <= 0x5A) // [a-z]
                || (encoded[i] >= 0x61 && encoded[i] <= 0x7A)
                || encoded[i] == 0x2D // '-'
                || encoded[i] == 0x5F // '_'
                || encoded[i] == 0x2E // '.'
                || encoded[i] == 0x7E) { // '~'                
                decoded.write(encoded[i]);
            } else if (encoded[i] == 0x25) {
                decoded.write(HEX.decodeSingle(encoded, ++i));
                ++i;
            } else {
                throw new IllegalArgumentException(
                    "illegal encoded: " + encoded[i]);
            }
        }

        return decoded.toByteArray();
    }


    public static byte[] decode(final String encoded) {

        LOGGER.log(Level.INFO, "decode({0})", encoded);

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        try {
            return decode(encoded.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?", uee);
//            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


    public static String decodeToString(final byte[] encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        try {
            return new String(decode(encoded), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?", uee);
//            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    public static String decodeToString(final String encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        try {
            return new String(decode(encoded), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?", uee);
//            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    /**
     * 
     * @param encoded
     * @return 
     * @deprecated 
     */
    public static byte[] decodeFromString(final String encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        try {
            return decode(encoded.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?", uee);
//            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


    public static byte[] encode(final byte[] decoded) {

        LOGGER.log(Level.INFO, "decoded.length: {0}", decoded.length);

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        final ByteArrayOutputStream encoded =
            new ByteArrayOutputStream(decoded.length * 3);

        final byte[] hex = new byte[2];
        for (int i = 0; i < decoded.length; i++) {
            if ((decoded[i] >= 0x30 && decoded[i] <= 0x39) // [0-9]
                || (decoded[i] >= 0x41 && decoded[i] <= 0x5A) // [a-z]
                || (decoded[i] >= 0x61 && decoded[i] <= 0x7A)
                || decoded[i] == 0x2D // '-'
                || decoded[i] == 0x5F // '_'
                || decoded[i] == 0x2E // '.'
                || decoded[i] == 0x7E) { // '~'
                encoded.write(decoded[i]);
            } else {
                encoded.write(0x25); // '%'
                HEX.encodeSingle(decoded[i] & 0xFF, hex, 0);
                encoded.write(hex[0]);
                encoded.write(hex[1]);
            }
        }

        return encoded.toByteArray();
    }


    public static byte[] encode(final String decoded) {

        LOGGER.log(Level.INFO, "encode({0})", decoded);

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        try {
            return encode(decoded.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" not supported?", uee);
//            throw new RuntimeException("\"UTF-8\" not supported?");
        }
    }


    public static String encodeToString(final byte[] decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        try {
            return new String(encode(decoded), "US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?", uee);
        }
    }


    public static String encodeToString(final String decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        try {
            return encodeToString(decoded.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?", uee);
//            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    protected PER() {
        super();
    }


}

