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


package com.googlecode.jinahya.xmlpull.v1;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class XSTemporals {


    /**
     * 
     * @param patterns
     * @return 
     */
    private static List<DateFormat> createDateFormats(final String[] patterns) {

        final List<DateFormat> formats =
            new ArrayList<DateFormat>(patterns.length);
        for (String pattern : patterns) {
            try {
                formats.add(new SimpleDateFormat(pattern));
            } catch (IllegalArgumentException ie) {
                System.err.println("failed to create a DateFormat from '"
                                   + pattern + "'");
            }
        }

        return formats;
    }


    /**
     * DateFormats for <code>xs:date</code>.
     */
    public static final List<DateFormat> XS_DATE_FORMATS =
        Collections.unmodifiableList(createDateFormats(new String[]{
            "yyyy-MM-ddXXX",
            "yyyy-MM-dd"
        }));


    /**
     * DateFormats for <code>xs:time</code>.
     */
    public static final List<DateFormat> XS_TIME_FORMATS =
        Collections.unmodifiableList(createDateFormats(
        new String[]{
            "HH:mm:ss.SSSXXX",
            "HH:mm:ssXXX",
            "HH:mm:ss.SSS",
            "HH:mm:ss"
        }));


    /**
     * DateFormats for <code>xs:dateTime</code>.
     */
    public static final List<DateFormat> XS_DATE_TIME_FORMATS =
        Collections.unmodifiableList(createDateFormats(new String[]{
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
            "yyyy-MM-dd'T'HH:mm:ssXXX",
            "yyyy-MM-dd'T'HH:mm:ss.SSS",
            "yyyy-MM-dd'T'HH:mm:ss"
        }));


    private static Date parseXSTemporal(final List<DateFormat> formats,
                                        final String string)
        throws XmlPullParserException {

        assert formats != null;
        assert string != null;

        for (DateFormat format : formats) {
            try {
                synchronized (format) {
                    return format.parse(string);
                }
            } catch (ParseException pe) {
                pe.printStackTrace(System.err);
            }
        }

        throw new XmlPullParserException(
            "unparsable string('" + string + "')");
    }


    private static String serializeXSTemporal(final List<DateFormat> formats,
                                              final Date value)
        throws XmlPullParserException {

        assert formats != null;
        assert value != null;

        for (DateFormat format : formats) {
            synchronized (format) {
                return format.format(value);
            }
        }

        throw new XmlPullParserException(
            "failed to serialize a value('" + value + "')");
    }


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

        return parseXSTemporal(XS_DATE_FORMATS, string);
    }


    /**
     * 
     * @param value
     * @return
     * @throws XmlPullParserException 
     */
    public static String serailizeXSDate(final Date value)
        throws XmlPullParserException {

        if (value == null) {
            throw new NullPointerException("null date");
        }

        return serializeXSTemporal(XS_DATE_FORMATS, value);

    }


    /**
     * Parses <code>xs:time</code> string to Date.
     *
     * @param string <code>xs:time</code> string
     * @return parsed Date or null if <code>timeString</code> is null.
     * @throws XmlPullParserException if an XML error occurs.
     */
    public static Date parseXSTime(final String string)
        throws XmlPullParserException {

        if (string == null) {
            throw new NullPointerException("null string");
        }

        return parseXSTemporal(XS_TIME_FORMATS, string);
    }


    /**
     * 
     * @param value
     * @return
     * @throws XmlPullParserException 
     */
    public static String serializeXSTime(final Date value)
        throws XmlPullParserException {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        return serializeXSTemporal(XS_TIME_FORMATS, value);
    }


    /**
     * 
     * @param string
     * @return
     * @throws XmlPullParserException 
     */
    public static Date parseXSDateTime(final String string)
        throws XmlPullParserException {

        if (string == null) {
            throw new NullPointerException("null string");
        }

        return parseXSTemporal(XS_DATE_TIME_FORMATS, string);
    }


    /**
     * 
     * @param value
     * @return
     * @throws XmlPullParserException 
     */
    public static String serializeXSDateTime(final Date value)
        throws XmlPullParserException {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        return serializeXSTemporal(XS_DATE_TIME_FORMATS, value);
    }


    private XSTemporals() {
        super();
    }


}

