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
import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * A W3C DOM implementation.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DOMElementLocator extends ElementLocator {


    /**
     * Parses given <code>document</code> and creates a new instance.
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
     * Parses given <code>element</code>.
     *
     * @param element element to parse
     * @return new ELElement
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
            elelement.getAttributes().put(
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
                    elelement.getElements().add(parse((Element) childNode));
                    break;
                default:
                    break;
            }
        }

        if (elelement.getElements().isEmpty()) {
            elelement.setText(text);
        }

        return elelement;
    }


    /**
     * Prints given <code>element</code> to specified <code>document</code>.
     *
     * @param element element
     * @param document document
     */
    public static void print(final ELElement element, final Document document) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        if (document == null) {
            throw new NullPointerException("null document");
        }

        print(element, document, getNamespaces(element), document);
    }


    /**
     * Prints given <code>child</code> to <code>parent</code>.
     *
     * @param child child to be attached to the parent
     * @param parent parent to which child is appended
     * @param namesapces namespaces
     * @param document document
     */
    private static void print(final ELElement child, final Node parent,
                              final Map namesapces, final Document document) {

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
            child.getNamespaceURI(), getQualifiedName(child, namesapces));
        if (parent instanceof Document) {
            final Element documentElement =
                ((Document) parent).getDocumentElement();
            if (documentElement != null) {
                parent.removeChild(documentElement);
            }
        }
        parent.appendChild(element);

        for (Iterator i = child.getAttributes().values().iterator();
             i.hasNext();) {
            final ELAttribute elattribute = (ELAttribute) i.next();
            element.setAttributeNS(elattribute.getNamespaceURI(),
                                   getQualifiedName(elattribute, namesapces),
                                   elattribute.getValue());
        }

        if (!child.getElements().isEmpty()) {
            for (Iterator i = child.getElements().iterator(); i.hasNext();) {
                print((ELElement) i.next(), element, namesapces, document);
            }
            return;
        }

        if (child.getText() != null) {
            element.appendChild(document.createTextNode(child.getText()));
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

