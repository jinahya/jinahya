/*
 * @(#)InvalidLocatorException.java	1.8 00/08/06
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
 * This exception is thrown when a <code>Locator</code> is not valid
 * in a particular context.  A <code>Locator</code> can be invalid or
 * several reasons, including:
 *
 * <ul>
 *
 * <li> The <code>Locator</code> refers to a resource that is not
 * valid at the time of usage.
 *
 * <li> The <code>Locator</code> refers to a type of resource that is
 * not appropriate for usage as a particular method parameter.
 *
 * <li> The <code>Locator</code> refers to a type of
 * resource whose usage is not supported on this system.
 *
 * </ul> */
public class InvalidLocatorException extends Exception {

	Locator locator = null;
	
  /**
   * Constructs an <code>InvalidLocatorException</code> with no
   * detail message.
   *
   * @param locator The offending <code>Locator</code>.
   */
  public InvalidLocatorException(Locator locator) {
	super();
	this.locator = locator;
  }
  
  /**
   * Constructs an <code>InvalidLocatorException</code> with the
   * specified detail message.
   *
   * @param locator The offending <code>Locator</code>.
   * @param reason The reason this <code>Locator</code> is invalid.
   */
  public InvalidLocatorException(Locator locator, String reason) {
	super(reason);
	//super(locator.toExternalForm() + ": " + reason);
	this.locator = locator;
  }

  /**
   * Returns the offending <code>Locator</code> instance.
   *
   * @return The locator that caused the exception.
   */
  public Locator getInvalidLocator() {
	return locator;
  }
}
