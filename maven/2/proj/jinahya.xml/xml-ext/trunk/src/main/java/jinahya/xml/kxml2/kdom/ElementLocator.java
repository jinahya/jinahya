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


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.kxml2.io.KXmlParser;
import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ElementLocator {


    /**
     * Returns an universal name.
     *
     * @param namespace namespace
     * @param name name
     * @return the universal name
     * @see <a href="http://www.jclark.com/xml/xmlns.htm">XML Namespaces</a>
     */
    static String key(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        return "{" + namespace + "}" + name;
    }


    /**
     * Returns an universal name.
     *
     * @param element element to be expressed
     * @return the universal name
     */
    static String key(final Element element) {

        if (element == null) {
            throw new IllegalArgumentException(
                "param:0:" + Element.class + ": is null");
        }

        return key(element.getNamespace(), element.getName());
    }


    /**
     *
     */
    static final class IElement {


        /**
         * 
         * @param element
         * @return
         */
        static String key(final IElement element) {

            if (element == null) {
                throw new IllegalArgumentException(
                    "param:0:" + IElement.class + ": is null");
            }

            return ElementLocator.key(element.element);
        }


        /**
         *
         * @param parent
         * @param element
         * @param index
         */
        IElement(final IElement parent, final Element element,
                 final int index) {
            super();

            if (element == null) {
                throw new IllegalArgumentException(
                    "param:0:" + Element.class + ": is null");
            }

            if (index < 0) {
                throw new IllegalArgumentException(
                    "param:1:" + Integer.TYPE + ":" + index + " < 0");
            }

            this.parent = parent;

            this.element = element;
            this.index = index;

            table = new Hashtable<String, Vector<IElement>>();
        }


        /**
         * Returns child elements.
         *
         * @param namespace child element's namespace.
         * @param name child element's name
         * @return children
         */
        Vector<IElement> getChildren(final String namespace,
                                     final String name) {

            if (namespace == null) {
                throw new IllegalArgumentException(
                    "param:0:" + String.class + ": is null");
            }

            if (name == null) {
                throw new IllegalArgumentException(
                    "param:1:" + String.class + ": is null");
            }

            final String key = ElementLocator.key(namespace, name);
            Vector<IElement> children = table.get(key);
            if (children == null) {
                children = new Vector<IElement>();
                table.put(key, children);
                final int childCount  = element.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    if (Node.ELEMENT != element.getType(i)) {
                        continue;
                    }
                    final Element child = element.getElement(i);
                    if (!namespace.equals(child.getNamespace())
                        || !name.equals(child.getName())) {
                        continue;
                    }
                    children.addElement(new IElement(this, child, i));
                }
            }
            return children;
        }


        /**
         *
         * @param element
         * @return
         */
        Vector<IElement> getSiblings(final IElement element) {
            return getChildren(element.element.getNamespace(),
                               element.element.getName());
        }


        /**
         *
         * @param namespace
         * @param name
         * @param index
         */
        void removeChild(final IElement child) {

            if (child == null) {
                throw new IllegalArgumentException(
                    "param:0:" + IElement.class + ": is null");
            }

            element.removeChild(child.index);
            getSiblings(child).remove(child);
        }


        @Override
        public boolean equals(final Object obj) {
            if (!(obj instanceof IElement)) {
                return false;
            }
            final IElement casted = (IElement) obj;
            return (casted.element.equals(element)) && (casted.index == index);
        }


        @Override
        public int hashCode() {
            int result = 17;
            result = 37 * result + element.hashCode();
            result = 37 * result + index;
            return result;
        }


        @Override
        public String toString() {
            return (key(this) + "[" + index + "]");
        }


        private IElement parent;

        private Element element;
        private int index;

        private final Hashtable<String, Vector<IElement>> table;
    }


    /**
     *
     * @param file
     * @return
     */
    public ElementLocator newInstance(final File file)
        throws XmlPullParserException, IOException {

        if (file == null) {
            throw new IllegalArgumentException(
                "param:0:" + File.class + ": is null");
        }

        final InputStream in = new FileInputStream(file);
        try {
            return newInstance(in, null);
        } finally {
            in.close();
        }
    }


    /**
     *
     * @param url
     * @return
     */
    public ElementLocator newInstance(final URL url)
        throws XmlPullParserException, IOException {

        if (url == null) {
            throw new IllegalArgumentException(
                "param:0:" + URL.class + ": is null");
        }
        return newInstance(url.openStream(), null);
    }


    /**
     *
     * @param in
     * @param enc
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    public ElementLocator newInstance(final InputStream in, final String enc)
        throws XmlPullParserException, IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + InputStream.class + ": is null");
        }

        if (enc != null) {
            return newInstance(new InputStreamReader(in, enc));
        }

        final XmlPullParser parser = new KXmlParser();
        parser.setInput(in, enc);

        final Document document = new Document();
        document.parse(parser);

        return new ElementLocator(document);
    }


    /**
     *
     * @param in
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    public ElementLocator newInstance(final Reader in)
        throws XmlPullParserException, IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + Reader.class + ": is null");
        }

        final XmlPullParser parser = new KXmlParser();
        parser.setInput(in);

        final Document document = new Document();
        document.parse(parser);

        return new ElementLocator(document);
    }


    /**
     * Creates a new instance. An <code>IllegalArgumentException</code> will be
     * thrown if specified <code>document</code> is null.
     *
     * @param document document instance
     */
    public ElementLocator(final Document document) {

        super();

        //path = new Vector<IElement>();

        final int childCount = document.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT == document.getType(i)) {
                current = new IElement(null, document.getElement(i), i);
                //path.addElement(new IElement(null, document.getElement(i), i));
                break;
            }
        }

        //if (path.isEmpty()) {
        if (current == null) {
            throw new IllegalArgumentException("no root element found");
        }
    }


    /**
     *
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator first(final String name)
        throws XmlPullParserException {

        return first(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator first(final String namespace, final String name)
        throws XmlPullParserException {

        return locateFirstChild(namespace, name);
    }


    /**
     *
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator locateFirstChild(final String name)
        throws XmlPullParserException {

        return locateFirstChild(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator locateFirstChild(final String namespace,
                                           final String name)
        throws XmlPullParserException {

        return locateChild(namespace, name, 0);
    }


    /**
     *
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator last(final String name)
        throws XmlPullParserException {

        return last(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator last(final String namespace, final String name)
        throws XmlPullParserException {

        return locateLastChild(namespace, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator locateLastChild(final String namespace,
                                          final String name)
        throws XmlPullParserException {

        final int childCount = getChildCount(namespace, name);

        if (childCount == 0) {
            throw new XmlPullParserException("no " + key(namespace, name));
        }

        return locateChild(name, childCount - 1);
    }


    /**
     *
     * @param name
     * @param index
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator locateChild(final String name, final int index)
        throws XmlPullParserException {

        return locateChild(XmlPullParser.NO_NAMESPACE, name, index);
    }


    /**
     *
     * @param namespace
     * @param name
     * @param index
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator locateChild(final String namespace, final String name,
                                      final int index)
        throws XmlPullParserException {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        if (index < 0) {
            throw new IllegalArgumentException(
                "param:2: " + Integer.TYPE + ":" + index + " < 0");
        }

        final Vector<IElement> children = current.getChildren(namespace, name);

        if (children.size() <= index) {
            throw new XmlPullParserException(
                "{" + namespace + "}name[" + index + "] is not found");
        }

        current = children.elementAt(index);

        return this;
    }


    /**
     *
     * @param name
     * @param index
     * @throws XmlPullParserException if child not found
     */
    public ElementLocator child(final String name, final int index)
        throws XmlPullParserException {

        return child(XmlPullParser.NO_NAMESPACE, name, index);
    }


    /**
     *
     * @param namespace
     * @param name
     * @param index
     * @throws XmlPullParserException if child not found
     */
    public ElementLocator child(final String namespace, final String name,
                                final int index)
        throws XmlPullParserException {

        return locateChild(namespace, name, index);
    }


    /**
     *
     * @return
     */
    public int getDepth() {
        int depth = 0;
        for (IElement element = current; element.parent != null; depth++) {
            element = element.parent;
        }
        return depth;
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator addAndLocateChild(final String namespace,
                                            final String name)
        throws XmlPullParserException {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }


        final Vector<IElement> children = current.getChildren(namespace, name);

        final Node element = current.element;
        final int count = element.getChildCount();
        element.addChild(Node.ELEMENT, element.createElement(namespace, name));

        final IElement child =
            new IElement(current, element.getElement(count), count);

        children.addElement(child);

        current = child;

        return this;
    }


    /**
     * Calls {@link #addAndLocateChild(java.lang.String, java.lang.String)} with
     * {@link org.xmlpull.v1.XmlPullParser#NO_NAMESPACE} as
     * <code>namespace</code> value.
     *
     * @param name new child element's name
     * @return self
     * @throws XmlPullParserException
     * @see #addAndLocateChild(java.lang.String, java.lang.String)
     * @see org.xmlpull.v1.XmlPullParser#NO_NAMESPACE
     */
    public ElementLocator child(final String name)
        throws XmlPullParserException {

        return child(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return self
     * @throws XmlPullParserException
     */
    public ElementLocator child(final String namespace, final String name)
        throws XmlPullParserException {

        return addAndLocateChild(namespace, name);
    }


    /**
     *
     * @return
     */
    public boolean hasParent() {
        return (current.parent != null);
    }


    /**
     *
     * @return self
     * @see #locateRoot()
     */
    public ElementLocator root() {

        return locateRoot();
    }


    /**
     *
     * @return self
     */
    public ElementLocator locateRoot() {

        while (hasParent()) {
            parent();
        }

        return this;
    }


    /**
     *
     * @return self
     */
    public ElementLocator locateParent() {

        if (hasParent()) {
            current = current.parent;
        }

        return this;
    }


    /**
     *
     * @return self
     */
    public ElementLocator parent() {

        return locateParent();
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public int getChildCount(final String name) {

        return getChildCount(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     */
    public int getChildCount(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        return current.getChildren(namespace, name).size();
    }


    /**
     * 
     */
    public int count(final String name) {
        return count(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public int count(final String namespace, final String name) {
        return getChildCount(namespace, name);
    }


    /**
     *
     * @return
     */
    public String getCurrentNamespace() {
        return current.element.getNamespace();
    }


    /**
     * Returns the current element's name.
     *
     * @return current element's name
     */
    public String getCurrentName() {
        return current.element.getName();
    }


    /**
     * Returns an attribute's value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @return attribute's value
     * @see Element#getAttributeValue(String, String)
     */
    public String getAttribute(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        return current.element.getAttributeValue(namespace, name);
    }


    /**
     * A shortened of {@link #getAttribute(String, String)} with
     * {@link XmlPullParser#NO_NAMESPACE} as namespace.
     *
     * @param name attribute's name
     * @return attribute's value
     * @see #getAttribute(String, String)
     */
    public String attribute(final String name) {

        return attribute(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public String attribute(final String namespace, final String name) {

        return getAttribute(namespace, name);
    }


    /**
     *
     * @param name
     * @param value
     * @return self
     */
    public ElementLocator setAttribute(final String name, final String value) {
        return setAttribute(XmlPullParser.NO_NAMESPACE, name, value);
    }


    /**
     * Sets new attribute value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @param value attribute's new value or null.
     * @return self
     * @see org.kxml2.kdom.Element#setAttribute(String, String, String)
     */
    public ElementLocator setAttribute(final String namespace,
                                       final String name, final String value) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        current.element.setAttribute(namespace, name, value);

        return this;
    }


    /**
     * Find text value and (iif found) append it to given <code>buffer</code>.
     * link  {@link XmlPullParser XmlPullParser} link
     *
     * @param buffer a buffer to which text value will be appended.
     * @return self
     */
    public ElementLocator getText(final StringBuffer buffer) {

        if (buffer == null) {
            throw new IllegalArgumentException(
                "param:0:" + StringBuffer.class + ": is null");
        }

        final String text = getText();
        if (text != null) {
            buffer.append(text);
        }
        return this;
    }


    /**
     * Returns text value.
     *
     * @param appendIgnorableWhiteSpaces
     * @return current element's text value or null if there is no text node.
     */
    public String getText() {

        final Element element = current.element;

        StringBuffer buffer = null;

        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!element.isText(i)
                || (Node.IGNORABLE_WHITESPACE == element.getType(i))) {
                continue;
            }
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            buffer.append(element.getText(i));
        }

        if (buffer == null) {
            return null;
        }

        return buffer.toString();
    }


    /**
     * A shortened of {@link #getText()}.
     *
     * @return current element's text value or null if there is no text node.
     * @see #getText()
     */
    public String text() {
        return getText();
    }


    /**
     * Removes all child elements and set text. Calling this method with null
     * value will just make the current element empty.
     *
     * @return self
     * @param text text value
     */
    public ElementLocator setText(final String text) {
        current.element.clear();
        current.table.clear();
        if (text != null) {
            current.element.addChild(Node.TEXT, text);
        }
        return this;
    }


    /**
     * A shortened of {@link #setText(java.lang.String)}.
     *
     * @param text new text value
     * @return self
     * @see #setText(java.lang.String)
     */
    public ElementLocator text(final String text) {
        return setText(text);
    }


    /**
     * A shortened of {@link #removeCurrentAndLocateParent()}.
     *
     * @return self
     * @throws XmlPullParserException if currently at root.
     * @see #removeCurrentAndLocateParent()
     */
    public ElementLocator remove() throws XmlPullParserException {
        return removeCurrentAndLocateParent();
    }


    /**
     * Removes current element from its parent and locate to the parent.
     *
     * @return self
     * @throws XmlPullParserException if currently at root.
     */
    public ElementLocator removeCurrentAndLocateParent()
        throws XmlPullParserException {

        if (!hasParent()) {
            throw new XmlPullParserException("root element can't be removed");
        }

        final String key = key(current.element);

        final Enumeration<String> keys = current.parent.table.keys();
        while (keys.hasMoreElements()) {
            final Enumeration<IElement> elements =
                current.parent.table.get(keys.nextElement()).elements();
            while (elements.hasMoreElements()) {
                final IElement element = elements.nextElement();
                if (element.index > current.index) {
                    element.index--;
                }
            }
        }

        current.parent.element.removeChild(current.index);
        current.parent.table.get(key).removeElement(current);

        current = current.parent;

        return this;
    }


    /**
     * Returns the document instance.
     *
     * @return the document instance or null
     */
    public Document getDocument() {
        Element element = current.element;
        while (element.getParent() instanceof Element) {
            element = (Element) element.getParent();
        }
        return (Document) element.getParent();
//        return (Document) path.elementAt(0).element.getParent();
    }


    private IElement current;
//    private final Vector<IElement> path;
}
