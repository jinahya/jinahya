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
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
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


    private static void exchange(final String[] a, final int i, final int j) {
        final String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    private static void quicksort(final String[] keys, final String[] values,
                                  final int low, final int high) {

        int i = low;
        int j = high;
        final String pivot = keys[low + (high - low) / 2];

        while (i <= j) {
            while (keys[i].compareTo(pivot) < 0) {
                i++;
            }
            while (keys[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                exchange(keys, i, j);
                exchange(values, i, j);
                i++;
                j--;
            }
        }
        if (low < j) {
            quicksort(keys, values, low, j);
        }
        if (i < high) {
            quicksort(keys, values, i, high);
        }
    }


    public static String encode(final Map<String, String> decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
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
            builder.append(entry.getKey()).append('=').append(entry.getValue());
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


    public static String encode(final Hashtable decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        final String[] normalizedKeys = new String[decoded.size()];
        final String[] normalizedValues = new String[decoded.size()];

        int length = 0;

        final Enumeration keys = decoded.keys();
        for (int i = 0; keys.hasMoreElements(); i++) {

            final Object key = keys.nextElement();
            if (!(key instanceof String)) {
                throw new IllegalArgumentException(
                    "illegal key (not a string): " + key);
            }
            normalizedKeys[i] = PER.encodeToString((String) key);
            length += normalizedKeys[i].length();

            final Object value = decoded.get(key);
            if (!(value instanceof String)) {
                throw new IllegalArgumentException(
                    "illegal value (not a string): " + value);
            }
            normalizedValues[i] = PER.encodeToString((String) value);
            length += (normalizedValues[i].length() + 1); // '='
        }

        if (normalizedKeys.length > 0) {
            length += (normalizedKeys.length - 1); // '&'
        }

        quicksort(normalizedKeys, normalizedValues, 0,
                  normalizedKeys.length - 1);

        final StringBuffer buffer = new StringBuffer(length);
        if (normalizedKeys.length > 0) {
            buffer.append(normalizedKeys[0]).
                append('=').
                append(normalizedValues[0]);
        }
        for (int i = 1; i < normalizedKeys.length; i++) {
            buffer.append('&').
                append(normalizedKeys[i]).
                append('=').
                append(normalizedValues[0]);
        }

        return buffer.toString();
    }


    public static Hashtable decode(final String encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        final Hashtable decoded = new Hashtable();

        int fromIndex = 0;
        for (int index = -1; (index = encoded.indexOf('&', fromIndex)) != -1;
             fromIndex += index) {

            final String pair = encoded.substring(fromIndex, index);
            final int ampersand = pair.indexOf('&');
            if (ampersand == -1) {
                throw new IllegalArgumentException(
                    "no ampersand index in pair: " + pair);
            }
            final String key = HEX.decodeToString(pair.substring(0, ampersand));
            final String value = HEX.decodeToString(pair.substring(ampersand + 1));

            final Object previous = decoded.put(key, value);
            if (previous != null) {
                throw new IllegalArgumentException(
                    "duplicate pair for key: " + key);
            }

            fromIndex = index + 1;
        }

        return decoded;
    }


    protected KVP() {
        super();
    }


}

