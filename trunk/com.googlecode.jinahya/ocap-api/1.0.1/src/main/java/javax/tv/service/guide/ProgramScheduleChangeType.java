/*
 * @(#)ProgramScheduleChangeType.java	1.3 00/10/09
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

import javax.tv.service.SIChangeType;

/**
 * This class represents types of changes to program schedules.
 *
 * @see ProgramScheduleEvent
 * @see ProgramSchedule
 */
public class ProgramScheduleChangeType extends SIChangeType {

  /**
   * Creates an <code>ProgramScheduleChangeType</code> object.
   *
   * @param name The string name of this type (e.g. "CURRENT_PROGRAM_EVENT").
   */
  protected ProgramScheduleChangeType(String name) {
    super(null);
  }

  /**
   * Provides the string name of the type.  For the type objects
   * defined in this class, the string name will be identical to the
   * class variable name.
   *
   * @return The string name of the type.
   */
  public String toString() {
	  return null; 
  }

  /**
   * <code>ProgramScheduleChangeType</code> indicating that the
   * current program event has changed.
   */
  public static final ProgramScheduleChangeType CURRENT_PROGRAM_EVENT=null;
}
