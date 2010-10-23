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

package jinahya.kxml2.kdom;


import java.util.Vector;

import jinahya.xml.ElementLocator;

import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import org.xmlpull.v1.XmlPullParser;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KDOMElementLocator implements ElementLocator<Element> {


    /**
     * Creates a new instance.
     *
     * @param root root element to be located.
     */
    public KDOMElementLocator(final Element root) {
        super();

        if (root == null) {
            throw new IllegalArgumentException("null root");
        }

        elements = new Vector<Element>();
        elements.addElement(root);
    }


    @Override
    public KDOMElementLocator root() {
        for (int i = elements.size() - 1; i > 0; i--) {
            elements.removeElementAt(i);
        }
        return this;
    }


    @Override
    public KDOMElementLocator parent() {

        if (elements.size() == 1) {
            throw new IllegalStateException("on root");
        }

        elements.removeElementAt(elements.size() - 1);

        return this;
    }


    @Override
    public int count(final String name) {
        return countNS(XmlPullParser.NO_NAMESPACE, name);
    }


    @Override
    public int countNS(final String namespace, final String name) {

        int count = 0;

        final int childCount = current().getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT == current().getType(i)) {
                final Element child = current().getElement(i);
                if (child.getNamespace().equals(namespace)
                    && child.getName().equals(name)) {
                    count++;
                }
            }
        }

        return count;
    }


    @Override
    public KDOMElementLocator child(final String name, final int index) {
        return childNS(XmlPullParser.NO_NAMESPACE, name, index);
    }


    @Override
    public KDOMElementLocator childNS(final String namespace, final String name,
                                      final int index) {

        int count = 0;

        final int childCount = current().getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT == current().getType(i)) {
                final Element child = current().getElement(i);
                if (child.getNamespace().equals(namespace)
                    && child.getName().equals(name)) {
                    if (count++ == index) {
                        elements.addElement(child);
                        return this;
                    }
                }
            }
        }

        throw new IndexOutOfBoundsException("no child at " + index);
    }


    @Override
    public KDOMElementLocator child(final String name, final String value) {
        return childNS(XmlPullParser.NO_NAMESPACE, name, value);
    }


    @Override
    public KDOMElementLocator childNS(final String namespace, final String name,
                                      final String value) {

        final Element child = current().createElement(namespace, name);
        current().addChild(Node.ELEMENT, child);
        elements.addElement(child);
        current().addChild(Node.TEXT, value);
        return this;
    }


    @Override
    public String attribute(final String name) {
        return attributeNS(XmlPullParser.NO_NAMESPACE, name);
    }


    @Override
    public String attributeNS(final String namespace, final String name) {
        return current().getAttributeValue(namespace, name);
    }


    @Override
    public KDOMElementLocator attribute(final String name, final String value) {
        return attributeNS(XmlPullParser.NO_NAMESPACE, name, value);
    }


    @Override
    public KDOMElementLocator attributeNS(final String namespace,
                                          final String name,
                                          final String value) {

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        current().setAttribute(namespace, name, value);

        return this;
    }


    @Override
    public String text() {

        final int childCount = current().getChildCount();
        if (childCount == 0) {
            return null;
        }

        StringBuffer buffer = null;
        int type;
        for (int i = 0; i < childCount; i++) {
            type = current().getType(i);
            if (type == Node.TEXT || type == Node.CDSECT) {
                if (buffer == null) {
                    buffer = new StringBuffer();
                }
                buffer.append(current().getText(i));
            }
        }

        if (buffer == null) {
            return null;
        }

        return buffer.toString();
    }


    @Override
    public String namespace() {
        return current().getNamespace();
    }


    @Override
    public String name() {
        return current().getName();
    }


    @Override
    public Element current() {
        return elements.elementAt(elements.size() - 1);
    }


    private final Vector<Element> elements;
}
