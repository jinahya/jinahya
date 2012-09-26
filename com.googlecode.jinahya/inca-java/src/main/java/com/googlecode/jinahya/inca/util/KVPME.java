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


import java.util.Enumeration;
import java.util.Hashtable;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class KVPME {


    /**
     * Exchanges.
     *
     * @param a array
     * @param i i
     * @param j j
     */
    private static void exchange(final String[] a, final int i, final int j) {
        final String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    /**
     * Sorts given
     * <code>keys</code> along with values.
     *
     * @param keys keys
     * @param values values
     * @param low low
     * @param high high
     */
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


    /**
     * Encodes given
     * <code>decoded</code>.
     *
     * @param decoded key-value pairs to encode
     * @return encoded hex string
     */
    public static String encode(final Hashtable decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        if (decoded.isEmpty()) {
            throw new IllegalArgumentException("empty decoded");
        }

        final String[] normalizedKeys = new String[decoded.size()];
        final String[] normalizedValues = new String[decoded.size()];

        final Enumeration keys = decoded.keys();
        for (int i = 0; keys.hasMoreElements(); i++) {

            final Object key = keys.nextElement();
            if (!(key instanceof String)) {
                throw new IllegalArgumentException(
                    "illegal key (not a string): " + key);
            }
            normalizedKeys[i] = PER.encodeToString((String) key);

            final Object value = decoded.get(key);
            if (!(value instanceof String)) {
                throw new IllegalArgumentException(
                    "illegal value (not a string): " + value);
            }
            normalizedValues[i] = PER.encodeToString((String) value);
        }

        quicksort(normalizedKeys, normalizedValues, 0,
                  normalizedKeys.length - 1);

        int length = normalizedKeys.length * 2; // '=', '&'
        for (int i = 0; i < normalizedKeys.length; i++) {
            length += normalizedKeys[i].length();
            length += normalizedValues[i].length();
        }
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
                append(normalizedValues[i]);
        }

        return buffer.toString();
    }


    private static void decodePair(final String encoded, final int beginIndex,
                                   final int endIndex,
                                   final Hashtable decoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (encoded.length() == 0) {
            throw new IllegalArgumentException("empty encoded");
        }

        if (beginIndex < 0) {
            throw new IllegalArgumentException(
                "beginIndex(" + beginIndex + ") < 0");
        }

        if (beginIndex >= encoded.length()) {
            throw new IllegalArgumentException(
                "beginIndex(" + beginIndex + ") >= encoded.length("
                + encoded.length() + ")");
        }

        if (endIndex <= beginIndex) {
            throw new IllegalArgumentException(
                "endIndex(" + endIndex + ") <= beginIndex(" + beginIndex + ")");
        }

        if (endIndex > encoded.length()) {
            throw new IllegalArgumentException(
                "endIndex(" + endIndex + ") <= encoded.length("
                + encoded.length() + ")");
        }

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        final int equalSignIndex = encoded.indexOf('=', beginIndex);
        if (equalSignIndex == -1) {
            throw new IllegalArgumentException("no equal sign in range");
        }
        if (equalSignIndex > endIndex) {
            throw new IllegalArgumentException("no equal sign in range");
        }

        final String key = PER.decodeToString(
            encoded.substring(beginIndex, equalSignIndex));
        final String value = PER.decodeToString(
            encoded.substring(equalSignIndex + 1, endIndex));

        final Object previous = decoded.put(key, value);
        if (previous != null) {
            throw new IllegalArgumentException(
                "duplicate pair for key: " + key);
        }
    }


    /**
     * Decodes given
     * <code>encoded</code>.
     *
     * @param encoded encoded hex string
     * @return decoded key-value pairs
     */
    public static Hashtable decode(final String encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (encoded.length() == 0) {
            throw new IllegalArgumentException("empty encoded");
        }

        final Hashtable decoded = new Hashtable();

        int f = 0;
        for (int i = -1; (i = encoded.indexOf('&', f)) != -1; f = i + 1) {
            decodePair(encoded, f, i, decoded);
        }

        decodePair(encoded, f, encoded.length(), decoded);

        return decoded;
    }


    /**
     * Creates a new instance.
     */
    protected KVPME() {
        super();
    }


}

