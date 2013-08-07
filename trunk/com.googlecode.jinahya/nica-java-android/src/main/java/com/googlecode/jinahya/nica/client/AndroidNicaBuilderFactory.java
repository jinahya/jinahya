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
import com.googlecode.jinahya.nica.CodeKeys;
import com.googlecode.jinahya.nica.PlatformIds;
import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.HacJCE;
import com.googlecode.jinahya.nica.util.Sha;
import com.googlecode.jinahya.nica.util.ShaJCA;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class AndroidNicaBuilderFactory extends NicaBuilderFactory {


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

        context = context.getApplicationContext(); // required?

        for (AndroidNicaBuilderFactory factory : FACTORIES) {

            factory.constantCodes.put(
                CodeKeys.PLATFORM_ID, PlatformIds.ANDROID);

            final Sha sha = new ShaJCA();
            final String deviceVersion = "unknown";
            System.out.println("deviceVersion: " + deviceVersion);
            factory.constantCodes.put(
                CodeKeys.DEVICE_VERSION, sha.hashToString(deviceVersion));
            final String deviceName = Build.HARDWARE;
            System.out.println("deviceName: " + deviceName);
            factory.constantCodes.put(
                CodeKeys.DEVICE_NAME, sha.hashToString(deviceName));
            final String deviceId = Build.SERIAL;
            System.out.println("deviceId: " + deviceId);
            factory.constantCodes.put(
                CodeKeys.DEVICE_ID, sha.hashToString(deviceId));

            final String systemVersion =
                Integer.toString(Build.VERSION.SDK_INT);
            System.out.println("systemVersion: " + systemVersion);
            factory.constantCodes.put(
                CodeKeys.SYSTEM_VERSION, sha.hashToString(systemVersion));
            final String systemName = "unknown";
            System.out.println("systemName: " + systemName);
            factory.constantCodes.put(
                CodeKeys.SYSTEM_NAME, sha.hashToString(systemName));
            final String systemId = Secure.getString(
                context.getContentResolver(), Secure.ANDROID_ID);
            System.out.println("systemId: " + systemId);
            factory.constantCodes.put(
                CodeKeys.SYSTEM_ID, sha.hashToString(systemId));

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

        for (AndroidNicaBuilder bulider : BUILDERS) {

            bulider.constantCodes.putAll(constantCodes);

            bulider.names.putAll(names);

            bulider.aes = new AesJCE(key);

            bulider.hac = new HacJCE(key);

            return bulider;
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
