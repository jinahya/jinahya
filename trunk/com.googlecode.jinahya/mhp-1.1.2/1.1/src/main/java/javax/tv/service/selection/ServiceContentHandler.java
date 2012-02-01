/*
 * @(#)ServiceContentHandler.java	1.17 00/08/26
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

package javax.tv.service.selection;

import javax.tv.locator.Locator;


/**
 *
 * A <code>ServiceContentHandler</code> represents a mechanism for
 * presenting, processing or playing portions of a service.  A single
 * <code>ServiceContentHandler</code> may handle one or more
 * constituent parts of a service, as represented by one or more
 * locators to those parts.  Each locator reported by a
 * <code>ServiceContentHandler</code> refers either to an individual
 * service component or to content within a service component (such as
 * an Xlet).
 *
 * @see ServiceMediaHandler
 */
public interface ServiceContentHandler {

  /**
   * Reports the portions of the service on which this handler operates.
   *
   * @return An array of locators representing the portions of the
   * service on which this handler operates.
   *
   * @see ServiceContext#select(Locator[] components)
   */
  public Locator[] getServiceContentLocators();
}

