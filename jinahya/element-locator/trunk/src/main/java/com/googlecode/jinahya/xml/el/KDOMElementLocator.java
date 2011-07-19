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


package com.googlecode.jinahya.xml.el;


import java.util.Map;

import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KDOMElementLocator extends ElementLocator<Document> {


    /**
     * 
     * @param document
     * @return 
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
     * 
     * @param element
     * @return 
     */
    public static ElementLocator<Document> newInstance(final Element element) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        return new KDOMElementLocator(parse(element));
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

        final ELElement _element = new ELElement(namespaceURI, localName);

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
            _element.attributes.put(
                ELNode.express(attributeNamespaceURI, attributeLocalName),
                new ELAttribute(attributeNamespaceURI, attributeLocalName,
                                attributeValue));
        }

        final int childCount = element.getChildCount();
        String text = null;
        for (int i = 0; i < childCount; i++) {
            switch (element.getType(i)) {
                case Node.CDSECT:
                case Node.TEXT:
                    if (text == null) {
                        text = element.getText(i);
                    }
                    break;
                case Node.ELEMENT:
                    _element.elements.add(parse((Element) element.getChild(i)));
                    break;
                default:
                    break;
            }
        }

        if (_element.elements.isEmpty() && text != null) {
            _element.text = text;
        }

        return _element;
    }


    /**
     * 
     * @param root 
     */
    protected KDOMElementLocator(final ELElement root) {
        super(root);
    }


    @Override
    protected void print(final ELElement root, final Document document,
                         final Map<String, String> namespaceMap) {

        if (root == null) {
            throw new NullPointerException("null root");
        }

        if (document == null) {
            throw new NullPointerException("null document");
        }

        if (namespaceMap == null) {
            throw new NullPointerException("null namespaces");
        }

        print(root, namespaceMap, document);
    }


    /**
     * 
     * @param _element
     * @param namesapces
     * @param parent
     */
    private void print(final ELElement _element,
                       final Map<String, String> namesapces,
                       final Node parent) {

        if (_element == null) {
            throw new NullPointerException("null _element");
        }

        if (namesapces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        final Element child = parent.createElement(
            _element.namespaceURI, _element.localName);
        parent.addChild(Node.ELEMENT, child);

        for (ELAttribute _attribute : _element.attributes.values()) {
            child.setAttribute(_attribute.namespaceURI, _attribute.localName,
                               _attribute.value);
        }

        if (_element.text != null) {
            child.addChild(Node.TEXT, _element.text);
        } else {
            for (ELElement grandchild : _element.elements) {
                print(grandchild, namesapces, child);
            }
        }
    }
}

