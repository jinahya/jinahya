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
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Headers {


    /**
     * synchronized class.
     */
    private static class SynchronizedHeaders extends Headers {


        public SynchronizedHeaders(final Headers headers) {
            super(headers.name, headers.codes, headers.aes, headers.hac);

            this.headers = headers;
        }


        //@Override
        public void setHeaders(final URLConnection connection) {
            synchronized (headers) {
                super.setHeaders(connection);
            }
        }


        //@Override
        public Map getHeaders() {
            synchronized (headers) {
                return super.getHeaders();
            }
        }


        /**
         * headers.
         */
        private final Headers headers;


    }


    /**
     * Returns a synchronized (thread-safe) map backed by the specified headers.
     *
     * @param headers the headers to be "wrapped" in a synchronized headers.
     *
     * @return a synchronized view of the specified headers.
     */
    public static Headers synchronizedHeaders(final Headers headers) {

        if (headers == null) {
            throw new IllegalArgumentException("null headers");
        }

        return new SynchronizedHeaders(headers);
    }


    /**
     * Creates a new instance.
     *
     * @param name a Par encoded nica names
     * @param codes codes
     * @param aes aes
     * @param hac hac
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
     * @param connection connection
     */
    public void setHeaders(final URLConnection connection) {

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        for (final Iterator entries = getHeaders().entrySet().iterator();
             entries.hasNext();) {
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
    //@SuppressWarnings("unchecked")
    public Map getHeaders() {

        final Map headers = new HashMap();


        // ----------------------------------------------------------- Nica-Name
        headers.put(HeaderFieldNames.NAME, name);


        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = Aes.newIv();
        headers.put(HeaderFieldNames.INIT, Hex.encodeToString(iv));


        // ----------------------------------------------------------- Nica-Code
        final byte[] base;
        try {
            base = Par.encode(codes.getCodes()).getBytes("US-ASCII");
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

