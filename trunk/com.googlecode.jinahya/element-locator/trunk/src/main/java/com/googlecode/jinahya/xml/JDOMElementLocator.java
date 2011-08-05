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
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
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
            elelement.attributes.put(
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
                elelement.elements.add(parse((Element) child));
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
     * @param namespaces
     * @param parent 
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
            child.localName, child.namespaceURI);

        if (parent instanceof Document) {
            final Document document = (Document) parent;
            if (document.hasRootElement()) {
                document.removeContent(document.getRootElement());
            }
            ((Document) parent).addContent(element);
        } else {
            ((Element) parent).addContent(element);
        }

        for (Iterator i = child.attributes.values().iterator(); i.hasNext();) {
            final ELAttribute elattribute = (ELAttribute) i.next();
            if (elattribute.namespaceURI.equals(ELNode.NULL_NS_URI)) {
                final Attribute attribute = new Attribute(
                    elattribute.localName, elattribute.value,
                    Namespace.NO_NAMESPACE);
                element.setAttribute(attribute);
                continue;
            }
            final Namespace namespace = Namespace.getNamespace(
                (String) namespaces.get(elattribute.namespaceURI),
                elattribute.namespaceURI);
            final Attribute attribute = new Attribute(
                elattribute.localName, elattribute.value, namespace);
            element.setAttribute(attribute);
        }

        if (!child.elements.isEmpty()) {
            for (Iterator i = child.elements.iterator(); i.hasNext();) {
                print((ELElement) i.next(), element, namespaces);
            }
            return;
        }

        if (child.text != null) {
            element.setText(child.text);
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

