/*
 * @(#)ServiceDetailsChangeEvent.java	1.3 00/09/05
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

package javax.tv.service.transport;

import javax.tv.service.SIChangeType;
import javax.tv.service.navigation.ServiceDetails;


/**
 * A <code>ServiceDetailsChangeEvent</code> notifies an
 * <code>ServiceDetailsChangeListener</code> of changes detected to a
 * <code>ServiceDetails</code> on a <code>Transport</code>.
 * Specifically, this event signals the addition, removal, or
 * modification of a <code>ServiceDetails</code>.
 * 
 * @see Transport
 * @see ServiceDetails
 */
public class ServiceDetailsChangeEvent extends TransportSIChangeEvent {

  /**
   * Constructs a <code>ServiceDetailsChangeEvent</code>.
   *
   * @param transport The <code>Transport</code> on which the change
   * occurred.
   *
   * @param type The type of change that occurred.
   *
   * @param s The <code>ServiceDetails</code> that changed.
   */
  public ServiceDetailsChangeEvent(Transport transport,
				   SIChangeType type, ServiceDetails s) {
    	super(null,null,null);
  }

  /**
   * Reports the <code>ServiceDetails</code> that changed.  It will be
   * identical to the object returned by the inherited
   * <code>SIChangeEvent.getSIElement</code> method.
   *
   * @return The <code>ServiceDetails</code> that changed.  */
  public ServiceDetails getServiceDetails() {
	 return null;
  }
}
