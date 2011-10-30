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


package com.googlecode.jinahya.xmlpull;


import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
public final class XmlPullParserHelper {


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
     * 
     * @param temporalFormats
     * @param temporalString
     * @return
     * @throws XmlPullParserException 
     */
    private static Date parseXSTemporal(final DateFormat[] temporalFormats,
                                        final String temporalString)
        throws XmlPullParserException {

        if (temporalString == null) {
            return null;
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
            "yyyy-MM-ssXXX",
            "yyyy-MM-ss"
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
            "yyyy-MM-ss'T'HH:mm:ss.SSSXXX",
            "yyyy-MM-ss'T'HH:mm:ssXXX",
            "yyyy-MM-ss'T'HH:mm:ss.SSS",
            "yyyy-MM-ss'T'HH:mm:ss"
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

        if (parser.isEmptyElementTag()) { // <tag/>
            return null;
        }

        final String dateText = parser.nextText();
        if (dateText.length() == 0) { // <tag></tag>
            return null;
        }

        return parseXSDate(dateText);
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

        if (parser.isEmptyElementTag()) { // <tag/>
            return null;
        }

        final String timeText = parser.nextText();
        if (timeText.length() == 0) { // <tag></tag>
            return null;
        }

        return parseXSTime(timeText);
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

        if (parser.isEmptyElementTag()) { // <tag/>
            return null;
        }

        final String dateTimeText = parser.nextText();
        if (dateTimeText.length() == 0) { // <tag></tag>
            return null;
        }

        return parseXSDateTime(dateTimeText);
    }


    /**
     * Parses an attribute and returns as a Number.
     *
     * @param parser parser
     * @param namespace attribute namespace
     * @param name attribute name
     * @param numberClass number class to convert
     * @return parsed Number instance of null if no attribute found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    static Object getNumberAttributeValue(final XmlPullParser parser,
                                          final String namespace,
                                          final String name,
                                          final Class numberClass)
        throws XmlPullParserException, IOException {

        if (!Number.class.isAssignableFrom(numberClass)) {
            throw new IllegalArgumentException(
                "numberClass(" + numberClass + ") is not assignable to "
                + Number.class);
        }

        final String value = parser.getAttributeValue(namespace, name);
        if (value == null) {
            return null;
        }

        try {
            final Method method = numberClass.getMethod(
                "valueOf", new Class[]{String.class});
            try {
                return method.invoke(null, new Object[]{value});
            } catch (IllegalAccessException iae) {
                iae.printStackTrace(System.err);
            } catch (InvocationTargetException ite) {
                ite.printStackTrace(System.err);
            }
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace(System.err);
        }

        return null;
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
    public static Byte getByteAttribute(final XmlPullParser parser,
                                        final String namespace,
                                        final String name)
        throws XmlPullParserException, IOException {

        return (Byte) getNumberAttributeValue(
            parser, namespace, name, Byte.class);
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
    public static Short getShortAttribute(final XmlPullParser parser,
                                          final String namespace,
                                          final String name)
        throws XmlPullParserException, IOException {

        return (Short) getNumberAttributeValue(
            parser, namespace, name, Short.class);
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

        return (Integer) getNumberAttributeValue(
            parser, namespace, name, Integer.class);
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

        return (Long) getNumberAttributeValue(
            parser, namespace, name, Long.class);
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

        return (Float) getNumberAttributeValue(
            parser, namespace, name, Float.class);
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

        return (Double) getNumberAttributeValue(
            parser, namespace, name, Double.class);
    }


    /**
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static String nextNillableText(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        if (parser.isEmptyElementTag()) {
            return null;
        }

        return parser.nextText();
    }


    /**
     * 
     * @param parser
     * @param numberClass
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    static Object nextNumberText(final XmlPullParser parser,
                                 final Class numberClass)
        throws XmlPullParserException, IOException {

        if (!Number.class.isAssignableFrom(numberClass)) {
            throw new IllegalArgumentException(
                "numberClass(" + numberClass + ") is not assignable to "
                + Number.class);
        }

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        try {
            final Method method = numberClass.getMethod(
                "valueOf", new Class[]{String.class});
            try {
                return method.invoke(null, new Object[]{text});
            } catch (IllegalAccessException iae) {
                iae.printStackTrace(System.err);
            } catch (InvocationTargetException ite) {
                ite.printStackTrace(System.err);
            }
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace(System.err);
        }

        return null;
    }


    /**
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static Byte nextByte(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        return (Byte) nextNumberText(parser, Byte.class);
    }


    /**
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static Short nextShort(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        return (Short) nextNumberText(parser, Short.class);
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

        return (Integer) nextNumberText(parser, Integer.class);
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

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        return Long.valueOf(text);
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

        return (Float) nextNumberText(parser, Float.class);
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

        return (Double) nextNumberText(parser, Double.class);
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

        return Boolean.valueOf(parseXSBoolean(value));
    }


    public static Boolean nextBoolean(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        return Boolean.valueOf(parseXSBoolean(text));
    }


    private XmlPullParserHelper() {
        super();
    }


}

