/*
 * @(#)PreferenceFilter.java	1.18 00/08/23
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

import javax.tv.*;
import javax.tv.service.*;
import javax.tv.locator.*;


/**
 * <code>PreferenceFilter</code> represents a
 * <code>ServiceFilter</code> based on a user preference for favorite
 * services. A <code>ServiceList</code> resulting from this filter
 * will include only user favorite services contained in the specified
 * preference.
 *
 * @see FavoriteServicesName
 * @see ServiceList
 */
public final class PreferenceFilter extends ServiceFilter {
	
   /**
   * Constructs the filter based on a particular user preference
   * for favorite services.
   *
   * @param preference A named user preference, obtained from
   * the <code>listPreferences()</code> method, representing favorite
   * Services to be included in a resulting service list.
   *
   * @throws IllegalArgumentException If the specified preference is
   * not obtainable from the <code>listPreferences()</code> method.
   *
   * @see #listPreferences
   */
  public PreferenceFilter(FavoriteServicesName preference) {
  }
  
  /**
   * Reports the available favorite service preferences which
   * can be used to create this filter.
   *
   * @return An array of preferences for favorite services.
   */
  public static FavoriteServicesName[] listPreferences() {
	return null;
  }
  
  
  /**
   * Reports the user preference used to create this filter.
   *
   * @return The user preference representing the favorite Services
   * by which the filter was constructed.
   */
  public FavoriteServicesName getFilterValue() {
	return null;
  }
  
  /**
   * Tests if the given service passes the filter.
   *
   * @param service An individual <code>Service</code> to be evaluated
   * against the filtering algorithm.
   *
   * @return <code>true</code> if <code>service</code> is part of the
   * favorite services indicated by the filter value; <code>false</code>
   * otherwise.  */
  public boolean accept(Service service) {
	return false;
  }
}
