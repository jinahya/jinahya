/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.xml;


import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.XMLConstants;

import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DOMFamilyTest extends FamilyTest<Document> {


    private static final DOMImplementationRegistry REGISTRY;


    static {
        try {
            REGISTRY = DOMImplementationRegistry.newInstance();
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        } catch (InstantiationException ie) {
            throw new InstantiationError(ie.getMessage());
        } catch (IllegalAccessException iae) {
            throw new InstantiationError(iae.getMessage());
        }
    }


    private static final DOMImplementationLS IMPLEMENTATION =
        (DOMImplementationLS) REGISTRY.getDOMImplementation("XML 3.0");


    @Override
    protected Document parseDocument(final InputStream source,
                                     final String charsetName)
        throws Exception {

        final LSParser parser = IMPLEMENTATION.createLSParser(
            DOMImplementationLS.MODE_SYNCHRONOUS,
            XMLConstants.W3C_XML_SCHEMA_NS_URI);

        final LSInput input = IMPLEMENTATION.createLSInput();
        input.setByteStream(source);
        input.setEncoding(charsetName);

        return parser.parse(input);
    }


    @Override
    protected ElementLocator parseLocator(final Document document)
        throws Exception {

        return DOMElementLocator.parse(document);
    }


    @Override
    protected void printLocator(final ElementLocator locator,
                                final Document document)
        throws Exception {

        DOMElementLocator.print(locator.getRootElement(), document);
    }


    @Override
    protected void printDocument(final Document document,
                                 final OutputStream target,
                                 final String charsetName)
        throws IOException {

        final LSSerializer serializer = IMPLEMENTATION.createLSSerializer();

        final LSOutput output = IMPLEMENTATION.createLSOutput();
        output.setByteStream(target);
        output.setEncoding(charsetName);

        serializer.write(document, output);
    }
}

