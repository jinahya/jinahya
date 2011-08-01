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
public class JDOMElementLocator extends ElementLocator<Document> {


    /**
     * Creates a new empty (root only) instance.
     *
     * @param namespaceURI root element's name space URI
     * @param localName root element's local name
     * @return a new empty (root only) instance.
     */
    public static ElementLocator<Document> newInstance(
        final String namespaceURI, final String localName) {

        return new JDOMElementLocator(new ELElement(namespaceURI, localName));
    }


    /**
     * Parses given <code>document</code> and creates a new instance.
     *
     * @param document document to parse
     * @return a new instance.
     */
    public static ElementLocator<Document> parseInstance(
        final Document document) {

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

        final List<?> attributes = element.getAttributes();
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
                ELNode.express(attributeNamespaceURI, attributeLocalName),
                new ELAttribute(attributeNamespaceURI, attributeLocalName,
                                attributeValue));
        }

        String text = null;

        final Iterator<?> i = element.getContent().iterator();
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
     * @param elelement
     * @param namespaces
     * @param parent 
     */
    private static void print(final ELElement elelement,
                              final Map<String, String> namespaces,
                              final Parent parent) {

        if (elelement == null) {
            throw new NullPointerException("null elelement");
        }

        if (namespaces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        final Element element = new Element(
            elelement.localName, elelement.namespaceURI);

        if (parent instanceof Document) {
            final Document document = (Document) parent;
            if (document.hasRootElement()) {
                document.removeContent(document.getRootElement());
            }
            ((Document) parent).addContent(element);
        } else {
            ((Element) parent).addContent(element);
        }

        for (ELAttribute elattribute : elelement.attributes.values()) {
            if (elattribute.namespaceURI.equals(ELNode.NULL_NS_URI)) {
                final Attribute attribute = new Attribute(
                    elattribute.localName, elattribute.value,
                    Namespace.NO_NAMESPACE);
                element.setAttribute(attribute);
                continue;
            }
            final Namespace namespace = Namespace.getNamespace(namespaces.get(
                elattribute.namespaceURI), elattribute.namespaceURI);
            final Attribute attribute = new Attribute(
                elattribute.localName, elattribute.value, namespace);
            element.setAttribute(attribute);
        }

        if (!elelement.elements.isEmpty()) {
            for (ELElement grandchild : elelement.elements) {
                print(grandchild, namespaces, element);
            }
            return;
        }

        if (elelement.text != null) {
            element.setText(elelement.text);
        }
    }


    /**
     * 
     * @param root 
     */
    private JDOMElementLocator(final ELElement root) {
        super(root);
    }


    /**
     * A new document contains this element's content.
     *
     * @return a new document
     */
    public final Document toDocument() {

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

