/*
 * @(#)FactoryConfigurationError.java	1.2 03/01/27 
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.parsers;

/**
 * Thrown when a problem with configuration with the Parser Factories
 * exists. This error will typically be thrown when the class of a
 * parser factory specified in the system properties cannot be found
 * or instantiated.
 *
 * @since JAXP 1.0
 * @version 1.0
 */

public class FactoryConfigurationError extends Error {
    public FactoryConfigurationError(final String msg) {
	super(msg);
    }
}







