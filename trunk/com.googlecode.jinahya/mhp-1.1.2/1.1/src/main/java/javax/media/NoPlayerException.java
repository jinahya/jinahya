/*
 * @(#)NoPlayerException.java	1.10 98/03/28
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
  * A <code>NoPlayerException</code>  is thrown when a <code>PlayerFactory</code> 
  * can't find a <code>Player</code> for a
  * particular <CODE>URL</CODE> or <CODE>MediaLocator</CODE>.
  *
  * @version 1.10, 98/03/28.
 */

public class NoPlayerException extends MediaException {

   public NoPlayerException() {
       super();
   }
    
    public NoPlayerException(String reason) {
	super(reason);
    }
}
