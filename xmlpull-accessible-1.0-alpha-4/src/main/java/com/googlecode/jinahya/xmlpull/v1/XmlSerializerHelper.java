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

import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class XmlSerializerHelper {


    /**
     * Serializes <code>xsi:nill="true"</code> attribute.
     *
     * @param serializer serializer
     * @throws IOException if an I/O error occurs.
     */
    public static void xsiNilAttribute(final XmlSerializer serializer)
        throws IOException {

        serializer.attribute(
            XmlConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "nil", "true");
    }


    /**
     * Serializes an optional attribute. Nothing is going to be written if both
     * <code>value</code> and <code>defaultValue</code> are null.
     *
     * @param <T> value type parameter
     * @param serializer serializer.
     * @param namespaceURI XML namespace name
     * @param localName XML local name
     * @param value attribute value; can be null
     * @param defaultValue default value going to be written when the
     * <code>value</code> is null; can be null
     * @throws IOException if an I/O error occurs.
     */
    public static <T> void optionalAttribute(final XmlSerializer serializer,
                                             final String namespaceURI,
                                             final String localName,
                                             final T value,
                                             final T defaultValue)
        throws IOException {

        if (value == null) {
            if (defaultValue != null) {
                serializer.attribute(
                    namespaceURI, localName, defaultValue.toString());
            }
            return;
        }

        serializer.attribute(namespaceURI, localName, value.toString());
    }


    /**
     * Serializes a boolean attribute.
     *
     * @param serializer serializer
     * @param namespaceURI XML namespace name
     * @param localName XML local name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void booleanAttribute(final XmlSerializer serializer,
                                        final String namespaceURI,
                                        final String localName,
                                        final boolean value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName,
                          Boolean.valueOf(value), null);
    }


    /**
     * Serializes an <code>Integer</code> value.
     *
     * @param serializer serializer
     * @param namespaceURI namespace
     * @param localName name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void intAttribute(final XmlSerializer serializer,
                                    final String namespaceURI,
                                    final String localName,
                                    final int value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName,
                          Integer.valueOf(value), null);
    }


    /**
     * Serializes a <code>Long</code> value.
     *
     * @param serializer serializer
     * @param namespaceURI XML namespace URI
     * @param localName XML name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void longAttribute(final XmlSerializer serializer,
                                     final String namespaceURI,
                                     final String localName,
                                     final long value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName,
                          Long.valueOf(value), null);
    }


    /**
     * Serializes a <code>Float</code> attribute.
     *
     * @param serializer serializer
     * @param namespaceURI XML namespace URI
     * @param localName XML local name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void floatAttribute(final XmlSerializer serializer,
                                      final String namespaceURI,
                                      final String localName,
                                      final float value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName,
                          Float.valueOf(value), null);
    }


    /**
     * Serializes a <code>Double</code> attribute.
     *
     * @param serializer serializer
     * @param namespaceURI XML namespace URI
     * @param localName XML local name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void doubleAttribute(final XmlSerializer serializer,
                                       final String namespaceURI,
                                       final String localName,
                                       final double value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName,
                          Double.valueOf(value), null);
    }


    /**
     * Serializes a tag. A NullPointerException will be thrown if
     * <code>nillable</code> is false and text is null.
     *
     * @param serializer serializer
     * @param namespaceURI XML namespace URI
     * @param localName XML local name
     * @param text text
     * @param nillable nillable flag
     * @throws IOException if an I/O error occurs.
     */
    public static void simpleTag(final XmlSerializer serializer,
                                 final String namespaceURI,
                                 final String localName,
                                 final Object text, final boolean nillable)
        throws IOException {

        if (!nillable && text == null) {
            throw new NullPointerException("null text with !nillable");
        }

        nillableSimpleTag(serializer, namespaceURI, localName, text);
    }


    /**
     * Serializes a nillable tag.
     *
     * @param serializer serializer
     * @param namespaceURI XML namespace URI
     * @param localName XML local name
     * @param text text
     * @throws IOException if an I/O error occurs.
     */
    public static void nillableSimpleTag(final XmlSerializer serializer,
                                         final String namespaceURI,
                                         final String localName,
                                         final Object text)
        throws IOException {

        if (serializer == null) {
            throw new NullPointerException("null serializer");
        }

        serializer.startTag(namespaceURI, localName);

        if (text == null) {
            xsiNilAttribute(serializer);
        } else {
            serializer.text(text.toString());
        }

        serializer.endTag(namespaceURI, localName);
    }


    /**
     * Creates a new instance.
     */
    private XmlSerializerHelper() {
        super();
    }


}

