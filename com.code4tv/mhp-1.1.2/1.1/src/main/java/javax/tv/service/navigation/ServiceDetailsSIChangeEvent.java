/*
 * @(#)ServiceDetailsSIChangeEvent.java	1.3 00/10/09
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
 * A <code>ServiceDetailsSIChangeEvent</code> notifies an
 * <code>SIChangeListener</code> of changes to a
 * <code>ServiceDetails</code>.
 * 
 * @see ServiceDetails
 * @see ServiceDetails
 */
public abstract class ServiceDetailsSIChangeEvent
    extends SIChangeEvent {
    
  /**
   * Constructs a <code>ServiceDetailsSIChangeEvent</code>.
   *
   * @param service The <code>ServiceDetails</code> in which the
   * change occurred.
   *
   * @param type The type of change that occurred.
   *
   * @param e The <code>SIElement</code> that changed.
   */
  public ServiceDetailsSIChangeEvent(ServiceDetails service,
				     SIChangeType type, SIElement e) {
    super(service, type, e);
  }

  /**
   * Reports the <code>ServiceDetails</code> that generated the
   * event.  It will be identical to the object returned by the
   * <code>getSource()</code> method.
   *
   * @return The <code>ServiceDetails</code> that generated the
   * event.
   */
  public ServiceDetails getServiceDetails() {
	return (ServiceDetails)super.getSource();
  }
}
