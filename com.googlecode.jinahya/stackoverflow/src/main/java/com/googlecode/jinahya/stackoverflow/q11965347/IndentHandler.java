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


package com.googlecode.jinahya.stackoverflow.q11965347;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IndentHandler extends DefaultHandler {


    private static final Logger LOGGER =
        Logger.getLogger(IndentHandler.class.getName());


    @Override
    public InputSource resolveEntity(final String publicId,
                                     final String systemId)
        throws IOException, SAXException {


        LOGGER.log(Level.WARNING, "resolveEntity({0}, {1})",
                   new Object[]{publicId, systemId});

        return super.resolveEntity(publicId, systemId);
    }


    @Override
    public void notationDecl(final String name, final String publicId,
                             final String systemId)
        throws SAXException {

        LOGGER.log(Level.WARNING, "notationDecl({0}, {1}, {2})",
                   new Object[]{name, publicId, systemId});

        super.notationDecl(name, publicId, systemId);
    }


    @Override
    public void unparsedEntityDecl(final String name, final String publicId,
                                   final String systemId,
                                   final String notationName)
        throws SAXException {

        LOGGER.log(Level.WARNING, "unparsedEntityDecl({0}, {1}, {2}, {3})",
                   new Object[]{name, publicId, systemId, notationName});

        super.unparsedEntityDecl(name, publicId, systemId, notationName);
    }


    @Override
    public void setDocumentLocator(final Locator locator) {

        LOGGER.log(Level.WARNING, "setDocumentLocator({0})",
                   new Object[]{locator});

        super.setDocumentLocator(locator);
    }


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }


    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }


    @Override
    public void startPrefixMapping(final String prefix, final String uri)
        throws SAXException {

        super.startPrefixMapping(prefix, uri);

        List<String> prefixes = uriPrefixes.get(uri);
        if (prefixes == null) {
            prefixes = new ArrayList<String>();
            uriPrefixes.put(uri, prefixes);
        }

        prefixes.add(prefix);
    }


    @Override
    public void endPrefixMapping(final String prefix) throws SAXException {
        super.endPrefixMapping(prefix);

        final List<String> uris = uriPrefixes.get(prefix);
        uris.remove(uris.size() - 1);
    }


    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attributes)
        throws SAXException {

        super.startElement(uri, localName, qName, attributes);

        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }

        final List<String> prefixes = uriPrefixes.get(uri);
        final String prefix = prefixes.get(prefixes.size() - 1);

        System.out.print("<" + prefix + ":" + localName);

        if (qName != null) {
            System.out.print(qName);
        } else {
            System.out.print(prefix + ":" + localName);
        }

        final int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            final String attributeQName = attributes.getQName(i);
            final String attributeValue = attributes.getValue(qName);
            System.out.println(" " + attributeQName + "=" + attributeValue);
        }

        System.out.print(">");
    }


    @Override
    public void endElement(final String uri, final String localName,
                           final String qName)
        throws SAXException {

        super.endElement(uri, localName, qName);
    }


    @Override
    public void characters(final char[] ch, final int start, final int length)
        throws SAXException {

        super.characters(ch, start, length);

        System.out.print(new String(ch, start, length));
        characters = true;
    }


    @Override
    public void ignorableWhitespace(final char[] ch, final int start,
                                    final int length)
        throws SAXException {

        super.ignorableWhitespace(ch, start, length);
    }


    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        super.processingInstruction(target, data);
    }


    @Override
    public void skippedEntity(final String name) throws SAXException {
        super.skippedEntity(name);
    }


    @Override
    public void warning(SAXParseException e) throws SAXException {
        super.warning(e);
    }


    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }


    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        super.fatalError(e);
    }


    private Map<String, List<String>> uriPrefixes =
        new HashMap<String, List<String>>();


    private transient boolean characters;


    private int depth = 0;


}

