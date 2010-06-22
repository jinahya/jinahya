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
     * Creates a new instance.
     *
     * @return a new instance
     */
    public static NodePathFactory newInstance() {
        return newInstance(null, null);
    }


    /**
     * Creates a new instance with given parameters.
     *
     * @param documentBuilder the document builder
     * @param xPath the xpath
     * @return a new instance
     */
    public static NodePathFactory newInstance(
        final DocumentBuilder documentBuilder, final XPath xPath) {

        return new NodePathFactory(documentBuilder, xPath);
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
     * @param documentBuilder
     * @param xPath
     */
    protected NodePathFactory(final DocumentBuilder documentBuilder,
                              final XPath xPath) {

        super();

        this.documentBuilder = documentBuilder;
        this.xPath = xPath;
    }


    /**
     * Creates a new instance of {@link jinahya.xml.nodepath.DocumentPath} with
     * a {@link org.w3c.dom.Document} parsed from given <code>source</code>.
     *
     * @param source document source
     * @return a new instance of {@link jinahya.xml.nodepath.DocumentPath}
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @see javax.xml.parsers.DocumentBuilder#parse(java.io.File)
     */
    public final NodePath<Document> parseRootPath(final File source)
        throws ParserConfigurationException, SAXException, IOException {

        return new DocumentPath(getDocumentBuilder().parse(source), getXPath());
    }


    /**
     * Creates a new instance of {@link jinahya.xml.nodepath.DocumentPath} with
     * a {@link org.w3c.dom.Document} parsed from given <code>source</code>.
     *
     * @param source the document source
     * @return a new instance of {@link jinahya.xml.nodepath.DocumentPath}
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @see javax.xml.parsers.DocumentBuilder#parse(org.xml.sax.InputSource)
     */
    public final NodePath<Document> parseRootPath(final InputSource source)
        throws ParserConfigurationException, SAXException, IOException {

        return new DocumentPath(getDocumentBuilder().parse(source), getXPath());
    }


    /**
     *
     * @param source
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public final NodePath<Document> parseRootPath(final InputStream source)
        throws ParserConfigurationException, SAXException, IOException {

        return new DocumentPath(getDocumentBuilder().parse(source), getXPath());
    }


    /**
     *
     * @param source
     * @param systemId
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @see javax.xml.parsers.DocumentBuilder#parse(java.io.InputStream,
     *                                              java.lang.String)
     */
    public final NodePath<Document> parseRootPath(final InputStream source,
                                                  final String systemId)
        throws ParserConfigurationException, SAXException, IOException {

        return new DocumentPath(getDocumentBuilder().parse(source, systemId),
                                getXPath());
    }


    /**
     *
     * @return
     */
    public synchronized DocumentBuilder getDocumentBuilder()
        throws ParserConfigurationException {

        if (documentBuilder == null) {
            documentBuilder =
                DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }

        return documentBuilder;
    }


    /**
     *
     * @param documentBuilder
     */
    public synchronized void setDocumentBuilder(
        final DocumentBuilder documentBuilder) {

        this.documentBuilder = documentBuilder;
    }


    /**
     *
     * @return
     */
    public synchronized XPath getXPath() {
        if (xPath == null) {
            xPath = XPathFactory.newInstance().newXPath();
        }
        return xPath;
    }


    /**
     *
     * @param xPath
     */
    public synchronized void setXPath(final XPath xPath) {
        this.xPath = xPath;
    }


    private volatile DocumentBuilder documentBuilder;

    private volatile XPath xPath;
}
