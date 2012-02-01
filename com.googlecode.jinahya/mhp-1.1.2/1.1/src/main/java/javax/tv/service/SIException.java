/*
 * @(#)SIException.java	1.9 00/08/06
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
 * The base class for exceptions related to service information.
 */
public class SIException extends Exception {

  /**
   * Constructs an <code>SIException</code> with no detail message.
   */
  public SIException() {
	super();
  }
  
  
  /**
   * Constructs an <code>SIException</code> with a detail message.
   *
   * @param reason The reason why this exception was thrown.
   */
  public SIException(String reason) {
	super(reason);
  }
}
