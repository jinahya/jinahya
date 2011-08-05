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

import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KDOMElementLocator extends ElementLocator {


    /**
     * 
     * @param document
     * @return 
     */
    public static ElementLocator parse(final Document document) {

        if (document == null) {
            throw new NullPointerException("null document");
        }

        final Element rootElement = document.getRootElement();
        if (rootElement == null) {
            throw new IllegalArgumentException("no root element");
        }

        return new KDOMElementLocator(parse(rootElement));
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

        String namespaceURI = element.getNamespace();
        if (namespaceURI == null) {
            namespaceURI = ELNode.NULL_NS_URI;
        }
        final String localName = element.getName();

        final ELElement elelement = new ELElement(namespaceURI, localName);

        final int attributeCount = element.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attributeNamespaceURI = element.getAttributeNamespace(i);
            if (ELNode.XMLNS_ATTRIBUTE_NS_URI.equals(attributeNamespaceURI)) {
                continue;
            }
            if (attributeNamespaceURI == null) {
                attributeNamespaceURI = ELNode.NULL_NS_URI;
            }
            final String attributeLocalName = element.getAttributeName(i);
            final String attributeValue = element.getAttributeValue(i);
            elelement.attributes.put(
                ELNode.jamesClark(attributeNamespaceURI, attributeLocalName),
                new ELAttribute(attributeNamespaceURI, attributeLocalName,
                                attributeValue));
        }

        String text = null;

        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            switch (element.getType(i)) {
                case Node.CDSECT:
                case Node.TEXT:
                    if (text == null) {
                        text = element.getText(i);
                    }
                    break;
                case Node.ELEMENT:
                    elelement.elements.add(parse((Element) element.getChild(i)));
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
     * @param element element to print
     * @return a new document contains to which given <code>element</code> has
     *         been printed.
     */
    public static Document print(final ELElement element) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        final Document document = new Document();

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

        print(element, document, getNamespaces(element));
    }


    /**
     * 
     * @param child
     * @param namesapces
     * @param parent
     */
    private static void print(final ELElement child, final Node parent,
                              final Map namesapces) {

        if (child == null) {
            throw new NullPointerException("null child");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        if (namesapces == null) {
            throw new NullPointerException("null namespaces");
        }

        final int childCount = parent.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            parent.removeChild(i);
        }

        final Element element = parent.createElement(
            child.namespaceURI, child.localName);
        parent.addChild(Node.ELEMENT, element);

        for (Iterator i = child.attributes.values().iterator(); i.hasNext();) {
            final ELAttribute attribute = (ELAttribute) i.next();
            element.setAttribute(attribute.namespaceURI, attribute.localName,
                                 attribute.value);
        }

        if (!child.elements.isEmpty()) {
            for (Iterator i = child.elements.iterator(); i.hasNext();) {
                print((ELElement) i.next(), element, namesapces);
            }
            return;
        }

        if (child.text != null) {
            element.addChild(Node.TEXT, child.text);
        }
    }


    /**
     * Creates a new instance.
     *
     * @param root root element
     */
    public KDOMElementLocator(final ELElement root) {
        super(root);
    }
}

