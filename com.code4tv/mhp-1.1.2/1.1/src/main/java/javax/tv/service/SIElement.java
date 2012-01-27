/*
 * @(#)SIElement.java	1.18 00/10/09
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

package javax.tv.service;

import javax.tv.locator.Locator;

/**
 * The base interface of elements provided by the SI database.
 * <code>SIElement</code> objects represent immutable <em>copies</em>
 * of the service information data contained in the SI database.  If
 * the information represented by an <code>SIElement</code> <em>E</em>
 * changes in the database, <em>E</em> will not be changed.  The value
 * of the <code>SIElement</code>'s locator (obtained by the
 * <code>getLocator()</code> method) will remain unchanged in this
 * case; the locator may be used to retrieve a copy of the SI element
 * with the new data.  Two <code>SIElement</code> objects retrieved
 * from the SI database using the same input <code>Locator</code> at
 * different times will report <code>Locator</code> objects that are
 * equal according to <code>Locator.equal()</code>.  However, the
 * <code>SIElement</code> objects themselves will not be
 * <code>equal()</code> if the corresponding data changed in the SI
 * database between the times of their respective retrievals.
 *
 * @see #getLocator
 * @see SIManager#retrieveSIElement
 */
public interface SIElement extends SIRetrievable {
	
 /**
  * Reports the <code>Locator</code> of this <code>SIElement</code>.
  *
  * @return Locator The locator referencing this
  * <code>SIElement</code> */
  public Locator getLocator();

  /**
   * Tests two <code>SIElement</code> objects for equality.  Returns
   * <code>true</code> if and only if:
   * <ul>
   * <li><code>obj</code>'s class is the
   * same as the class of this <code>SIElement</code>, and<p>
   * <li><code>obj</code>'s <code>Locator</code> is equal to
   * the <code>Locator</code> of this object (as reported by
   * <code>SIElement.getLocator()</code>, and<p>
   * <li><code>obj</code> and this object encapsulate identical data.
   * </ul>
   *
   * @param obj The object against which to test for equality.
   *
   * @return <code>true</code> if the two <code>SIElement</code> objects
   * are equal; <code>false</code> otherwise.
   */
  public boolean equals(Object obj);

  /**
   * Reports the hash code value of this <code>SIElement</code>.  Two
   * <code>SIElement</code> objects that are equal will have identical
   * hash codes.
   *
   * @return The hash code value of this <code>SIElement</code>.
   */
  public int hashCode();
  
  /**
   * Reports the SI format in which this <code>SIElement</code> was
   * delivered.
   * 
   * NOTE: In the case where an SI format is in use which is not encoded as one of the set of values defined as
* constants in the class ServiceInformationType, the value returned may be outside this set of values.
   * 
   * @return The SI format in which this SI element was delivered.
   */
  public ServiceInformationType getServiceInformationType();
}
