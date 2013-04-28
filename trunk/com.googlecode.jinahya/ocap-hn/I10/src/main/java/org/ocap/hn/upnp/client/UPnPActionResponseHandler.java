package org.ocap.hn.upnp.client;

import org.ocap.hn.upnp.common.UPnPResponse;
/**
 * This interface represents a listener for an asynchronous UPnP action
 * response.
 */
public interface UPnPActionResponseHandler
{

    /**
     * Notifies the listener when a UPnP action response is received.
     *
     * @param response The response to a UPnP action: a 
     *                 <code>UPnPActionResponse</code>, a
     *                 <code>UPnPErrorResponse</code>, or a
     *                 <code>UPnPGeneralErrorResponse</code>.
     */
    public void notifyUPnPActionResponse(UPnPResponse response);

}
