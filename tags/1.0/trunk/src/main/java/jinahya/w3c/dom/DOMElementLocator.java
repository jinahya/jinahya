/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.w3c.dom;


import java.util.LinkedList;
import javax.xml.XMLConstants;

import jinahya.xml.ElementLocator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DOMElementLocator implements ElementLocator<Element> {


    /**
     * Creates a new instance.
     *
     * @param root root element
     */
    public DOMElementLocator(final Element root) {
        super();

        if (root == null) {
            throw new IllegalArgumentException("null root");
        }

        elements = new LinkedList<Element>();
        elements.add(root);

        for (Node parent = null; (parent = root.getParentNode()) != null;) {
            if (Node.DOCUMENT_NODE == parent.getNodeType()) {
                document = (Document) parent;
                break;
            }
        }

        if (document == null) {
            throw new IllegalArgumentException(
                "can't locate document from the root");
        }
    }


    @Override
    public DOMElementLocator root() {
        while (elements.size() > 1) {
            elements.removeLast();
        }
        return this;
    }


    @Override
    public DOMElementLocator parent() {

        if (elements.size() == 1) {
            throw new IllegalStateException("on root");
        }

        elements.removeLast();

        return this;
    }


    @Override
    public int count(final String name) {
        return countNS(XMLConstants.NULL_NS_URI, name);
    }


    @Override
    public int countNS(final String namespace, final String name) {
        return current().getElementsByTagNameNS(namespace, name).getLength();
    }


    @Override
    public DOMElementLocator child(final String name, final int index) {
        return childNS(XMLConstants.NULL_NS_URI, name, index);
    }


    @Override
    public DOMElementLocator childNS(final String namespace, final String name,
                                     final int index) {

        final NodeList children =
            current().getElementsByTagNameNS(namespace, name);
        Node child = children.item(index);
        if (child == null) {
            throw new IndexOutOfBoundsException("no child at " + index);
        }
        elements.add((Element) child);
        return this;
    }


    @Override
    public DOMElementLocator child(final String name) {
        return childNS(XMLConstants.NULL_NS_URI, name);
    }


    @Override
    public DOMElementLocator childNS(final String namespace,
                                     final String name) {


        final Element child = document.createElementNS(namespace, name);
        elements.add((Element) current().appendChild(child));

        return this;
    }


    @Override
    public String attribute(final String name) {
        return attributeNS(XMLConstants.NULL_NS_URI, name);
    }


    @Override
    public String attributeNS(final String namespace, final String name) {
        return current().getAttributeNS(namespace, name);
    }


    @Override
    public DOMElementLocator attribute(final String name, final String value) {
        return attributeNS(XMLConstants.NULL_NS_URI, name, value);
    }


    @Override
    public DOMElementLocator attributeNS(final String namespace,
                                         final String name,
                                         final String value) {

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        current().setAttributeNS(namespace, name, value);

        return this;
    }


    @Override
    public String text() {
        return text(false);
    }


    @Override
    public String text(final boolean parent) {
        final String text = current().getTextContent();
        if (parent) {
            parent();
        }
        return text;
    }


    @Override
    public DOMElementLocator text(final String value) {

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        return text(value, false);
    }


    @Override
    public DOMElementLocator text(final String value, final boolean parent) {

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        final Text text = document.createTextNode(value);
        current().appendChild(text);

        if (parent) {
            return parent();
        }

        return this;
    }


    @Override
    public String namespace() {
        return current().getNamespaceURI();
    }


    @Override
    public String name() {
        return current().getNodeName();
    }


    @Override
    public Element current() {
        return elements.getLast();
    }


    private final LinkedList<Element> elements;
    private Document document = null;
}
