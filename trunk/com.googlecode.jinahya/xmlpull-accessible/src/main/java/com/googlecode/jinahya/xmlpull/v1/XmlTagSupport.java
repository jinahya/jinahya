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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <A> accessible type parameter
 */
public class XmlTagSupport<A extends XmlAccessible> implements XmlTag {


    /**
     * Creates a new instance.
     *
     * @param supported the object to be supported
     */
    public XmlTagSupport(final String namespaceURI, final String localName,
                         final A supported) {
        super();

        if (supported == null) {
            throw new NullPointerException("null supported");
        }

        this.namespaceURI = namespaceURI;
        this.localName = localName;

        this.supported = supported;
    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, namespaceURI, localName);

        supported.parse(parser);

        parser.require(XmlPullParser.END_TAG, namespaceURI, localName);
    }


    @Override
    public void serialize(final XmlSerializer serializer) throws IOException {

        serializer.startTag(namespaceURI, localName);

        supported.serialize(serializer);

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
     * wrapper.
     */
    protected final A supported;


}
