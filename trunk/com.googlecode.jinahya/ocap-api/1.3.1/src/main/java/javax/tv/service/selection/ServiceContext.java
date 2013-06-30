/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
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
 * pending</em> state for the most recent <code>select()</code> call.
 * Otherwise, if the state before the failed select operation was
 * <em>not presenting</em>, the <code>ServiceContext</code> will
 * return to that state and a <code>PresentationTerminatedEvent</code>
 * be generated.
 * Likewise, if the state before the failed select operation was
 * <em>presentation pending</em>, the <code>ServiceContext</code> will
 * move to the <em>not presenting</em> state and generate a
 * <code>PresentationTerminatedEvent</code>.
 * If the state before the failed select operation was
 * <em>presenting</em>, it will attempt to return to that previous
 * state, which can result in a <code>NormalContentEvent</code> or
 * <code>AlternativeContentEvent</code> if this is possible or a
 * <code>PresentationTerminatedEvent</code> if it is not possible.<p>
 *
 * The <em>not presenting</em> state is entered due to service
 * presentation being stopped which is reported by a
 * {@link PresentationTerminatedEvent}. Service
 * presentation stops when an application calls the
 * <code>stop()</code> method or
 * when continued presentation becomes impossible -- due, for example,
 * to a a change in the environment or a failed service selection attempt.
 * <p>
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
 * @see ServiceContextListener 
 */
public interface ServiceContext
{

    /** 
     * Selects a service to be presented in this
     * <code>ServiceContext</code>.  If the <code>ServiceContext</code>
     * is already presenting content, the new selection replaces the
     * content being presented. If the <code>ServiceContext</code> is
     * not presenting, successful conclusion of this operation results in
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
     * API.
     * <p>
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
     * <p>
     * <span style="color: red;">
     * If the <code>Service</code> that is currently selected in this
     * <code>ServiceContext</code> is re-selected, its auto-start
     * components are ignored and no change is made to the content
     * being presented.
     * A {@link NormalContentEvent} is generated immediately.
     * </span>
     * <p>
     * If a service with no auto-start components is selected, the
     * <code>ServiceContext</code> generates a
     * <code>NormalContentEvent</code> and enters the <em>presenting</em>
     * state, but no <code>ServiceContentHandler</code>s are created.
     * 
     * @param selection The <code>Service</code> the service to be
     * selected.
     *
     * @throws SecurityException If the caller does not have
     * <code>SelectPermission(selection.getLocator(), "own")</code>.
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
     * @see SelectPermission
     */
    public void select(Service selection) throws SecurityException;

