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
import java.net.HttpURLConnection;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractHeaders extends AbstractCodes {


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
    protected static void copy(final String[] entries,
                               final java.util.Map map) {

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


    protected static void copy(final String[] entries,
                               final java.util.Hashtable table) {

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
    public AbstractHeaders(final String name, final AbstractCodes codes,
                           final Aes aes, final Hac hac) {

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

        final String[] entries = getEntries();
        for (int i = 0; i < entries.length; i += 2) {
            connection.setRequestProperty(entries[i], entries[i + 1]);
        }
    }


    /**
     *
     * @return
     */
    protected final String[] getEntries() {

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
    protected abstract byte[] getBase(AbstractCodes codes);


    //@Override
    public final void putConstantCode(final String key, final String value) {
        codes.putConstantCode(key, value);
    }


    //@Override
    public final String putVariableCode(final String key, final String value) {
        return codes.putVariableCode(key, value);
    }


    //@Override
    public final String putVolatileCode(final String key, final String value) {
        return codes.putVolatileCode(key, value);
    }


    /**
     * name.
     */
    protected final String name;


    /**
     * codes.
     */
    protected final AbstractCodes codes;


    /**
     * aes.
     */
    protected final Aes aes;


    /**
     * hac.
     */
    protected final Hac hac;


}

