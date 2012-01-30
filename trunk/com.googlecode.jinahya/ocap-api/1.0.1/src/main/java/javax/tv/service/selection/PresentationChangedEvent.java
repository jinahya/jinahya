/*
 * @(#)PresentationChangedEvent.java	1.12 00/08/06
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
 * <code>PresentationChangedEvent</code> indicates that the content
 * being presented in the <code>ServiceContext</code> has changed.
 * <code>PresentationChangedEvent</code> is the parent class of events
 * indicating dynamic changes to the presentation of a service due to
 * interaction with the CA system.  It is generated when neither
 * <code>AlternativeContentEvent</code> nor
 * <code>NormalContentEvent</code> are applicable.<p>
 * 
 * Applications may determine the nature of the new content by
 * querying the current <code>ServiceContentHandler</code> instances
 * of the <code>ServiceContext</code>.
 *
 * @see AlternativeContentEvent
 * @see NormalContentEvent
 * @see ServiceContentHandler#getServiceContentLocators */
public class PresentationChangedEvent extends ServiceContextEvent 
{
  /**
   * Constructs the event.
   *
   * @param source The <code>ServiceContext</code> that generated the
   * event.
   */
  public PresentationChangedEvent(ServiceContext source)
  {
    super(null);
  }
}	

