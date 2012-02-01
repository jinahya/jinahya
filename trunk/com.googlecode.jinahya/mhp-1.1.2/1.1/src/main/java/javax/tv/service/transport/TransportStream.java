/*
 * @(#)TransportStream.java	1.11 00/08/06
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

package javax.tv.service.transport;

import javax.tv.service.SIElement;

/**
 * This interface provides information about a transport stream.
 */
public interface TransportStream extends SIElement {
	
 /**
  * Reports the ID of this transport stream.
  *
  * @return A number identifying this transport stream.
  */
  public abstract int getTransportStreamID();


 /**
  * Reports the textual name or description of this transport stream.
  *
  * @return A string representing the name of this transport stream, or
  * an empty string if no information is available.
  */
  public abstract String getDescription();

}
