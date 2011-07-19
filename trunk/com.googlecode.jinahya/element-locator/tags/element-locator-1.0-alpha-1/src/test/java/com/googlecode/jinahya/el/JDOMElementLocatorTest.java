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


package com.googlecode.jinahya.el;


import com.googlecode.jinahya.el.ElementLocator;
import com.googlecode.jinahya.el.ELElement;
import com.googlecode.jinahya.el.JDOMElementLocator;
import java.io.InputStream;
import java.io.OutputStream;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class JDOMElementLocatorTest
    extends ElementLocatorTest<JDOMElementLocator, Document> {


    @Override
    protected ElementLocator<Document> parseLocator(final InputStream in)
        throws Exception {

        final SAXBuilder builder = new SAXBuilder();
        final Document document = builder.build(in);
        return JDOMElementLocator.newInstance(document);
    }


    @Override
    protected ElementLocator<Document> createLocator(final String namespaceURI,
                                                     final String localName)
        throws Exception {

        return new JDOMElementLocator(new ELElement(namespaceURI, localName));
    }


    @Override
    protected Document createDocument() throws Exception {

        return new Document();
    }


    @Override
    protected void printDocument(final Document document,
                                 final OutputStream out)
        throws Exception {

        final XMLOutputter outputter = new XMLOutputter();
        outputter.output(document, out);
    }
}

