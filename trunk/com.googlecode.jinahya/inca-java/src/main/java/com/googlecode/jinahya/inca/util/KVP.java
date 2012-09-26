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


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Logger;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class KVP {


    /**
     * logger.
     */
    private static final Logger LOGGER = Logger.getLogger(KVP.class.getName());


    static {
//        LOGGER.setLevel(Level.OFF);
    }


    /**
     * Encodes given
     * <code>decoded</code>.
     *
     * @param decoded a map of keys and values to encode
     * @return encoded string
     */
    public static String encode(final Map<String, String> decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        if (decoded.isEmpty()) {
            throw new IllegalArgumentException("empty decoded");
        }

        final Map<String, String> encoded = new TreeMap<String, String>();

        for (Entry<String, String> entry : decoded.entrySet()) {
            if (entry.getKey() == null) {
                throw new IllegalArgumentException("illegal key: null");
            }
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("illegal value: null");
            }
            encoded.put(PER.encodeToString(entry.getKey()),
                        PER.encodeToString(entry.getValue()));
        }

        int length = encoded.size() * 2; // '=', '&'
        for (Entry<String, String> entry : encoded.entrySet()) {
            length += entry.getKey().length();
            length += entry.getValue().length();
        }

        final StringBuilder builder = new StringBuilder(length);
        final Iterator<Entry<String, String>> entries =
            encoded.entrySet().iterator();
        if (entries.hasNext()) {
            final Entry<String, String> entry = entries.next();
            builder.
                append(entry.getKey()).
                append('=').
                append(entry.getValue());
        }
        while (entries.hasNext()) {
            final Entry<String, String> entry = entries.next();
            builder.append('&').
                append(entry.getKey()).
                append('=').
                append(entry.getValue());
        }

        return builder.toString();
    }


    /**
     * Decodes given
     * <code>encoded</code>.
     *
     * @param encoded encoded
     * @return a map of decoded keys and values.
     */
    public static Map<String, String> decode(final String encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (encoded.isEmpty()) {
            throw new IllegalArgumentException("empty encoded");
        }

        final Map<String, String> decoded = new HashMap<String, String>();

        for (String pair : encoded.split("&")) {
            final int index = pair.indexOf('=');
            if (index == -1) {
                throw new IllegalArgumentException("illegal pair: " + pair);
            }
            final String key = pair.substring(0, index);
            final String value = pair.substring(index + 1);
            decoded.put(PER.decodeToString(key), PER.decodeToString(value));
        }

        return decoded;
    }


    /**
     * Creates a new instance.
     */
    protected KVP() {
        super();
    }


}

