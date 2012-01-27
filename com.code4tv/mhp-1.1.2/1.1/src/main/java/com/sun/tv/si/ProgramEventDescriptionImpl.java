/*
 * @(#)ProgramEventDescriptionImpl.java	1.4 00/01/19
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

package com.sun.tv.si;

import java.util.*;
import javax.tv.locator.*;
import javax.tv.service.*;
import javax.tv.service.guide.*;
import com.sun.tv.*;

/**
 *
 * This <code>SIElement</code> provides a textual description of a
 * <code>ProgramEvent</code>. <p>
 *
 * (In ATSC PSIP, this information is obtained from the Extended Text Table;
 * in DVB SI, from the Short Event Descriptor.)
 *
 */
public class ProgramEventDescriptionImpl implements ProgramEventDescription, SIElement {

	private Date updatedTime;
	private String description;
	private ProgramEvent program = null;

	public ProgramEventDescriptionImpl(
		ProgramEvent program, String name, String description, Date updatedTime) {

		this.description = description;
		this.updatedTime = updatedTime;
		this.program = program;
	}

  /**
   * Provides a textual description of the <code>ProgramEvent</code>.
   *
   * @return A textual description of the <code>ProgramEvent</code>.
   */
  public String getProgramEventDescription() {
	return this.description;
  }

 /**
  * Returns the time when this object was last updated from data in
  * the broadcast.
  *
  * @return The date of the last update in UTC format, or <code>null</code>
  * if unknown.
  */
  public Date getUpdateTime() {
	return this.updatedTime;
  }

 /**
  * Gets the complete Locator of this SI Element. Each SI Element (such as
  * BroadcastService, ProgramEvent, etc.) in the MPEG-2 domain is
  * identified by a Locator. This identification is encapsulated by the
  * Locator object which may use a URL format, specific MPEG numbers, such
  * as network ID, etc., or other mechanisms.
  *
  * @return Locator representing this SI Element
  */
  public Locator getLocator() {
	Locator locator = (Locator)program.getLocator();
	return LocatorImpl.transformToProgramEventDescription(locator);
  }

 /**
  * Reports the service information format of this object.
  *
  * @return The service information format.
  */
	public ServiceInformationType getServiceInformationType() {
		ServiceImpl service = (ServiceImpl)program.getService();
		return service.getServiceInformationType();
	}
}
