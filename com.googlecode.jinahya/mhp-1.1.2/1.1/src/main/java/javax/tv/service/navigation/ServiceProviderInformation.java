/*
 * @(#)ServiceProviderInformation.java	1.11 00/08/06
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


/**
 * This interface is used to report additional information concerning the
 * provider of a service. ServiceDetails objects may optionally implement
 * this interface on systems that provide information concerning the
 * service provider in their specific SI format. It can be mapped to the
 * DVB Service descriptor.
 *
 * @see ServiceDetails
 *
 * @see <a href="../../../../overview-summary.html#guidelines-opinterfaces">Optionally implemented interfaces</a>
 */
public interface ServiceProviderInformation {
	
  /**
   * Returns the name of the service provider. It can be retrieved from the
   * DVB Service Descriptor or the Multilingual Service Name Descriptor.
   *
   * @return A string representing the service provider's name. It returns
   * an empty string if no provider information is available.
   */
  public abstract String getProviderName();
}
