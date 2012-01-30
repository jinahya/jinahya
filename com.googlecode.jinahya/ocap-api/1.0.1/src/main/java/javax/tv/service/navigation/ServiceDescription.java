/*
 * @(#)ServiceDescription.java	1.8 00/08/06
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

import javax.tv.service.SIRetrievable;


/**
 *
 * This interface provides a textual description of a
 * <code>Service</code>.
 * (In ATSC PSIP, this information is obtained from the ETT
 * associated with this service.)
 *
 */
public interface ServiceDescription extends SIRetrievable {

  /**
   * Provides a textual description of the <code>Service</code>.
   *
   * @return A textual description of the <code>Service</code>, or
   * an empty string if no description is available.
   */
  public String getServiceDescription();

}
