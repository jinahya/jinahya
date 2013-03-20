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
     * Decodes given {@code encoded}.
     *
     * @param encoded the value to be decoded
     *
     * @return the decoded value
     */
    public static long decodeLong(final String encoded) {
        return new IdDecoder().decode(encoded);
    }


    /**
     * Decodes given
     * <code>encoded</code>.
     *
     * @param encoded the value to be decoded
     *
     * @return the decoded value
     */
    public static UUID decodeUUID(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("null encoded");
        }

        final int index = encoded.indexOf('_');
        if (index == -1) {
            throw new IllegalArgumentException("wrong encoded: " + encoded);
        }

        final IdDecoder decoder = new IdDecoder();
        final long mostSignificantBits =
            decoder.decode(encoded.substring(0, index));
        final long leastSignificantBits =
            decoder.decode(encoded.substring(index + 1));

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

        if (encoded == null) {
            throw new NullPointerException("null encoded");
        }

        final int index = encoded.indexOf('-');
        if (index == -1) {
            throw new IllegalArgumentException("wrong encoded: " + encoded);
        }

        return (block(encoded.substring(0, index)) << 32)
               | (block(encoded.substring(index + 1)));
    }


    /**
     * Decodes a single block.
     *
     * @param encoded the block to decode
     *
     * @return decoded block
     */
    private long block(final String encoded) {

        final StringBuilder builder = new StringBuilder(
            Long.toString(Long.parseLong(encoded, Character.MAX_RADIX)));

        builder.reverse();

        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);

        return Long.parseLong(builder.toString());
    }


}

