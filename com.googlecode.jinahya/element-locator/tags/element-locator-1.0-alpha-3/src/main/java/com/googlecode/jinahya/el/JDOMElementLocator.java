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


package com.googlecode.jinahya.el;


import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.Parent;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class JDOMElementLocator extends ElementLocator<Document> {


    public static ElementLocator<Document> newInstance(
        final Document document) {

        if (document == null) {
            throw new NullPointerException("null document");
        }

        final Element element = document.getRootElement();
        if (element == null) {
            throw new IllegalArgumentException("no root element");
        }

        return newInstance(element);
    }


    public static ElementLocator<Document> newInstance(final Element element) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        return new JDOMElementLocator(parse(element));
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

        final ELElement _element = new ELElement(namespaceURI, localName);

        @SuppressWarnings("unchecked")
        final List<Attribute> attributes = element.getAttributes();
        for (Attribute attribute : attributes) {
            String attributeNamespaceURI = attribute.getNamespaceURI();
            if (ELNode.XMLNS_ATTRIBUTE_NS_URI.equals(attributeNamespaceURI)) {
                continue;
            }
            if (attributeNamespaceURI == null) {
                attributeNamespaceURI = ELNode.NULL_NS_URI;
            }
            final String attributeLocalName = attribute.getName();
            final String attributeValue = attribute.getValue();
            _element.attributes.put(
                ELNode.express(attributeNamespaceURI, attributeLocalName),
                new ELAttribute(attributeNamespaceURI, attributeLocalName,
                                attributeValue));
        }

        String text = null;

        @SuppressWarnings("unchecked")
        final List<Element> children = element.getChildren();
        for (Element child : children) {
            _element.elements.add(parse(child));
        }

        if (_element.elements.isEmpty()) {
            _element.text = element.getValue();
        }

        return _element;
    }


    /**
     * 
     * @param root 
     */
    protected JDOMElementLocator(final ELElement root) {
        super(root);
    }


    @Override
    protected void print(final ELElement root, final Document document,
                         final Map<String, String> namespaces) {

        if (root == null) {
            throw new NullPointerException("null root");
        }

        if (document == null) {
            throw new NullPointerException("null document");
        }

        if (namespaces == null) {
            throw new NullPointerException("null namespaces");
        }

        print(root, namespaces, document);
    }


    /**
     * 
     * @param _element
     * @param namespaces
     * @param parent
     */
    private void print(final ELElement _element,
                       final Map<String, String> namespaces,
                       final Parent parent) {

        if (_element == null) {
            throw new NullPointerException("null _element");
        }

        if (namespaces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        final Element element = new Element(
            _element.localName, _element.namespaceURI);
        if (parent instanceof Document) {
            ((Document) parent).addContent(element);
        } else {
            ((Element) parent).addContent(element);
        }

        for (ELAttribute _attribute : _element.attributes.values()) {
            if (_attribute.namespaceURI.equals(ELNode.NULL_NS_URI)) {
                final Attribute attribute = new Attribute(
                    _attribute.localName, _attribute.value,
                    Namespace.NO_NAMESPACE);
                element.setAttribute(attribute);
                continue;
            }
            final Namespace namespace = Namespace.getNamespace(namespaces.get(
                _attribute.namespaceURI), _attribute.namespaceURI);
            final Attribute attribute = new Attribute(
                _attribute.localName, _attribute.value, namespace);
            element.setAttribute(attribute);
        }

        if (_element.elements.isEmpty()) {
            element.setText(_element.text);
        } else {
            for (ELElement grandchild : _element.elements) {
                print(grandchild, namespaces, element);
            }
        }
    }
}

