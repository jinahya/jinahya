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


package com.googlecode.jinahya.nica.client;


import com.googlecode.jinahya.nica.Code;
import com.googlecode.jinahya.nica.Header;
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
public abstract class NicaClient {


    private static class PrivateNicaClient extends NicaClient {


        public PrivateNicaClient(final NicaClient nicaClient) {
            super(nicaClient.name, nicaClient.aes, nicaClient.hac);
        }


    }


    public static NicaClient newSynchronizedInstance(
        final NicaClient nicaClient) {

        if (nicaClient == null) {
            throw new IllegalArgumentException("null nicaClient");
        }

        return new PrivateNicaClient(nicaClient) {


            @Override
            public final synchronized void setHeaders(
                final URLConnection connection) {
                super.setHeaders(connection);
            }


            @Override
            public final synchronized Map<String, String> getHeaders() {
                return super.getHeaders();
            }


            @Override
            public final synchronized void putConstantCode(
                final String key, final String value) {
                super.putConstantCode(key, value);
            }


            @Override
            public final synchronized String putVariableCode(
                final String key, final String value) {
                return super.putVariableCode(key, value);
            }


        };
    }


    /**
     * Creates a new instance.
     *
     * @param name
     * @param aes
     * @param hac
     */
    public NicaClient(final String name, final Aes aes, final Hac hac) {

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
        headers.put(Header.NAME.fieldName(), name);


        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = Aes.newIv();
        headers.put(Header.INIT.fieldName(), Hex.encodeToString(iv));


        // ----------------------------------------------------------- Nica-Code
        final long requestTimestamp = System.currentTimeMillis();
        variableCodes.put(Code.REQUEST_TIMESTAMP.name(),
                          Long.toString(requestTimestamp));

        final long requestNonce = Nuo.generate(requestTimestamp);
        variableCodes.put(Code.REQUEST_NONCE.name(),
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
        headers.put(Header.CODE.fieldName(), Hex.encodeToString(code));


        // ----------------------------------------------------------- Nica-Auth
        final byte[] auth = hac.authenticate(base);
        headers.put(Header.AUTH.fieldName(), Hex.encodeToString(auth));

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

