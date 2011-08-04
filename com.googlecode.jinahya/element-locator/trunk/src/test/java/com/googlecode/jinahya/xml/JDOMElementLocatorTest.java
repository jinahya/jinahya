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
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class JDOMElementLocatorTest extends ElementLocatorTest<Document> {


    @Override
    protected ElementLocator parseLocator(final InputStream in)
        throws Exception {

        final SAXBuilder builder = new SAXBuilder();
        final Document document = builder.build(in);
        return JDOMElementLocator.parse(document);
    }


    @Override
    protected ElementLocator createLocator(final String namespaceURI,
                                           final String localName)
        throws Exception {

        return new JDOMElementLocator(new ELElement(namespaceURI, localName));
    }


    @Override
    protected Document createDocument() throws Exception {

        return new Document();
    }


    @Override
    protected void printElement(final ELElement element,
                                final Document document)
        throws Exception {

        JDOMElementLocator.print(element, document);
    }


    @Override
    protected void printDocument(final Document document,
                                 final OutputStream out,
                                 final String charsetName)
        throws Exception {

        final XMLOutputter outputter = new XMLOutputter();
        final Writer writer = new OutputStreamWriter(out, charsetName);
        outputter.output(document, writer);
        writer.flush();
    }
}

