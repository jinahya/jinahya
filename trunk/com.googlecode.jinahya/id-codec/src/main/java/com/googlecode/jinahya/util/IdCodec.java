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


import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 *
 * @author onacit
 */
public class IdCodec {


    /**
     * Pattern string for single block.
     */
    private static final String SINGLE_REGEX = "([a-z0-9]+)-([a-z0-9]+)";


    /**
     * the encoded output pattern.
     */
    static final Pattern SINGLE_PATTERN;


    static {
        try {
            SINGLE_PATTERN = Pattern.compile(SINGLE_REGEX);
        } catch (PatternSyntaxException pse) {
            throw new InstantiationError("pattern compilation failed");
        }
    }


    /**
     * Pattern string for double block.
     */
    private static final String DOUBLE_REGEX =
        "(" + SINGLE_REGEX + ")-(" + SINGLE_REGEX + ")";


    /**
     * the encoded output pattern for UUID.
     */
    static final Pattern DOUBLE_PATTERN;


    static {
        try {
            DOUBLE_PATTERN = Pattern.compile(DOUBLE_REGEX);
        } catch (PatternSyntaxException pse) {
            throw new InstantiationError("pattern compilation failed");
        }
    }


    /**
     * Private instance holder.
     */
    private static final class PrivateInstanceHolder {


        /**
         * INSTANCE.
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
     *
     * @return the encoded value
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
     * @return the encoded value
     */
    public static String encodeUUID(final UUID decoded) {
        return IdEncoder.encodeUUID(decoded);
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
     * @param encoded the value to be decoded
     *
     * @return the decoded value
     */
    public static UUID decodeUUID(final String encoded) {
        return IdDecoder.decodeUUID(encoded);
    }


    /**
     * Encodes given
     * <code>decoded</code>.
     *
     * @param decoded the value to be encoded
     *
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
     *
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

