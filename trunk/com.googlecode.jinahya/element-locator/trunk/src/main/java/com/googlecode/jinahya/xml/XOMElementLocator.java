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
 * A XOM implementation.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://www.xom.nu/">XOM</a>
 */
public class XOMElementLocator extends ElementLocator {


    /**
     * Parses given <code>document</code> and creates a new ElementLocator.
     *
     * @param document the document to be parsed
     * @return a new ElementLocator instance.
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
     * Parses given <code>element</code> and creates a new ELElement.
     *
     * @param element element to be parsed
     * @return a new ELElement
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
            elelement.getAttributes().put(
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
                elelement.getElements().add(
                    parse((Element) element.getChild(i)));
            }
        }

        if (elelement.getElements().isEmpty()) {
            elelement.setText(text);
        }

        return elelement;
    }


    /**
     * Prints given <code>element</code> to a new Document.
     *
     * @param element the element to be printed
     * @return a new Document to which <code>element</code> has been printed.
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
     * Prints given <code>child</code> to specified <code>document</code>.
     *
     * @param child the root element to be printed
     * @param document the document to which <code>child</code> is printed.
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
     * Prints given <code>child</code> to specified <code>parent</code>.
     *
     * @param child child
     * @param parent parent
     * @param namesapces namespaces
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
            getQualifiedName(child, namesapces), child.getNamespaceURI());

        if (parent instanceof Document) {
            final Element previousRoot = ((Document) parent).getRootElement();
            parent.replaceChild(previousRoot, element);
        } else {
            parent.appendChild(element);
        }

        for (Iterator i = child.getAttributes().values().iterator();
             i.hasNext();) {
            final ELAttribute elattribute = (ELAttribute) i.next();
            final Attribute attribute = new Attribute(
                getQualifiedName(elattribute, namesapces),
                elattribute.getNamespaceURI(), elattribute.getValue());
            element.addAttribute(attribute);
        }

        if (!child.getElements().isEmpty()) {
            for (Iterator i = child.getElements().iterator(); i.hasNext();) {
                print((ELElement) i.next(), element, namesapces);
            }
            return;
        }

        if (child.getText() != null) {
            element.appendChild(child.getText());
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

