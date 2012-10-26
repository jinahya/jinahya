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
import com.googlecode.jinahya.nica.util.Nuo;
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
public abstract class NicaHeaders {


    private static class SynchronizedNicaHeaders extends NicaHeaders {


        public SynchronizedNicaHeaders(final NicaHeaders nicaHeaders) {
            super(nicaHeaders.name, nicaHeaders.aes, nicaHeaders.hac);

            this.nicaHeaders = nicaHeaders;
        }


        @Override
        public void setHeaders(final URLConnection connection) {
            synchronized (nicaHeaders) {
                super.setHeaders(connection);
            }
        }


        @Override
        public Map<String, String> getHeaders() {
            synchronized (nicaHeaders) {
                return super.getHeaders();
            }
        }


        @Override
        public void putConstantCode(final String key, final String value) {
            synchronized (nicaHeaders) {
                super.putConstantCode(key, value);
            }
        }


        @Override
        public String putVariableCode(final String key, final String value) {
            synchronized (nicaHeaders) {
                return super.putVariableCode(key, value);
            }
        }


        private final NicaHeaders nicaHeaders;


    }


    public static NicaHeaders newSynchronizedInstance(
        final NicaHeaders nicaHeaders) {

        if (nicaHeaders == null) {
            throw new IllegalArgumentException("null nicaHeaders");
        }

        return new SynchronizedNicaHeaders(nicaHeaders);
    }


    /**
     * Creates a new instance.
     *
     * @param name
     * @param aes
     * @param hac
     */
    public NicaHeaders(final String name, final Aes aes, final Hac hac) {

        super();

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }

        if (aes == null) {
            throw new IllegalArgumentException("null aes");
        }

        if (hac == null) {
            throw new IllegalArgumentException("null hac");
        }

        this.name = name;
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
        headers.put(NicaHeader.NAME.fieldName(), name);


        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = Aes.newIv();
        headers.put(NicaHeader.INIT.fieldName(), Hex.encodeToString(iv));


        // ----------------------------------------------------------- Nica-Code
        final long requestTimestamp = System.currentTimeMillis();
        variableCodes.put(NicaCode.REQUEST_TIMESTAMP.name(),
                          Long.toString(requestTimestamp));

        final long requestNonce = Nuo.generate(requestTimestamp);
        variableCodes.put(NicaCode.REQUEST_NONCE.name(),
                          Long.toString(requestNonce));

        final Map<String, String> codes = new HashMap<String, String>(
            constantCodes.size() + variableCodes.size());
        codes.putAll(variableCodes);
        variableCodes.clear();
        codes.putAll(constantCodes);

        final byte[] base;
        try {
            base = Par.encode(codes).getBytes("US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
        final byte[] code = aes.encrypt(iv, base);
        headers.put(NicaHeader.CODE.fieldName(), Hex.encodeToString(code));


        // ----------------------------------------------------------- Nica-Auth
        final byte[] auth = hac.authenticate(base);
        headers.put(NicaHeader.AUTH.fieldName(), Hex.encodeToString(auth));

        return headers;
    }


    /**
     * Puts a constant code.
     *
     * @param key key
     * @param value value
     */
    public void putConstantCode(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        if (constantCodes.containsKey(key)) {
            throw new IllegalArgumentException(
                "key(" + key + ") is already occupied");
        }

        constantCodes.put(key, value);
    }


    /**
     * Puts a variable code. Note that variable codes are cleared after they
     * used.
     *
     * @param key key
     * @param value value
     *
     * @return previous value
     */
    public String putVariableCode(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        return variableCodes.put(key, value);
    }


    /**
     * name.
     */
    private final String name;


    private final Aes aes;


    private final Hac hac;


    /**
     * constant codes.
     */
    private final Map<String, String> constantCodes =
        new HashMap<String, String>();


    /**
     * variable codes.
     */
    private final transient Map<String, String> variableCodes =
        new HashMap<String, String>();


}

