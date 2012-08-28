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


package com.googlecode.jinahya.ucloud.storage;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SimpleHandler extends DefaultHandler {


    private static final SAXParserFactory PARSER_FACTORY =
        SAXParserFactory.newInstance();


    /**
     *
     * @param input
     * @param parentQName
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static List<Map<String, String>> parse(final InputStream input,
                                                  final String parentQName)
        throws ParserConfigurationException, SAXException, IOException {

        final List<Map<String, String>> results =
            new ArrayList<Map<String, String>>();

        final DefaultHandler handler = new SimpleHandler(parentQName, results);

        final SAXParser parser = PARSER_FACTORY.newSAXParser();

        parser.parse(input, handler);

        return results;
    }


    /**
     *
     * @param parentQName
     * @param results
     */
    public SimpleHandler(final String parentQName,
                         final List<Map<String, String>> results) {

        super();

        if (parentQName == null) {
            throw new IllegalArgumentException("null parentQName");
        }

        if (results == null) {
            throw new IllegalArgumentException("null results");
        }

        this.parentQName = parentQName;
        this.results = results;
    }


    @Override
    public void characters(final char[] ch, final int start, final int length)
        throws SAXException {

        super.characters(ch, start, length);

        if (childQName != null) {
            builder.append(ch, start, length);
        }
    }


    @Override
    public void endElement(final String uri, final String localName,
                           final String qName)
        throws SAXException {
        //super.endElement(uri, localName, qName);

        if (parentQName.equals(qName)) {
            results.add(result);
            result = null;
        } else if (childQName != null) {
            result.put(childQName, builder.toString());
            childQName = null;
        }
    }


    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attributes)
        throws SAXException {

        //super.startElement(uri, localName, qName, attributes);

        if (parentQName.equals(qName)) {
            result = new HashMap<String, String>();
        } else if (result != null) {
            // in parent
            childQName = qName;
            builder.delete(0, builder.length());
        }
    }


    private final String parentQName;


    private final List<Map<String, String>> results;


    private transient Map<String, String> result;


    private transient String childQName;


    private transient final StringBuilder builder = new StringBuilder();


}

