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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AndroidCodes extends Codes {


    /**
     * Creates a new instance.
     *
     * @param context context
     */
    public AndroidCodes(final Context context) {
        super();

        if (context == null) {
            throw new IllegalArgumentException("null context");
        }

        this.context = context;

        final String androidId = Settings.Secure.getString(
            context.getContentResolver(), Settings.Secure.ANDROID_ID);
        putConstantCode(Code.SYSTEM_ID.key(), androidId);

        putConstantCode(Code.PLATFORM_ID.key(), Platform.ANDROID.id());
    }


    /**
     * context.
     */
    protected final Context context;


}

