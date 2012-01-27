/*
 * @(#)Bouquet.java	1.14 00/08/28
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

import javax.tv.service.SIElement;

/**
 * This interface represents information about a bouquet.<p>
 *
 * A <code>Bouquet</code> object may optionally implement the
 * <code>CAIdentification</code> interface. Note that bouquets are not
 * supported in ATSC.
 * 
 * @see javax.tv.service.navigation.CAIdentification
 *
 * @see <a href="../../../../overview-summary.html#guidelines-opinterfaces">Optionally implemented interfaces</a>
 */
public interface Bouquet extends SIElement {
	
 /**
  * Reports the ID of this bouquet definition.
  *
  * @return A number identifying this bouquet
  */
  public abstract int getBouquetID();
	
 /**
  * Reports the name of this bouquet.
  *
  * @return A string representing the name of this bouquet, or an empty
  * string if the name is not available.
  */
  public abstract String getName();
}
