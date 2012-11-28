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
import java.util.Vector;


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
     * @param keys keys
     * @param values values
     * @param low low
     * @param high high
     */
    private static void sort(final String[] keys, final String[] values,
                             final int low, final int high) {

        if (keys.length < 2) {
            return;
        }

        int i = low;
        int j = high;
        final String p = keys[low + (high - low) / 2];

        while (i <= j) {
            while (keys[i].compareTo(p) < 0) {
                i++;
            }
            while (keys[j].compareTo(p) > 0) {
                j--;
            }
            if (i <= j) {
                exchange(keys, i, j);
                if (values != null) {
                    exchange(values, i, j);
                }
                i++;
                j--;
            }
        }
        if (low < j) {
            sort(keys, values, low, j);
        }
        if (i < high) {
            sort(keys, values, i, high);
        }
    }


//    private static String join(final String delimiter, final Vector split) {
//
//        if (split == null) {
//            throw new IllegalArgumentException("null split");
//        }
//
//        if (delimiter == null) {
//            throw new IllegalArgumentException("null delimiter");
//        }
//
//        final Enumeration elements = split.elements();
//        for (int i = 0; elements.hasMoreElements(); i++) {
//            final Object element = elements.nextElement();
//            if (element == null) {
//                throw new IllegalArgumentException("null element");
//            }
//            if (!(element instanceof String)) {
//                throw new IllegalArgumentException(
//                    "element(" + element + ") is not an instance of "
//                    + String.class);
//            }
//            normalizedValues[i] = Per.encodeToString((String) element);
//        }
//    }
    private String[] split_string(String original, String separator) {
        Vector nodes = new Vector();

        int index = original.indexOf(separator);
        while (index >= 0) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
            index = original.indexOf(separator);
        }
        nodes.addElement(original);
        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); loop++) {
                result[loop] = (String) nodes.elementAt(loop);
                System.out.println(result[loop]);
            }
        }
        return result;
    }


    private static Vector split(final String j, final String d,
                                final Vector s) {

        if (d == null) {
            throw new IllegalArgumentException("null delimiter");
        }

        if (d.length() == 0) {
            throw new IllegalArgumentException(
                "delimiter.length(" + d.length() + ") == 0");
        }

        int f = 0;
        for (int i = -1; (i = j.indexOf(d, f)) != -1; f = i + d.length()) {
            s.add(j.substring(f, i));
        }

        s.add(j.substring(f));

        return s;
    }


    private static Vector split(final String j, final String d) {

        return split(j, d, new Vector());
    }


    public static String encodeValues(final Vector values,
                                      final StringBuffer buffer) {

        if (values == null) {
            throw new IllegalArgumentException("null values");
        }

        if (buffer == null) {
            throw new IllegalArgumentException("null buffer");
        }

        final String[] normalizedValues = new String[values.size()];

        final Enumeration elements = values.elements();
        for (int i = 0; elements.hasMoreElements(); i++) {

            final Object value = elements.nextElement();
            if (value == null) {
                throw new IllegalArgumentException("null value");
            }
            if (!(value instanceof String)) {
                throw new IllegalArgumentException(
                    "value(" + value + ") is not an instance of "
                    + String.class);
            }
            normalizedValues[i] = Per.encodeToString((String) value);
        }

        sort(normalizedValues, null, 0, normalizedValues.length);

        if (normalizedValues.length > 0) {
            buffer.append(normalizedValues[0]);
        }
        for (int i = 1; i < normalizedValues.length; i++) {
            buffer.append('&').append(normalizedValues[i]);
        }

        return buffer.toString();
    }


    public static String encodeValues(final Vector values) {

        if (values == null) {
            throw new IllegalArgumentException("null values");
        }

        return encodeValues(values, new StringBuffer());
    }


    /**
     * Encodes given {@code decoded}.
     *
     * @param decoded key-value pairs to encode
     *
     * @return encoded hex string
     */
    public static String encode(final Hashtable decoded) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        final String[] normalizedKeys = new String[decoded.size()];
        final String[] normalizedValues = new String[decoded.size()];

        final Enumeration keys = decoded.keys();
        for (int i = 0; keys.hasMoreElements(); i++) {

            final Object key = keys.nextElement();
            if (key == null) {
                throw new IllegalArgumentException("null key");
            }
            if (!(key instanceof String)) {
                throw new IllegalArgumentException(
                    "key(" + key + ") is not an instance of " + String.class);
            }
            normalizedKeys[i] = Per.encodeToString((String) key);

            final Object value = decoded.get(key);
            if (value == null) {
                throw new IllegalArgumentException("null value");
            }
            if (!(value instanceof String)) {
                throw new IllegalArgumentException(
                    "value(" + value + ") is not an instance of "
                    + String.class);
            }
            normalizedValues[i] = Per.encodeToString((String) value);
        }

        sort(normalizedKeys, normalizedValues, 0, normalizedKeys.length - 1);

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


    public static Vector decodeValues(final String encoded) {

        return decodeValues(encoded, new Vector());
    }


    public static Vector decodeValues(final String encoded,
                                      final Vector values) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (values == null) {
            throw new IllegalArgumentException("null values");
        }

        final Enumeration e = split(encoded, "&", new Vector()).elements();
        while (e.hasMoreElements()) {
            values.add(Per.decodeToString((String) e.nextElement()));
        }

        return values;
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

        final Enumeration pairs = split(encoded, "&").elements();
        while (pairs.hasMoreElements()) {
            final String pair = (String) pairs.nextElement();
            final int index = pair.indexOf('=');
            if (index == -1) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final String key = Per.decodeToString(pair.substring(0, index));
            final String value = Per.decodeToString(pair.substring(index + 1));
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
    protected ParME() {
        super();
    }


}

