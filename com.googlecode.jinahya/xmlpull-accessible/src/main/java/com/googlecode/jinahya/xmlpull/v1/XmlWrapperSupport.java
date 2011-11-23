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
 * @param <A> tag type parameter
 */
public class XmlWrapperSupport<A extends XmlAccessible>
    extends XmlTagSupport<XmlCollectable<A>> {


    /**
     * Creates a new instance.
     *
     * @param supported the object to be supported
     */
    public XmlWrapperSupport(final String namespaceURI, final String localName,
                             final XmlCollectable<A> supported) {

        super(namespaceURI, localName, supported);
    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, namespaceURI, localName);

        final Class<A> tagType = supported.getAccessibleType();
        final Collection<A> tags = supported.getAccessibles();

        tags.clear();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            try {
                final A tag = tagType.newInstance();
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

        for (A tag : supported.getAccessibles()) {
            tag.serialize(serializer);
        }

        serializer.endTag(namespaceURI, localName);
    }


}

