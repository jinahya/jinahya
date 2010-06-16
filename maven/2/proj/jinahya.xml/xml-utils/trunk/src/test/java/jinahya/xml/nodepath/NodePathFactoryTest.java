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


import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class NodePathFactoryTest {


    private static NodePathFactory instance;


    @BeforeClass
    public static void testNewInstance() {
        instance = NodePathFactory.newInstance();
    }


    /**
     *
     * @param builder
     * @param xPath
     * @param xml
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static NodePath<Document> load(final DocumentBuilder builder,
                                          final XPath xPath, final String xml)
        throws ParserConfigurationException, SAXException, IOException {

        final NodePathFactory factory =
            NodePathFactory.newInstance(builder, xPath);

        return factory.parseRootPath(new InputSource(new StringReader(xml)));
    }


    @Test
    public void test() throws ParserConfigurationException, SAXException,
                              IOException {

        final NodePath<Document> documentPath =
            load(null, null, NodePathTest.XML);

        Assert.assertNotNull(documentPath);
    }


    @Test
    public void testParseRootPathWithFile()
        throws ParserConfigurationException, SAXException, IOException {

        instance.parseRootPath(new File("src/test/resources/sample.xml"));
    }


    @Test(expectedExceptions = java.lang.IllegalArgumentException.class)
    public void testParseRootPathWithNullFile()
        throws ParserConfigurationException, SAXException, IOException {

        instance.parseRootPath((File) null);
    }


    @Test
    public void testParseRootWithInputStream()
        throws ParserConfigurationException, SAXException, IOException {

        final InputStream source =
            new FileInputStream("src/test/resources/sample.xml");
        try {
            instance.parseRootPath(source);
        } finally {
            source.close();
        }
    }


    @Test(expectedExceptions = java.lang.IllegalArgumentException.class)
    public void testParseRootPathWithNullInputStream()
        throws ParserConfigurationException, SAXException, IOException {

        instance.parseRootPath((InputStream) null);
    }


    @Test
    public void testParseRootPathWithInputSource()
        throws ParserConfigurationException, SAXException, IOException {

        final Reader source = new FileReader("src/test/resources/sample.xml");
        try {
            instance.parseRootPath(new InputSource(source));
        } finally {
            source.close();
        }
    }


    @Test(expectedExceptions = java.lang.IllegalArgumentException.class)
    public void testParseRootPathWithNullInputSource()
        throws ParserConfigurationException, SAXException, IOException {

        instance.parseRootPath((InputSource) null);
    }
}
