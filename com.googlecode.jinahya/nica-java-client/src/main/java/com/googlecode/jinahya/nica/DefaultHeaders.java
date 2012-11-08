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
import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.Hac;
import com.googlecode.jinahya.nica.util.HacJCE;
import com.googlecode.jinahya.nica.util.Par;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class DefaultHeaders extends Headers {


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
     */
    public static Headers newInstance(final Map<String, String> names,
                                      final byte[] key) {

        if (names == null) {
            throw new IllegalArgumentException("null names");
        }

        if (names.isEmpty()) {
            throw new IllegalArgumentException("empty names");
        }

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (key.length != Aes.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != " + Aes.KEY_SIZE_IN_BYTES);
        }

        return new DefaultHeaders(Par.encode(names), new DefaultCodes(),
                                  new AesJCE(key), new HacJCE(key));
    }


    public DefaultHeaders(final String name, final Codes codes, final Aes aes,
                          final Hac hac) {
        super(name, codes, aes, hac);
    }


    /**
     * Generates request headers.
     *
     * @return a map of request headers
     */
    public final Map<String, String> getHeaders() {

        final Map<String, String> headers = new HashMap<String, String>(4);

        getHeaders(headers);

        return headers;
    }


    /**
     * Put http request headers to given
     * <code>headers</code>.
     *
     * @param headers the map to be filled
     *
     * @return given map.
     */
    public final void getHeaders(final Map<String, String> headers) {

        if (headers == null) {
            throw new IllegalArgumentException("null headers");
        }

        final String[] entries = getEntries();
        for (int i = 0; i < entries.length; i += 2) {
            headers.put(entries[i], entries[i + 1]);
        }
    }


    @Override
    protected byte[] getBase(final Codes codes) {
        try {
            return Par.encode(((DefaultCodes) codes).getCodes()).getBytes("US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


}

