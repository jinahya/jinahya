package org.ocap.hn.upnp.common;


/**
 * This interface represents a UPnP device icon as it is described on a
 * particular network.
 * It provides the data constituting a UPnP device icon, portions of
 * which depend on the network interface on which it is advertised.
 * Corresponds to the {@code icon} entry in the UPnP device description
 * {@code iconList} element.
 */
public interface UPnPAdvertisedDeviceIcon extends UPnPDeviceIcon {

    /**
     * Gets the URL for retrieval of this icon.
     *
     * @return The URL used to retrieve this icon.
     */
    String getURL();

    /**
     * Gets the UPnP device that this icon is associated with.
     *
     * @return The device that this icon is associated with.
     */
    // public UPnPAdvertisedDevice getAdvertisedDevice();

}
