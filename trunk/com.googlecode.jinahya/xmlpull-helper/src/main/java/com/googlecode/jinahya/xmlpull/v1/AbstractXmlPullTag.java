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
 */
public abstract class AbstractXmlPullTag implements XmlPullTag {


    /**
     * Creates a new instance.
     *
     * @param namespaceURI XML namespace URI
     * @param localName XML local name
     */
    public AbstractXmlPullTag(final String namespaceURI,
                              final String localName) {

        super();

        this.namespaceURI = namespaceURI;
        this.localName = localName;
    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, namespaceURI, localName);

        parseContents(parser);

        parser.nextTag();
        parser.require(XmlPullParser.END_TAG, namespaceURI, localName);
    }


    /**
     * Parses contents from given <code>parser</code>.
     *
     * @param parser parser
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    protected abstract void parseContents(final XmlPullParser parser)
        throws XmlPullParserException, IOException;


    @Override
    public void serialize(final XmlSerializer serializer) throws IOException {

        serializer.startTag(namespaceURI, localName);

        serializeContents(serializer);

        serializer.endTag(namespaceURI, localName);
    }


    /**
     * Serializes contents to give <code>serializer</code>.
     *
     * @param serializer serializer
     * @throws IOException if an I/O error occurs.
     */
    protected abstract void serializeContents(final XmlSerializer serializer)
        throws IOException;


    @Override
    public final String getNamespaceURI() {
        return namespaceURI;
    }


    @Override
    public final String getLocalName() {
        return localName;
    }


    /**
     * XML namespace URI.
     */
    protected final String namespaceURI;


    /** XML local name. */
    private final String localName;


}

