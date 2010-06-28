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
     *
     */
    private static class IElement {


        /**
         * 
         * @param element
         * @param index
         */
        public IElement(final Element element, final int index) {
            super();

            if (element == null) {
                throw new IllegalArgumentException(
                    "param:0:" + Element.class + ": is null");
            }

            if (index < 0) {
                throw new IllegalArgumentException(
                    "param:1:" + Integer.TYPE + ":" + index + " < 0");
            }

            this.element = element;
            this.index = index;

            table = new Hashtable<String, Vector<IElement>>();
        }


        /**
         *
         * @param namespace
         * @param name
         * @return
         */
        private String key(final String namespace, final String name) {

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


        private String key(final Element element) {

            if (element == null) {
                throw new IllegalArgumentException(
                    "param:0:" + Element.class + ": is null");
            }

            return key(element.getNamespace(), element.getName());
        }


        private String key(final IElement element) {

            if (element == null) {
                throw new IllegalArgumentException(
                    "param:0:" + IElement.class + ": is null");
            }

            return key(element.element);
        }


        /**
         *
         * @param namespace
         * @param name
         * @return
         */
        private Vector<IElement> getChildren(final String namespace,
                                             final String name) {

            if (namespace == null) {
                throw new IllegalArgumentException(
                    "param:0:" + String.class + ": is null");
            }

            if (name == null) {
                throw new IllegalArgumentException(
                    "param:1:" + String.class + ": is null");
            }

            final String key = key(namespace, name);
            Vector<IElement> children = table.get(key);
            if (children == null) {
                children = new Vector<IElement>();
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
                    children.addElement(new IElement(child, i));
                }
            }
            return children;
        }


        /**
         *
         * @param element
         * @return
         */
        private Vector<IElement> getChildren(final IElement element) {
            return getChildren(element.element.getNamespace(),
                               element.element.getName());
        }


        /**
         *
         * @param namespace
         * @param name
         * @param index
         */
        private void removeChild(final IElement child) {

            if (child == null) {
                throw new IllegalArgumentException(
                    "param:0:" + IElement.class + ": is null");
            }

            element.removeChild(child.index);
            getChildren(child).remove(child);
        }


        private Element element;
        private int index;

        private Hashtable<String, Vector<IElement>> table;
    }


    /**
     *
     * @param url
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
     *
     * @param element
     */
    public ElementLocator(final Document document)
        throws XmlPullParserException {

        super();

        path = new Vector<IElement>();

        final int childCount = document.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT != document.getType(i)) {
                continue;
            }
            path.addElement(new IElement(document.getElement(i), i));
            break;
        }

        if (path.isEmpty()) {
            throw new XmlPullParserException("no root element found");
        }
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

        return child(name, index);
    }


    /**
     *
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator locateFirstChild(final String name)
        throws XmlPullParserException {

        return locateChild(name, 0);
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
     */
    public ElementLocator locateLastChild(final String name)
        throws XmlPullParserException {

        return child(name, count(name) - 1);
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

        return child(namespace, name, count(name) - 1);
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

        return child(namespace, name, index);
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
     * Locates child identified by given parameters.
     *
     * @param namespace child's namespace
     * @param name child's name
     * @param index child's index
     * @throw XmlPullParserException if given child is not found
     */
    public ElementLocator child(final String namespace, final String name,
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

        final Vector<IElement> children =
            path.lastElement().getChildren(namespace, name);

        if (children.size() <= index) {
            throw new XmlPullParserException(
                "{" + namespace + "}name[" + index + "] is not found");
        }

        path.addElement(children.elementAt(index));

        return this;
    }


    /**
     *
     * @return
     */
    public int getDepth() {
        return path.size() - 1;
    }


    /**
     *
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator addAndLocateChild(final String name)
        throws XmlPullParserException {

        return addAndLocateChild(XmlPullParser.NO_NAMESPACE, name);
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

        return child(namespace, name);
    }


    /**
     * Add and locate a new child element with
     * {@link org.xmlpull.v1.XmlPullParser#NO_NAMESPACE} as namespace.
     *
     * @param name new child element's name
     * @return self
     * @throws XmlPullParserException
     * @see #child(java.lang.String, java.lang.String)
     * @see org.xmlpull.v1.XmlPullParser#NO_NAMESPACE
     */
    public ElementLocator child(final String name)
        throws XmlPullParserException {

        return child(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * Add and locate a new child element.
     *
     * @param namespace new child element's namespace
     * @param name new child element's name
     * @return self
     * @throws XmlPullParserException
     */
    public ElementLocator child(final String namespace, final String name)
        throws XmlPullParserException {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:namespace:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:name:" + String.class + ": is null");
        }


        final int count = count(namespace, name);

        final Node current = path.lastElement().element;
        current.addChild(Node.ELEMENT, current.createElement(namespace, name));

        return child(namespace, name, count);
    }


    /**
     *
     * @return
     */
    public boolean atRoot() {
        return (path.size() == 1);
    }


    /**
     *
     * @throws XmlPullParserException
     */
    public ElementLocator root() throws XmlPullParserException {

        while (!atRoot()) {
            parent();
        }

        return this;
    }


    /**
     *
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator locateRoot() throws XmlPullParserException {
        return root();
    }


    /**
     *
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator locateParent() throws XmlPullParserException {
        return parent();
    }


    /**
     *
     * @throws XmlPullParserException
     */
    public ElementLocator parent() throws XmlPullParserException {

        if (atRoot()) {
            throw new XmlPullParserException("no parent");
        }

        path.removeElementAt(path.size() - 1);

        return this;
    }


    /**
     *
     * @param name
     * @return
     */
    public int getChildCount(final String name) {
        return count(name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     */
    public int getChildCount(final String namespace, final String name) {
        return count(namespace, name);
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

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class +": is null");
        }

        return path.lastElement().getChildren(namespace, name).size();
    }


    /**
     * Identical to <code>getChildText(XmlPullParser.NO_NAMESPACE, name)</code>.
     *
     * @param name child element name.
     * @return specified child element's text.
     * @throws XmlPullParserException if any error occurs.
     * @see #getChildText(java.lang.String, java.lang.String, int)
     */
    public String getChildText(final String name, int index)
        throws XmlPullParserException {

        return getChildText(XmlPullParser.NO_NAMESPACE, name, index);
    }


    /**
     *
     * @param namespace
     * @param name
     * @param index
     * @return
     * @throws XmlPullParserException
     */
    public String getChildText(final String namespace, final String name,
                               final int index)
        throws XmlPullParserException {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class +": is null");
        }

        if (index < 0) {
            throw new IllegalArgumentException(
                "param:2:" + Integer.TYPE + ":" + index + " < 0");
        }

        child(namespace, name, index);
        final String text = getText();
        parent();
        return text;
    }


    /**
     *
     * @return
     */
    public String getNamespace() {
        return path.lastElement().element.getNamespace();
    }


    /**
     *
     * @return
     */
    public String getName() {
        return path.lastElement().element.getName();
    }


    /*
     * 
     * @param namespace
     * @param name
     * @throws XmlPullParserException
    public void require(final String namespace, final String name)
        throws XmlPullParserException {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        if (!namespace.equals(path.lastElement().element.getNamespace())
            || !name.equals(path.lastElement().element.getName())) {

            throw new XmlPullParserException(
                "actual: {" + path.lastElement().element.getNamespace() + "}"
                + path.lastElement().element.getName()
                + " / expected: {" + namespace + "}" + name);
        }
    }
     */


    /**
     *
     * @param name
     * @return
     */
    public String getAttribute(final String name) {
        return getAttr(name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public String getAttribute(final String namespace, final String name) {
        return getAttr(namespace, name);
    }


    /**
     *
     * @param name
     * @return
     * @see org.kxml2.kdom.Element#getAttributeValue(
     *      java.lang.String, java.lang.String)
     */
    public String getAttr(final String name) {
        return getAttr(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     * @see org.kxml2.kdom.Element#getAttributeValue(
     *      java.lang.String, java.lang.String)
     */
    public String getAttr(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        return path.lastElement().element.getAttributeValue(namespace, name);
    }


    /**
     * 
     * @param name
     * @param value
     */
    public void setAttribute(final String name, final String value) {
        setAttr(name, value);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @param value
     */
    public void setAttribute(final String namespace, final String name,
                             final String value) {

        setAttr(namespace, name, value);
    }


    /**
     *
     * @param name
     * @param value
     * @see org.kxml2.kdom.Element#setAttribute(
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public void setAttr(final String name, final String value) {
        setAttr(XmlPullParser.NO_NAMESPACE, name, value);
    }


    /**
     *
     * @param namespace
     * @param name
     * @param value
     * @see org.kxml2.kdom.Element#setAttribute(
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public void setAttr(final String namespace, final String name,
                        final String value) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        path.lastElement().element.setAttribute(namespace, name, value);
    }


    /**
     * Returns text value.
     *
     * @param appendIgnorableWhiteSpaces
     * @return text value of the current element or null.
     */
    public String getText() {

        final Element current = path.lastElement().element;

        StringBuffer buffer = null;

        final int childCount = current.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!current.isText(i)
                || (Node.IGNORABLE_WHITESPACE == current.getType(i))) {
                continue;
            }
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            buffer.append(current.getText(i));
        }

        if (buffer == null) {
            return null;
        }

        return buffer.toString();
    }


    /**
     *
     * @param text
     */
    public ElementLocator setText(final String text) {
        path.lastElement().element.clear();
        path.lastElement().table.clear();
        if (text != null) {
            path.lastElement().element.addChild(Node.TEXT, text);
        }
        return this;
    }


    /**
     * Removes current element and locate to the parent.
     *
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator remove() throws XmlPullParserException {

        if (atRoot()) {
            throw new XmlPullParserException("root element can't be removed");
        }

        path.elementAt(path.size() - 2).removeChild(path.lastElement());
        path.removeElementAt(path.size() - 1);

        return this;
    }


    /**
     *
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator removeCurrentAndLocateParent()
        throws XmlPullParserException {

        return remove();
    }


    /**
     *
     * @return
     */
    public Document getDocument() {
        return (Document) path.elementAt(0).element.getParent();
    }


    private final Vector<IElement> path;
}
