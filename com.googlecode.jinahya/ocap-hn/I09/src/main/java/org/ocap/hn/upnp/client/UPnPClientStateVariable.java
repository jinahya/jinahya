package org.ocap.hn.upnp.client;

import org.ocap.hn.upnp.common.UPnPAdvertisedStateVariable;


/**
 * This interface is the client representation of a UPnP state 
 * variable. 
 */
public interface UPnPClientStateVariable extends UPnPAdvertisedStateVariable
{

    /**
     * Gets the value of the UPnP state variable corresponding to this
     * <code>UPnPClientStateVariable</code> object.
     *
     * @return The most recently received evented value of the state
     *         variable.
     *
     * @throws UnsupportedOperationException if the UPnP state variable
     *      corresponding to this <code>UPnPStateVariable</code> object is not
     *      evented as can be determined by calling the
     *      <code>isEvented()</code> method.
     */
    public String getEventedValue();

    /**
     * Gets the UPnP service that this state variable is a member of.
     *
     * @return The service that this state variable is part of.
     */
    public UPnPClientService getService();

}
