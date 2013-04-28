package org.ocap.hn.upnp.common;

import org.ocap.hn.upnp.server.UPnPActionHandler;
import org.ocap.hn.upnp.client.UPnPActionResponseHandler;

/**
 * The class represents a response to a successfully completed 
 * UPnP action. It carries only the OUT arguments from the 
 * action. Instances of this class are constructed by the 
 * <code>UPnPActionHandler</code> on a UPnP server, and are 
 * passed to a client in the {@code UPnPActionResponseHandler}.
 *
 * @see UPnPActionHandler#notifyActionReceived(UPnPActionInvocation)
 * @see UPnPActionResponseHandler#notifyUPnPActionResponse(UPnPResponse)
 */
public class UPnPActionResponse extends UPnPResponse
{
    /**
     * Constructs a {@code UPnPActionResponse} that conforms to
     * the OUT argument requirements of its associated {@code UPnPAction}.
     * <p>
     * This constructor ensures that the resulting action response
     * provides an argument value, in the proper {@code dataType} format,
     * for each of the OUT arguments of the UPnP action reported by
     * {@code actionInvocation.getAction()}.
     * <p>
     * For objects created through this constructor,
     * {@link #getArgumentNames()} will report, in order,
     * the required OUT argument names of the associated UPnP action.
     *
     * @param argVals An array of argument values corresponding,
     *                in order, to the OUT arguments of
     *                {@code actionInvocation.getAction()}.
     *
     * @param actionInvocation The action invocation that this action response
     *                         relates to.
     *
     * @throws IllegalArgumentException if {@code argVals} does not conform
     * to the OUT argument requirements of {@code actionInvocation.getAction()}.
     *
     * @throws NullPointerException if {@code action} is {@code null},
     * or {@code argVals} or any of its array elements is {@code null}.
     */
    public UPnPActionResponse(String[] argVals,
                              UPnPActionInvocation actionInvocation)
    {

    }

    /**
     * Gets the output argument names specified by this action response,
     * in the order they were specified in the constructor.
     *
     * @return The action response output argument names. If the 
     *         action response has no output arguments, returns a
     *         zero-length array.
     */
    public String [] getArgumentNames()
    {
        return null;
    }

    /**
     * Gets the output argument values specified by this action response,
     * in the order they were specified in the constructor.
     *
     * @return The action response output argument values. If the 
     *         action response has no output arguments, returns a
     *         zero-length array.
     */
    public String [] getArgumentValues()
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
     * of the argument names specified for this action response.
     *
     * @see #getArgumentNames()
     */
    public String getArgumentValue(String name)
    {
        return null;
    }

}
