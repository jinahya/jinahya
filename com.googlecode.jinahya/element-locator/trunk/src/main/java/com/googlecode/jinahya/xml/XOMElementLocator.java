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

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.ParentNode;
import nu.xom.Text;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XOMElementLocator extends ElementLocator {


    /**
     * 
     * @param document
     * @return 
     */
    public static ElementLocator parse(final Document document) {

        if (document == null) {
            throw new NullPointerException("null document");
        }

        final Element element = document.getRootElement();
        if (element == null) {
            throw new IllegalArgumentException("no root element");
        }

        return new XOMElementLocator(parse(element));
    }


    /**
     * 
     * @param element element to be parsed
     * @return an ELElement
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

        final int attributeCount = element.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            final Attribute attribute = element.getAttribute(i);
            String attributeNamespaceURI = attribute.getNamespaceURI();
            if (ELNode.XMLNS_ATTRIBUTE_NS_URI.equals(attributeNamespaceURI)) {
                continue;
            }
            if (attributeNamespaceURI == null) {
                attributeNamespaceURI = ELNode.NULL_NS_URI;
            }
            final String attributeLocalName = attribute.getLocalName();
            final String attributeValue = attribute.getValue();
            elelement.attributes.put(
                ELNode.jamesClark(attributeNamespaceURI, attributeLocalName),
                new ELAttribute(attributeNamespaceURI, attributeLocalName,
                                attributeValue));
        }

        String text = null;

        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final Node child = element.getChild(i);
            if (child instanceof Text) {
                if (text == null) {
                    text = element.getValue();
                }
            } else if (child instanceof Element) {
                elelement.elements.add(parse((Element) element.getChild(i)));
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
     * @return 
     */
    public static Document print(final ELElement element) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        final Document document =
            new Document(new Element("ignore:me", "http://ignore.me"));

        print(element, document);

        return document;
    }


    /**
     * 
     * @param child
     * @param document 
     */
    public static void print(final ELElement child, final Document document) {

        if (child == null) {
            throw new NullPointerException("null child");
        }

        if (document == null) {
            throw new NullPointerException("null document");
        }

        print(child, document, getNamespaces(child));
    }


    /**
     * 
     * @param child
     * @param namesapces
     * @param parent
     */
    private static void print(final ELElement child, final ParentNode parent,
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

        final Element element = new Element(
            getQualifiedName(child, namesapces), child.namespaceURI);

        if (parent instanceof Document) {
            final Element previousRoot = ((Document) parent).getRootElement();
            parent.replaceChild(previousRoot, element);
        } else {
            parent.appendChild(element);
        }

        for (Iterator i = child.attributes.values().iterator(); i.hasNext();) {
            final ELAttribute elattribute = (ELAttribute) i.next();
            final Attribute attribute = new Attribute(
                getQualifiedName(elattribute, namesapces),
                elattribute.namespaceURI, elattribute.value);
            element.addAttribute(attribute);
        }

        if (!child.elements.isEmpty()) {
            for (Iterator i = child.elements.iterator(); i.hasNext();) {
                print((ELElement) i.next(), element, namesapces);
            }
            return;
        }

        if (child.text != null) {
            element.appendChild(child.text);
        }
    }


    /**
     * Creates new instance.
     *
     * @param root the root element
     */
    public XOMElementLocator(final ELElement root) {
        super(root);
    }
}

