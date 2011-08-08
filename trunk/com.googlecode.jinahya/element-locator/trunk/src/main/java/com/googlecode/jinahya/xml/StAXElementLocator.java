/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.xml;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;


/**
 * A XOM implementation.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://www.xom.nu/">XOM</a>
 */
public class StAXElementLocator extends ElementLocator {


    /**
     * Parses from given <code>reader</code> and creates a new ElementLocator.
     *
     * @param reader reader to parse
     * @return a new ElementLocator
     * @throws XMLStreamException if an XML error occurs.
     */
    public static ElementLocator parse(final XMLStreamReader reader)
        throws XMLStreamException {

        if (reader == null) {
            throw new NullPointerException("null reader");
        }

        reader.require(XMLStreamConstants.START_DOCUMENT, null, null);

        reader.nextTag();

        reader.require(XMLStreamConstants.START_ELEMENT, null, null);

        String namespaceURI = reader.getNamespaceURI();
        if (namespaceURI == null) {
            namespaceURI = ELNode.NULL_NS_URI;
        }
        final String localName = reader.getLocalName();

        final ELElement root = new ELElement(namespaceURI, localName);

        final ElementLocator locator = new StAXElementLocator(root);

        parse(reader, locator);

        reader.require(XMLStreamConstants.END_ELEMENT, null, null);

        reader.next();
        reader.require(XMLStreamConstants.END_DOCUMENT, null, null);

        return locator;
    }


    /**
     * Parses given <code>reader</code> and appends element to specified
     * <code>locator</code>.
     *
     * @param reader reader
     * @param locator locator
     * @throws XMLStreamException if an XML error occurs.
     */
    private static void parse(final XMLStreamReader reader,
                              final ElementLocator locator)
        throws XMLStreamException {

        if (reader == null) {
            throw new NullPointerException("null reader");
        }

        if (locator == null) {
            throw new NullPointerException("null locator");
        }

        reader.require(XMLStreamConstants.START_ELEMENT, null, null);

        final int attributeCount = reader.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String namespaceURI = reader.getAttributeNamespace(i);
            if (namespaceURI == null) {
                namespaceURI = ELNode.NULL_NS_URI;
            }
            final String localName = reader.getAttributeLocalName(i);
            final String value = reader.getAttributeValue(i);
            locator.setAttribute(namespaceURI, localName, value);
        }

        String text = null;
        while (reader.next() != XMLStreamConstants.END_ELEMENT) {
            switch (reader.getEventType()) {
                case XMLStreamConstants.CDATA:
                case XMLStreamConstants.CHARACTERS:
                    if (text == null) {
                        text = reader.getText();
                    }
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    String namespaceURI = reader.getNamespaceURI();
                    if (namespaceURI == null) {
                        namespaceURI = ELNode.NULL_NS_URI;
                    }
                    final String localName = reader.getLocalName();
                    locator.addChild(namespaceURI, localName);
                    parse(reader, locator);
                    locator.locateParent();
                    break;
                default:
                    break;
            }
        }

        final ELElement current = locator.getCurrent();
        if (current.getElements().isEmpty()) {
            current.setText(text);
        }
    }


    /**
     * Prints given <code>locator</code> to specified <code>writer</code>.
     *
     * @param locator locator to print
     * @param writer the writer to which the locator is printed
     * @throws XMLStreamException if an XML error occurs.
     */
    public static void print(final ElementLocator locator,
                             final XMLStreamWriter writer)
        throws XMLStreamException {

        if (locator == null) {
            throw new NullPointerException("null locator");
        }

        if (writer == null) {
            throw new NullPointerException("null writer");
        }

        print(locator, writer, "UTF-8", "1.0");
    }


    /**
     * Prints given <code>locator</code> to specified <code>writer</code>.
     *
     * @param locator the locator to print
     * @param writer the writer to which the locator is printed
     * @param encoding output encoding for XML declaration
     * @param version xml version for XML declaration
     * @throws XMLStreamException if an XML error occurs.
     */
    public static void print(final ElementLocator locator,
                             final XMLStreamWriter writer,
                             final String encoding, final String version)
        throws XMLStreamException {

        if (locator == null) {
            throw new NullPointerException("null locator");
        }

        if (writer == null) {
            throw new NullPointerException("null writer");
        }

        if (encoding == null) {
            throw new NullPointerException("null encoding");
        }

        if (version == null) {
            throw new NullPointerException("null version");
        }

        writer.writeStartDocument(encoding, version);

        final ELElement element = locator.getRoot();
        print(element, writer, getNamespaces(element), true);

        writer.writeEndDocument();
    }


    /**
     * 
     * @param element
     * @param writer
     * @param namespaces
     * @throws XMLStreamException 
     */
    private static void print(final ELElement element,
                              final XMLStreamWriter writer,
                              final Map namespaces)
        throws XMLStreamException {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        if (writer == null) {
            throw new NullPointerException("null writer");
        }

        if (namespaces == null) {
            throw new NullPointerException("null namespaces");
        }

        print(element, writer, namespaces, false);
    }


    /**
     * 
     * @param element
     * @param writer
     * @param namespaces
     * @param writeNamespace
     * @throws XMLStreamException 
     */
    private static void print(final ELElement element,
                              final XMLStreamWriter writer,
                              final Map namespaces,
                              final boolean writeNamespace)
        throws XMLStreamException {


        if (element == null) {
            throw new NullPointerException("null element");
        }

        if (writer == null) {
            throw new NullPointerException("null writer");
        }

        if (namespaces == null) {
            throw new NullPointerException("null namespaces");
        }

        final String elementNamespaceURI = element.getNamespaceURI();
        if (elementNamespaceURI.equals(ELNode.NULL_NS_URI)) {
            writer.writeStartElement(element.getLocalName());
        } else {
            writer.writeStartElement(
                (String) namespaces.get(elementNamespaceURI),
                element.getLocalName(), elementNamespaceURI);
        }

        if (writeNamespace) {
            for (Iterator i = namespaces.entrySet().iterator(); i.hasNext();) {
                final Entry entry = (Entry) i.next();
                final String namespaceURI = (String) entry.getKey();
                if (namespaceURI.equals(ELNode.NULL_NS_URI)) {
                    continue;
                }
                final String namespacePrefix = (String) entry.getValue();
                writer.writeNamespace(namespacePrefix, namespaceURI);
            }
        }

        final Map attributes = element.getAttributes();
        for (Iterator i = attributes.values().iterator(); i.hasNext();) {
            final ELAttribute attribute = (ELAttribute) i.next();
            final String attributeNamespaceURI = attribute.getNamespaceURI();
            if (attributeNamespaceURI.equals(ELNode.NULL_NS_URI)) {
                writer.writeAttribute(attribute.getLocalName(),
                                      attribute.getValue());
            } else {
                writer.writeAttribute(
                    (String) namespaces.get(attributeNamespaceURI),
                    attributeNamespaceURI, attribute.getLocalName(),
                    attribute.getValue());
            }
        }

        final List children = element.getElements();
        if (!children.isEmpty()) {
            for (Iterator i = children.iterator(); i.hasNext();) {
                final ELElement child = (ELElement) i.next();
                print(child, writer, namespaces);
            }
        } else if (element.getText() != null) {
            writer.writeCharacters(element.getText());
        }

        writer.writeEndElement();
    }


    public StAXElementLocator(final ELElement root) {
        super(root);
    }
}

