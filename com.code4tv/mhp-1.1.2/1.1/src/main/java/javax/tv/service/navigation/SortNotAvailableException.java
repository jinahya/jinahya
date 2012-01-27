/*
 * @(#)SortNotAvailableException.java	1.11 00/08/06
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
 * This exception indicates that the requested sorting method is not
 * available for the particular <code>ServiceList</code>, for example,
 * sorting by service numbers.
 * 
 * @see ServiceList */
public class SortNotAvailableException extends SIException {

  /**
   * Constructs a <code>SortNotAvailableException</code> with no
   * detail message.
   */
  public SortNotAvailableException() {
	super();
  }
  
  
  /**
   * Constructs a <code>SortNotAvailableException</code> with a
   * detail message.
   *
   * @param reason The reason this exception was thrown.
   */
  public SortNotAvailableException(String reason) {
	super(reason);
  }  
}
