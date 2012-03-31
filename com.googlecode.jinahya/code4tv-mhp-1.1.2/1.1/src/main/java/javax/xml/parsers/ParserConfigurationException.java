/*
 * @(#)ParserConfigurationException.java	1.2 03/01/27
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.parsers;

/**
 * Indicates a serious configuration error.
 *
 * @since JAXP 1.0
 * @version 1.0
 */

public class ParserConfigurationException extends Exception {
    public ParserConfigurationException(final String msg) {
	super(msg);
    }
}

