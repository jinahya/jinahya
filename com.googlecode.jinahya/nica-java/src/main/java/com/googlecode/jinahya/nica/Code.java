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
public enum Code {


    /**
     * 3-letter uppercase country/region code. ISO 3166-1 alpha-3.
     */
    USER_COUNTRY3,
    /**
     * 2-letter uppercase country/region code. ISO 3166-1 alpha-2.
     */
    USER_COUNTRY2,
    /**
     * User country/region name in en_US.
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
     * User language name in en_US.
     */
    USER_LANGUAGE,
    /**
     * Current time in milliseconds since 1970.
     */
    SYSTEM_MILLIS,
    /**
     * System version. OS version.
     */
    SYSTEM_VERSION,
    /**
     * System identifier. OS name.
     */
    SYSTEM_ID,
    /**
     * Device version.
     */
    DEVICE_VERSION,
    /**
     * Device identifier. Must be globally unique.
     */
    DEVICE_ID


}

