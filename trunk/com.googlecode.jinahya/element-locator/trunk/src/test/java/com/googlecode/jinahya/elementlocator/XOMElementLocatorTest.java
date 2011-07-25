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


package com.googlecode.jinahya.elementlocator;


import java.io.InputStream;
import java.io.OutputStream;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XOMElementLocatorTest
    extends ElementLocatorTest<XOMElementLocator, Document> {


    @Override
    protected ElementLocator<Document> parseLocator(final InputStream in)
        throws Exception {

        final Builder builder = new Builder();
        final Document document = builder.build(in);
        return XOMElementLocator.parse(document);
    }


    @Override
    protected ElementLocator<Document> createLocator(final String namespaceURI,
                                                     final String localName)
        throws Exception {

        return XOMElementLocator.newInstance(namespaceURI, localName);
    }


    @Override
    protected Document createDocument() throws Exception {

        final Element element = new Element("tmp:tmp", "http://tmp");

        final Document document = new Document(element);

        return document;
    }


    @Override
    protected void printDocument(final Document document,
                                 final OutputStream out,
                                 final String charsetName)
        throws Exception {

        final Serializer serializer = new Serializer(out, charsetName);
        serializer.write(document);
    }
}

