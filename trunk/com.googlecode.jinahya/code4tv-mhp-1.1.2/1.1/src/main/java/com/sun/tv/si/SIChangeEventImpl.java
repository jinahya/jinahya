/*
 * @(#)SIChangeEventImpl.java	1.14 00/01/10
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

import javax.tv.service.*;


/**
 *
 * SIChangeEventImpl objects are sent to SIChangeListeners to signal
 * detected changes in the SI Database.<p>
 *
 * Note that while the SI database may detect changes, notification of
 * which specific <code>SIElement</code> has changed is not guaranteed.
 */
public class SIChangeEventImpl extends SIChangeEvent {

  /**
   * Constructs an SIChangeEventImpl object.
   *
   * @param source The SI entity in which the change occurred.
   *
   * @param type The type of change that occurred.
   *
   * @param e The SIElement that changed, or <code>null</code> if this
   * is unknown.
   */
  public SIChangeEventImpl(Object source, SIChangeType type, SIElement e) {
	super(source, type, e);
  }
  
}
