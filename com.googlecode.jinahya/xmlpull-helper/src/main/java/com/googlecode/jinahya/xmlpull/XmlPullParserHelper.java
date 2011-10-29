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
     * @throws ParseException 
     */
    private static Date parseXSTemporal(final DateFormat[] temporalFormats,
                                        final String temporalString)
        throws ParseException {

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

        throw new ParseException(
            "failed to parse given temporalString('" + temporalString + "')",
            0);
    }


    /**
     * 
     */
    static final DateFormat[] XS_DATE_FORMATS = getXSTemporalFormats(
        new String[]{
            "yyyy-MM-ssXXX",
            "yyyy-MM-ss"
        });


    /**
     * 
     * @param dateString
     * @return
     * @throws ParseException 
     */
    static Date parseXSDate(final String dateString)
        throws ParseException {

        return parseXSTemporal(XS_DATE_FORMATS, dateString);
    }


    /**
     * 
     */
    static final DateFormat[] XS_TIME_FORMATS = getXSTemporalFormats(
        new String[]{
            "HH:mm:ss.SSSXXX",
            "HH:mm:ssXXX",
            "HH:mm:ss.SSS",
            "HH:mm:ss"
        });


    /**
     * 
     * @param timeString
     * @return
     * @throws ParseException 
     */
    static Date parseXSTime(final String timeString)
        throws ParseException {

        return parseXSTemporal(XS_TIME_FORMATS, timeString);
    }


    /**
     * 
     */
    static final DateFormat[] XS_DATE_TIME_FORMATS = getXSTemporalFormats(
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
     * @throws ParseException 
     */
    static Date parseXSDateTime(final String dateTimeString)
        throws ParseException {

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
     * @throws ParseException 
     */
    public static Date getXSDateAttributeValue(final XmlPullParser parser,
                                               final String namespace,
                                               final String name)
        throws XmlPullParserException, IOException, ParseException {

        final String dateString = parser.getAttributeValue(namespace, name);
        if (dateString == null) {
            return null;
        }

        return parseXSDate(dateString);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public static Date getXSTimeAttributeValue(final XmlPullParser parser,
                                               final String namespace,
                                               final String name)
        throws XmlPullParserException, IOException, ParseException {

        final String timeString = parser.getAttributeValue(namespace, name);
        if (timeString == null) {
            return null;
        }

        return parseXSTime(timeString);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public static Date getXSDateTimeAttributeValue(final XmlPullParser parser,
                                                   final String namespace,
                                                   final String name)
        throws XmlPullParserException, IOException, ParseException {

        final String dateTimeString = parser.getAttributeValue(namespace, name);
        if (dateTimeString == null) {
            return null;
        }

        return parseXSDateTime(dateTimeString);
    }


    /**
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public static Date nextXSDateText(final XmlPullParser parser)
        throws XmlPullParserException, IOException, ParseException {

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
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public static Date nextXSTimeText(final XmlPullParser parser)
        throws XmlPullParserException, IOException, ParseException {

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
     * 
     * @param parser
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public static Date nextXSDateTimeText(final XmlPullParser parser)
        throws XmlPullParserException, IOException, ParseException {

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
     * 
     * @param parser
     * @param namespace
     * @param name
     * @param numberClass
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    private static Object getNumberAttributeValue(final XmlPullParser parser,
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
    public static Byte getByteAttributeValue(final XmlPullParser parser,
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
    public static Short getShortAttributeValue(final XmlPullParser parser,
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
    public static Integer getIntAttributeValue(final XmlPullParser parser,
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
    public static Long getLongAttributeValue(final XmlPullParser parser,
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
    public static Float getFloatAttributeValue(final XmlPullParser parser,
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
    public static Double getDoubleAttributeValue(final XmlPullParser parser,
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
    private static Object nextNumberText(final XmlPullParser parser,
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
    public static Byte nextByteText(final XmlPullParser parser)
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
    public static Short nextShortText(final XmlPullParser parser)
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
    public static Integer nextIntText(final XmlPullParser parser)
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
    public static Long nextLongText(final XmlPullParser parser)
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
    public static Float nextFloatText(final XmlPullParser parser)
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
    public static Double nextDoubleText(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        return (Double) nextNumberText(parser, Double.class);
    }


    private XmlPullParserHelper() {
        super();
    }


}

