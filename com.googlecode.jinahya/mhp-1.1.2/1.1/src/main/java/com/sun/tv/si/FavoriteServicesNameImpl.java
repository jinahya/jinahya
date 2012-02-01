/*
 * @(#)FavoriteServicesNameImpl.java	1.6 00/01/08
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

package com.sun.tv.si;

import javax.tv.service.navigation.*;

/**
 * This class represents the name of a preference for a set of
 * favorite services. It can be used to create a collection of
 * Services based on a user preference for favorite services.
 * 
 * @see PreferenceFilter
 */
public class FavoriteServicesNameImpl implements FavoriteServicesName {

String name = null;

public FavoriteServicesNameImpl(String name) {
	this.name = name;
}
	
/**
 * Provides a human-readable name for this favorite services preference.
 *
 * @return The name of the favorite services preference.
 */
public String getName() {
	return name;
}
}
