package org.ocap.hn.upnp.server;


import org.ocap.hn.upnp.common.UPnPAction;
import org.ocap.hn.upnp.common.UPnPAdvertisedService;
import org.ocap.hn.upnp.common.UPnPService;

/**
 * This interface provides the server representation of a UPnP 
 * service created when a privileged application uses the 
 * <code>UPnPDeviceManager</code> in the local host. 
 */
public interface UPnPManagedService extends UPnPService
{
    /**
     * Sets an action handler for this service, replacing any prior 
     * action handler. 
     *  
     * @param handler UPnPActionHandler to be set for this managed
     *               service.
     *  
     * @return Previous action handler, if any; null, if none.
     * 
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     * 
     * @see UPnPActionHandler
     */
    public UPnPActionHandler setActionHandler(UPnPActionHandler handler)
    						throws SecurityException;
    
    /**
     * Gets the current action handler for this service. If no action
     * server is registered, returns null.
     * 
     * @return Current action handler, if any; null if none. 
     * 
     * @see UPnPActionHandler
     */
    public UPnPActionHandler getActionHandler();

    /**
     * Control whether the service responds to UPnP 
     * QueryStateVariable actions. 
     *  
     * <p>If respond is true, the UPnP stack will respond to the 
     * QueryStateVariable action invocation with a value from any 
     * registered <code>UPnPManagedStateVariableHandler</code>, or 
     * with the most recently set value for the state value if no 
     * handler is registered, or with the default value of the state 
     * variable if no handler is registered and no calls to 
     * <code>UPnPManagedStateVariable.setValue()</code> have been 
     * made. If respond is false, responds with an error as defined 
     * by UPnP Device Architecture 1.0, with UPnPError errorCode of 
     * 401 (Invalid Action). 
     *  
     * <p>If respond is true and a request is received with a state 
     * variable name that is not part of this service, responds with 
     * an error as defined by UPnP Device Architecture 1.0, with 
     * UPnPError errorCode of 404 (Invalid Var). 
     * 
     * @param respond True to cause the stack to respond to 
     *                QueryStateVariable actions, returning the
     *                current state variable value to the UPnP
     *                control point. False to refuse
     *                QueryStateVariable actions with UPnP errorCode
     *                of 401 (Invalid Action).
     * 
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public void respondToQueries(boolean respond) throws SecurityException;

    /**
     * Gets the representations of this service on the network interfaces
     * on which is it advertised.
     * Since the UPnP device and service descriptions contain network-dependent
     * information, there can be multiple {@code UPnPAdvertisedService}
     * objects associated with a single {@code UPnPManagedService}.
     *
     * @return The network representations of this {@code UPnPManagedService}.
     * Returns a zero-length array if
     * the service has not been advertised on a network interface.
     */
    public UPnPAdvertisedService[] getAdvertisedServices();


    /**
     * Gets the state variables that are part of this service definition.
     *  
     * @return The state variables that
     *      are part of this service definition. If this service
     *      defines no state variables, returns a zero-length
     *      array.
     */
    public UPnPManagedStateVariable[] getStateVariables();


    /**
     * Gets the UPnP device that this service is a part of.
     *  
     * @return The device that this service is a part of.
     */
    public UPnPManagedDevice getDevice();

    /**
     * Sets a <code>UPnPStateVariableHandler</code>
     * to this <code>UPnPManagedService</code>.
     * The handler provides the ability to respond dynamically to
     * the QueryStateVariable action, and to be notified of subscription
     * requests.
     *
     * <p>Only a single handler may be registered at any point in
     * time. Subsequent requests to add a handler replace any
     * existing handler.
     *
     * @param handler The handler to add. May be null, removing any
     *                previously set handler.
     *
     * @return The previously set UPnPStateVariableHandler,
     *         if any. Returns null if no prior handler set.
     *
     */
    public UPnPStateVariableHandler
            setHandler(UPnPStateVariableHandler handler);

    /**
     * Reports the subscription status of the service.
     *
     * @return True if any control points are presently subscribed to this
     * service.
     */
    public boolean getSubscribedStatus();

    ///////////  Methods inherited from UPnPService  //////////

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the service has been advertised, this method returns the same value
     * as {@code getAdvertisedServices()[0].getAction()}.
     *
     * @throws IllegalArgumentException {@inheritDoc}
     */
    UPnPAction getAction(String actionName);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the service has been advertised, this method returns the same value
     * as {@code getAdvertisedServices()[0].getActions()}.
     */
    UPnPAction[] getActions();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the service has been advertised, this method returns the same value
     * as {@code getAdvertisedServices()[0].getServiceId()}.
     */
    String getServiceId();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the service has been advertised, this method returns the same value
     * as {@code getAdvertisedServices()[0].getServiceType()}.
     */
    String getServiceType();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the service has been advertised, this method returns the same value
     * as {@code getAdvertisedServices()[0].getSpecVersion()}.
     */
    String getSpecVersion();
}
