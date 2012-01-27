/*
 * @(#)LocatorFilter.java	1.17 00/08/06
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

import javax.tv.locator.*;
import javax.tv.service.*;


/**
 * <code>LocatorFilter</code> represents a <code>ServiceFilter</code>
 * based on a set of locators.  A <code>ServiceList</code> resulting
 * from this filter will include only services matching the specified
 * locators.
 *
 * @see Locator
 * @see ServiceList */
public final class LocatorFilter extends ServiceFilter {
	
   private Locator[] locators;

  /**
   * Constructs the filter based on a set of locators.
   *
   * @param locators An array of locators representing services
   * to be included in a resulting <code>ServiceList</code>.
   *
   * @throws InvalidLocatorException If one of the given
   * <code>locators</code> does not reference a valid
   * <code>Service</code>.
   **/
	
  public LocatorFilter(Locator[] locators) throws InvalidLocatorException {
	if (locators == null) {
		throw new NullPointerException("LocatorFilter: locators = null");
	}

	// TBD add locator validate check;
        // here is a fix for this one (bug ID 4337291)

	SIManager manager = SIManager.createInstance();
	if (manager != null) {
		for (int i = 0; i < locators.length; i++) {
			try {
				if (manager.getService(locators[i]) == null) {
					throw new InvalidLocatorException(locators[i]);
				}
			} catch (Exception e) {
				throw new InvalidLocatorException(locators[i]);
			}
		}
	}

	this.locators = locators; // Should we be cloning this array?
  }
  
  /**
   * Reports the locators used to create this filter.
   *
   * @return The array of locators used to create this filter.
   */
  public Locator[] getFilterValue() {
	return this.locators;
  }
  
  
  /**
   * Tests if the given service passes the filter.
   *
   * @param service An individual <code>Service</code> to be evaluated
   * against the filtering algorithm.
   *
   * @return <code>true</code> if <code>service</code> belongs to the
   * set of locators indicated by the filter value; <code>false</code>
   * otherwise.  */
  public boolean accept(Service service) {
	Locator theLocator = service.getLocator();
	if (theLocator == null) {
		throw new NullPointerException("accept: service locator == null");
	}
		
	for (int i = 0; i < locators.length; i++) {
		if (theLocator == locators[i]) { // TBD good test?
			return true;
		}
	}
	return false;
  }
}
