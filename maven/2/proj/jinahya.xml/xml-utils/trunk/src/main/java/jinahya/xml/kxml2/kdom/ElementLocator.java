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

package jinahya.xml.kxml2.kdom;


import java.util.ArrayList;
import java.util.List;

import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ElementLocator {


    /*
    public static ElementLocator newInstance(final String rootElementNamespace,
                                             final String rootElementName) {

        final Document document = new Document();
        document.addChild(Node.ELEMENT, document.createElement(
            rootElementNamespace, rootElementName));
        return new ElementLocator(document);
    }
     */


    /*
     *
     * @param document
    public ElementLocator(final Document document) {
        this(document.getRootElement());
    }
     */


    /**
     *
     * @param element
     */
    public ElementLocator(final Element element) {
        super();

        this.element = element;
    }


    /**
     *
     * @param name
     * @return
     */
    public boolean hasChild(final String name) {
        return hasChild(element.getNamespace(), name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public boolean hasChild(final String namespace, final String name) {
        return findChild(namespace, name) != null;
    }


    /**
     *
     * @param name
     * @return
     */
    public ElementLocator locateChild(final String name) {
        return locateChild(element.getNamespace(), name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public ElementLocator locateChild(final String namespace,
                                      final String name) {

        Element child = findChild(namespace, name);
        if (child == null) {
            child = element.createElement(namespace, name);
            element.addChild(Node.ELEMENT, child);
        }
        element = child;
        return this;
    }


    public ElementLocator locateParent() {
        Node parent = element.getParent();
        if (parent != null && (parent instanceof Element)) {
            element = (Element) parent;
        }
        return this;
    }


    /**
     *
     * @param name
     * @return
     */
    public Element findChild(final String name) {
        return findChild(element.getNamespace(), name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public Element findChild(final String namespace, final String name) {
        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT != element.getType(i)) {
                continue;
            }
            final Element child  = element.getElement(i);
            if (namespace.equals(child.getNamespace())
                && name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public List<Element> getChildren(final String namespace,
                                     final String name) {

        final List<Element> children = new ArrayList<Element>();
        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT != element.getType(i)) {
                continue;
            }
            final Element child  = element.getElement(i);
            if (namespace.equals(child.getNamespace())
                && name.equals(child.getName())) {
                children.add(child);
            }
        }
        return children;
    }


    /**
     * Find and return the root document instance.
     *
     * @return the document instance of null
     */
    public Document getDocument() {
        Node root = element.getRoot();
        if (root instanceof Document) {
            return (Document) root;
        }

        /*
        Node parent = element.getParent();
        while (parent != null) {
            if (parent instanceof Element) {
                parent = ((Element) parent).getParent();
                continue;
            }
            if (parent instanceof Document) {
                return (Document) parent;
            }
        }
         */

        return null;
    }


    /**
     *
     * @return
     */
    public Element getElement() {
        return element;
    }


    public String getAttributeValue(final String namespace, final String name) {
        return element.getAttributeValue(namespace, name);
    }


    public void setAttribute(final String namespace, final String name,
                             final String value) {

        element.setAttribute(namespace, name, value);
    }


    public String getText() {
        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (element.isText(i)) {
                return element.getText(i);
            }
        }
        return null;
    }


    public void setText(final String text) {
        element.addChild(Node.TEXT, text);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public ElementLocator fork(final String namespace, final String name) {
        final Element child = this.findChild(namespace, name);
        if (child == null) {
            return null;
        }
        return new ElementLocator(child);
    }


    private Element element;
}
