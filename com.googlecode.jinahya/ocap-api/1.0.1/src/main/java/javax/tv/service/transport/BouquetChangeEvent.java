/*
 * @(#)BouquetChangeEvent.java	1.26 00/09/05
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
 * A <code>BouquetChangeEvent</code> notifies an
 * <code>BouquetChangeListener</code> of changes detected in a
 * <code>BouquetCollection</code>.  Specifically, this event
 * signals the addition, removal, or modification of a
 * <code>Bouquet</code>.
 *
 * @see BouquetCollection
 *@see Bouquet
 */
public class BouquetChangeEvent extends TransportSIChangeEvent {

  /**
   * Constructs a <code>BouquetChangeEvent</code>.
   *
   * @param collection The <code>BouquetCollection</code> in which the
   * change occurred.
   *
   * @param type The type of change that occurred.
   *
   * @param b The <code>Bouquet</code> that changed.
   */
  public BouquetChangeEvent(BouquetCollection collection,
			    SIChangeType type, Bouquet b) {
    super(null,null,null);
  }

  /**
   * Reports the <code>BouquetCollection</code> that generated the
   * event.  It will be identical to the object returned by the
   * <code>getTransport()</code> method.
   *
   * @return The <code>BouquetCollection</code> that generated the
   * event.
   */
  public BouquetCollection getBouquetCollection() { 
    return null; 
  }

  /**
   * Reports the <code>Bouquet</code> that changed.  It will be
   * identical to the object returned by the inherited
   * <code>SIChangeEvent.getSIElement</code> method.
   *
   * @return The <code>Bouquet</code> that changed.  */
  public Bouquet getBouquet() { 
     return null;
  }
}
