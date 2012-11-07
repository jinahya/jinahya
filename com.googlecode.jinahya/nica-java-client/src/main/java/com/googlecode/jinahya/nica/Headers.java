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


package com.googlecode.jinahya.nica;


import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.Hac;
import com.googlecode.jinahya.nica.util.Hex;
import com.googlecode.jinahya.nica.util.Par;
import com.googlecode.jinahya.nica.util.ParME;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
//import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Headers {


//    /**
//     * The class for synchronized instances.
//     */
//    private static class SynchronizedHeaders extends Headers {
//
//
//        public SynchronizedHeaders(final Headers headers) {
//            super(headers.name, headers.codes, headers.aes, headers.hac);
//
//            this.mutex = headers;
//        }
//
//
//        //@Override
//        public void setHeaders(final HttpURLConnection connection) {
//            synchronized (mutex) {
//                super.setHeaders(connection);
//            }
//        }
//
//
//        //@Override
//        public Map getHeaders() {
//            synchronized (mutex) {
//                return super.getHeaders();
//            }
//        }
//
//
////        //@Override
////        public Codes getCodes() {
////            synchronized (mutex) {
////                return mutex.getCodes();
////            }
////        }
//        /**
//         * mutex.
//         */
//        private final Headers mutex;
//
//
//    }
//
//
//    /**
//     * Returns a synchronized (thread-safe) headers backed by the specified
//     * headers.
//     *
//     * @param headers the headers to be "wrapped" in a synchronized headers.
//     *
//     * @return a synchronized view of the specified headers.
//     */
//    public static Headers synchronizedHeaders(final Headers headers) {
//
//        if (headers == null) {
//            throw new IllegalArgumentException("null headers");
//        }
//
//        return new SynchronizedHeaders(headers);
//    }
    /**
     * Creates a new instance.
     *
     * @param name a {@link Par}-encoded nica names.
     * @param codes codes.
     * @param aes aes.
     * @param hac hac.
     */
    public Headers(final String name, final Codes codes, final Aes aes,
                   final Hac hac) {

        super();

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }

        if (codes == null) {
            throw new IllegalArgumentException("null codes");
        }

        if (aes == null) {
            throw new IllegalArgumentException("null aes");
        }

        if (hac == null) {
            throw new IllegalArgumentException("null hac");
        }

        this.name = name;
        this.codes = codes;
        this.aes = aes;
        this.hac = hac;
    }


    /**
     * Sets request headers on given
     * <code>connection</code>.
     *
     * @param connection connection.
     */
    public final void setHeaders(final HttpURLConnection connection) {

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        codes.putVolatileCode(CodeKeys.REQUEST_URL,
                              connection.getURL().toExternalForm());
        codes.putVolatileCode(CodeKeys.REQUEST_METHOD,
                              connection.getRequestMethod());

        final Iterator entries = getHeaders().entrySet().iterator();
        while (entries.hasNext()) {
            final Entry entry = (Entry) entries.next();
            connection.setRequestProperty(
                (String) entry.getKey(), (String) entry.getValue());
        }
    }


    /**
     * Generates request headers.
     *
     * @return a map of request headers
     */
    public final Map getHeaders() {

        return getHeaders(new HashMap(4));
    }


    /**
     * Put http request headers to given
     * <code>headers</code>.
     *
     * @param headers the map to be filled
     *
     * @return given map.
     */
    public final Map getHeaders(final Map headers) {

        if (headers == null) {
            throw new IllegalArgumentException("null headers");
        }

        // ----------------------------------------------------------- Nica-Name
        headers.put(HeaderFieldNames.NAME, name);

        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = Aes.newIv();
        headers.put(HeaderFieldNames.INIT, Hex.encodeToString(iv));

        // ----------------------------------------------------------- Nica-Code
        final byte[] base;
        try {
            base = ParME.encode(codes.getCodes(new java.util.Hashtable())).
                getBytes("US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
        final byte[] code = aes.encrypt(iv, base);
        headers.put(HeaderFieldNames.CODE, Hex.encodeToString(code));

        // ----------------------------------------------------------- Nica-Auth
        final byte[] auth = hac.authenticate(base);
        headers.put(HeaderFieldNames.AUTH, Hex.encodeToString(auth));

        return headers;
    }


    /**
     *
     * @param headers
     *
     * @return
     *
     * @deprecated
     */
    public final java.util.Hashtable getHeaders(
        final java.util.Hashtable headers) {

        return getHeaders(headers);
    }


    /**
     * Puts a constant code entry.
     *
     * @param key code key
     * @param value code value
     */
    public final void putConstantCode(final String key, final String value) {
        codes.putConstantCode(key, value);
    }


    /**
     * Puts a variable code entry.
     *
     * @param key code key
     * @param value code value
     *
     * @return previous value mapped to the key or null
     */
    public final String putVariableCode(final String key, final String value) {
        return codes.putVariableCode(key, value);
    }


    /**
     * Puts a volatile code entry.
     *
     * @param key code key
     * @param value code value
     *
     * @return previous value mapped to the key or null
     */
    public final String putVolatileCode(final String key, final String value) {
        return codes.putVolatileCode(key, value);
    }


    /**
     * name.
     */
    private final String name;


    /**
     * codes.
     */
    private final Codes codes;


    /**
     * aes.
     */
    private final Aes aes;


    /**
     * hac.
     */
    private final Hac hac;


}

