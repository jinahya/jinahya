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


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Par {


    protected static final String WORD = "[^=&]*";
//        "([\\p{Alnum}\\-\\._~]|%(\\p{XDigit}{2}))*";


    protected static final String PAIR = WORD + "=" + WORD;


    protected static final String REGEX = "(" + PAIR + ")?(&(" + PAIR + "))*";


    protected static final Pattern PATTERN;


    static {
        PATTERN = Pattern.compile(REGEX);
    }


    /**
     * Encodes given
     * <code>decoded</code>.
     *
     * @param decoded a map of keys and values to encode
     *
     * @return encoded string
     */
    public static String encode(final Map<String, String> decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        final Map<String, String> encoded = new TreeMap<String, String>();

        for (Entry<String, String> entry : decoded.entrySet()) {
            final String key = entry.getKey();
            if (key == null) {
                throw new IllegalArgumentException("null key detected");
            }
            final String value = entry.getValue();
            if (value == null) {
                throw new IllegalArgumentException("null value detected");
            }
            encoded.put(Per.encodeToString(key), Per.encodeToString(value));
        }

        final StringBuilder builder = new StringBuilder();
        final Iterator<Entry<String, String>> entries =
            encoded.entrySet().iterator();
        if (entries.hasNext()) {
            final Entry<String, String> entry = entries.next();
            builder.append(entry.getKey()).
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
     *
     * @return a map of decoded keys and values.
     */
    public static Map<String, String> decode(final String encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        final Map<String, String> decoded = new HashMap<String, String>();

        if (encoded.isEmpty()) {
            return decoded;
        }

        int fr = 0;
        for (int am = -1; (am = encoded.indexOf('&', fr)) != -1;) {
            if (am == fr) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final int eq = encoded.indexOf('=', fr);
            if (eq == -1 || eq > am) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final String key = Per.decodeToString(encoded.substring(fr, eq));
            final String value =
                Per.decodeToString(encoded.substring(eq + 1, am));
            if (decoded.put(key, value) != null) {
                throw new IllegalArgumentException(
                    "illegal encoded: duplicate entry: " + key);
            }
            fr = am + 1;
        }

        if (fr <= encoded.length()) {
            final int eq = encoded.indexOf('=', fr);
            if (eq == -1) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final String key = Per.decodeToString(encoded.substring(fr, eq));
            final String value = Per.decodeToString(encoded.substring(eq + 1));
            if (decoded.put(key, value) != null) {
                throw new IllegalArgumentException(
                    "illegal encoded: duplicate key: " + key);
            }
        }

        return decoded;
    }


    /**
     * Creates a new instance.
     */
    protected Par() {
        super();
    }


}

