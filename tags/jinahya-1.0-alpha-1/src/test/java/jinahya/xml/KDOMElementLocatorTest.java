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


package jinahya.xml;


import java.io.IOException;
import java.io.StringReader;

import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KDOMElementLocatorTest
    extends ElementLocatorTest<KDOMElementLocator> {


    @Test
    public void testReadingForSimpleXML()
        throws XmlPullParserException, IOException {

        final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        final XmlPullParser parser = factory.newPullParser();

        parser.setInput(new StringReader(SIMPLE_XML));

        final Document document = new Document();

        document.parse(parser);

        testReadingForSimpleXML(
            new KDOMElementLocator(document.getRootElement()));
    }


    @Test
    public void testWritingForSimpleXML()
        throws XmlPullParserException, IOException {

        final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        final XmlPullParser parser = factory.newPullParser();

        parser.setInput(new StringReader(SIMPLE_XML));

        final Document document = new Document();
        final Element root = document.createElement(
            XmlPullParser.NO_NAMESPACE, ROOT_ELEMENT_NAME);
        document.addChild(Node.ELEMENT, root);

        testWritingForSimpleXML(
            new KDOMElementLocator(document.getRootElement()));

        final XmlSerializer serializer = factory.newSerializer();
        serializer.setOutput(System.out, "UTF-8");

        document.write(serializer);
    }
}
