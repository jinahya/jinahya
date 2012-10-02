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
public enum Platform {


    /**
     * Android.
     */
    ANDROID("/platforms/android"),
    /**
     * BlackBerry.
     */
    BLACK_BERRY("/platforms/black_berry"),
    /**
     * iOS.
     */
    IOS("/platforms/ios"),
    /**
     * Tizen.
     */
    TIZEN("/platforms/tizen"),
    /**
     * Windows Phone.
     */
    WINDOWS_PHONE("/platforms/windows_phone");


    /**
     * Finds a constants mapped to given
     * <code>value</code>.
     *
     * @param value value
     * @return found constant
     */
    public static Platform fromValue(final String value) {

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        for (Platform constant : values()) {
            if (constant.value.equals(value)) {
                return constant;
            }
        }

        throw new IllegalArgumentException(
            "constant not found for value: " + value);
    }


    /**
     * Creates a new instance.
     *
     * @param value value
     */
    private Platform(final String value) {

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        this.value = value;
    }


    /**
     * value.
     */
    private final String value;


}

