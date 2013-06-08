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


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractHeaders {


//    /**
//     *
//     * @param entries
//     * @param map
//     */
//    private static void copy(final String[] entries, final Map map) {
//
//        if (entries == null) {
//            throw new IllegalArgumentException("null entries");
//        }
//
//        if ((entries.length & 0x01) == 1) {
//            throw new IllegalArgumentException("entries.length is not even");
//        }
//
//        if (map == null) {
//            throw new IllegalArgumentException("null map");
//        }
//
//        for (int i = 0; i < entries.length; i += 2) {
//            map.put(entries[i], entries[i + 1]);
//        }
//    }
//
//
//    /**
//     *
//     * @param entries
//     * @param table
//     */
//    private static void copy(final String[] entries, final Hashtable table) {
//
//        if (entries == null) {
//            throw new IllegalArgumentException("null entries");
//        }
//
//        if ((entries.length & 0x01) == 1) {
//            throw new IllegalArgumentException("entries.length is not even");
//        }
//
//        if (table == null) {
//            throw new IllegalArgumentException("null table");
//        }
//
//        for (int i = 0; i < entries.length; i += 2) {
//            table.put(entries[i], entries[i + 1]);
//        }
//    }
    /**
     * Creates a new instance.
     *
     * @param name a {@link Par}-encoded nica names.
     * @param codes codes.
     * @param aes aes.
     * @param hac hac.
     */
    public AbstractHeaders(final String name, final AbstractCodes codes,
                           final Aes aes, final Hac hac) {

        super();

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

//        if (name.isEmpty()) {
//            throw new IllegalArgumentException("empty name");
//        }

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


//    /**
//     * Sets HTTP request headers on given
//     * <code>connection</code>.
//     *
//     * @param connection connection.
//     */
//    public final void setHeaders(final HttpURLConnection connection) {
//
//        if (connection == null) {
//            throw new IllegalArgumentException("null connection");
//        }
//
//        codes.putVolatileEntry(CodeKeys.REQUEST_METHOD,
//                               connection.getRequestMethod());
//
//        URL url = connection.getURL();
//        final String protocol = url.getProtocol();
//        final String host = url.getHost();
//        int port = url.getPort();
//        if (port == url.getDefaultPort()) {
//            port = -1;
//        }
//        final String path = url.getPath();
//        url = new URL(protocol, host, port, path);
//
//
//        codes.putVolatileEntry(CodeKeys.REQUEST_URL,
//                               connection.getURL().toExternalForm());
//
//
//        final String[] fieldPairs = getFieldPairs();
//        for (int i = 0; i < fieldPairs.length; i += 2) {
//            connection.setRequestProperty(fieldPairs[i], fieldPairs[i + 1]);
//        }
//    }
//
//
//    /**
//     * Sets HTTP request headers on given
//     * <code>connection</code>.
//     *
//     * @param connection connection
//     *
//     * @throws IOException if an I/O error occurs.
//     */
//    public final void setHeaders(final HttpConnection connection)
//        throws IOException {
//
//        if (connection == null) {
//            throw new IllegalArgumentException("null connection");
//        }
//
//        codes.putVolatileEntry(CodeKeys.REQUEST_URL, connection.getURL());
//
//        codes.putVolatileEntry(CodeKeys.REQUEST_METHOD,
//                               connection.getRequestMethod());
//
//        final String[] entries = getFieldPairs();
//        for (int i = 0; i < entries.length; i += 2) {
//            connection.setRequestProperty(entries[i], entries[i + 1]);
//        }
//    }
//
//
//    /**
//     * Put http request headers to given
//     * <code>headers</code>.
//     *
//     * @param entries the map to be filled
//     *
//     * @return given map.
//     */
//    public final void getEntries(final Map entries) {
//
//        if (entries == null) {
//            throw new IllegalArgumentException("null entries");
//        }
//
//        copy(getFieldPair(), entries);
//    }
//
//
//    /**
//     * Put http request headers to given
//     * <code>headers</code>.
//     *
//     * @param entries the hashtable to be filled.
//     *
//     * @deprecated Use {@link #getEntries(java.util.Map)}.
//     */
//    public final void getEntries(final Hashtable entries) {
//
//        if (entries == null) {
//            throw new IllegalArgumentException("null entries");
//        }
//
//        copy(getFieldPair(), entries);
//    }
    /**
     *
     * @return
     */
    protected final String[] getFieldPairs() {

        final String[] headers = new String[8];
        int index = 0;

        // ----------------------------------------------------------- Nica-Name
        headers[index++] = HeaderNames.NAME;
        headers[index++] = name;

        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = Aes.newIv();
        headers[index++] = HeaderNames.INIT;
        headers[index++] = Hex.encodeToString(iv);

        // ----------------------------------------------------------- Nica-Base
        final byte[] base;
        try {
            base = codes.getBase().getBytes("US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("'US-ASCII' not supported?");
        }

        // ----------------------------------------------------------- Nica-Code
        final byte[] code = aes.encrypt(iv, base);
        headers[index++] = HeaderNames.CODE;
        headers[index++] = Hex.encodeToString(code);

        // ----------------------------------------------------------- Nica-Auth
        final byte[] auth = hac.authenticate(base);
        headers[index++] = HeaderNames.AUTH;
        headers[index++] = Hex.encodeToString(auth);

        return headers;
    }


    public AbstractCodes getCodes() {
        return codes;
    }


    /**
     * name.
     */
    protected final String name;


    /**
     * codes.
     */
    private final AbstractCodes codes;


    /**
     * aes.
     */
    protected final Aes aes;


    /**
     * hac.
     */
    protected final Hac hac;


}

