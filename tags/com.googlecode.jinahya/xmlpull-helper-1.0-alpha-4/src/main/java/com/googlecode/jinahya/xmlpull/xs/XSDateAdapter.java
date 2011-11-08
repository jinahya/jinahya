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


import java.io.IOException;
import java.text.DateFormat;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XSDateAdapter extends XSTemporalAdapter {


    /**
     * DateFormats for <code>xs:date</code>.
     */
    private static final List<DateFormat> FORMATS =
        Collections.unmodifiableList(createFormats(new String[]{
            "yyyy-MM-ddXXX",
            "yyyy-MM-dd"
        }));


    /**
     * Parses <code>xs:date</code>.
     *
     * @param string date string
     * @return parsed Date or null if given <code>dateString</code> is null.
     * @throws XmlPullParserException if an XML error occurs.
     */
    public static Date parseXSDate(final String string)
        throws XmlPullParserException {

        if (string == null) {
            throw new NullPointerException("null string");
        }

        return parseXSTemporal(FORMATS, string);
    }


    /**
     * Serializes given <code>value</code> to <code>xs:date</code> format.
     *
     * @param value value
     * @return String representation
     * @throws XmlPullParserException if failed to serialize
     */
    public static String serializeXSDate(final Date value)
        throws XmlPullParserException {

        if (value == null) {
            throw new NullPointerException("null date");
        }

        return serializeXSTemporal(FORMATS, value);

    }


    @Override
    public Date parse(final String string)
        throws XmlPullParserException, IOException {

        if (string == null) {
            throw new NullPointerException("null string");
        }

        return parseXSDate(string);
    }


    @Override
    public String serialize(final Date value)
        throws XmlPullParserException, IOException {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        return serializeXSDate(value);
    }


}

