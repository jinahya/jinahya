/*
 * @(#)SAXParser.java	1.2 03/04/11
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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
 * @version 1.0
 */

public abstract class SAXParser {

    protected SAXParser() {
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
     * @see org.xml.sax.DocumentHandler
     */

    public abstract void parse(InputSource is, DefaultHandler dh)
        throws SAXException, IOException;
    
    /**
     * Indicates whether or not this parser is configured to
     * understand namespaces.
     *
     * @return true if this parser is configured to
     *         understand namespaces; false otherwise.
     */
    
    public abstract boolean isNamespaceAware();

    /**
     * Indicates whether or not this parser is configured to validate
     * XML documents.
     *
     * @return true if this parser is configured to validate XML
     *          documents; false otherwise.
     */
    
    public abstract boolean isValidating();
}
