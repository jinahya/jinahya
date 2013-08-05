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


package com.googlecode.jinahya.nica;


import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.HacJCE;
import com.googlecode.jinahya.nica.util.Sha;
import com.googlecode.jinahya.nica.util.ShaJCA;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class AndroidNicaBuilderFactory extends NicaBuilderFactory {


    private static final class InstanceHolder {


        private static final AndroidNicaBuilderFactory INSTANCE =
            new AndroidNicaBuilderFactory();


        private InstanceHolder() {

            super();
        }


    }


//    private static void probeCountry(final Context context,
//                                     final Map<String, String> codes) {
//
//        final Locale locale = Locale.getDefault();
//
//        try {
//            final String userCountry3 = locale.getISO3Country();
//            codes.put(CodeKeys.USER_COUNTRY3, userCountry3);
//        } catch (MissingResourceException mre) {
//            codes.put(CodeKeys.USER_COUNTRY3, "missing");
//        }
//
//        final String userCountry2 = locale.getCountry();
//        codes.put(CodeKeys.USER_COUNTRY2, userCountry2);
//
//        final String userCountry = locale.getDisplayCountry(Locale.ENGLISH);
//        codes.put(CodeKeys.USER_COUNTRY, userCountry);
//    }
//
//
//    private static void probeLanguage(final Context context,
//                                      final Map<String, String> codes) {
//
//        final Locale locale = Locale.getDefault();
//
//        try {
//            final String userLanguage3 = locale.getISO3Language();
//            codes.put(CodeKeys.USER_LANGUAGE3, userLanguage3);
//        } catch (MissingResourceException mre) {
//            codes.put(CodeKeys.USER_LANGUAGE3, "missing");
//        }
//
//        final String userLanguage2 = locale.getLanguage();
//        codes.put(CodeKeys.USER_LANGUAGE2, userLanguage2);
//
//        final String userLanguage = locale.getDisplayLanguage(Locale.ENGLISH);
//        codes.put(CodeKeys.USER_LANGUAGE, userLanguage);
//    }
//
//
//    private static void probeSystem(final Context context,
//                                    final Map<String, String> codes) {
//
//        final Sha sha = new ShaJCA();
//
//        final String systemVersion =
//            Integer.toString(Build.VERSION.SDK_INT);
//        codes.put(CodeKeys.SYSTEM_VERSION, sha.hashToString(systemVersion));
//
//        final String systemName = "unknown";
//        codes.put(CodeKeys.SYSTEM_NAME, sha.hashToString(systemName));
//
//        final String systemId = Secure.getString(
//            context.getContentResolver(), Secure.ANDROID_ID);
//        codes.put(CodeKeys.SYSTEM_ID, sha.hashToString(systemId));
//    }
//
//
//    private static void probeDevice(final Context context,
//                                    final Map<String, String> codes) {
//
//        final Sha sha = new ShaJCA();
//
//        final String deviceVersion = "unknown";
//        codes.put(CodeKeys.DEVICE_VERSION, sha.hashToString(deviceVersion));
//
//        final String deviceName = Build.HARDWARE;
//        codes.put(CodeKeys.DEVICE_NAME, sha.hashToString(deviceName));
//
//        final String deviceId = Build.SERIAL;
//        codes.put(CodeKeys.DEVICE_ID, sha.hashToString(deviceId));
//    }
//
//
//    private static void probePlatform(final Context context,
//                                      final Map<String, String> codes) {
//
//        codes.put(CodeKeys.PLATFORM_ID, PlatformIds.ANDROID);
//    }
    /**
     * Returns the instance.
     *
     * @param context the application context
     *
     * @return the NicaBuilderFactory instance.
     */
    public static NicaBuilderFactory getInstance(Context context) {

        if (context == null) {
            throw new NullPointerException("context");
        }

        context = context.getApplicationContext(); // required?

        synchronized (InstanceHolder.INSTANCE) {

            if (InstanceHolder.INSTANCE.constantCodes == null) {

                InstanceHolder.INSTANCE.constantCodes =
                    new HashMap<String, String>();

                InstanceHolder.INSTANCE.constantCodes.put(
                    CodeKeys.PLATFORM_ID, PlatformIds.ANDROID);

                final Sha sha = new ShaJCA();
                final String deviceVersion = "unknown";
                InstanceHolder.INSTANCE.constantCodes.put(
                    CodeKeys.DEVICE_VERSION, sha.hashToString(deviceVersion));
                final String deviceName = Build.HARDWARE;
                InstanceHolder.INSTANCE.constantCodes.put(
                    CodeKeys.DEVICE_NAME, sha.hashToString(deviceName));
                final String deviceId = Build.SERIAL;
                InstanceHolder.INSTANCE.constantCodes.put(
                    CodeKeys.DEVICE_ID, sha.hashToString(deviceId));

                final String systemVersion =
                    Integer.toString(Build.VERSION.SDK_INT);
                InstanceHolder.INSTANCE.constantCodes.put(
                    CodeKeys.SYSTEM_VERSION, sha.hashToString(systemVersion));
                final String systemName = "unknown";
                InstanceHolder.INSTANCE.constantCodes.put(
                    CodeKeys.SYSTEM_NAME, sha.hashToString(systemName));
                final String systemId = Secure.getString(
                    context.getContentResolver(), Secure.ANDROID_ID);
                InstanceHolder.INSTANCE.constantCodes.put(
                    CodeKeys.SYSTEM_ID, sha.hashToString(systemId));

                // [TODO] load names and key here
            }
        }

        return InstanceHolder.INSTANCE;
    }


    protected AndroidNicaBuilderFactory() {

        super();
    }


    @Override
    public NicaBuilder newNicaBuilder() {

        final AndroidNicaBuilder nicaBuilder = new AndroidNicaBuilder();

        nicaBuilder.constantCodes.putAll(constantCodes);

        nicaBuilder.names.putAll(names);

        nicaBuilder.aes = new AesJCE(key);

        nicaBuilder.hac = new HacJCE(key);

        return nicaBuilder;
    }


    private transient volatile Map<String, String> constantCodes = null;


    private final transient Map<String, String> names =
        new HashMap<String, String>();


    private transient byte[] key;


}
