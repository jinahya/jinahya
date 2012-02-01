/*
 * @(#)ServiceComponentChangeEvent.java	1.2 00/09/05
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

import javax.tv.service.*;


/**
 * A <code>ServiceComponentChangeEvent</code> notifies an
 * <code>ServiceComponentChangeListener</code> of changes to a
 * <code>ServiceComponent</code> detected in a
 * <code>ServiceDetails</code>.  Specifically, this event signals the
 * addition, removal, or modification of a
 * <code>ServiceComponent</code>.
 * 
 * @see ServiceDetails
 * @see ServiceComponent
 */
public class ServiceComponentChangeEvent extends ServiceDetailsSIChangeEvent {
    
  /**
   * Constructs a <code>ServiceComponentChangeEvent</code>.
   *
   * @param service The <code>ServiceDetails</code> in which the
   * change occurred.
   *
   * @param type The type of change that occurred.
   *
   * @param c The <code>ServiceComponent</code> that changed.
   */
  public ServiceComponentChangeEvent(ServiceDetails service,
				     SIChangeType type, ServiceComponent c) {
    super(service, type, c);
  }

  /**
   * Reports the <code>ServiceComponent</code> that changed.  It will be
   * identical to the object returned by the inherited
   * <code>SIChangeEvent.getSIElement</code> method.
   *
   * @return The <code>ServiceComponent</code> that changed.  */
  public ServiceComponent getServiceComponent() {
	return (ServiceComponent)getSIElement();
  }
}
