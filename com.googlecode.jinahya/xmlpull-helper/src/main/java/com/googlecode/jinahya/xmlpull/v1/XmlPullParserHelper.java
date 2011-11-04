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


import com.googlecode.jinahya.xmlpull.xs.XSBooleanAdapter;
import com.googlecode.jinahya.xmlpull.xs.XSDateAdapter;
import com.googlecode.jinahya.xmlpull.xs.XSDateTimeAdapter;
import com.googlecode.jinahya.xmlpull.xs.XSTimeAdapter;

import java.io.IOException;

import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class XmlPullParserHelper {


    /**
     * Parses an attribute as <code>xs:date</code>.
     *
     * @param parser parser
     * @param namespace namespace
     * @param name name
     * @return the parsed Date value or null if no attribute found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Date getXSDateAttribute(final XmlPullParser parser,
                                          final String namespace,
                                          final String name)
        throws XmlPullParserException, IOException {

        final String dateString = parser.getAttributeValue(namespace, name);
        if (dateString == null) {
            return null;
        }

        return XSDateAdapter.parseXSDate(dateString);
    }


    /**
     * Parses an attribute as <code>xs:time</code>.
     *
     * @param parser parser
     * @param namespace tag namespace
     * @param name tag name
     * @return parsed Date value or null if no attribute found.
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

        return XSTimeAdapter.parseXSTime(timeString);
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

        return XSDateTimeAdapter.parseXSDateTime(dateTimeString);
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

        final String string = nextNillable(parser);
        if (string == null) {
            return null;
        }

        return XSDateAdapter.parseXSDate(string);
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

        final String string = nextNillable(parser);
        if (string == null) {
            return null;
        }

        return XSTimeAdapter.parseXSTime(string);
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

        final String string = nextNillable(parser);
        if (string == null) {
            return null;
        }

        return XSDateTimeAdapter.parseXSDateTime(string);
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

        final String string = parser.getAttributeValue(namespace, name);
        if (string == null) {
            return null;
        }

        return Integer.valueOf(string);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @param defaultValue
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static int getIntAttribute(final XmlPullParser parser,
                                      final String namespace, final String name,
                                      final int defaultValue)
        throws XmlPullParserException, IOException {

        final Integer value = getIntAttribute(parser, namespace, name);
        if (value == null) {
            return defaultValue;
        }

        return value;
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

        final String string = parser.getAttributeValue(namespace, name);
        if (string == null) {
            return null;
        }

        return Long.valueOf(string);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @param defaultValue
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static long getLongAttribute(final XmlPullParser parser,
                                        final String namespace,
                                        final String name,
                                        final long defaultValue)
        throws XmlPullParserException, IOException {

        final Long value = getLongAttribute(parser, namespace, name);
        if (value == null) {
            return defaultValue;
        }

        return value.longValue();
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

        final String string = parser.getAttributeValue(namespace, name);
        if (string == null) {
            return null;
        }

        return Float.valueOf(string);
    }


    public static float getFloatAttribute(final XmlPullParser parser,
                                          final String namespace,
                                          final String name,
                                          final float defaultValue)
        throws XmlPullParserException, IOException {

        final Float value = getFloatAttribute(parser, namespace, name);
        if (value == null) {
            return defaultValue;
        }

        return value.floatValue();
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

        final String string = parser.getAttributeValue(namespace, name);
        if (string == null) {
            return null;
        }

        return Double.valueOf(string);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @param defaultValue
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static double getDoubleAttribute(final XmlPullParser parser,
                                            final String namespace,
                                            final String name,
                                            final double defaultValue)
        throws XmlPullParserException, IOException {

        final Double value = getDoubleAttribute(parser, namespace, name);
        if (value == null) {
            return defaultValue;
        }

        return value.doubleValue();
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
            parser.nextTag(); // move to end tag
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

        final String string = nextNillable(parser);
        if (string == null) {
            return null;
        }

        return Integer.valueOf(string);
    }


    /**
     * 
     * @param parser
     * @param defaultValue
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static int nextInt(final XmlPullParser parser,
                              final int defaultValue)
        throws XmlPullParserException, IOException {

        final Integer value = nextInt(parser);
        if (value == null) {
            return defaultValue;
        }

        return value.intValue();
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

        final String string = nextNillable(parser);
        if (string == null) {
            return null;
        }

        return Long.valueOf(string);
    }


    /**
     * 
     * @param parser
     * @param defaultValue
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static long nextLong(final XmlPullParser parser,
                                final long defaultValue)
        throws XmlPullParserException, IOException {

        final Long value = nextLong(parser);
        if (value == null) {
            return defaultValue;
        }

        return value.longValue();
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

        final String string = nextNillable(parser);
        if (string == null) {
            return null;
        }

        return Float.valueOf(string);
    }


    /**
     * 
     * @param parser
     * @param defaultValue
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static float nextFloat(final XmlPullParser parser,
                                  final float defaultValue)
        throws XmlPullParserException, IOException {

        final Float value = nextFloat(parser);
        if (value == null) {
            return defaultValue;
        }

        return value.floatValue();
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

        final String string = nextNillable(parser);
        if (string == null) {
            return null;
        }

        return Double.valueOf(string);
    }


    /**
     * 
     * @param parser
     * @param defaultValue
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static double nextDouble(final XmlPullParser parser,
                                    final double defaultValue)
        throws XmlPullParserException, IOException {

        final Double value = nextDouble(parser);
        if (value == null) {
            return defaultValue;
        }

        return value.doubleValue();
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
    public static Boolean getBooleanAttribute(final XmlPullParser parser,
                                              final String namespace,
                                              final String name)
        throws XmlPullParserException, IOException {

        final String string = parser.getAttributeValue(namespace, name);
        if (string == null) {
            return null;
        }

        return XSBooleanAdapter.parseXSBoolean(string);
    }


    /**
     * 
     * @param parser
     * @param namespace
     * @param name
     * @param defaultValue
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static boolean getBooleanAttribute(final XmlPullParser parser,
                                              final String namespace,
                                              final String name,
                                              final boolean defaultValue)
        throws XmlPullParserException, IOException {

        final Boolean value = getBooleanAttribute(parser, namespace, name);
        if (value == null) {
            return defaultValue;
        }

        return value;
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

        final String string = nextNillable(parser);
        if (string == null) {
            return null;
        }

        return XSBooleanAdapter.parseXSBoolean(string);
    }


    /**
     * 
     * @param parser
     * @param defaultValue
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static boolean nextBoolean(final XmlPullParser parser,
                                      boolean defaultValue)
        throws XmlPullParserException, IOException {

        final Boolean value = nextBoolean(parser);
        if (value == null) {
            return defaultValue;
        }

        return value.booleanValue();
    }


    /**
     * Creates a new instance.
     */
    private XmlPullParserHelper() {
        super();
    }


}

