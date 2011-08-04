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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
    static String getQualifiedName(final ELNode node,
                                   final Map<String, String> namespaces) {

        if (node == null) {
            throw new NullPointerException("null node");
        }

        if (namespaces == null) {
            throw new NullPointerException("null namespaceMap");
        }

        return getQualifiedName(node.namespaceURI, node.localName, namespaces);
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
                                   final Map<String, String> namespaces) {

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

        final String namespacePrefix = namespaces.get(namespaceURI);
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
    static Map<String, String> getNamespaces(final ELElement element) {

        if (element == null) {
            throw new NullPointerException("null element");
        }

        final Map<String, String> namespaces = new TreeMap<String, String>();


        for (String namespaceURI : element.getNamespaceURIs()) {
            namespaces.put(namespaceURI, null);
        }

        int index = 0;
        for (Entry<String, String> entry : namespaces.entrySet()) {
            entry.setValue("ns" + index);
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
     * Returns the number of child elements which each has given
     * <code>localName</code> in no namespace.
     *
     * @param localName local name
     * @return number of children
     */
    public final int count(final String localName) {

        if (localName == null) {
            throw new NullPointerException("null localName");
        }

        if (localName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty localName");
        }

        return count(ELNode.NULL_NS_URI, localName);
    }


    /**
     * Returns the number of child elements which each has given
     * <code>localName</code> in specified <code>namespaceURI</code>. En empty
     * string("") must be used for no(null) namespace.
     *
     * @param namespaceURI The namespace URI.
     * @param localName The local name.
     * @return child count
     * @see #getCount(String) 
     */
    public final int count(final String namespaceURI, final String localName) {

        int count = 0;

        for (ELElement child : current().elements) {
            if (!child.localName.equals(localName)) {
                continue;
            }
            if (!child.namespaceURI.equals(namespaceURI)) {
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
    public final ElementLocator root() {

        while (path.size() > 1) {
            path.remove(path.size() - 1);
        }

        return this;
    }


    /**
     * Locates to the parent element. An <code>IllegalStateException</code> will
     * be thrown if there is no parent to locate (on the root).
     * 
     * @return self
     */
    public final ElementLocator parent() {

        if (path.size() == 1) {
            throw new IllegalStateException("no parent to locate");
        }

        path.remove(path.size() - 1);

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
     * @see #child(java.lang.String, java.lang.String, int)
     */
    public final ElementLocator child(final String localName,
                                      final int index) {

        return child(ELNode.NULL_NS_URI, localName, index);
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
     * @see #child(String, int) 
     */
    public final ElementLocator child(final String namespaceURI,
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
        for (ELElement element : current().elements) {
            if (!element.localName.equals(localName)) {
                continue;
            }
            if (!element.namespaceURI.equals(namespaceURI)) {
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
    public final ElementLocator child(final String localName) {

        return child(ELNode.NULL_NS_URI, localName);
    }


    /**
     * Adds a child element and locate it. The new child element will be named
     * as <code>localName</code> in given <code>namespaceURI</code>.
     *
     * @param namespaceURI child element's namespace URI
     * @param localName child element's local name
     * @return self
     */
    public final ElementLocator child(final String namespaceURI,
                                      final String localName) {

        final ELElement child = new ELElement(namespaceURI, localName);

        current().elements.add(child);

        path.add(child);

        return this;
    }


    /**
     * Returns text value of currently located element.
     *
     * @return text value; may be null
     */
    public final String text() {
        return current().text;
    }


    /**
     * Sets the text value of currently located element.
     * 
     * @param text new text value
     * @return self
     */
    public final ElementLocator text(final String text) {

        current().text = text;

        return this;
    }


    /**
     * Finds an attribute and returns its value.
     *
     * @param namespaceURI attribute's namespace URI
     * @param localName attribute's local name
     * @return attribute's value or null if not found
     */
    public final String attribute(final String namespaceURI,
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

        final ELAttribute attribute = current().attributes.get(
            ELNode.jamesClark(namespaceURI, localName));

        if (attribute == null) {
            return null;
        }

        return attribute.value;
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
    public final ElementLocator attribute(final String namespaceURI,
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
            current().attributes.remove(expressed);
            return this;
        }

        final ELAttribute attribute =
            new ELAttribute(namespaceURI, localName, value);

        current().attributes.put(expressed, attribute);

        return this;
    }


    /**
     * 
     * @return 
     */
    final Map<String, String> getNamespaces() {

        final Map<String, String> namespaces = new TreeMap<String, String>();

        final ELElement root = path.get(0);

        for (String namespaceURI : root.getNamespaceURIs()) {
            namespaces.put(namespaceURI, null);
        }

        int index = 0;
        for (Entry<String, String> entry : namespaces.entrySet()) {
            entry.setValue("ns" + index);
            index++;
        }

        namespaces.put(ELNode.XML_NS_URI, ELNode.XML_NS_PREFIX);

        return namespaces;
    }


    /**
     * Removes currently located element and locate parent. An
     * <code>IllegalStateException</code> will be thrown if this locate is
     * currently locating the root element.
     *
     * @return self
     */
    public final ElementLocator remove() {

        if (path.size() == 1) {
            throw new IllegalStateException("can't remove the root element");
        }

        final ELElement element = current();

        parent().current().elements.remove(element);

        return this;
    }


    /**
     * Returns currently located element.
     *
     * @return current element
     */
    final ELElement current() {
        return path.get(path.size() - 1);
    }


    /**
     * Returns the root element.
     *
     * @return the root element
     */
    public final ELElement getRootElement() {
        return path.get(0);
    }


    /**
     * Returns JSON representation of the contents.
     *
     * @return a JSON string.
     */
    public final String toJSON() {
        return path.get(0).toJSON();
    }


    /** element path. */
    private final List<ELElement> path = new ArrayList<ELElement>();
}

