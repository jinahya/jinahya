/*
 * Copyright 2012 onacit.
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


import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 *
 * @author onacit
 */
public class IdCodec {


    /**
     * the encoded output pattern.
     */
    static final Pattern PATTERN;


    static {
        try {
            PATTERN = Pattern.compile("([a-z0-9]+)-([a-z0-9]+)");
        } catch (PatternSyntaxException pse) {
            throw new InstantiationError("pattern compilation failed");
        }
    }


    /**
     * private instance holder.
     */
    private static class PrivateInstanceHolder {


        /**
         * instance.
         */
        private static final IdCodec INSTANCE = new IdCodec();


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
     * @param decoded the value to be encoded
     * @return the encoded value
     */
    public static String encodeId(final long decoded) {
        return PrivateInstanceHolder.INSTANCE.encode(decoded);
    }


    /**
     * Decodes given
     * <code>encoded</code>.
     *
     * @param encoded the value to be decoded
     * @return the decoded value
     */
    public static long decodeId(final String encoded) {
        return PrivateInstanceHolder.INSTANCE.decode(encoded);
    }


    /**
     * Encodes given
     * <code>decoded</code>.
     *
     * @param decoded the value to be encoded
     * @return the encoded value
     */
    public String encode(final long decoded) {
        return encoder.encode(decoded);
    }


    /**
     * Decodes given
     * <code>encoded</code>.
     *
     * @param encoded the value to be decoded
     * @return the decoded value
     */
    public long decode(final String encoded) {
        return decoder.decode(encoded);
    }


    /**
     * encoder.
     */
    private final IdEncoder encoder = new IdEncoder();


    /**
     * decoder.
     */
    private final IdDecoder decoder = new IdDecoder();


}

