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


import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class XSBoolean {


    public static boolean parseXSBoolean(final String string)
        throws XmlPullParserException {

        if (string == null) {
            throw new NullPointerException("null value");
        }

        if ("true".equals(string) || "1".equals(string)) {
            return true;
        }

        if ("false".equals(string) || "0".equals(string)) {
            return false;
        }

        throw new XmlPullParserException(
            "unparsable xs:boolean value: " + string);
    }


    public static String serializeXSBoolean(final Boolean value) {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        return Boolean.toString(value);
    }


    private XSBoolean() {
        super();
    }


}

