/*
 * @(#)ServiceFilter.java	1.17 00/08/06
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

import javax.tv.service.Service;


/**
 * This class represents a set filtering criteria used to generate a
 * <code>ServiceList</code>.  <code>ServiceFilter</code> is extended
 * to create concrete filters based on various criteria.  Applications
 * may also extend this class to define custom filters, although
 * custom filters may not be supported on certain filtering
 * operations.
 *
 * @see ServiceList */
public abstract class ServiceFilter {
	
  /**
   * Constructs the filter.
   */
  protected ServiceFilter() {}
  
  /**
   * Tests if a particular service passes this filter. 
   * Subtypes of <code>ServiceFilter</code> override this method to
   * provide the logic for a filtering operation on individual
   * <code>Service</code> objects.
   *
   * @param service A <code>Service</code> to be evaluated
   * against the filtering algorithm.
   *
   * @return <code>true</code> if <code>service</code> satisfies the
   * filtering algorithm; <code>false</code> otherwise.  */
  public abstract boolean accept(Service service);
}
