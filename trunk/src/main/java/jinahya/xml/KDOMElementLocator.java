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


import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParser;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KDOMElementLocator extends ElementLocator<Element> {


    /**
     * Creates a new instance.
     *
     * @param root root element to be located.
     */
    public KDOMElementLocator(final Element root) {
        super(root);
    }


    @Override
    public int count(final String name, final String space) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        int count = 0;

        final Element current = current();
        final int childCount = current.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT != current.getType(i)) {
                continue;
            }
            final Element child = current().getElement(i);
            if (space != null && !space.equals(child.getNamespace())) {
                continue;
            }
            if (!name.equals(child.getName())) {
                continue;
            }
            count++;
        }

        return count;
    }


    @Override
    protected Element locate(final int index, final String name,
                             final String space) {

        int count = 0;

        final Element current = current();
        final int childCount = current.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT != current.getType(i)) {
                continue;
            }
            final Element child = current().getElement(i);
            if (space != null && !space.equals(child.getNamespace())) {
                continue;
            }
            if (!name.equals(child.getName())) {
                continue;
            }
            if (count == index) {
                return child;
            }
            count++;
        }

        throw new IndexOutOfBoundsException(
            "no {" + space + "}" + name + " at " + index);
    }


    @Override
    public String text() {

        StringBuffer buffer = null;

        final Element current = current();
        final int childCount = current.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final int type = current.getType(i);
            if (type != Node.TEXT && type != Node.CDSECT) {
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


    @Override
    public String attribute(final String name) {
        return attribute(name, XmlPullParser.NO_NAMESPACE);
    }


    /*
    @Override
    public void setAttribute(final String name, final String value) {
    setAttributeNS(XmlPullParser.NO_NAMESPACE, name, value);
    }
     */
    @Override
    public String attribute(final String name, final String space) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (space == null) {
            throw new IllegalArgumentException("null space");
        }

        final Element current = current();
        final int attributeCount = current.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (space != null
                && space.equals(current.getAttributeNamespace(i))) {
                continue;
            }
            if (name.equals(current.getAttributeName(i))) {
                return current.getAttributeValue(i);
            }
        }

        return null;
    }
    /*
    @Override
    public void setAttributeNS(final String space, final String name,
    final String value) {
    
    if (space == null) {
    throw new IllegalArgumentException("null space");
    }
    
    if (name == null) {
    throw new IllegalArgumentException("null name");
    }
    
    if (value == null) {
    throw new IllegalArgumentException("null value");
    }
    
    current().setAttribute(space, name, value);
    }
     */
}
