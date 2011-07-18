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


package jinahya.xml.el;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <D> 
 */
public abstract class ElementLocator<D> {


    /**
     * 
     * @param node
     * @param namespaces
     * @return 
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
            throw new NullPointerException("null namespaceMap");
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
    protected ElementLocator(final ELElement root) {
        super();

        if (root == null) {
            throw new NullPointerException("null root");
        }

        path.add(root);
    }


    /**
     * 
     * @param localName
     * @return 
     */
    public final int getChildCount(final String localName) {

        return getChildCount(ELNode.NULL_NS_URI, localName);
    }


    /**
     * Returns the number of child elements of currently located element with
     * the specified local name and name space URI.
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
     * Locate the root.
     *
     * @return self
     */
    public final ElementLocator<D> locateRoot() {

        while (path.size() > 1) {
            path.remove(path.size() - 1);
        }

        return this;
    }


    /**
     * Locate to the parent of the current element.
     *
     * @return self
     */
    public final ElementLocator<D> locateParent() {

        if (path.size() == 1) {
            throw new IllegalStateException("no parent to locate");
        }

        path.remove(path.size() - 1);

        return this;
    }


    /**
     * 
     * @param localName
     * @param index
     * @return 
     */
    public final ElementLocator<D> locateChild(final String localName,
                                               final int index) {

        return locateChild(ELNode.NULL_NS_URI, localName, index);
    }


    /**
     * Locate a child with <code>name</code> at <code>index</code> in
     * <code>space</code>.
     *
     * @param namespaceURI element's name space URI
     * @param localName element's local name
     * @param index target index to locate
     * @return self
     * @see #locateChild(String, int) 
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
            if (count == index) {
                path.add(element);
                return this;
            }
            count++;
        }

        throw new IndexOutOfBoundsException("no child at " + index);
    }


    /**
     * 
     * @param localName
     * @return 
     */
    public final ElementLocator<D> addChild(final String localName) {

        return addChild(ELNode.NULL_NS_URI, localName);
    }


    /**
     * 
     * @param namespaceURI
     * @param localName
     * @return 
     */
    public final ElementLocator<D> addChild(final String namespaceURI,
                                            final String localName) {

        final ELElement child = new ELElement(namespaceURI, localName);

        getCurrent().elements.add(child);

        path.add(child);

        return this;
    }


    /**
     * Returns text value of current element.
     *
     * @return text value
     */
    public final String getText() {
        return getCurrent().text;
    }


    /**
     * Returns text value of current element and locate parent if specified.
     *
     * @param parent flag for locating parent.
     * @return text value.
     */
    public final String getText(final boolean parent) {

        final String text = getText();

        if (parent) {
            locateParent();
        }

        return text;
    }


    /**
     * Remove all child elements and add given <code>text</code> value to the
     * current element.
     *
     * @param text text value; may be null
     * @return self
     */
    public final ElementLocator<D> setText(final String text) {

        getCurrent().text = text;

        return this;
    }


    /**
     * Remove all child elements and add given <code>text</code> value to the
     * current element and locate parent if specified.
     *
     * @param text text value; may be null
     * @param parent flag for locating parent
     * @return self
     */
    public final ElementLocator<D> setText(final String text,
                                           final boolean parent) {

        setText(text);

        if (parent) {
            locateParent();
        }

        return this;
    }


    /**
     * 
     * @param localName attribute's local name
     * @return attribute's value
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
     * 
     * @param localName
     * @param value
     * @return 
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
     * 
     * @param document output document
     */
    public final void print(final D document) {

        if (document == null) {
            throw new NullPointerException("null document");
        }

        final Map<String, String> namespaces = new TreeMap<String, String>();

        final ELElement root = path.get(0);

        for (String namespaceURI : root.getNamespaceURIs()) {
            namespaces.put(namespaceURI, null);
        }

        long index = 0L;
        for (Entry<String, String> entry : namespaces.entrySet()) {
            entry.setValue("ns" + (index++));
        }

        namespaces.put(ELNode.XML_NS_URI, ELNode.XML_NS_PREFIX);

        print(path.get(0), document, Collections.unmodifiableMap(namespaces));
    }


    /**
     * 
     * @param root
     * @param document
     * @param namespaceMap
     */
    protected abstract void print(final ELElement root, final D document,
                                  final Map<String, String> namespaceMap);


    /**
     * Removes current element and locate parent. An
     * <code>IllegalStateException</code> will be thrown if currently on the
     * root.
     */
    public final void removeCurrent() {

        if (path.size() == 1) {
            throw new IllegalStateException("can't remove the root element");
        }

        final ELElement element = getCurrent();

        locateParent().getCurrent().elements.remove(element);
    }


    /**
     * Returns currently located element.
     *
     * @return current element
     */
    protected final ELElement getCurrent() {
        return path.get(path.size() - 1);
    }


    /**
     * Returns JSOM representation.
     *
     * @return a JSON string.
     */
    public final String toJSON() {
        return path.get(0).toJSON();
    }


    /** element path. */
    private final List<ELElement> path = new ArrayList<ELElement>();
}

