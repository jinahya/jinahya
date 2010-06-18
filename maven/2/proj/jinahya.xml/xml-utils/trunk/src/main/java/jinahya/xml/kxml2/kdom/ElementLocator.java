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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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


    private static String key(final Element element) {
        if (element == null) {
            throw new IllegalArgumentException(
                "param(0:" + Element.class + ") is null");
        }

        return key(element.getNamespace(), element.getName());
    }


    private static String key(final String namespace, final String name) {
        if (namespace == null) {
            throw new IllegalArgumentException(
                "param(0:" + String.class + ") is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param(1:" + String.class + ") is null");
        }

        return "{" + namespace + "}" + name;
    }


    /**
     *
     * @param element
     */
    public ElementLocator(final Document document) {
        super();

        if ((element = document.getRootElement()) == null) {
            throw new IllegalArgumentException("no root element");
        }

        qNameChildrenMap = new HashMap<String, Map<Element, Integer>>();
    }


    /**
     *
     * @param namespace
     * @param name
     * @param index
     */
    public ElementLocator locateChild(final String name, final int index) {

        if (name == null) {
            throw new IllegalArgumentException(
                "param(0:name:" + String.class + ") is null");
        }

        if (index < 0) {
            throw new IllegalArgumentException(
                "param(1:index(" + index + ")) < 0");
        }

        return locateChild(XmlPullParser.NO_NAMESPACE, name, index);
    }


    /**
     *
     * @param namespace
     * @param name
     * @param index
     */
    public ElementLocator locateChild(final String namespace, final String name,
                                      final int index) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param(0:namespace:" + String.class + ") is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param(1:name:" + String.class + ") is null");
        }

        if (index < 0) {
            throw new IllegalArgumentException(
                "param(2:index(" + index + ")) < 0");
        }

        setElement(getChildrenArray(namespace, name)[index]);

        return this;
    }


    /**
     * Identical to <code>addAndLocateChild(
     * {@link org.xmlpull.v1.XmlPullParser#NO_NAMESPACE},
     * java.lang.String)</code>.
     *
     * @param name child element's name
     * @return self whilc is located to the newly created child element.
     * @see #addAndLocateChild(java.lang.String, java.lang.String)
     */
    public ElementLocator addAndLocateChild(final String name) {

        if (name == null) {
            throw new IllegalArgumentException(
                "param(0:" + String.class + " is null");
        }

        return addAndLocateChild(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public ElementLocator addAndLocateChild(final String namespace,
                                            final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException("'namespace' is null");
        }

        if (name == null) {
            throw new IllegalArgumentException("'name' is null");
        }

        element.addChild(Node.ELEMENT, element.createElement(namespace, name));
        setElement(element.getElement(element.getChildCount() - 1));

        return this;
    }


    /**
     *
     * @return
     */
    public boolean atRoot() {
        final Node parent = element.getParent();
        return !(parent instanceof Element);
    }


    /**
     *
     * @throws XmlPullParserException
     */
    public ElementLocator locateRoot() throws XmlPullParserException {
        while (!atRoot()) {
            locateParent();
        }

        return this;
    }


    /**
     *
     * @throws XmlPullParserException
     */
    public ElementLocator locateParent() throws XmlPullParserException {

        final Node parent = element.getParent();

        if (!(parent instanceof Element)) {
            throw new XmlPullParserException("no parent element");
        }

        return setElement((Element) parent);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public int getChildCount(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException("'namespace' is null");
        }

        if (name == null) {
            throw new IllegalArgumentException("'name' is null");
        }

        return getChildrenMap(namespace, name).size();
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

        locateChild(namespace, name, index);
        String text = getText();
        locateParent();
        return text;
    }


    /**
     *
     * @return
     */
    public String getNamespace() {
        return element.getNamespace();
    }


    /**
     *
     * @return
     */
    public String getName() {
        return element.getName();
    }


    /**
     * 
     * @param namespace
     * @param name
     * @throws XmlPullParserException
     */
    public void require(final String namespace, final String name)
        throws XmlPullParserException {

        if (namespace == null) {
            throw new IllegalArgumentException("parameter(namespace) is null");
        }

        if (name == null) {
            throw new IllegalArgumentException("parameter(name) is null");
        }

        if (!namespace.equals(element.getNamespace())
            || !name.equals(element.getName())) {

            throw new XmlPullParserException(
                "{" + element.getNamespace() + "}" + element.getName()
                + " <> {" + namespace + "}" + name);
        }
    }


    /**
     *
     * @param namespace
     * @param name
     * @param defaultValue
     * @return
     * @see org.kxml2.kdom.Element#getAttributeValue(
     *      java.lang.String, java.lang.String)
     */
    public String getAttribute(final String namespace, final String name,
                               final String defaultValue) {

        final String value = element.getAttributeValue(namespace, name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }


    /**
     *
     * @param namespace
     * @param name
     * @param value
     * @see org.kxml2.kdom.Element#setAttribute(
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public void setAttribute(final String namespace, final String name,
                             final String value) {

        element.setAttribute(namespace, name, value);
    }


    /**
     *
     * @return
     */
    public String getText() {
        final StringBuffer buffer = new StringBuffer();

        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (element.isText(i)) {
                buffer.append(element.getText(i));
            }
        }

        return buffer.toString();
    }


    /**
     *
     * @param text
     */
    public void setText(final String text) {
        element.clear();
        if (text != null) {
            element.addChild(Node.TEXT, text);
        }
    }


    /**
     *
     * @param element
     */
    private ElementLocator setElement(final Element element) {

        if (element == null) {
            throw new IllegalArgumentException("'element' is null");
        }

        this.element = element;
        qNameChildrenMap.clear();

        return this;
    }


    private Element[] getChildrenArray(final String namespace,
                                       final String name) {

        final Map<Element, Integer> childrenMap =
            getChildrenMap(namespace, name);

        final Element[] childrenArray = new Element[childrenMap.size()];
        childrenMap.keySet().toArray(childrenArray);
        return childrenArray;
    }


    private Map<Element, Integer> getChildrenMap(final String namespace,
                                                 final String name) {

        final String key = "{" + namespace + "}" + name;

        Map<Element, Integer> children = qNameChildrenMap.get(key);

        if (children == null) {
            children = new LinkedHashMap<Element, Integer>();
            final int childCount = element.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (Node.ELEMENT != element.getType(i)) {
                    continue;
                }
                final Element child = element.getElement(i);
                if (!namespace.equals(child.getNamespace())
                    || !name.equals(child.getName())) {
                    continue;
                }
                children.put(child, i);
            }
            qNameChildrenMap.put(key, children);
        }

        return children;
    }


    /**
     * Removes current element and locate to the parent.
     *
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator removeAndLocateParent()
        throws XmlPullParserException {

        if (atRoot()) {
            throw new XmlPullParserException("root element can't be removed");
        }

        final Element toBeRemoved = element;
        final String key = key(toBeRemoved);

        locateParent();

        final Map<Element, Integer> childrenMap =
            getChildrenMap(toBeRemoved.getNamespace(), toBeRemoved.getName());

        element.removeChild(childrenMap.get(toBeRemoved));
        childrenMap.remove(toBeRemoved);

        return this;
    }


    private Element element;

    private Map<String, Map<Element, Integer>> qNameChildrenMap;
}
