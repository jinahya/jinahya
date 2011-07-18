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


package jinahya.xml.el;


import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DOMElementLocator extends ElementLocator<Document> {


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

        final Element element = document.getDocumentElement();
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

        return new DOMElementLocator(parse(element));
    }


    /**
     * Parses given DOM Element to a ELElement.
     *
     * @param element element
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

        final ELElement _element = new ELElement(namespaceURI, localName);

        final NamedNodeMap attributes = element.getAttributes();
        final int attributeLength = attributes.getLength();
        for (int i = 0; i < attributeLength; i++) {
            final Attr attribute = (Attr) attributes.item(i);
            String attributeNamespaceURI = attribute.getNamespaceURI();
            if (ELNode.XMLNS_ATTRIBUTE_NS_URI.equals(
                attributeNamespaceURI)) {
                continue;
            }
            if (attributeNamespaceURI == null) {
                attributeNamespaceURI = ELNode.NULL_NS_URI;
            }
            final String attributeLocalName = attribute.getLocalName();
            final String attributeValue = attribute.getNodeValue();
            _element.attributes.put(
                ELNode.express(attributeNamespaceURI, attributeLocalName),
                new ELAttribute(attributeNamespaceURI, attributeLocalName,
                                attributeValue));
        }

        String text = null;

        final NodeList childNodes = element.getChildNodes();
        final int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            final Node childNode = childNodes.item(i);
            switch (childNode.getNodeType()) {
                case Node.CDATA_SECTION_NODE:
                case Node.TEXT_NODE:
                    if (text == null) {
                        text = childNode.getNodeValue();
                    }
                    break;
                case Node.ELEMENT_NODE:
                    _element.elements.add(parse((Element) childNode));
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
    protected DOMElementLocator(final ELElement root) {
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

        print(root, document, namespaces, document);
    }


    /**
     * 
     * @param _element
     * @param document
     * @param namesapces
     * @param parent
     */
    private void print(final ELElement _element, final Document document,
                       final Map<String, String> namesapces,
                       final Node parent) {

        if (_element == null) {
            throw new NullPointerException("null _element");
        }

        if (document == null) {
            throw new NullPointerException("null document");
        }

        if (namesapces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (parent == null) {
            throw new NullPointerException("null parent");
        }

        final Element element = document.createElementNS(
            _element.namespaceURI, getQualifiedName(_element, namesapces));
        parent.appendChild(element);

        for (ELAttribute _attribute : _element.attributes.values()) {
            element.setAttributeNS(_attribute.namespaceURI,
                                   getQualifiedName(_attribute, namesapces),
                                   _attribute.value);
        }

        if (_element.text != null) {
            element.appendChild(document.createTextNode(_element.text));
        } else {
            for (ELElement child : _element.elements) {
                print(child, document, namesapces, element);
            }
        }
    }
}

