/*
 * @(#)ServiceNumber.java	1.10 00/08/06
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
 * This interface is used to identify services by service (or channel)
 * numbers. The service number may represent a receiver-specific service
 * designation or a broadcaster-specific service designation delivered as a
 * private descriptor. <p>
 *
 * Service and ServiceDetails objects may optionally implement this
 * interface. <code>ServiceNumber</code> is extended by
 * <code>ServiceMinorNumber</code> to report two-part ATSC channel numbers.
 *
 * @see Service
 *
 * @see javax.tv.service.navigation.ServiceDetails
 *
 * @see ServiceMinorNumber
 *
 * @see <a href="../../../overview-summary.html#guidelines-opinterfaces">Optionally implemented interfaces</a>
 */
public interface ServiceNumber {
	
  /**
   * Reports the service number of a service.
   *
   * @return The number of the service.
   */
  public int getServiceNumber();
}
