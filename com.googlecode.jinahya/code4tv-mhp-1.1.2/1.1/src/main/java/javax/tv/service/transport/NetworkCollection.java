/*
 * @(#)NetworkCollection.java	1.31 00/09/27
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
import javax.tv.locator.*;


/**
 * This interface represents a collection of networks on a
 * <code>Transport</code>.  This information is carried in the DVB SI
 * NIT or US Cable SI (A56) NIT tables.
 * <code>NetworkCollection</code> may be optionally implemented by
 * <code>Transport</code> objects, depending on the SI data carried on
 * that transport.
 *
 * @see <a href="../../../../overview-summary.html#guidelines-opinterfaces">Optionally implemented interfaces</a> */
public interface NetworkCollection extends Transport {
	
  /**
   * Retrieves the specified <code>Network</code> from the collection.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param locator Locator referencing the <code>Network</code> of interest.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid network.
   *
   * @throws SecurityException If the caller does not have
   * <code>javax.tv.service.ReadPermission(locator)</code>.
   *
   * @see Network
   * @see javax.tv.service.ReadPermission
   */
  public abstract SIRequest retrieveNetwork(Locator locator,
					    SIRequestor requestor)
      throws InvalidLocatorException, SecurityException;
  
  /**
   * Retrieves an array of all the <code>Network</code> objects in
   * this <code>NetworkCollection</code>.  The array will only contain
   * <code>Network</code> instances <code>n</code> for which the
   * caller has
   * <code>javax.tv.service.ReadPermission(n.getLocator())</code>. If
   * no <code>Network</code> instances meet this criteria, this method
   * will result in an <code>SIRequestFailureType</code> of
   * <code>DATA_UNAVAILABLE</code>.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @see Network
   * @see javax.tv.service.ReadPermission
   */
  public abstract SIRequest retrieveNetworks(SIRequestor requestor);

  /**
   * Registers a <code>NetworkChangeListener</code> to be notified of
   * changes to a <code>Network</code> that is part of this
   * <code>NetworkCollection</code>. Subsequent notification is made
   * via <code>NetworkChangeEvent</code> with this
   * <code>NetworkCollection</code> as the event source and an
   * <code>SIChangeType</code> of <code>ADD</code>,
   * <code>REMOVE</code> or <code>MODIFY</code>.  Only changes to
   * <code>Network</code> instances <code>n</code> for which the
   * caller has
   * <code>javax.tv.service.ReadPermission(n.getLocator())</code> will
   * be reported.<p>
   * 
   * This method is only a request for notification.  No guarantee is
   * provided that the SI database will detect all, or even any, SI
   * changes or whether such changes will be detected in a timely
   * fashion.<p>
   * 
   * If the specified <code>NetworkChangeListener</code> is
   * already registered, no action is performed.
   *
   * @param listener A <code>NetworkChangeListener</code> to be
   * notified about changes related to <code>Network</code>
   * carried on this <code>Transport</code>.
   *
   * @see NetworkChangeEvent
   * @see javax.tv.service.ReadPermission
   **/
  public abstract void addNetworkChangeListener(NetworkChangeListener listener);

  /**
   * Called to unregister an
   * <code>NetworkChangeListener</code>.  If the specified
   * <code>NetworkChangeListener</code> is not registered, no
   * action is performed.
   *
   * @param listener A previously registered listener.  */
  public abstract void removeNetworkChangeListener(NetworkChangeListener listener);

}
