/*
 * @(#)InsufficientResourcesException.java	1.11 00/08/06
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

package javax.tv.service.selection;

/**
 * <code>InsufficientResourcesException</code> is generated when
 * sufficient resources for an operation are not available.
 **/
public class InsufficientResourcesException extends ServiceContextException {

  /**
   * 
   * Constructs an <code>InsufficientResourcesException</code> with no
   * detail message.
   */
  public InsufficientResourcesException()
  {
	super();
  }

  /**
   *
   * Constructs an <code>InsufficientResourcesException</code> with a
   * detail message.
   *
   * @param s The detail message.
   **/
  public InsufficientResourcesException(String s)
  {
	super(null);
  }
}

