package org.ocap.hn.upnp.common;

/**
 * This interface is an abstract representation of a UPnP device.
 * It provides the data constituting a UPnP device that is
 * independent of the network interface on which the device is advertised.
 */
public interface UPnPDevice {

    /**
     * Gets the UPnP deviceType of this device. This value is
     * taken from the value of the {@code deviceType} element within the
     * device description.
     *
     * @return The type of this device.
     * If the {@code deviceType} is empty or not present,
     * returns the empty String.
     */
    String getDeviceType();

    /**
     * Gets the UPnP "friendly name" of this device. This value is
     * taken from the value of the {@code friendlyName} element within the
     * device description.
     *
     * @return The {@code friendlyName} of this device.
     * If the {@code friendlyName} is empty or not present, returns the
     * empty String.
     */
    String getFriendlyName();

    /**
     * Gets the UPnP manufacturer of this device. This value is
     * taken from the value of the {@code manufacturer} element within the
     * device description.
     *
     * @return The manufacturer of this device.
     * If the manufacturer is empty or not present, returns the
     * empty String.
     */
    String getManufacturer();

    /**
     * Gets the UPnP manufacturer URL of this device. This value is
     * taken from the value of the {@code manufacturerURL} element
     * within the device description.
     * If the manufacturerURL is empty or not present, returns
     * the empty String.
     *
     * @return The manufacturerURL of this device.
     */
    String getManufacturerURL();

    /**
     * Gets the UPnP model description of this device.
     * This value is taken from the value of the
     * {@code modelDescription} element within the device description.
     * If the modelDescription is empty or not present, returns
     * the empty String.
     *
     * @return The modelDescription of this device.
     */
    String getModelDescription();

    /**
     * Gets the UPnP model name of this device. This value is
     * taken from the value of the {@code modelName} element within the device
     * description.
     *
     * @return The {@code modelName} of this device.
     * If the {@code modelName} is empty or not present, returns the
     * empty String.
     */
    String getModelName();

    /**
     * Gets the UPnP model number of this device. This value is
     * taken from the value of the {@code modelNumber} element within the
     * device description.
     * If the modelNumber is empty or not present, returns the
     * empty String.
     *
     * @return The modelNumber of this device.
     */
    String getModelNumber();

    /**
     * Gets the UPnP model URL of this device. This value is
     * taken from the value of the {@code modelURL} element within the
     * device description.
     * If the modelURL is empty or not present, returns the empty
     * String.
     *
     * @return The modelURL of this device.
     */
    String getModelURL();

    /**
     * Gets the UPnP serial number of this device. This value is
     * taken from the value of the {@code serialNumber} element within the
     * device description.
     * If the serialNumber is empty or not present, returns the
     * empty String.
     *
     * @return The serialNumber of this device.
     */
    String getSerialNumber();

    /**
     * Gets the UPnP specVersion major and minor values of this
     * UPnP device, or of the root UPnP device containing this device.
     * This value is taken from the value of the major and minor sub
     * elements of the {@code specVersion} element within the device
     * description.
     * The format of the returned String is the &lt;major&gt;
     * value, followed by '.', followed by the &lt;minor&gt; value.
     *
     * @return The UPnP specVersion of this device.
     */
    String getSpecVersion();

    /**
     * Gets the UPnP Unique Device Name of this device. This value
     * is taken from the value of the {@code UDN} element
     * within the device description.
     *
     * @return The UDN of this device.
     * If the UDN is empty or not present, returns the empty
     * String.
     */
    String getUDN();

    /**
     * Gets the UPnP Universal Product Code of this device. This
     * value is taken from the value of the {@code UPC} element
     * within the device description.
     * If the UPC is empty or not present, returns the empty
     * String.
     *
     * @return The UPC of this device.
     */
    String getUPC();

    /**
     * Reports whether this UPnP device is a UPnP root device.
     *
     * @return true if this UPnP device represents a root device,
     * false if not.
     */
    boolean isRootDevice();

}
