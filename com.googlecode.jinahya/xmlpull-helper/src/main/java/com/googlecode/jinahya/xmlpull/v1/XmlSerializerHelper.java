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
     * Serializes an optional attribute.
     *
     * @param serializer serializer
     * @param namespace XML namespace URI
     * @param name XML local name
     * @param value attribute value
     * @throws IOException if an I/O error occurs.
     */
    public static void optionalAttribute(final XmlSerializer serializer,
                                         final String namespace,
                                         final String name, final Object value)
        throws IOException {

        if (value == null) {
            return;
        }

        serializer.attribute(namespace, name, value.toString());
    }


    /**
     * Serializes a <code>Boolean</code> attribute.
     *
     * @param serializer serializer
     * @param namespace namespace
     * @param name name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void booleanAttribute(final XmlSerializer serializer,
                                        final String namespace,
                                        final String name, final Boolean value)
        throws IOException {

        optionalAttribute(serializer, namespace, name, value);
    }


    /**
     * Serializes a <code>boolean</code> attribute.
     *
     * @param serializer serializer
     * @param namespace namespace
     * @param name name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void booleanAttribute(final XmlSerializer serializer,
                                        final String namespace,
                                        final String name, final boolean value)
        throws IOException {

        booleanAttribute(serializer, namespace, name, Boolean.valueOf(name));
    }


    /**
     * Serializes a <code>Byte</code> value.
     *
     * @param serializer serializer
     * @param namespace namespace
     * @param name name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void byteAttribute(final XmlSerializer serializer,
                                     final String namespace, final String name,
                                     final Byte value)
        throws IOException {

        optionalAttribute(serializer, namespace, name, value);
    }


    /**
     * Serializes a <code>byte</code> value.
     *
     * @param serializer serializer
     * @param namespace namespace
     * @param name name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void byteAttribute(final XmlSerializer serializer,
                                     final String namespace, final String name,
                                     final byte value)
        throws IOException {

        byteAttribute(serializer, namespace, name, Byte.valueOf(value));
    }


    /**
     * Serializes a <code>Short</code> value.
     *
     * @param serializer serializer
     * @param namespace namespace
     * @param name name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void shortAttribute(final XmlSerializer serializer,
                                      final String namespace, final String name,
                                      final Short value)
        throws IOException {

        optionalAttribute(serializer, namespace, name, value);
    }


    /**
     * Serializes a <code>short</code> value.
     *
     * @param serializer serializer
     * @param namespace namespace
     * @param name name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void shortAttribute(final XmlSerializer serializer,
                                      final String namespace, final String name,
                                      final short value)
        throws IOException {

        shortAttribute(serializer, namespace, name, Short.valueOf(value));
    }


    /**
     * Serializes an <code>Integer</code> value.
     *
     * @param serializer serializer
     * @param namespace namespace
     * @param name name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void intAttribute(final XmlSerializer serializer,
                                    final String namespace, final String name,
                                    final Integer value)
        throws IOException {

        optionalAttribute(serializer, namespace, name, value);
    }


    /**
     * Serializes an <code>int</code> value.
     *
     * @param serializer serializer
     * @param namespace namespace
     * @param name name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void intAttribute(final XmlSerializer serializer,
                                    final String namespace, final String name,
                                    final int value)
        throws IOException {

        intAttribute(serializer, namespace, name, Integer.valueOf(value));
    }


    /**
     * Serializes a <code>Long</code> value.
     *
     * @param serializer serializer
     * @param namespace XML namespace URI
     * @param name XML name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void longAttribute(final XmlSerializer serializer,
                                     final String namespace, final String name,
                                     final Long value)
        throws IOException {

        optionalAttribute(serializer, namespace, name, value);
    }


    /**
     * Serializes a <code>long</code> attribute.
     *
     * @param serializer serializer
     * @param namespace XML namespace URI
     * @param name XML local name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void longAttribute(final XmlSerializer serializer,
                                     final String namespace, final String name,
                                     final long value)
        throws IOException {

        longAttribute(serializer, namespace, name, Long.valueOf(value));
    }


    /**
     * Serializes a <code>Float</code> attribute.
     *
     * @param serializer serializer
     * @param namespace XML namespace URI
     * @param name XML local name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void floatAttribute(final XmlSerializer serializer,
                                      final String namespace, final String name,
                                      final Float value)
        throws IOException {

        optionalAttribute(serializer, namespace, name, value);
    }


    /**
     * Serializes a <code>float</code> value.
     *
     * @param serializer serializer
     * @param namespace XML namespace URI
     * @param name XML local name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void floatAttribute(final XmlSerializer serializer,
                                      final String namespace, final String name,
                                      final float value)
        throws IOException {

        floatAttribute(serializer, namespace, name,
                       Float.valueOf(value));
    }


    /**
     * Serializes a <code>Double</code> attribute.
     *
     * @param serializer serializer
     * @param namespace XML namespace URI
     * @param name XML local name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void doubleAttribute(final XmlSerializer serializer,
                                       final String namespace,
                                       final String name, final Double value)
        throws IOException {

        optionalAttribute(serializer, namespace, name, value);
    }


    /**
     * Serializes a <code>double</code> value.
     *
     * @param serializer serializer
     * @param namespace XML namespace URI
     * @param name XML local name
     * @param value value
     * @throws IOException if an I/O error occurs.
     */
    public static void doubleAttribute(final XmlSerializer serializer,
                                       final String namespace,
                                       final String name, final double value)
        throws IOException {

        doubleAttribute(serializer, namespace, name, Double.valueOf(value));
    }


    /**
     * Serializes a tag. A NullPointerException will be thrown if
     * <code>nillable</code> is false and text is null.
     *
     * @param serializer serializer
     * @param namespace XML namespace URI
     * @param name XML local name
     * @param text text
     * @param nillable nillable flag
     * @throws IOException if an I/O error occurs.
     */
    public static void simpleTag(final XmlSerializer serializer,
                                 final String namespace, final String name,
                                 final Object text, final boolean nillable)
        throws IOException {

        if (!nillable && text == null) {
            throw new NullPointerException(
                "{" + namespace + "}" + name + "/text()");
        }

        nillableSimpleTag(serializer, namespace, name, text);
    }


    /**
     * Serializes a nillable tag.
     *
     * @param serializer serializer
     * @param namespace XML namespace URI
     * @param name XML local name
     * @param text text
     * @throws IOException if an I/O error occurs.
     */
    public static void nillableSimpleTag(final XmlSerializer serializer,
                                         final String namespace,
                                         final String name, final Object text)
        throws IOException {

        if (serializer == null) {
            throw new NullPointerException("null serializer");
        }

        serializer.startTag(namespace, name);

        if (text == null) {
            xsiNilAttribute(serializer);
        } else {
            serializer.text(text.toString());
        }

        serializer.endTag(namespace, name);
    }


    /**
     * Creates a new instance.
     */
    private XmlSerializerHelper() {
        super();
    }


}

