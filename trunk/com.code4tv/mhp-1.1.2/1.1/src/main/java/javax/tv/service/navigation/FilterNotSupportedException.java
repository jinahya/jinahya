/*
 * @(#)FilterNotSupportedException.java	1.9 00/08/06
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

package javax.tv.service.navigation;

import javax.tv.service.SIException;


/**
 * This exception indicates that the specified <code>ServiceFilter</code> is
 * not supported.
 *
 * @see javax.tv.service.navigation.ServiceFilter
 */
public class FilterNotSupportedException extends SIException {

  /**
   * Constructs a <code>FilterNotSupportedException</code> with no
   * detail message.
   */
  public FilterNotSupportedException() {
	super();
  }
  
  
  /**
   * Constructs a <code>FilterNotSupportedException</code> with a
   * detail message.
   *
   * @param reason The reason why this exception was thrown.
   */
  public FilterNotSupportedException(String reason) {
	super(reason);
  }
}
