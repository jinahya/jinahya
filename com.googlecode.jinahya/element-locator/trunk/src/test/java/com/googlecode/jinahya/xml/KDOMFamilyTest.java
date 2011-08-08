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


import java.io.InputStream;
import java.io.OutputStream;

import org.kxml2.kdom.Document;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KDOMFamilyTest extends FamilyTest<Document> {


    private static final XmlPullParserFactory FACTORY;


    static {
        try {
            FACTORY = XmlPullParserFactory.newInstance();
            FACTORY.setNamespaceAware(true);
        } catch (XmlPullParserException xppe) {
            throw new InstantiationError(xppe.getMessage());
        }
    }


    @Override
    protected Document parseDocument(final InputStream source,
                                     final String charsetName)
        throws Exception {

        final XmlPullParser parser = FACTORY.newPullParser();
        parser.setInput(source, charsetName);

        final Document document = new Document();
        document.parse(parser);

        return document;
    }


    @Override
    protected ElementLocator parseLocator(final Document document)
        throws Exception {

        return KDOMElementLocator.parse(document);
    }


    @Override
    protected void printLocator(final ElementLocator locator,
                                final Document document)
        throws Exception {

        KDOMElementLocator.print(locator, document);
    }


    @Override
    protected void printDocument(final Document document,
                                 final OutputStream target,
                                 final String charsetName)
        throws Exception {

        final XmlSerializer serializer = FACTORY.newSerializer();
        serializer.setOutput(target, charsetName);

        document.write(serializer);
        serializer.flush();
    }
}

