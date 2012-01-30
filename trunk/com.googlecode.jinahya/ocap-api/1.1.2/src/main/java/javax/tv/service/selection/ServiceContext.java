/*
 * @(#)ServiceContext.java	1.49 00/10/09
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

import javax.tv.locator.*;
import javax.tv.service.Service;


/**
 * A <code>ServiceContext</code> represents an environment in which
 * services are presented in a broadcast receiver.  Applications may
 * use <code>ServiceContext</code> objects to select new services to
 * be presented.  Content associated with a selected service is
 * presented by one or more <code>ServiceContentHandler</code> objects
 * managed by the <code>ServiceContext</code>.<p>
 *
 * A <code>ServiceContext</code> can exist in four states -
 * <em>presenting</em>, <em>not presenting</em>, <em>presentation
 * pending</em> and <em>destroyed</em>. The initial state is
 * <em>not presenting</em>. <p>
 *
 * The <code>select()</code> method can be called from any state
 * except <em>destroyed</em>. Assuming no exception is thrown, the
 * service context then enters the <em>presentation pending</em>
 * state. No event is generated on this state transition. If a call to
 * <code>select()</code> completes successfully, either a
 * <code>NormalContentEvent</code> or an
 * <code>AlternativeContentEvent</code> is generated and the
 * <code>ServiceContext</code> moves into the <em>presenting</em>
 * state.<p>
 *
 * If the selection operation fails, a
 * <code>SelectionFailedEvent</code> is generated.  If the
 * <code>select()</code> method is called during the <em>presentation
 * pending</em> state, a <code>SelectionFailedEvent</code> with reason
 * code <code>INTERRUPTED</code> is generated, and the
 * <code>ServiceContext</code> will proceed in the <em>presentation
 * pending</em> state for the most recent <code>select()</em> call.
 * Otherwise, if the state before the failed select operation was
 * <em>not presenting</em>, the <code>ServiceContext</code> will
 * return to that state and a <code>PresentationTerminatedEvent</code>
 * be generated. If the state before the failed select operation was
 * <em>presenting</em>, it will attempt to return to that previous
 * state, which can result in a <code>NormalContentEvent</code> or
 * <code>AlternativeContentEvent</code> if this is possible, or a
 * <code>PresentationTerminatedEvent</code> if it is not possible.<p>
 *
 * The <em>not presenting</em> state is entered due to service
 * presentation being stopped which is reported by a
 * <code>PresentationTerminatedEvent</code>. The stopping of service
 * presentation can be initiated by an application calling the
 * <code>stop()</code> method or because some change in the environment
 * makes continued presentation impossible.<p>
 *
 * The <em>destroyed</em> state is entered by calling the
 * <code>destroy()</code> method, and is signaled by a
 * <code>ServiceContextDestroyedEvent</code>. Once this state is
 * entered, the <code>ServiceContext</code> can no longer be used for
 * any purpose.  A destroyed <code>ServiceContext</code> will be
 * eligible for garbage collection once all references to it by any
 * applications have been removed.<p>
 *
 * Note that the ability to select a service for presentation does not
 * imply exclusive rights to the resources required for that
 * presentation.  Subsequent attempts to select the same service may
 * fail.<p>
 *
 * Applications may also use this interface to register for events
 * associated with <code>ServiceContext</code> state changes.<p>
 *
 * @see javax.tv.service.Service
 * @see ServiceContentHandler
 *
 * @see NormalContentEvent
 * @see AlternativeContentEvent
 * @see SelectionFailedEvent
 * @see PresentationTerminatedEvent
 * @see ServiceContextDestroyedEvent
 * @see ServiceContextListener */

public interface ServiceContext {

