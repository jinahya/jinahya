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


package com.googlecode.jinahya.elementlocator;


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
public class XOMElementLocator extends ElementLocator<Document> {


    /**
     * 
     * @param namespaceURI
     * @param localName
     * @return 
     */
    public static ElementLocator<Document> newInstance(
        final String namespaceURI, final String localName) {

        return new XOMElementLocator(new ELElement(namespaceURI, localName));
    }


    /**
     * 
     * @param document
     * @return 
     */
    public static ElementLocator<Document> parseInstance(
        final Document document) {

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
                ELNode.express(attributeNamespaceURI, attributeLocalName),
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

        if (elelement.elements.isEmpty() && text != null) {
            elelement.text = text;
        }

        return elelement;
    }


    /**
     * 
     * @param elelement
     * @param namesapces
     * @param parent
     */
    private static void print(final ELElement elelement,
                              final Map<String, String> namesapces,
                              final ParentNode parent) {

        if (elelement == null) {
            throw new NullPointerException("null elelement");
        }

        if (namesapces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        final Element element = new Element(
            getQualifiedName(elelement, namesapces), elelement.namespaceURI);

        if (parent instanceof Document) {
            final Element previousRoot = ((Document) parent).getRootElement();
            parent.replaceChild(previousRoot, element);
        } else {
            parent.appendChild(element);
        }

        for (ELAttribute elattribute : elelement.attributes.values()) {
            final Attribute attribute = new Attribute(
                getQualifiedName(elattribute, namesapces),
                elattribute.namespaceURI, elattribute.value);
            element.addAttribute(attribute);
        }

        if (elelement.elements.isEmpty()) {
            if (elelement.text != null) {
                element.appendChild(elelement.text);
            }
            return;
        }

        for (ELElement grandchild : elelement.elements) {
            print(grandchild, namesapces, element);
        }
    }


    /**
     * 
     * @param root 
     */
    private XOMElementLocator(final ELElement root) {
        super(root);
    }


    /**
     * 
     * @return 
     */
    public final Document toDocument() {

        // the tmp root element will be replaced
        return toDocument(new Document(new Element("tmp:tmp", "http://tmp")));
    }


    @Override
    public Document toDocument(final Document document) {

        if (document == null) {
            throw new NullPointerException("document");
        }

        print(getRoot(), getNamespaces(), document);

        return document;
    }
}

