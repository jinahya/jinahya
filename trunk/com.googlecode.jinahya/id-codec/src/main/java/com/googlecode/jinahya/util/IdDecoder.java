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


import java.util.StringTokenizer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdDecoder {


    /**
     * Instance holder.
     */
    private static class PrivateInstanceHolder {


        /**
         * INSTANCE.
         */
        private static final IdDecoder INSTANCE = new IdDecoder();


        /**
         * PRIVATE.
         */
        private PrivateInstanceHolder() {
            super();
        }


    }


    /**
     * Decodes given
     * <code>encoded</code>.
     *
     * @param encoded the value to be decoded
     *
     * @return the decoded value
     */
    public static long decodeId(final String encoded) {
        return PrivateInstanceHolder.INSTANCE.decode(encoded);
    }


    /**
     * Decodes given
     * <code>encoded</code>.
     *
     * @param encoded the value to be decoded.
     *
     * @return the decoded value
     */
    public long decode(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("null encoded");
        }

        if (encoded.length() < 2) {
            throw new IllegalArgumentException(
                "encoded.length(" + encoded.length() + ") < 2");
        }

        System.out.println("encoded: " + encoded);

        final String[] tokens = encoded.split("-");
        return (block(tokens[0]) << 30) | (block(tokens[1]) << 15) | block(tokens[2]);
    }


    public long block(final String encoded) {

        final String parsed =
            Integer.toString(Integer.parseInt(encoded, Character.MAX_RADIX));

        final String reversed = new StringBuilder(parsed).reverse().toString();

        final long block= Integer.parseInt(reversed.substring(0, reversed.length() - 1));

        System.out.println("\tdecode.block: " + encoded + " / " + block);
        
        return block;
    }


}

