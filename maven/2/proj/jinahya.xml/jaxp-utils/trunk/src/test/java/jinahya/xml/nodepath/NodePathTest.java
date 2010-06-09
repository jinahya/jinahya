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

package jinahya.xml.nodepath;


import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import jinahya.xml.namespace.NamespaceContextImpl;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class NodePathTest {


    /**
     *
     */
    public static final String NS = "http://www.test.com";


    /**
     *
     */
    public static final String XML =
        "<?xml version=\"1.0\"?>"
        + "<a xmlns=\"" + NS + "\" b=\"c\">"
        + "  <d e=\"f\">"
        + "    <g>h</g>"
        + "    <b>true</b>"
        + "  </d>"
        + "</a>";


    /** PRIVATE. */
    private static NodePath<Document> documentPath;


    @BeforeClass
    public static void createDocumentNodePath()
        throws ParserConfigurationException, SAXException, IOException {

        documentPath = DocumentPathFactory.newInstance().newDocumentPath(
            new InputSource(new StringReader(XML)));
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEvaluateWithNullExpression()
        throws XPathExpressionException {

        documentPath.evaluate(null);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEvaluateWithNullReturnType()
        throws XPathExpressionException {

        documentPath.evaluate("/", null);
    }
}
