/*
 * Copyright 2011 Jin Kwon.
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


import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DOMElementLocatorTest
    extends ElementLocatorTest<DOMElementLocator, Document> {


    private static final DocumentBuilderFactory DBF =
        DocumentBuilderFactory.newInstance();


    static {
        DBF.setNamespaceAware(true);
    }


    private static final DocumentBuilder DB;


    static {
        try {
            DB = DBF.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            throw new InstantiationError(pce.getMessage());
        }
    }


    private static final DOMImplementationRegistry DOMIR;


    static {
        try {
            DOMIR = DOMImplementationRegistry.newInstance();
        } catch (Exception e) {
            throw new InstantiationError(e.getMessage());
        }
    }


    private static final DOMImplementationLS DOMILS =
        (DOMImplementationLS) DOMIR.getDOMImplementation("XML 3.0");


    @Override
    protected ElementLocator<Document> parseLocator(InputStream in)
        throws Exception {

        final Document document = DB.parse(in);

        return DOMElementLocator.parseInstance(document);
    }


    @Override
    protected ElementLocator<Document> createLocator(final String namespaceURI,
                                                     final String localName)
        throws Exception {

        return DOMElementLocator.newInstance(namespaceURI, localName);
    }


    @Override
    protected Document createDocument() throws Exception {
        return DB.newDocument();
    }


    @Override
    protected void printDocument(final Document document,
                                 final OutputStream out,
                                 final String charsetName)
        throws Exception {

        final LSSerializer serializer = DOMILS.createLSSerializer();

        final LSOutput output = DOMILS.createLSOutput();
        output.setByteStream(out);
        output.setEncoding(charsetName);

        serializer.write(document, output);
    }
}

