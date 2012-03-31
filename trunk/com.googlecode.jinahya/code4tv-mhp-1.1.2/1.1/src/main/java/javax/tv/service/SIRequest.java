/*
 * @(#)SIRequest.java	1.12 00/10/09
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

package javax.tv.service;


/**
 * An <code>SIRequest</code> object is used to cancel a pending
 * asynchronous SI retrieval operation.  Individual asynchronous SI
 * retrieval operations are identified by unique
 * <code>SIRequest</code> objects generated at the time the operation
 * is initiated.
 *
 * @see SIRequestor */
public interface SIRequest {
	
 /**
  * Cancels a pending SI retrieval request.  If the request is still
  * pending and can be canceled then the <code>notifyFailure()</code>
  * method of the <code>SIRequestor</code> that initiated the
  * asynchronous retrieval will be called with the
  * <code>SIRequestFailureType</code> code of
  * <code>CANCELED</code>. If the request is no longer pending then no
  * action is performed.
  *
  * @return <code>true</code> if the request was pending and
  * successfully canceled; <code>false</code> otherwise.
  *
  * @see SIRequestor#notifyFailure
  * @see SIRequestFailureType#CANCELED */
  public abstract boolean cancel();
}
