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
     * Parses <code>xsi:nill</code> attribute.
     *
     * @param parser parser
     * @return <code>xsi:nill</code> value
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static boolean getXSINilAttribute(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        return getBooleanAttribute(
            parser, XmlConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "nil", false);
    }


    /**
     * Parses an attribute as <code>xs:date</code>.
     *
     * @param parser parser
     * @param namespaceURI namespace
     * @param localName name
     * @return parsed Date value or null if no attribute found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Date getXSDateAttribute(final XmlPullParser parser,
                                          final String namespaceURI,
                                          final String localName)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespaceURI, localName);
        if (value == null) {
            return null;
        }

        return XSDateAdapter.parseXSDate(value);
    }


    /**
     * Parses an attribute as <code>xs:time</code>.
     *
     * @param parser parser
     * @param namespaceURI tag namespace
     * @param localName tag name
     * @return parsed Date value or null if no attribute found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Date getXSTimeAttribute(final XmlPullParser parser,
                                          final String namespaceURI,
                                          final String localName)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespaceURI, localName);
        if (value == null) {
            return null;
        }

        return XSTimeAdapter.parseXSTime(value);
    }


    /**
     * Parses an attribute as <code>xs:dateTime</code>.
     *
     * @param parser parser
     * @param namespaceURI tag namespace
     * @param localName tag name
     * @return parsed Date or null if no attribute found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Date getXSDateTimeAttribute(final XmlPullParser parser,
                                              final String namespaceURI,
                                              final String localName)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespaceURI, localName);
        if (value == null) {
            return null;
        }

        return XSDateTimeAdapter.parseXSDateTime(value);
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

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        return XSDateAdapter.parseXSDate(text);
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

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        return XSTimeAdapter.parseXSTime(text);
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

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        return XSDateTimeAdapter.parseXSDateTime(text);
    }


    /**
     * Parses an attribute as an Integer.
     *
     * @param parser parser
     * @param namespaceURI namespace
     * @param localName name
     * @return parsed Integer value or null if the tag is an empty.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Integer getIntAttribute(final XmlPullParser parser,
                                          final String namespaceURI,
                                          final String localName)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespaceURI, localName);
        if (value == null) {
            return null;
        }

        return Integer.valueOf(value);
    }


    /**
     * Parses an attribute as an int.
     *
     * @param parser parser
     * @param namespaceURI attribute namespace
     * @param localName attribute name
     * @param defaultValue default value
     * @return parsed int value or <code>defaultValue</code> if attribute not
     *         found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static int getIntAttribute(final XmlPullParser parser,
                                      final String namespaceURI,
                                      final String localName,
                                      final int defaultValue)
        throws XmlPullParserException, IOException {

        final Integer value = getIntAttribute(parser, namespaceURI, localName);
        if (value == null) {
            return defaultValue;
        }

        return value;
    }


    /**
     * Parses an attribute as a Long.
     *
     * @param parser parser
     * @param namespaceURI attribute namespace
     * @param localName attribute name
     * @return parsed Long value or null if attribute not found
     * @throws XmlPullParserException if an XML error occurs
     * @throws IOException if an I/O error occurs.
     */
    public static Long getLongAttribute(final XmlPullParser parser,
                                        final String namespaceURI,
                                        final String localName)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespaceURI, localName);
        if (value == null) {
            return null;
        }

        return Long.valueOf(value);
    }


    /**
     * Parses an attribute as a long.
     *
     * @param parser parser
     * @param namespaceURI attribute namespace
     * @param localName attribute name
     * @param defaultValue default value
     * @return parsed long value or <code>defaultValue</code> if attribute not
     *         found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static long getLongAttribute(final XmlPullParser parser,
                                        final String namespaceURI,
                                        final String localName,
                                        final long defaultValue)
        throws XmlPullParserException, IOException {

        final Long value = getLongAttribute(parser, namespaceURI, localName);
        if (value == null) {
            return defaultValue;
        }

        return value.longValue();
    }


    /**
     * Parses an attribute as a Float.
     *
     * @param parser parser
     * @param namespaceURI attribute namespace
     * @param localName attribute name
     * @return parsed Float value or null if attribute not found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Float getFloatAttribute(final XmlPullParser parser,
                                          final String namespaceURI,
                                          final String localName)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespaceURI, localName);
        if (value == null) {
            return null;
        }

        return Float.valueOf(value);
    }


    /**
     * Parses an attribute as a float.
     *
     * @param parser parser
     * @param namespaceURI attribute namespace
     * @param localName attribute name
     * @param defaultValue default value
     * @return parsed float value or <code>defaultValue</code> if attribute not
     *         found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static float getFloatAttribute(final XmlPullParser parser,
                                          final String namespaceURI,
                                          final String localName,
                                          final float defaultValue)
        throws XmlPullParserException, IOException {

        final Float value = getFloatAttribute(parser, namespaceURI, localName);
        if (value == null) {
            return defaultValue;
        }

        return value.floatValue();
    }


    /**
     * Parses an attribute as a Double.
     *
     * @param parser parser
     * @param namespaceURI attribute namespace
     * @param localName attribute name
     * @return parsed Double value or null if attribute not found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Double getDoubleAttribute(final XmlPullParser parser,
                                            final String namespaceURI,
                                            final String localName)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespaceURI, localName);
        if (value == null) {
            return null;
        }

        return Double.valueOf(value);
    }


    /**
     * Parses an attribute as a double.
     *
     * @param parser parser
     * @param namespaceURI attribute namespace
     * @param localName attribute name
     * @param defaultValue default value
     * @return parsed double value or <code>defaultValue</code> if attribute not
     *         found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static double getDoubleAttribute(final XmlPullParser parser,
                                            final String namespaceURI,
                                            final String localName,
                                            final double defaultValue)
        throws XmlPullParserException, IOException {

        final Double value = getDoubleAttribute(
            parser, namespaceURI, localName);
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

        if (parser == null) {
            throw new NullPointerException("null parser");
        }

        final boolean nil = getXSINilAttribute(parser);

        final String text = parser.nextText();

        if ((text == null || text.length() == 0) && nil) {
            return null;
        }

        return text;
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

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        return Integer.valueOf(text);
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

        final Integer text = nextInt(parser);
        if (text == null) {
            return defaultValue;
        }

        return text.intValue();
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

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        return Long.valueOf(text);
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

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        return Float.valueOf(text);
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

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        return Double.valueOf(text);
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
     * @param namespaceURI namespace
     * @param localName name
     * @return parsed Boolean value or null if attribute not found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static Boolean getBooleanAttribute(final XmlPullParser parser,
                                              final String namespaceURI,
                                              final String localName)
        throws XmlPullParserException, IOException {

        final String value = parser.getAttributeValue(namespaceURI, localName);
        if (value == null) {
            return null;
        }

        return XSBooleanAdapter.parseXSBoolean(value);
    }


    /**
     * Parses an attribute as a boolean.
     *
     * @param parser parser
     * @param namespaceURI namespace
     * @param localName name
     * @param defaultValue default value
     * @return parsed boolean value or <code>defaultValue</code> if no attribute
     *         found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs
     */
    public static boolean getBooleanAttribute(final XmlPullParser parser,
                                              final String namespaceURI,
                                              final String localName,
                                              final boolean defaultValue)
        throws XmlPullParserException, IOException {

        final Boolean value = getBooleanAttribute(
            parser, namespaceURI, localName);
        if (value == null) {
            return defaultValue;
        }

        return value.booleanValue();
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

        final String text = nextNillableText(parser);
        if (text == null) {
            return null;
        }

        return XSBooleanAdapter.parseXSBoolean(text);
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

