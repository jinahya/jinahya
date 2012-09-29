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
import com.googlecode.jinahya.nica.util.AES;
import com.googlecode.jinahya.nica.util.AESJCE;
import com.googlecode.jinahya.nica.util.HEX;
import com.googlecode.jinahya.nica.util.KVP;
import com.googlecode.jinahya.nica.util.MACJCE;
import java.io.IOException;
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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class NicaRequestInterceptor implements HttpRequestInterceptor {


    /**
     * random.
     */
    private static final Random RANDOM = new SecureRandom();


    private static final Locale EN_US = new Locale("en", "US");


    /**
     * Creates a new instance.
     *
     * @param context context
     * @param key keys
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
                "key.length(" + key.length + ") != KEY_SIZE_IN_BYTES("
                + AES.KEY_SIZE_IN_BYTES + ")");
        }

        if (names == null) {
            throw new IllegalArgumentException("null names");
        }

        if (names.isEmpty()) {
            throw new IllegalArgumentException("empty names");
        }

        this.context = context;
        this.key = key.clone();
        this.name = KVP.encode(names);

        final Locale locale = Locale.getDefault();
        codes.put(Code.USER_LANGUAGE.name(),
                  locale.getDisplayLanguage(Locale.ENGLISH));
        codes.put(Code.USER_LANGUAGE2.name(), locale.getLanguage());
        try {
            codes.put(Code.USER_LANGUAGE2.name(), locale.getISO3Language());
        } catch (MissingResourceException mre) {
        }
        codes.put(Code.USER_COUNTRY.name(),
                  locale.getDisplayCountry(Locale.ENGLISH));
        codes.put(Code.USER_COUNTRY2.name(), locale.getLanguage());
        try {
            codes.put(Code.USER_COUNTRY3.name(), locale.getISO3Country());
        } catch (MissingResourceException mre) {
        }
        codes.put(Code.DEVICE_ID.name(),
                  Secure.getString(this.context.getContentResolver(),
                                   Secure.ANDROID_ID));
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
        codes.put(Code.SYSTEM_MILLIS.name(),
                  Long.toString(System.currentTimeMillis()));
        final String base = KVP.encode(codes);
        final String code = new AESJCE(key, iv).encryptToString(base);
        request.setHeader(Header.CODE.fieldName(), code);

        // ----------------------------------------------------------- Nica-Auth
        final String auth = new MACJCE(key).authenticateToString(base);
        request.setHeader(Header.AUTH.fieldName(), auth);
    }


    /**
     * context.
     */
    private final Context context;


    /**
     * key.
     */
    private final byte[] key;


    /**
     * name.
     */
    private final String name;


    /**
     * codes.
     */
    private final Map<String, String> codes = new HashMap<String, String>();


}

