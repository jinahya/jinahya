/*
 * @(#)FavoriteServicesName.java	1.7 00/08/06
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
 * This interface represents the name of a preference for a set of
 * favorite services. It can be used to create a collection of
 * <code>Service</code> objects based on a user preference for
 * favorite services.
 * 
 * @see PreferenceFilter */
public interface FavoriteServicesName {
	
 /**
  * Provides a human-readable name for this favorite services preference.
  *
  * @return The name of the favorite services preference.
  */
  public String getName();
}
