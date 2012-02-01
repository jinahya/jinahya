/*
 * @(#)BouquetImpl.java	1.10 00/01/19
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

/**
 * TBD
 * @note, bascially done, check if we want to implement the 
 * CAIdentification interface here.
 */

package com.sun.tv.si;

import java.util.*;
import com.sun.tv.*;
import javax.tv.locator.*;
import javax.tv.service.*;
import javax.tv.service.transport.*;

/**
 * This class implements the Bouquet interface which
 * represents information about a bouquet (a collection of
 * services which can span transport stream and network boundaries).<p>
 *
 * A <code>BouquetImpl</code> object may optionally implement the
 * <code>CAIdentification</code> interface. Note that bouquets are not
 * supported in ATSC.
 * 
 * @see javax.tv.service.navigation.CAIdentification
 */
public class BouquetImpl implements Bouquet {

private String name = null;
private int bouquetID = -1;
private Date updatedTime = null;
private Locator locator = null;
private ServiceInformationType siType;

/**
 * This constructor creates the Bouquet object, with all the
 * class variables, passed into the constructor. 
 * @see com.sun.tv.si.SIManagerImpl
 */
public BouquetImpl(
		String name,
		int bouquetID,
		ServiceInformationType siType,
		Date updatedTime) {

	this.name = name;
	this.bouquetID = bouquetID;
	this.updatedTime = updatedTime;
	this.siType = siType;
}
	
/**
 * This method returns the ID of this bouquet definition.
 *
 * @return A number identifying this bouquet
 */
public int getBouquetID() {	
	return bouquetID;
}
	
/**
 * This method returns the name of this bouquet.
 *
 * @return A string representing the name of this bouquet
 */
public String getName() {
	return this.name;
}

/**
 * Gets the complete Locator of this SI Element. Each SI Element (such as
 * BroadcastService, ProgramEvent, etc.) in the MPEG-2 domain is
 * identified by a Locator. This identification is encapsulated by the
 * Locator object which may use a URL format, specific MPEG numbers, such
 * as network ID, etc., or other mechanisms.
 *
 * @return Locator representing this SI Element
 *
 * @see LocatorImpl
 */
public Locator getLocator() {
	if (this.locator == null) {
		try {
			this.locator = LocatorFactory.getInstance().createLocator(
				LocatorImpl.BouquetProtocol + name);
		} catch (Exception e) {
			;
		}
	}
	return this.locator;
}

/**
 * Returns the time when this object was last updated from data in
 * the broadcast.
 *
 * @return The date of the last update in UTC format, or <code>null</code>
 * if unknown.
 */
public Date getUpdateTime() {
	return this.updatedTime;
}

/**
 * Reports the service information format of this object.
 *
 * @return The service information format.
 */
public ServiceInformationType getServiceInformationType() {
	return this.siType;
}
}
