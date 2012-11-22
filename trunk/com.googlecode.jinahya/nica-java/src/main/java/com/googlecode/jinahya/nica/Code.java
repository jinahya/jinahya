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
 * Constants for Nica-Code.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum Code {


    /**
     * See {@link CodeKeys#REQUEST_NONCE}.
     */
    REQUEST_NONCE(CodeKeys.REQUEST_NONCE),
    /**
     * See {@link CodeKeys#REQUEST_TIMESTAMP}.
     */
    REQUEST_TIMESTAMP(CodeKeys.REQUEST_TIMESTAMP),
    /**
     * See {@link CodeKeys#REQUEST_METHOD}.
     */
    REQUEST_METHOD(CodeKeys.REQUEST_METHOD),
    /**
     * See {@link CodeKeys#REQUEST_URL}.
     */
    REQUEST_URL(CodeKeys.REQUEST_URL),
    /**
     * See {@link CodeKeys#USER_PASSWORD_NEW}.
     */
    USER_PASSWORD_NEW(CodeKeys.USER_PASSWORD_NEW),
    /**
     * See {@link CodeKeys#USER_PASSWORD_OLD}.
     */
    USER_PASSWORD_OLD(CodeKeys.USER_PASSWORD_OLD),
    /**
     * See {@link CodeKeys#USER_PASSWORD}.
     */
    USER_PASSWORD(CodeKeys.USER_PASSWORD),
    /**
     * See {@link CodeKeys#USER_USERNAME}.
     */
    USER_USERNAME(CodeKeys.USER_USERNAME),
    /**
     * See {@link CodeKeys#USER_COUNTRY3}.
     */
    USER_COUNTRY3(CodeKeys.USER_COUNTRY3),
    /**
     * See {@link CodeKeys#USER_COUNTRY2}.
     */
    USER_COUNTRY2(CodeKeys.USER_COUNTRY2),
    /**
     * See {@link CodeKeys#USER_COUNTRY}.
     */
    USER_COUNTRY(CodeKeys.USER_COUNTRY),
    /**
     * See {@link CodeKeys#USER_LANGUAGE3}.
     */
    USER_LANGUAGE3(CodeKeys.USER_LANGUAGE3),
    /**
     * See {@link CodeKeys#USER_LANGUAGE2}.
     */
    USER_LANGUAGE2(CodeKeys.USER_LANGUAGE2),
    /**
     * See {@link CodeKeys#USER_LANGUAGE}.
     */
    USER_LANGUAGE(CodeKeys.USER_LANGUAGE),
    /**
     * See {@link CodeKeys#SYSTEM_VERSION}.
     */
    SYSTEM_VERSION(CodeKeys.SYSTEM_VERSION),
    /**
     * See {@link CodeKeys#SYSTEM_NAME}.
     */
    SYSTEM_NAME(CodeKeys.SYSTEM_NAME),
    /**
     * See {@link CodeKeys#SYSTEM_ID}.
     */
    SYSTEM_ID(CodeKeys.SYSTEM_ID),
    /**
     * See {@link CodeKeys#DEVICE_VERSION}.
     */
    DEVICE_VERSION(CodeKeys.DEVICE_VERSION),
    /**
     * See {@link CodeKeys#DEVICE_NAME}.
     */
    DEVICE_NAME(CodeKeys.DEVICE_NAME),
    /**
     * See {@link CodeKeys#DEVICE_ID}.
     */
    DEVICE_ID(CodeKeys.DEVICE_ID),
    /**
     * See {@link CodeKeys#PLATFORM_ID}.
     */
    PLATFORM_ID(CodeKeys.PLATFORM_ID);


    /**
     * Finds the value whose key matches to given
     * <code>key</code>. An IllegalArgumentException will be thrown if no values
     * match.
     *
     * @param key key
     *
     * @return the matched value.
     */
    public static Code fromKey(final String key) {

        for (Code value : values()) {
            if (value.key.equals(key)) {
                return value;
            }
        }

        throw new IllegalArgumentException("unknown key: " + key);
    }


    /**
     * Creates a new instance.
     *
     * @param key key
     */
    private Code(final String key) {
        this.key = key;
    }


    /**
     * Returns key.
     *
     * @return key
     */
    public String key() {
        return key;
    }


    /**
     * key.
     */
    private final String key;


}

