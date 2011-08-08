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

import javax.xml.XMLConstants;

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Family {


    static InputStream family() {
        return Family.class.getResourceAsStream("family.xml");
    }


    static void family(final ElementLocator locator) {

        final String family = "http://www.example.org/family";
        final String other = "http://www.example.com/family/other";

        final String namespaceURI = locator.getNamespaceURI(); // family
        final String localName = locator.getLocalName(); // "grandparent"
        final String grandparentAge = locator.getAttribute("age"); // "100"
        System.out.println("/:grandparent/@age -> " + grandparentAge);

        final int parentCount = locator.getChildCount(family, "parent");
        for (int i = 0; i < parentCount; i++) {

            locator.locateChild(family, "parent", i);

            final String parentAge = locator.getAttribute("age"); // "50";
            System.out.println(
                "/:grandparent/:parent[" + (i + 1) + "]/@age -> " + parentAge);

            final int childCount = locator.getChildCount(family, "child");
            for (int j = 0; j < childCount; j++) {

                locator.locateChild(family, "child", j);

                final String childAge = locator.getAttribute("age");
                System.out.println(
                    "/:grandparent/:parent[" + (i + 1) + "]/:child[" + (j + 1)
                    + "]/@age -> " + childAge);

                final int grandChildCount =
                    locator.getChildCount(family, "grandchild");
                for (int k = 0; k < grandChildCount; k++) {

                    locator.locateChild(family, "grandchild", k);

                    final String gender = locator.getAttribute(other, "gender");
                    System.out.println(
                        "/:grandparent/:parent[" + (i + 1) + "]/:child["
                        + (j + 1) + "]/:grandchild/@o:gender -> " + gender);
                    locator.setAttribute(other, "gender", null);

                    final String name = locator.getText();
                    System.out.println(
                        "/:grandparent/:parent[" + (i + 1) + "]/:child["
                        + (j + 1) + "]/:grandchild/text() -> " + name);

                    locator.locateParent();
                }

                final int spouseCount = locator.getChildCount(other, "spouse");
                for (int k = spouseCount - 1; k >= 0; k--) {

                    locator.locateChild(other, "spouse", k);

                    final String spouseName =
                        locator.getAttribute(other, "name");
                    System.out.println(
                        "/:grandparent/:parent[" + (i + 1) + "]/:child["
                        + (j + 1) + "]/o:spouse[" + (k + 1) + "]/@o:name -> "
                        + spouseName);

                    if (k > 0) {
                        locator.removeCurrent();
                    } else {
                        locator.locateParent();
                    }
                }

                locator.locateParent();
            }

            locator.locateParent();
        }
    }


    public static void main(String[] args) throws Exception {

        // ------------------------------------------ PARSE DOCUMENT FROM SOURCE
        DOMImplementationRegistry registry =
            DOMImplementationRegistry.newInstance();
        DOMImplementationLS implementation =
            (DOMImplementationLS) registry.getDOMImplementation("XML 3.0");
        final LSParser parser = implementation.createLSParser(
            DOMImplementationLS.MODE_SYNCHRONOUS,
            XMLConstants.W3C_XML_SCHEMA_NS_URI);
        final LSInput input = implementation.createLSInput();
        input.setByteStream(Family.class.getResourceAsStream("family.xml"));
        input.setEncoding("UTF-8");
        final Document document = parser.parse(input);

        // ----------------------------------------- PARSE LOCATOR FROM DOCUMENT
        final ElementLocator locator = DOMElementLocator.parse(document);


        family(locator);


        // ------------------------------------------- PRINT LOCATOR TO DOCUMENT
        DOMElementLocator.print(locator, document);


        // -------------------------------------------- PRINT DOCUMENT TO TARGET
        final LSSerializer serializer = implementation.createLSSerializer();
        final DOMConfiguration domConfig = serializer.getDomConfig();
        domConfig.setParameter("format-pretty-print", Boolean.TRUE);
        final LSOutput output = implementation.createLSOutput();
        output.setByteStream(System.out);
        output.setEncoding("UTF-8");
        serializer.write(document, output);
        System.out.println();
    }
}

