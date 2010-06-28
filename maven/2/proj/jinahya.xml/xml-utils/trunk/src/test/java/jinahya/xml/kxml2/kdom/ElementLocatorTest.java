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
 */

package jinahya.xml.kxml2.kdom;


import java.io.IOException;
import java.util.Random;

import org.kxml2.io.KXmlSerializer;
import org.kxml2.kdom.Document;
import org.kxml2.kdom.Node;

import org.testng.Assert;
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

    private static final String ROOT_NAMESPACE = "root";

    private static final String ROOT_NAME = "root";


    private static void print(final Document document) throws IOException {
        System.out.println("-------------------------------------------------");
        final XmlSerializer serializer = new KXmlSerializer();
        serializer.setOutput(System.out, "UTF-8");
        document.write(serializer);
        System.out.println();
    }


    private static ElementLocator newInstance() throws XmlPullParserException {
        final Document document = new Document();
        document.addChild(
            Node.ELEMENT, document.createElement(ROOT_NAMESPACE, ROOT_NAME));
        return new ElementLocator(document);
    }


    @Test
    public void testConstructor() throws XmlPullParserException {
        final ElementLocator locator = newInstance();

        Assert.assertEquals(locator.getDepth(), 0);

        Assert.assertEquals(locator.getCurrentNamespace(), ROOT_NAMESPACE);
        Assert.assertEquals(locator.getCurrentName(), ROOT_NAME);
    }


    @Test(expectedExceptions = XmlPullParserException.class)
    public void testConstructorWithEmptyDocument()
        throws XmlPullParserException {

        new ElementLocator(new Document());
    }


    @Test
    public void testAddAndLocateChild() throws XmlPullParserException {

        final ElementLocator locator = newInstance();

        Assert.assertEquals(locator.getDepth(), 0);

        locator.addAndLocateChild(NAMESPACE, NAME);
        Assert.assertEquals(locator.getCurrentNamespace(), NAMESPACE);
        Assert.assertEquals(locator.getCurrentName(), NAME);

        Assert.assertEquals(locator.getDepth(), 1);
    }


    @Test
    public void testGetChildCount() throws XmlPullParserException {

        final ElementLocator locator = newInstance();

        Assert.assertEquals(locator.getChildCount(NAMESPACE, NAME), 0);

        final int expected = RANDOM.nextInt(MAXIMUM_CHILD_COUNT);
        for (int i = 0; i < expected; i++) {
            locator.addAndLocateChild(NAMESPACE, NAME).parent();
        }
        Assert.assertEquals(locator.getChildCount(NAMESPACE, NAME), expected);
    }


    @Test
    public void testLocateChild() throws XmlPullParserException, IOException {

        final ElementLocator locator = newInstance();

        final int count = RANDOM.nextInt(MAXIMUM_CHILD_COUNT);

        final String[] texts = new String[count];

        for (int i = 0; i < texts.length; i++) {
            texts[i] = "<" + Integer.toString(i) + ">&";
            locator.addAndLocateChild(NAMESPACE, NAME).
                setText(texts[i]).locateParent();
        }

        for (int i = 0; i < texts.length; i++) {
            locator.locateChild(NAMESPACE, NAME, i);
            Assert.assertEquals(locator.getText(), texts[i]);
            locator.parent();
        }

        print(locator.getDocument());
    }


    @Test
    public void testGetAttribute() throws XmlPullParserException {
        final ElementLocator locator = newInstance();

        Assert.assertNull(locator.getAttribute(NAMESPACE, NAME));

        final String value = "value";
        locator.setAttribute(NAMESPACE, NAME, value);

        Assert.assertEquals(locator.getAttribute(NAMESPACE, NAME), value);
    }


    @Test
    public void testSetAttribute() throws XmlPullParserException {
        final ElementLocator locator = newInstance();

        Assert.assertNull(locator.getAttribute(NAMESPACE, NAME));

        final String value = "value";
        locator.setAttribute(NAMESPACE, NAME, value);

        Assert.assertEquals(locator.getAttribute(NAMESPACE, NAME), value);

        locator.setAttribute(NAMESPACE, NAME, null);

        Assert.assertNull(locator.getAttribute(NAMESPACE, NAME));
    }


    //@Test
    public void testGetText() throws XmlPullParserException {
        final ElementLocator locator = newInstance();
        Assert.assertNull(locator.getText());
    }


    @Test
    public void testSetText() throws XmlPullParserException {

        final ElementLocator locator = newInstance();

        final String text = "<text>&";
        locator.setText(text);

        Assert.assertEquals(locator.getText(), text);
    }


    @Test
    public void testSetTextWithNull() throws XmlPullParserException {

        final ElementLocator locator = newInstance();

        String text = null;
        locator.setText(text);

        Assert.assertEquals(locator.getText(), null);
    }


    @Test
    public void testRemove() throws XmlPullParserException, IOException {

        final ElementLocator locator = newInstance();

        final int expected = RANDOM.nextInt(MAXIMUM_CHILD_COUNT);

        for (int i = 0; i < expected; i++) {
            locator.addAndLocateChild(NAMESPACE, NAME).parent();
        }
        Assert.assertEquals(locator.getChildCount(NAMESPACE, NAME), expected);

        for (int i = 0; i < expected; i++) {
            final int childCount = locator.getChildCount(NAMESPACE, NAME);
            locator.locateChild(NAMESPACE, NAME, childCount - 1);
            locator.removeCurrentAndLocateParent();
        }
    }


    @Test(expectedExceptions = XmlPullParserException.class)
    public void testRemoveAtRoot() throws XmlPullParserException {

        final ElementLocator locator = newInstance();

        Assert.assertEquals(locator.atRoot(), true);

        locator.removeCurrentAndLocateParent();
    }
}
