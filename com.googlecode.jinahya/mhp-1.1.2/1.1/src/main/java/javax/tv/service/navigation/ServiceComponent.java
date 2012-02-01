/*
 * @(#)ServiceComponent.java	1.18 00/09/19
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

import javax.tv.service.*;


/**
 * This interface represents an abstraction of an elementary
 * stream. It provides information about individual components of a
 * service.  Generally speaking, a service component carries content
 * such as media (e.g. as audio or video) or data.  Content within a
 * <code>ServiceComponent</code> may include <code>Xlet</code>s.
 *
 * @see ServiceDetails#retrieveComponents
 * @see javax.tv.service.guide.ProgramEvent#retrieveComponents
 */
public interface ServiceComponent extends SIElement {
	
 /**
  * Returns a name associated with this component. The Component Descriptor
  * (DVB) or Component Name Descriptor (ATSC) may be used if present. A
  * generic name (e.g., "video", "first audio", etc.) may be used otherwise.
  *
  * @return A string representing the component name or an empty string
  * if no name can be associated with this component.
  */
  public abstract String getName();
	
  /**
   * Identifies the language used for the elementary stream. The
   * associated language is indicated using a language code.  This is
   * typically a three-character language code as specified by ISO
   * 639.2/B, but the code may be system-dependent.
   *
   * @return A string representing a language code defining the
   * language associated with this component.  An empty string is
   * returned when there is no language associated with this component.  */
  public abstract String getAssociatedLanguage();
	
 /**
  * Provides the stream type of this component. (For example, "video",
  * "audio", etc.)
  *
  * @return Stream type of this component.  */
  public abstract StreamType getStreamType();

  /**
   * Provides the <code>Service</code> object to which this
   * <code>ServiceComponent</code> belongs. The result may be
   * <code>null</code> if the Service cannot be determined.
   *
   * @return The <code>Service</code> to which this
   * <code>ServiceComponent</code> belongs.  */
  public abstract Service getService();
}
