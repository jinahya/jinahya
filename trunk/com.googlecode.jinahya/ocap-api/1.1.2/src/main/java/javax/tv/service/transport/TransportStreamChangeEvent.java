/*
 * @(#)TransportStreamChangeEvent.java	1.26 00/09/05
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
 * A <code>TransportStreamChangeEvent</code> notifies an
 * <code>TransportStreamChangeListener</code> of changes detected in a
 * <code>TransportStreamCollection</code>.  Specifically, this event
 * signals the addition, removal, or modification of a
 * <code>TransportStream</code>.
 *
 * @see TransportStreamCollection
 * @see TransportStream
 */
public class TransportStreamChangeEvent extends TransportSIChangeEvent {
    
  /**
   * Constructs a <code>TransportStreamChangeEvent</code>.
   *
   * @param collection The transport stream collection in which the
   * change occurred.
   *
   * @param type The type of change that occurred.
   *
   * @param ts The <code>TransportStream</code> that changed.
   */
  public TransportStreamChangeEvent(TransportStreamCollection collection,
				    SIChangeType type,
				    TransportStream ts) {
    super(null, null, null);
  }

  /**
   * Reports the <code>TransportStreamCollection</code> that generated
   * the event.  It will be identical to the object returned by the
   * <code>getTransport()</code> method.
   *
   * @return The <code>TransportStreamCollection</code> that generated
   * the event.
   */
  public TransportStreamCollection getTransportStreamCollection() {
    return null; 
  }

  /**
   * Reports the <code>TransportStream</code> that changed.  It will be
   * identical to the object returned by the inherited
   * <code>SIChangeEvent.getSIElement</code> method.
   *
   * @return The <code>TransportStream</code> that changed.  */
  public TransportStream getTransportStream() {  
     return null;
  }

}
