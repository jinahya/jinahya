package org.ocap.hn.upnp.client;

import org.ocap.hn.upnp.common.UPnPAdvertisedService;
import org.ocap.hn.upnp.common.UPnPActionInvocation;
import org.ocap.hn.upnp.common.UPnPAction;


/**
 * This interface is the client representation of a UPnP 
 * service. 
 */
public interface UPnPClientService extends UPnPAdvertisedService
{

    /**
     * Gets the UPnP device that this service is a part of.
     *
     * @return The device that this service is a part of.
     */
    UPnPClientDevice getDevice();

    /**
     * Posts an action to the network.  Sends the action from the control
     * point to the device the service is in.  The device MAY be on 
     * the local host.  If no handler is set when this method is 
     * called, the response is consumed by the implementation in an 
     * implementation-specific fashion.
     *
     * @param actionInvocation The action invocation to post.
     *
     * @param handler The handler that will be notified when the action
     *      response is received. May be null, in which case the
     *      action response will be discarded.
     *
     * @throws NullPointerException if action is null.
     *
     * @see UPnPActionInvocation
     */
    public void postActionInvocation(UPnPActionInvocation actionInvocation,
            UPnPActionResponseHandler handler);

    /**
     * Adds a state variable listener to this <code>UPnPClientService</code>.
     * If this service has evented state variables,
     * this method will cause the control point to attempt to
     * subscribe to the service if it is not already subscribed.
     * See UPnP Device Architecture specification for
     * UPnP service and state variable subscription. 
     *  
     * <p>Adding a listener which is the same instance as a 
     * previously added (and not removed) listener has no effect.
     *
     * @param listener The listener to add.
     *
     * @see #setSubscribedStatus(boolean)
     */
    public void addStateVariableListener(UPnPStateVariableListener listener);

    /**
     * Removes a change listener.
     *
     * @param listener The listener to remove.
     */
    public void removeStateVariableListener(UPnPStateVariableListener listener);

    /**
     * Attempts to subscribe or unsubscribe the control point to/from
     * this service.  Changes to subscription status are signaled
     * asynchronously via the {@link UPnPStateVariableListener} interface.
     *  
     * @param subscribed True to subscribe to evented state variable
     *                   updates, false to unsubscribe.
     *
     * @throws UnsupportedOperationException if {@code subscribed} is
     * {@code true} but the service has no evented state variables.
     */
    public void setSubscribedStatus(boolean subscribed);

    /**
     * Gets the subscription status of the service.
     * Defaults to subscribed if the service has evented state variables;
     * false otherwise.
     *
     * @return True if the control point is presently registered to
     *      receive UPnP events from the service, false if not.
     */
    public boolean getSubscribedStatus();

    /**
     * Gets a UPnP state variable from the UPnP description of this
     * service.  Supported state variable names are provided by a UPnP device
     * in the name element of each stateVariable element in a device
     * service description.
     *
     * @param stateVariableName The name of the state variable to get.
     *
     * @return The state variable corresponding to the
     * <code>stateVariableName</code> parameter.
     *
     * @throws IllegalArgumentException if the <code>stateVariableName</code>
     * does not match a state variable name in this service.
     */
    UPnPClientStateVariable getStateVariable(String stateVariableName);

    /**
     * Gets all of the UPnP state variables supported by this
     * service.  UPnP state variable information is taken from the
     * {@code stateVariable} elements in the UPnP service description.
     *
     * @return  The UPnP state variables supported by this
     * service. If the service has no state variables, returns a zero-length
     * array.
     */
    UPnPClientStateVariable[] getStateVariables();
}
