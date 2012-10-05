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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class SHA {


    /**
     * Hashes given
     * <code>data</code>.
     *
     * @param data data to hash
     *
     * @return hashed output
     */
    public abstract byte[] hash(final byte[] data);


    /**
     * Hashes given
     * <code>data</code>.
     *
     * @param data the string to hash
     *
     * @return hashed output
     */
    public byte[] hash(final String data) {

        if (data == null) {
            throw new IllegalArgumentException("null data");
        }

        try {
            return hash(data.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalArgumentException("\"UTF-8\" not supported?");
        }
    }


    /**
     * Hashes given
     * <code>data</code> and returns as a HEX string.
     *
     * @param data data to hash
     *
     * @return hashed output as a HEX string.
     */
    public String hashToString(final byte[] data) {

        if (data == null) {
            throw new IllegalArgumentException("null data");
        }

        return HEX.encodeToString(hash(data));
    }


    /**
     * Hashes given
     * <code>unhashed</code> and returns as a HEX string.
     *
     * @param data the string to hash
     *
     * @return hashed output as a HEX string
     */
    public String hashToString(final String data) {

        if (data == null) {
            throw new IllegalArgumentException("null data");
        }

        return HEX.encodeToString(hash(data));
    }


}

