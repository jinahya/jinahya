/*
 * @(#)NetworkChangeEvent.java	1.29 00/09/05
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
 * A <code>NetworkChangeEvent</code> notifies an
 * <code>NetworkChangeListener</code> of changes detected in a
 * <code>NetworkCollection</code>.  Specifically, this event
 * signals the addition, removal, or modification of a
 * <code>Network</code>.
 * 
 * @see NetworkCollection
 * @see Network
 */
public class NetworkChangeEvent extends TransportSIChangeEvent {
    
  /**
   * Constructs a <code>NetworkChangeEvent</code>.
   *
   * @param collection The network collection in which the change
   * occurred.
   *
   * @param type The type of change that occurred.
   *
   * @param n The <code>Network</code> that changed.
   */
  public NetworkChangeEvent(NetworkCollection collection,
			    SIChangeType type, Network n) {
    super(null,null,null);
  }

  /**
   * Reports the <code>NetworkCollection</code> that generated the
   * event.  It will be identical to the object returned by the
   * <code>getTransport()</code> method.
   *
   * @return The <code>NetworkCollection</code> that generated the
   * event.
   */
  public NetworkCollection getNetworkCollection() { 
     return null;
  }

  /**
   * Reports the <code>Network</code> that changed.  It will be
   * identical to the object returned by the inherited
   * <code>SIChangeEvent.getSIElement</code> method.
   *
   * @return The <code>Network</code> that changed.  */
  public Network getNetwork() {  
     return null;
  }
}
