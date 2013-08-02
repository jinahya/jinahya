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


import java.util.UUID;


/**
 * Decoder for Database IDs.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdDecoder {


    /**
     * Decodes a single block.
     *
     * @param encoded the block to decode
     *
     * @return decoded block
     */
    private static long block(final String encoded) {

        final StringBuilder builder = new StringBuilder(
            Long.toString(Long.parseLong(encoded, Character.MAX_RADIX)));

        builder.reverse();

        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);

        return Long.parseLong(builder.toString());
    }


    /**
     * Decodes given {@code encoded}.
     *
     * @param encoded the value to be decoded
     *
     * @return the decoded value
     */
    public static long decodeLong(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        final int index = encoded.indexOf('-');
        if (index == -1) {
            throw new IllegalArgumentException("wrong encoded: " + encoded);
        }

        return (block(encoded.substring(0, index)) << 32)
               | (block(encoded.substring(index + 1)));
    }


    /**
     * Decodes given {@code encoded}.
     *
     * @param encoded the value to be decoded
     *
     * @return the decoded value
     */
    public static UUID decodeUUID(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        final int first = encoded.indexOf('-');
        if (first == -1) {
            throw new IllegalArgumentException("wrong encoded: " + encoded);
        }

        final int second = encoded.indexOf('-', first + 1);
        if (second == -1) {
            throw new IllegalArgumentException("wrong encoded: " + encoded);
        }

        final long mostSignificantBits =
            decodeLong(encoded.substring(0, second));
        final long leastSignificantBits =
            decodeLong(encoded.substring(second + 1));

        return new UUID(mostSignificantBits, leastSignificantBits);
    }


    /**
     * Decodes given {@code decoded}.
     *
     * @param encoded encoded value to decode.
     *
     * @return decoded value
     */
    public long decode(final String encoded) {

        return decodeLong(encoded);
    }


}

