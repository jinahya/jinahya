package org.ocap.hn.upnp.client;

import org.ocap.hn.upnp.common.UPnPAdvertisedDeviceIcon;


/**
 * This interface is the client representation of a UPnP Device Icon.
 */
public interface UPnPClientDeviceIcon extends UPnPAdvertisedDeviceIcon
{
    /**
     * Gets the UPnP device that this icon is associated with.
     *
     * @return The device that this icon is associated with.
     */
    public UPnPClientDevice getDevice();
}
