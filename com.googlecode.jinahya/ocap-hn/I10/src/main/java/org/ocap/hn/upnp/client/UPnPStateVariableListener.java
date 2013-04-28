package org.ocap.hn.upnp.client;

/**
 * This interface represents a client variable change listener 
 * for a {@code UPnPClientService} object.
 */
public interface UPnPStateVariableListener extends java.util.EventListener
{

    /**
     * Notifies the listener that the value of the UPnP state variable being
     * listened to has changed.
     *
     * @param variable The UPnP state variable that changed.
     */
    public void notifyValueChanged(UPnPClientStateVariable variable);

    /**
     * Notifies the listener that the control point has successfully
     * subscribed to receive state variable eventing from the specified
     * service.
     *
     * @param service The UPnP service that was subscribed to.
     */
    public void notifySubscribed(UPnPClientService service);

    /**
     * Notifies the listener that the control point has successfully
     * unsubscribed from receiving state variable eventing from the specified
     * service.
     *
     * @param service The UPnP service that was un-subscribed from.
     */
    public void notifyUnsubscribed(UPnPClientService service);
}
