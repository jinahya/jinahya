/*
 * @(#)MediaException.java	1.11 98/03/28
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.media;

/**
  * A <code>MediaException</code> indicates an unexpected error
  * condition in a JavaMedia method.
  *
  * @version 1.11, 98/03/28
 */

public class MediaException extends Exception {

    public MediaException() {
	super();
    }
    
    public MediaException(String reason) {
	super(reason);
    }
}
