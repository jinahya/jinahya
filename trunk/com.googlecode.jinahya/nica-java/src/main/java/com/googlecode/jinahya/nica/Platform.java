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
    BLACKBERRY("/platforms/blackberry"),
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
     * @param id value
     *
     * @return found constant
     */
    public static Platform fromId(final String id) {

        if (id == null) {
            throw new IllegalArgumentException("null id");
        }

        for (Platform value : values()) {
            if (value.id.equals(id)) {
                return value;
            }
        }

        throw new IllegalArgumentException(
            "constant not found for id: " + id);
    }


    /**
     * Creates a new instance.
     *
     * @param id id
     */
    private Platform(final String id) {

        if (id == null) {
            throw new IllegalArgumentException("null id");
        }

        if (id.isEmpty()) {
            throw new IllegalArgumentException("empty id");
        }

        this.id = id;
    }


    /**
     * Returns id.
     *
     * @return id
     */
    public String id() {
        return id;
    }


    /**
     * id.
     */
    private final String id;


}

