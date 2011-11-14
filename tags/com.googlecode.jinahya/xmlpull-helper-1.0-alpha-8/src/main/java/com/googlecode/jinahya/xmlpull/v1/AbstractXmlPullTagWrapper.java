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
 * Abstract element wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <E> AbstractXmlElementWrapper type parameter
 */
public abstract class AbstractXmlPullTagWrapper<E extends AbstractXmlPullTag>
    extends AbstractXmlPullTag implements XmlPullCollectable<E> {


    /**
     * Creates a new instance.
     *
     * @param tagType accessible type
     * @param namespace XML namespace URI
     * @param name XML local name
     */
    public AbstractXmlPullTagWrapper(final Class<E> tagType,
                                     final String namespace,
                                     final String name) {

        super(namespace, name);

        if (tagType == null) {
            throw new NullPointerException("null tagType");
        }

        if (!AbstractXmlPullTag.class.isAssignableFrom(tagType)) {
            throw new IllegalArgumentException(
                tagType + " is not assignable to " + AbstractXmlPullTag.class);
        }

        this.tagType = tagType;
    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        requireStartTag(parser);

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            final E tag = newTag();
            tag.parse(parser);
            getAccessibles().add(tag);
        }

        requireEndTag(parser);
    }


    @Override
    public void serialize(final XmlSerializer serializer) throws IOException {

        startTag(serializer);

        for (final Iterator<E> i = getAccessibles().iterator(); i.hasNext();) {
            i.next().serialize(serializer);
        }

        endTag(serializer);
    }


    @Override
    public final Class<E> getAccessibleType() {
        return tagType;
    }


    @Override
    public final Collection<E> getAccessibles() {

        if (tags == null) {
            return new ArrayList<E>();
        }

        return tags;
    }


    /**
     * Creates a new instance of <code>tagType</code>.
     *
     * @return a new tag.
     */
    protected E newTag() {

        try {
            return tagType.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create a new instance of " + tagType, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create a new instance of " + tagType, iae);
        }
    }


    /**
     * Tag type.
     */
    protected final Class<E> tagType;


    /**
     * Tags.
     */
    private Collection<E> tags;


}

