/*
 * @(#)Transport.java	1.37 00/09/22
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

import javax.tv.service.navigation.DeliverySystemType;


/**
 * This interface represents an individual content delivery mechanism.
 * A <code>Transport</code> serves as an access point for acquiring
 * information about services and their groupings.<p>
 *
 * A <code>Transport</code> may expose various types of
 * entities (e.g. bouquets, networks and/or transport streams) by
 * optionally implementing additional interfaces
 * (i.e. <code>BouquetCollection</code>,
 * <code>NetworkCollection</code>, and/or
 * <code>TransportStreamCollection</code>), depending on the particular
 * SI format used and the presence of optional elements and tables in
 * the SI data being broadcast.<p>
 *
 * @see BouquetCollection
 * @see NetworkCollection
 * @see TransportStreamCollection
 *
 * @see <a href="../../../../overview-summary.html#guidelines-opinterfaces">Optionally implemented interfaces</a>
 */
public interface Transport {
    
  /**
   * Registers a <code>ServiceDetailsChangeListener</code> to be
   * notified of changes to <code>ServiceDetails</code> that are
   * carried on this <code>Transport</code>. Subsequent notification
   * is made via <code>ServiceDetailsChangeEvent</code> with this
   * <code>Transport</code> instance as the event source and an
   * <code>SIChangeType</code> of <code>ADD</code>,
   * <code>REMOVE</code> or <code>MODIFY</code>.  Only changes to
   * <code>ServiceDetails</code> <code>sd</code> for which the caller
   * has <code>javax.tv.service.ReadPermission(sd.getLocator())</code>
   * will be reported.<p>
   * 
   * This method is only a request for notification.  No guarantee is
   * provided that the SI database will detect all, or even any, SI
   * changes or whether such changes will be detected in a timely
   * fashion.  Applications may indicate <code>ServiceDetails</code>
   * of particular interest via the method {@link
   * javax.tv.service.SIManager#registerInterest}. <p>
   * 
   * If the specified <code>ServiceDetailsChangeListener</code> is
   * already registered, no action is performed.
   *
   * @param listener An <code>ServiceDetailsChangeListener</code> to be
   * notified about changes related to <code>ServiceDetails</code>
   * carried on this <code>Transport</code>.
   *
   * @see ServiceDetailsChangeEvent
   * @see javax.tv.service.SIManager#registerInterest
   * @see javax.tv.service.ReadPermission
   */
  public void addServiceDetailsChangeListener(ServiceDetailsChangeListener
					      listener);
  
  /**
   * Called to unregister an
   * <code>ServiceDetailsChangeListener</code>.  If the specified
   * <code>ServiceDetailsChangeListener</code> is not registered, no
   * action is performed.
   *
   * @param listener A previously registered listener.  */
  public void removeServiceDetailsChangeListener(ServiceDetailsChangeListener listener);

  /**
   * Reports the type of mechanism by which this
   * <code>Transport</code> delivers content.
   *
   * @return The delivery system type of this transport.
   */
  public DeliverySystemType getDeliverySystemType();

}
