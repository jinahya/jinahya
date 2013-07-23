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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Par {


    /**
     * Encodes given {@code decoded}.
     *
     * @param decoded the map to encode.
     *
     * @return encoded output
     */
    public static String encode(final Map decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        if (decoded.isEmpty()) {
            return "";
        }

        final Map encoded = new TreeMap();

        for (final Iterator e = decoded.entrySet().iterator(); e.hasNext();) {
            final Entry entry = (Entry) e.next();
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

        final Iterator e = encoded.entrySet().iterator();
        if (e.hasNext()) {
            final Entry entry = (Entry) e.next();
            result += entry.getKey();
            result += "=";
            result += entry.getValue();
        }
        while (e.hasNext()) {
            final Entry entry = (Entry) e.next();
            result += "&";
            result += entry.getKey();
            result += "=";
            result += entry.getValue();
        }

        return result;
    }


    /**
     * Encodes given {@code decoded}.
     *
     * @param decoded the keys and values to encode
     *
     * @return encoded output
     *
     * @deprecated
     */
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
            String value = (String) decoded.get(key);
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
     * Encodes given {@code encoded} and returns a map of keys and values.
     *
     * @param encoded encoded string to decode
     *
     * @return decoded output
     */
    public static Map decode(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        if (encoded.length() == 0 || encoded.trim().length() == 0) {
            return new HashMap();
        }

        final Map decoded = new HashMap();

        int from = 0;
        String pair;
        while (from < encoded.length()) {
            final int ampe = encoded.indexOf('&', from);
            if (ampe == -1) {
                pair = encoded.substring(from);
                from = encoded.length();
            } else {
                pair = encoded.substring(from, ampe);
                from = ampe + 1;
                if (from == encoded.length()) {
                    throw new IllegalArgumentException(
                        "illegal encoded: " + encoded);
                }
            }
            final int equa = pair.indexOf('=');
            if (equa == -1) {
                throw new IllegalArgumentException(
                    "no equal('=') in pair: " + pair);
            }
            final String key = Per.decodeToString(pair.substring(0, equa));
            final String val = Per.decodeToString(pair.substring(equa + 1));
            final Object pre = decoded.put(key, val);
            if (pre != null) {
                throw new IllegalArgumentException(
                    "duplicated entry for key: " + key);
            }
        }

        return decoded;
    }


    /**
     * Decodes given {@code encoded}.
     *
     * @param encoded the string to decode
     *
     * @return decoded key-value in a hashtable.
     *
     * @deprecated
     */
    public static Hashtable demicro(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        if (encoded.length() == 0 || encoded.trim().length() == 0) {
            return new Hashtable();
        }

        final Hashtable decoded = new Hashtable();

        int from = 0;
        String pair;
        while (from < encoded.length()) {
            final int ampe = encoded.indexOf('&', from);
            if (ampe == -1) {
                pair = encoded.substring(from);
                from = encoded.length();
            } else {
                pair = encoded.substring(from, ampe);
                from = ampe + 1;
                if (from == encoded.length()) {
                    throw new IllegalArgumentException(
                        "illegal encoded: " + encoded);
                }
            }
            final int equa = pair.indexOf('=');
            if (equa == -1) {
                throw new IllegalArgumentException(
                    "no equal('=') in pair: " + pair);
            }
            final String key = Per.decodeToString(pair.substring(0, equa));
            final String val = Per.decodeToString(pair.substring(equa + 1));
            final Object pre = decoded.put(key, val);
            if (pre != null) {
                throw new IllegalArgumentException(
                    "duplicated entry for key: " + key);
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
