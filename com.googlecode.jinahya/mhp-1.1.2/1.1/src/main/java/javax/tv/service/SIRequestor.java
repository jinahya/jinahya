/*
 * @(#)SIRequestor.java	1.16 00/08/06
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
 * This interface is implemented by application classes to receive the
 * results of asynchronous SI retrieval requests. The
 * <code>SIRequestor</code> registers itself at the time of the
 * asynchronous call for a single request and is automatically
 * unregistered when the request is completed.  Applications can
 * disambiguate retrieval operations by registering a unique
 * <code>SIRequestor</code> for each retrieval request.<p>
 *
 * The asynchronous SI retrieval mechanisms invoke the methods of this
 * interface using system threads that are guaranteed to not hold
 * locks on application objects.
 * */
public interface SIRequestor {
	
  /**
   * Notifies the <code>SIRequestor</code> of successful asynchronous
   * SI retrieval.
   * 
   * @param result The previously requested data.
   */
  public void notifySuccess(SIRetrievable[] result);

  /**
   * Notifies the <code>SIRequestor</code> of unsuccessful asynchronous
   * SI retrieval.
   * 
   * @param reason The reason why the asynchronous request failed.
   */
  public void notifyFailure(SIRequestFailureType reason);
}
