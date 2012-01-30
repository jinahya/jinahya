/*
 * @(#)ProgramScheduleEvent.java	1.33 00/09/05
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

import javax.tv.service.*;


/**
 * A <code>ProgramScheduleEvent</code> notifies an
 * <code>ProgramScheduleListener</code> of changes to program events
 * detected in a <code>ProgramSchedule</code>.  Specifically, this
 * event signals the addition, removal, or modification of a
 * <code>ProgramEvent</code> in a <code>ProgramSchedule</code>, or a
 * change to the <code>ProgramEvent</code> that is current.<p>
 *
 * The class <code>ProgramScheduleChangeType</code> defines the kinds
 * of changes reported by <code>ProgramScheduleEvent</code>.  A
 * <code>ProgramScheduleChangeType</code> of
 * <code>CURRENT_PROGRAM_EVENT</code> indicates that the current
 * <code>ProgramEvent</code> of a <code>ProgramSchedule</code> has
 * changed in identity.
 *
 * @see ProgramScheduleListener
 * @see ProgramScheduleChangeType
 */
public class ProgramScheduleEvent extends SIChangeEvent {

  /**
   * Constructs a <code>ProgramScheduleEvent</code>.
   *
   * @param schedule The schedule in which the change occurred.
   *
   * @param type The type of change that occurred.
   *
   * @param e The <code>ProgramEvent</code> that changed.
   */
  public ProgramScheduleEvent(ProgramSchedule schedule,
			      SIChangeType type,
			      ProgramEvent e) {
    super(null,null,null);
  }

  /**
   * Reports the <code>ProgramSchedule</code> that generated the
   * event.  The object returned will be identical to the object
   * returned by the inherited <code>EventObject.getSource()</code>
   * method.
   *
   * @return The <code>ProgramSchedule</code> that generated the event.
   *
   * @see java.util.EventObject#getSource
   **/
  public ProgramSchedule getProgramSchedule() {
	  return null; 
  }

  /**
   * Reports the <code>ProgramEvent</code> that changed.  If the
   * <code>ProgramScheduleChangeType</code> is
   * <code>CURRENT_PROGRAM_EVENT</code>, the <code>ProgramEvent</code>
   * that became current will be returned.  The object returned will
   * be identical to the object returned by inherited
   * <code>SIEvent.getSIElement</code> method.
   *
   * @return The <code>ProgramEvent</code> that changed.
   *
   * @see javax.tv.service.SIChangeEvent#getSIElement
   */
  public ProgramEvent getProgramEvent() {
	  return null; 
  }
}
