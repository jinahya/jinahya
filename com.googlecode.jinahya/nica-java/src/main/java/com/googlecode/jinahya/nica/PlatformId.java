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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum PlatformId implements FieldEnum<PlatformId, String> {


    /**
     * An enum constant for {@link PlatformIds#ANDROID}.
     */
    ANDROID(PlatformIds.ANDROID),
    /**
     * An enum constant for {@link PlatformIds#BLACKBERRY}.
     */
    BLACKBERRY(PlatformIds.BLACKBERRY),
    /**
     * An enum constant for {@link PlatformIds#IOS}.
     */
    IOS(PlatformIds.IOS),
    /**
     * An enum constant for {@link PlatformIds#JAVA_TV}.
     */
    JAVA_TV(PlatformIds.JAVA_TV),
    /**
     * An enum constant for {@link PlatformIds#OTHER}.
     */
    OTHER(PlatformIds.OTHER),
    /**
     * An enum constant for {@link PlatformIds#TIZEN}.
     */
    TIZEN(PlatformIds.TIZEN),
    /**
     * An enum constant for {@link PlatformIds#WINDOWS_PHONE}.
     */
    WINDOWS_PHONE(PlatformIds.WINDOWS_PHONE);


    /**
     * Returns the enum constant of this type with the specified field value.
     *
     * @param fieldValue the field value of the enum constant to be returned
     *
     * @return the enum constant with specified field value
     */
    public static PlatformId fromFieldValue(final String fieldValue) {

        return FieldEnumHelper.fromFieldValue(PlatformId.class, fieldValue);
    }


    /**
     * Returns an array containing the field values of this enum type, in the
     * order they are declared.
     *
     * @return an array containing the constant of this enum type, in the order
     * they are declared
     */
    public static String[] fieldValues() {

        return FieldEnumHelper.fieldValues(PlatformId.class, String.class);
    }


    /**
     * Creates a new instance with specified field value.
     *
     * @param fieldValue the field value
     */
    private PlatformId(final String fieldValue) {

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
