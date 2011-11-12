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

import java.util.Iterator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;


/**
 * Abstract element wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <E> AbstractXmlElementWrapper type parameter
 */
public abstract class AbstractXmlElementWrapper<E extends AbstractXmlElement>
    extends AbstractXmlCollectable<E> {


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

        super(elementType);

        this.namespaceURI = namespaceURI;
        this.localName = localName;
    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, namespaceURI, localName);

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            final E element = newAccessible();
            element.parse(parser);
            getAccessibles().add(element);
        }

        parser.nextTag();
        parser.require(XmlPullParser.END_TAG, namespaceURI, localName);
    }


    @Override
    public void serialize(final XmlSerializer serializer)
        throws IOException {

        serializer.startTag(namespaceURI, localName);

        for (final Iterator<E> i = getAccessibles().iterator(); i.hasNext();) {
            i.next().serialize(serializer);
        }

        serializer.endTag(namespaceURI, localName);
    }


    /**
     * XML namespace URI.
     */
    protected final String namespaceURI;


    /**
     * XML local name.
     */
    private final String localName;


}