  /**
   * Selects a service to be presented in this
   * <code>ServiceContext</code>.  If the <code>ServiceContext</code>
   * is already presenting content, the new selection replaces the
   * content being presented. If the <code>ServiceContext</code> is
   * not presenting, successful conclusion of this method results in
   * the <code>ServiceContext</code> entering the <em>presenting</em>
   * state. <p>
   *
   * This method is asynchronous and successful completion of the
   * selection is notified by either a <code>NormalContentEvent</code>
   * or a <code>AlternativeContentEvent</code>.  If an exception is
   * thrown when this method is called, the state of the service
   * context does not change. In such a case, any service being
   * presented before this method was called will continue to be
   * presented.<p>
   *
   * If the selection process fails after this method returns, a
   * <code>SelectionFailedEvent</code> will be generated. In this
   * case, the system will attempt to return to presenting the
   * original service (if any). If this is not possible (due, for
   * example, to issues such as tuning or CA) the
   * <code>ServiceContext</code> will enter the <em>not
   * presenting</em> state and a
   * <code>PresentationTerminatedEvent</code> will be generated.<p>
   *
   * If the <code>ServiceContext</code> is currently presenting a
   * service and components of the current service are also to be
   * presented in the newly selected service, these components will
   * continue to be presented and will not be restarted.  If the
   * calling application is not a part of the newly selected service
   * and the application lifecycle policy on the receiver dictates
   * that the calling application be terminated, termination of the
   * application will be affected through the application lifecycle
   * API.<p>
   *
   * If the provided <code>Service</code> is transport-independent,
   * this method will resolve the <code>Service</code> to a
   * transport-dependent <code>Service</code> before performing the
   * selection. The actual <code>Service</code> selected will be
   * reported through the <code>getService()</code> method.<p>
   *
   * Successful completion of a select operation using this method
   * provides <code>ServiceContentHandler</code> instances for all
   * components that are signaled as "auto-start" in the selected
   * service.  Upon entering the <em>presenting</em> state, these
   * <code>ServiceContentHandler</code> instances will have begun
   * presenting their respective content;
   * <code>ServiceMediaHandler</code> instances will be in the
   * <em>started</em> state.
   * 
   * @param selection The <code>Service</code> the service to be
   * selected.
   *
   * @throws SecurityException If the caller owns this
   * <code>ServiceContext</code> but does not have
   * <code>SelectPermission(selection.getLocator(), "own")</code>, or if 
   * the caller
   * does not own this <code>ServiceContext</code> and does not have
   * <code>SelectPermission(selection.getLocator(), "*")</code>.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   *
   * @see NormalContentEvent
   * @see AlternativeContentEvent
   * @see SelectionFailedEvent
   * @see PresentationTerminatedEvent
   * @see javax.tv.service.Service
   * @see ServiceContext#getService
   * @see ServiceContentHandler
   * @see ServiceContext#destroy 
   **/
  
  public void select(Service selection)
    throws SecurityException;

  /**
   * Selects content by specifying the parts of a service to be
   * presented.  If the <code>ServiceContext</code> is
   * already presenting content, the new selection replaces the
   * content being presented. If the
   * <code>ServiceContext</code> is not presenting,
   * successful conclusion of this method results in the
   * <code>ServiceContext</code> entering the
   * <em>presenting</em> state. <p>
   *
   * This method is asynchronous and successful completion of the
   * selection is notified by either a <code>NormalContentEvent</code>
   * or a <code>AlternativeContentEvent</code>.  If failure of the
   * selection can be determined when this method is called, an
   * exception will be generated and the state of the
   * <code>ServiceContext</code> will not change. In this case, any
   * service being presented before this method was called will
   * continue to be presented.<p>
   *
   * If failure of the selection is determined after this method
   * returns, a <code>SelectionFailedEvent</code> will be
   * generated. In this case, the implementation of the method will
   * try to return to presenting the original service (if any). If
   * this is not possible (due, for example, to issues such as tuning
   * or CA) the <code>ServiceContext</code> will enter the
   * <em>not presenting</em> state and a
   * <code>PresentationTerminatedEvent</code> will be generated.<p>
   *
   * If the <code>ServiceContext</code> is currently presenting a service and
   * components of the current service are also to be presented in the
   * newly selected content, these components will continue to be
   * presented and will not be restarted.  If the calling application
   * is not a part of the newly selected content and the application
   * lifecycle policy on the receiver dictates that the calling
   * application be terminated, termination of the application
   * will be affected through the application lifecycle API.<p>
   *
   * Successful completion of a select operation using this method
   * provides <code>ServiceContentHandler</code> instances for all
   * components that are indicated in the <code>components</code>
   * parameter.  Upon entering the <em>presenting</em> state, these
   * <code>ServiceContentHandler</code> instances will have begun
   * presenting their respective content;
   * <code>ServiceMediaHandler</code> instances will be in the
   * <em>started</em> state.  This method will not provide
   * <code>ServiceContentHandler</code> instances for service
   * components for which a locator is not specified.
   *
   * @param components An array of <code>Locator</code> instances
   * representing the parts of this service to be selected.  Each
   * array element must be a locator to either a
   * <code>ServiceComponent</code> or content within a service
   * component (such as an Xlet).
   *
   * @throws InvalidLocatorException If a <code>Locator</code> provided
   * does not reference a selectable service component or selectable
   * content within a service component.
   *
   * @throws InvalidServiceComponentException If a
   * specified service component must be presented in conjunction
   * with another service component not contained in
   * <code>components</code>, if the specified
   * components are not all members of the same service, or if the
   * specified set of components cannot be presented as a coherent
   * whole.
   *
   * @throws SecurityException If, for any valid <code>i</code>, the
   * caller owns this <code>ServiceContext</code> but does not have
   * <code>SelectPermission(components[i], "own")</code>, or if the caller
   * does not own this <code>ServiceContext</code> and does not have
   * <code>SelectPermission(components[i], "*")</code>.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   *
   * @see NormalContentEvent
   * @see AlternativeContentEvent
   * @see SelectionFailedEvent
   * @see PresentationTerminatedEvent
   * @see ServiceContentHandler
   * @see javax.tv.service.navigation.ServiceComponent */
  public void select(Locator[] components)
    throws InvalidLocatorException,
      InvalidServiceComponentException,
      SecurityException;

