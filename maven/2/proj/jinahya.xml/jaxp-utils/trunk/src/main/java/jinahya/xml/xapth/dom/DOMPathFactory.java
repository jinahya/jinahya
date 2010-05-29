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

package jinahya.xml.xapth.dom;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
public class DOMPathFactory {


    /**
     *
     * @param source
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public final DocumentPath newDocumentPath(final File source)
        throws ParserConfigurationException, SAXException, IOException {

        final Document document =
            getDocumentBuilderFactory().newDocumentBuilder().parse(source);
        final XPath xPath = getXPathFactory().newXPath();

        return new DocumentPath(document, xPath);
    }


    /**
     *
     * @param source
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public final DocumentPath newDocumentPath(final InputStream source)
        throws ParserConfigurationException, SAXException, IOException {

        final Document document =
            getDocumentBuilderFactory().newDocumentBuilder().parse(source);

        final XPath xPath = getXPathFactory().newXPath();

        return new DocumentPath(document, xPath);
    }


    /**
     *
     * @param source
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public final DocumentPath newDocumentPath(final InputSource source)
        throws ParserConfigurationException, SAXException, IOException {

        final Document document =
            getDocumentBuilderFactory().newDocumentBuilder().parse(source);

        final XPath xPath = getXPathFactory().newXPath();

        return new DocumentPath(document, xPath);
    }


    /**
     *
     * @return
     */
    public DocumentBuilderFactory getDocumentBuilderFactory() {
        if (dbf == null) {
            dbf = DocumentBuilderFactory.newInstance();
            //dbf.setNamespaceAware(true);
        }
        return dbf;
    }


    /**
     *
     * @param factory
     */
    public final void setDocumentBuilderFactory(
        final DocumentBuilderFactory factory) {

        this.dbf = factory;
    }


    /**
     *
     * @return
     */
    public XPathFactory getXPathFactory() {
        if (xpf == null) {
            xpf = XPathFactory.newInstance();
        }
        return xpf;
    }


    /**
     *
     * @param factory
     */
    public final void setXPathFactory(final XPathFactory factory) {
        this.xpf = factory;
    }


    private DocumentBuilderFactory dbf;

    private XPathFactory xpf;
}
