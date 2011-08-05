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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <D> document type parameter
 */
public abstract class FamilyTest<D> {


    private static final byte[] XML;


    static {

        final InputStream input =
            FamilyTest.class.getResourceAsStream("family.xml");
        if (input == null) {
            throw new InstantiationError("no resource for 'family.xml'");
        }
        try {
            try {
                final ByteArrayOutputStream output =
                    new ByteArrayOutputStream();
                final byte[] buffer = new byte[1024];
                for (int read = -1; (read = input.read(buffer)) != -1;) {
                    output.write(buffer, 0, read);
                }
                output.flush();
                XML = output.toByteArray();
            } finally {
                input.close();
            }
        } catch (IOException ioe) {
            throw new InstantiationError(ioe.getMessage());
        }
    }


    protected abstract D parseDocument(InputStream source, String charsetName)
        throws Exception;


    protected abstract ElementLocator parseLocator(D document) throws Exception;


    protected abstract void printLocator(ElementLocator locator, D document)
        throws Exception;


    protected abstract void printDocument(D document, OutputStream target,
                                          String charsetName)
        throws IOException;


    @Test
    public void test() throws Exception {

        final D document = parseDocument(new ByteArrayInputStream(XML), "UTF-8");
        final ElementLocator locator = parseLocator(document);


        final String family = "http://www.example.org/family";

        final String other = "http://www.example.com/family/other";


        // located to /:grandparent
        Assert.assertEquals(locator.getNamespaceURI(), family);
        Assert.assertEquals(locator.getLocalName(), "grandparent");
        Assert.assertEquals(locator.getAttribute("age"), "100");

        // locate to /:grandparent/:parent[1]
        locator.locateChild(family, "parent", 0);
        Assert.assertEquals(locator.getAttribute("age"), "50");

        // /:grandparent/:parent/:child[N]
        final int childCount = locator.getChildCount(family, "child");
        for (int i = 0; i < childCount; i++) {

            locator.locateChild(family, "child", i);
            final String age = locator.getAttribute(family, "age");

            // /:grandparent/:parent/:child[N]/:grandchild
            final int grandChildCount =
                locator.getChildCount(family, "grandchild");
            for (int j = 0; j < grandChildCount; j++) {
                locator.locateChild(family, "grandchild", j);
                final String gender = locator.getAttribute(other, "gender");
                locator.setAttribute(other, "gender", null); // remove attribute
                System.out.println("gender: " + gender);
                final String name = locator.getText();
                System.out.println("name: " + name);
                locator.setText(null); // remove text
                locator.locateParent();
            }

            // /:grandparent/:parent/:child/o:spouse
            final int spouseCount = locator.getChildCount(other, "spouse");
            for (int j = spouseCount - 1; j >= 0; j--) {
                locator.locateChild(other, "spouse", j);
                final String spouseName = locator.getAttribute(other, "name");
                locator.setAttribute(other, "spouse", null); // remove attribute
                System.out.println("spouseName: " + spouseName);
                if (j > 0) {
                    locator.removeCurrent();
                } else {
                    locator.locateParent();
                }
            }

            locator.locateParent();
        }


        printLocator(locator, document);

        printDocument(document, System.out, "UTF-8");
    }
}

