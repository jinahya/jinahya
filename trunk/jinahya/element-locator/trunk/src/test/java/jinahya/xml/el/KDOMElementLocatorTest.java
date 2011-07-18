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


package jinahya.xml.el;


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
public class KDOMElementLocatorTest
    extends ElementLocatorTest<KDOMElementLocator, Document> {


    private static final XmlPullParserFactory XMLPPF;


    static {
        try {
            XMLPPF = XmlPullParserFactory.newInstance();
            XMLPPF.setNamespaceAware(true);
        } catch (XmlPullParserException xppe) {
            throw new InstantiationError(xppe.getMessage());
        }
    }


    @Override
    protected ElementLocator<Document> parseLocator(final InputStream in)
        throws Exception {

        final XmlPullParser parser = XMLPPF.newPullParser();
        parser.setInput(in, null);

        final Document document = new Document();
        document.parse(parser);

        return KDOMElementLocator.newInstance(document);
    }


    @Override
    protected ElementLocator<Document> createLocator(final String namespaceURI,
                                                     final String localName)
        throws Exception {

        return new KDOMElementLocator(new ELElement(namespaceURI, localName));
    }


    @Override
    protected Document createDocument() throws Exception {
        return new Document();
    }


    @Override
    protected void printDocument(final Document document,
                                 final OutputStream out)
        throws Exception {

        final XmlSerializer serializer = XMLPPF.newSerializer();
        serializer.setOutput(out, "UTF-8");

        document.write(serializer);
    }
}

