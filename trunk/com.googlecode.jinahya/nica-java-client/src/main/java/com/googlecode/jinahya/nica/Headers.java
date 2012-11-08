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
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Map;
import javax.microedition.io.HttpConnection;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpUriRequest;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Headers extends Codes {


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
     *
     * @param entries
     * @param map
     */
    private static void copy(final String[] entries, final Map map) {

        if (entries == null) {
            throw new IllegalArgumentException("null entries");
        }

        if ((entries.length & 0x01) == 1) {
            throw new IllegalArgumentException("entries.length is not even");
        }

        if (map == null) {
            throw new IllegalArgumentException("null map");
        }

        for (int i = 0; i < entries.length; i += 2) {
            map.put(entries[i], entries[i + 1]);
        }
    }


    /**
     *
     * @param entries
     * @param table
     */
    private static void copy(final String[] entries, final Hashtable table) {

        if (entries == null) {
            throw new IllegalArgumentException("null entries");
        }

        if ((entries.length & 0x01) == 1) {
            throw new IllegalArgumentException("entries.length is not even");
        }

        if (table == null) {
            throw new IllegalArgumentException("null table");
        }

        for (int i = 0; i < entries.length; i += 2) {
            table.put(entries[i], entries[i + 1]);
        }
    }


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
     * Sets HTTP request headers on given
     * <code>connection</code>.
     *
     * @param connection connection.
     */
    public final void setHeaders(final HttpURLConnection connection) {

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        codes.putVolatileEntry(CodeKeys.REQUEST_URL,
                               connection.getURL().toExternalForm());

        codes.putVolatileEntry(CodeKeys.REQUEST_METHOD,
                               connection.getRequestMethod());

        final String[] entries = getEntriesAsArray();
        for (int i = 0; i < entries.length; i += 2) {
            connection.setRequestProperty(entries[i], entries[i + 1]);
        }
    }


    /**
     * Sets HTTP request headers on given
     * <code>connection</code>.
     *
     * @param connection connection
     * @throws IOException if an I/O error occurs.
     */
    public final void setHeaders(final HttpConnection connection)
        throws IOException {

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        codes.putVolatileEntry(CodeKeys.REQUEST_URL, connection.getURL());

        codes.putVolatileEntry(CodeKeys.REQUEST_METHOD,
                               connection.getRequestMethod());

        final String[] entries = getEntriesAsArray();
        for (int i = 0; i < entries.length; i += 2) {
            connection.setRequestProperty(entries[i], entries[i + 1]);
        }
    }


    /**
     * Sets HTTP request headers on given
     * <code>request</code>.
     *
     * @param request request
     *
     * @deprecated Use {@link #setHeaders(java.net.HttpURLConnection) }
     */
    public void setHeaders(final HttpUriRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("null request");
        }

        try {
            putVolatileEntry(Code.REQUEST_URL.key(),
                             request.getURI().toURL().toExternalForm());
        } catch (MalformedURLException murle) {
            throw new RuntimeException(murle);
        }
        putVolatileEntry(Code.REQUEST_METHOD.key(), request.getMethod());

        final String[] entries = getEntriesAsArray();
        for (int i = 0; i < entries.length; i += 2) {
            request.setHeader(entries[i], entries[i + 1]);
        }
    }


    /**
     * Sets HTTP request headers on given
     * <code>request</code>.
     *
     * @param request request
     *
     * @deprecated Use {@link #setHeaders(java.net.HttpURLConnection) }
     */
    public void setHeaders(final HttpRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("null request");
        }

        if (request instanceof HttpUriRequest) {
            setHeaders((HttpUriRequest) request);
            return;
        }

        final String[] entries = getEntriesAsArray();
        for (int i = 0; i < entries.length; i += 2) {
            request.setHeader(entries[i], entries[i + 1]);
        }
    }


    /**
     * Put http request headers to given
     * <code>headers</code>.
     *
     * @param entries the map to be filled
     *
     * @return given map.
     */
    public final void getEntries(final Map entries) {

        if (entries == null) {
            throw new IllegalArgumentException("null entries");
        }

        copy(getEntriesAsArray(), entries);
    }


    /**
     * Put http request headers to given
     * <code>headers</code>.
     *
     * @param entries the hashtable to be filled.
     * @deprecated Use {@link #getEntries(java.util.Map)}.
     */
    public final void getEntries(final Hashtable entries) {

        if (entries == null) {
            throw new IllegalArgumentException("null entries");
        }

        copy(getEntriesAsArray(), entries);
    }


    /**
     *
     * @return
     */
    protected final String[] getEntriesAsArray() {

        final String[] headers = new String[8];
        int index = 0;

        // ----------------------------------------------------------- Nica-Name
        headers[index++] = HeaderFieldNames.NAME;
        headers[index++] = name;

        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = Aes.newIv();
        headers[index++] = HeaderFieldNames.INIT;
        headers[index++] = Hex.encodeToString(iv);

        // ----------------------------------------------------------- Nica-Base
        final byte[] base = getBase(codes);

        // ----------------------------------------------------------- Nica-Code
        final byte[] code = aes.encrypt(iv, base);
        headers[index++] = HeaderFieldNames.CODE;
        headers[index++] = Hex.encodeToString(code);

        // ----------------------------------------------------------- Nica-Auth
        final byte[] auth = hac.authenticate(base);
        headers[index++] = HeaderFieldNames.AUTH;
        headers[index++] = Hex.encodeToString(auth);

        return headers;
    }


    /**
     *
     * @param codes
     * @return
     */
    protected abstract byte[] getBase(Codes codes);


    //@Override
    public final void putConstantEntry(final String key, final String value) {
        codes.putConstantEntry(key, value);
    }


    //@Override
    public final String putVariableEntry(final String key, final String value) {
        return codes.putVariableEntry(key, value);
    }


    //@Override
    public final String putVolatileEntry(final String key, final String value) {
        return codes.putVolatileEntry(key, value);
    }


    /**
     * name.
     */
    protected final String name;


    /**
     * codes.
     */
    private final Codes codes;


    /**
     * aes.
     */
    protected final Aes aes;


    /**
     * hac.
     */
    protected final Hac hac;


}

