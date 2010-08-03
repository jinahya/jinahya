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
import org.kxml2.kdom.Node;
import org.kxml2.kdom.Element;

import org.xmlpull.v1.XmlPullParser;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ElementLocator {


    /**
     * 
     */
    private static class LinkedElement {


        String key(final Element element) {
            return key(element.getNamespace(), element.getName());
        }


        String key(final String namespace, final String name) {
            return "{" + namespace + "}" + name;
        }


        Vector<LinkedElement> children(final Element element) {
            return children(element.getNamespace(), element.getName());
        }


        Vector<LinkedElement> children(final String namespace,
                                       final String name) {

            if (namespace == null) {
                throw new IllegalArgumentException(
                    "param:0:namespace:" + String.class + ": is null");
            }

            if (name == null) {
                throw new IllegalArgumentException(
                    "param:1:name:" + String.class + ": is null");
            }

            final String key = key(namespace, name);

            Vector<LinkedElement> children = classes().get(key);

            if (children == null) {

                children = new Vector<LinkedElement>();
                classes().put(key, children);

                final int childCount = element.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    if (Node.ELEMENT != element.getType(i)) {
                        continue;
                    }
                    final Element childElement = element.getElement(i);
                    if (namespace.equals(childElement.getNamespace())
                        && name.equals(childElement.getName())) {
                        final LinkedElement child = new LinkedElement();
                        child.parent = this;
                        child.element = childElement;
                        child.index = i;
                        children.addElement(child);
                    }
                }
            }

            return children;
        }



        LinkedElement remove(final int index) {

            String key = null;
            if (element.getType(index) == Node.ELEMENT) {
                key = key(element.getElement(index));
            }

            element.removeChild(index);

            final Enumeration<Vector<LinkedElement>> e = classes().elements();

            while (e.hasMoreElements()) {

                final Vector<LinkedElement> children = e.nextElement();

                for (int i = children.size() - 1; i >= 0; i--) {
                    if (children.elementAt(i).index < index) {
                        continue;
                    }
                    if (children.elementAt(i).index == index) {
                        children.removeElementAt(i);
                    } else {
                        children.elementAt(i).index--;
                    }
                }
            }

            if (key != null && classes.containsKey(key)
                && classes.get(key).isEmpty()) {

                classes.remove(key);
            }

            return this;
        }


        Hashtable<String, Vector<LinkedElement>> classes() {
            if (classes == null) {
                classes = new Hashtable<String, Vector<LinkedElement>>();
            }

            return classes;
        }


        private LinkedElement parent;
        private Element element;
        private int index;

        private Hashtable<String, Vector<LinkedElement>> classes;
    }


    /**
     * Creates a new instance. An <code>IllegalArgumentException</code> will be
     * thrown if specified <code>document</code> is null.
     *
     * @param document document instance
     */
    public ElementLocator(final Document document) {

        super();

        if (document == null) {
            throw new IllegalArgumentException(
                "param:0:" + Document.class + ": is null");
        }

        final int childCount = document.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT == document.getType(i)) {
                current = new LinkedElement();
                current.parent = null;
                current.element = document.getElement(i);
                current.index = i;
                break;
            }
        }

        if (current == null) {
            throw new IllegalArgumentException("no root element");
        }
    }


    /**
     *
     * @param element
     */
    public ElementLocator(final Element element) {

        if (element == null) {
            throw new IllegalArgumentException(
                "param:0:" + Element.class + ": is null");
        }

        current = new LinkedElement();
        current.parent = null;
        current.element = element;
        current.index = -1;
    }


    /**
     * Adds a new child element with no namespace.
     *
     * @param name new element's name
     * @return self
     * @see #child(String, String)
     */
    public ElementLocator child(final String name) {

        if (name == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        return childNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * Adds a new child element and locate it.
     *
     * @param namespace new element's namespace
     * @param name new element's name
     * @return self
     */
    public ElementLocator childNS(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        final Vector<LinkedElement> children =
            current.children(namespace, name);

        final int childCount = current.element.getChildCount();

        current.element.addChild(
            Node.ELEMENT, current.element.createElement(namespace, name));

        final LinkedElement child = new LinkedElement();
        child.parent = current;
        child.element = current.element.getElement(childCount);
        child.index = childCount;

        children.addElement(child);

        current = child;

        return this;
    }


    /**
     * Locates a child element with no namespace.
     *
     * @param name child element's name
     * @param index child element's index
     * @return self
     * @see #child(String, String, int)
     */
    public ElementLocator child(final String name, final int index) {
        return childNS(XmlPullParser.NO_NAMESPACE, name, index);
    }


    /**
     * Locates child element by given conditions.
     *
     * @param namespace child element's namespace
     * @param name child element's name
     * @param index child element's index
     * @return self
     * @throws ArrayIndexOutOfBoundsException
     * @see LinkedElement#child(String, String, int)
     */
    public ElementLocator childNS(final String namespace, final String name,
                                  final int index) {

        current = current.children(namespace, name).elementAt(index);

        return this;
    }


    /**
     *
     * @return self
     */
    public ElementLocator root() {

        while (current.parent != null) {
            current = current.parent;
        }

        return this;
    }


    /**
     *
     * @return
     * @throws IllegalStateException if there is no parent
     */
    public ElementLocator parent() {

        if (current.parent != null) {
            current = current.parent;
            return this;
        }

        throw new IllegalStateException("no parent");
    }


    /**
     * Removes all child nodes from the current element.
     *
     * @return self
     */
    public ElementLocator clear() {
        final int childCount = current.element.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            current.remove(i);
        }
        return this;
    }


    /**
     * Removes all child which type is equals to given <code>type</code>.
     *
     * @param type node type
     * @return self
     */
    public ElementLocator clear(final int type) {
        final int childCount = current.element.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            if (current.element.getType(i) == type) {
                current.remove(i);
            }
        }
        return this;
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @see #count(String, String)
     */
    public int count(final String name) {
        return countNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     */
    public int countNS(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        return current.children(namespace, name).size();
    }


    /**
     * Returns an attribute's value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @return attribute's value
     * @see Element#getAttributeValue(String, String)
     */
    public String attribute(final String name) {

        if (name == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        return attributeNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * Returns an attribute's value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @return attribute's value
     * @see Element#getAttributeValue(String, String)
     */
    public String attributeNS(final String namespace, final String name) {

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
     *
     * @param name
     * @param value
     * @return
     */
    public ElementLocator attribute(final String name, final String value) {

        if (name == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        return attributeNS(XmlPullParser.NO_NAMESPACE, name, value);
    }


    /**
     * Sets new attribute value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @param value attribute's new value or null.
     * @return self
     * @see Element#setAttribute(String, String, String)
     */
    public ElementLocator attributeNS(final String namespace, final String name,
                                      final String value) {

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
     * Returns text value. Identical to <code>text(false></code>
     *
     * @return text value or null.
     * @see #text(boolean)
     */
    public String text() {
        return text(false);
    }


    /**
     * Returns text value.
     *
     * @param parent locate parent before returning value.
     * @return text value or null
     */
    public String text(final boolean parent) {

        boolean appended = false;
        final StringBuffer buffer = new StringBuffer();
        final int childCount = current.element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (current.element.getType(i) == Node.TEXT) {
                buffer.append(current.element.getText(i));
                appended = true;
            }
        }

        if (parent) {
            parent();
        }

        return (appended ? buffer.toString() : null);
    }


    /**
     *
     * @param text text value to be added
     * @return self
     * @throws IllegalArgumentException if text is null
     */
    public ElementLocator text(final String text) {

        if (text == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        current.element.addChild(Node.TEXT, text);

        return this;
    }


    /**
     * Removes current element from its parent and locate to the parent.
     *
     * @return self
     * @throws IllegalStateException if there is no parent
     */
    public ElementLocator remove() {

        final int indexToRemove = current.index;

        parent(); // IllegalStateException

        current.remove(indexToRemove);

        return this;
    }


    /**
     * Prints child elements and texts to given buffer and locate parent.
     * Identical to <code>print(buffer, false)</code>.
     *
     * @param buffer
     * @return
     * @throws IllegalArgumentException if the buffer is null.
     */
    public StringBuffer print(final StringBuffer buffer) {
        return print(buffer, false);
    }


    /**
     *
     * @param buffer
     * @param parent
     * @return given <code>buffer</code>.
     * @throws IllegalArgumentException if buffer is null.
     * @throws IllegalStateException if there is no parent to locate.
     */
    public StringBuffer print(final StringBuffer buffer, final boolean parent) {

        if (buffer == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        try {
            return print(current.element, buffer);
        } finally {
            if (parent) {
                parent();
            }
        }
    }


    /**
     *
     * @param element
     * @param buffer
     * @return given <code>buffer</code>
     */
    StringBuffer print(final Element element, final StringBuffer buffer) {

        buffer.append(("<" + element.getName()));
        final int attributeCount = element.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            buffer.append((" " + element.getAttributeName(i) + "=\""
                           + element.getAttributeValue(i) + "\""));
        }

        final Vector<Element> children = new Vector<Element>();
        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT == element.getType(i)) {
                children.addElement(element.getElement(i));
            }
        }

        if (children.isEmpty()) {
            final int offset = buffer.length();
            boolean appended = false;
            for (int i = 0; i < childCount; i++) {
                if (element.getType(i) == Node.TEXT) {
                    buffer.append(element.getText(i));
                    appended = true;
                }
            }
            if (appended) {
                buffer.insert(offset, '>');
                buffer.append(("</" + element.getName() + ">"));
            } else {
                buffer.append("/>");
            }
        } else {
            buffer.append('>');
            for (int i = 0; i < children.size(); i++) {
                print(children.elementAt(i), buffer);
            }
            buffer.append(("</" + element.getName() + ">"));
        }

        return buffer;
    }


    /**
     *
     * @return
     */
    public Element current() {
        return current.element;
    }


    private LinkedElement current;
}
