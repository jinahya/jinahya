package org.ocap.hn.upnp.server;

import org.ocap.hn.upnp.common.UPnPAdvertisedDeviceIcon;
import org.ocap.hn.upnp.common.UPnPDeviceIcon;


/**
 * This class represents a UPnP Device Icon with associated 
 * binary data for a {@code UPnPManagedDevice}.
 */
public class UPnPManagedDeviceIcon implements UPnPDeviceIcon
{
    /**
     * Construct the instance. 
     *  
     * @param mimetype The mimetype of this icon in the form 
     *                 image/xxxx.
     * @param width The width of this icon in pixels. 
     * @param height The height of this icon in pixels. 
     * @param colordepth The color depth of this icon in bits. 
     * @param data A byte array containing the binary icon data.
     *             The contents of the array are copied into the resulting
     *             {@code UPnPManagedDeviceIcon} object.
     *             No validation is performed on the array, but it
     *             should contain data consistent with the other
     *             parameters to the constructor.
     */
    public UPnPManagedDeviceIcon(String mimetype, int width, int height,
                                 int colordepth, byte[] data)
    {

    }

    /**
     * Gets the binary data that represents this icon. 
     * 
     * @return An array containing a copy of the binary icon data.
     */
    public byte[] getData()
    {
        return null;
    }

    /**
     * Gets the network representations of this
     * <code>UPnPManagedDeviceIcon</code>.
     * Since the UPnP device description {@code iconList} element contains
     * information specific to the network interface on which it is advertised,
     * there can be multiple {@code UPnPAdvertisedDeviceIcon} objects
     * associated with a single {@code UPnPManagedDeviceIcon}.
     *
     * @return The network representations of this
     * {@code UPnPManagedDeviceIcon}.  Returns a zero-length array if
     * the corresponding UPnP device has not been advertised on a network
     * interface.
     */
    public UPnPAdvertisedDeviceIcon[] getAdvertisedDeviceIcons()
    {
        return null;
    }

    ////////  Methods inherited from UPnPDeviceIcon  ////////

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP device has been advertised,
     * this method returns the same value as
     * {@code getAdvertisedDeviceIcons()[0].getColorDepth()}.
     */
    public int getColorDepth()
    {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP device has been advertised,
     * this method returns the same value as
     * {@code getAdvertisedDeviceIcons()[0].getHeight()}.
     */
    public int getHeight()
    {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP device has been advertised,
     * this method returns the same value as
     * {@code getAdvertisedDeviceIcons()[0].getMimeType()}.
     */
    public String getMimeType()
    {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP device has been advertised,
     * this method returns the same value as
     * {@code getAdvertisedDeviceIcons()[0].getWidth()}.
     */
    public int getWidth()
    {
        return 0;
    }

}
