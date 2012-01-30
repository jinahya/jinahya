/*
 * @(#)SIChangeType.java	1.14 00/08/26
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


/**
 * This class represents types of changes to SI elements.
 *
 * @see SIChangeEvent
 * @see SIElement
 */
public class SIChangeType {

  /**
   * Creates an <code>SIChangeType</code> object.
   *
   * @param name The string name of this type (e.g. "ADD").
   */
  protected SIChangeType(String name) {
  }

  /**
   * Provides the string name of the type.  For the type objects
   * defined in this class, the string name will be identical to the
   * class variable name.
   */
  public String toString() {
	return null;
  }


  /**
   * <code>SIChangeType</code> indicating that an <code>SIElement</code>
   * has been added.
   */
  public static final SIChangeType ADD=null;

  /**
   * <code>SIChangeType</code> indicating that an <code>SIElement</code>
   * has been removed.
   */
  public static final SIChangeType REMOVE=null;

  /**
   * <code>SIChangeType</code> indicating that an <code>SIElement</code>
   * has been modified.
   */
  public static final SIChangeType MODIFY=null;
}
