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


import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XOMElementLocator extends ElementLocator<Element> {


    /**
     * Creates a new instance.
     *
     * @param document document
     */
    public XOMElementLocator(final Document document) {
        this(document.getRootElement());
    }


    /**
     * Creates a new instance.
     *
     * @param element element
     */
    public XOMElementLocator(final Element element) {
        super(element);
    }


    @Override
    public int getCount(final String name) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        return getCountNS("", name);
    }


    @Override
    public int getCountNS(final String space, final String name) {

        if (space == null) {
            throw new IllegalArgumentException("null space");
        }

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        return getCurrent().getChildElements(name, space).size();
    }


    @Override
    protected Element findChild(final String name, final int index) {

        return findChildNS("", name, index);
    }


    @Override
    protected Element addChild(final String name) {
        return addChildNS("", name);
    }


    @Override
    protected Element findChildNS(final String space, final String name,
                                  final int index) {

        if (space == null) {
            throw new IllegalArgumentException("null space");
        }

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        try {
            return getCurrent().getChildElements(name, space).get(index);
        } catch (IndexOutOfBoundsException ioobe) {
            return null;
        }
    }


    @Override
    protected Element addChildNS(final String space, final String name) {

        final Element child = new Element(name, space);

        getCurrent().appendChild(child); // void

        return child;
    }


    @Override
    public String getText() {

        return getCurrent().getValue();
    }


    @Override
    public ElementLocator<Element> setText(final String text) {

        getCurrent().appendChild(text);

        return this;
    }


    @Override
    public String getAttribute(final String name) {

        return getAttributeNS("", name);
    }


    @Override
    public String getAttributeNS(final String space, final String name) {

        if (space == null) {
            throw new IllegalArgumentException("null space");
        }

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        return getCurrent().getAttributeValue(name, space);
    }


    @Override
    public ElementLocator<Element> setAttribute(final String name,
                                                final String value) {

        return setAttributeNS("", name, value);
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

        final Element current = getCurrent();

        Attribute attribute = current.getAttribute(name, space);

        if (value == null) {
            if (attribute != null) {
                current.removeAttribute(attribute);
            }
        } else {
            if (attribute == null) {
                attribute = new Attribute(name, space, value);
            }
            current.addAttribute(attribute);
        }

        return this;
    }
}
