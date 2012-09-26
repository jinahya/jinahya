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


package com.googlecode.jinahya.inca;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum CodeProperty {


    /**
     * 3-letter uppercase country/region code. ISO 3166-1 alpha-3.
     */
    INCA_USER_COUNTRY3,
    /**
     * 2-letter uppercase country/region code. ISO 3166-1 alpha-2.
     */
    INCA_USER_COUNTRY2,
    /**
     * User country/region name in en_US.
     */
    INCA_USER_COUNTRY,
    /**
     * 3-letter lowercase language code. ISO 639-2.
     */
    INCA_USER_LANGUAGE3,
    /**
     * 2-letter lowercase language code. ISO 639-1.
     */
    INCA_USER_LANGUAGE2,
    /**
     * User language name in en_US.
     */
    INCA_USER_LANGUAGE,
    /**
     * Current time in milliseconds since 1970.
     */
    INCA_SYSTEM_MILLIS,
    /**
     * System version. OS version.
     */
    INCA_SYSTEM_VERSION,
    /**
     * System identifier. OS name.
     */
    INCA_SYSTEM_ID,
    /**
     * Device's version.
     */
    INCA_DEVICE_VERSION,
    /**
     * Globally unique device identifier.
     */
    INCA_DEVICE_ID


}

