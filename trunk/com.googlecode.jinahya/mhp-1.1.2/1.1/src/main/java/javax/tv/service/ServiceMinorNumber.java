/*
 * @(#)ServiceMinorNumber.java	1.8 00/08/06
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
 * This interface extends the basic <code>ServiceNumber</code> interface to
 * provide the minor number of two-part service numbers described in
 * <em>major.minor</em> format. <p>
 *
 * Service and ServiceDetails objects may optionally implement this
 * interface. <p>
 *
 * The major number of a service is obtained from the
 * <code>ServiceNumber.getServiceNumber</code> method.
 *
 * @see Service
 *
 * @see javax.tv.service.navigation.ServiceDetails
 *
 * @see ServiceNumber#getServiceNumber
 *
 * @see <a href="../../../overview-summary.html#guidelines-opinterfaces">Optionally implemented interfaces</a>
 */
public interface ServiceMinorNumber extends ServiceNumber {


  /**
   * Reports the minor number of the service.
   *
   * @return The minor number of this service.
   */
  public int getMinorNumber();
}
