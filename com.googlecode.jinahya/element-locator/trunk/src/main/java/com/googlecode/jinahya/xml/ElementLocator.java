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


package com.googlecode.jinahya.xml;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 * Abstract element locator.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class ElementLocator {


    /**
     * Returns qualified name.
     *
     * @param node node
     * @param namespaces namespace map
     * @return qualified name
     */
    static String getQualifiedName(final ELNode node, final Map namespaces) {

        if (node == null) {
            throw new NullPointerException("null node");
        }

        if (namespaces == null) {
            throw new NullPointerException("null namespaceMap");
        }

        return getQualifiedName(node.getNamespaceURI(), node.getLocalName(),
                                namespaces);
    }


    /**
     * 
     * @param namespaceURI
     * @param localName
     * @param namespaces
     * @return 
     */
    static String getQualifiedName(final String namespaceURI,
                                   final String localName,
                                   final Map namespaces) {

        if (namespaceURI == null) {
            throw new NullPointerException("null namespaceURI");
        }

        if (localName == null) {
            throw new NullPointerException("null localName");
        }

        if (localName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty localName");
        }

        if (namespaces == null) {
            throw new NullPointerException("null namespaces");
        }

        if (ELNode.NULL_NS_URI.equals(namespaceURI)) {
            return localName;
        }

        final String namespacePrefix = (String) namespaces.get(namespaceURI);
        if (namespacePrefix == null) {
            throw new RuntimeException(
                "no namespace prefix for " + namespaceURI);
        }

        return namespacePrefix + ":" + localName;
    }


    /**
     * 
     * @param element
     * @return 
     */
    static Map getNamespaces(final ELElement element) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        final Map namespaces = new TreeMap();

        final Set namespaceURIs = element.getNamespaceURIs();
        for (Iterator i = namespaceURIs.iterator(); i.hasNext();) {
            namespaces.put(i.next(), null);
        }

        int index = 0;
        for (Iterator i = namespaceURIs.iterator(); i.hasNext();) {
            namespaces.put(i.next(), "ns" + index);
            index++;
        }

        namespaces.put(ELNode.XML_NS_URI, ELNode.XML_NS_PREFIX);

        return namespaces;
    }


    /**
     * Creates a new instance.
     *
     * @param root root element
     */
    public ElementLocator(final ELElement root) {
        super();

        if (root == null) {
            throw new NullPointerException("null root");
        }

        path.add(root);
    }


    /**
     * Returns currently located element's namespace URI.
     *
     * @return current element's namespace URI
     */
    public final String getNamespaceURI() {

        return getCurrent().getNamespaceURI();
    }


    /**
     * Returns currently located element's local name.
     *
     * @return current element's local name.
     */
    public final String getLocalName() {

        return getCurrent().getLocalName();
    }


    /**
     * Returns the number of child elements which each has given
     * <code>localName</code> in no namespace.
     *
     * @param localName local name
     * @return number of children
     * @see #getChildCount(java.lang.String, java.lang.String) 
     */
    public final int getChildCount(final String localName) {

        if (localName == null) {
            throw new NullPointerException("null localName");
        }

        if (localName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty localName");
        }

        return getChildCount(ELNode.NULL_NS_URI, localName);
    }


    /**
     * Returns the number of child elements which each has given
     * <code>localName</code> in specified <code>namespaceURI</code>. En empty
     * string("") must be used for no(null) namespace.
     *
     * @param namespaceURI The namespace URI.
     * @param localName The local name.
     * @return child count
     * @see #getChildCount(String) 
     */
    public final int getChildCount(final String namespaceURI,
                                   final String localName) {

        int count = 0;

        for (Iterator i = getCurrent().getElements().iterator(); i.hasNext();) {
            final ELElement child = (ELElement) i.next();
            if (!child.getLocalName().equals(localName)) {
                continue;
            }
            if (!child.getNamespaceURI().equals(namespaceURI)) {
                continue;
            }
            count++;
        }

        return count;
    }


    /**
     * Locate to the root element. Nothing happens if this locator is already
     * locating the root element.
     *
     * @return self
     */
    public final ElementLocator locateRoot() {

        while (path.size() > 1) {
            path.remove(path.size() - 1);
        }

        return this;
    }


    /**
     * Locates to the parent element.
     * 
     * @return self
     */
    public final ElementLocator locateParent() {

        if (path.size() > 1) {
            path.remove(path.size() - 1);
        }

        return this;
    }


    /**
     * Locates child element which has given <code>localName</code> in no
     * namespace at <code>index</code>. An
     * <code>IndexOutOfBoundsException</code> will be thrown if there is no
     * child at specified <code>index</code>.
     *
     * @param localName element's local name
     * @param index child element index
     * @return self
     * @see #locateChild(java.lang.String, java.lang.String, int)
     */
    public final ElementLocator locateChild(final String localName,
                                            final int index) {

        return locateChild(ELNode.NULL_NS_URI, localName, index);
    }


    /**
     * Locate a child element which has given <code>localName</code> in given
     * <code>namespaceURI</code> at <code>index</code>. An
     * <code>IndexOutOfBoundsException</code> will be thrown if there is no
     * child at specified <code>index</code>.
     *
     * @param namespaceURI element's namespace URI
     * @param localName element's local name
     * @param index target index to locate
     * @return self
     * @see #locateChild(String, int) 
     */
    public final ElementLocator locateChild(final String namespaceURI,
                                            final String localName,
                                            final int index) {

        if (namespaceURI == null) {
            throw new NullPointerException("null namespaceURI");
        }

        if (localName == null) {
            throw new NullPointerException("null localName");
        }

        if (localName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty localName");
        }

        if (index < 0) {
            throw new IllegalArgumentException("negative index: " + index);
        }

        int count = 0;
        for (Iterator i = getCurrent().getElements().iterator(); i.hasNext();) {
            final ELElement element = (ELElement) i.next();
            if (!element.getLocalName().equals(localName)) {
                continue;
            }
            if (!element.getNamespaceURI().equals(namespaceURI)) {
                continue;
            }
            if (count == index) {
                path.add(element);
                return this;
            }
            count++;
        }

        throw new IndexOutOfBoundsException("no child at " + index);
    }


    /**
     * Adds a child element and locate it. The new child element will be named
     * as <code>localName</code> in no namespace.
     *
     * @param localName new child element's local name.
     * @return self
     */
    public final ElementLocator addChild(final String localName) {

        return addChild(ELNode.NULL_NS_URI, localName);
    }


    /**
     * Adds a child element and locate it. The new child element will be named
     * as <code>localName</code> in given <code>namespaceURI</code>.
     *
     * @param namespaceURI child element's namespace URI
     * @param localName child element's local name
     * @return self
     */
    public final ElementLocator addChild(final String namespaceURI,
                                         final String localName) {

        final ELElement child = new ELElement(namespaceURI, localName);

        getCurrent().getElements().add(child);

        path.add(child);

        return this;
    }


    /**
     * Returns text value of currently located element.
     *
     * @return text value; may be null
     */
    public final String getText() {
        return getCurrent().getText();
    }


    /**
     * Sets the text value of currently located element.
     * 
     * @param text new text value
     * @return self
     */
    public final ElementLocator setText(final String text) {

        getCurrent().setText(text);

        return this;
    }


    public final String getAttribute(final String localName) {

        if (localName == null) {
            throw new NullPointerException("null localName");
        }

        if (localName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty localName");
        }

        return getAttribute(ELNode.NULL_NS_URI, localName);
    }


    /**
     * Finds an attribute and returns its value.
     *
     * @param namespaceURI attribute's namespace URI
     * @param localName attribute's local name
     * @return attribute's value or null if not found
     */
    public final String getAttribute(final String namespaceURI,
                                     final String localName) {

        if (namespaceURI == null) {
            throw new NullPointerException("null namespaceURI");
        }

        if (localName == null) {
            throw new NullPointerException("null localName");
        }

        if (localName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty localName");
        }

        final ELAttribute attribute =
            (ELAttribute) getCurrent().getAttributes().get(
            ELNode.jamesClark(namespaceURI, localName));

        if (attribute == null) {
            return null;
        }

        return attribute.getValue();
    }


    public final ElementLocator setAttribute(final String localName,
                                             final String value) {

        if (localName == null) {
            throw new NullPointerException("null localName");
        }

        if (localName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty localName");
        }

        return setAttribute(ELNode.NULL_NS_URI, localName, value);
    }


    /**
     * Sets or removes attribute's value. If <code>value</code> is null mapped
     * attribute will be removed.
     *
     * @param namespaceURI attribute's namespace URI
     * @param localName attribute's local name
     * @param value attribute's value; null for removing attribute
     * @return self
     */
    public final ElementLocator setAttribute(final String namespaceURI,
                                             final String localName,
                                             final String value) {

        if (namespaceURI == null) {
            throw new NullPointerException("null namespaceURI");
        }

        if (localName == null) {
            throw new NullPointerException("null localName");
        }

        if (localName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty localName");
        }

        final String expressed = ELNode.jamesClark(namespaceURI, localName);

        if (value == null) {
            getCurrent().getAttributes().remove(expressed);
            return this;
        }

        final ELAttribute attribute =
            new ELAttribute(namespaceURI, localName, value);

        getCurrent().getAttributes().put(expressed, attribute);

        return this;
    }


    /**
     * Removes currently located element and locate parent. An
     * <code>IllegalStateException</code> will be thrown if this locate is
     * currently locating the root element.
     *
     * @return self
     */
    public final ElementLocator removeCurrent() {

        if (path.size() == 1) {
            throw new IllegalStateException("can't remove the root element");
        }

        final ELElement element = getCurrent();

        locateParent().getCurrent().getElements().remove(element);

        return this;
    }


    /**
     * Returns currently located element.
     *
     * @return current element
     */
    final ELElement getCurrent() {
        return (ELElement) path.get(path.size() - 1);
    }


    /**
     * Returns the root element.
     *
     * @return the root element
     */
    final ELElement getRoot() {
        return (ELElement) path.get(0);
    }


    /** element path. */
    private final List path = new ArrayList();
}

