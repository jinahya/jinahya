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


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class NodePathFactory {


    /**
     *
     * @return
     */
    public static NodePathFactory newInstance() {
        return newInstance(null, null);
    }


    /**
     *
     * @param builder
     * @param xPath
     * @return
     */
    public static NodePathFactory newInstance(final DocumentBuilder builder,
                                              final XPath xPath) {

        return new NodePathFactory(builder, xPath);
    }


    /**
     *
     */
    private static class DocumentPath extends NodePath<Document> {


        /**
         *
         * @param node
         * @param path
         */
        private DocumentPath(final Document node, final XPath path) {
            super(node, path);
        }
    }


    /**
     *
     * @param builder
     * @param xPath
     */
    protected NodePathFactory(final DocumentBuilder builder,
                              final XPath xPath) {

        super();

        db = builder;
        xp = xPath;
    }


    /**
     *
     * @param source
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public final NodePath<Document> newDocumentPath(final File source)
        throws ParserConfigurationException, SAXException, IOException {

        return newDocumentPath(getDocumentBuilder().parse(source));
    }


    /**
     *
     * @param source
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public final NodePath<Document> newDocumentPath(final InputStream source)
        throws ParserConfigurationException, SAXException, IOException {

        return newDocumentPath(getDocumentBuilder().parse(source));
    }


    /**
     *
     * @param source
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public final NodePath<Document> newDocumentPath(final InputSource source)
        throws ParserConfigurationException, SAXException, IOException {

        return newDocumentPath(getDocumentBuilder().parse(source));
    }


    /**
     *
     * @param document
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public final NodePath<Document> newDocumentPath(final Document document)
        throws ParserConfigurationException, SAXException, IOException {

        return new DocumentPath(document, getXPath());
    }


    /**
     *
     * @return
     */
    public synchronized DocumentBuilder getDocumentBuilder()
        throws ParserConfigurationException {

        if (db == null) {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }

        return db;
    }


    /**
     *
     * @param factory
     */
    public synchronized void setDocumentBuilder(final DocumentBuilder builder) {
        db = builder;
    }


    /**
     *
     * @return
     */
    public synchronized XPath getXPath() {
        if (xp == null) {
            xp = XPathFactory.newInstance().newXPath();
        }
        return xp;
    }


    /**
     *
     * @param factory
     */
    public synchronized void setXPath(final XPath xPath) {
        xp = xPath;
    }


    private DocumentBuilder db;

    private XPath xp;
}
