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

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.Parent;
import org.jdom.Text;


/**
 * A JDOM implementation.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://www.jdom.org/">JDOM</a>
 */
public class JDOMElementLocator extends ElementLocator {


    /**
     * Parses given <code>document</code> and creates a new instance.
     *
     * @param document document to parse
     * @return a new instance.
     */
    public static ElementLocator parse(final Document document) {

        if (document == null) {
            throw new NullPointerException("null document");
        }

        final Element rootElement = document.getRootElement();
        if (rootElement == null) {
            throw new IllegalArgumentException("no root element");
        }

        return new JDOMElementLocator(parse(rootElement));
    }


    /**
     * Parses given JDOM Element to ELElement.
     *
     * @param element JDOM Element to parse
     * @return converted ELElement
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

        final List attributes = element.getAttributes();
        for (int i = 0; i < attributes.size(); i++) {
            final Attribute attribute = (Attribute) attributes.get(i);
            String attributeNamespaceURI = attribute.getNamespaceURI();
            if (ELNode.XMLNS_ATTRIBUTE_NS_URI.equals(attributeNamespaceURI)) {
                continue;
            }
            if (attributeNamespaceURI == null) {
                attributeNamespaceURI = ELNode.NULL_NS_URI;
            }
            final String attributeLocalName = attribute.getName();
            final String attributeValue = attribute.getValue();
            elelement.getAttributes().put(
                ELNode.jamesClark(attributeNamespaceURI, attributeLocalName),
                new ELAttribute(attributeNamespaceURI, attributeLocalName,
                                attributeValue));
        }

        String text = null;

        final Iterator i = element.getContent().iterator();
        while (i.hasNext()) {
            final Object child = i.next();
            if (child instanceof Text) {
                if (text == null) {
                    text = ((Text) child).getText();
                }
            } else if (child instanceof Element) {
                elelement.getElements().add(parse((Element) child));
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
     * @param element element to print
     * @return a new Document
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
     * Prints given <code>element</code> to specified <code>document</code>.
     *
     * @param element element to print
     * @param document document to which the element is printed
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
     * Appends given <code>child</code> to <code>parent</code>.
     *
     * @param child child element
     * @param parent parent
     * @param namespaces namespaces
     */
    private static void print(final ELElement child, final Parent parent,
                              final Map namespaces) {

        if (child == null) {
            throw new NullPointerException("null child");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        if (namespaces == null) {
            throw new NullPointerException("null namespaces");
        }

        final Element element = new Element(
            child.getLocalName(), child.getNamespaceURI());

        if (parent instanceof Document) {
            final Document document = (Document) parent;
            if (document.hasRootElement()) {
                document.removeContent(document.getRootElement());
            }
            ((Document) parent).addContent(element);
        } else {
            ((Element) parent).addContent(element);
        }

        for (Iterator i = child.getAttributes().values().iterator();
             i.hasNext();) {
            final ELAttribute elattribute = (ELAttribute) i.next();
            if (elattribute.getNamespaceURI().equals(ELNode.NULL_NS_URI)) {
                final Attribute attribute = new Attribute(
                    elattribute.getLocalName(), elattribute.getValue(),
                    Namespace.NO_NAMESPACE);
                element.setAttribute(attribute);
                continue;
            }
            final Namespace namespace = Namespace.getNamespace(
                (String) namespaces.get(elattribute.getNamespaceURI()),
                elattribute.getNamespaceURI());
            final Attribute attribute = new Attribute(
                elattribute.getLocalName(), elattribute.getValue(), namespace);
            element.setAttribute(attribute);
        }

        if (!child.getElements().isEmpty()) {
            for (Iterator i = child.getElements().iterator(); i.hasNext();) {
                print((ELElement) i.next(), element, namespaces);
            }
            return;
        }

        if (child.getText() != null) {
            element.setText(child.getText());
        }
    }


    /**
     * Creates a new instance.
     *
     * @param root root element
     */
    public JDOMElementLocator(final ELElement root) {
        super(root);
    }
}

