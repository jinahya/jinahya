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
     * @param vals values
     * @param l low
     * @param h high
     */
    private static void quicksort(final String[] keys, final String[] vals,
                                  final int l, final int h) {

        int i = l;
        int j = h;
        final String pivot = keys[l + (h - l) / 2];

        while (i <= j) {
            while (keys[i].compareTo(pivot) < 0) {
                i++;
            }
            while (keys[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                exchange(keys, i, j);
                exchange(vals, i, j);
                i++;
                j--;
            }
        }
        if (l < j) {
            quicksort(keys, vals, l, j);
        }
        if (i < h) {
            quicksort(keys, vals, i, h);
        }
    }


    /**
     * Encodes given
     * <code>decoded</code>.
     *
     * @param decoded key-value pairs to encode
     *
     * @return encoded hex string
     */
    public static String encode(final Hashtable decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        if (decoded.isEmpty()) {
            return "";
        }

        final String[] normalizedKeys = new String[decoded.size()];
        final String[] normalizedVals = new String[decoded.size()];

        final Enumeration keys = decoded.keys();
        for (int i = 0; keys.hasMoreElements(); i++) {

            final Object key = keys.nextElement();
            if (!(key instanceof String)) {
                throw new IllegalArgumentException(
                    "key(" + key + ") is not an instance of " + String.class);
            }
            normalizedKeys[i] = PER.encodeToString((String) key);

            final Object value = decoded.get(key);
            if (!(value instanceof String)) {
                throw new IllegalArgumentException(
                    "value(" + value + ") is not an instance of "
                    + String.class);
            }
            normalizedVals[i] = PER.encodeToString((String) value);
        }

        quicksort(normalizedKeys, normalizedVals, 0,
                  normalizedKeys.length - 1);

        int length = normalizedKeys.length * 2; // '=', '&'
        for (int i = 0; i < normalizedKeys.length; i++) {
            length += normalizedKeys[i].length();
            length += normalizedVals[i].length();
        }
        final StringBuffer buffer = new StringBuffer(length - 1);
        if (normalizedKeys.length > 0) {
            buffer.append(normalizedKeys[0]).
                append('=').
                append(normalizedVals[0]);
        }
        for (int i = 1; i < normalizedKeys.length; i++) {
            buffer.append('&').
                append(normalizedKeys[i]).
                append('=').
                append(normalizedVals[i]);
        }

        return buffer.toString();
    }


    /**
     * Decodes given
     * <code>encoded</code>.
     *
     * @param encoded encoded hex string
     *
     * @return decoded key-value pairs
     */
    public static Hashtable decode(final String encoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        final Hashtable decoded = new Hashtable();

        int f = 0;
        for (int a = -1; (a = encoded.indexOf('&', f)) != -1;) {
            if (a == f) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final int e = encoded.indexOf('=', f);
            if (e > a) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final String key = PER.decodeToString(encoded.substring(f, e));
            final String val = PER.decodeToString(encoded.substring(e + 1, a));
            if (decoded.put(key, val) != null) {
                throw new IllegalArgumentException(
                    "illegal encoded: duplicated key: " + key);
            }
            f = a + 1;
        }

        if (f < encoded.length()) {
            final int e = encoded.indexOf('=', f);
            if (e == -1) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final String key = PER.decodeToString(encoded.substring(f, e));
            final String val = PER.decodeToString(encoded.substring(e + 1));
            if (decoded.put(key, val) != null) {
                throw new IllegalArgumentException(
                    "illegal encoded: duplicated key: " + key);
            }
        }

        return decoded;
    }


    /**
     * Creates a new instance.
     */
    protected KVPME() {
        super();
    }


}

