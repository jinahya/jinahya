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

import javax.xml.bind.annotation.XmlTransient;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;


/**
 * Abstract element wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> AbstractXmlElementWrapper type parameter
 */
@XmlTransient
public abstract class AbstractXmlWrapper<T extends XmlTag>
    extends AbstractXmlTag implements XmlWrapper<T> {


    /**
     * Creates a new instance.
     *
     * @param tagType accessible type
     * @param namespace XML namespace URI
     * @param name XML local name
     */
    public AbstractXmlWrapper(final Class<T> tagType,
                                 final String namespace,
                                 final String name) {

        super(namespace, name);

        if (tagType == null) {
            throw new NullPointerException("null tagType");
        }

        if (!AbstractXmlTag.class.isAssignableFrom(tagType)) {
            throw new IllegalArgumentException(
                tagType + " is not assignable to " + AbstractXmlTag.class);
        }

        this.tagType = tagType;
    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        new XmlWrapperSupport(this).parse(parser);
    }


    @Override
    public void serialize(final XmlSerializer serializer) throws IOException {

        new XmlWrapperSupport(this).serialize(serializer);
    }


    @Override
    public final Class<T> getTagType() {
        return getAccessibleType();
    }


    @Override
    public final Collection<T> getTags() {
        return getAccessibles();
    }


    @Override
    public final Class<T> getAccessibleType() {
        return tagType;
    }


    @Override
    public final Collection<T> getAccessibles() {

        if (tags == null) {
            return new ArrayList<T>();
        }

        return tags;
    }


    /**
     * tagType.
     */
    protected final Class<T> tagType;


    /**
     * tags.
     */
    private Collection<T> tags;


}

