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
    public static void nilAttribute(final XmlSerializer serializer)
        throws IOException {

        serializer.attribute(
            XmlPullConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "nil", "true");
    }


    public static void optionalAttribute(final XmlSerializer serializer,
                                         final String namespaceURI,
                                         final String localName,
                                         final Object value)
        throws IOException {

        if (value == null) {
            return;
        }

        serializer.attribute(namespaceURI, localName, value.toString());
    }


    public static void booleanAttribute(final XmlSerializer serializer,
                                        final String namespaceURI,
                                        final String localName,
                                        final Boolean value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName, value);
    }


    public static void booleanAttribute(final XmlSerializer serializer,
                                        final String namespaceURI,
                                        final String localName,
                                        final boolean value)
        throws IOException {

        booleanAttribute(serializer, namespaceURI, localName,
                         Boolean.valueOf(localName));
    }


    public static void byteAttribute(final XmlSerializer serializer,
                                     final String namespaceURI,
                                     final String localName,
                                     final Byte value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName, value);
    }


    public static void byteAttribute(final XmlSerializer serializer,
                                     final String namespaceURI,
                                     final String localName,
                                     final byte value)
        throws IOException {

        byteAttribute(serializer, namespaceURI, localName, Byte.valueOf(value));
    }


    public static void shortAttribute(final XmlSerializer serializer,
                                      final String namespaceURI,
                                      final String localName,
                                      final Short value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName, value);
    }


    public static void shortAttribute(final XmlSerializer serializer,
                                      final String namespaceURI,
                                      final String localName,
                                      final short value)
        throws IOException {

        shortAttribute(serializer, namespaceURI, localName,
                       Short.valueOf(value));
    }


    public static void intAttribute(final XmlSerializer serializer,
                                    final String namespaceURI,
                                    final String localName,
                                    final Integer value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName, value);
    }


    public static void intAttribute(final XmlSerializer serializer,
                                    final String namespaceURI,
                                    final String localName,
                                    final int value)
        throws IOException {

        intAttribute(serializer, namespaceURI, localName,
                     Integer.valueOf(value));
    }


    public static void longAttribute(final XmlSerializer serializer,
                                     final String namespaceURI,
                                     final String localName,
                                     final Long value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName, value);
    }


    /**
     * Serializes a long attribute.
     *
     * @param serializer serializer
     * @param namespaceURI XML namespace URI
     * @param localName XML local name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void longAttribute(final XmlSerializer serializer,
                                     final String namespaceURI,
                                     final String localName,
                                     final long value)
        throws IOException {

        longAttribute(serializer, namespaceURI, localName, Long.valueOf(value));
    }


    /**
     * Serializes a Float attribute.
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
                                      final Float value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName, value);
    }


    /**
     * Serializes a float value.
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

        floatAttribute(serializer, namespaceURI, localName,
                       Float.valueOf(value));
    }


    /**
     * Serializes a Double attribute.
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
                                       final Double value)
        throws IOException {

        optionalAttribute(serializer, namespaceURI, localName, value);
    }


    /**
     * Serializes a double value.
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

        doubleAttribute(serializer, namespaceURI, localName,
                        Double.valueOf(value));
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
    public static void nillableTag(final XmlSerializer serializer,
                                   final String namespaceURI,
                                   final String localName, final Object text)
        throws IOException {

        serializer.startTag(namespaceURI, localName);

        if (text == null) {
            nilAttribute(serializer);
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

