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
import com.googlecode.jinahya.nica.util.Hex;
import com.googlecode.jinahya.nica.util.Par;
import com.googlecode.jinahya.nica.util.Hac;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Headers {


    /**
     * random.
     */
    //private static final Random RANDOM = new SecureRandom();
    private static ThreadLocal<Random> RANDOM = new ThreadLocal<Random>() {


        @Override
        protected Random initialValue() {
            return new Random(
                System.currentTimeMillis()
                + Thread.currentThread().getId() * 86400000L);
        }


    };


    /**
     * Creates a new instance.
     *
     * @param name NICA name; must be a PVK encoded String
     * @param aes an instance of AES
     * @param mac an instance of MAC
     */
    protected Headers(final String name, final Aes aes, final Hac mac) {

        super();

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (aes == null) {
            throw new IllegalArgumentException("null aes");
        }

        if (mac == null) {
            throw new IllegalArgumentException("null mac");
        }

        this.name = name;
        this.aes = aes;
        this.mac = mac;
    }


    /**
     * Returns HTTP headers.
     *
     * @return HTTP headers.
     */
    protected Map getHeaders() {

        final Map headers = new HashMap(4);

        // ----------------------------------------------------------- Nica-Name
        headers.put("Nica-Name", name);

        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = new byte[Aes.BLOCK_SIZE_IN_BYTES];
        RANDOM.get().nextBytes(iv);
        headers.put("Nica-Init", Hex.encodeToString(iv));

        // ----------------------------------------------------------- Nica-Code
        final String platformId =
            (String) constantCodes.get(Code.PLATFORM_ID.name());
        variableCodes.put(Code.REQUEST_TIMESTAMP.name(),
                          Long.toString(System.currentTimeMillis()));
//        final String platformId = variableCodes.get(Code.RE)
        final Map codes = new HashMap(
            constantCodes.size() + variableCodes.size());
        codes.putAll(constantCodes);
        codes.putAll(variableCodes);
        variableCodes.clear();
        final byte[] base;
        try {
            base = Par.encode(codes).getBytes("US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
        final byte[] code = aes.encrypt(iv, base);
        headers.put("Nica-Code", Hex.encodeToString(code));

        // ----------------------------------------------------------- Nica-Auth
        final byte[] auth = mac.authenticate(base);
        headers.put("Nica-Auth", Hex.encodeToString(auth));

        return headers;
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

        final Iterator entries = getHeaders().entrySet().iterator();
        while (entries.hasNext()) {
            final Entry entry = (Entry) entries.next();
            final String fieldName = (String) entry.getKey();
            final String fieldValue = (String) entry.getValue();
            connection.setRequestProperty(fieldName, fieldValue);
        }
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
                "key(" + key + ") is already oocupied");
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

        return (String) variableCodes.put(key, value);
    }


    /**
     * name.
     */
    private final String name;


    private final Aes aes;


    private final Hac mac;


    /**
     * constant codes.
     */
    private final Map constantCodes = new HashMap();


    /**
     * variable codes.
     */
    private final transient Map variableCodes = new HashMap();


}

