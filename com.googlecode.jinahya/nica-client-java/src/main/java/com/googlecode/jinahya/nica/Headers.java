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
import java.util.Map;
import java.util.Map.Entry;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Headers {


    private static class SynchronizedHeaders extends Headers {


        public SynchronizedHeaders(final Headers headers) {
            super(headers.name, headers.codes, headers.aes, headers.hac);

            this.headers = headers;
        }


        @Override
        public void setHeaders(final URLConnection connection) {
            synchronized (headers) {
                super.setHeaders(connection);
            }
        }


        @Override
        public Map<String, String> getHeaders() {
            synchronized (headers) {
                return super.getHeaders();
            }
        }


        private final Headers headers;


    }


    public static Headers synchronizedHeaders(final Headers headers) {

        if (headers == null) {
            throw new IllegalArgumentException("null headers");
        }

        return new SynchronizedHeaders(headers);
    }


    /**
     * Creates a new instance.
     *
     * @param name
     * @param codes
     * @param aes
     * @param hac
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

        for (Entry<String, String> header : getHeaders().entrySet()) {
            connection.setRequestProperty(header.getKey(), header.getValue());
        }
    }


    /**
     * Generates request headers.
     *
     * @return a map of request headers
     */
    public Map<String, String> getHeaders() {

        final Map<String, String> headers = new HashMap<String, String>();


        // ----------------------------------------------------------- Nica-Name
        headers.put(Header.NAME.fieldName(), name);


        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = Aes.newIv();
        headers.put(Header.INIT.fieldName(), Hex.encodeToString(iv));


        // ----------------------------------------------------------- Nica-Code
        final byte[] base;
        try {
            base = Par.encode(codes.getCodes()).getBytes("US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
        final byte[] code = aes.encrypt(iv, base);
        headers.put(Header.CODE.fieldName(), Hex.encodeToString(code));


        // ----------------------------------------------------------- Nica-Auth
        final byte[] auth = hac.authenticate(base);
        headers.put(Header.AUTH.fieldName(), Hex.encodeToString(auth));

        return headers;
    }


    /**
     * name.
     */
    private final String name;


    private final Codes codes;


    private final Aes aes;


    private final Hac hac;


}

