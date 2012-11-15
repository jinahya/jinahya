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
 * A Par for micro edition.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ParME {


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
     * @param k keys
     * @param v values
     * @param l low
     * @param h high
     */
    private static void quicksort(final String[] k, final String[] v,
                                  final int l, final int h) {

        int i = l;
        int j = h;
        final String p = k[l + (h - l) / 2];

        while (i <= j) {
            while (k[i].compareTo(p) < 0) {
                i++;
            }
            while (k[j].compareTo(p) > 0) {
                j--;
            }
            if (i <= j) {
                exchange(k, i, j);
                exchange(v, i, j);
                i++;
                j--;
            }
        }
        if (l < j) {
            quicksort(k, v, l, j);
        }
        if (i < h) {
            quicksort(k, v, i, h);
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

//        if (decoded.isEmpty()) {
//            return "";
//        }

        final String[] normalizedKeys = new String[decoded.size()];
        final String[] normalizedValues = new String[decoded.size()];

        final Enumeration keys = decoded.keys();
        for (int i = 0; keys.hasMoreElements(); i++) {

            final Object key = keys.nextElement();
            if (!(key instanceof String)) {
                throw new IllegalArgumentException(
                    "key(" + key + ") is not an instance of " + String.class);
            }
            normalizedKeys[i] = Per.encodeToString((String) key);

            final Object value = decoded.get(key);
            if (!(value instanceof String)) {
                throw new IllegalArgumentException(
                    "value(" + value + ") is not an instance of "
                    + String.class);
            }
            normalizedValues[i] = Per.encodeToString((String) value);
        }

        if (normalizedKeys.length > 1) {
            quicksort(normalizedKeys, normalizedValues, 0,
                      normalizedKeys.length - 1);
        }

        final StringBuffer buffer = new StringBuffer();
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

        if (encoded.length() == 0) {
            return decoded;
        }
        
        int fr = 0;
        for (int am = -1; (am = encoded.indexOf('&', fr)) != -1; fr = am + 1) {
            if (am == fr) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final int eq = encoded.indexOf('=', fr);
            if (eq == -1 || eq > am) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final String key = Per.decodeToString(encoded.substring(fr, eq));
            final String val =
                Per.decodeToString(encoded.substring(eq + 1, am));
            if (decoded.put(key, val) != null) {
                throw new IllegalArgumentException(
                    "illegal encoded: duplicate key: " + key);
            }
        }

        if (fr <= encoded.length()) {
            final int eq = encoded.indexOf('=', fr);
            if (eq == -1) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final String key = Per.decodeToString(encoded.substring(fr, eq));
            final String val = Per.decodeToString(encoded.substring(eq + 1));
            if (decoded.put(key, val) != null) {
                throw new IllegalArgumentException(
                    "illegal encoded: duplicate key: " + key);
            }
        }

        return decoded;
    }


    /**
     * Creates a new instance.
     */
    protected ParME() {
        super();
    }


}

