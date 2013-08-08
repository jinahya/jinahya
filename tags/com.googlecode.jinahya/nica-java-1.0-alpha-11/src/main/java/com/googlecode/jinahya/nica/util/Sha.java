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
 * An abstract class for SHA-1 hashing.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Sha {


    /**
     * Hashes given {@code data}.
     *
     * @param data the bytes to hash
     *
     * @return hashed output
     */
    public abstract byte[] hash(final byte[] data);


    /**
     * Hashes given {@code data} which is treated as a {@code UTF-8} encoded
     * string.
     *
     * @param data the string to hash
     *
     * @return hashed output
     */
    public byte[] hash(final String data) {

        if (data == null) {
            throw new NullPointerException("data");
        }

        try {
            return hash(data.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalArgumentException("\"UTF-8\" is not supported?");
        }
    }


    /**
     * Hashes given {@code data} and returns the result as a {@code hex} encoded
     * string.
     *
     * @param data the bytes to hash.
     *
     * @return hashed output as a {@code hex} encoded string.
     */
    public String hashToString(final byte[] data) {

        return Hex.encodeToString(hash(data));
    }


    /**
     * Hashes given {@code data} which is treated as a {@code UTF-8} encoded
     * string and returns the result as a {@code hex} encoded string.
     *
     * @param data the string to hash.
     *
     * @return hashed output as a {@code hex} encoded string.
     */
    public String hashToString(final String data) {

        try {
            return hashToString(data.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalArgumentException("\"UTF-8\" is not supported?");
        }
    }


}
