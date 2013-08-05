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
     * See {@link PlatformIds#ANDROID}.
     */
    ANDROID(PlatformIds.ANDROID),
    /**
     * See {@link PlatformIds#BLACKBERRY}.
     */
    BLACKBERRY(PlatformIds.BLACKBERRY),
    /**
     * See {@link PlatformIds#IOS}.
     */
    IOS(PlatformIds.IOS),
    /**
     * See {@link PlatformIds#JAVA_TV}.
     */
    JAVA_TV(PlatformIds.JAVA_TV),
    /**
     * See {@link PlatformIds#TIZEN}.
     */
    TIZEN(PlatformIds.TIZEN),
    /**
     * See {@link PlatformIds#UNKNOWN}.
     */
    UNKNOWN(PlatformIds.UNKNOWN),
    /**
     * See {@link PlatformIds#WINDOWS_PHONE}.
     */
    WINDOWS_PHONE(PlatformIds.WINDOWS_PHONE);


    /**
     * Finds a constants mapped to given {@code id}.
     *
     * @param id value
     *
     * @return found constant
     */
    public static Platform fromId(final String id) {

        for (Platform value : values()) {
            if (value.id.equals(id)) {
                return value;
            }
        }

        throw new IllegalArgumentException("unknown id: " + id);
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

        if (id.trim().isEmpty()) {
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

