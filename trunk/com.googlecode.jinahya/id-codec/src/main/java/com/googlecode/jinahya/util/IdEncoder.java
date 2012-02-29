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


import java.util.Random;


/**
 * Encoder for Database IDs.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdEncoder {


    /**
     * .
     */
    private static final Random RANDOM = new Random();


    /**
     * Instance holder.
     */
    private static class PrivateInstanceHolder {


        /**
         * INSTANCE.
         */
        private static final IdEncoder INSTANCE = new IdEncoder();


        /**
         * PRIVATE.
         */
        private PrivateInstanceHolder() {
            super();
        }


    }


    /**
     * Encodes given
     * <code>decoded</code>.
     *
     * @param decoded the value to be encoded.
     *
     * @return the decoded value.
     */
    public static String encodeId(final long decoded) {



        return PrivateInstanceHolder.INSTANCE.encode(decoded);
    }


    /**
     * Encodes given
     * <code>decoded</code>.
     *
     * @param decoded the value to be encoded
     *
     * @return the encoded value.
     */
    public String encode(final long decoded) {

        return block(decoded >>> 32) + "-" + block(decoded & 0xFFFFFFFFL);
    }


    private String block(final long decoded) {

        if (decoded > 0xFFFFFFFFL) {
            throw new IllegalArgumentException(
                "decoded(" + decoded + ") > 0xFFFFFFFFL");
        }

        final String concatenated =
            Long.toString(decoded) + (RANDOM.nextInt(9) + 1);

        final String reversed =
            new StringBuilder(concatenated).reverse().toString();

        final String encoded =
            Long.toString(Long.parseLong(reversed), Character.MAX_RADIX);

        return encoded;
    }


}

