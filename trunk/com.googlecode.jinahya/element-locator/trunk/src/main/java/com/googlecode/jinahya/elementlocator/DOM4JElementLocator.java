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
     * Creates a new instance.
     *
     * @param document document
     * @return new instance of DOMElementLocator.
     */
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


    /**
     * Creates a new instance.
     *
     * @param element element
     * @return new instance of DOMelementLocator
     */
    public static ElementLocator<Document> newInstance(final Element element) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        return new DOM4JElementLocator(parse(element));
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
            _element.attributes.put(
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
                    _element.elements.add(parse((Element) node));
                    break;
                default:
                    break;
            }
        }

        if (_element.elements.isEmpty()) {
            _element.text = text;
        }

        return _element;
    }


    /**
     * Creates a new instance.
     *
     * @param root root element
     */
    protected DOM4JElementLocator(final ELElement root) {
        super(root);
    }


    /**
     * 
     * @return 
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


    /**
     * 
     * @param _element
     * @param document
     * @param namesapces
     * @param parent
     */
    private void print(final ELElement _element,
                       final Map<String, String> namesapces,
                       final Branch parent) {

        if (_element == null) {
            throw new NullPointerException("null _element");
        }

        if (namesapces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        final Element element = parent.addElement(
            getQualifiedName(_element, namesapces), _element.namespaceURI);

        for (ELAttribute _attribute : _element.attributes.values()) {
            final Namespace namespace = new Namespace(
                namesapces.get(_attribute.namespaceURI),
                _attribute.namespaceURI);
            final QName qName = new QName(
                _attribute.localName, namespace,
                getQualifiedName(_attribute, namesapces));
            element.addAttribute(qName, _attribute.value);
        }

        if (_element.elements.isEmpty() && _element.text != null) {
            element.setText(_element.text);
            return;
        }

        for (ELElement child : _element.elements) {
            print(child, namesapces, element);
        }
    }
}

