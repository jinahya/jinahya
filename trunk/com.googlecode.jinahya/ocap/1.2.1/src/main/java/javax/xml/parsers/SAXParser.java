/*
 * Copyright  1990-2008 Sun Microsystems, Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License version
 * 2 only, as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License version 2 for more details (a copy is
 * included at /legal/license.txt).
 * 
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA
 * 
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa
 * Clara, CA 95054 or visit www.sun.com if you need additional
 * information or have any questions.
 */

package javax.xml.parsers;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Defines the API that represents a simple SAX parser.
 *
 * An instance of this class can be obtained from the
 * {@link javax.xml.parsers.SAXParserFactory#newSAXParser()} method.
 * Once an instance of this class is obtained, XML can be parsed from
 * an InputStream<p>
 *
 * As the content is parsed by the underlying parser, methods of the
 * given {@link org.xml.sax.helpers.DefaultHandler} are called.<p>
 *
 * An implementation of <code>SAXParser</code> is <em>NOT</em> 
 * guaranteed to behave as per the specification if it is used concurrently by 
 * two or more threads. It is recommended to have one instance of the
 * <code>SAXParser</code> per thread or it is upto the application to 
 * make sure about the use of <code>SAXParser</code> from more than one
 * thread.
 *
 * @since JAXP 1.0
 */

public abstract class SAXParser {

    protected SAXParser()
    {
    }


    /**
     * Parse the content of the given {@link java.io.InputStream}
     * instance as XML using the specified
     * {@link org.xml.sax.helpers.DefaultHandler}.
     *
     * @param is InputStream containing the content to be parsed.
     * @param dh The SAX DefaultHandler to use.
     * @exception IOException If any IO errors occur.
     * @exception IllegalArgumentException If the given InputStream is null.
     * @exception SAXException If the underlying parser throws a
     * SAXException while parsing.
     */

    public abstract void parse(InputStream is, DefaultHandler dh)
        throws SAXException, IOException;


    /**
     * Parse the content given {@link org.xml.sax.InputSource}
     * as XML using the specified
     * {@link org.xml.sax.helpers.DefaultHandler}.
     *
     * @param is The InputSource containing the content to be parsed.
     * @param dh The SAX DefaultHandler to use.
     * @exception IOException If any IO errors occur.
     * @exception IllegalArgumentException If the InputSource is null.
     * @exception SAXException If the underlying parser throws a
     * SAXException while parsing.
     * @see org.xml.sax.helpers.DefaultHandler
     */

    public abstract void parse(InputSource is, DefaultHandler dh)
        throws SAXException, IOException;


    /**
     * Returns the {@link org.xml.sax.XMLReader} that is encapsulated by the
     * implementation of this class.
     *
     * @return The XMLReader that is encapsulated by the
     *         implementation of this class.
     * 
     * @throws SAXException If any SAX errors occur during processing.
     */

    public abstract org.xml.sax.XMLReader getXMLReader()
        throws SAXException;


    /**
     * Indicates whether or not this parser is configured to
     * understand namespaces.
     *
     * @return <code>true</code> if this parser is configured to
     *         understand namespaces; <code>false</code> otherwise.
     */
    
    public abstract boolean isNamespaceAware();


    /**
     * Indicates whether or not this parser is configured to validate
     * XML documents.
     *
     * @return <code>true</code> if this parser is configured to validate XML
     *          documents; <code>false</code> otherwise.
     */

    public abstract boolean isValidating();

}
