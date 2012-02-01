/*
 * @(#)CacheManager.java	1.8 99/10/18
 *
 * Copyright 2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv;

import java.util.*;
import javax.tv.service.*;
import javax.tv.locator.*;

/**
 * This class manages the SI cache. Currently, the cache is implemented as a 
 * hashtable which is keyed using locators external form strings. And the elements
 * are made up of SIElements or derivations thereof.
 */

public class CacheManager extends java.util.Hashtable {

	private static CacheManager siCache = new CacheManager();

	private static CacheManager serviceCache = new CacheManager();

	public CacheManager() {
		super();
	}

	public static CacheManager getSICache() {
		return siCache;
	}

	public static CacheManager getServiceCache() {
		return serviceCache;
	}

	public void put(Locator locator, Object object) {
		if (locator == null || locator.toExternalForm() == null)
			return;

		if (object == null) 
			return;

		this.put(locator.toExternalForm(), object);
	}	

	public Object get(Locator locator) {
		if (locator == null || locator.toExternalForm() == null)
			return null;

		return this.get(locator.toExternalForm());
	}

	public boolean containsKey(Locator locator) {
		if (locator == null || locator.toExternalForm() == null)
			return false;

		return this.containsKey(locator.toExternalForm());
	}

	public void remove(Locator locator) {
		if (locator == null || locator.toExternalForm() == null)
			return;

		this.remove(locator.toExternalForm());
	}
} 
