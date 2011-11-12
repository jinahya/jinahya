/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.xmlpull.xs;


/**
 * XSTypeAdapter for <code>xs:boolean</code>.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XSBooleanAdapter implements XSTypeAdapter<Boolean> {


    /**
     * Parses given <code>string</code>.
     *
     * @param string string to parse
     * @return parsed Boolean value
     */
    public static Boolean parseXSBoolean(final String string) {

        if (string == null) {
            throw new NullPointerException("null value");
        }

        if ("true".equals(string) || "1".equals(string)) {
            return Boolean.TRUE;
        }

        if ("false".equals(string) || "0".equals(string)) {
            return Boolean.FALSE;
        }

        throw new IllegalArgumentException(
            "failed to parse xs:boolean value from " + string);
    }


    /**
     * Serializes given <code>value</code>.
     *
     * @param value value to serialize
     * @return String representation.
     */
    public static String serializeXSBoolean(final Boolean value) {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        return Boolean.toString(value);
    }


    @Override
    public Boolean parse(final String string) {

        if (string == null) {
            throw new NullPointerException("null value");
        }

        return parseXSBoolean(string);
    }


    @Override
    public String print(final Boolean value) {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        return serializeXSBoolean(value);
    }


    /**
     * Creates a new instance.
     */
    private XSBooleanAdapter() {
        super();
    }


}

