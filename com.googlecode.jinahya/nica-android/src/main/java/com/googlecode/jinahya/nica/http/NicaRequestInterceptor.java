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


package com.googlecode.jinahya.nica.http;


import android.content.Context;
import android.provider.Settings.Secure;
import com.googlecode.jinahya.nica.Code;
import com.googlecode.jinahya.nica.Header;
import com.googlecode.jinahya.nica.Platform;
import com.googlecode.jinahya.nica.util.AES;
import com.googlecode.jinahya.nica.util.AESJCE;
import com.googlecode.jinahya.nica.util.HEX;
import com.googlecode.jinahya.nica.util.KVP;
import com.googlecode.jinahya.nica.util.MACJCE;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Random;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class NicaRequestInterceptor implements HttpRequestInterceptor {


    /**
     * random.
     */
    private static final Random RANDOM = new SecureRandom();


    /**
     * Creates a new instance.
     *
     * @param context context
     * @param key encryption key
     * @param names names
     */
    public NicaRequestInterceptor(final Context context, final byte[] key,
                                  final Map<String, String> names) {

        super();

        if (context == null) {
            throw new IllegalArgumentException("null context");
        }

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (key.length != AES.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != " + AES.KEY_SIZE_IN_BYTES);
        }

        if (names == null) {
            throw new IllegalArgumentException("null names");
        }

        this.context = context;
        this.key = key.clone();
        this.name = KVP.encode(names);

        final Locale locale = Locale.getDefault();
        try {
            putConstantCode(Code.USER_LANGUAGE3.name(),
                            locale.getISO3Language());
        } catch (MissingResourceException mre) {
        }
        constantCodes.put(Code.USER_LANGUAGE2.name(), locale.getLanguage());
//        constantCodes.put(Code.USER_LANGUAGE.name(),
//                          locale.getDisplayLanguage(Locale.ENGLISH));
        try {
            constantCodes.put(Code.USER_COUNTRY3.name(),
                              locale.getISO3Country());
        } catch (MissingResourceException mre) {
        }
        constantCodes.put(Code.USER_COUNTRY2.name(), locale.getLanguage());
//        constantCodes.put(Code.USER_COUNTRY.name(),
//                          locale.getDisplayCountry(Locale.ENGLISH));

        constantCodes.put(Code.DEVICE_ID.name(),
                          Secure.getString(this.context.getContentResolver(),
                                           Secure.ANDROID_ID));

        constantCodes.put(Code.PLATFORM_ID.name(), Platform.ANDROID.id());
    }


    @Override
    public void process(final HttpRequest request, final HttpContext context)
        throws HttpException, IOException {

        // ----------------------------------------------------------- Nica-Name
        request.setHeader(Header.NAME.fieldName(), name);

        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = new byte[AES.KEY_SIZE_IN_BYTES];
        RANDOM.nextBytes(iv);
        request.setHeader(Header.INIT.fieldName(), HEX.encodeToString(iv));

        // ----------------------------------------------------------- Nica-Code
        variableCodes.put(Code.SYSTEM_MILLIS.name(),
                          Long.toString(System.currentTimeMillis()));
        final Map<String, String> codes = new HashMap<String, String>(
            constantCodes.size() + variableCodes.size());
        codes.putAll(variableCodes);
        codes.putAll(constantCodes);
        final byte[] base;
        try {
            base = KVP.encode(codes).getBytes("US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
        final byte[] code = new AESJCE(key).encrypt(iv, base);
        request.setHeader(Header.CODE.fieldName(), HEX.encodeToString(code));
        variableCodes.clear();

        // ----------------------------------------------------------- Nica-Auth
        final byte[] auth = new MACJCE(key).authenticate(base);
        request.setHeader(Header.AUTH.fieldName(), HEX.encodeToString(auth));
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
     * context.
     */
    protected final Context context;


    /**
     * key.
     */
    private final byte[] key;


    /**
     * name.
     */
    private final String name;


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

