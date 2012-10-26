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


import android.content.Context;
import android.provider.Settings;
import com.googlecode.jinahya.nica.Code;
import com.googlecode.jinahya.nica.Codes;
import com.googlecode.jinahya.nica.Platform;
import java.util.Locale;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AndroidCodes extends Codes {


    public AndroidCodes(final Context context) {
        super();

        if (context == null) {
            throw new IllegalArgumentException("null context");
        }

        final Locale locale = Locale.getDefault();
//        try {
//            putConstantCode(Code.USER_LANGUAGE3.name(),
//                            locale.getISO3Language());
//        } catch (MissingResourceException mre) {
//        }
        putConstantCode(Code.USER_LANGUAGE2.name(), locale.getLanguage());
//        putConstantCode(Code.USER_LANGUAGE.name(),
//                        locale.getDisplayLanguage(Locale.ENGLISH));

//        try {
//            putConstantCode(Code.USER_COUNTRY3.name(),
//                            locale.getISO3Country());
//        } catch (MissingResourceException mre) {
//        }
        putConstantCode(Code.USER_COUNTRY2.name(), locale.getLanguage());
//        putConstantCode(Code.USER_COUNTRY.name(),
//                        locale.getDisplayCountry(Locale.ENGLISH));

        final String androidId = Settings.Secure.getString(
            context.getContentResolver(), Settings.Secure.ANDROID_ID);
        putConstantCode(Code.DEVICE_ID.name(), androidId);

        putConstantCode(Code.PLATFORM_ID.name(), Platform.ANDROID.id());
    }


}

