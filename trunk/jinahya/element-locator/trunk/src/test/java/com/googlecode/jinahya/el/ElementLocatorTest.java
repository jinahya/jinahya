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


package com.googlecode.jinahya.el;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <L> 
 * @param <D> 
 */
public abstract class ElementLocatorTest<L extends ElementLocator<D>, D> {


    protected static URL getResource() {
        return ElementLocatorTest.class.getResource("test.xml");
    }


    @Test
    public void testRead() throws Exception {

        final URL url = ElementLocatorTest.class.getResource("test.xml");
        Assert.assertNotNull(url);

        final ElementLocator<D> locator = parseLocator(url.openStream());
        System.out.println("-------------------------------------------- JSON");
        System.out.println(locator.getClass());
        System.out.println(locator.toJSON());
        System.out.println("-------------------------------------------- JSON");

        Assert.assertEquals(locator.getCurrent().namespaceURI, "http://a");
        Assert.assertEquals(locator.getCurrent().localName, "grandparent");

        Assert.assertEquals(locator.getChildCount("parent"), 2);

        for (int i = 0; i < 2; i++) {
            locator.locateChild("parent", i);
            Assert.assertEquals(locator.getText(), "parent" + i);
            locator.locateParent();
        }

        for (int i = 0; i < 2; i++) {
            locator.locateChild("parent", i);
            Assert.assertEquals(locator.getText(), "parent" + i);
            locator.locateParent();
        }

        Assert.assertEquals(locator.getChildCount("http://b", "parent"), 1);
        locator.locateChild("http://b", "parent", 0);

        Assert.assertEquals(locator.getAttribute("http://a", "a"), "a");
        Assert.assertNull(locator.getAttribute("a"));

        Assert.assertEquals(locator.getAttribute("http://b", "b"), "b");
        Assert.assertNull(locator.getAttribute("b"));

        Assert.assertEquals(locator.getAttribute("http://c", "c"), "c");
        Assert.assertNull(locator.getAttribute("c"));

        Assert.assertEquals(locator.getAttribute("d"), "d");
        Assert.assertNull(locator.getAttribute("http://a", "d"));
        Assert.assertNull(locator.getAttribute("http://b", "d"));
        Assert.assertNull(locator.getAttribute("http://c", "d"));

        // --------------------------------------- /a:grandparent/b:parent/child
        Assert.assertEquals(locator.getChildCount("child"), 3);
        for (int i = 0; i < 3; i++) {
            locator.locateChild("child", i);
            Assert.assertEquals(locator.getText(), "child" + i);
            locator.locateParent();
        }
        Assert.assertEquals(locator.getChildCount("child"), 3);
        for (int i = 0; i < 3; i++) {
            locator.locateChild("child", i);
            Assert.assertEquals(locator.getText(), "child" + i);
            locator.locateParent();
        }

        // ------------------------------------- /a:grandparent/b:parent/c:child
        Assert.assertEquals(locator.getChildCount("http://c", "child"), 1);
        locator.locateChild("http://c", "child", 0);

        // -------------------------- /a:grandparent/b:parent/c:child/grandchild
        Assert.assertEquals(locator.getChildCount("", "grandchild"), 1);
        locator.locateChild("", "grandchild", 0);
        Assert.assertEquals(locator.getText(), "grandchild");
    }


    protected abstract ElementLocator<D> parseLocator(InputStream in)
        throws Exception;


    protected abstract ElementLocator<D> createLocator(String namespaceURI,
                                                       String localName)
        throws Exception;


    protected abstract D createDocument() throws Exception;


    protected abstract void printDocument(D document, OutputStream out)
        throws Exception;


    @Test
    public void testWrite() throws Exception {

        final ElementLocator<D> locator =
            createLocator("http://a", "grandparent");

        locator.addChild("parent").setText("parent0", true);

        locator.addChild("http://b", "parent");
        locator.setAttribute("http://a", "a", "a");
        locator.setAttribute("http://b", "b", "b");
        locator.setAttribute("http://c", "c", "c");
        locator.setAttribute("d", "d");
        locator.setAttribute("e", "e");

        locator.addChild("child").setText("child0").locateParent();
        locator.addChild("child").setText("child1").locateParent();

        locator.addChild("http://c", "child").
            addChild("grandchild").setText("grandchild", true).
            locateParent();

        locator.addChild("child").setText("child2").locateParent();

        locator.locateParent();

        locator.addChild("parent").setText("parent1");

        locator.locateRoot().addChild("normalizable");
        locator.setAttribute("normalizable", "  \t\n\n\tsdajfsdf    \t\n");
        locator.setText("    \t\t\\n\nlkjsdfljsaflsdfjsd\t\t\\\t\n\\t");


        final D document = createDocument();

        locator.print(document);

        System.out.println("--------------------------------------------- XML");
        System.out.println(locator.getClass());

        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        printDocument(document, out);
        out.flush();

        final byte[] bytes = out.toByteArray();

        System.out.println(new String(bytes, "UTF-8"));
        System.out.println("--------------------------------------------- XML");
    }
}

