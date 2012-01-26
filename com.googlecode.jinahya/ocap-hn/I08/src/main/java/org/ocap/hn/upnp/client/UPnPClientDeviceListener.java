package org.ocap.hn.upnp.client;

/**
 * This interface represents a listener to UPnP device availability on a home
 * network.
 *
 * @see org.ocap.hn.upnp.server.UPnPManagedDeviceListener
 */
public interface UPnPClientDeviceListener extends java.util.EventListener
{

    /**
     * Notifies the listener that a UPnP device was added to a home network.
     *
     * @param device The <code>UPnPDevice</code> that was added.
     */
    public void notifyDeviceAdded(UPnPClientDevice device);

    /**
     * Notifies the listener that a UPnP device was removed from a 
     * home network, or did not renew its advertisement prior to 
     * expiration of the prior advertisement. 
     *
     * @param device The <code>UPnPDevice</code> that was removed.
     */
    public void notifyDeviceRemoved(UPnPClientDevice device);

}
