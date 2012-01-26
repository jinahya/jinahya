package org.ocap.hn.upnp.common;

import java.net.InetAddress;
import org.w3c.dom.Document;

/**
 * This interface represents a UPnP device as it is advertised on a particular
 * network. It provides the data constituting the device, portions of
 * which depend on the network interface on which it is advertised.
 * Corresponds to the information carried in the UPnP device description
 * document.
 */
public interface UPnPAdvertisedDevice extends UPnPDevice {

    /**
     * Returns the parent UPnP Device of this device, if any.
     *
     * @return This device's parent device. Returns null if this device
     * has no parent.
     */
    // UPnPAdvertisedDevice getParentAdvertisedDevice();

    /**
     * Gets the embedded devices for this UPnP Device.
     *
     * @return The embedded devices for this device.  If this device
     * has no embedded devices, returns a zero length array.
     * Returns only the next level of embedded devices, not
     * recursing through embedded devices for subsequent
     * levels of embedded devices.
     */
    UPnPAdvertisedDevice[] getEmbeddedAdvertisedDevices();

    /**
     * Gets the icons of this device. This returned array is
     * derived from the icon elements within the {@code iconList} element
     * of a device description.
     * If the iconList element in the device description is empty
     * or not present, returns a zero length array.
     *
     * @return The icons that the device declares.
     */
    UPnPAdvertisedDeviceIcon[] getAdvertisedIcons();

    /**
     * Gets the services supported by this device.  Does not return
     * services held in embedded devices.
     *
     * @return    The services supported by this device.  If the
     * serviceList element in the device description is
     * empty, this method returns a zero length array.
     */
    UPnPAdvertisedService[] getAdvertisedServices();

    /**
     * Returns the IP address from which this device was advertised.
     *
     * @return an InetAddress representing this device's IP address.
     */
    InetAddress getInetAddress();

    /**
     * Gets the UPnP presentation page URL of this device. This
     * value is taken from the value of the presentationURL
     * element within a device description.
     *
     * <p>If the presentationURL is empty or not present, returns
     * the empty String.
     *
     * @return The presentationURL of this device.
     */
    String getPresentationURL();

    /**
     * Reports the base URL for all relative URLs of this device.
     * This value is obtained from the {@code URLBase} element within the
     * device description document.  If this is an embedded device, the
     * {@code URLBase} element of the root device is returned.
     *
     * <p>If the {@code URLBase} property is not specified in the device
     * description document, this method returns the URL from which the
     * device description may be retrieved.
     *
     * @return The base URL for all relative URLs of this UPnP Device.
     */
    String getURLBase();

    /**
     * Gets the device description document in XML.  The form of the document
     * is defined by the UPnP Device Architecture specification.
     *
     * <p>For a root device, returns the document starting with the
     * &lt;?xml&gt; node. For an embedded device, returns the
     * sub-document starting
     * with the &lt;device&gt; node of the embedded device. Returns the
     * complete XML document from the level that is appropriate,
     * including any embedded devices.
     *
     * @return The device description document.
     */
    Document getXML();

}
