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


import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class XmlPullParsableHelper {


    /**
     * Parses a new instance of given <code>type</code> from specified
     * <code>parser</code>.
     *
     * @param <T> type parameter
     * @param parser parser
     * @param type type
     * @return a new instance
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static <T extends XmlPullParsable> T parseInstance(
        final XmlPullParser parser, final Class<T> type)
        throws XmlPullParserException, IOException {

        try {
            final T instance = type.newInstance();
            instance.parse(parser);
            return instance;
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        }
    }


    /**
     * 
     * @param patterns
     * @return 
     */
    private static DateFormat[] getXSTemporalFormats(final String[] patterns) {

        final DateFormat[] formats = new DateFormat[patterns.length];

        for (int i = 0; i < formats.length; i++) {
            try {
                formats[i] = new SimpleDateFormat(patterns[i]);
            } catch (IllegalArgumentException ie) {
                System.err.println("failed to create DateFormat from '"
                                   + patterns[i] + "'");
            }
        }

        return formats;
    }


    /**
     * Parses given <code>temporalString</code> with specified
     * <code>temporalFormats</code>.
     *
     * @param temporalFormats date formats
     * @param temporalString date value
     * @return parsed date
     * @throws XmlPullParserException 
     */
    private static Date parseXSTemporal(final DateFormat[] temporalFormats,
                                        final String temporalString)
        throws XmlPullParserException {

        if (temporalFormats == null) {
            throw new NullPointerException("null tempralFormats");
        }

        if (temporalFormats.length == 0) {
            throw new IllegalArgumentException("empty temporalFormats");
        }

        if (temporalString == null) {
            throw new NullPointerException("null temporalString");
        }

        if (temporalString.length() == 0) {
            throw new IllegalArgumentException("empty temporalString");
        }

        for (int i = 0; i < temporalFormats.length; i++) {
            if (temporalFormats[i] == null) {
                continue;
            }
            try {
                synchronized (temporalFormats[i]) {
                    return temporalFormats[i].parse(temporalString);
                }
            } catch (ParseException pe) {
                // empty
            }
        }

        throw new XmlPullParserException(
            "unparsable temporalString('" + temporalString + "')");
    }


    /**
     * DateFormats for <code>xs:date</code>.
     */
    private static final DateFormat[] XS_DATE_FORMATS = getXSTemporalFormats(
        new String[]{
            "yyyy-MM-ddXXX",
            "yyyy-MM-dd"
        });


    /**
     * Parses <code>xs:date</code>.
     *
     * @param dateString date string
     * @return parsed Date or null if given <code>dateString</code> is null.
     * @throws XmlPullParserException if an XML error occurs.
     */
    static Date parseXSDate(final String dateString)
        throws XmlPullParserException {

        return parseXSTemporal(XS_DATE_FORMATS, dateString);
    }


    /**
     * DateFormats for <code>xs:time</code>.
     */
    private static final DateFormat[] XS_TIME_FORMATS = getXSTemporalFormats(
        new String[]{
            "HH:mm:ss.SSSXXX",
            "HH:mm:ssXXX",
            "HH:mm:ss.SSS",
            "HH:mm:ss"
        });


    /**
     * Parses <code>xs:time</code> string to Date.
     *
     * @param timeString <code>xs:time</code> string
     * @return parsed Date or null if <code>timeString</code> is null.
     * @throws XmlPullParserException if an XML error occurs.
     */
    static Date parseXSTime(final String timeString)
        throws XmlPullParserException {

        return parseXSTemporal(XS_TIME_FORMATS, timeString);
    }


    /**
     * DateFormats for <code>xs:dateTime</code>.
     */
    private static final DateFormat[] XS_DATE_TIME_FORMATS =
        getXSTemporalFormats(
        new String[]{
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
            "yyyy-MM-dd'T'HH:mm:ssXXX",
            "yyyy-MM-dd'T'HH:mm:ss.SSS",
            "yyyy-MM-dd'T'HH:mm:ss"
        });


    /**
     * 
     * @param dateTimeString
     * @return
     * @throws XmlPullParserException 
     */
    static Date parseXSDateTime(final String dateTimeString)
        throws XmlPullParserException {

        return parseXSTemporal(XS_DATE_TIME_FORMATS, dateTimeString);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static Date getXSDateAttribute(final XmlPullParser parser,
                                          final String namespace,
                                          final String name)
        throws XmlPullParserException, IOException {

        final String dateString = parser.getAttributeValue(namespace, name);
        if (dateString == null) {
            return null;
        }

        return parseXSDate(dateString);
    }


    /**
     * Returns the attribute value as a Date.
     *
     * @param parser parser
     * @param namespace tag namespace
     * @param name tag name
     * @return parsed Date or null if no attribute found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Date getXSTimeAttribute(final XmlPullParser parser,
                                          final String namespace,
                                          final String name)
        throws XmlPullParserException, IOException {

        final String timeString = parser.getAttributeValue(namespace, name);
        if (timeString == null) {
            return null;
        }

        return parseXSTime(timeString);
    }


    /**
     * Parses an <code>xs:dateTime</code> attribute and returns as Date.
     *
     * @param parser parser
     * @param namespace tag namespace
     * @param name tag name
     * @return parsed Date or null if no attribute found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Date getXSDateTimeAttribute(final XmlPullParser parser,
                                              final String namespace,
                                              final String name)
        throws XmlPullParserException, IOException {

        final String dateTimeString = parser.getAttributeValue(namespace, name);
        if (dateTimeString == null) {
            return null;
        }

        return parseXSDateTime(dateTimeString);
    }


    /**
     * Parses a <code>xs:date</code> text and returns as a Date.
     *
     * @param parser parser
     * @return parsed Date or null if current tag is an empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Date nextXSDate(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        if (parser == null) {
            throw new NullPointerException("null parser");
        }

        final String value = nextNillable(parser);
        if (value == null) {
            return null;
        }

        return parseXSDate(value);
    }


    /**
     * Parses a <code>xs:time</code> text and returns as a Date.
     *
     * @param parser parser
     * @return parsed Date or null if current tag is an empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Date nextXSTime(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        if (parser == null) {
            throw new NullPointerException("null parser");
        }

        final String value = nextNillable(parser);
        if (value == null) {
            return null;
        }

        return parseXSTime(value);
    }


    /**
     * Parsed a <code>xs:dateTime</code> text and returns as a Date.
     *
     * @param parser parser
     * @return parsed Date or null if current tag is an empty tag
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Date nextXSDateTime(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        if (parser == null) {
            throw new NullPointerException("null parser");
        }

        final String value = nextNillable(parser);
        if (value == null) {
            return null;
        }

        return parseXSDateTime(value);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static Integer getIntAttribute(final XmlPullParser parser,
                                          final String namespace,
                                          final String name)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespace, name);
        if (value == null) {
            return null;
        }

        return Integer.valueOf(value);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static Long getLongAttribute(final XmlPullParser parser,
                                        final String namespace,
                                        final String name)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespace, name);
        if (value == null) {
            return null;
        }

        return Long.valueOf(value);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static Float getFloatAttribute(final XmlPullParser parser,
                                          final String namespace,
                                          final String name)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespace, name);
        if (value == null) {
            return null;
        }

        return Float.valueOf(value);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static Double getDoubleAttribute(final XmlPullParser parser,
                                            final String namespace,
                                            final String name)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespace, name);
        if (value == null) {
            return null;
        }

        return Double.valueOf(value);
    }


    /**
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static String nextNillable(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        if (parser.isEmptyElementTag()) {
            parser.nextTag();
            return null;
        }

        return parser.nextText();
    }


    /**
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static Integer nextInt(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String value = nextNillable(parser);
        if (value == null) {
            return null;
        }

        return Integer.valueOf(value);
    }


    /**
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static Long nextLong(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String value = nextNillable(parser);
        if (value == null) {
            return null;
        }

        return Long.valueOf(value);
    }


    /**
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static Float nextFloat(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String value = nextNillable(parser);
        if (value == null) {
            return null;
        }

        return Float.valueOf(value);
    }


    /**
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static Double nextDouble(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String value = nextNillable(parser);
        if (value == null) {
            return null;
        }

        return Double.valueOf(value);
    }


    static boolean parseXSBoolean(final String value)
        throws XmlPullParserException {

        if (value == null) {
            throw new NullPointerException("null value");
        }

        if ("true".equals(value) || "1".equals(value)) {
            return true;
        }

        if ("false".equals(value) || "0".equals(value)) {
            return false;
        }

        throw new XmlPullParserException(
            "unparsable xs:boolean value: " + value);
    }


    public static Boolean getBooleanAttribute(final XmlPullParser parser,
                                              final String namespace,
                                              final String name)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespace, name);
        if (value == null) {
            return null;
        }

        return parseXSBoolean(value);
    }


    /**
     * 
     * @param parser
     * @return the parsed value or null if the current tag is an empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Boolean nextBoolean(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String value = nextNillable(parser);
        if (value == null) {
            return null;
        }

        return parseXSBoolean(value);
    }


    private XmlPullParsableHelper() {
        super();
    }


}

