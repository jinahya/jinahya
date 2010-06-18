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
import java.util.LinkedList;
import java.util.Random;
import java.util.List;

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


    //@BeforeClass
    @BeforeSuite
    public void setup() {
        final Document document = new Document();
        final Element element = document.createElement(null, "root");
        document.addChild(Node.ELEMENT, element);
        instance = new ElementLocator(document);
    }


    @BeforeTest
    public void removeAllChildrenFromTheRootElement()
        throws XmlPullParserException {

        while (!instance.atRoot()) {
            instance.locateParent();
        }
    }


    @Test
    public void testGetChildCount() throws XmlPullParserException {

        assertEquals(instance.getChildCount(NAMESPACE, NAME), 0);

        final int expected = RANDOM.nextInt(MAXIMUM_CHILD_COUNT);
        for (int i = 0; i < expected; i++) {
            instance.addAndLocateChild(NAMESPACE, NAME).locateParent();
        }
        assertEquals(instance.getChildCount(NAMESPACE, NAME), expected);
    }



    public void testLocateChild() throws XmlPullParserException {

        final int count = RANDOM.nextInt(MAXIMUM_CHILD_COUNT);

        final String[] texts = new String[count];

        for (int i = 0; i < texts.length; i++) {
            instance.addAndLocateChild(NAMESPACE, NAME);
            instance.setText(texts[i] = Integer.toString(i));
            instance.locateParent();
        }

        /*
        XmlSerializer s = new KXmlSerializer();
        try {
            s.setOutput(System.out, null);
            ((Document) rootElement.getRoot()).write(s);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
         */

        for (int i = 0; i < texts.length; i++) {
            instance.locateChild(NAMESPACE, NAME, i);
            assertEquals(instance.getText(), texts[i]);
            instance.locateParent();
        }
    }


    @Test
    public void testGetText() {
        assertNull(instance.getText());
    }


    @Test
    public void testSetText() {
        String text = null;
        instance.setText(text);

        text = "text";
        instance.setText(text);
        assertEquals(instance.getText(), text);
    }


    private ElementLocator instance;
}
