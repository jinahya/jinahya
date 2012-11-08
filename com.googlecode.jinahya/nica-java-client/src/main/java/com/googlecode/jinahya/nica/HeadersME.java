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
import com.googlecode.jinahya.nica.util.AesBC;
import com.googlecode.jinahya.nica.util.Hac;
import com.googlecode.jinahya.nica.util.HacBC;
import com.googlecode.jinahya.nica.util.ParME;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import javax.microedition.io.HttpConnection;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HeadersME extends Headers {


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
     * @param names
     * @param key
     * @return
     */
    public static Headers newInstance(final Hashtable names, final byte[] key) {

        return new HeadersME(ParME.encode(names), new CodesME(), new AesBC(key),
                             new HacBC(key));
    }


    /**
     *
     * @param names
     * @param codes
     * @param key
     * @return
     */
    public static Headers newInstance(final Hashtable names, final Codes codes,
                                      final byte[] key) {

        return new HeadersME(ParME.encode(names), codes, new AesBC(key),
                             new HacBC(key));
    }


    /**
     *
     * @param name
     * @param codes
     * @param aes
     * @param hac
     */
    public HeadersME(final String name, final Codes codes, final Aes aes,
                     final Hac hac) {

        super(name, codes, aes, hac);
    }


    public final void setHeaders(final HttpConnection connection)
        throws IOException {

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        codes.putVolatileCode(CodeKeys.REQUEST_URL, connection.getURL());

        codes.putVolatileCode(CodeKeys.REQUEST_METHOD,
                              connection.getRequestMethod());

        final String[] entries = getEntries();
        for (int i = 0; i < entries.length; i += 2) {
            connection.setRequestProperty(entries[i], entries[i + 1]);
        }
    }


    /**
     * Generates request headers.
     *
     * @return a map of request headers
     */
    public final Hashtable getHeaders() {

        final Hashtable headers = new Hashtable(4);

        getHeaders(headers);

        return headers;
    }


    /**
     * Put http request headers to given
     * <code>headers</code>.
     *
     * @param headers the hashtable to be filled.
     */
    public final void getHeaders(final Hashtable headers) {

        if (headers == null) {
            throw new IllegalArgumentException("null headers");
        }

        copy(getEntries(), headers);
    }


    //@Override
    protected byte[] getBase(final Codes codes) {
        try {
            return ParME.encode(((CodesME) codes).getCodes()).
                getBytes("US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


}

