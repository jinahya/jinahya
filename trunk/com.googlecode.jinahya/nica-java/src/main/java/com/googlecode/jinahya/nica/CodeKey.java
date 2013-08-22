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


import com.googlecode.jinahya.lang.FieldEnum;
import com.googlecode.jinahya.lang.FieldEnumHelper;


/**
 * Constants for Nica-Code.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum CodeKey implements FieldEnum<CodeKey, String> {


    /**
     * An enum constant for {@link CodeKeys#REQUEST_QUERY}.
     */
    REQUEST_QUERY(CodeKeys.REQUEST_QUERY),
    /**
     * An enum constant for {@link CodeKeys#REQUEST_TARGET}.
     */
    REQUEST_TARGET(CodeKeys.REQUEST_TARGET),
    /**
     * An enum constant for {@link CodeKeys#REQUEST_METHOD}.
     */
    REQUEST_METHOD(CodeKeys.REQUEST_METHOD),
    /**
     * An enum constant for {@link CodeKeys#REQUEST_NONCE}.
     */
    REQUEST_NONCE(CodeKeys.REQUEST_NONCE),
    /**
     * An enum constant for {@link CodeKeys#REQUEST_TIMESTAMP}.
     */
    REQUEST_TIMESTAMP(CodeKeys.REQUEST_TIMESTAMP),
    /**
     * An enum constant for {@link CodeKeys#USER_PASSWORD_NEW}.
     */
    USER_PASSWORD_NEW(CodeKeys.USER_PASSWORD_NEW),
    /**
     * An enum constant for {@link CodeKeys#USER_PASSWORD_OLD}.
     */
    USER_PASSWORD_OLD(CodeKeys.USER_PASSWORD_OLD),
    /**
     * An enum constant for {@link CodeKeys#USER_PASSWORD}.
     */
    USER_PASSWORD(CodeKeys.USER_PASSWORD),
    /**
     * An enum constant for {@link CodeKeys#USER_USERNAME}.
     */
    USER_USERNAME(CodeKeys.USER_USERNAME),
    /**
     * An enum constant for {@link CodeKeys#USER_COUNTRY3}.
     */
    USER_COUNTRY3(CodeKeys.USER_COUNTRY3),
    /**
     * An enum constant for {@link CodeKeys#USER_COUNTRY2}.
     */
    USER_COUNTRY2(CodeKeys.USER_COUNTRY2),
    /**
     * An enum constant for {@link CodeKeys#USER_COUNTRY}.
     */
    USER_COUNTRY(CodeKeys.USER_COUNTRY),
    /**
     * An enum constant for {@link CodeKeys#USER_LANGUAGE3}.
     */
    USER_LANGUAGE3(CodeKeys.USER_LANGUAGE3),
    /**
     * An enum constant for {@link CodeKeys#USER_LANGUAGE2}.
     */
    USER_LANGUAGE2(CodeKeys.USER_LANGUAGE2),
    /**
     * An enum constant for {@link CodeKeys#USER_LANGUAGE}.
     */
    USER_LANGUAGE(CodeKeys.USER_LANGUAGE),
    //    /**
    //     * A constant for {@link CodeKeys#CLIENT_ID}.
    //     */
    //    CLIENT_ID(CodeKeys.CLIENT_ID),
    /**
     * An enum constant for {@link CodeKeys#USER_ID}.
     */
    USER_ID(CodeKeys.USER_ID),
    /**
     * An enum constant for {@link CodeKeys#SYSTEM_VERSION}.
     */
    SYSTEM_VERSION(CodeKeys.SYSTEM_VERSION),
    /**
     * An enum constant for {@link CodeKeys#SYSTEM_NAME}.
     */
    SYSTEM_NAME(CodeKeys.SYSTEM_NAME),
    /**
     * An enum constant for {@link CodeKeys#SYSTEM_ID}.
     */
    SYSTEM_ID(CodeKeys.SYSTEM_ID),
    /**
     * An enum constant for {@link CodeKeys#DEVICE_VERSION}.
     */
    DEVICE_VERSION(CodeKeys.DEVICE_VERSION),
    /**
     * An enum constant for {@link CodeKeys#DEVICE_NAME}.
     */
    DEVICE_NAME(CodeKeys.DEVICE_NAME),
    /**
     * An enum constant for {@link CodeKeys#DEVICE_ID}.
     */
    DEVICE_ID(CodeKeys.DEVICE_ID),
    /**
     * An enum constant for {@link CodeKeys#PLATFORM_ID}.
     */
    PLATFORM_ID(CodeKeys.PLATFORM_ID);


    /**
     * Returns the enum constant of this type with the specified field value.
     *
     * @param fieldValue the field value of the enum constant to be returned
     *
     * @return the enum constant with specified field value
     */
    public static CodeKey fromFieldValue(final String fieldValue) {

        return FieldEnumHelper.fromFieldValue(CodeKey.class, fieldValue);
    }


    /**
     * Returns an array containing the field values of this enum type, in the
     * order they are declared.
     *
     * @return an array containing the constant of this enum type, in the order
     * they are declared
     */
    public static String[] fieldValues() {

        return FieldEnumHelper.fieldValues(CodeKey.class, String.class);

    }


    /**
     * Creates a new instance with specified field value.
     *
     * @param fieldValue the field value
     */
    private CodeKey(final String fieldValue) {

        if (fieldValue == null) {
            throw new NullPointerException("fieldValue");
        }

        if (fieldValue.trim().isEmpty()) {
            throw new IllegalArgumentException("empty fieldValue");
        }

        this.fieldValue = fieldValue;
    }


    @Override
    public String getFieldValue() {
        return fieldValue;
    }


    /**
     * field value.
     */
    private final String fieldValue;


}
