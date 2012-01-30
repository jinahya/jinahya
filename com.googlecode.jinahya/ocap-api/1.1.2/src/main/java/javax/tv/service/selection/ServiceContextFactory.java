/*
 * @(#)ServiceContextFactory.java	1.28 00/10/09
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
 *
 * This class serves as a factory for the creation of
 *<code>ServiceContext</code> objects.
 */
public abstract class ServiceContextFactory {

  /**
   * Creates a <code>ServiceContextFactory</code>.
   */
  protected ServiceContextFactory() {}


  /**
   * Provides an instance of <code>ServiceContextFactory</code>.
   *
   * @return An instance of <code>ServiceContextFactory</code>.
   */
  public static ServiceContextFactory getInstance() {
	return null;
  }

  /** 
   * Creates a <code>ServiceContext</code> object.  The new
   * <code>ServiceContext</code> is created in the <em>not
   * presenting</em> state.
   *
   * <p>Due to resource restrictions,
   * implementations may limit the total number of simultaneous
   * <code>ServiceContext</code> objects.  In such a case,
   * <code>InsufficientResourcesException</code> is thrown.
   *
   * @return A new <code>ServiceContext</code> object.
   *
   * @throws InsufficientResourcesException If the receiver lacks
   * the resources to create this <code>ServiceContext</code>.
   *
   * @throws SecurityException if the caller doesn't have
   * <code>ServiceContextPermission("create", "own")</code>.
   **/
  public abstract ServiceContext createServiceContext()
    throws InsufficientResourcesException, SecurityException;


  /**
   * Provides the <code>ServiceContext</code> instances to which the
   * caller of the method is permitted access.  If the caller has
   * <code>ServiceContextPermission("access","*")</code>, then all
   * current (i.e., undestroyed) <code>ServiceContext</code> instances
   * are returned.  If the application making this call is running in
   * a <code>ServiceContext</code> and has
   * <code>ServiceContextPermission("access","own")</code>, its own
   * <code>ServiceContext</code> will be included in the returned
   * array.  If no <code>ServiceContext</code> instances are
   * accessible to the caller, a zero-length array is returned.  No
   * <code>ServiceContext</code> instances in the <em>destroyed</em>
   * state are returned by this method.
   *
   * @return An array of accessible <code>ServiceContext</code> objects.
   *
   * @see ServiceContextPermission
   */
  public abstract ServiceContext[] getServiceContexts(); 

  /**
   * Reports the <code>ServiceContext</code> in which the
   * <code>Xlet</code> corresponding to the specified
   * <code>XletContext</code> is running.  The returned
   * <code>ServiceContext</code> is the one from which the
   * <code>Service</code> carrying the <code>Xlet</code> was selected.
   *
   * @param ctx The <code>XletContext</code> of the <code>Xlet</code>
   * of interest.
   *
   * @return The <code>ServiceContext</code> in which the <code>Xlet</code>
   * corresponding to <code>ctx</code> is running.
   *
   * @throws SecurityException If the
   * <code>Xlet</code> corresponding to <code>ctx</code> does not have
   * <code>ServiceContextPermission("access", "own")</code>.
   *
   * @throws ServiceContextException If the
   * <code>Xlet</code> corresponding to <code>ctx</code> is not running
   * within a <code>ServiceContext</code>.
   */
  public abstract ServiceContext getServiceContext(javax.tv.xlet.XletContext
						   ctx)
      throws SecurityException, ServiceContextException;
}

