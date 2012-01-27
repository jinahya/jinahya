/*
 * @(#)MalformedLocatorException.java	1.5 00/08/06
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.tv.locator;


/**
 *  This exception is thrown to indicate that a malformed locator
 *  string has been used.  Either no legal mapping could be determined
 *  for the specified string, or the string could not be parsed.
 */
public class MalformedLocatorException extends Exception {
	
  /**
   * Constructs a <code>MalformedLocatorException</code> with no
   * detail message.  */
  public MalformedLocatorException() {
	super();
  }
  
  /**
   *
   * Constructs a <code>MalformedLocatorException</code> with the
   * specified detail message.
   *
   * @param reason The reason the exception was raised.  */
  public MalformedLocatorException(String reason) {
	super(reason);
  }
}
