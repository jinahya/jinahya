/*
 * @(#)LocatorFactory.java	1.24 00/10/09
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
 * This class defines a factory for the creation of
 * <code>Locator</code> objects.
 *
 * @see javax.tv.locator.Locator */
public abstract class LocatorFactory {

  /**
   * Creates the <code>LocatorFactory</code> instance.
   */
  protected LocatorFactory() {}

  /**
   * Provides an instance of <code>LocatorFactory</code>.
   * 
   * @return A <code>LocatorFactory</code> instance.
   */
  public static LocatorFactory getInstance() {
	return null;
  }

  /**
   * Creates a <code>Locator</code> object from the specified locator
   * string.  The format of the locator string may be entirely
   * implementation-specific.
   *
   * @param locatorString The string form of the <code>Locator</code>
   * to be created.  The created <code>Locator</code> will have an
   * external form that is identical to <code>locatorString</code>.
   *
   * @return A <code>Locator</code> object representing the resource
   * referenced by the given locator string.
   *
   * @throws MalformedLocatorException If an incorrectly formatted
   * locator string is detected.
   *
   * @see Locator#toExternalForm */
  public abstract Locator createLocator(String locatorString)
      throws MalformedLocatorException;
  
  /**
   * Transforms a <code>Locator</code> into its respective collection
   * of transport dependent <code>Locator</code> objects. A
   * transformation on a transport dependent <code>Locator</code>
   * results in an identity transformation, i.e. the same locator is
   * returned in a single-element array.
   *
   * @param source The <code>Locator</code> to transform.
   *
   * @return An array of transport dependent <code>Locator</code>
   * objects for the given <code>Locator</code>.
   * 
   * @throws InvalidLocatorException If <code>source</code> is not a valid
   * Locator.  */
  public abstract Locator[] transformLocator(Locator source)
      throws InvalidLocatorException;
}
