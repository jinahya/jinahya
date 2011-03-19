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
import org.w3c.dom.NodeList;


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
    public int count(final String name, final String space) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        final Element current = current();
        final NodeList children;
        if (space == null) {
            children = current.getElementsByTagName(name);
        } else {
            children = current.getElementsByTagNameNS(space, name);
        }
        return children.getLength();
    }


    @Override
    protected Element locate(final int index, final String name,
                             final String space) {

        final Element current = current();
        final NodeList children;
        if (space == null) {
            children = current.getElementsByTagName(name);
        } else {
            children = current.getElementsByTagNameNS(space, name);
        }
        final Node child = children.item(index);
        if (child == null) {
            throw new IndexOutOfBoundsException(
                "no {" + space + "}" + name + " at " + index);
        }
        return (Element) child;
    }


    @Override
    public String text() {

        return current().getTextContent();
    }


    @Override
    public String attribute(final String name) {

        return attribute(name, XMLConstants.NULL_NS_URI);
    }


    @Override
    public String attribute(final String name, final String space) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (space == null) {
            return attribute(name, XMLConstants.NULL_NS_URI);
        }

        return current().getAttributeNS(space, name);
    }


    private Document document;
}
