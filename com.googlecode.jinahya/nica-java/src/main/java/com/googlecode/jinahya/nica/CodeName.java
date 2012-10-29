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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum CodeName {


    /**
     * Number used once per request.
     */
    REQUEST_NONCE,
    /**
     * Current time in milliseconds since
     * <code>midnight Coordinated Universal Time (UTC), 1 January
     * 1970</code>.
     */
    REQUEST_TIMESTAMP,
    /**
     * User's username, if required, per request.
     */
    USER_USERNAME,
    /**
     * User's password, if required, per request. The value for this code must
     * be encoded in SHA-512.
     */
    USER_PASSWORD,
    /**
     * 3-letter uppercase country/region code. ISO 3166-1 alpha-3.
     */
    USER_COUNTRY3,
    /**
     * 2-letter uppercase country/region code. ISO 3166-1 alpha-2.
     */
    USER_COUNTRY2,
    /**
     * User country/region name in en-US. Not strictly required.
     */
    USER_COUNTRY,
    /**
     * 3-letter lowercase language code. ISO 639-2.
     */
    USER_LANGUAGE3,
    /**
     * 2-letter lowercase language code. ISO 639-1.
     */
    USER_LANGUAGE2,
    /**
     * User language name in en-US. Not strictly required.
     */
    USER_LANGUAGE,
    /**
     * System(OS) version.
     */
    SYSTEM_VERSION,
    /**
     * System(OS) name.
     */
    SYSTEM_NAME,
    /**
     * System(OS) identifier. Must be, if specified, unique by
     * {@link #DEVICE_ID}. This code is required if {@link #DEVICE_ID} is
     * absent. The value must not be empty nor blank.
     */
    SYSTEM_ID,
    /**
     * Device's version.
     */
    DEVICE_VERSION,
    /**
     * Device's name.
     */
    DEVICE_NAME,
    /**
     * Device identifier. Must be, if specified, unique by {@link #PLATFORM_ID}.
     * This code is required if {@link #SYSTEM_ID} is absent. The value must not
     * be empty nor blank.
     */
    DEVICE_ID,
    /**
     * platform identifier.
     */
    PLATFORM_ID;


}

