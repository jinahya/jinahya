package org.ocap.hn.upnp.client;

import org.ocap.hn.upnp.common.UPnPAdvertisedDevice;


/**
 * This interface provides the client representation of a 
 * UPnP device, associated with a single IP address.
 */
public interface UPnPClientDevice extends UPnPAdvertisedDevice {

    /**
     * Returns the parent UPnP Device of this device, if any.
     *
     * @return This device's parent device. Returns null if this device
     * has no parent.
     */
    UPnPClientDevice getParentDevice();

    /**
     * Gets the embedded devices for this UPnP Device.
     *
     * @return The embedded devices for this device.  If this device
     * has no embedded devices, returns a zero length array.
     * Returns only the next level of embedded devices, not
     * recursing through embedded devices for subsequent
     * levels of embedded devices.
     */
    UPnPClientDevice[] getEmbeddedDevices();

    /**
     * Gets the services supported by this device.  Does not return
     * services held in embedded devices.
     *
     * @return    The services supported by this device.  If the
     * serviceList element in the device description is
     * empty, this method returns a zero length array.
     */
    UPnPClientService[] getServices();

    /**
     * Gets the icons of this device. This returned array is
     * derived from the icon elements within the {@code iconList} element
     * of a device description.
     * If the iconList element in the device description is empty
     * or not present, returns a zero length array.
     *
     * @return The icons that the device declares.
     */
    UPnPClientDeviceIcon[] getIcons();
}
