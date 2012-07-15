package org.ocap.hn.upnp.common;


/**
 * This interface represents the description of a UPnP service 
 * action, parsed from the UPnP service description XML. It 
 * contains both IN and OUT argument descriptions, but does not 
 * carry any values. 
 */
public interface UPnPAction
{
    /**
     * Gets the name of the action from the action name element in the
     * UPnP service description.
     *
     * @return name of the action.
     */
    public String getName();

    /**
     * Gets the action argument names from the action description in the UPnP
     * service description.
     *
     * @return The IN and OUT argument names for this action, in the order
     * specified by the UPnP service description defining this action.
     * If the action has no arguments, returns a zero length array.
     */
    public String[] getArgumentNames();

    /**
     * Gets the direction of an argument.
     *
     * @param name Name of the argument.
     *
     * @return True if the argument is an input argument.
     *
     * @throws IllegalArgumentException if the name does not represent a
     *      valid argument name for the action.
     */
    public boolean isInputArgument(String name);

    /**
     * Determines whether the specified argument is flagged as a 
     * return value in the service description.
     *
     * @param name Name of the argument.
     *
     * @return true if the argument is flagged as a retval.
     *
     * @throws IllegalArgumentException if the name does not represent a
     *      valid argument name for the action.
     */
    public boolean isRetval(String name);

    /**
     * Gets the UPnP service that this <code>UPnPAction</code>
     * is associated with.
     * The returned {@code UPnPService} object may be cast to a
     * {@link org.ocap.hn.upnp.server.UPnPManagedService UPnPManagedService}
     * by server applications, or to a
     * {@link org.ocap.hn.upnp.client.UPnPClientService UPnPClientService}
     * by client applications.
     *
     * @return The UPnP service that this action is associated with.
     */
    public UPnPService getService();

    /**
     * Gets the UPnP state variable associated with the specified
     * argument name.
     * The returned {@code UPnPStateVariable} object may be cast to a
     * {@link org.ocap.hn.upnp.server.UPnPManagedStateVariable UPnPManagedStateVariable}
     * by server applications, or to a
     * {@link org.ocap.hn.upnp.client.UPnPClientStateVariable UPnPClientStateVariable}
     * by client applications.
     *
     * @param name Name of the argument.
     *
     * @return The UPnP state variable associated with the specified
     *          argument name.
     *
     * @throws IllegalArgumentException if the name does not represent a
     *      valid argument name for the action.
     */
    public UPnPStateVariable getRelatedStateVariable(String name);
}
