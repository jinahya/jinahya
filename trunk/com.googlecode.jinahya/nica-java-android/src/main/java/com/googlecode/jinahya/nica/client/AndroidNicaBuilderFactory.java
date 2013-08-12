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


import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import android.util.AndroidRuntimeException;
import android.util.Log;
import com.googlecode.jinahya.nica.CodeKeys;
import com.googlecode.jinahya.nica.PlatformIds;
import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.HacJCE;
import com.googlecode.jinahya.nica.util.Sha;
import com.googlecode.jinahya.nica.util.ShaJCA;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class AndroidNicaBuilderFactory extends NicaBuilderFactory {


    private static final String LOG_TAG = "NICA";


    private static ServiceLoader<AndroidNicaBuilderFactory> FACTORIES =
        ServiceLoader.load(AndroidNicaBuilderFactory.class);


    private static ServiceLoader<AndroidNicaBuilder> BUILDERS =
        ServiceLoader.load(AndroidNicaBuilder.class);


    /**
     * Returns the instance.
     *
     * @param context the application context
     *
     * @throws NicaClientException
     */
    public static NicaBuilderFactory newInstance(Context context)
        throws NicaClientException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        System.out.println("os.arch: " + System.getProperty("os.arch"));
        System.out.println("os.name: " + System.getProperty("os.name"));
        System.out.println("os.version: " + System.getProperty("os.version"));

        for (Field field : Build.class.getFields()) {
            try {
                Log.i(LOG_TAG, "Build." + field.getName() + ": "
                               + field.get(null));
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }

        for (Field field : Build.VERSION.class.getFields()) {
            try {
                Log.i(LOG_TAG, "Build.VERSION." + field.getName() + ": "
                               + field.get(null));
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }

        context = context.getApplicationContext(); // required?

        for (AndroidNicaBuilderFactory factory : FACTORIES) {

            factory.constantCodes.put(
                CodeKeys.PLATFORM_ID, PlatformIds.ANDROID);

            final Sha sha = new ShaJCA();

            final String deviceVersion = "unknown";
            factory.constantCodes.put(
                CodeKeys.DEVICE_VERSION, sha.hashToString(deviceVersion));

            final String deviceName = Build.HARDWARE;
            factory.constantCodes.put(
                CodeKeys.DEVICE_NAME, sha.hashToString(deviceName));

            final String deviceId = Build.SERIAL;
            factory.constantCodes.put(
                CodeKeys.DEVICE_ID, sha.hashToString(deviceId));

            final String systemVersion = Build.VERSION.RELEASE;
            factory.constantCodes.put(
                CodeKeys.SYSTEM_VERSION, sha.hashToString(systemVersion));

            final String systemName = "unknown";
            factory.constantCodes.put(
                CodeKeys.SYSTEM_NAME, sha.hashToString(systemName));

            final String systemId = Secure.getString(
                context.getContentResolver(), Secure.ANDROID_ID);
            factory.constantCodes.put(
                CodeKeys.SYSTEM_ID, sha.hashToString(systemId));

            for (Entry<String, String> entry
                 : factory.constantCodes.entrySet()) {
                Log.i(LOG_TAG, entry.getKey() + ": " + entry.getValue());
            }

            final ClientCredential credential =
                factory.loadClientCredential(context);
            final Map names = credential.getNames();
            factory.names.putAll(names);
            final byte[] key = credential.getKey();
            System.arraycopy(key, 0, factory.key, 0,
                             key.length);

            return factory;
        }

        throw new NicaClientException(
            "no implementation found: " + AndroidNicaBuilderFactory.class);
    }


    protected AndroidNicaBuilderFactory() {

        super();
    }


    @Override
    public NicaBuilder newNicaBuilder() throws NicaClientException {

        for (AndroidNicaBuilder builder : BUILDERS) {

            builder.constantCodes.putAll(constantCodes);

            try {
                final Field field =
                    AndroidNicaBuilder.class.getDeclaredField("names");
                field.setAccessible(true);
                try {
                    @SuppressWarnings("unchecked")
                    final Map<String, String> value =
                        (Map<String, String>) field.get(builder);
                    value.putAll(names);
                } catch (IllegalAccessException iae) {
                    throw new AndroidRuntimeException(iae);
                }
            } catch (NoSuchFieldException nsfe) {
                throw new AndroidRuntimeException(nsfe);
            }
//            builder.names.putAll(names);

            try {
                final Field field =
                    AndroidNicaBuilder.class.getDeclaredField("aes");
                field.setAccessible(true);
                try {
                    field.set(builder, new AesJCE(key));
                } catch (IllegalAccessException iae) {
                    throw new AndroidRuntimeException(iae);
                }
            } catch (NoSuchFieldException nsfe) {
                throw new AndroidRuntimeException(nsfe);
            }
//            builder.aes = new AesJCE(key);

            try {
                final Field field =
                    AndroidNicaBuilder.class.getDeclaredField("hac");
                field.setAccessible(true);
                try {
                    field.set(builder, new HacJCE(key));
                } catch (IllegalAccessException iae) {
                    throw new AndroidRuntimeException(iae);
                }
            } catch (NoSuchFieldException nsfe) {
                throw new AndroidRuntimeException(nsfe);
            }
//            builder.hac = new HacJCE(key);

            return builder;
        }

        throw new NicaClientException(
            "no implementation found: " + AndroidNicaBuilder.class);
    }


    /**
     *
     * @param context application context
     *
     * @return a loaded instance of {@link ClientCredential}.
     *
     * @throws NicaClientException if failed to load
     */
    protected abstract ClientCredential loadClientCredential(Context context)
        throws NicaClientException;


    private final transient Map<String, String> constantCodes =
        new HashMap<String, String>();


    private final transient Map<String, String> names =
        new HashMap<String, String>();


    private final transient byte[] key = new byte[Aes.KEY_SIZE_IN_BYTES];


}
