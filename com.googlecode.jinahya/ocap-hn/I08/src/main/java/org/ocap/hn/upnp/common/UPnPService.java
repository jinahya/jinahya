package org.ocap.hn.upnp.common;


/**
 * This interface is an abstract representation of a UPnP service.
 * It provides the data constituting a UPnP service that is
 * independent of the network interface on which it has been advertised.
 */
public interface UPnPService {

    /**
     * Gets the named action from this service.
     *
     * @param actionName The name of the UPnPAction to retrieve.
     *
     * @return The UPnPAction object from this service with the
     * matched name.
     *
     * @throws IllegalArgumentException if the
     * <code>actionName</code> does not match an action
     * name in this service.
     */
    UPnPAction getAction(String actionName);

    /**
     * Gets the actions that can be used with this service.
     *
     * @return An array of <code>UPnPAction</code>s. If the service
     * has no actions, returns an zero-length array.
     */
    UPnPAction[] getActions();

    /**
     * Gets the UPnP serviceId of this service. This value is taken
     * from the value of the {@code serviceId} element within the device
     * description.
     *
     * @return The serviceId of this service.
     */
    String getServiceId();

    /**
     * Gets the UPnP serviceType of this service. This value is
     * taken from the value of the {@code serviceType} element within the
     * device description.
     *
     * @return The type of this service.
     */
    String getServiceType();

    /**
     * Gets the UPnP specVersion major and minor values of this
     * service. This value is taken from the value of the major
     * and minor sub-elements of the {@code specVersion} element within the
     * service description.
     * The format of the returned String is the &lt;major&gt;
     * value, followed by '.', followed by the &lt;minor&gt; value.
     *
     * @return The UPnP specVersion of this service.
     */
    String getSpecVersion();
}
