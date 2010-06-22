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

        element = document.getRootElement(); // RuntimeException

        qNameChildrenMap = new HashMap<String, Map<Element, Integer>>();
    }


    /**
     *
     * @param name
     * @param index
     */
    public ElementLocator child(final String name, final int index) {
        return child(XmlPullParser.NO_NAMESPACE, name, index);
    }


    /**
     *
     * @param namespace
     * @param name
     * @param index
     */
    public ElementLocator child(final String namespace, final String name,
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

        setElement((Element) getChildrenMap(namespace, name).
            keySet().toArray()[index]);

        return this;
    }


    /**
     * Add and locate a new child element with
     * {@link org.xmlpull.v1.XmlPullParser#NO_NAMESPACE} as namespace.
     *
     * @param name new child element's name
     * @return self
     * @see #child(java.lang.String, java.lang.String)
     * @see org.xmlpull.v1.XmlPullParser#NO_NAMESPACE
     */
    public ElementLocator child(final String name) {
        return child(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * Add and locate a new child element.
     *
     * @param namespace new child element's namespace
     * @param name new child element's name
     * @return self
     */
    public ElementLocator child(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param(0:namespace:" + String.class + ") is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param(1:name:" + String.class + ") is null");
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
    public ElementLocator root() throws XmlPullParserException {
        while (!atRoot()) {
            parent();
        }

        return this;
    }


    /**
     *
     * @throws XmlPullParserException
     */
    public ElementLocator parent() throws XmlPullParserException {

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
    public int count(final String namespace, final String name) {

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
    public String getText(final String name, int index)
        throws XmlPullParserException {

        return getText(XmlPullParser.NO_NAMESPACE, name, index);
    }


    /**
     *
     * @param namespace
     * @param name
     * @param index
     * @return
     * @throws XmlPullParserException
     */
    public String getText(final String namespace, final String name,
                          final int index)
        throws XmlPullParserException {

        child(namespace, name, index);
        String text = getText();
        parent();
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
        return element.getAttributeValue(namespace, name);
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

        Map<Element, Integer> childrenMap = qNameChildrenMap.get(key);

        if (childrenMap == null) {
            childrenMap = new LinkedHashMap<Element, Integer>();
            final int childCount = element.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (Node.ELEMENT != element.getType(i)) {
                    continue;
                }
                final Element child = element.getElement(i);
                if (namespace.equals(child.getNamespace())
                    && name.equals(child.getName())) {
                    childrenMap.put(child, i);
                }
            }
            qNameChildrenMap.put(key, childrenMap);
        }

        return childrenMap;
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

        final Element toBeRemoved = element;
        final String key = key(toBeRemoved);

        parent();

        final Map<Element, Integer> childrenMap =
            getChildrenMap(toBeRemoved.getNamespace(), toBeRemoved.getName());

        element.removeChild(childrenMap.get(toBeRemoved));
        childrenMap.remove(toBeRemoved);

        return this;
    }


    private Element element;

    private Map<String, Map<Element, Integer>> qNameChildrenMap;
}
