/*
 * @(#)ProgramEventDescription.java	1.6 00/08/06
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

package javax.tv.service.guide;

import javax.tv.service.SIRetrievable;


/**
 * This <code>SIElement</code> provides a textual description of a
 * <code>ProgramEvent</code>.  In ATSC PSIP, this information is
 * obtained from the Extended Text Table; in DVB SI, from the Short
 * Event Descriptor.)
 * */
public interface ProgramEventDescription extends SIRetrievable {

  /**
   * Provides a textual description of the <code>ProgramEvent</code>.
   *
   * @return A textual description of the <code>ProgramEvent</code>,
   * or an empty string if no description is available.
   */
  public String getProgramEventDescription();

}