    /** 
     * Selects content by specifying the parts of a service to be
     * presented.  If the <code>ServiceContext</code> is
     * already presenting content, the new selection replaces the
     * content being presented. If the
     * <code>ServiceContext</code> is not presenting,
     * successful conclusion of this operation results in the
     * <code>ServiceContext</code> entering the
     * <em>presenting</em> state. <p>
     *
     * If failure of the selection operation
     * is determined in the execution of this method, an
     * exception is generated and the state of the
     * <code>ServiceContext</code> does not change.
     * (Note that such sychronous failure may result from a failure
     * to select the content corresponding to <em>any</em> locator
     * in the array.)
     * In this case, any
     * service being presented before this method was called will
     * continue to be presented.
     * <p>
     * If the method returns successfully, then the selection operation
     * proceeds asynchronously.
     * Successful completion of the operation will be signaled by either
     * a single
     * <code>NormalContentEvent</code> or a single
     * <code>AlternativeContentEvent</code>.  
     * If the content corresponding to any of the specified locators can
     * be successfully presented then the selection operation is
     * considered successful, i.e., even if attempts to present content
     * corresponding to some locators fail.
     * <p>
     * If failure of the selection is determined asynchronously,
     * a <code>SelectionFailedEvent</code> will be
     * generated indicating the reason for the failure.
     * (Note that such asychronous failure must result from a failure
     * to select the content corresponding to <em>all</em> locators
     * in the array.)
     * If the selection fails due to multiple reasons, then the
     * reason code will be <code>SelectionFailedEvent.OTHER</code>.
     * After a <code>SelectionFailedEvent</code> is generated,
     * the <code>ServiceContext</code> will
     * try to return to presenting the original service, if any. If
     * this is not possible (due, for example, to issues such as tuning
     * or conditional access), then the <code>ServiceContext</code>
     * will enter the <em>not presenting</em> state and a
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
     * components indicated in the <code>components</code>
     * parameter that were successfully presented.
     * Upon entering the <em>presenting</em> state, these
     * <code>ServiceContentHandler</code> instances will have begun
     * presenting their respective content;
     * <code>ServiceMediaHandler</code> instances will be in the
     * <em>started</em> state.  This method will not provide
     * <code>ServiceContentHandler</code> instances for service
     * components for which a locator is not specified.
     * <p>
     * For locators referencing a service component that is not
     * selectable and for which there is no <code>ServiceContentHandler</code>
     * defined, <code>InvalidLocatorException</code> is thrown if the
     * condition can be detected without causing the method to block.
     * For example, implementations of this method do not block while
     * waiting for network access or tuning if such is required to determine
     * whether a specific component can be succesfully presented.
     * When such an error condition is discovered after this method returns,
     * a <code>SelectionFailedEvent</code> with reason code
     * <code>MISSING_HANDLER</code> is generated.
     * <p>
     * In addition, selection of content involving service-bound xlets is
     * subject to some <a href="doc-files/select-xlets.html">special rules</a>.
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
     * caller does not have
     * <code>SelectPermission(components[i], "own")</code>.
     *
     * @throws IllegalStateException If the <code>ServiceContext</code>
     * has been destroyed.
     *
     * @throws IllegalArgumentException If <code>components</code> is a
     * zero-length array.
     *
     * @see NormalContentEvent
     * @see AlternativeContentEvent
     * @see SelectionFailedEvent
     * @see PresentationTerminatedEvent
     * @see ServiceContentHandler
     * @see javax.tv.service.navigation.ServiceComponent
     * @see SelectionPermission
     */
    public void select(Locator[] components)
        throws InvalidLocatorException, InvalidServiceComponentException,
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
     * @throws SecurityException If the caller does not have
     * <code>ServiceContextPermission("stop", "own")</code>.
     *
     * @throws IllegalStateException If the <code>ServiceContext</code>
     * has been destroyed.
     *
     * @see ServiceContextPermission
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
     * to call {@link #stop} on this <code>ServiceContext</code>,
     * or if the caller does
     * not have <code>ServiceContextPermission("destroy", "own")</code>.
     *
     * @see #stop
     * @see ServiceContextPermission
     */
    public void destroy() throws SecurityException;

    /** 
     * Reports the current collection of ServiceContentHandlers. The order
     * of the ServiceContentHandlers in the returned array is arbitrary.
     * A zero-length array is returned
     * <ul>
     * <li> if the <code>ServiceContext</code> is in the
     * <em>not presenting</em> or <em>presentation pending</em> states,
     * <li> if the <code>ServiceContext</code> is in the <em>presenting</em>
     * state, but the currently selected service contains no service
     * components, or
     * <li> if the <code>ServiceContext</code> is in the <em>presenting</em>
     * state, but there are no ServiceContentHandlers defined for the
     * types of service components which are being presented.
     * </ul>
     *
     * @throws SecurityException If the caller does not have
     * <code>ServiceContextPermission("getServiceContentHandlers",
     * "own")</code>.
     *
     * @return The current <code>ServiceContentHandler</code> instances.
     *
     * @throws IllegalStateException If the <code>ServiceContext</code>
     * has been destroyed.
     *
     * @see ServiceContextPermission
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
