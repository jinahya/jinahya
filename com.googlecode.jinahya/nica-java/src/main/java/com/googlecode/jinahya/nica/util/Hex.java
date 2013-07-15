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


import java.io.UnsupportedEncodingException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Hex {


    /**
     * Encodes a half octet.
     *
     * @param decoded half octet
     *
     * @return encoded output
     */
    private static int encodeHalf(final int decoded) {

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
     * @param encoded the byte array to which encoded output are written
     * @param offset offset in the array
     */
    static void encodeSingle(final int decoded, final byte[] encoded,
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
//
//        if (encoded == null) {
//            throw new IllegalArgumentException("null encoded");
//        }
//
//        if (offset < 0) {
//            throw new IllegalArgumentException("offset(" + offset + ") < 0");
//        }
//
//        if (offset >= encoded.length - 1) {
//            throw new IllegalArgumentException(
//                "offset(" + offset + ") >= encoded.length(" + encoded.length
//                + ") - 1");
//        }

        encoded[offset] = (byte) encodeHalf((decoded >> 4) & 0x0F);
        encoded[offset + 1] = (byte) encodeHalf(decoded & 0x0F);
    }


    /**
     * Encodes given {@code decoded}.
     *
     * @param decoded the bytes to encode
     *
     * @return encoded output
     */
    public static byte[] encode(final byte[] decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
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
     * Encodes given {@code decoded}.
     *
     * @param decoded the string to encode
     *
     * @return encoded output
     */
    public static byte[] encode(final String decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        try {
            return encode(decoded.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" not supported?");
        }
    }


    /**
     * Encodes given {@code decoded} and returns as an ASCII string.
     *
     * @param decoded the bytes to encode
     *
     * @return encoded output as an ASCII string
     */
    public static String encodeToString(final byte[] decoded) {

        if (decoded == null) {
            throw new NullPointerException("null decoded");
        }

        try {
            return new String(encode(decoded), "US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


    /**
     * Encodes given {@code decoded} and returns as a string.
     *
     * @param decoded the string to encode
     *
     * @return encoded output as a string
     */
    public static String encodeToString(final String decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        try {
            return encodeToString(decoded.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?", uee);
        }
    }


    public static byte[] encode_(final byte[] decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        final byte[] encoded = new byte[decoded.length << 1];

        int offset = 0;
        for (int i = 0; i < decoded.length; i++) {
            final int h = (decoded[i] >> 4) & 0x0F;
            final int l = decoded[i] & 0x0F;
            encoded[offset++] = (byte) (h + (h < 0x09 ? 0x30 : 0x37));
            encoded[offset++] = (byte) (l + (l < 0x09 ? 0x30 : 0x37));
        }

        return encoded;
    }


    public static byte[] encode_(final String decoded) {


        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        try {
            return encode_(decoded.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF_8\" is not supported?", uee);
        }
    }


    public static String encodeToString_(final byte[] decoded) {

        try {
            return new String(encode_(decoded), "US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?", uee);
        }
    }


    public static String encodeToString_(final String decoded) {

        try {
            return new String(encode_(decoded), "US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?", uee);
        }
    }


    private static int decodeHalf(final int encoded) {

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


    static int decodeSingle(final byte[] encoded, final int offset) {

//        if (encoded == null) {
//            throw new IllegalArgumentException("null encoded");
//        }
//
//        if (offset < 0) {
//            throw new IllegalArgumentException("offset(" + offset + ") < 0");
//        }
//
//        if (offset >= encoded.length - 1) {
//            throw new IllegalArgumentException(
//                "offset(" + offset + ") >= encoded.length(" + encoded.length
//                + ") - 1");
//        }

        return (decodeHalf(encoded[offset]) << 4)
               | decodeHalf(encoded[offset + 1]);
    }


    /**
     * Decodes given {@code encoded}.
     *
     * @param encoded the bytes to decode
     *
     * @return decoded output
     */
    public static byte[] decode(final byte[] encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
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
     * Decodes given {@code encoded}.
     *
     * @param encoded the string to decode
     *
     * @return decoded output
     */
    public static byte[] decode(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        try {
            return decode(encoded.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


    /**
     * Decodes given {@code encoded} and returns as a 'UTF-8' encoded String.
     *
     * @param encoded encoded bytes
     *
     * @return decoded output as a UTF-8 String
     */
    public static String decodeToString(final byte[] encoded) {

        try {
            return new String(decode(encoded), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    /**
     * Decodes given {@code encoded} and returns output as a 'UTF-8' encoded
     * String.
     *
     * @param encoded encoded 'US-ASCII' String
     *
     * @return decoded output as a 'UTF-8' encoded String
     */
    public static String decodeToString(final String encoded) {

        try {
            return decodeToString(encoded.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


    public static byte[] decode_(final byte[] encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        final byte[] decoded = new byte[encoded.length >> 1];

        int offset = 0;
        for (int i = 0; i < decoded.length; i++) {
            final int h = encoded[offset]
                          - (encoded[offset] <= 0x39 ? 0x30
                             : (encoded[offset] <= 0x46 ? 0x37 : 0x57));
            ++offset;
            final int l = encoded[offset]
                          - (encoded[offset] <= 0x39 ? 0x30
                             : (encoded[offset] <= 0x46 ? 0x37 : 0x57));
            ++offset;
            decoded[i] = (byte) ((h << 4) | l);
        }

        return decoded;
    }


    public static byte[] decode_(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        try {
            return decode_(encoded.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported? ", uee);
        }
    }


    public static String decodeToString_(final byte[] encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        try {
            return new String(decode_(encoded), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported? ", uee);
        }
    }


    public static String decodeToString_(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        try {
            return new String(decode_(encoded), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported? ", uee);
        }
    }


    /**
     * Creates a new instance.
     */
    protected Hex() {
        super();
    }


}
