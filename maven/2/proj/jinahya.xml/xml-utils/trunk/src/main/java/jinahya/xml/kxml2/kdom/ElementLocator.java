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

import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ElementLocator {


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
    public void locateChild(final String namespace, final String name,
                            final int index) {

        if (namespace == null) {
            throw new IllegalArgumentException("'namespace' is null");
        }

        if (name == null) {
            throw new IllegalArgumentException("'name' is null");
        }

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        setElement(getChildrenArray(namespace, name)[index]);
    }


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
    public void locateRoot() throws XmlPullParserException {
        while (!atRoot()) {
            locateParent();
        }
    }


    /**
     *
     * @throws XmlPullParserException
     */
    public void locateParent() throws XmlPullParserException {

        final Node parent = element.getParent();

        if (!(parent instanceof Element)) {
            throw new XmlPullParserException("no parent element");
        }

        setElement((Element) parent);
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
        String text = null;

        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (element.isText(i)) {
                text = element.getText(i);
                break;
            }
        }

        setText(text);
        return text;
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
    private void setElement(final Element element) {

        if (element == null) {
            throw new IllegalArgumentException("'element' is null");
        }

        this.element = element;
        qNameChildrenMap.clear();
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


    private Element element;

    private Map<String, Map<Element, Integer>> qNameChildrenMap;
}
