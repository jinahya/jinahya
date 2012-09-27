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


package com.googlecode.jinahya.inca.util;


import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HEX {


    /**
     * logger.
     */
    private static final Logger LOGGER = Logger.getLogger(HEX.class.getName());


    static {
        LOGGER.setLevel(Level.OFF);
    }


    /**
     * Encodes a half octet.
     *
     * @param decoded half octet
     * @return encoded ascii
     */
    private static int encodeHalf(final int decoded) {

        LOGGER.log(Level.INFO, "encodedHalf({0})", decoded);

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
                return decoded + 0x30;
            case 0x0A:
            case 0x0B:
            case 0x0C:
            case 0x0D:
            case 0x0E:
            case 0x0F:
                return decoded + 0x37;
            default:
                throw new IllegalArgumentException("illegal half: " + decoded);
        }
    }


    /**
     * Encodes a single octet.
     *
     * @param decoded the octet to encode
     * @param encoded the byte array to which encoded asciis are written
     * @param offset offset in the array
     */
    protected static void encodeSingle(final int decoded, final byte[] encoded,
                                       final int offset) {

        LOGGER.log(Level.INFO, "encodedSingle({0}, {1}, {2})",
                   new Object[]{decoded, encoded, offset});

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
     * Encodes given
     * <code>decoded</code>.
     *
     * @param decoded the octets to encode
     * @return decoded asciis
     */
    public static byte[] encode(final byte[] decoded) {

        LOGGER.log(Level.INFO, "decoded.length: {0}", decoded.length);

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        final byte[] encoded = new byte[decoded.length << 1];

        int offset = 0;
        for (int i = 0; i < decoded.length; i++) {
            encodeSingle(decoded[i] & 0xFF, encoded, offset);
            offset += 2;
        }

        return encoded;
    }


    /**
     * Encodes given
     * <code>decoded</code>.
     *
     * @param decoded the string to encode
     * @return encoded octets
     */
    public static byte[] encode(final String decoded) {

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


    /**
     * Encodes given
     * <code>decoded</code> and returns as an ascii string.
     *
     * @param decoded the octets to encode
     * @return encoded ascii string
     */
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


    /**
     * Encodes given
     * <code>decoded</code> and returns as a string.
     *
     * @param decoded the string to encode
     * @return encoded output as a string
     */
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


    private static int decodeHalf(final int encoded) {

        LOGGER.log(Level.INFO, "decodeHalf({0})", encoded);

        switch (encoded) {
            case 0x30: // '0'
            case 0x31:
            case 0x32:
            case 0x33:
            case 0x34:
            case 0x35:
            case 0x36:
            case 0x37:
            case 0x38:
            case 0x39: // '9'
                return encoded - 0x30;
            case 0x41: // 'A'
            case 0x42:
            case 0x43:
            case 0x44:
            case 0x45:
            case 0x46: // 'F'
                return encoded - 0x37;
            case 0x61: // 'a'
            case 0x62:
            case 0x63:
            case 0x64:
            case 0x65:
            case 0x66: // 'f'
                return encoded - 0x57;
            default:
                throw new IllegalArgumentException("illegal half: " + encoded);
        }
    }


    protected static int decodeSingle(final byte[] encoded, final int offset) {

        LOGGER.log(Level.INFO, "decodeSingle({0}, {1})",
                   new Object[]{encoded, offset});

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

        return (decodeHalf(encoded[offset]) << 4)
               | decodeHalf(encoded[offset + 1]);
    }


    protected static int decodeSingle(final String encoded, final int offset) {

        LOGGER.log(Level.INFO, "decodeSingle({0}, {1})",
                   new Object[]{encoded, offset});

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (offset < 0) {
            throw new IllegalArgumentException("offset(" + offset + ") < 0");
        }

        if (offset >= encoded.length() - 1) {
            throw new IllegalArgumentException(
                "offset(" + offset + ") >= encoded.length(" + encoded.length()
                + ") - 1");
        }

        return (decodeHalf((encoded.charAt(offset)) << 4)
                | decodeHalf((encoded.charAt(offset + 1))));
    }


    /**
     * Decodes given
     * <code>encoded</code>.
     *
     * @param encoded encoded octets
     * @return decoded octets
     */
    public static byte[] decode(final byte[] encoded) {

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
     * Decodes given
     * <code>encoded</code>.
     *
     * @param encoded encoded string
     * @return decoded octets
     */
    public static byte[] decode(final String encoded) {

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


    /**
     * Decodes given
     * <code>encoded</code> and returns as a string.
     *
     * @param encoded encoded octets
     * @return decoded output as a string
     */
    public static String decodeToString(final byte[] encoded) {

        try {
            return new String(decode(encoded), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
//            throw new RuntimeException("\"UTF-8\" is not supported?", uee);
            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    /**
     * Decodes given
     * <code>encoded</code> and returns output as a utf string.
     *
     * @param encoded encoded ascii string
     * @return decoded output as a utf string
     */
    public static String decodeToString(final String encoded) {

        try {
            return decodeToString(encoded.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException uee) {
//            throw new RuntimeException("\"US-ASCII\" is not supported?", uee);
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


    /**
     * Creates a new instance.
     */
    protected HEX() {
        super();
    }


}

