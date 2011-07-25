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

import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KDOMElementLocator extends ElementLocator<Document> {


    public static ElementLocator<Document> newInstance(
        final String namespaceURI, final String localName) {

        return new KDOMElementLocator(new ELElement(namespaceURI, localName));
    }


    /**
     * 
     * @param document
     * @return 
     */
    public static ElementLocator<Document> parse(final Document document) {

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
                ELNode.express(attributeNamespaceURI, attributeLocalName),
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
     * @param elelement
     * @param namesapces
     * @param parent
     */
    private static void print(final ELElement elelement,
                              final Map<String, String> namesapces,
                              final Node parent) {

        if (elelement == null) {
            throw new NullPointerException("null elelement");
        }

        if (namesapces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        final Element child = parent.createElement(
            elelement.namespaceURI, elelement.localName);
        parent.addChild(Node.ELEMENT, child);

        for (ELAttribute _attribute : elelement.attributes.values()) {
            child.setAttribute(_attribute.namespaceURI, _attribute.localName,
                               _attribute.value);
        }

        if (elelement.elements.isEmpty()) {
            if (elelement.text != null) {
                child.addChild(Node.TEXT, elelement.text);
            }
            return;
        }

        for (ELElement grandchild : elelement.elements) {
            print(grandchild, namesapces, child);
        }
    }


    /**
     * 
     * @param root 
     */
    private KDOMElementLocator(final ELElement root) {
        super(root);
    }


    /**
     * 
     * @return 
     */
    public final Document print() {

        return toDocument(new Document());
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

