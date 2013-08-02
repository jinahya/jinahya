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


import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Encoder for Database IDs.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdEncoder {


    /**
     * Encodes a single block.
     *
     * @param decoded block to encode
     * @param random random
     *
     * @return encoded block
     */
    private static String block(final long decoded, final Random random) {

        final StringBuilder builder = new StringBuilder(Long.toString(decoded));

        builder.append(Integer.toString(random.nextInt(9) + 1)); // 1-9
        builder.append(Integer.toString(random.nextInt(9) + 1)); // 1-9

        builder.reverse();

        return Long.toString(
            Long.parseLong(builder.toString()), Character.MAX_RADIX);
    }


    /**
     * Encodes a single block.
     *
     * @param decoded block to encode
     *
     * @return encoded block
     */
    private static String block(final long decoded) {

        return block(decoded, ThreadLocalRandom.current());
    }


    /**
     * Encodes given {@code decoded}.
     *
     * @param decoded the value to encode
     *
     * @return encoded output.
     */
    public static String encodeLong(final long decoded) {

        return block(decoded >>> 0x20) + "-" + block(decoded & 0xFFFFFFFFL);
    }


    /**
     * Encodes given {@code decoded}.
     *
     * @param decoded the value to encode.
     *
     * @return encoded output.
     */
    public static String encodeUUID(final UUID decoded) {

        return encodeLong(decoded.getMostSignificantBits()) + "-"
               + encodeLong(decoded.getLeastSignificantBits());
    }


    /**
     * Encodes given {@code decoded}.
     *
     * @param decoded the value to encode.
     *
     * @return encoded result.
     */
    public String encode(final long decoded) {

        return encodeLong(decoded);
    }


}
