package org.ocap.hn.upnp.server;

/**
 * This interface represents a handler for the evented UPnP state
 * variables on a service.  The hander is called as a result of
 * incoming subscription and query actions, and supplies the state
 * variable values to be evented.
 */
public interface UPnPStateVariableHandler
{

    /**
     * Notifies the listener that a control point has requested the
     * value of a state variable through the {@code QueryStateVariable} action.
     * The handler must return the current value of the
     * requested state variable.
     *
     * @param variable The UPnP state variable that was queried.
     *
     * @return The current value of the state variable.
     */
    public String getValue(UPnPManagedStateVariable variable);

    /**
     * Notifies the listener that a control point has subscribed to 
     * state variable eventing on the specified service.
     * This method is called subsequent to the transmission of
     * subscription response message,
     * but prior to the transmission of the initial event message.
     * The eventing process blocks until this method returns,
     * permitting the handler to set the initial values of the service's
     * state variables as desired.
     *
     * @param service The UPnP service that was subscribed to.
     */
    public void notifySubscribed(UPnPManagedService service);

    /**
     * Notifies the listener that a control point has successfully
     * unsubscribed from state variable eventing on the specified service,
     * or that a prior subscription has expired.
     * This method is called subsequent to the transmission of the
     * unsubscription response message.
     *
     * @param service The UPnP service that was unsubscribed from.
     *  
     * @param remainingSubs The number of remaining active 
     *                      subscriptions to this service.
     */
    public void notifyUnsubscribed(UPnPManagedService service,
                                    int remainingSubs);
}
