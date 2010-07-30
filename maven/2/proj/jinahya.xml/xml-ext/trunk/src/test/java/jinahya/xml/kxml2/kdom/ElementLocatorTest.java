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
import java.io.InputStream;

import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ElementLocatorTest {

    /*

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


    private static void print(final ElementLocator locator) throws IOException {
        print(locator.getDocument());
    }


    private static ElementLocator newInstance() {
        final Document document = new Document();
        document.addChild(
            Node.ELEMENT, document.createElement(ROOT_NAMESPACE, ROOT_NAME));
        return new ElementLocator(document);
    }


    private static ElementLocator newInstance(final String namespace,
                                              final String name) {

        final Document document = new Document();
        document.addChild(Node.ELEMENT,
                          document.createElement(namespace, name));
        return new ElementLocator(document);
    }

    @Test
    public void testConstructor() throws XmlPullParserException {
        final ElementLocator locator = newInstance();

        Assert.assertEquals(locator.getDepth(), 0);

        Assert.assertEquals(locator.getCurrentNamespace(), ROOT_NAMESPACE);
        Assert.assertEquals(locator.getCurrentName(), ROOT_NAME);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
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

        Assert.assertEquals(locator.count(NAMESPACE, NAME), 0);

        final int expected = RANDOM.nextInt(MAXIMUM_CHILD_COUNT);
        for (int i = 0; i < expected; i++) {
            locator.addAndLocateChild(NAMESPACE, NAME).parent();
        }
        Assert.assertEquals(locator.count(NAMESPACE, NAME), expected);
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
    public void testLocateRoot() {

        final ElementLocator locator = newInstance();

        final int count = RANDOM.nextInt(100) + 100;

        for (int i = 0; i < count; i++) {
            locator.root();
        }
    }


    @Test
    public void testGetAttribute() throws XmlPullParserException {
        final ElementLocator locator = newInstance();

        Assert.assertNull(locator.attribute(NAMESPACE, NAME));

        final String expected = "value";
        locator.attribute(NAMESPACE, NAME, expected);

        String actual = locator.attribute(NAMESPACE, NAME);
        Assert.assertNotNull(actual);
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testSetAttribute() throws XmlPullParserException, IOException {

        ElementLocator locator = newInstance();
        locator.child("child");

        final String value = "value";

        Assert.assertNull(locator.attribute(NAMESPACE, NAME));

        locator.attribute(NAMESPACE, NAME, value);
        Assert.assertEquals(locator.attribute(NAMESPACE, NAME), value);

        print(locator);

        locator.attribute(NAMESPACE, NAME, null);
        Assert.assertNull(locator.attribute(NAMESPACE, NAME));

        // ---------------------------------------------------------------------

        locator = newInstance(XmlPullParser.NO_NAMESPACE, "root");
        locator.child("child");

        Assert.assertNull(locator.attribute(XmlPullParser.NO_NAMESPACE, "attr"));

        locator.attribute(XmlPullParser.NO_NAMESPACE, "attr", value);
        Assert.assertEquals(locator.attribute(XmlPullParser.NO_NAMESPACE, "attr"), value);

        print(locator);

        locator.attribute(XmlPullParser.NO_NAMESPACE, "attr", null);
        Assert.assertNull(locator.attribute(XmlPullParser.NO_NAMESPACE, "attr"));
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


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetTextWithNull() throws XmlPullParserException {

        final ElementLocator locator = newInstance();

        String text = null;
        locator.setText(text);
    }


    @Test
    public void testRemoveAndLocateParent()
        throws XmlPullParserException, IOException {

        final ElementLocator locator = newInstance();

        final int expected = RANDOM.nextInt(MAXIMUM_CHILD_COUNT);

        for (int i = 0; i < expected; i++) {
            locator.addAndLocateChild(NAMESPACE, NAME).parent();
        }
        Assert.assertEquals(locator.count(NAMESPACE, NAME), expected);

        for (int i = 0; i < expected; i++) {
            final int childCount = locator.count(NAMESPACE, NAME);
            System.out.println("CHILD COUNT: " + childCount);
            Assert.assertTrue(childCount > 0);
            locator.locateChild(NAMESPACE, NAME, childCount - 1);
            locator.removeCurrentAndLocateParent();
        }
    }


    @Test(expectedExceptions = XmlPullParserException.class)
    public void testRemoveAndLocateParentAtRoot()
        throws XmlPullParserException {

        final ElementLocator locator = newInstance();

        Assert.assertEquals(locator.hasParent(), false);

        locator.removeCurrentAndLocateParent();
    }
     */


    @Test
    public void testPrint() throws XmlPullParserException, IOException {

        InputStream resource =
            ElementLocatorTest.class.getResourceAsStream("/sample.xml");

        final XmlPullParser parser =
            XmlPullParserFactory.newInstance().newPullParser();
        parser.setFeature(
            "http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        parser.setInput(resource, null);

        final Document document = new Document();
        document.parse(parser);

        final ElementLocator locator = new ElementLocator(document);

        final StringBuffer buffer = new StringBuffer();

        locator.root().childNS("ns:family", "parent", 0);
        System.out.println(locator.print(new StringBuffer()).toString());

        {
            final Element current = locator.current();
            final int childCount = current.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (current.getType(i) == Node.ELEMENT) {
                    final Element child = current.getElement(i);
                    System.out.println(
                        "{" + child.getNamespace() + "}" + child.getName());
                }
            }
        }

        final int femaleChildCount = locator.countNS("ns:female", "child");
        for (int i = 0; i < femaleChildCount; i++) {
            System.out.println(locator.childNS("ns:female", "child", i).
                print(buffer.delete(0, buffer.length()), true));
        }

        final int maleChildCount = locator.countNS("ns:male", "child");
        for (int i = 0; i < maleChildCount; i++) {
            System.out.println(locator.childNS("ns:male", "child", i).
                print(buffer.delete(0, buffer.length()), true));
        }
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testPrintWithNullBuffer()
        throws XmlPullParserException, IOException {

        InputStream resource =
            ElementLocatorTest.class.getResourceAsStream("/sample.xml");

        final XmlPullParser parser =
            XmlPullParserFactory.newInstance().newPullParser();
        parser.setFeature(
            "http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        parser.setInput(resource, null);

        final Document document = new Document();
        document.parse(parser);

        final ElementLocator locator = new ElementLocator(document);

        locator.print(null);
    }
}
