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
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.testng.annotations.Test;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class NodePathTest {


    private static final String XML = "<a><b c=\"d\">f</b></a>";


    @Test
    public void test()
        throws ParserConfigurationException, SAXException, IOException,
               XPathExpressionException {

        final Document document = DocumentBuilderFactory.newInstance().
            newDocumentBuilder().parse(new InputSource(new StringReader(XML)));

        final XPath path = XPathFactory.newInstance().newXPath();

        NodePath<Document> docpath = new NodePath(document, path);

        NodePath<Element> b = docpath.getChildPath(Element.class, "/a/b", true);
        System.out.println(b.evaluate("text()", true));
    }
}
