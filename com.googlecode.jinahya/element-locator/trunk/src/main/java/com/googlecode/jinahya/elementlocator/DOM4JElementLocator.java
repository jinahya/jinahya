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

import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DOM4JElementLocator extends ElementLocator<Document> {


    /**
     * Creates a new empty (root only) instance.
     *
     * @param namespaceURI root element's name space URI.
     * @param localName root element's local namer
     * @return a new root-only instance.
     */
    public static ElementLocator<Document> newInstance(
        final String namespaceURI, final String localName) {

        return new DOM4JElementLocator(new ELElement(namespaceURI, localName));
    }


    /**
     * Creates a new instance.
     *
     * @param document document
     * @return new instance of DOMElementLocator.
     */
    public static ElementLocator<Document> parse(final Document document) {

        if (document == null) {
            throw new NullPointerException("null document");
        }

        final Element rootElement = document.getRootElement();
        if (rootElement == null) {
            throw new IllegalArgumentException("no root element");
        }

        return new DOM4JElementLocator(parse(rootElement));
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
        final String localName = element.getName();

        final ELElement elelement = new ELElement(namespaceURI, localName);


        final int attributeCount = element.attributeCount();
        for (int i = 0; i < attributeCount; i++) {
            final Attribute attribute = element.attribute(i);
            String attributeNamespaceURI = attribute.getNamespaceURI();
            if (ELNode.XMLNS_ATTRIBUTE_NS_URI.equals(
                attributeNamespaceURI)) {
                continue;
            }
            if (attributeNamespaceURI == null) {
                attributeNamespaceURI = ELNode.NULL_NS_URI;
            }
            final String attributeLocalName = attribute.getName();
            final String attributeValue = attribute.getValue();
            elelement.attributes.put(
                ELNode.express(attributeNamespaceURI, attributeLocalName),
                new ELAttribute(attributeNamespaceURI, attributeLocalName,
                                attributeValue));
        }

        String text = null;

        final int nodeCount = element.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            final Node node = element.node(i);
            switch (node.getNodeType()) {
                case Node.CDATA_SECTION_NODE:
                case Node.TEXT_NODE:
                    if (text == null) {
                        text = node.getText();
                    }
                    break;
                case Node.ELEMENT_NODE:
                    elelement.elements.add(parse((Element) node));
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
     * Prints given <code>elelement</code> to specified <code>parent</code>.
     *
     * @param elelement element to print
     * @param namesapces name space map
     * @param parent the parent to which the element is added.
     */
    private static void print(final ELElement elelement,
                              final Map<String, String> namesapces,
                              final Branch parent) {

        if (elelement == null) {
            throw new NullPointerException("null elelement");
        }

        if (namesapces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        if (parent instanceof Document) {
            final Document document = (Document) parent;
            final Element rootElement = document.getRootElement();
            if (rootElement != null) {
                document.remove(rootElement);
            }
        }

        final Element element = parent.addElement(
            getQualifiedName(elelement, namesapces), elelement.namespaceURI);

        for (ELAttribute elattribute : elelement.attributes.values()) {
            final Namespace namespace = new Namespace(
                namesapces.get(elattribute.namespaceURI),
                elattribute.namespaceURI);
            final QName qName = new QName(
                elattribute.localName, namespace,
                getQualifiedName(elattribute, namesapces));
            element.addAttribute(qName, elattribute.value);
        }

        if (!elelement.elements.isEmpty()) {
            for (ELElement grandchild : elelement.elements) {
                print(grandchild, namesapces, element);
            }
            return;
        }

        if (elelement.text != null) {
            element.setText(elelement.text);
        }
    }


    /**
     * Creates a new instance.
     *
     * @param root root element
     */
    private DOM4JElementLocator(final ELElement root) {
        super(root);
    }


    /**
     * Prints contents to a new document.
     *
     * @return a new document
     */
    public final Document print() {

        return print(DocumentHelper.createDocument());
    }


    @Override
    public Document print(final Document document) {

        if (document == null) {
            throw new NullPointerException("document");
        }

        print(getRoot(), getNamespaces(), document);

        return document;
    }
}

