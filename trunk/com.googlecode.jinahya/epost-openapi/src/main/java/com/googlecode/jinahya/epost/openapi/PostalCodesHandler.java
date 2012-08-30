/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.epost.openapi;


import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PostalCodesHandler extends DefaultHandler {


    public PostalCodesHandler(final Map<String, String> results) {
        super();

        if (results == null) {
            throw new IllegalArgumentException("null results");
        }

        this.results = results;
    }


    @Override
    public void characters(final char[] ch, final int start, final int length)
        throws SAXException {

        builder.append(ch, start, length);
    }


    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attributes)
        throws SAXException {

        if ("address".equals(qName) || "postcd".equals(qName)) {
            builder.delete(0, builder.length());
        }
    }


    @Override
    public void endElement(final String uri, final String localName,
                           final String qName)
        throws SAXException {

        if ("address".equals(qName)) {
            address = builder.toString();
        } else if ("postcd".equals(qName)) {
            postcd = builder.toString();
        } else if ("item".equals(qName)) {
            results.put(address, postcd);
        }
    }


    private final Map<String, String> results;


    private final transient StringBuilder builder = new StringBuilder();


    private transient String address;


    private transient String postcd;


}

