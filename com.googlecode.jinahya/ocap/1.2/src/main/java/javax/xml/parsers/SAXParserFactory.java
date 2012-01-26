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

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;

/**
 * Defines a factory API that enables applications to configure and
 * obtain a SAX based parser to parse XML documents.<p>
 * An implementation of the <code>SAXParserFactory</code> class is
 * <em>NOT</em> guaranteed to be thread safe. It is up to the user application 
 * to make sure about the use of the <code>SAXParserFactory</code> from 
 * more than one thread. Alternatively the application can have one instance 
 * of the <code>SAXParserFactory</code> per thread.
 * An application can use the same instance of the factory to obtain one or 
 * more instances of the <code>SAXParser</code> provided the instance
 * of the factory isn't being used in more than one thread at a time.
 * <p>
 *
 * The static <code>newInstance</code> method returns a new concrete 
 * implementation of this class.
 *
 * @since JAXP 1.0
 */

public abstract class SAXParserFactory
{

    private boolean namespaceAware = false;

    private boolean validating = false;

    protected SAXParserFactory()
    {
    }

    /**
     * Obtain a new instance of a <code>SAXParserFactory</code>. This
     * static method creates a new factory instance
     * This method uses the following ordered lookup procedure to determine
     * the <code>SAXParserFactory</code> implementation class to
     * load:
     * <ul>
     * <li>
     * Use the <code>javax.xml.parsers.SAXParserFactory</code> system
     * property.
     * </li>
     * <li>
     * Platform default <code>SAXParserFactory</code> instance.
     * </li>
     * </ul>
     *
     * Once an application has obtained a reference to a
     * <code>SAXParserFactory</code> it can use the factory to
     * configure and obtain parser instances.
     *
     * @return A new instance of a SAXParserFactory.
     *
     * @exception FactoryConfigurationError if the implementation is
     * not available or cannot be instantiated.
     */

    public static SAXParserFactory newInstance()
        throws FactoryConfigurationError
    {
        try {
            String fname = System.getProperty("javax.xml.parsers.SAXParserFactory");
            if(fname == null)
                fname = "com.sun.ukit.xml.SAXParserFactoryImp";
            return (SAXParserFactory) Class.forName(fname).newInstance();
        } catch (java.lang.Throwable ex) {
            throw new FactoryConfigurationError("");
        }
    }


    /**
     * Creates a new instance of a SAXParser using the currently
     * configured factory parameters.
     *
     * @return A new instance of a SAXParser.
     *
     * @exception ParserConfigurationException if a parser cannot
     * be created which satisfies the requested configuration.
     */

    public abstract SAXParser newSAXParser()
        throws ParserConfigurationException, SAXException;


    /**
     * Specifies that the parser produced by this code will
     * provide support for XML namespaces. By default the value of this is set
     * to <code>false</code>.
     *
     * @param awareness <code>true</code> if the parser produced by this code will
     *                  provide support for XML namespaces; <code>false</code> otherwise.
     */
    
    public void setNamespaceAware(boolean awareness)
    {
        namespaceAware = awareness;
    }


    /**
     * Indicates whether or not the factory is configured to produce
     * parsers which are namespace aware.
     *
     * @return <code>true</code> if the factory is configured to produce
     *         parsers which are namespace aware; <code>false</code> otherwise.
     */
    
    public boolean isNamespaceAware() 
    {
        return namespaceAware;
    }


    /**
     * Specifies that the parser produced by this code will validate
     * documents as they are parsed. By default the value of this is
     * set to <code>false</code>.
     *
     * @param validating <code>true</code> if the parser produced by this code will
     *                   validate documents as they are parsed; <code>false</code>
     *                   otherwise.
     */
    public void setValidating(boolean validating)
    { 
        // NOTE: the RI's factory does not currently support a validating parser
        this.validating = validating;
    }


    /**
     * Indicates whether or not the factory is configured to produce
     * parsers which validate the XML content during parse.
     *
     * @return true if the factory is configured to produce parsers
     *         which validate the XML content during parse.
     */
    public boolean isValidating() 
    {
        return validating;
    }


    /**
     * Sets the particular feature in the underlying implementation of
     * org.xml.sax.XMLReader.
     * A list of the core features and properties can be found at
     * <a href="http://www.saxproject.org/?selected=get-set"> http://www.saxproject.org/?selected=get-set </a>
     *
     * @param name The name of the feature to be set.
     * @param value The value of the feature to be set.
     * @exception SAXNotRecognizedException When the underlying XMLReader does
     *            not recognize the property name.
     *
     * @exception SAXNotSupportedException When the underlying XMLReader
     *            recognizes the property name but doesn't support the
     *            property.
     *
     * @see org.xml.sax.XMLReader#setFeature
     */
    public abstract void setFeature(String name, boolean value)
        throws ParserConfigurationException, SAXNotRecognizedException,
                SAXNotSupportedException;


    /**
     * Returns the particular property requested for in the underlying
     * implementation of org.xml.sax.XMLReader.
     *
     * @param name The name of the property to be retrieved.
     * @return Value of the requested property.
     *
     * @exception SAXNotRecognizedException When the underlying XMLReader does
     *            not recognize the property name.
     *
     * @exception SAXNotSupportedException When the underlying XMLReader
     *            recognizes the property name but doesn't support the
     *            property.
     *
     * @see org.xml.sax.XMLReader#getProperty
     */
    public abstract boolean getFeature(String name)
        throws ParserConfigurationException, SAXNotRecognizedException,
                SAXNotSupportedException;

}

