package org.ocap.hn.upnp.common;

import org.ocap.hn.upnp.client.UPnPClientService;
import org.ocap.hn.upnp.server.UPnPActionHandler;

/**
 * This class represents a UPnP service action invocation, 
 * carrying only IN arguments and a reference to the action 
 * definition (<code>UPnPAction</code>). It is constructed by a 
 * client application and passed to a <code>UPnPService</code> 
 * via the <code>postActionInvocation</code> method in order to 
 * invoke an action on a UPnP server.
 *
 * @see UPnPClientService#postActionInvocation(UPnPActionInvocation, UPnPActionResponseHandler)
 * @see UPnPActionHandler#notifyActionReceived(UPnPActionInvocation)
 */
public class UPnPActionInvocation
{
    /**
     * Constructs a {@code UPnPActionInvocation} that conforms to
     * the IN argument requirements of its associated {@code UPnPAction}.
     * <p>
     * This constructor ensures that the resulting action invocation
     * provides an argument value, in the proper {@code dataType} format,
     * for each of the IN arguments of the specified UPnP action.
     * <p>
     * For objects created through this constructor,
     * {@link #getArgumentNames()} will report, in order,
     * the required IN argument names of the specified {@code UPnPAction}.
     *
     * @param argVals An array of argument values corresponding,
     *                in order, to the IN arguments of {@code action}.
     * @param action The UPnP action that this action invocation relates to.
     *
     * @throws IllegalArgumentException if {@code argVals} does not conform
     * to the IN argument requirements of {@code action}.
     *
     * @throws NullPointerException if {@code action} is {@code null},
     * or {@code argVals} or any of its array elements is {@code null}.
     */
    public UPnPActionInvocation(String[] argVals, UPnPAction action)
    {

    }

    /**
     * Gets the name of the action as specifed by the action name element
     * in the UPnP service description.  Calls {@code getAction().getName()}.
     *
     * @return the name of the action.
     *
     * @see #getAction()
     */
    public String getName()
    {
        return null;
    }

    /**
     * Gets the argument names specified by this action invocation,
     * in the order they were specified in the constructor.
     *
     * @return The argument names of this action invocation. If no arguments
     * have been specified, returns a zero-length array.
     */
    public String[] getArgumentNames()
    {
        return null;
    }

    /**
     * Gets the value of the specified argument.
     *
     * @param name The name of the argument.
     *
     * @return The value of the argument.
     *
     * @throws IllegalArgumentException if {@code name} does not match one
     * of the argument names specified for this action invocation.
     *
     * @see #getArgumentNames()
     */
    public String getArgumentValue(String name)
    {
        return null;
    }

    /**
     * Gets the {@code UPnPAction} that this {@code UPnPActionInvocation}
     * is associated with.
     *
     * @return The {@code UPnPAction} that this action invocation is
     * associated with.
     */
    public UPnPAction getAction()
    {
        return null;
    }
}
