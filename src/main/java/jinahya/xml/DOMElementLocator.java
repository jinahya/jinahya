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


package jinahya.xml;


import javax.xml.XMLConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DOMElementLocator extends ElementLocator<Element> {


    /**
     * Creates a new instance.
     *
     * @param root root element
     */
    public DOMElementLocator(final Element root) {
        super(root);

        for (Node parent = null; (parent = root.getParentNode()) != null;) {
            if (Node.DOCUMENT_NODE == parent.getNodeType()) {
                document = (Document) parent;
                break;
            }
        }

        if (document == null) {
            throw new IllegalArgumentException(
                "can't locate document from the root");
        }
    }


    @Override
    public int getCount(final String name) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        return getCurrent().getElementsByTagName(name).getLength();
    }


    @Override
    public int getCountNS(final String space, final String name) {

        if (space == null) {
            throw new IllegalArgumentException("null space");
        }

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        return getCurrent().getElementsByTagNameNS(space, name).getLength();
    }


    @Override
    protected Element findChild(final String name, final int index) {

        return (Element) getCurrent().getElementsByTagName(name).item(index);
    }


    @Override
    protected Element addChild(final String name) {

        return (Element) getCurrent().appendChild(
            document.createElement(name));
    }


    @Override
    protected Element findChildNS(final String space, final String name,
                                  final int index) {

        return (Element) getCurrent().
            getElementsByTagNameNS(space, name).item(index);
    }


    @Override
    protected Element addChildNS(final String space, final String name) {
        return (Element) getCurrent().appendChild(
            document.createElementNS(space, name));
    }


    @Override
    public String getText() {

        return getCurrent().getTextContent();
    }


    @Override
    public ElementLocator<Element> setText(final String text) {

        getCurrent().appendChild(document.createTextNode(text));

        return this;
    }


    @Override
    public String getAttribute(final String name) {

        return getAttributeNS(XMLConstants.NULL_NS_URI, name);
    }


    @Override
    public ElementLocator<Element> setAttribute(final String name,
                                                final String value) {

        getCurrent().setAttribute(name, value);

        return this;
    }


    @Override
    public String getAttributeNS(final String space, final String name) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (space == null) {
            return getAttributeNS(XMLConstants.NULL_NS_URI, name);
        }

        return getCurrent().getAttributeNS(space, name);
    }


    @Override
    public ElementLocator<Element> setAttributeNS(final String space,
                                                  final String name,
                                                  final String value) {

        if (space == null) {
            throw new IllegalArgumentException("null space");
        }

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        getCurrent().setAttributeNS(space, name, value);

        return this;
    }


    /** located document. */
    private Document document;
}
