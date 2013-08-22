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
     * namespace.
     */
    private static final String NAMESPACE =
        "http://jinahya.googlecode.com/nica/codes";


    /**
     * HTTP request query.
     */
    public static final String REQUEST_QUERY = NAMESPACE + "/request_query";


    /**
     * HTTP request target. The value for this code is the request target in
     * {@code absolute-form} without the query part.
     */
    public static final String REQUEST_TARGET = NAMESPACE + "/request_target";


    /**
     * HTTP request method in upper case.
     */
    public static final String REQUEST_METHOD = NAMESPACE + "/request_method";


    /**
     * Number used once per request.
     */
    public static final String REQUEST_NONCE = NAMESPACE + "/request_nonce";


    /**
     * Current time in milliseconds since
     * {@code 00:00:00 UTC on 1 January 1970}.
     */
    public static final String REQUEST_TIMESTAMP =
        NAMESPACE + "/request_timestamp";


    /**
     * User's new password for changing, if required, per request. The value for
     * this code must be hashed in SHA-512.
     */
    public static final String USER_PASSWORD_NEW =
        NAMESPACE + "/user_password_new";


    /**
     * User's old password for changing, if required, per request. The value for
     * this code must be hashed in SHA-512.
     */
    public static final String USER_PASSWORD_OLD =
        NAMESPACE + "/user_password_old";


    /**
     * User's password, if required, per request. The value for this code must
     * be hashed in SHA-512.
     */
    public static final String USER_PASSWORD = NAMESPACE + "/user_password";


    /**
     * User's username, if required, per request. The value for this code may be
     * hashed in SHA-512.
     */
    public static final String USER_USERNAME = NAMESPACE + "/user_username";


    /**
     * 3-letter uppercase country/region code. {@code ISO 3166-1 alpha-3}.
     */
    public static final String USER_COUNTRY3 = NAMESPACE + "/user_country3";


    /**
     * 2-letter uppercase country/region code. {@code ISO 3166-1 alpha-2}.
     */
    public static final String USER_COUNTRY2 = NAMESPACE + "/user_country2";


    /**
     * User country/region name in {@code English}. Not strictly required.
     */
    public static final String USER_COUNTRY = NAMESPACE + "/user_country";


    /**
     * 3-letter lowercase language code. {@code ISO 639-2}.
     */
    public static final String USER_LANGUAGE3 = NAMESPACE + "/user_language3";


    /**
     * 2-letter lowercase language code. {@code ISO 639-1}.
     */
    public static final String USER_LANGUAGE2 = NAMESPACE + "/user_language2";


    /**
     * User language name in {@code English}. Not strictly required.
     */
    public static final String USER_LANGUAGE = NAMESPACE + "/user_language";


    /**
     * User identifier.
     */
    public static final String USER_ID = NAMESPACE + "/user_id";


    // ------------------------------------------------------------------ SYSTEM
    /**
     * System (OS) version.
     */
    public static final String SYSTEM_VERSION = NAMESPACE + "/system_version";


    /**
     * System (OS) name.
     */
    public static final String SYSTEM_NAME = NAMESPACE + "/system_name";


    /**
     * System(OS) identifier. Must be, if specified, unique by
     * {@link #DEVICE_ID}. The value must not be empty nor blank. The actual
     * value must be transformed in a consistent manner. Exposing or storing the
     * raw value is strictly prohibited.
     */
    public static final String SYSTEM_ID = NAMESPACE + "/system_id";


    // ------------------------------------------------------------------ DEVICE
    /**
     * Device's version.
     */
    public static final String DEVICE_VERSION = NAMESPACE + "/device_version";


    /**
     * Device's name.
     */
    public static final String DEVICE_NAME = NAMESPACE + "/device_name";


    /**
     * Device(H/W) identifier. Must be, if specified, unique by
     * {@link #PLATFORM_ID}. The value must not be empty nor blank. The actual
     * value must be transformed in a consistent manner. Exposing or storing the
     * raw value is strictly prohibited.
     */
    public static final String DEVICE_ID = NAMESPACE + "/device_id";


    // ---------------------------------------------------------------- PLATFORM
    /**
     * Platform identifier. This code is required and the value must not be
     * empty nor blank.
     */
    public static final String PLATFORM_ID = NAMESPACE + "/platform_id";


    /**
     * Creates a new instance.
     */
    private CodeKeys() {
        super();
    }


}
