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
public enum Header {


    /**
     * Header for names.
     */
    NAME(HeaderFieldNames.NAME),
    /**
     * Header for initialization vector.
     */
    INIT(HeaderFieldNames.INIT),
    /**
     * Header for encrypted code.
     */
    CODE(HeaderFieldNames.CODE),
    /**
     * Header for HMAC.
     */
    AUTH(HeaderFieldNames.AUTH);


    /**
     * Finds value whose fieldName matched with given
     * <code>fieldName</code>.
     *
     * @param fieldName HTTP header field name.
     * @return matched value.
     */
    public static Header fromFieldName(final String fieldName) {

        for (Header value : values()) {
            if (value.fieldName.equals(fieldName)) {
                return value;
            }
        }

        throw new IllegalArgumentException("unknown fieldName: " + fieldName);
    }


    /**
     * Creates a new instance.
     *
     * @param fieldName HTTP header field-name.
     */
    private Header(final String fieldName) {
        this.fieldName = fieldName;
    }


    /**
     * Returns fieldName.
     *
     * @return fieldName
     */
    public String fieldName() {
        return fieldName;
    }


    /**
     * fieldName.
     */
    private final String fieldName;


}

