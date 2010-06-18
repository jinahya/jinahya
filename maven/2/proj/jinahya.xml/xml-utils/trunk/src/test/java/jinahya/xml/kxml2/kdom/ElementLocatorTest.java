/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package jinahya.xml.kxml2.kdom;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;


import org.kxml2.io.KXmlSerializer;
import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ElementLocatorTest {


    private static final Random RANDOM = new Random();

    private static final int MAXIMUM_CHILD_COUNT = 1000;

    private static final String NAMESPACE = "a";

    private static final String NAME = "b";


    private static void print(final Document document) throws IOException {
        System.out.println("-------------------------------------------------");
        final XmlSerializer serializer = new KXmlSerializer();
        serializer.setOutput(System.out, "UTF-8");
        document.write(serializer);
        System.out.println();
    }


    @Test
    public void testGetChildCount() throws XmlPullParserException {
        final Document document = new Document();
        document.addChild(Node.ELEMENT, document.createElement("root", "root"));
        final ElementLocator locator = new ElementLocator(document);

        assertEquals(locator.getChildCount(NAMESPACE, NAME), 0);

        final int expected = RANDOM.nextInt(MAXIMUM_CHILD_COUNT);
        for (int i = 0; i < expected; i++) {
            locator.addAndLocateChild(NAMESPACE, NAME).locateParent();
        }
        assertEquals(locator.getChildCount(NAMESPACE, NAME), expected);
    }


    @Test
    public void testLocateChild() throws XmlPullParserException {
        final Document document = new Document();
        document.addChild(Node.ELEMENT, document.createElement("root", "root"));
        final ElementLocator locator = new ElementLocator(document);

        final int count = RANDOM.nextInt(MAXIMUM_CHILD_COUNT);

        final String[] texts = new String[count];

        for (int i = 0; i < texts.length; i++) {
            texts[i] = Integer.toString(i);
            locator.addAndLocateChild(NAMESPACE, NAME);
            locator.setText(texts[i]);
            locator.locateParent();
        }

        for (int i = 0; i < texts.length; i++) {
            locator.locateChild(NAMESPACE, NAME, i);
            assertEquals(locator.getText(), texts[i]);
            locator.locateParent();
        }
    }


    //@Test
    public void testGetText() {
        final Document document = new Document();
        document.addChild(Node.ELEMENT, document.createElement("root", "root"));
        final ElementLocator locator = new ElementLocator(document);
        assertNull(locator.getText());
    }


    @Test
    public void testSetText() {

        final Document document = new Document();
        document.addChild(Node.ELEMENT, document.createElement("root", "root"));
        final ElementLocator locator = new ElementLocator(document);

        String text = null;
        locator.setText(text);

        text = "&lt;text&gt;";
        locator.setText(text);
        assertEquals(locator.getText(), text);
    }


    @Test
    public void testRemove() throws XmlPullParserException, IOException {
        System.out.println("\ntestRemove\n");

        final Document document = new Document();
        document.addChild(
            Node.ELEMENT,
            document.createElement(XmlPullParser.NO_NAMESPACE, "root"));
        final ElementLocator locator = new ElementLocator(document);

        final int count = RANDOM.nextInt(MAXIMUM_CHILD_COUNT) + 1;

        for (int i = 0; i < count; i++) {
            locator.addAndLocateChild(XmlPullParser.NO_NAMESPACE, "child");
            locator.locateParent();
        }

        //print(document);

        for (int i = 0; i < count; i++) {
            final int childCount =
                locator.getChildCount(XmlPullParser.NO_NAMESPACE, "child");
            locator.locateChild(
                XmlPullParser.NO_NAMESPACE, "child", childCount - 1);
            locator.removeAndLocateParent();
        }

        //print(document);
    }
}