  /**
   * Causes the <code>ServiceContext</code> to stop presenting content
   * and enter the <em>not presenting</em> state.  Resources used
   * in the presentation will be released, associated
   * <code>ServiceContentHandlers</code> will cease presentation
   * (<code>ServiceMediaHandlers</code> will no longer be in the
   * <em>started</em> state), and a
   * <code>PresentationTerminatedEvent</code> will be posted.<p>
   *
   * This operation completes asynchronously. No action is performed
   * if the <code>ServiceContext</code> is already in the <em>not
   * presenting</em> state.
   *
   * @throws SecurityException If the caller owns this
   * <code>ServiceContext</code> but does not have
   * <code>ServiceContextPermission("stop", "own")</code>, or if the caller
   * does not own this <code>ServiceContext</code> and does not have
   * <code>SelectPermission("stop", "*")</code>.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   */
  public void stop() throws SecurityException;


  /** 
   * Causes the <code>ServiceContext</code> to release all resources
   * and enter the <em>destroyed</em> state. This method indicates
   * that the the <code>ServiceContext</code> must cease further
   * activity, and that it will no longer be used.  After completion
   * of this method, <code>ServiceMediaHandler</code> instances
   * associated with this <code>ServiceContext</code> will have become
   * <em>unrealized</em> or will have been closed.
   *
   * If the <code>ServiceContext</code> is currently in the
   * <em>presenting</em> or <em>presentation pending</em> state, this
   * method will first stop the <code>ServiceContext</code>, causing a
   * <code>PresentationTerminatedEvent</code> to be posted.  After the
   * <code>ServiceContext</code> has moved to the <em>destroyed</em>
   * state, a <code>ServiceContextDestroyedEvent</code> will be
   * posted.<p>
   *
   * This operation completes asynchronously.  No action is performed
   * if the <code>ServiceContext</code> is already in the
   * <em>destroyed</em> state.
   *
   * @throws SecurityException If the caller does not have permission
   * to call <code>stop()</code> on this <code>ServiceContext</code>,
   * or if the caller owns this <code>ServiceContext</code> but does
   * not have <code>ServiceContextPermission("destroy", "own")</code>,
   * or if the caller does not own this <code>ServiceContext</code>
   * and does not have <code>SelectPermission("destroy", "*")</code>.
   *
   * @see #stop */
  public void destroy() throws SecurityException;

  
  /**
   * Reports the current collection of ServiceContentHandlers.  A
   * zero-length array is returned if the <code>ServiceContext</code>
   * is in in the <em>not presenting</em> or <em>presentation
   * pending</em> states.
   *
   * @throws SecurityException If the caller owns this
   * <code>ServiceContext</code> but does not have
   * <code>ServiceContextPermission("getServiceContentHandlers",
   * "own")</code>, or if the caller does not own this
   * <code>ServiceContext</code> and does not have
   * <code>SelectPermission("getServiceContentHandlers", "*")</code>.
   *
   * @return The current <code>ServiceContentHandler</code> instances.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   */
  public ServiceContentHandler[] getServiceContentHandlers()
    throws SecurityException;


  /**
   * Reports the <code>Service</code> being presented in this
   * <code>ServiceContext</code>. If the <code>ServiceContext</code>
   * is currently presenting a service, the <code>Service</code>
   * returned will be a network-dependent representation of the
   * <code>Service</code> indicated in the last successful
   * <code>select()</code> method call. If the
   * <code>ServiceContext</code> is not in the <em>presenting</em>
   * state then <code>null</code> is returned.
   *
   * @return The service currently being presented.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   */
  public Service getService();


  /**
   *
   * Subscribes a listener to receive events related to this
   * <code>ServiceContext</code>.  If the specified listener is currently
   * subscribed, no action is performed.
   *
   * @param listener The <code>ServiceContextListener</code> to subscribe.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code> has been
   * destroyed.
   *
   * @see ServiceContextEvent
   */
  public void addListener(ServiceContextListener listener);


  /** 
   * Unsubscribes a listener from receiving events related to this
   * <code>ServiceContext</code>.  If the specified listener is not currently
   * subscribed, no action is performed.
   *
   * @param listener The <code>ServiceContextListener</code> to unsubscribe.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code> has been
   * destroyed.
   */
  public void removeListener(ServiceContextListener listener);
}

