/*
 * @(#)ServiceContextEvent.java	1.10 00/08/06
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
 * The parent class for service context events.
 */

public class ServiceContextEvent extends java.util.EventObject {

  /**
   * Constructs the event.
   *
   * @param source The <code>ServiceContext</code> that generated the
   * event.
   */
  public ServiceContextEvent(ServiceContext source)
  {
    super(source);
  }

  /**
   * Reports the <code>ServiceContext</code> that generated the event.
   *
   * @return The <code>ServiceContext</code> that generated the event.
   */
  public ServiceContext getServiceContext()
  {
    return (ServiceContext)getSource();
  }
}

