/*
 * @(#)Locator.java	1.24 00/10/09
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
 * The <code>Locator</code> interface provides an opaque reference to
 * the location information of objects which are addressable within the
 * Java TV API. A given locator may represent a transport independent
 * object and have multiple mappings to transport dependent locators.
 * Methods are provided for discovery of such circumstances and for
 * transformation to transport dependent locators.
 *
 * @see javax.tv.locator.LocatorFactory
 * @see javax.tv.locator.LocatorFactory#transformLocator
 */
public interface Locator {
	
  /**
   * Generates a canonical, string-based representation of this
   * <code>Locator</code>. The string returned may be entirely
   * platform-dependent.  If two locators have identical external
   * forms, they refer to the same resource.  However, two locators
   * that refer to the same resource may have different external
   * forms.<p>
   *
   * This method returns the canonical
   * form of the string that was used to create the Locator (via
   * <code>LocatorFactory.createLocator()</code>).  In generating
   * canonical external forms, the implementation will make its best
   * effort at resolving locators to one-to-one relationships
   * with the resources that they reference.<p>
   *
   * The result of this method can be used to create new
   * <code>Locator</code> instances as well as other types of
   * locators, such as JMF <code>MediaLocator</code>s and
   * <code>URL</code>s.
   *
   * @return A string-based representation of this Locator.
   * 
   * @see LocatorFactory#createLocator
   * @see javax.media.MediaLocator javax.media.MediaLocator 
   * @see java.net.URL */
  public String toExternalForm();
  
  /**
   *
   * Indicates whether this <code>Locator</code> has a mapping to
   * multiple transports.
   *
   * @return <code>true</code> if multiple transformations exist for
   * this <code>Locator</code>, false otherwise.  */
  public boolean hasMultipleTransformations();

  /**
   * Compares this <code>Locator</code> with the specified object for
   * equality.  The result is <code>true</code> if and only if the
   * specified object is also a <code>Locator</code> and has an
   * external form identical to the external form of this
   * <code>Locator</code>.
   *
   * @param o The object against which to compare this <code>Locator</code>.
   *
   * @return <code>true</code> if the specified object is equal to this
   * <code>Locator</code>.
   *
   * @see java.lang.String#equals(Object)
   */
  public boolean equals(Object o);

  /**
   * Generates a hash code value for this <code>Locator</code>.
   * Two <code>Locator</code> instances for which <code>Locator.equals()</code>
   * is <code>true</code> will have identical hash code values.
   *
   * @return The hash code value for this <code>Locator</code>.
   *
   * @see #equals(Object)
   */
  public int hashCode();
    
  /**
   * Returns the string used to create this locator.
   *
   * @return The string used to create this locator.
   *
   * @see LocatorFactory#createLocator
   */
  public String toString();
       
}
