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
 * @param <D> document type parameter
 */
public abstract class ElementLocator<D> {


    /**
     * Returns qualified name.
     *
     * @param node node
     * @param namespaces name space map
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
     * Creates a new instance.
     *
     * @param root root element
     */
    ElementLocator(final ELElement root) {
        super();

        if (root == null) {
            throw new NullPointerException("null root");
        }

        path.add(root);
    }


    /**
     * Returns the number of child elements which each has given
     * <code>localName</code> in no name space.
     *
     * @param localName local name
     * @return number of children
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
     * string("") must be used for no(null) name space.
     *
     * @param namespaceURI The name space URI.
     * @param localName The local name.
     * @return child count
     * @see #getCount(String) 
     */
    public final int getChildCount(final String namespaceURI,
                                   final String localName) {

        int count = 0;

        for (ELElement child : getCurrent().elements) {
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
     * Locate the root element.
     *
     * @return self
     * @deprecated Use {@link #root()}
     */
    public final ElementLocator<D> locateRoot() {

        return root();
    }


    /**
     * Locate to the root element. Nothing happens if this locator is already
     * locating the root element.
     *
     * @return self
     */
    public final ElementLocator<D> root() {

        while (path.size() > 1) {
            path.remove(path.size() - 1);
        }

        return this;
    }


    /**
     * Locate to the parent of the current element. An IllegalStateException
     * will be thrown if this locator is already on the root.
     *
     * @return self
     * @deprecated Use {@link #parent() }
     */
    public final ElementLocator<D> locateParent() {

        if (path.size() == 1) {
            throw new IllegalStateException("no parent to locate");
        }

        path.remove(path.size() - 1);

        return this;
    }


    /**
     * Locates to the parent element. An <code>IllegalStateException</code> will
     * be thrown if there is no parent to locate (on the root).
     * 
     * @return self
     */
    public final ElementLocator<D> parent() {

        if (path.size() == 1) {
            throw new IllegalStateException("no parent to locate");
        }

        path.remove(path.size() - 1);

        return this;
    }


    /**
     * Locates child element which has given <code>localName</code> with no
     * name space at <code>index</code>.
     *
     * @param localName local name
     * @param index index
     * @return self
     * @deprecated Use {@link #child(java.lang.String, int) }
     */
    public final ElementLocator<D> locateChild(final String localName,
                                               final int index) {

        return locateChild(ELNode.NULL_NS_URI, localName, index);
    }


    /**
     * Locate a child with <code>localName</code> at <code>index</code> in
     * <code>namespaceURI</code>.
     *
     * @param namespaceURI element's name space URI
     * @param localName element's local name
     * @param index target index to locate
     * @return self
     * @see #locateChild(String, int) 
     * @deprecated Use {@link #child(java.lang.String, java.lang.String, int) }
     */
    public final ElementLocator<D> locateChild(final String namespaceURI,
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
        for (ELElement element : getCurrent().elements) {
            if (!element.localName.equals(localName)) {
                continue;
            }
            if (!element.namespaceURI.equals(namespaceURI)) {
                continue;
            }
            if (count++ == index) {
                path.add(element);
                return this;
            }
        }

        throw new IndexOutOfBoundsException("no child at " + index);
    }


    /**
     * Locates child element which has given <code>localName</code> in no
     * name space at <code>index</code>. An
     * <code>IndexOutOfBoundsException</code> will be thrown if there is no
     * child at specified <code>index</code>.
     *
     * @param localName element's local name
     * @param index child element index
     * @return self
     * @see #child(java.lang.String, java.lang.String, int)
     */
    public final ElementLocator<D> child(final String localName,
                                         final int index) {

        return child(ELNode.NULL_NS_URI, localName, index);
    }


    /**
     * Locate a child element which has given <code>localName</code> in given
     * <code>namespaceURI</code> at <code>index</code>. An
     * <code>IndexOutOfBoundsException</code> will be thrown if there is no
     * child at specified <code>index</code>.
     *
     * @param namespaceURI element's name space URI
     * @param localName element's local name
     * @param index target index to locate
     * @return self
     * @see #child(String, int) 
     */
    public final ElementLocator<D> child(final String namespaceURI,
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
        for (ELElement element : getCurrent().elements) {
            if (!element.localName.equals(localName)) {
                continue;
            }
            if (!element.namespaceURI.equals(namespaceURI)) {
                continue;
            }
            if (count++ == index) {
                path.add(element);
                return this;
            }
        }

        throw new IndexOutOfBoundsException("no child at " + index);
    }


    /**
     * Adds a child element whose name is <code>localName</code> with
     * no name space and locate it.
     *
     * @param localName local name
     * @return self
     * @deprecated Use {@link #child(java.lang.String) }
     */
    public final ElementLocator<D> addChild(final String localName) {

        return addChild(ELNode.NULL_NS_URI, localName);
    }


    /**
     * Adds a child element whose name is <code>localName</code> with given
     * <code>namespaceURI</code> and locate it.
     *
     * @param namespaceURI
     * @param localName
     * @return 
     * @deprecated Use {@link #child(java.lang.String, java.lang.String) }
     */
    public final ElementLocator<D> addChild(final String namespaceURI,
                                            final String localName) {

        final ELElement child = new ELElement(namespaceURI, localName);

        getCurrent().elements.add(child);

        path.add(child);

        return this;
    }


    /**
     * Adds a child element and locate it. The new child element will be named
     * as <code>localName</code> in no name space.
     *
     * @param localName new child element's local name.
     * @return self
     */
    public final ElementLocator<D> child(final String localName) {

        return child(ELNode.NULL_NS_URI, localName);
    }


    /**
     * Adds a child element and locate it. The new child element will be named
     * as <code>localName</code> in given <code>namespaceURI</code>.
     *
     * @param namespaceURI child element's name space URI
     * @param localName child element's local name
     * @return self
     */
    public final ElementLocator<D> child(final String namespaceURI,
                                         final String localName) {

        final ELElement child = new ELElement(namespaceURI, localName);

        getCurrent().elements.add(child);

        path.add(child);

        return this;
    }


    /**
     * Returns text value of currently located element.
     *
     * @return text value; may be null
     * @deprecated Use {@link #text() }
     */
    public final String getText() {
        return getCurrent().text;
    }


    /**
     * Returns text value of currently located element.
     *
     * @return text value; may be null
     */
    public final String text() {
        return getCurrent().text;
    }


    /**
     * Sets the text value.
     *
     * @param text text value
     * @return self
     * @deprecated Use {@link #text(java.lang.String) }
     */
    public final ElementLocator<D> setText(final String text) {

        getCurrent().text = text;

        return this;
    }


    /**
     * Sets the text value of currently located element.
     * 
     * @param text new text value
     * @return self
     */
    public final ElementLocator<D> text(final String text) {

        getCurrent().text = text;

        return this;
    }


    /**
     * Returns attribute value.
     *
     * @param localName attribute's local name
     * @return attribute's value
     * @deprecated Use {@link #attribute(java.lang.String, java.lang.String) }
     */
    public final String getAttribute(final String localName) {
        return getAttribute(ELNode.NULL_NS_URI, localName);
    }


    /**
     * Returns the value of attribute which has <code>name</code> in
     * <code>space</code>.
     *
     * @param namespaceURI attribute's name space URI
     * @param localName attribute's local name
     * @return attribute's value
     * @deprecated Use {@link #attribute(java.lang.String, java.lang.String) }
     */
    public final String getAttribute(final String namespaceURI,
                                     final String localName) {

        final ELAttribute attribute = getCurrent().attributes.get(
            ELNode.express(namespaceURI, localName));

        if (attribute == null) {
            return null;
        }

        return attribute.value;
    }


    /**
     * Finds an attribute and returns its value.
     *
     * @param namespaceURI attribute's name space URI
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

        final ELAttribute attribute = getCurrent().attributes.get(
            ELNode.express(namespaceURI, localName));

        if (attribute == null) {
            return null;
        }

        return attribute.value;
    }


    /**
     * Sets attribute value.
     *
     * @param localName local name
     * @param value attribute value
     * @return self
     * @deprecated Use {@link #attribute(String, String, String) }
     */
    public final ElementLocator<D> setAttribute(final String localName,
                                                final String value) {

        return setAttribute(ELNode.NULL_NS_URI, localName, value);
    }


    /**
     * Sets the value of attribute which has <code>localName</code> in
     * <code>namespaceURI</code>.
     * 
     * @param namespaceURI attribute's name space URI
     * @param localName attribute's local name
     * @param value attribute's value
     * @return self
     * @deprecated Use {@link #attribute(String, String, String) }
     */
    public final ElementLocator<D> setAttribute(final String namespaceURI,
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

        if (value == null) {
            throw new NullPointerException("null value");
        }

        final ELAttribute attribute =
            new ELAttribute(namespaceURI, localName, value);

        getCurrent().attributes.put(ELNode.express(attribute), attribute);

        return this;
    }


    /**
     * Sets or removes attribute's value. If <code>value</code> is null mapped
     * attribute will be removed.
     *
     * @param namespaceURI attribute's name space URI
     * @param localName attribute's local name
     * @param value attribute's value; null for removing attribute
     * @return self
     */
    public final ElementLocator<D> attribute(final String namespaceURI,
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

        final String expressed = ELNode.express(namespaceURI, localName);

        if (value == null) {
            getCurrent().attributes.remove(expressed);
            return this;
        }

        final ELAttribute attribute =
            new ELAttribute(namespaceURI, localName, value);

        getCurrent().attributes.put(expressed, attribute);

        return this;
    }


    /**
     * Removes attribute whose name is <code>localName</code> with no
     * namespaceURI.
     *
     * @param localName attribute's local name
     * @return self.
     * @deprecated Use {@link #attribute(String, String, String) } with
     *             <code>null value</code>.
     */
    public final ElementLocator<D> removeAttribute(final String localName) {

        return removeAttribute(ELNode.NULL_NS_URI, localName);
    }


    /**
     * Sets the value of attribute which has <code>localName</code> in
     * <code>namespaceURI</code>.
     * 
     * @param namespaceURI attribute's name space URI
     * @param localName attribute's local name
     * @return self
     * @deprecated Use {@link #attribute(String, String, String) } with
     *             <code>null value</code>.
     */
    public final ElementLocator<D> removeAttribute(final String namespaceURI,
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

        getCurrent().attributes.remove(ELNode.express(namespaceURI, localName));

        return this;
    }


    /**
     * Prints contents to given <code>document</code>.
     *
     * @param document an empty document
     * @return given <code>document</code>
     */
    public abstract D toDocument(final D document);


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
            entry.setValue("ns" + index++);
        }

        namespaces.put(ELNode.XML_NS_URI, ELNode.XML_NS_PREFIX);

        return namespaces;
    }


    /**
     * Removes current element and locate parent. An
     * <code>IllegalStateException</code> will be thrown if this element locator
     * is already locating the root.
     *
     * @return self
     */
    public final ElementLocator<D> removeCurrent() {

        if (path.size() == 1) {
            throw new IllegalStateException("can't remove the root element");
        }

        final ELElement element = getCurrent();

        locateParent().getCurrent().elements.remove(element);

        return this;
    }


    /**
     * Removes currently located element and locate parent. An
     * <code>IllegalStateException</code> will be thrown if this locate is
     * currently locating the root element.
     *
     * @return self
     */
    public final ElementLocator<D> remove() {

        if (path.size() == 1) {
            throw new IllegalStateException("can't remove the root element");
        }

        final ELElement element = getCurrent();

        parent().getCurrent().elements.remove(element);

        return this;
    }


    /**
     * Returns currently located element.
     *
     * @return current element
     */
    final ELElement getCurrent() {
        return path.get(path.size() - 1);
    }


    /**
     * Returns the root element.
     *
     * @return the root element
     */
    final ELElement getRoot() {
        return path.get(0);
    }


    /*
     * Sets the root element.
     *
     * @param root root element
    final void setRoot(final ELElement root) {

        if (root == null) {
            throw new NullPointerException("null root");
        }

        path.clear();
        path.add(root);
    }
     */


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

