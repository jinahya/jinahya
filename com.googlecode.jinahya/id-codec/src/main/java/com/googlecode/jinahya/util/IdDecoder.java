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


package com.googlecode.jinahya.util;


import java.util.UUID;
import java.util.regex.Matcher;


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

        final Matcher matcher = IdCodec.DOUBLE_PATTERN.matcher(encoded);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("illegal pattern: " + encoded);
        }

        final IdDecoder decoder = new IdDecoder();
        final long mostSignificantBits = decoder.decode(matcher.group(1));
        final long leastSignificantBits = decoder.decode(matcher.group(4));

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

        final Matcher matcher = IdCodec.SINGLE_PATTERN.matcher(encoded);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("illegal pattern: " + encoded);
        }

        return (block(matcher.group(1)) << 32) | (block(matcher.group(2)));
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

