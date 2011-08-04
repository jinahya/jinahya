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
public class DOMElementLocator extends ElementLocator {


    /**
     * Creates a new instance.
     *
     * @param document document
     * @return new instance of DOMElementLocator.
     */
    public static ElementLocator parse(final Document document) {

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
                ELNode.jamesClark(attributeNamespaceURI, attributeLocalName),
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
     * @param element
     * @param builder
     * @return 
     */
    public static Document print(final ELElement element,
                                 final DocumentBuilder builder) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        if (builder == null) {
            throw new NullPointerException("null builder");
        }

        final Document document = builder.newDocument();

        print(element, document);

        return document;
    }


    /**
     * 
     * @param element
     * @param document 
     */
    public static void print(final ELElement element, final Document document) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        if (document == null) {
            throw new NullPointerException("null document");
        }

        final Map<String, String> namespaces = getNamespaces(element);

        print(element, document, namespaces, document);
    }


    /**
     * 
     * @param child
     * @param parent
     * @param namesapces
     * @param document
     */
    private static void print(final ELElement child, final Node parent,
                              final Map<String, String> namesapces,
                              final Document document) {

        if (child == null) {
            throw new NullPointerException("null child");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        if (namesapces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (document == null) {
            throw new NullPointerException("null document");
        }

        final Element element = document.createElementNS(
            child.namespaceURI, getQualifiedName(child, namesapces));
        parent.appendChild(element);

        for (ELAttribute elattribute : child.attributes.values()) {
            element.setAttributeNS(elattribute.namespaceURI,
                                   getQualifiedName(elattribute, namesapces),
                                   elattribute.value);
        }

        if (!child.elements.isEmpty()) {
            for (ELElement grandchild : child.elements) {
                print(grandchild, element, namesapces, document);
            }
            return;
        }

        if (child.text != null) {
            element.appendChild(document.createTextNode(child.text));
        }
    }


    /**
     * Creates a new instance.
     *
     * @param root root element
     */
    public DOMElementLocator(final ELElement root) {
        super(root);
    }
}

