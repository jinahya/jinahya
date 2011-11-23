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
public class XmlWrapperSupport<T extends XmlTag> implements XmlAccessible {


    /**
     * Creates a new instance.
     *
     * @param wrapper wrapper
     */
    public XmlWrapperSupport(final XmlWrapper<T> wrapper) {
        super();

        if (wrapper == null) {
            throw new NullPointerException("null wrapper");
        }

        this.wrapper = wrapper;
    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, wrapper.getNamespaceURI(),
                       wrapper.getLocalName());

        final Class<T> tagType = wrapper.getTagType();
        final Collection<T> tags = wrapper.getTags();

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

        parser.require(XmlPullParser.END_TAG, wrapper.getNamespaceURI(),
                       wrapper.getLocalName());
    }


    @Override
    public void serialize(final XmlSerializer serializer) throws IOException {

        serializer.startTag(wrapper.getNamespaceURI(), wrapper.getLocalName());

        for (T tag : wrapper.getTags()) {
            tag.serialize(serializer);
        }

        serializer.endTag(wrapper.getNamespaceURI(), wrapper.getLocalName());
    }


    /**
     * wrapper.
     */
    private final XmlWrapper<T> wrapper;


}

