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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;
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
     *
     * @param <C>
     * @param j joined string
     * @param d the delimiter
     * @param s the collection
     *
     * @return given collection
     *
     * @deprecated
     */
    private static <C extends Collection<String>> C split(
        final String j, final String d, final C s) {

        if (j == null) {
            throw new IllegalArgumentException("null joined");
        }

        if (d == null) {
            throw new IllegalArgumentException("null delimiter");
        }

        if (d.length() == 0) {
            throw new IllegalArgumentException(
                "delimiter.length(" + d.length() + ") == 0");
        }

        if (s == null) {
            throw new IllegalArgumentException("null split");
        }

        int f = 0;
        for (int i = -1; (i = j.indexOf(d, f)) != -1; f = i + d.length()) {
            s.add(j.substring(f, i));
        }

        s.add(j.substring(f));

        return s;
    }


    /**
     *
     * @param j joined string
     * @param d the delimiter
     *
     * @return a list of split tokens
     *
     * @deprecated
     */
    private static List<String> split(final String j, final String d) {

        return split(j, d, new ArrayList<String>());
    }


    /**
     *
     * @param values
     * @param builder
     *
     * @return
     *
     * @deprecated
     */
    protected static String encodeValues(final List<String> values,
                                         final StringBuilder builder) {

        if (values == null) {
            throw new IllegalArgumentException("null values");
        }

        if (values.isEmpty()) {
            throw new IllegalArgumentException("empty values");
        }

        if (builder == null) {
            throw new IllegalArgumentException("null builder");
        }

        final List<String> encoded = new ArrayList<String>(values.size());

        for (String value : values) {
            if (value == null) {
                throw new IllegalArgumentException("null value");
            }
            encoded.add(Per.encodeToString(value));
        }

        Collections.sort(encoded);

        final Iterator<String> i = encoded.iterator();
        if (i.hasNext()) {
            builder.append(i.next());
        }
        while (i.hasNext()) {
            builder.append('&').append(i.next());
        }

        return builder.toString();
    }


    /**
     *
     * @param values
     *
     * @return
     *
     * @deprecated
     */
    public static String encodeValues(final List<String> values) {

        return encodeValues(values, new StringBuilder());
    }


    /**
     *
     * @param decoded
     * @param builder
     *
     * @return
     *
     * @deprecated
     */
    public static String encodeMultivalued(
        final Map<String, List<String>> decoded, final StringBuilder builder) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        if (builder == null) {
            throw new IllegalArgumentException("null builder");
        }

        final Map<String, String> encoded = new TreeMap<String, String>();

        for (Entry<String, List<String>> entry : decoded.entrySet()) {
            final String key = entry.getKey();
            if (key == null) {
                throw new IllegalArgumentException("null key detected");
            }
            final List<String> values = entry.getValue();
            if (values == null) {
                throw new IllegalArgumentException("null values detected");
            }
            builder.delete(0, builder.length());
            encoded.put(Per.encodeToString(key),
                        Per.encodeToString(encodeValues(values, builder)));
        }

        builder.delete(0, builder.length());
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
     *
     * @param decoded
     *
     * @return
     *
     * @deprecated
     */
    public static String encodeMultivalued(
        final Map<String, List<String>> decoded) {

        return encodeMultivalued(decoded, new StringBuilder());
    }


    /**
     *
     * @param decoded
     * @param builder
     *
     * @return
     *
     * @deprecated
     */
    protected static String encode(final Map<String, String> decoded,
                                   final StringBuilder builder) {

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        if (decoded.isEmpty()) {
            throw new IllegalArgumentException("empty decoded");
        }

        if (builder == null) {
            throw new IllegalArgumentException("null builder");
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
     * Encodes given {@code decoded}.
     *
     * @param decoded a map of keys and values to encode
     *
     * @return encoded string
     *
     * @deprecated
     */
    public static String encode(final Map<String, String> decoded) {

        return encode(decoded, new StringBuilder());
    }


    public static String encode_(final Map decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        if (decoded.isEmpty()) {
            return "";
        }

        final Map encoded = new TreeMap();

        for (Iterator i = decoded.entrySet().iterator(); i.hasNext();) {
            final Entry entry = (Entry) i.next();
            final String key = (String) entry.getKey();
            if (key == null) {
                throw new IllegalArgumentException("null key");
            }
            final String value = (String) entry.getValue();
            if (value == null) {
                throw new IllegalArgumentException("null value");
            }
            encoded.put(Per.encodeToString(key), Per.encodeToString(value));
        }

        String result = "";

        final Iterator entries = encoded.entrySet().iterator();
        if (entries.hasNext()) {
            final Entry entry = (Entry) entries.next();
            result += entry.getKey();
            result += "=";
            result += entry.getValue();
        }
        while (entries.hasNext()) {
            final Entry entry = (Entry) entries.next();
            result += "&";
            result += entry.getKey();
            result += "=";
            result += entry.getValue();
        }

        return result;
    }


    public static String enmicro(final Hashtable decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        if (decoded.isEmpty()) {
            return "";
        }

        final Vector encoded = new Vector(decoded.size() * 2);

        outer:
        for (Enumeration e = decoded.keys(); e.hasMoreElements();) {
            String key = (String) e.nextElement();
            System.out.println("key: " + key);
            String value = (String) decoded.get(key);
            System.out.println("value: " + value);
            key = Per.encodeToString(key);
            value = Per.encodeToString(value);
            for (int j = 0; j < encoded.size(); j += 2) {
                if (((String) encoded.elementAt(j)).compareTo(key) > 0) {
                    encoded.insertElementAt(value, j);
                    encoded.insertElementAt(key, j);
                    continue outer;
                }
            }
            encoded.addElement(key);
            encoded.addElement(value);
        }

        String result = "";

        final Enumeration elements = encoded.elements();
        if (elements.hasMoreElements()) {
            result += elements.nextElement();
            result += "=";
            result += elements.nextElement();
        }
        while (elements.hasMoreElements()) {
            result += "&";
            result += elements.nextElement();
            result += "=";
            result += elements.nextElement();
        }

        return result;
    }


    /**
     *
     * @param encoded
     * @param values
     *
     * @return
     *
     * @deprecated
     */
    protected static List<String> decodeValues(final String encoded,
                                               final List<String> values) {

        for (String split : split(encoded, "&", new ArrayList<String>())) {
            values.add(Per.decodeToString(split));
        }

        return values;
    }


    /**
     *
     * @param encoded
     *
     * @return
     *
     * @deprecated
     */
    public static List<String> decodeValues(final String encoded) {

        return decodeValues(encoded, new ArrayList<String>());
    }


    /**
     *
     * @param encoded
     * @param decoded
     *
     * @return
     *
     * @deprecated
     */
    public static Map<String, List<String>> decodeMutivalued(
        final String encoded, final Map<String, List<String>> decoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        if (encoded.isEmpty()) {
            return decoded;
        }

        for (String pair : split(encoded, "&")) {
            final int index = pair.indexOf('=');
            if (index == -1) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final String key = Per.decodeToString(pair.substring(0, index));
            final String value = Per.decodeToString(pair.substring(index + 1));
            if (decoded.put(key, decodeValues(value)) != null) {
                throw new IllegalArgumentException(
                    "illegal encoded: duplicate entry for key: " + key);
            }
        }

        return decoded;
    }


    /**
     *
     * @param encoded
     *
     * @return
     *
     * @deprecated
     */
    public static Map<String, List<String>> decodeMultiValued(
        final String encoded) {

        return decodeMutivalued(encoded, new HashMap<String, List<String>>());
    }


    /**
     *
     * @param encoded
     * @param decoded
     *
     * @return
     *
     * @deprecated
     */
    protected static Map<String, String> decode(
        final String encoded, final Map<String, String> decoded) {

        if (encoded == null) {
            throw new IllegalArgumentException("null encoded");
        }

        if (decoded == null) {
            throw new IllegalArgumentException("null decoded");
        }

        for (String pair : split(encoded, "&")) {
            final int index = pair.indexOf('=');
            if (index == -1) {
                throw new IllegalArgumentException("illegal encoded");
            }
            final String key = Per.decodeToString(pair.substring(0, index));
            final String value = Per.decodeToString(pair.substring(index + 1));
            if (decoded.put(key, value) != null) {
                throw new IllegalArgumentException(
                    "illegal encoded: duplicate entry: " + key);
            }
        }

        return decoded;
    }


    /**
     * Decodes given {@code encoded}.
     *
     * @param encoded encoded
     *
     * @return a map of decoded keys and values.
     *
     * @deprecated
     */
    public static Map<String, String> decode(final String encoded) {

        return decode(encoded, new HashMap<String, String>());
    }


    public static Map decode_(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        if (encoded.length() == 0 || encoded.trim().length() == 0) {
            return new HashMap();
        }

        final Map decoded = new HashMap();

        int fromIndex = 0;
        String pair;
        while (fromIndex < encoded.length()) {
            final int ampeIndex = encoded.indexOf('&', fromIndex);
            if (ampeIndex == -1) {
                pair = encoded.substring(fromIndex);
                fromIndex = encoded.length();
            } else {
                pair = encoded.substring(fromIndex, ampeIndex);
                fromIndex = ampeIndex + 1;
            }
            final int equaIndex = pair.indexOf('=');
            if (equaIndex == -1) {
                throw new IllegalArgumentException(
                    "no equal('=') in pair: " + pair);
            }
            decoded.put(Per.decodeToString(pair.substring(0, equaIndex)),
                        Per.decodeToString(pair.substring(equaIndex + 1)));
        }

        return decoded;
    }


    public static Hashtable demicro(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        if (encoded.length() == 0 || encoded.trim().length() == 0) {
            return new Hashtable();
        }

        final Hashtable decoded = new Hashtable();

        int fromIndex = 0;
        String pair;
        while (fromIndex < encoded.length()) {
            final int ampeIndex = encoded.indexOf('&', fromIndex);
            if (ampeIndex == -1) {
                pair = encoded.substring(fromIndex);
                fromIndex = encoded.length();
            } else {
                pair = encoded.substring(fromIndex, ampeIndex);
                fromIndex = ampeIndex + 1;
            }
            final int equaIndex = pair.indexOf('=');
            if (equaIndex == -1) {
                throw new IllegalArgumentException(
                    "no equal('=') in pair: " + pair);
            }
            decoded.put(Per.decodeToString(pair.substring(0, equaIndex)),
                        Per.decodeToString(pair.substring(equaIndex + 1)));
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
