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


/**
 * Constant names for Nica-Codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class CodeKeys {


    /**
     * Number used once per request.
     */
    public static final String REQUEST_NONCE = "REQUEST_NONCE";


    /**
     * Current time in milliseconds since
     * <code>00:00:00 UTC on 1 January 1970</code>.
     */
    public static final String REQUEST_TIMESTAMP = "REQUEST_TIMESTAMP";


    /**
     * HTTP request method in upper case.
     */
    public static final String REQUEST_METHOD = "REQUEST_METHOD";


    /**
     * HTTP request URL.
     */
    public static final String REQUEST_URL = "REQUEST_URL";


    /**
     * User's username, if required, per request.
     */
    public static final String USER_USERNAME = "USER_USERNAME";


    /**
     * User's password, if required, per request. The value for this code must
     * be hashed in SHA-512.
     */
    public static final String USER_PASSWORD = "USER_PASSWORD";


    /**
     * 3-letter uppercase country/region code. ISO 3166-1 alpha-3.
     */
    public static final String USER_COUNTRY3 = "USER_COUNTRY3";


    /**
     * 2-letter uppercase country/region code. ISO 3166-1 alpha-2.
     */
    public static final String USER_COUNTRY2 = "USER_COUNTRY2";


    /**
     * User country/region name in en-US. Not strictly required.
     */
    public static final String USER_COUNTRY = "USER_COUNTRY";


    /**
     * 3-letter lowercase language code. ISO 639-2.
     */
    public static final String USER_LANGUAGE3 = "USER_LANGUAGE3";


    /**
     * 2-letter lowercase language code. ISO 639-1.
     */
    public static final String USER_LANGUAGE2 = "USER_LANGUAGE2";


    /**
     * User language name in en-US. Not strictly required.
     */
    public static final String USER_LANGUAGE = "USER_LANGUAGE";


    /**
     * System (OS) version.
     */
    public static final String SYSTEM_VERSION = "SYSTEM_VERSION";


    /**
     * System (OS) name.
     */
    public static final String SYSTEM_NAME = "SYSTEM_NAME";


    /**
     * System (OS) identifier. Must be, if specified, unique by
     * {@link #DEVICE_ID}. This code is required if the {@link #DEVICE_ID} is
     * absent. The value must not be empty nor blank.
     */
    public static final String SYSTEM_ID = "SYSTEM_ID";


    /**
     * Device's version.
     */
    public static final String DEVICE_VERSION = "DEVICE_VERSION";


    /**
     * Device's name.
     */
    public static final String DEVICE_NAME = "DEVICE_NAME";


    /**
     * Device identifier. Must be, if specified, unique by {@link #PLATFORM_ID}.
     * This code is required if the {@link #SYSTEM_ID} is absent. The value must
     * not be empty nor blank.
     */
    public static final String DEVICE_ID = "DEVICE_ID";


    /**
     * Platform identifier. This code is required and the value must not be
     * empty nor blank.
     */
    public static final String PLATFORM_ID = "PLATFORM_ID";


    /**
     * Creates a new instance.
     */
    private CodeKeys() {
        super();
    }


}

