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
     * @return parsed Date value or null if no attribute found
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
     * Parses an attribute as <code>xs:dateTime</code>.
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
     * Parses <code>nextText()</code> as <code>xs:date</code>.
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

        final String string = nextNillableText(parser);
        if (string == null) {
            return null;
        }

        return XSDateAdapter.parseXSDate(string);
    }


    /**
     * Parses <code>xs:time</code> text as a Date.
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

        final String string = nextNillableText(parser);
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

        final String string = nextNillableText(parser);
        if (string == null) {
            return null;
        }

        return XSDateTimeAdapter.parseXSDateTime(string);
    }


    /**
     * Parses an attribute as an Integer.
     *
     * @param parser parser
     * @param namespace namespace
     * @param name name
     * @return parsed Integer value or null if the tag is an empty.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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
     * Parses an attribute as an int.
     *
     * @param parser parser
     * @param namespace attribute namespace
     * @param name attribute name
     * @param defaultValue default value
     * @return parsed int value or <code>defaultValue</code> if attribute not
     *         found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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
     * Parses an attribute as a Long.
     *
     * @param parser parser
     * @param namespace attribute namespace
     * @param name attribute name
     * @return parsed Long value or null if attribute not found
     * @throws XmlPullParserException if an XML error occurs
     * @throws IOException if an I/O error occurs.
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
     * Parses an attribute as a long.
     *
     * @param parser parser
     * @param namespace attribute namespace
     * @param name attribute name
     * @param defaultValue default value
     * @return parsed long value or <code>defaultValue</code> if attribute not
     *         found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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
     * Parses an attribute as a Float.
     *
     * @param parser parser
     * @param namespace attribute namespace
     * @param name attribute name
     * @return parsed Float value or null if attribute not found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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


    /**
     * Parses an attribute as a float.
     *
     * @param parser parser
     * @param namespace attribute namespace
     * @param name attribute name
     * @param defaultValue default value
     * @return parsed float value or <code>defaultValue</code> if attribute not
     *         found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
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
     * Parses an attribute as a Double.
     *
     * @param parser parser
     * @param namespace attribute namespace
     * @param name attribute name
     * @return parsed Double value or null if attribute not found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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
     * Parses an attribute as a double.
     *
     * @param parser parser
     * @param namespace attribute namespace
     * @param name attribute name
     * @param defaultValue default value
     * @return parsed double value or <code>defaultValue</code> if attribute not
     *         found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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
     * Parses <code>nextText</code>.
     *
     * @param parser parser
     * @return <code>nextText</code> value or null if current tag is an empty
     *         tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static String nextNillableText(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final boolean nil = getBooleanAttribute(
            parser, XmlConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "nil", false);

        String value = parser.nextText();
        if (value.length() == 0 && nil) {
            return null;
        }

        return value;
    }


    /**
     * Parses <code>nextText()</code> as an Integer.
     *
     * @param parser parser
     * @return parsed Integer value or null if current tag is an empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Integer nextInt(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String string = nextNillableText(parser);
        if (string == null) {
            return null;
        }

        return Integer.valueOf(string);
    }


    /**
     * Parses <code>nextText</code> as an int.
     *
     * @param parser parser
     * @param defaultValue default value
     * @return parsed int value or <code>defaultValue</code> if current tag is
     *         an empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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
     * Parses <code>nextText</code> as a Long.
     *
     * @param parser parser
     * @return parsed Long value or null if current tag is an empty tag.
     * @throws XmlPullParserException if an XML error occurs
     * @throws IOException if an I/O error occurs.
     */
    public static Long nextLong(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String string = nextNillableText(parser);
        if (string == null) {
            return null;
        }

        return Long.valueOf(string);
    }


    /**
     * Parses <code>nextText()</code> as a long value.
     *
     * @param parser parser
     * @param defaultValue default value
     * @return parsed long value or <code>defaultValue</code> if nill.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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
     * Parses <code>nextText()</code> as a Float value.
     *
     * @param parser parser
     * @return parsed value or null if nill
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs
     */
    public static Float nextFloat(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String string = nextNillableText(parser);
        if (string == null) {
            return null;
        }

        return Float.valueOf(string);
    }


    /**
     * Parses <code>nextText()</code> as a float value.
     *
     * @param parser parser
     * @param defaultValue default value
     * @return parsed value or <code>defaultValue</code> if nill
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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
     * Parses <code>nextText</code> as a Double value.
     *
     * @param parser parser
     * @return parsed Double value or null if current tag is nill
     * @throws XmlPullParserException is an XML error occurs.
     * @throws IOException if an I/O error occurs
     */
    public static Double nextDouble(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String string = nextNillableText(parser);
        if (string == null) {
            return null;
        }

        return Double.valueOf(string);
    }


    /**
     * Parses <code>nextText</code> as a double.
     *
     * @param parser parser
     * @param defaultValue default value
     * @return parsed double value or the <code>defaultValue</code> if the tag
     *         is an empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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
     * Parses an attribute as a Boolean.
     *
     * @param parser parser
     * @param namespace namespace
     * @param name name
     * @return parsed Boolean value or null if attribute not found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
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
     * Parses an attribute as a boolean.
     *
     * @param parser parser
     * @param namespace namespace
     * @param name name
     * @param defaultValue default value
     * @return parsed boolean value or <code>defaultValue</code> if no attribute
     *         found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs
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
     * Parses <code>nextText</code> as a Boolean value.
     *
     * @param parser parser
     * @return Boolean value or null if the tag is an empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Boolean nextBoolean(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        final String string = nextNillableText(parser);
        if (string == null) {
            return null;
        }

        return XSBooleanAdapter.parseXSBoolean(string);
    }


    /**
     * Parses <code>nextText()</code> as a boolean value.
     *
     * @param parser parser
     * @param defaultValue default value
     * @return boolean value or the <code>defaultValue</code> if the tag is an
     *         empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static boolean nextBoolean(final XmlPullParser parser,
                                      final boolean defaultValue)
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

