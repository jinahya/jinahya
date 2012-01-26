package org.ocap.hn.upnp.server;

import org.ocap.hn.upnp.common.UPnPActionInvocation;
import org.ocap.hn.upnp.common.UPnPResponse;
import org.ocap.hn.upnp.common.UPnPActionResponse;
import org.ocap.hn.upnp.common.UPnPErrorResponse;

/**
 * This class represents an action handler an application can use to receive
 * action requests and respond to them. 
 * A <code>UPnPActionHandler</code> must be provided with the 
 * service description in a call to 
 * <code>UPnPDeviceManager.createService()</code>, or in a call 
 * to <code>UPnPManagedService.setActionHandler()</code> .
 */
public interface UPnPActionHandler
{

    /**
     * Notifies an application registered as an action handler that an action
     * invocation has been received for the {@code UPnPManagedService}
     * it is associated with.  The handler must respond with a properly-formed
     * {@code UPnPResponse}, per the UPnP Device Architecture specification.
     *
     * @param action The UPnP action invocation received. 
     *
     * @return The response to the action: either a 
     *         {@link UPnPActionResponse} or a
     *         {@link UPnPErrorResponse}. A null return
     *         indicates the action was not handled.
     */
    public UPnPResponse notifyActionReceived(UPnPActionInvocation action);

    /**
     * Notifies an application registered as an action handler that 
     * it has been replaced by another 
     * <code>UPnPActionHandler</code>. 
     *  
     * @param replacement The replacement UPnPActionHandler.
     *
     */
    public void notifyActionHandlerReplaced(UPnPActionHandler replacement);
}
