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


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <D> document type parameter
 */
public abstract class ElementLocatorTest<D> {


    protected static URL getResource() {
        return ElementLocatorTest.class.getResource("test.xml");
    }


    @Test
    public void testRead() throws Exception {

        final URL url = ElementLocatorTest.class.getResource("test.xml");
        Assert.assertNotNull(url);

        final ElementLocator locator = parseLocator(url.openStream());
        System.out.println("-------------------------------------------- JSON");
        System.out.println(locator.getClass());
        System.out.println(locator.getRootElement().toJSON());
        System.out.println("-------------------------------------------- JSON");

        Assert.assertEquals(locator.getCurrentElement().namespaceURI, "http://a");
        Assert.assertEquals(locator.getCurrentElement().localName, "grandparent");

        Assert.assertEquals(locator.count("parent"), 2);

        for (int i = 0; i < 2; i++) {
            locator.child("parent", i);
            Assert.assertEquals(locator.text(), "parent" + i);
            locator.parent();
        }

        for (int i = 0; i < 2; i++) {
            locator.child("parent", i);
            Assert.assertEquals(locator.text(), "parent" + i);
            locator.parent();
        }

        Assert.assertEquals(locator.count("http://b", "parent"), 1);
        locator.child("http://b", "parent", 0);

        Assert.assertEquals(locator.attribute("http://a", "a"), "a");
        Assert.assertNull(locator.attribute("", "a"));

        Assert.assertEquals(locator.attribute("http://b", "b"), "b");
        Assert.assertNull(locator.attribute("", "b"));

        Assert.assertEquals(locator.attribute("http://c", "c"), "c");
        Assert.assertNull(locator.attribute("", "c"));

        Assert.assertEquals(locator.attribute("", "d"), "d");
        Assert.assertNull(locator.attribute("http://a", "d"));
        Assert.assertNull(locator.attribute("http://b", "d"));
        Assert.assertNull(locator.attribute("http://c", "d"));

        // --------------------------------------- /a:grandparent/b:parent/child
        Assert.assertEquals(locator.count("child"), 3);
        for (int i = 0; i < 3; i++) {
            locator.child("child", i);
            Assert.assertEquals(locator.text(), "child" + i);
            locator.parent();
        }
        Assert.assertEquals(locator.count("child"), 3);
        for (int i = 0; i < 3; i++) {
            locator.child("child", i);
            Assert.assertEquals(locator.text(), "child" + i);
            locator.parent();
        }

        // ------------------------------------- /a:grandparent/b:parent/c:child
        Assert.assertEquals(locator.count("http://c", "child"), 1);
        locator.child("http://c", "child", 0);

        // -------------------------- /a:grandparent/b:parent/c:child/grandchild
        Assert.assertEquals(locator.count("", "grandchild"), 1);
        locator.child("", "grandchild", 0);
        Assert.assertEquals(locator.text(), "grandchild");
    }


    protected abstract ElementLocator parseLocator(InputStream in)
        throws Exception;


    protected abstract ElementLocator createLocator(String namespaceURI,
                                                    String localName)
        throws Exception;


    protected abstract D createDocument() throws Exception;


    protected abstract void printElement(ELElement root, D document)
        throws Exception;


    protected abstract void printDocument(D document, OutputStream out,
                                          String charsetName)
        throws Exception;


    @Test
    public void testWrite() throws Exception {

        final ElementLocator locator =
            createLocator("http://a", "grandparent");

        locator.child("parent").text("parent0");
        locator.parent();

        locator.child("http://b", "parent");
        locator.attribute("http://a", "a", "a");
        locator.attribute("http://b", "b", "b");
        locator.attribute("http://c", "c", "c");
        locator.attribute("", "d", "d");
        locator.attribute("", "e", "e");

        locator.child("child").text("child0").parent();
        locator.child("child").text("child1").parent();

        locator.child("http://c", "child").
            child("grandchild").text("grandchild").
            parent().parent();

        locator.child("child").text("child2").parent();

        locator.parent();

        locator.child("parent").text("parent1");

        locator.root().child("normalizable");
        locator.attribute("", "normalizable", "  \t\n\n\tsdajfsdf    \t\n");
        locator.text("    \t\t\\n\nlkjsdfljsaflsdfjsd\t\t\\\t\n\\t");

        locator.root().child("\uD55C\uAE00").text("\uAD8C\uC9C4");

        final MessageDigest md = MessageDigest.getInstance("SHA-1");


        System.out.println("-------------------------------------------- JSON");
        System.out.println(locator.getClass());
        final String json = locator.getRootElement().toJSON();
        System.out.println(json);
        System.out.println("JSON.DIGEST: " + Hex.encodeHexString(
            md.digest(json.getBytes("US-ASCII"))));
        System.out.println("-------------------------------------------- JSON");


        final D document = createDocument();

        printElement(locator.getRootElement(), document);

        System.out.println("--------------------------------------------- XML");
        System.out.println(locator.getClass());

        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        final String charsetName = "UTF-8";

        printDocument(document, out, charsetName);
        out.flush();

        final byte[] bytes = out.toByteArray();

        System.out.println(new String(bytes, charsetName));
        System.out.println("--------------------------------------------- XML");
    }
}

