/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


import com.googlecode.jinahya.nica.CodeKeys;
import com.googlecode.jinahya.nica.HeaderNames;
import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.Hac;
import com.googlecode.jinahya.nica.util.Hex;
import com.googlecode.jinahya.nica.util.Par;
import java.net.HttpURLConnection;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import org.apache.http.HttpRequest;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class AndroidNicaBuilder extends NicaBuilder {


    protected AndroidNicaBuilder() {

        super();
    }


    @Override
    public NicaBuilder putConstantCode(final String key, final String value) {

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (value == null) {
            throw new NullPointerException("value");
        }

        if (constantCodes.containsKey(key)) {
            throw new IllegalStateException("key already occupied: " + key);
        }

        final String previous = constantCodes.put(key, value);

        return this;
    }


    @Override
    public NicaBuilder putVariableCode(final String key, final String value) {

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (value == null) {
            throw new NullPointerException("value");
        }

        final String previous = variableCodes.put(key, value);

        return this;
    }


    @Override
    public NicaBuilder putVolatileCode(final String key, final String value) {

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (value == null) {
            throw new NullPointerException("value");
        }

        final String previous = volatileCodes.put(key, value);

        return this;
    }


    @Override
    public void build(final Object object) {

        if (object == null) {
            throw new NullPointerException("object");
        }

        final Map<String, String> headers = headers();

        if (object instanceof HttpURLConnection) {
            final HttpURLConnection connection = (HttpURLConnection) object;
            for (Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            return;
        }

        if (object instanceof HttpRequest) {
            final HttpRequest request = (HttpRequest) object;
            for (Entry<String, String> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
            return;
        }

        throw new IllegalArgumentException("unsupported: " + object);
    }


    /**
     *
     * @return a map of request header names and values.
     */
    private Map<String, String> headers() {

        final Locale locale = Locale.getDefault();

        try {
            final String userCountry3 = locale.getISO3Country();
            variableCodes.put(CodeKeys.USER_COUNTRY3, userCountry3);
        } catch (MissingResourceException mre) {
            variableCodes.put(CodeKeys.USER_COUNTRY3, "missing");
        }

        final String userCountry2 = locale.getCountry();
        variableCodes.put(CodeKeys.USER_COUNTRY2, userCountry2);

        final String userCountry = locale.getDisplayCountry(Locale.ENGLISH);
        variableCodes.put(CodeKeys.USER_COUNTRY, userCountry);

        try {
            final String userLanguage3 = locale.getISO3Language();
            variableCodes.put(CodeKeys.USER_LANGUAGE3, userLanguage3);
        } catch (MissingResourceException mre) {
            variableCodes.put(CodeKeys.USER_LANGUAGE3, "missing");
        }

        final String userLanguage2 = locale.getLanguage();
        variableCodes.put(CodeKeys.USER_LANGUAGE2, userLanguage2);

        final String userLanguage = locale.getDisplayLanguage(Locale.ENGLISH);
        variableCodes.put(CodeKeys.USER_LANGUAGE, userLanguage);

        final Map<String, String> map = new HashMap<String, String>(4);

        // ----------------------------------------------------------- Nica-Name
        map.put(HeaderNames.NAME, Par.encode(names));

        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = new byte[Aes.BLOCK_SIZE_IN_BYTES];
        new SecureRandom().nextBytes(iv);
        map.put(HeaderNames.INIT, Hex.encodeToString(iv));


        // ----------------------------------------------------------- Nica-Code
        final Map<String, String> codes = new HashMap<String, String>(
            volatileCodes.size() + variableCodes.size() + constantCodes.size());
        codes.putAll(volatileCodes);
        codes.putAll(variableCodes);
        codes.putAll(constantCodes);
        final String base = Par.encode(codes);
        final String code = aes.encryptToString(iv, base);
        map.put(HeaderNames.BASE, base);
        map.put(HeaderNames.CODE, code);

        // ----------------------------------------------------------- Nica-Auth
        final String auth = hac.authenticateToString(base);
        map.put(HeaderNames.AUTH, auth);

        volatileCodes.clear();

        return map;
    }


    final transient Map<String, String> constantCodes =
        new HashMap<String, String>();


    final transient Map<String, String> variableCodes =
        new HashMap<String, String>();


    final transient Map<String, String> volatileCodes =
        new HashMap<String, String>();


    final transient Map<String, String> names = new HashMap<String, String>();


    transient Aes aes;


    transient Hac hac;


}
