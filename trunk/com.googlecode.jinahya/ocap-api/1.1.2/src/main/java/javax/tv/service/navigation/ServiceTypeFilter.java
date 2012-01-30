/*
 * @(#)ServiceTypeFilter.java	1.16 00/08/06
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

import javax.tv.service.*;


/** 
 * <code>ServiceTypeFilter</code> represents a
 * <code>ServiceFilter</code> based on a particular
 * <code>ServiceType</code>.  A <code>ServiceList</code> resulting
 * from this filter will include only <code>Service</code> objects of
 * the specified service type.
 *
 * @see ServiceType
 * @see ServiceList */
public final class ServiceTypeFilter extends ServiceFilter {
	
   /**
   * Constructs the filter based on a particular <code>ServiceType</code>.
   *
   * @param type A <code>ServiceType</code> object indicating the type
   * of services to be included in a resulting service list.
   */
  public ServiceTypeFilter(ServiceType type) {
  }
  
  /**
   * Reports the <code>ServiceType</code> used to create this filter.
   *
   * @return The <code>ServiceType</code> used to create this filter.
   */
  public ServiceType getFilterValue() {
	return null;
  }
  
  
  /**
   * Tests if the given service passes the filter.
   *
   * @param service An individual <code>Service</code> to be evaluated
   * against the filtering algorithm.
   *
   * @return <code>true</code> if <code>service</code> is of the type
   * indicated by the filter value; <code>false</code> otherwise.  */
  public boolean accept(Service service) {
	return false;
  }
}
