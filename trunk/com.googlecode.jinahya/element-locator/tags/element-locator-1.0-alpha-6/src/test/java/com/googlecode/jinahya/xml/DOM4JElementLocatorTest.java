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

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DOM4JElementLocatorTest extends ElementLocatorTest<Document> {


    @Override
    protected ElementLocator parseLocator(InputStream in) throws Exception {

        final SAXReader reader = new SAXReader();
        final Document document = reader.read(in);

        return DOM4JElementLocator.parse(document);
    }


    @Override
    protected ElementLocator createLocator(final String namespaceURI,
                                           final String localName)
        throws Exception {

        return new DOM4JElementLocator(new ELElement(namespaceURI, localName));
    }


    @Override
    protected Document createDocument() throws Exception {

        return DocumentHelper.createDocument();
    }


    @Override
    protected void printElement(final ELElement element,
                                final Document document)
        throws Exception {

        DOM4JElementLocator.print(element, document);
    }


    @Override
    protected void printDocument(final Document document,
                                 final OutputStream out,
                                 final String charsetName)
        throws Exception {

        final Writer writer = new OutputStreamWriter(out, charsetName);
        document.write(writer);
        writer.flush();
    }
}

