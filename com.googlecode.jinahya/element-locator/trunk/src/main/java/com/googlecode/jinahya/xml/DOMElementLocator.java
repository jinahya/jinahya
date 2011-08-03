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


import java.util.Map;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DOMElementLocator extends ElementLocator<Document> {


    /**
     * 
     * @param namespaceURI root element's name space URI
     * @param localName root element's local name
     * @return new empty (root-only) instance
     */
    public static ElementLocator<Document> newInstance(
        final String namespaceURI, final String localName) {

        return new DOMElementLocator(new ELElement(namespaceURI, localName));
    }


    /**
     * Creates a new instance.
     *
     * @param document document
     * @return new instance of DOMElementLocator.
     */
    public static ElementLocator<Document> parseInstance(
        final Document document) {

        if (document == null) {
            throw new NullPointerException("null document");
        }

        final Element documentElement = document.getDocumentElement();
        if (documentElement == null) {
            throw new IllegalArgumentException("no document element");
        }

        return new DOMElementLocator(parse(documentElement));
    }


    /**
     * 
     * @param element
     * @return 
     */
    private static ELElement parse(final Element element) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        String namespaceURI = element.getNamespaceURI();
        if (namespaceURI == null) {
            namespaceURI = ELNode.NULL_NS_URI;
        }
        final String localName = element.getLocalName();

        final ELElement elelement = new ELElement(namespaceURI, localName);

        final NamedNodeMap attributes = element.getAttributes();
        final int attributeLength = attributes.getLength();
        for (int i = 0; i < attributeLength; i++) {
            final Attr attribute = (Attr) attributes.item(i);
            String attributeNamespaceURI = attribute.getNamespaceURI();
            if (ELNode.XMLNS_ATTRIBUTE_NS_URI.equals(attributeNamespaceURI)) {
                continue;
            }
            if (attributeNamespaceURI == null) {
                attributeNamespaceURI = ELNode.NULL_NS_URI;
            }
            final String attributeLocalName = attribute.getLocalName();
            final String attributeValue = attribute.getNodeValue();
            elelement.attributes.put(
                ELNode.express(attributeNamespaceURI, attributeLocalName),
                new ELAttribute(attributeNamespaceURI, attributeLocalName,
                                attributeValue));
        }

        String text = null;

        final NodeList childNodes = element.getChildNodes();
        final int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            final Node childNode = childNodes.item(i);
            switch (childNode.getNodeType()) {
                case Node.CDATA_SECTION_NODE:
                case Node.TEXT_NODE:
                    if (text == null) {
                        text = childNode.getNodeValue();
                    }
                    break;
                case Node.ELEMENT_NODE:
                    elelement.elements.add(parse((Element) childNode));
                    break;
                default:
                    break;
            }
        }

        if (elelement.elements.isEmpty()) {
            elelement.text = text;
        }

        return elelement;
    }


    /**
     * 
     * @param elelement
     * @param document
     * @param namesapces
     * @param parent
     */
    private static void print(final ELElement elelement,
                              final Document document,
                              final Map<String, String> namesapces,
                              final Node parent) {

        if (elelement == null) {
            throw new NullPointerException("null elelement");
        }

        if (document == null) {
            throw new NullPointerException("null document");
        }

        if (namesapces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        final Element element = document.createElementNS(
            elelement.namespaceURI, getQualifiedName(elelement, namesapces));
        parent.appendChild(element);

        for (ELAttribute elattribute : elelement.attributes.values()) {
            element.setAttributeNS(elattribute.namespaceURI,
                                   getQualifiedName(elattribute, namesapces),
                                   elattribute.value);
        }

        if (elelement.elements.isEmpty()) {
            if (elelement.text != null) {
                element.appendChild(document.createTextNode(elelement.text));
            }
            return;
        }

        for (ELElement grandchild : elelement.elements) {
            print(grandchild, document, namesapces, element);
        }
    }


    /**
     * Creates a new instance.
     *
     * @param root root element
     */
    private DOMElementLocator(final ELElement root) {
        super(root);
    }


    /**
     * 
     * @param builder
     * @return 
     */
    public Document toDocument(final DocumentBuilder builder) {

        if (builder == null) {
            throw new NullPointerException("null builder");
        }

        return toDocument(builder.newDocument());
    }


    @Override
    public Document toDocument(final Document document) {

        if (document == null) {
            throw new NullPointerException("document");
        }

        print(getRoot(), document, getNamespaces(), document);

        return document;
    }
}

