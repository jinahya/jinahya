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
public enum Header {


    /**
     * Header for initialization vector.
     */
    INIT("Inca-Init"),
    /**
     * Header for names.
     */
    NAME("Inca-Name"),
    /**
     * Header for encrypted properties.
     */
    CODE("Inca-Code"),
    /**
     * Header for HMAC.
     */
    AUTH("Inca-Auth");


    /**
     * Creates a new instance.
     *
     * @param fieldName http header field-name.
     */
    private Header(final String fieldName) {
//        super();

        if (fieldName == null) {
            throw new IllegalArgumentException("null fieldName");
        }

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

