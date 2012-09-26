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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SHA {


    /**
     * logger.
     */
    private static final Logger LOGGER = Logger.getLogger(SHA.class.getName());


    static {
//        LOGGER.setLevel(Level.OFF);
    }


    /**
     * Hashes given
     * <code>unhashed</code>.
     *
     * @param unhashed the octets to hash
     * @return hashed output
     */
    public static byte[] hash(final byte[] unhashed) {

        LOGGER.log(Level.INFO, "hash({0})", unhashed);

        if (unhashed == null) {
            throw new IllegalArgumentException("null unhashed");
        }

        try {
            return MessageDigest.getInstance("SHA-512").digest(unhashed);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("\"SHA-512 not available?", nsae);
        }
    }


    /**
     * Hashes given
     * <code>unhashed</code>.
     *
     * @param unhashed the string to hash
     * @return hashed output as octets
     */
    public static byte[] hash(final String unhashed) {

        LOGGER.log(Level.INFO, "hash({0})", unhashed);

        if (unhashed == null) {
            throw new IllegalArgumentException("null unhashed");
        }

        try {
            return hash(unhashed.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalArgumentException("\"UTF-8\" not supported?", uee);
//            throw new IllegalArgumentException("\"UTF-8\" not supported?");
        }
    }


    /**
     * Hashes given
     * <code>unhashed</code> and returns as a hex string.
     *
     * @param unhashed the octets to hash
     * @return hashed output as a hex string.
     */
    public static String hashToString(final byte[] unhashed) {

        LOGGER.log(Level.INFO, "hasToString({0})", unhashed);

        if (unhashed == null) {
            throw new IllegalArgumentException("null unhashed");
        }

        return HEX.encodeToString(hash(unhashed));
    }


    /**
     * Hashes given
     * <code>unhashed</code> and returns as a hex string.
     *
     * @param unhashed the string to hash
     * @return hashed output as a hex string
     */
    public static String hashToString(final String unhashed) {

        LOGGER.log(Level.INFO, "hasToString({0})", unhashed);

        if (unhashed == null) {
            throw new IllegalArgumentException("null unhashed");
        }

        try {
            return hashToString(unhashed.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalArgumentException("\"UTF-8\" not supported?", uee);
//            throw new IllegalArgumentException("\"UTF-8\" not supported?");
        }
    }


    /**
     * Creates a new instance.
     */
    protected SHA() {
        super();
    }


}

