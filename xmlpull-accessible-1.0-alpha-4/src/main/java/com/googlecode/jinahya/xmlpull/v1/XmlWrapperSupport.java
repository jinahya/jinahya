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

import java.util.Iterator;
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


    /** GENERATED. */
    private static final long serialVersionUID = 6366421039387557728L;


    /**
     * Creates a new instance.
     *
     * @param supported the object to be supported
     * @param tagType tag type
     * @param namespaceURI XML namespace name
     * @param localName XML local name
     */
    public XmlWrapperSupport(final Object supported, final Class<T> tagType,
                             final String namespaceURI,
                             final String localName) {

        super(tagType);

        if (supported == null) {
            throw new NullPointerException("null supported");
        }

        if (localName == null) {
            throw new NullPointerException("null localName");
        }
        if (localName.trim().length() == 0) {
            throw new IllegalArgumentException("empty localName");
        }

        this.supported = supported;

        this.namespaceURI = namespaceURI;
        this.localName = localName;

    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, namespaceURI, localName);

        final Class<T> tagType = getAccessibleType();
        final Collection<T> tags = getAccessibles();

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

        final Collection<T> tags = getAccessibles();

        for (final Iterator<T> i = tags.iterator(); i.hasNext();) {
            i.next().serialize(serializer);
        }

        serializer.endTag(namespaceURI, localName);
    }


    public final Object getSupported() {
        return supported;
    }


    @Override
    public final String getNamespaceURI() {
        return namespaceURI;
    }


    @Override
    public final String getLocalName() {
        return localName;
    }


    /**
     * supported.
     */
    private final Object supported;


    /**
     * XML namespace name.
     */
    private final String namespaceURI;


    /**
     * XML local name.
     */
    private final String localName;


}

