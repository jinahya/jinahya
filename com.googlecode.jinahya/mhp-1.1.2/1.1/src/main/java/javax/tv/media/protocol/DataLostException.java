/*
 * @(#)DataLostException.java	1.5 00/07/27
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

package javax.tv.media.protocol;

import java.io.IOException;


/**
 * Signals that streaming data has been lost.
 *
 */
public class DataLostException extends IOException {

  /**
   * Constructs the exception with no detail message.
   *
   */
  public DataLostException() {
	super();
  }


  /**
   * Constructs the exception with the given detail message.
   * 
   * @param reason The reason for the exception.
   */
  public DataLostException(String reason) {
	super(reason);
  }
}
