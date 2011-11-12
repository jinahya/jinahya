/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.xmlpull.v1;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <E> AbstractXmlElementWrapper type parameter
 */
public abstract class AbstractXmlElementWrapper<E extends AbstractXmlElement>
    extends AbstractXmlElement implements XmlCollectable<E> {


    /**
     * Creates a new instance.
     *
     * @param elementType accessible type
     * @param namespaceURI XML namespace URI
     * @param localName XML local name
     */
    public AbstractXmlElementWrapper(final Class<E> elementType,
                                     final String namespaceURI,
                                     final String localName) {

        super(namespaceURI, localName);

        this.elementType = elementType;
    }


    @Override
    protected void parseContents(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            final E element = newElement();
            element.parse(parser);
            getElements().add(element);
        }
    }


    @Override
    protected void serializeContents(final XmlSerializer serializer)
        throws IOException {

        for (final Iterator<E> i = getElements().iterator(); i.hasNext();) {
            i.next().serialize(serializer);
        }
    }


    /**
     * Creates a new instance of <code>elementType</code>.
     *
     * @return a new instance of <code>elementType</code>
     */
    protected E newElement() {
        try {
            return elementType.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create a new instance of " + elementType, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create a new instance of " + elementType, iae);
        }
    }


    /**
     * Returns elements.
     *
     * @return elements.
     */
    public Collection<E> getElements() {

        if (elements == null) {
            elements = new ArrayList<E>();
        }

        return elements;
    }


    @Override
    public Collection<E> getAccessibles() {
        return getElements();

    }


    public final Class<E> getElementType() {
        return elementType;
    }


    @Override
    public final Class<E> getAccessibleType() {
        return getElementType();
    }


    /** elementType. */
    protected final Class<E> elementType;


    /** elements. */
    private Collection<E> elements;


}

