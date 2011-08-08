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
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DOM4JFamilyTest extends FamilyTest<Document> {


    @Override
    protected Document parseDocument(final InputStream source,
                                     final String charsetName)
        throws Exception {

        final SAXReader reader = new SAXReader();
        reader.setEncoding(charsetName);

        return reader.read(source);
    }


    @Override
    protected ElementLocator parseLocator(final Document document)
        throws Exception {

        return DOM4JElementLocator.parse(document);
    }


    @Override
    protected void printLocator(final ElementLocator locator,
                                final Document document)
        throws Exception {

        DOM4JElementLocator.print(locator, document);
    }


    @Override
    protected void printDocument(final Document document,
                                 final OutputStream target,
                                 final String charsetName)
        throws IOException {

        final Writer writer = new OutputStreamWriter(target, charsetName);
        document.write(writer);
        writer.flush();
    }
}

