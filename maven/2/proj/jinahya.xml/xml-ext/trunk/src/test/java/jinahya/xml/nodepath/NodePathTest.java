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

package jinahya.xml.nodepath;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpressionException;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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


    @Test
    public void printBooks2DotXml()
        throws ParserConfigurationException, SAXException, IOException,
               XPathExpressionException {

        final InputStream books2DotXmlStream =
            NodePathTest.class.getResourceAsStream("/books2.xml");

        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        final DocumentBuilder db = dbf.newDocumentBuilder();

        final XPathFactory xpf = XPathFactory.newInstance();

        final Node n = db.parse(books2DotXmlStream);
        final XPath xp = xpf.newXPath();

        final NodePath np = new NodePath(n, xp);

        final NodeList bookNodeList = np.NODESET("/catalog/book");
        final int length = bookNodeList.getLength();
        for (int i = 0; i < length; i++) {
            final Node bookNode = bookNodeList.item(i);
            final NodePath bookPath = np.child(bookNode);
            System.out.println("---------------------------------------------");
            System.out.println("id: " + bookPath.STRING("@id"));
            System.out.println("author: " + bookPath.STRING("author/text()"));
            System.out.println("title: " + bookPath.STRING("title/text()"));
            System.out.println("genre: " + bookPath.STRING("genre/text()"));
            System.out.println("price: " + bookPath.FLOAT("price/text()"));
            System.out.println("publish_date: " + bookPath.STRING("publish_date/text()"));
            System.out.println("description: " + bookPath.STRING("description/text()"));


        }
    }
}
