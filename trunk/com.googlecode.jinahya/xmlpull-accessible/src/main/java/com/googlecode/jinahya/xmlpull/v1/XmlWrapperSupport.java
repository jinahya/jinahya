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

import java.util.Collection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> tag type parameter
 */
public class XmlWrapperSupport<T extends XmlTag>
    extends AbstractXmlCollectable<T> implements XmlWrapper<T> {


    /**
     * Creates a new instance.
     *
     * @param namespaceURI XML namespace URI
     * @param localName XML local name
     * @param supported the object to be supported
     */
    public XmlWrapperSupport(final String namespaceURI, final String localName,
                             final XmlCollectable<T> supported) {

        super(supported.getAccessibleType());

        this.namespaceURI = namespaceURI;
        this.localName = localName;
        this.supported = supported;
    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, namespaceURI, localName);

        final Class<T> tagType = supported.getAccessibleType();
        final Collection<T> tags = supported.getAccessibles();

        tags.clear();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            try {
                final T tag = tagType.newInstance();
                tag.parse(parser);
                tags.add(tag);
            } catch (InstantiationException ie) {
                throw new RuntimeException(
                    "failed to create a new instance of " + tagType, ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(
                    "failed to create a new instance of " + tagType, iae);
            }
        }

        parser.require(XmlPullParser.END_TAG, namespaceURI, localName);
    }


    @Override
    public void serialize(final XmlSerializer serializer) throws IOException {

        serializer.startTag(namespaceURI, localName);

        for (T tag : supported.getAccessibles()) {
            tag.serialize(serializer);
        }

        serializer.endTag(namespaceURI, localName);
    }


    @Override
    public String getLocalName() {
        return localName;
    }


    @Override
    public String getNamespaceURI() {
        return namespaceURI;
    }


    /**
     * XML namespace URI.
     */
    protected final String namespaceURI;


    /**
     * XML local name.
     */
    protected final String localName;


    /**
     * supported.
     */
    protected final XmlCollectable<T> supported;


}

