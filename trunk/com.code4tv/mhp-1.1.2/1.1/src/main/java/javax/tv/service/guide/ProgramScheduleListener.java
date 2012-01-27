/*
 * @(#)ProgramScheduleListener.java	1.1 00/08/26
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

import javax.tv.service.SIChangeListener;


/**
 * This interface is implemented by applications wishing to receive
 * notification of changes to <code>ProgramSchedule</code> data.
 */
public interface ProgramScheduleListener extends SIChangeListener {
	
 /**
  * Notifies the <code>ProgramScheduleListener</code> of a
  * change to a <code>ProgramSchedule</code>.
  *
  * @param event A <code>ProgramScheduleEvent</code>
  * describing what changed and how.
  */
  public abstract void notifyChange(ProgramScheduleEvent event);
}
