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

package jinahya.xml.xpath;


import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import jinahya.xml.namespace.NamespaceContextImpl;

import org.testng.Assert;
import org.testng.annotations.Test;

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
        "<?xml version=\"1.0\"?>" +
        "<a xmlns=\"" + NS + "\" b=\"c\">" +
        "  <d e=\"f\">" +
        "    <g>h</g>" +
        "  </d>" +
        "</a>";

    /**
     *
     */
    public static final String XML2 =
        "<?xml version=\"1.0\"?>" +
        "<a:a xmlns:a=\"" + NS + "\" b=\"c\">" +
        "  <a:d e=\"f\">" +
        "    <a:g>h</a:g>" +
        "  </a:d>" +
        "</a:a>";


    //@Test
    public void test()
        throws ParserConfigurationException, SAXException, IOException,
               XPathExpressionException {

        final NodePath<Document> documentPath =
            NodePathFactoryTest.load(null, null, XML);

        test("", documentPath);
    }


    @Test
    public void testNS()
        throws ParserConfigurationException, SAXException, IOException,
               XPathExpressionException {

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        final DocumentBuilder builder = factory.newDocumentBuilder();

        final XPath xPath = XPathFactory.newInstance().newXPath();
        final NamespaceContextImpl nci = new NamespaceContextImpl();
        //nci.setDefaultNamespaceURI(NS);
        nci.bind(NS, "a");
        xPath.setNamespaceContext(nci);

        final NodePath<Document> documentPath =
            NodePathFactoryTest.load(builder, xPath, XML);

        test("a:", documentPath);
    }


    private void test(final String prefix, NodePath<Document> path)
        throws XPathExpressionException {

        NodePath<Element> a =
            path.getChildPath(Element.class, prefix + "a", true);
        System.out.println("a: " + a);

        Assert.assertEquals(a.evaluate("@b", true), "c");


        NodePath<Element> d = a.getChildPath(Element.class, prefix + "d", true);
        Assert.assertEquals(d.evaluate("@e", true), "f");


        NodePath<Element> g = d.getChildPath(Element.class, prefix + "g", true);
        Assert.assertEquals(g.evaluate("text()", true), "h");
    }
}
