/*
 * @(#)ServiceContextException.java	1.10 00/08/06
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
 * The base class for exceptions related to service contexts.
 */

public class ServiceContextException extends java.lang.Exception {

  /**
   * Constructs a <code>ServiceContextException</code> with no detail message.
   */
  public ServiceContextException()
  {
	super();
  }


  /**
   * Constructs a <code>ServiceContextException</code> with a detail message.
   *
   * @param reason The reason this exception was thrown.
   */
  public ServiceContextException(String reason)
  {
	super(reason);
  }
}

