package org.ocap.hn.upnp.common;

/**
 * This interfaces is an abstract representation of a UPnP device icon.
 * It provides the data constituting a device icon that is
 * independent of the network interface on which it has been advertised.
 */
public interface UPnPDeviceIcon {

    /**
     * Gets the color depth of this icon in bits.
     *
     * @return The color depth of the icon in bits.
     */
    int getColorDepth();

    /**
     * Gets the height of this icon in pixels.
     *
     * @return The height of the icon in pixels.
     */
    int getHeight();

    /**
     * Gets the mimetype for this UPnPDeviceIcon. For UPnPDeviceIcons conforming
     * to UPnP Device Architecture 1.0, this should be of the form
     * image/xxxx where xxxx is the specific image subtype.
     *
     * @return  The mimetype string for this icon.
     */
    String getMimeType();

    /**
     * Gets the width of this icon in pixels.
     *
     * @return The width of the icon in pixels.
     */
    int getWidth();

}
