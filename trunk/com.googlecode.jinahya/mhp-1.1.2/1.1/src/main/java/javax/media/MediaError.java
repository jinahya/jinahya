/*
 * @(#)MediaError.java	1.13 98/03/28
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
  * A <code>MediaError</code> indicates an error condition that
  * occurred through incorrect usage of the API.
  * You should not check for <code>MediaErrors</code>.
 * @version 1.13, 98/03/28.
 */

public class MediaError extends Error {

    public MediaError() {
	super();
    }
    
    public MediaError(String reason) {
       super(reason);
    }
}
