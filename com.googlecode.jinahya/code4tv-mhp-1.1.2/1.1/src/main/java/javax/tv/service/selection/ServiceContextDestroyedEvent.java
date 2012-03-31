/*
 * @(#)ServiceContextDestroyedEvent.java	1.9 00/08/28
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

package javax.tv.service.selection;

/**
 * <code>ServiceContextDestroyedEvent</code> is generated when a
 * <code>ServiceContext</code> is destroyed via its
 * <code>destroy()</code> method.
 *
 * @see ServiceContext#destroy */

public class ServiceContextDestroyedEvent extends ServiceContextEvent {

  /**
   * Constructs the event.
   *
   * @param source The <code>ServiceContext</code> that was destroyed.
   */
  
  public ServiceContextDestroyedEvent(ServiceContext source)
  {
    super(source);
  }
}	

