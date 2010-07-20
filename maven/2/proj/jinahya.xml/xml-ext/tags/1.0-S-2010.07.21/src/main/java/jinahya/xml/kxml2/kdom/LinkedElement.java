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


import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import org.xmlpull.v1.XmlPullParser;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class LinkedElement {


    /**
     *
     * @param document
     * @return
     * @throws IllegalArgumentException if given <code>document</code> is null
     *         or has no child element.
     */
    public static LinkedElement newInstance(final Document document) {

        if (document == null) {
            throw new IllegalArgumentException(
                "param:0:" + Document.class + ": is null");
        }

        final int childCount = document.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT == document.getType(i)) {
                return new LinkedElement(null, document.getElement(i), i);
            }
        }

        throw new IllegalArgumentException(
            "param:0:" + Document.class + ":" + document +
            " has no child elements");
    }


    /**
     *
     * @param parent
     * @param element
     * @param index
     */
    private LinkedElement(final LinkedElement parent, final Element element,
                          final int index) {

        super();

        this.parent = parent;
        this.element = element;
        this.index = index;

        classes = new  Hashtable<String, Vector<LinkedElement>>();
    }


    /**
     *
     * @return
     */
    public Hashtable<String, Vector<String>> attributes() {
        return attributes(new Hashtable<String, Vector<String>>());
    }


    /**
     *
     * @param attributes
     * @return given <code>attributes</code>
     */
    public Hashtable<String, Vector<String>> attributes(
        final Hashtable<String, Vector<String>> attributes) {

        final int attributeCount = element.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {

            final String namespace = element.getAttributeNamespace(i);
            final String name = element.getAttributeName(i);

            Vector<String> names = attributes.get(namespace);
            if (names == null) {
                names = new Vector<String>();
                attributes.put(namespace, names);
            }
            names.addElement(name);
        }

        return attributes;
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     */
    public String attribute(final String name) {
        return attributeNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     * @see org.kxml2.kdom.Element#getAttributeValue(String, String)
     */
    public String attributeNS(final String namespace, final String name) {
        return element.getAttributeValue(namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @param value
     */
    public void attribute(final String name, final String value) {
        attributeNS(XmlPullParser.NO_NAMESPACE, name, value);
    }


    /**
     *
     * @param namespace
     * @param name
     * @param value
     * @see org.kxml2.kdom.Element#setAttribute(String, String, String)
     */
    public void attributeNS(final String namespace, final String name,
                            final String value) {

        element.setAttribute(namespace, name, value);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     */
    public LinkedElement child(final String name) {
        return childNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public LinkedElement childNS(final String namespace, final String name) {

        final Vector<LinkedElement> children = children(namespace, name);

        final int childCount = element.getChildCount();

        element.addChild(Node.ELEMENT, element.createElement(namespace, name));

        LinkedElement added = new LinkedElement(
            this, element.getElement(childCount), childCount);

        children.addElement(added);

        return added;
    }


    /**
     * 
     * @param namespace
     * @param name
     * @param index
     * @return
     */
    public LinkedElement child(final String name, final int index) {
        return childNS(XmlPullParser.NO_NAMESPACE, name, index);
    }


    /**
     *
     * @param namespace
     * @param name
     * @param index
     * @return
     * @throws ArrayIndexOutOfBoundsException if the index is negative or not
     *         less than the children count of given kind.
     */
    public LinkedElement childNS(final String namespace, final String name,
                                 final int index) {

        return children(namespace, name).elementAt(index);
    }


    Vector<LinkedElement> children() {

        final Vector<LinkedElement> children = new Vector<LinkedElement>();

        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT != element.getType(i)) {
                continue;
            }
            final Element child = element.getElement(i);
            children.addElement(new LinkedElement(this, child, i));
        }

        return children;
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    private Vector<LinkedElement> children(final String namespace,
                                           final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:namespace:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:name:" + String.class + ": is null");
        }

        final String key = "{" + namespace + "}" + name;

        Vector<LinkedElement> children = classes.get(key);

        if (children == null) {
            children = new Vector<LinkedElement>();

            final int childCount = element.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (Node.ELEMENT != element.getType(i)) {
                    continue;
                }
                final Element child = element.getElement(i);
                if (!(namespace.equals(child.getNamespace()))
                    || !(name.equals(child.getName()))) {
                    continue;
                }

                children.addElement(new LinkedElement(this, child, i));
            }

            classes.put(key, children);
        }

        return children;
    }


    /**
     * Returns the number of children by given kind.
     *
     * @param namespace
     * @param name
     * @return the number of children by given kind.
     */
    public int count(final String namespace, final String name) {
        return children(namespace, name).size();
    }


    /**
     * 
     * @return
     */
    public Document document() {
        return (Document) root().element.getParent();
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws NoSuchElementException if there is no children by given kind.
     */
    public LinkedElement first(final String namespace, final String name) {
        return children(namespace, name).firstElement();
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     * @throws NoSuchElementException if there is no children by given kind.
     */
    public LinkedElement last(final String namespace, final String name) {
        return children(namespace, name).lastElement();
    }


    /**
     *
     * @return
     */
    public LinkedElement parent() {
        return parent(false);
    }


    /**
     * 
     * @param remove
     * @return
     * @throws IllegalStateException if there is no parent
     */
    private LinkedElement parent(final boolean remove) {

        if (parent == null) {
            throw new IllegalStateException("no parent");
        }

        if (remove) {
            parent.remove(index);
        }

        return parent;
    }


    /**
     * 
     */
    public LinkedElement remove() {
        return parent(true);
    }


    /**
     * 
     * @param index
     */
    private void remove(final int index) {

        element.removeChild(index);

        final Enumeration<Vector<LinkedElement>> elements =
            classes.elements();

        while (elements.hasMoreElements()) {

            final Vector<LinkedElement> children = elements.nextElement();

            for (int i = children.size() - 1; i >= 0; i--) {
                if (children.elementAt(i).index >= index) {
                    if (children.elementAt(i).index == index) {
                        children.removeElementAt(i);
                    } else {
                        children.elementAt(i).index--;
                    }
                }
            }
        }
    }


    /**
     *
     * @return
     */
    public LinkedElement root() {
        if (parent != null) {
            return parent.root();
        }
        return this;
    }


    /**
     *
     * @return text value or null
     */
    public String text() {

        final StringBuffer buffer = new StringBuffer();

        if (text(buffer)) {
            return buffer.toString();
        }

        return null;
    }


    /**
     *
     * @param buffer a buffer to which text values are appended.
     * @return true if any text value appended to given <code>buffer</code>,
     *         false otherwise.
     */
    public boolean text(final StringBuffer buffer) {

        if (buffer == null) {
            throw new IllegalArgumentException(
                "param:0:" + StringBuffer.class + ": is null");
        }

        boolean appended = false;
        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (element.isText(i)
                && (Node.IGNORABLE_WHITESPACE != element.getType(i))) {

                buffer.append(element.getText(i));
                appended = true;
            }
        }

        return appended;
    }


    /**
     *
     * @param text
     */
    public void text(final String text) {

        final int childCount = element.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            if (Node.TEXT == element.getType(i)
                || Node.IGNORABLE_WHITESPACE == element.getType(i)) {

                remove(i);
            }
        }

        if (text != null) {
            element.addChild(Node.TEXT, text);
        }
    }


    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + (parent == null ? 0 : parent.hashCode());
        result = 37 * result + element.hashCode();
        result = 37 * result + index;

        return result;
    }


    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof LinkedElement)) {
            return false;
        }

        final LinkedElement casted = (LinkedElement) obj;

        if (!(parent == casted.parent
              || (parent != null && parent.equals(casted.parent)))) {
            return false;
        }

        if (!(element == casted.element
              || (element != null && element.equals(casted.element)))) {
            return false;
        }

        if (index != casted.index) {
            return false;
        }

        return true;
    }


    public String namespace() {
        return element.getNamespace();
    }


    public String name() {
        return element.getName();
    }


    private LinkedElement parent;
    private Element element;
    private int index;

    private transient Hashtable<String, Vector<LinkedElement>> classes;
}
