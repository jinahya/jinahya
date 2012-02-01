/*
 * @(#)BouquetCollection.java	1.34 00/09/27
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
 * This interface represents a collection of bouquets on a
 * <code>Transport</code>.  In DVB SI, this information is contained
 * in the BAT tables.  <code>BouquetCollection</code> may be
 * optionally implemented by <code>Transport</code> objects, depending
 * on the SI data carried on that transport.
 *
 * @see <a href="../../../../overview-summary.html#guidelines-opinterfaces">Optionally implemented interfaces</a> */
public interface BouquetCollection extends Transport {
	
  /**
   * Retrieves the specified <code>Bouquet</code> from the collection.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param locator A <code>Locator</code> referencing the
   * <code>Bouquet</code> of interest.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid bouquet.
   *
   * @throws SecurityException If the caller does not have
   * <code>javax.tv.service.ReadPermission(locator)</code>.
   *
   * @see Bouquet
   * @see javax.tv.service.ReadPermission
   */
  public abstract SIRequest retrieveBouquet(Locator locator,
					    SIRequestor requestor)
      throws InvalidLocatorException, SecurityException;
	
  /**
   *
   * Retrieves an array of all the <code>Bouquet</code> objects in
   * this <code>BouquetCollection</code>.  This array will only contain
   * <code>Bouquet</code> instances <code>b</code> for which the caller has
   * <code>javax.tv.service.ReadPermission(b.getLocator())</code>.  If
   * no <code>Bouquet</code> instances meet this criteria, this method
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
   * @see Bouquet
   * @see javax.tv.service.ReadPermission
   */
  public abstract SIRequest retrieveBouquets(SIRequestor requestor);

  /**
   * Registers a <code>BouquetChangeListener</code> to be notified of
   * changes to a <code>Bouquet</code> that is part of this
   * <code>BouquetCollection</code>. Subsequent notification is made
   * via <code>BouquetChangeEvent</code> with this
   * <code>BouquetCollection</code> as the event source and an
   * <code>SIChangeType</code> of <code>ADD</code>,
   * <code>REMOVE</code> or <code>MODIFY</code>.  Only changes to
   * <code>Bouquet</code> instances <code>b</code> for which the
   * caller has
   * <code>javax.tv.service.ReadPermission(b.getLocator())</code> will
   * be reported.<p>
   * 
   * This method is only a request for notification.  No guarantee is
   * provided that the SI database will detect all, or even any, SI
   * changes or whether such changes will be detected in a timely
   * fashion.<p>
   * 
   * If the specified <code>BouquetChangeListener</code> is
   * already registered, no action is performed.
   *
   * @param listener A <code>BouquetChangeListener</code> to be
   * notified about changes related to <code>Bouquet</code>
   * carried on this <code>Transport</code>.
   *
   * @see BouquetChangeEvent 
   * @see javax.tv.service.ReadPermission
   **/
  public abstract void addBouquetChangeListener(BouquetChangeListener listener);

  /**
   * Called to unregister an
   * <code>BouquetChangeListener</code>.  If the specified
   * <code>BouquetChangeListener</code> is not registered, no
   * action is performed.
   *
   * @param listener A previously registered listener.  */
  public abstract void removeBouquetChangeListener(BouquetChangeListener listener);

}
