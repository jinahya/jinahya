/*
 * @(#)TransportSIChangeEvent.java	1.6 00/09/05
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

import javax.tv.service.*;


/**
 * An <code>TransportSIChangeEvent</code> notifies an
 * <code>SIChangeListener</code> of changes detected to the SI on a
 * <code>Transport</code>.<p>
 *
 * Subtypes <code>ServiceDetailsChangeEvent</code>,
 * <code>TransportStreamChangeEvent</code>,
 * <code>NetworkChangeEvent</code> and <code>BouquetChangeEvent</code>
 * are used to signal changes to service details, transport streams,
 * networks and bouquets, respectively.  Changes to program events are
 * signaled through <code>ProgramScheduleChangeEvent</code>.
 *
 * @see Transport
 */
public abstract class TransportSIChangeEvent extends SIChangeEvent {
    
  /**
   * Constructs an <code>TransportSIChangeEvent</code>.
   *
   * @param transport The <code>Transport</code> on which the change
   * occurred.
   *
   * @param type The type of change that occurred.
   *
   * @param e The <code>SIElement</code> that changed.
   */
  public TransportSIChangeEvent(Transport transport,
				SIChangeType type, SIElement e) {
    super(null,null,null);
  }

  /**
   * Reports the <code>Transport</code> that generated the event.  It
   * will be identical to the object returned by the
   * <code>getSource()</code> method.
   *
   * @return The <code>Transport</code> that generated the event.
   */
  public Transport getTransport() {
	  return null; 
  }
}
